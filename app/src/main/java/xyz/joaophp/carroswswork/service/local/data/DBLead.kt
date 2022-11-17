package xyz.joaophp.carroswswork.service.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBLead.LEAD_TABLE)
data class DBLead(
    @PrimaryKey
    @ColumnInfo(name = CARRO_ID)
    val carroId: Int,

    @ColumnInfo(name = EMAIL_USUARIO) val emailUsuario: String? = null,
    @ColumnInfo(name = TELEFONE_USUARIO) val telefoneUsuario: String? = null,
    @ColumnInfo(name = TIMESTAMP) val timestamp: Long? = null
) {

    companion object {
        const val LEAD_TABLE = "lead"
        const val CARRO_ID = "carro_id"
        const val EMAIL_USUARIO = "email_usuario"
        const val TELEFONE_USUARIO = "telefone_usuario"
        const val TIMESTAMP = "timestamp"
    }
}
