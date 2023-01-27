package com.wit.myapplication.model



data class Product(
    val id: String = "",
    val name: String = "",
    val price: Int = 0,
    val currency: String = "",
    val image: String = "",
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val description: String = ""
)


