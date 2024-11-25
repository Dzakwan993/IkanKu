package com.example.ikanku.model

data class Ulasan(
    val profilePictureResId: Int, // Changed from profilePictureUrl to profilePictureResId
    val username: String,
    val review: String,
    val rating: Float
)
