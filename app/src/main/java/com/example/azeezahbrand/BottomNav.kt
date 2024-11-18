package com.example.azeezahbrand

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddReaction
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
    object Profile: BottomNav(
        route = "Profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object CustomOrder: BottomNav(
        route = "Custom Order",
        title = "Custom Order",
        icon = Icons.Default.AddCircle
    )
    object SizeGuide: BottomNav(
        route = "Size Guide",
        title = "Size Guide",
        icon = Icons.Default.AddReaction
    )
}