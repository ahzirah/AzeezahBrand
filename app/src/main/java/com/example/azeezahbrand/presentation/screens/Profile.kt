package com.example.azeezahbrand.presentation.screens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.azeezahbrand.R
import com.example.azeezahbrand.viewmodel.HomeViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val showImageDialog = remember { mutableStateOf(false) }

    // Launcher for picking an image from the gallery
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    // Launcher for capturing an image with the camera
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            val savedUri = saveBitmapToInternalStorage(context, it)
            imageUri.value = savedUri
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile", color = Color.White) },
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Image Surface with Camera Icon
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.size(200.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier.matchParentSize(),
                    elevation = 4.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (imageUri.value != null) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUri.value),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = "No Image Selected",
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                IconButton(
                    onClick = { showImageDialog.value = true },
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp)
                        .background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = "Camera",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Profile Details
            Text(
                text = homeViewModel.userName!!,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp
            )
            homeViewModel.currentUser?.email?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Profile Options
            ProfileOption("Address Book", android.R.drawable.ic_menu_mylocation, navController, "AddressBookScreen")
            ProfileOption("My Orders", android.R.drawable.ic_menu_view, navController, "MyOrdersScreen")
            ProfileOption("About AZEEZAH", android.R.drawable.ic_menu_more, navController, "AboutAzeezahScreen")
            ProfileOption("Logout", android.R.drawable.ic_lock_power_off, navController, "LogoutScreen")
        }
    }

    // Image Dialog
    if (showImageDialog.value) {
        Dialog(onDismissRequest = { showImageDialog.value = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Choose an Option",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            pickImageLauncher.launch("image/*")
                            showImageDialog.value = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Pick Image from Gallery")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            takePictureLauncher.launch()
                            showImageDialog.value = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Capture Image")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        onClick = { showImageDialog.value = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cancel", color = Color.Red)
                    }
                }
            }
        }
    }
}


// Function to save Bitmap to internal storage
fun saveBitmapToInternalStorage(context: Context, bitmap: Bitmap): Uri {
    val filename = "profile_image_${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, filename)

    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }
    return Uri.fromFile(file)
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

