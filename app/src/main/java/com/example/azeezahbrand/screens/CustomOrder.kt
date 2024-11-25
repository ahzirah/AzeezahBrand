package com.example.azeezahbrand.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.azeezahbrand.R

data class OrderDetails(
    val materialType: String,
    val color: String,
    val length: String,
    val size: String,
    val additionalNotes: String
)

@Composable
fun CustomOrderScreen(
    onAddToCart: (OrderDetails) -> Unit,
    onBack: () -> Unit
) {
    var materialType by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var additionalNotes by remember { mutableStateOf("") }

    val materialOptions = listOf("Cotton", "Chiffon", "Silk", "Linen")
    val colorOptions = listOf("Black", "White", "Navy Blue", "Beige", "Emerald Green")
    val sizeOptions = listOf("Small", "Medium", "Large", "Extra Large")
    val lengthOptions = listOf("52 inches", "54 inches", "56 inches", "58 inches")


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Custom Order", color = Color.White) },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Image(
                painter = painterResource(id = R.drawable.abaya1),
                contentDescription = "Abaya Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(280.dp)
            )

            // Material Type Dropdown
            DropdownMenuField(
                label = "Material Type",
                options = materialOptions,
                selectedOption = materialType,
                onOptionSelected = { materialType = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Color Dropdown
            DropdownMenuField(
                label = "Color",
                options = colorOptions,
                selectedOption = color,
                onOptionSelected = { color = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Length Dropdown
            DropdownMenuField(
                label = "Length",
                options = lengthOptions,
                selectedOption = length,
                onOptionSelected = { length = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Size Dropdown
            DropdownMenuField(
                label = "Size",
                options = sizeOptions,
                selectedOption = size,
                onOptionSelected = { size = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Notes
            TextField(
                value = additionalNotes,
                onValueChange = { additionalNotes = it },
                label = { Text("Additional Requirements (optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Add to Cart Button
            Button(
                onClick = {
                    val orderDetails = OrderDetails(
                        materialType = materialType,
                        color = color,
                        length = length,
                        size = size,
                        additionalNotes = additionalNotes
                    )
                    onAddToCart(orderDetails)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.brand_color)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add to Cart", color = Color.White, fontSize = 16.sp)
            }
        }
    }
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
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomOrderScreenPreview() {
    CustomOrderScreen(
        onAddToCart = { orderDetails ->
            println("Order added to cart: $orderDetails")
        },
        onBack = { println("Back pressed") }
    )
}