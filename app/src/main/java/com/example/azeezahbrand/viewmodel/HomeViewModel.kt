package com.example.azeezahbrand.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azeezahbrand.domain.repository.AuthenticationRepository
import com.example.azeezahbrand.domain.repository.ShoppingRepository
import com.example.azeezahbrand.model.CartItem
import com.example.azeezahbrand.model.Order
import com.example.azeezahbrand.model.OrderItem
import com.example.azeezahbrand.model.Product
import com.example.azeezahbrand.model.productList
import com.example.azeezahbrand.presentation.authentication.AuthenticationState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class HomeViewModel: ViewModel(){
    private val _homeState = MutableStateFlow<HomeState>(HomeState.inActive)
    val homeState: StateFlow<HomeState> get() = _homeState
    val authRepo = AuthenticationRepository()
    val shoppingRepository = ShoppingRepository()


    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: SnapshotStateList<CartItem> get() = _cartItems

    private val _orderItems = mutableStateListOf<Order>()
    val orderItems: SnapshotStateList<Order> get() = _orderItems

    private val _products = mutableStateListOf<Product>()
    val products: SnapshotStateList<Product> get() = _products

    var currentUserID: String? = null
    var currentUser:FirebaseUser? = null


    var userName: String? = null



    init {
        viewModelScope.launch {
            fetchCurrentUserID()
            fetchCartItem()
            fetchOrders()
            fetchProducts()

        }
    }


    private suspend fun fetchCurrentUserID() {
        currentUserID = authRepo.currentUser()?.uid
        currentUser = authRepo.currentUser();
        currentUserID?.let { fetchUserName(it) };
    }


    private suspend fun fetchUserName(userId: String){


        try {
            var result =  authRepo.getUserDocument(userId = userId)
            userName= result.getOrNull()?.get("fullName")?.toString()

        }catch (e: Exception){
            e.printStackTrace()
            // - TODO - Put a log statement here.
        }
    }

    private suspend fun fetchCartItem(){
        val queryFilter = Pair("userId", currentUserID!!)

        try {
            val cartItemList =  shoppingRepository.fetchDocuments<CartItem>("cartItem", queryFilter)
            println(cartItemList);
            _cartItems.addAll(cartItemList)

        }catch (e: Exception){
            e.printStackTrace()
            // - TODO - Put a log statement here.
        }
    }

    private suspend fun fetchOrders(){
        val queryFilter = Pair("userId", currentUserID!!)

        try {
            val orderItemList =  shoppingRepository.fetchDocuments<Order>("orders", queryFilter)
            println(orderItemList);
            _orderItems.addAll(orderItemList)

        }catch (e: Exception){
            e.printStackTrace()
            // - TODO - Put a log statement here.
        }
    }

     fun saveOrderToCollection( cartItems: List<CartItem>, totalPrice: Double, address: String) {

         val orderId = UUID.randomUUID().toString()
         viewModelScope.launch {
             _homeState.value = HomeState.loading
             val order = hashMapOf(
                 "userId" to currentUserID!!,
                 "id" to orderId,
                 "items" to cartItems.map { item ->
                     mapOf(
                         "productName" to item.productName,
                         "size" to item.size,
                         "price" to item.productPrize
                     )
                 },
                 "totalPrice" to totalPrice,
                 "address" to address,
                 "timestamp" to System.currentTimeMillis()
             )
             shoppingRepository.saveDocument("orders", order, orderId)
//             _homeState.value = HomeState.Success(order)
             // Add to Firestore or any other database
             // Firestore.collection("orders").add(order)
         }
     }

     fun deleteCartItems() {
         val queryFilter = Pair("userId", currentUserID!!)
         viewModelScope.launch {
             _cartItems.clear()
             shoppingRepository.deleteMany("cartItem", queryFilter);
         }
        // Example Firestore query to delete all cart items for the user

    }

    fun fetchProducts() {
        // Fetch dummy data
        _products.addAll(productList)
    }

    fun addToCartList (cartItem: CartItem){
         // To simulate quick UI update, we will first add item to local List
        // First we would create a shallow copy of the current state of the cart, add the newItem to the cart and then
        _cartItems.add(cartItem)

        //  then persist the item addition in firestore
        viewModelScope.launch {
            try {

                val cartItemId = UUID.randomUUID().toString()
                cartItem.id = cartItemId
                cartItem.userId=currentUserID!!
                shoppingRepository.saveDocument("cartItem", cartItem, cartItem.id)

            }catch (e: Exception){
                e.printStackTrace()
                // You can add a log here to debug if you are getting unexpected result.
            }
        }


    }

    fun deleteCartList (cartItem: CartItem){


        //  then persist the item addition in firestore
        viewModelScope.launch {
            try {


                shoppingRepository.delete( "cartItem", docId =  cartItem.id)

            }catch (e: Exception){
                e.printStackTrace()
                // You can add a log here to debug if you are getting unexpected result.
            }
        }


    }
}


sealed class HomeState {
    object loading : HomeState()
    object inActive : HomeState()
    data class Success(val response: Any?) : HomeState()
    data class Error(val message: Any) : HomeState()
}