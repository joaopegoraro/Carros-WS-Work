package xyz.joaophp.carroswswork.data

import com.google.gson.annotations.SerializedName

data class Lead(
    @SerializedName("email_usuario") val emailUsuario: String = "",
    @SerializedName("telefone_usuario") val telefoneUsuario: String = "",
    @SerializedName("carro_id") val carroId: Int,
)
