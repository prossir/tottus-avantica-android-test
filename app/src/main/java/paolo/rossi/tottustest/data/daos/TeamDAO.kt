package paolo.rossi.tottustest.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import paolo.rossi.tottustest.data.models.Team


@Dao
interface TeamDAO {

    @Query("SELECT * FROM team WHERE status = 1")
    fun retrieveDetailedActive() : LiveData<List<Team>>


    @Query("SELECT * FROM team WHERE user_id = :user_id")
    fun retrieveByUserId(user_id: Long): LiveData<List<Team>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: Team) : Long


    @Delete
    fun delete(team: Team)


}