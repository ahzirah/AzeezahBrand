package com.example.azeezahbrand.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.azeezahbrand.R
import com.example.azeezahbrand.model.CartItem
import com.example.azeezahbrand.viewmodel.HomeViewModel

@Composable
fun ShoppingCartScreen(
    homeViewModel: HomeViewModel,
    onBack: () -> Unit,
    navController: NavHostController
) {
    val cartItems = homeViewModel.cartItems

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Shopping Cart", color = Color.White) },
                backgroundColor = colorResource(id = R.color.brand_color),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Display Cart Items
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { cartItem ->
                    CartItemRow(
                        cartItem = cartItem,
                        onDelete = {
                            homeViewModel.cartItems.remove(cartItem)
                            homeViewModel.deleteCartList(cartItem) }
                    )
                }
            }

            // Checkout Button
            Button(
                onClick = {  navController.navigate("Checkout") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.brand_color))
            ) {
                Text("Checkout", color = Color.White)
            }
        }
    }
}

@Composable
fun CartItemRow(
    cartItem: CartItem,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        Image(
            painter = painterResource(id = cartItem.imageId),
            contentDescription = cartItem.productName,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )

        // Product Details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = cartItem.productName, style = MaterialTheme.typography.h6)
            Text(text = cartItem.productDescription, style = MaterialTheme.typography.body2)
            Text(text = "Size: ${cartItem.size}", style = MaterialTheme.typography.body2)
            Text(text = "Price: $${cartItem.productPrize}", style = MaterialTheme.typography.body2, color = Color.Gray)
        }

        // Delete Button
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",

                tint = Color.Red
            )
        }
    }
}

