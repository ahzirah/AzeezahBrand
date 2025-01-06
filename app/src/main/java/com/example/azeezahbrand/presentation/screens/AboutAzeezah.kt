package com.example.azeezahbrand.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.azeezahbrand.R




@Composable
fun AboutAzeezahScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About AZEEZAH Brand", color = Color.White) },
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
            Text(
                text = "Welcome to AZEEZAH Brand!",
                style = androidx.compose.material.MaterialTheme.typography.h5,
                color = colorResource(id = R.color.brand_color)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "AZEEZAH is a premium clothing brand specializing in stylish and elegant abayas. Our mission is to empower women by offering a wide range of designs, from ready-made collections to custom-made pieces tailored to your unique style.",
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Our Values:",
                style = androidx.compose.material.MaterialTheme.typography.h6
            )
            Text("- Quality craftsmanship\n- Cultural heritage\n- Modern elegance\n- Customer satisfaction")

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Thank you for choosing AZEEZAH!",
                style = androidx.compose.material.MaterialTheme.typography.h6,
                color = colorResource(id = R.color.brand_color)
            )
        }
    }
}

@Preview
@Composable
fun AboutAzeezahScreenPreview() {
    AboutAzeezahScreen(
        onBack = { println("Back pressed") }
    )
}


