package xyz.joaophp.carroswswork.service.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.service.local.data.DBCarro
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.CARRO_TABLE
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.ID
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.SALVO
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.TIMESTAMP_CADASTRO
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.TIMESTAMP_SALVAMENTO

@Dao
interface CarroDao {

    /**
     *  QUERY
     */

    @Query("SELECT * FROM $CARRO_TABLE ORDER BY $TIMESTAMP_CADASTRO DESC")
    fun getAll(): Flow<List<DBCarro>>

    @Query("SELECT * FROM $CARRO_TABLE WHERE $ID=:id")
    fun getWithId(id: Int): Flow<DBCarro>

    @Query("SELECT * FROM $CARRO_TABLE WHERE $SALVO=1 ORDER BY $TIMESTAMP_SALVAMENTO DESC")
    fun getAllSalvos(): Flow<List<DBCarro>>

    /**
     *  INSERT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(carros: List<DBCarro>)

    /**
     *  UPDATE
     */

    @Query("UPDATE $CARRO_TABLE SET $SALVO=1 WHERE $ID=:id")
    fun salvar(id: Int)

    @Query("UPDATE $CARRO_TABLE SET $SALVO=0 WHERE $ID=:id")
    fun removerSalvo(id: Int)

    /**
     *   DELETE
     */

    @Query("DELETE FROM $CARRO_TABLE")
    fun clear()

}