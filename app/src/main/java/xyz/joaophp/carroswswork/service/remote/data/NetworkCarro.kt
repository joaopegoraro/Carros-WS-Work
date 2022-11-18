package xyz.joaophp.carroswswork.service.remote.data

import com.google.gson.annotations.SerializedName

data class NetworkCarro(
    @SerializedName("id") val id: Int,
    @SerializedName("marca_id") val marcaId: Int? = null,
    @SerializedName("marca_nome") val marcaNome: String? = null,
    @SerializedName("nome_modelo") val nomeModelo: String? = null,
    @SerializedName("ano") val ano: Int? = null,
    @SerializedName("combustível") val combustivel: String? = null,
    @SerializedName("num_portas") val numeroPortas: Int? = null,
    @SerializedName("valor_fipe") val valorFipe: Double? = null,
    @SerializedName("cor") val cor: String? = null,
    @SerializedName("timestamp_cadastro") val timestampCadastro: Long? = null
)
