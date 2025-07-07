// app/src/main/java/com/example/mywishlistapp/models/Wish.kt
package com.example.mywishlistapp.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class Wish(
    val id: Int,
    val user: User, // Nested User object
    val title: String,
    val image: String?, // URL to image, can be null
    val price: BigDecimal?, // Using BigDecimal for currency
    @SerializedName("shop_link") val shopLink: String?, // Map JSON field to Kotlin property
    val description: String?,
    @SerializedName("created_at") val createdAt: Date,
    val private: Boolean,
    val completed: Boolean,
    val tags: List<Tag> // List of nested Tag objects
)

// app/src/main/java/com/example/mywishlistapp/models/Tag.kt
package com.example.mywishlistapp.models

data class Tag(
    val id: Int,
    val name: String
)

// app/src/main/java/com/example/mywishlistapp/models/User.kt
package com.example.mywishlistapp.models

data class User(
    val id: Int,
    val username: String
)
