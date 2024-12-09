package com.example.azeezahbrand.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.azeezahbrand.R
import com.example.azeezahbrand.model.Order
import com.example.azeezahbrand.model.OrderItem
import com.example.azeezahbrand.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun OrdersScreen(
    homeViewModel: HomeViewModel,
    onBack: () -> Unit
) {
    val orders  = homeViewModel.orderItems


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Orders", color = Color.White) },
                backgroundColor = colorResource(id = R.color.brand_color),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
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
            // Display Orders
            if (orders.isEmpty()) {
                Text(
                    text = "No orders found.",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(orders) { order ->
                        OrderItemCard(order = order)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Order ID: ${order.id}", style = MaterialTheme.typography.h6)
            Text(text = "Total Price: $${"%.2f".format(order.totalPrice)}")
            Text(text = "Address: ${order.address}")
            Text(text = "Ordered On: ${order.timestamp}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Items:", style = MaterialTheme.typography.subtitle1)
            order.items.forEach { item ->
                Text("- ${item.productName} (Size: ${item.size}, Price: $${item.price})")
            }
        }
    }
}

