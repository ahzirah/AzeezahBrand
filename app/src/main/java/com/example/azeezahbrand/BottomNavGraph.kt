package com.example.azeezahbrand

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.azeezahbrand.screens.CustomOrderScreen
import com.example.azeezahbrand.screens.HomeScreen
import com.example.azeezahbrand.screens.OrderDetails
import com.example.azeezahbrand.screens.ProfileScreen
import com.example.azeezahbrand.screens.ShoppingCartScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    cartItems: MutableList<OrderDetails>
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = BottomNav.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNav.Profile.route) {
            ProfileScreen()
        }
        composable(route = BottomNav.CustomOrder.route) {
            CustomOrderScreen(
                onAddToCart = { orderDetails ->
                    cartItems.add(orderDetails)
                    println("Added to cart: $orderDetails")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(route = BottomNav.ShoppingCart.route) {
            ShoppingCartScreen(cartItems = cartItems)
        }
    }
}

fun ShoppingCartScreen(cartItems: MutableList<OrderDetails>) {
    TODO("Not yet implemented")
}
