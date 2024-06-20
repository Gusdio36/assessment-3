package com.d3if3089.homedecor.model

data class Decoration(
    val user_email: String,
    val created_at: String,
    val tipe_dekorasi: String,
    val harga: Int,
    val nama_dekorasi: String,
    val id: Int,
    val image_id: String
)