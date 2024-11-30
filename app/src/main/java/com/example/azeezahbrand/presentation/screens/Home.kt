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
import com.example.azeezahbrand.model.Product
import com.example.azeezahbrand.viewmodel.HomeViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedAbaya by remember { mutableStateOf("") }


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

            Spacer(modifier = Modifier.height(25.dp))
        }

        Icon(
            painter = painterResource(id = R.drawable.shoppingcart),
            contentDescription = "Cart Icon",
            tint = colorResource(id = R.color.brand_color),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 45.dp, end = 2.dp)
                .size(85.dp)
                .clickable {
                    // cart click
                }
        )


        CarouselSlider()

        // Image Grid
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 285.dp, start = 8.dp, end = 8.dp)
        ) {

            // Loop through the list and create rows with 2 items each
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns in the grid
                contentPadding = PaddingValues(2.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                items(homeViewModel.products){ product ->
                    ProductCard(
                        product = product,
                        onProductClick = { clickedProduct ->
                            Log.d("PRODUCT_CLICKED", "HomeScreen: ${clickedProduct.productName}")
                        }
                    )
                }
            }

        }
        //custom order wrapped in a box
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
       // custom order action button
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
            modifier = Modifier.align(Alignment.Start)
                .border(
                    width = 1.5.dp, // Border thickness
                    color = colorResource(id = R.color.brand_color), // Border color
                    shape = RoundedCornerShape(8.dp) // Matches the button's shape
                )
        )

                Spacer(modifier = Modifier.height(25.dp)) // Add space after the button
            }
        }

        // Footer Text
        Text(
            text = "Abaya’s are for modest and classy ladies",
            color = colorResource(id = R.color.brand_color),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
                .fillMaxWidth()
        )
    }
    if (showDialog) {
        AbayaDetailsDialog(abayaName = selectedAbaya) {
            showDialog = false
        }
        CustomOrderDialog(
            onDismiss = { showDialog = false },
            onAddToCart = { /* Handle adding the custom order to the cart */ }
        )
    }
}


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
            .clickable { onProductClick(product) }, // Adjust aspect ratio as needed
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
                    modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}


@Composable
fun CarouselSlider() {
    val images = listOf(
        R.drawable.abaya1,
        R.drawable.abaya2,
        R.drawable.abaya3,
        R.drawable.abaya4
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(3000)
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
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(8.dp),
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
    onDismissRequest: () -> Unit
) {
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
                    listOf("S", "M", "L", "XL").forEach { size ->
                        Text(
                            text = size,
                            modifier = Modifier
                                .border(1.dp, colorResource(id = R.color.brand_color))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price: $49.99")
            }
        },
        confirmButton = {
            androidx.compose.material.TextButton(onClick = onDismissRequest) {
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
fun CustomOrderDialog(onDismiss: () -> Unit, onAddToCart: () -> Unit) {
    var size by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var fabric by remember { mutableStateOf("") }

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
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddToCart()
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

