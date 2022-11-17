package xyz.joaophp.carroswswork.data

data class Carro(
    val id: Int,
    val marcaId: Int? = null,
    val marcaNome: String? = null,
    val nomeModelo: String? = null,
    val ano: Int? = null,
    val combustivel: String? = null,
    val numeroPortas: Int? = null,
    val valorFipe: Int? = null,
    val cor: String? = null,
    val timestampCadastro: Long? = null
)
