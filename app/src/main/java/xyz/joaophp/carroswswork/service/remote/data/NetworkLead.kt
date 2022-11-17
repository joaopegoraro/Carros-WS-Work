package xyz.joaophp.carroswswork.service.remote.data

import com.google.gson.annotations.SerializedName

data class NetworkLead(
    @SerializedName("email_usuario") val emailUsuario: String = "",
    @SerializedName("telefone_usuario") val telefoneUsuario: String = "",
    @SerializedName("carro_id") val carroId: Int,
    @SerializedName("timestamp") val timestamp: Long
)
