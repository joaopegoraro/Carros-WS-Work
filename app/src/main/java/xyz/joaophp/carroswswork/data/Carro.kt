package xyz.joaophp.carroswswork.data

data class Carro(
    val id: Int,
    val marcaId: Int,
    val marcaNome: String,
    val nomeModelo: String,
    val ano: Int,
    val combustivel: String,
    val numeroPortas: Int,
    val valorFipe: Int,
    val cor: String,
    val timestampCadastro: Long,
    val salvo: Boolean,
    val timestampSalvamento: Long,
)
