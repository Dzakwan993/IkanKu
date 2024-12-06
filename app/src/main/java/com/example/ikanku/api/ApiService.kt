package com.example.ikanku.network

import com.example.ikanku.model.Kecamatan
import com.example.ikanku.model.KodePos
import com.example.ikanku.model.Pembeli
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST

interface ApiService {
    @POST("api/pembeli/register")
    suspend fun registerUser(@Body userData: Map<String, String>): Response<ResponseBody>

    @POST("api/pembeli/login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<Pembeli>


    @GET("api/kecamatan")
    suspend fun getKecamatan(): Response<List<Kecamatan>>

    @GET("api/kodepos/{id_kecamatan}")
    suspend fun getKodePosByKecamatan(@Path("id_kecamatan") districtId: String): Response<List<KodePos>>
}
