package com.example.azeezahbrand.presentation.authentication

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azeezahbrand.MainActivity
import com.example.azeezahbrand.R
import com.example.azeezahbrand.domain.repository.AuthenticationRepository
import com.example.azeezahbrand.components.LoginTextField


val defaultPadding = 8.dp
val itemSpacing = 15.dp


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen()
        }

    }
}

@Composable
fun LoginScreen(
    leadingIcon: ImageVector? = null,

) {
    var authRepo = AuthenticationRepository()
    var authenticationViewModel = AuthenticationViewModel(authRepo)


  val email = remember {
      mutableStateOf("")
  }
  val password = remember {
      mutableStateOf("")
  }
    val context = LocalContext.current
    val initialColor = colorResource(id = R.color.brand_color)
    val myButtonBackgroundColor = remember {
        mutableStateOf(initialColor)

    }
    val authenticateSta by authenticationViewModel.authState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                value = email.value,
                onValueChange = { email.value = it },
                labelText = "Email",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(itemSpacing))

            LoginTextField(
                value = password.value,
                onValueChange = {password.value=it},
                labelText = "Password",
                leadingIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),

            )
            Spacer(modifier = Modifier.height(itemSpacing))


            Spacer(modifier = Modifier.height(itemSpacing))

            Button(onClick =
                {
                    when{
                        (email.value.isEmpty() || password.value.isEmpty())->{
                            Toast.makeText(context,"Fields cannot be empty", Toast.LENGTH_SHORT).show()
                        }
                        else->{
                            authenticationViewModel.signIn(
                                email = email.value,
                                password=password.value,
                                onNavigate = {myButtonBackgroundColor.value = Color.Gray
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent) },
                                context=context
                            )

                        }
                    }

            }, modifier = Modifier.size(170.dp, 45.dp),
                //enabled = isFieldsEmpty,
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
            ) {
                when(authenticateSta){
                    is AuthenticationState.loading-> CircularProgressIndicator()

                    else-> Text("Login",
                        fontFamily = FontFamily.Serif,)
                }


            }

            Spacer(modifier = Modifier.padding(50.dp))

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
                        text = "Don't have an account?",
                        color = colorResource(id = R.color.black),
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    )
                }
                TextButton(onClick = {}) {
                    Text("Register",
                        fontStyle = FontStyle.Italic,)
                }
            }

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
                        text = "-----------------",
                        color = colorResource(id = R.color.black),
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Or Continue With",
                        color = colorResource(id = R.color.black),
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    )
                }
                Text(
                    text = "-----------------",
                    color = colorResource(id = R.color.black),
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                )


            }

            Spacer(modifier = Modifier.padding(14.dp))

            AlternativeLoginOption(
                onIconClick = { index ->
                    when (index) {
                        0 -> {
                            Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
            )
        }

}

@Composable
fun AlternativeLoginOption(
    onIconClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val iconList = listOf(
        R.drawable.social_icon
    )
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        iconList.forEachIndexed { index, iconResId ->
            Icon(
                painter = painterResource(iconResId),
                contentDescription = "Alternative Login",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onIconClick(index)
                    }
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(14.dp))
        }
    }
}





@Preview()
@Composable
fun PreviewLoginActivity() {
    LoginScreen()
            }

