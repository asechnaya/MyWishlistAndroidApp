// app/src/main/java/com/example/myfirstwishlistapp/models/Wish.kt
package com.example.myfirstwishlistapp.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class Wish(
    val id: Int,
    val user: User, // Ensure 'User' is correctly referenced
    val title: String,
    val image: String?, // URL to image, can be null
    val price: BigDecimal?, // Using BigDecimal for currency
    @SerializedName("shop_link") val shopLink: String?, // Map JSON field to Kotlin property
    val description: String?,
    @SerializedName("created_at") val createdAt: Date,
    val private: Boolean,
    val completed: Boolean,
    val tags: List<Tag>? // Tags can be an empty list, so make it nullable or provide default empty list
)

