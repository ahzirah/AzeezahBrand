package com.example.azeezahbrand.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.azeezahbrand.R

@Composable
fun SizeGuideScreen(
   onBack: () -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Size Guide", color = Color.White) },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                ) {

                Image(
                    painter = painterResource(id = R.drawable.abaya1),
                    contentDescription = "Abaya Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(300.dp)
                        .width(150.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.sizeguide),
                    contentDescription = "Abaya Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(350.dp)
                )
            }
            Text(
                text = "Find the perfect size for your abaya. Use the size chart below to match your measurements.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Size Chart
            SizeChart()

            Spacer(modifier = Modifier.height(16.dp))

            // Measurement Tips
            Text(
                text = "Measurement Tips:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.brand_color),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Use a soft measuring tape.\n" +
                        "- Measure over light clothing.\n" +
                        "- Make sure the tape is snug but not tight.",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun SizeChart() {
    val sizeData = listOf(
        listOf("Size", "Bust (inches)", "Waist (inches)", "Hips (inches)"),
        listOf("XS", "28-31", "22-25", "32-35"),
        listOf("S", "32-34", "24-26", "34-36"),
        listOf("M", "35-37", "27-29", "37-39"),
        listOf("L", "38-40", "30-32", "40-42"),
        listOf("XL", "41-43", "33-35", "43-45"),
        listOf("XXL", "44-46", "36-38", "46-48")
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        sizeData.forEachIndexed { index, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index == 0) colorResource(id = R.color.brand_color) else Color.LightGray)
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        fontSize = if (index == 0) 16.sp else 14.sp,
                        fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                        color = if (index == 0) Color.White else Color.Black,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SizeGuideScreenPreview() {
    SizeGuideScreen(
        onBack = { println("Back pressed") }
    )
}