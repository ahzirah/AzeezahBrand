package com.example.azeezahbrand.presentation.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azeezahbrand.MainActivity
import com.example.azeezahbrand.R
import com.example.azeezahbrand.domain.repository.AuthenticationRepository
import com.example.azeezahbrand.components.LoginTextField


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen()
        }

    }
}

@Composable
fun RegisterScreen(

    leadingIcon: ImageVector? = null,
) {
    var authRepo = AuthenticationRepository()
    var authenticationViewModel = AuthenticationViewModel(authRepo)
     val fullName = remember {
       mutableStateOf("")
     }
    val email= remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword= remember {
        mutableStateOf("")
    }
    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf("")
    }
    var agree by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val initialColor = colorResource(id = R.color.brand_color)
    val myButtonBackgroundColor = remember {
        mutableStateOf(initialColor)
    }
    val authenticateState by authenticationViewModel.authState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(defaultPadding)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.azeezahlogo),
            contentDescription = "AZEEZAH Logo",
            modifier = Modifier
                .size(250.dp)
        )

        Text(
            text = "AZEEZAH",
            color = colorResource(id = R.color.brand_color),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(500.dp)
        )

        Spacer(modifier = Modifier.padding(5.dp))


        Text(
            text = "Tailor Your Vision....",
            color = colorResource(id = R.color.brand_color),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(defaultPadding))


        LoginTextField(
            value = fullName.value,
            onValueChange = {fullName.value=it},
            labelText = "Full Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(itemSpacing))

        LoginTextField(
            value = email.value,
            onValueChange = { email.value=it },
            labelText = "Email",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(itemSpacing))

        LoginTextField(
            value = password.value,
            onValueChange = { password.value=it },
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),

            )
        Spacer(modifier = Modifier.height(itemSpacing))

        LoginTextField(
            value = confirmPassword.value,
            onValueChange = {confirmPassword.value=it},
            labelText = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),

            )
        Spacer(modifier = Modifier.padding(12.dp))

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            val privacyText = "Privacy"
            val policyText = "Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("I Agree with")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = privacyText,privacyText)
                    append(privacyText)
                }
                append(" And ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = policyText,policyText)
                    append(policyText)
                }

            }

            Checkbox(checked = agree, onCheckedChange = { agree = it } )
             ClickableText(
                 annotatedString,
             ){ offset ->
                 annotatedString.getStringAnnotations(offset,offset).forEach {
                     when(it.tag){
                         privacyText -> {
                             Toast.makeText(context, "Privacy Text Clicked ", Toast.LENGTH_SHORT).show()
                         }
                         policyText -> {
                             Toast.makeText(context, "Policy Text Clicked ", Toast.LENGTH_SHORT).show()
                         }
                     }
                 }

             }
        }

        Button(onClick =  {
            when {
                !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
                    Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                }
                password.value != confirmPassword.value -> {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                (email.value.isEmpty() || password.value.isEmpty()||  fullName.value.isEmpty()) -> {
                    Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    authenticationViewModel.signUp(email.value, password.value,fullName.value, {
                        Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT)
                            .show()
                        myButtonBackgroundColor.value = Color.Gray
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent) // Navigate to the next screen
                    },context)
                }
            }

        }, modifier = Modifier.size(170.dp, 45.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
        ) {
            when(authenticateState) {
                is AuthenticationState.loading -> CircularProgressIndicator()

                else -> Text(
                    "Register",
                    fontFamily = FontFamily.Serif,
                )
            }

        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "Already have an account?",
                    color = colorResource(id = R.color.black),
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                )
            }
            TextButton(onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Login",
                    fontStyle = FontStyle.Italic,)
            }
        }


    }
}



