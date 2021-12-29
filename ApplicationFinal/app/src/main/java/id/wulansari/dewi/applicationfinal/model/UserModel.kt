package id.wulansari.dewi.applicationfinal.model

import com.google.gson.annotations.SerializedName

data class  UserModel(
    @field:SerializedName("items")
    val item : ArrayList<User>

)
