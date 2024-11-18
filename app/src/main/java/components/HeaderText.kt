package components

import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.azeezahbrand.R


@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
){
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall,
        color = colorResource(id = R.color.brand_color),
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )






}