package com.example.azeezahbrand.model

import com.example.azeezahbrand.R
import java.util.UUID

data class CartItem (
    var id: String = "",
    var imageId: Int = 0,
    var userId: String = "",
    var productName: String = "",
    var productDescription: String = "",
    var productPrize: String = " ",
    var size: String = ""
)
data class OrderItem(
    val productName: String = "",
    val size: String = "",
    val price: String =""
)
data class Order(
    val id: String ="",
    val userId: String="",
    val items: List<OrderItem> = emptyList(),
    val totalPrice: Double=0.0,
    val address: String="",
    val timestamp: Long=12234567890
)



data class  Product (
    var productid: String = "",
    var imageId: Int = 0,
    var productName: String = "",
    var productDescription: String = "",
    var productPrize: String = " ",
    var size: List<String> = emptyList()

)

// Dummy product Data
val productList = listOf(
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya1,
        productName = "Sheer abaya",
        productDescription = "",
        productPrize = "£45",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya2,
        productName = "kimono style abaya",
        productDescription = "£67",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya3,
        productName = "Classic abaya",
        productDescription = "£90",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya9,
        productName = "Elegant abaya",
        productDescription = "£78",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya5,
        productName = "Luxury abaya",
        productDescription = "£23",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya6,
        productName = "Simple abaya",
        productDescription = "£34",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya7,
        productName = "Stylish abaya",
        productDescription = "£55",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya8,
        productName = "Everyday abaya",
        productDescription = "£56",
        size = listOf("XS", "S", "M", "L", "XL", "XXL")
    ),
)

