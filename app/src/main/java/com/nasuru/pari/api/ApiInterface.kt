package com.nasuru.pari.api

import com.nasuru.pari.model.ImageResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiInterface {

    companion object {
        var BASE_URL = "http://35.240.154.155:5000/"
    }

    @Multipart
    @POST("api/predict")
    fun uploadImage(
        @Part("image\"; filename=\"myfile.jpg\" ") file: RequestBody?
    ): Call<ImageResponse>

}