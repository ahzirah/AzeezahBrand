package com.example.azeezahbrand.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.azeezahbrand.presentation.authentication.defaultPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Header Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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

            Spacer(modifier = Modifier.height(24.dp))
        }

        CarouselSlider()

        // Image Grid
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 290.dp,start=8.dp,end=8.dp) // Adjust this for proper alignment below the header
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageGrid()
        }

        // Footer Text
        Text(
            text = "Abaya’s are for modest and classy ladies",
            color = colorResource(id = R.color.brand_color),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ImageGrid() {
    val images = listOf(
        R.drawable.abaya1, // Replace with your image names
        R.drawable.abaya1,
        R.drawable.abaya1,
        R.drawable.abaya1
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in images.indices step 2) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // First item in the row
                images.getOrNull(i)?.let { image ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f) // Ensure both items in a row take equal space
                            .aspectRatio(0.9f), // Adjust aspect ratio for abaya display
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Image Section
                            Box(
                                modifier = Modifier
                                    .weight(0.8f)
                                    .fillMaxSize()
                                    .background(Color.White) // Background for the image
                                    .border(
                                        width = 2.dp,
                                        color = colorResource(id = R.color.brand_color), // Border in brand color
                                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = "Abaya Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            // Text Section with Purple Background
                            Box(
                                modifier = Modifier
                                    .weight(0.12f)
                                    .fillMaxWidth()
                                    .background(colorResource(id = R.color.brand_color)), // Purple background
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Abaya", // Replace with dynamic text if necessary
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                // Second item in the row
                images.getOrNull(i + 1)?.let { image ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f) // Ensure both items in a row take equal space
                            .aspectRatio(0.9f), // Adjust aspect ratio for abaya display
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Image Section
                            Box(
                                modifier = Modifier
                                    .weight(0.8f)
                                    .fillMaxSize()
                                    .background(Color.White) // Background for the image
                                    .border(
                                        width = 2.dp,
                                        color = colorResource(id = R.color.brand_color), // Border in brand color
                                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = "Abaya Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            // Text Section with Purple Background
                            Box(
                                modifier = Modifier
                                    .weight(0.12f)
                                    .fillMaxWidth()
                                    .background(colorResource(id = R.color.brand_color)), // Purple background
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Abaya", // Replace with dynamic text if necessary
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // Space between rows
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselSlider() {
    val images = listOf(
        R.drawable.abaya1,
        R.drawable.abaya1,
        R.drawable.abaya1,
        R.drawable.abaya1
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-slide functionality
    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(3000) // Slide every 3 seconds
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth().padding(top = 120.dp)
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
fun CustomCard(image: Int) {

}
//@Composable
//fun GridImageItem(imageRes: Int) {
//
//}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}