package com.example.azeezahbrand.components


import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange:(String) -> Unit,
    labelText:String,
    leadingIcon:ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None



) {
    OutlinedTextField(
        modifier = Modifier.height(60.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {if (leadingIcon != null) Icon (imageVector = leadingIcon, null)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(0)

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTextField(){
    LoginTextField(
        value = "",
        onValueChange = {},
        labelText = "Password"
    )
}
