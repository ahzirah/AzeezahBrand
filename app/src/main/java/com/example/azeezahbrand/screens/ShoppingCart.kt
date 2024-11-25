package com.example.azeezahbrand.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.azeezahbrand.R

data class CartItem(val name: String, val price: Double, val imageRes: Int)

@Composable
fun ShoppingCartScreen(
    cartItems: List<CartItem>,
    onBack: () -> Unit,
    onCheckout: () -> Unit
) {
    var totalPrice by remember { mutableDoubleStateOf(cartItems.sumOf { it.price }) }

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
        },
        bottomBar = {
            BottomAppBar(backgroundColor = colorResource(id = R.color.background2)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: \$${"%.2f".format(totalPrice)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = onCheckout,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.brand_color)
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "Checkout",
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(cartItems.size) { index ->
                val item = cartItems[index]
                CartItemRow(
                    item = item,
                    onRemove = {
                        val updatedItems = cartItems.toMutableList()
                        updatedItems.removeAt(index)
                        totalPrice = updatedItems.sumOf { it.price }
                    }
                )
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, onRemove: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "\$${"%.2f".format(item.price)}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun ShoppingCartScreenPreview() {
    val sampleItems = listOf(
        CartItem("Black Abaya", 49.99, R.drawable.abaya1),
        CartItem("Custom Navy Abaya", 59.99, R.drawable.abaya2),
        CartItem("Elegant Beige Abaya", 69.99, R.drawable.abaya3)
    )
    ShoppingCartScreen(
        cartItems = sampleItems,
        onBack = { println("Back pressed") },
        onCheckout = { println("check out pressed") }
    )
}
