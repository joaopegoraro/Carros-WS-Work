package xyz.joaophp.carroswswork.data

data class Carro(
    val id: Int,
    val marcaId: Int,
    val marcaNome: String,
    val nomeModelo: String,
    val ano: Int?,
    val combustivel: String,
    val numeroPortas: Int,
    val valorFipe: Double?,
    val cor: String,
    val timestampCadastro: Long
) {
    companion object {
        const val MARCA_TOYOTA = "TOYOTA"
        const val MARCA_FORD = "FORD"
        const val MARCA_VW = "VW"
        const val MARCA_FIAT = "FIAT"
    }
}
