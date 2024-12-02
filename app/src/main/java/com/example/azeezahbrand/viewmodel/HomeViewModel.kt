package com.example.azeezahbrand.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azeezahbrand.domain.repository.AuthenticationRepository
import com.example.azeezahbrand.domain.repository.ShoppingRepository
import com.example.azeezahbrand.model.CartItem
import com.example.azeezahbrand.model.Product
import com.example.azeezahbrand.model.productList
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class HomeViewModel: ViewModel(){

    val authRepo = AuthenticationRepository()
    val shoppingRepository = ShoppingRepository()


    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: SnapshotStateList<CartItem> get() = _cartItems

    private val _products = mutableStateListOf<Product>()
    val products: SnapshotStateList<Product> get() = _products

    var currentUserID: String? = null
    var currentUser:FirebaseUser? = null


    var userName: String? = null



    init {
        viewModelScope.launch {
            fetchCurrentUserID()
            fetchCartItem()
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