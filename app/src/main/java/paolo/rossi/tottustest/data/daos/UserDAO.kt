package paolo.rossi.tottustest.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import paolo.rossi.tottustest.data.models.User


@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE is_logged = 1 LIMIT 1")
    fun retrieveLoggedIn(): User?

    @Query("SELECT * FROM user WHERE status = 1")
    fun retrieveDetailedActive() : LiveData<List<User>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)


    @Delete
    fun delete(user: User)


}