package com.example.azeezahbrand.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNav (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomNav(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object SizeGuide: BottomNav(
        route = "Size Guide",
        title = "Size Guide",
        icon = Icons.Default.Create
    )

    object Profile: BottomNav(
        route = "Profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}