package xyz.joaophp.carroswswork.service.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.service.local.data.DBLead
import xyz.joaophp.carroswswork.service.local.data.DBLead.Companion.CARRO_ID
import xyz.joaophp.carroswswork.service.local.data.DBLead.Companion.LEAD_TABLE

@Dao
interface LeadDao {

    /**
     *  QUERY
     */

    @Query("SELECT * FROM $LEAD_TABLE")
    fun getAll(): Flow<List<DBLead>>

    /**
     *  INSERT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lead: DBLead)

    /**
     *   DELETE
     */

    @Query("DELETE FROM $LEAD_TABLE")
    suspend fun clear()

    @Query("DELETE FROM $LEAD_TABLE WHERE $CARRO_ID=:carroId")
    suspend fun deleteWithCarroId(carroId: Int)
}