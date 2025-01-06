package com.example.azeezahbrand.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.azeezahbrand.R
import com.example.azeezahbrand.presentation.authentication.RegisterActivity

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreen()
        }

    }
}

@Composable
fun OnboardingScreen() {

    val context = LocalContext.current
    val initialColor = colorResource(id = R.color.brand_color)
    val myButtonBackgroundColor = remember {
        mutableStateOf(initialColor)
    }


    Column (modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(

                    colorResource(id = R.color.background3),
                    colorResource(id = R.color.background2),
                    colorResource(id = R.color.background2),
                    colorResource(id = R.color.background2),
                    colorResource(id = R.color.background3)
                )
            )
        ),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.mainlogo),
            contentDescription = "AZEEZAH Logo",
            modifier = Modifier
                .size(350.dp)
        )
        Spacer(modifier = Modifier.size(0.dp))

        Text(text = "AZEEZAH",
            color = colorResource(id = R.color.brand_color),
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(500.dp))

        Spacer(modifier = Modifier.size(20.dp))

        Text(text = "Exclusive Abaya's",
            color = colorResource(id = R.color.brand_color),
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(500.dp))

        Spacer(modifier = Modifier.size(25.dp))

        Text(text = "Tailored To Your Vision",
            color = colorResource(id = R.color.brand_color),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(500.dp))

        Spacer(modifier = Modifier.size(35.dp))

        Text(text = "where tradition meets modernity. " +
                "Discover our exclusive collection " +
                "of ready-made abayas or create a custom design that's uniquely yours. " +
                "Start your style journey with us today!",
            color = colorResource(id = R.color.brand_color),
            fontSize = 20.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(350.dp))

        Spacer(modifier = Modifier.size(65.dp))

            Button(
                onClick = {
                    myButtonBackgroundColor.value = Color.Gray
                    val intent = Intent(context, RegisterActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.size(170.dp, 45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
                shape = RoundedCornerShape(0.dp),
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = FontFamily.Serif,
                    color = colorResource(id = R.color.white),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
    }
}



@Preview
@Composable
fun PreviewOnboardingActivity() {
    OnboardingScreen()
}


