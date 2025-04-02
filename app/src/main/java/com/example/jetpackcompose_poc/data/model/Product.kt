package com.example.jetpackcompose_poc.data.model


//{
//      "id": 1,
//      "title": "Essence Mascara Lash Princess",
//      "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
//      "category": "beauty",
//      "price": 9.99,
//      "discountPercentage": 7.17,
//      "rating": 4.94,
//      "stock": 5,
//      "images": ["...", "...", "..."]
//      "sku": "RCH45Q1A",
// }
    data class Product(
    val id: Int,
    val title: String?,
    val description: String?,
    val category: String?,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val images: Array<String>,
    val sku: String?
)


