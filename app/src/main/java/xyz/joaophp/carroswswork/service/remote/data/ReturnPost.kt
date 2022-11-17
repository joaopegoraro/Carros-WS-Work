package xyz.joaophp.carroswswork.service.remote.data

import com.google.gson.annotations.SerializedName

data class ReturnPost(
    @SerializedName("status") val status: String? = null
)
