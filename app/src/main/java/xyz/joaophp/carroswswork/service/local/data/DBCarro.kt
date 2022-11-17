package xyz.joaophp.carroswswork.service.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBCarro.CARRO_TABLE)
data class DBCarro(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = MARCA_ID) val marcaId: Int? = null,
    @ColumnInfo(name = MARCA_NOME) val marcaNome: String? = null,
    @ColumnInfo(name = NOME_MODELO) val nomeModelo: String? = null,
    @ColumnInfo(name = ANO) val ano: Int? = null,
    @ColumnInfo(name = COMBUSTIVEL) val combustivel: String? = null,
    @ColumnInfo(name = NUMERO_PORTAS) val numeroPortas: Int? = null,
    @ColumnInfo(name = VALOR_FIPE) val valorFipe: Int? = null,
    @ColumnInfo(name = COR) val cor: String? = null,
    @ColumnInfo(name = TIMESTAMP_CADASTRO) val timestampCadastro: Long? = null,
    @ColumnInfo(name = SALVO) val salvo: Boolean? = false,
    @ColumnInfo(name = TIMESTAMP_SALVAMENTO) val timestampSalvamento: Long? = null,
) {

    companion object {
        const val CARRO_TABLE = "carro"
        const val ID = "id"
        const val MARCA_ID = "marca_id"
        const val MARCA_NOME = "marca_nome"
        const val NOME_MODELO = "nome_modelo"
        const val ANO = "ano"
        const val COMBUSTIVEL = "combustivel"
        const val NUMERO_PORTAS = "num_portas"
        const val VALOR_FIPE = "valor_fipe"
        const val COR = "cor"
        const val TIMESTAMP_CADASTRO = "timestamp_cadastro"
        const val SALVO = "salvo"
        const val TIMESTAMP_SALVAMENTO = "timestamp_salvamento"
    }
}