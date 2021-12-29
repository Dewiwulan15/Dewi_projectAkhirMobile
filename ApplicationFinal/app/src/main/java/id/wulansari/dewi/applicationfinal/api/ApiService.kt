package id.wulansari.dewi.applicationfinal.api

import id.wulansari.dewi.applicationfinal.model.DetailuserModel
import id.wulansari.dewi.applicationfinal.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * Membuat interface class yang berisi list endpoint dari api yang akan digunakan.
     * dengan menggunakan anotasi @GET untuk request method yang digunakan dan endpoint API
     * */
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserModel>


    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailuserModel>

}