package id.wulansari.dewi.applicationfinal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    //menentukan URL dasar yang tetap dan digunakan sebagai Base dari API tersebut
    private const val BASE_URL = "https://api.github.com/"

    //menggunakan retrofit untuk membuat object retrofit menggunakan converter Gson
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
    //Menentukan GsonConverterFactory sebagai konverter, yang menggunakan Gson untuk deserialisasi JSON-nya.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //membuat instance retrofit
    val apiInstance = retrofit.create(ApiService::class.java)
}