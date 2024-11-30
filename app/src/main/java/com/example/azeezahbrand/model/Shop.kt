package com.example.azeezahbrand.model
import com.example.azeezahbrand.R
import com.google.protobuf.Empty

import java.util.UUID

data class CartItem (
    var id: String = "",
    var imageId: Int = 0,
    var productName: String = "",
    var productdescription: String = "",
    var size: String = ""
)

data class  Product (
    var productid: String = "",
    var imageId: Int = 0,
    var productName: String = "",
    var productdescription: String = "",
    var size: List<String> = emptyList()


)

// Dummy product Data
val productList = listOf(
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya1,
        productName = "sheer abaya",
        productdescription = "",
        size = listOf("xm", "xxl")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya2,
        productName = "kimono style abaya",
        productdescription = "",
        size = listOf("xm", "xxl")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya3,
        productName = "classic abaya",
        productdescription = "",
        size = listOf("xm", "xxl")
    ),
    Product(
        productid = UUID.randomUUID().toString(),
        imageId = R.drawable.abaya4,
        productName = "elegant abaya",
        productdescription = "",
        size = listOf("xm", "xxl")
    )
)

