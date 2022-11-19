package xyz.joaophp.carroswswork.service.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBPerfil.PERFIL_TABLE)
data class DBPerfil(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = PERFIL_EMAIL)
    val email: String? = null
) {
    companion object {
        const val PERFIL_TABLE = "perfil_table"
        const val PERFIL_EMAIL = "perfil_email"
    }
}
