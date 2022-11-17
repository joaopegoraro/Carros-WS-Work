package xyz.joaophp.carroswswork.service.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.service.local.data.DBCarro
import xyz.joaophp.carroswswork.service.local.data.DBLead
import xyz.joaophp.carroswswork.service.local.data.DBLead.Companion.CARRO_ID
import xyz.joaophp.carroswswork.service.local.data.DBLead.Companion.LEAD_TABLE

@Dao
interface LeadDao {

    /**
     *  QUERY
     */

    @Query("SELECT * FROM $LEAD_TABLE")
    fun getAll(): Flow<List<DBCarro>>

    /**
     *  INSERT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lead: List<DBLead>)

    /**
     *   DELETE
     */

    @Query("DELETE FROM $LEAD_TABLE")
    fun clear()

    @Query("DELETE FROM $LEAD_TABLE WHERE $CARRO_ID-:carroId")
    fun deleteWithCarroId(carroId: Int)
}