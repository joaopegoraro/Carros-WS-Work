package xyz.joaophp.carroswswork.service.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.service.local.data.DBCarro
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.CARRO_TABLE
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.ID
import xyz.joaophp.carroswswork.service.local.data.DBCarro.Companion.TIMESTAMP_CADASTRO

@Dao
interface CarroDao {

    /**
     *  QUERY
     */

    @Query("SELECT * FROM $CARRO_TABLE ORDER BY $TIMESTAMP_CADASTRO DESC")
    fun getAll(): Flow<List<DBCarro>>

    @Query("SELECT * FROM $CARRO_TABLE WHERE $ID=:id")
    fun getWithId(id: Int): Flow<DBCarro>

    @Query("SELECT * FROM $CARRO_TABLE WHERE $ID in (:ids)")
    fun getAllWithId(ids: List<Int>): Flow<List<DBCarro>>

    /**
     *  INSERT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(carros: List<DBCarro>)

    /**
     *   DELETE
     */

    @Query("DELETE FROM $CARRO_TABLE")
    suspend fun clear()

}