package com.example.azeezahbrand.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.azeezahbrand.R
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.azeezahbrand.model.CartItem
import com.example.azeezahbrand.model.Product
import com.example.azeezahbrand.viewmodel.HomeViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavHostController,) {
    var showDialog by remember { mutableStateOf(false) }
    var showDialogAbaya by remember { mutableStateOf(false) }
    var selectedAbaya by remember { mutableStateOf("") }
    var selectedAbayaImage by remember { mutableStateOf(R.drawable.abaya1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background2))
    ) {
        // Header Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.azeezahlogo),
                    contentDescription = "AZEEZAH Logo",
                    modifier = Modifier.size(85.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AZEEZAH",
                    color = colorResource(id = R.color.brand_color),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        // Cart Icon with Badge
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shoppingcart),
                contentDescription = "Cart Icon",
                tint = colorResource(id = R.color.brand_color),
                modifier = Modifier
                    .size(70.dp)
                    .clickable {
                        navController.navigate("CART")
                    }
            )
            if (homeViewModel.cartItems.size > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .background(color = colorResource(id = R.color.background2), shape = RoundedCornerShape(14.dp))
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${homeViewModel.cartItems.size}",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        CarouselSlider()

        // Image Grid
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 285.dp, start = 8.dp, end = 8.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns in the grid
                contentPadding = PaddingValues(2.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(homeViewModel.products) { product ->
                    ProductCard(
                        product = product,
                        onProductClick = { clickedProduct ->
                            selectedAbaya = clickedProduct.productName
                            showDialogAbaya = true
                            selectedAbayaImage = clickedProduct.imageId
                        }
                    )
                }
            }
        }

        // Custom Order Floating Button
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))
                ExtendedFloatingActionButton(
                    text = { Text("Custom Order") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.azeezahlogo),
                            contentDescription = "Custom Order Icon",
                            modifier = Modifier.size(65.dp),
                        )
                    },
                    onClick = { showDialog = true },
                    containerColor = colorResource(id = R.color.background2),
                    contentColor = colorResource(id = R.color.brand_color),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .border(
                            width = 1.5.dp, // Border thickness
                            color = colorResource(id = R.color.brand_color), // Border color
                            shape = RoundedCornerShape(8.dp) // Matches the button's shape
                        )
                )
                Spacer(modifier = Modifier.height(25.dp))
            }
        }

    }

    if (showDialogAbaya) {
        AbayaDetailsDialog(abayaName = selectedAbaya,image=selectedAbayaImage,onDismissRequest={
            showDialogAbaya = false
        }, homeViewModel = homeViewModel,
        )}
    if (showDialog) {
        CustomOrderDialog(
            onDismiss = { showDialog = false },Image=selectedAbayaImage
            , homeViewModel = homeViewModel,
        )
    }
}

// Product Card Component
@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .aspectRatio(0.95f)
            .clickable { onProductClick(product) },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Image Section
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
                    .background(Color.White)
                    .border(
                        width = 0.dp,
                        color = colorResource(id = R.color.brand_color),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = product.imageId),
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Text Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.brand_color)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.productName,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


@Composable
fun CarouselSlider() {
    val images = listOf(
        R.drawable.carousel1,
        R.drawable.carousel2,
        R.drawable.carousel3

    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(4000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp)
            .height(150.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 18.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(0.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Carousel Image $page",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dots Indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = colorResource(id = R.color.brand_color), // Purple color
            inactiveColor = Color.LightGray,
            indicatorHeight = 8.dp,
            indicatorWidth = 8.dp,
            spacing = 8.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AbayaDetailsDialog(
    abayaName: String,
    image : Int,
    onDismissRequest: () -> Unit,
    homeViewModel: HomeViewModel
) {
    var selectedSize by remember { mutableStateOf("") }
    androidx.compose.material.AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = abayaName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.brand_color)
            )
        },
        text = {
            Column {
                Text(text = "Select Size:")
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("XS", "S", "M", "L", "XL", "XXL").forEach { size ->
                        Text(
                            text = size,
                            modifier = Modifier
                                .clickable { selectedSize = size }
                                .background(
                                    if (selectedSize == size) colorResource(id = R.color.brand_color) else Color.Transparent,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .border(1.dp, colorResource(id = R.color.brand_color), RoundedCornerShape(4.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            color = if (selectedSize == size) Color.White else colorResource(id = R.color.brand_color), // Change text color
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price: $49.99")
            }
        },
        confirmButton = {
            androidx.compose.material.TextButton(onClick = {     homeViewModel.addToCartList(cartItem= CartItem(
                size = selectedSize,
                productDescription = abayaName,
                productPrize = "49.99",
                productName = abayaName,
                imageId = image,

                ))
                onDismissRequest()
            }) {
                Text("Add to Cart", color = colorResource(id = R.color.brand_color))
            }
        },
        dismissButton = {
            androidx.compose.material.TextButton(onClick = onDismissRequest) {
                Text("Cancel", color = Color.Gray)
            }
        }
    )
}

@Composable
fun CustomOrderDialog(onDismiss: () -> Unit, Image:Int, homeViewModel: HomeViewModel) {
    var size by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var fabric by remember { mutableStateOf("") }
    var ImageNew by remember { mutableStateOf(Image) }
    var ImageProductName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Customize Your Abaya",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.brand_color)
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Size Selector
                DropdownMenuField(
                    label = "Select Size",
                    options = listOf("Small", "Medium", "Large", "Extra Large"),
                    selectedOption = size,
                    onOptionSelected = { size = it }
                )

                // Color Selector
                DropdownMenuField(
                    label = "Select Color",
                    options = listOf("Black", "Navy Blue", "Beige", "Emerald Green"),
                    selectedOption = color,
                    onOptionSelected = { color = it }
                )

                // Fabric Selector
                DropdownMenuField(
                    label = "Select Fabric",
                    options = listOf("Chiffon", "Cotton", "Silk", "Linen"),
                    selectedOption = fabric,
                    onOptionSelected = { fabric = it }
                )

                DropdownMenuProductField (
                    label = "Select Product",
                    options = homeViewModel.products,
                    selectedOption =ImageProductName ,
                    onOptionSelected = { ImageNew = it.imageId;
                        ImageProductName = it.productName
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    homeViewModel.addToCartList(cartItem= CartItem(
                        size = size,
                        productDescription = fabric,
                        productPrize = "49.9",
                        productName = fabric,
                        imageId = ImageNew,

                    ))
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.brand_color))
            ) {
                Text("Add to Cart", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel", color = colorResource(id = R.color.brand_color))
            }
        }
    )
}

@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(12.dp)
        ) {
            Text(text = selectedOption.ifEmpty { "Select $label" }, color = Color.Gray)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun DropdownMenuProductField(
    label: String,
    options: List<Product>,
    selectedOption: String,
    onOptionSelected: (Product) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(12.dp)
        ) {
            Text(text = selectedOption.ifEmpty { "Select $label" }, color = Color.Gray)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {   Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Image
                        Image(
                            painter = painterResource(id = option.imageId),
                            contentDescription = option.productName,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp)
                        )

                        // Product Details
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            androidx.compose.material.Text(
                                text = option.productName,
                                style = MaterialTheme.typography.h6
                            )
                            androidx.compose.material.Text(
                                text = option.productDescription,
                                style = MaterialTheme.typography.body2
                            )
                            androidx.compose.material.Text(
                                text = "Size: ${option.size}",
                                style = MaterialTheme.typography.body2
                            )
                            androidx.compose.material.Text(
                                text = "Price: $${option.productPrize}",
                                style = MaterialTheme.typography.body2,
                                color = Color.Gray
                            )
                        }


                    } },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

