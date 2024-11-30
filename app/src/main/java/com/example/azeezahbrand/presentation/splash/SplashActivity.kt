package com.example.azeezahbrand.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp
import com.example.azeezahbrand.presentation.screens.OnboardingActivity
import com.example.azeezahbrand.R

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the Onboarding activity
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, 3000) // 3000 milliseconds delay
    }
}

@Composable
fun SplashScreen() {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                   // colorResource(id = R.color.background3), // Start color from colors.xml

                    colorResource(id = R.color.background2),
                    colorResource(id = R.color.background2),
                    colorResource(id = R.color.background3)
                )
            )
        ),
       // .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Image(
            painter = painterResource(id = R.drawable.azeezahlogo),
            contentDescription = "AZEEZAH Logo",
            modifier = Modifier.size(350.dp)
        )

        Text(text = "AZEEZAH",
            color = colorResource(id = R.color.brand_color),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(500.dp))


    }



}

@Preview
@Composable
fun PreviewSplashActivity() {
    SplashScreen()
}
