package com.wit.myapplication.ui.main.home

/*data class Product(

    val productPhoto: String,
    val productDescription: String,
    val price: String,
    val rating: String,
    val review: String,
    val dots: String,
    )*/


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


