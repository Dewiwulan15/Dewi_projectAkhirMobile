package id.wulansari.dewi.applicationfinal.detailActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.wulansari.dewi.applicationfinal.api.ApiConfig
import id.wulansari.dewi.applicationfinal.model.DetailuserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    val userDetail = MutableLiveData<DetailuserModel>()


    fun setUserDetail(username: String) {
        ApiConfig.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailuserModel> {
                override fun onResponse(
                    call: Call<DetailuserModel>,
                    response: Response<DetailuserModel>,
                ) {
                    if (response.isSuccessful) {
                        userDetail.postValue(response.body())
                    }
                }

                // membuat blok kode pemanggilan metode lebih ekspresif.
                override fun onFailure(call: Call<DetailuserModel>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
    }

    //Menggunakan LiveData untuk menangani data dengan cara berbasis siklus proses.
    fun getUserDetail(): LiveData<DetailuserModel> {
        return userDetail
    }
}