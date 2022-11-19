package xyz.joaophp.carroswswork.service.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.service.local.data.DBPerfil
import xyz.joaophp.carroswswork.service.local.data.DBPerfil.Companion.PERFIL_TABLE

@Dao
interface PerfilDao {

    /**
     *  QUERY
     */

    @Query("SELECT * FROM $PERFIL_TABLE")
    fun get(): Flow<DBPerfil?>

    /**
     *  INSERT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(perfil: DBPerfil)

    /**
     *   DELETE
     */

    @Query("DELETE FROM $PERFIL_TABLE")
    suspend fun delete()
}