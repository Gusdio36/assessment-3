package com.d3if3089.homedecor.network

import com.d3if3089.homedecor.model.Decoration
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.d3if3089.homedecor.model.MessageResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://home-decor-gusdio.vercel.app/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface UserApi {
    @Multipart
    @POST("decors/")
    suspend fun addData(
        @Part("nama_dekorasi") namaDekorasi: RequestBody,
        @Part("tipe_dekorasi") tipeDekorasi: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part("user_email") userEmail: RequestBody,
        @Part file: MultipartBody.Part
    ): Decoration

    @GET("decors/")
    suspend fun getAllData(
        @Query("email") email: String,
    ): List<Decoration>

    @DELETE("decors/{decor_id}")
    suspend fun deleteData(
        @Path("decor_id") id: Int,
        @Query("email") email: String
    ): MessageResponse
}


object Api {
    val userService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun getImageUrl(imageId: String): String{
        return BASE_URL + "decors/images/$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }