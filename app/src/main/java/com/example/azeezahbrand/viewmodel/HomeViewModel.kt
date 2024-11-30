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
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel: ViewModel(){

    val authRepo = AuthenticationRepository()
    val shoppingRepository = ShoppingRepository()


    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: SnapshotStateList<CartItem> get() = _cartItems

    private val _products = mutableStateListOf<Product>()
    val products: SnapshotStateList<Product> get() = _products

    var currentUserID: String? = null



    init {
        viewModelScope.launch {
            fetchCurrentUserID()
            fetchCartItem()
            fetchProducts()
        }
    }


    private suspend fun fetchCurrentUserID() {
        currentUserID = authRepo.currentUser()?.uid
    }


    private suspend fun fetchCartItem(){
        val queryFilter = Pair("userId", currentUserID!!)

        try {
            val cartItemList =  shoppingRepository.fetchDocuments<CartItem>("cartItem", queryFilter)
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
                shoppingRepository.saveDocument("cartItem", cartItem, cartItem.id)

            }catch (e: Exception){
                e.printStackTrace()
                // You can add a log here to debug if you are getting unexpected result.
            }
        }


    }
}