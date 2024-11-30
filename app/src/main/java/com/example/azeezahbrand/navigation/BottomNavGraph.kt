package com.example.azeezahbrand.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.azeezahbrand.presentation.screens.AboutAzeezahScreen
import com.example.azeezahbrand.presentation.screens.AddressBookScreen
import com.example.azeezahbrand.presentation.screens.HomeScreen
import com.example.azeezahbrand.presentation.screens.LogoutScreen
import com.example.azeezahbrand.presentation.screens.ManageAccountScreen
import com.example.azeezahbrand.presentation.screens.MyOrdersScreen
import com.example.azeezahbrand.presentation.screens.ProfileScreen
import com.example.azeezahbrand.presentation.screens.ShoppingCartScreen
import com.example.azeezahbrand.presentation.screens.SizeGuideScreen
import com.example.azeezahbrand.viewmodel.HomeViewModel


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
   // cartItems: MutableList<OrderDetails>
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = BottomNav.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(homeViewModel = homeViewModel)
        }

        composable(route = BottomNav.Profile.route) {
            ProfileScreen(
                navController= navController,
                onBack = { navController.popBackStack() }
            )
        }
        composable(route = BottomNav.SizeGuide.route) {
            SizeGuideScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("AboutAzeezahScreen") {
            AboutAzeezahScreen(onBack = { navController.popBackStack() })
        }
        composable("MyOrdersScreen") {
            MyOrdersScreen(onBack = { navController.popBackStack() })
        }
        composable("AddressBookScreen") {
            AddressBookScreen(onBack = { navController.popBackStack() })
        }
        composable("ManageAccountScreen") {
            ManageAccountScreen(onBack = { navController.popBackStack() })
        }
        composable("LogoutScreen") {
            LogoutScreen()
        }
    }

    }


