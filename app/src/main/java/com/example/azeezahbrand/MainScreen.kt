package com.example.azeezahbrand

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        padding ->
        BottomNavGraph(navController = navController, paddingValues = padding)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNav.Home,
        BottomNav.Profile,
        BottomNav.CustomOrder,
        BottomNav.SizeGuide,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.White) {
        screens.forEach{ screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Navigation Icon",
                        tint = colorResource(id = R.color.brand_color)
                    ) },
                selected = currentDestination?.hierarchy?.any{
                    it.route == screen.route

                } == true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }

            )

        }

    }

}

@Composable
fun RowScope.AddItem(
    screen:BottomNav,
    currentDestination: NavDestination?,
    navController: NavHostController
){

    }

@Composable
fun RowScope.BottomNavigationItem(
    label: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true
) {

}
