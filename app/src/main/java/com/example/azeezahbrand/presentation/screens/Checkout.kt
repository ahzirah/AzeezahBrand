package com.example.azeezahbrand.presentation.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.azeezahbrand.MainActivity
import com.example.azeezahbrand.R
import com.example.azeezahbrand.model.CartItem
import com.example.azeezahbrand.presentation.authentication.AuthenticationState
import com.example.azeezahbrand.viewmodel.HomeState
import com.example.azeezahbrand.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(
    homeViewModel: HomeViewModel,
    onBack: () -> Unit,

) {
    val cartItems = homeViewModel.cartItems
    val context = LocalContext.current
    val totalPrice = cartItems.sumOf { it.productPrize.toDouble() }
    var showPaymentDialog by remember { mutableStateOf(false) }

    if (showPaymentDialog) {
        PaymentDialog(
            totalPrice = totalPrice,
            onDismiss = { showPaymentDialog = false },
            homeViewModel = homeViewModel,
            onConfirmPayment = { address ->
                showPaymentDialog = false
                CoroutineScope(Dispatchers.IO).launch {
                    homeViewModel.saveOrderToCollection(cartItems, totalPrice, address)
                    homeViewModel.deleteCartItems()


                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Order was successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }

        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Checkout", color = Color.White) },
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
            // Cart Items Summary
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { cartItem ->
                    CheckoutItemRow(cartItem = cartItem)
                }
            }

            // Total Price
            Text(
                text = "Total Price: $${"%.2f".format(totalPrice)}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Confirm Checkout Button
            Button(
                onClick = { showPaymentDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Checkout")
            }
        }
    }
}

@Composable
fun PaymentDialog(
    totalPrice: Double,
    onDismiss: () -> Unit,
    homeViewModel: HomeViewModel,
    onConfirmPayment: (String) -> Unit
) {
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    val checkoutState by homeViewModel.homeState.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Payment Details") },
        text = {
            Column {
                Text(text = "Total Amount: $${"%.2f".format(totalPrice)}")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Card Number") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = { Text("Expiry Date (MM/YY)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    label = { Text("CVV") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Shipping Address") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirmPayment(address) }) {
                when(checkoutState){
                    is HomeState.loading-> androidx.compose.material3.CircularProgressIndicator()

                    else-> Text("Pay Now")
                }

            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



@Composable
fun CheckoutItemRow(cartItem: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product Image
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
            Text(text = "Size: ${cartItem.size}", style = MaterialTheme.typography.body2)
            Text(text = "Price: $${cartItem.productPrize}", style = MaterialTheme.typography.body2, color = Color.Gray)
        }
    }
}



