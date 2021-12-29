package id.wulansari.dewi.applicationfinal.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.wulansari.dewi.applicationfinal.api.ApiConfig
import id.wulansari.dewi.applicationfinal.model.User
import id.wulansari.dewi.applicationfinal.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    val listUsers = MutableLiveData<ArrayList<User>>()

    //digunakan untuk menemukan user dengan fungsi query, yang memanggil Api config yang dibuat sebelumnya
    fun findUsers(query: String) {
        ApiConfig.apiInstance
            .getUser(query)
            .enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.item)

                        Log.e("debug", listUsers.value.toString())

                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.e("onFailure", t.message.toString())
                }

            })
    }

    fun getListUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }
}