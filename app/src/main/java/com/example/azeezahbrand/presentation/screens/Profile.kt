package com.example.azeezahbrand.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.azeezahbrand.R

@Composable
fun ProfileScreen(
    navController: NavHostController,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile", color = Color.White) },
                backgroundColor = colorResource(id = R.color.brand_color),
                navigationIcon = {
                    IconButton(onClick = onBack ) {
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(100.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://example.com/profile.jpg"), // Replace with your image URL
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Zira James",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "zirajames@gmail.com",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))
            ProfileOption("Manage Account", android.R.drawable.ic_dialog_email, navController, "ManageAccountScreen")
            ProfileOption("Address Book", android.R.drawable.ic_menu_view, navController, "AddressBookScreen")
            ProfileOption("My Orders", android.R.drawable.ic_menu_view, navController, "MyOrdersScreen")
            ProfileOption("About AZEEZAH", android.R.drawable.ic_menu_more, navController, "AboutAzeezahScreen")
            ProfileOption("Logout", android.R.drawable.ic_lock_power_off, navController, "LogoutScreen")
        }
    }
}

@Composable
fun ProfileOption(title: String,
                  iconRes: Int,
                  navController: NavController,
                  destination: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Color(0xFF6200EE),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
                                    .clickable {
                                        navController.navigate(destination)
                                    }
            )
        }
    }
}


@Composable
fun LogoutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Logout Screen")
    }
}

