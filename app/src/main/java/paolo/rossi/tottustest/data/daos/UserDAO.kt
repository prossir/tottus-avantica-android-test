package paolo.rossi.tottustest.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import paolo.rossi.tottustest.data.models.User


@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE is_logged = 1 LIMIT 1")
    fun retrieveLoggedIn(): User?


    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    fun retrieveByEmailAndPassword(email: String, password: String): User?


    @Query("UPDATE user SET is_logged = :logging_status WHERE id = :user_id")
    fun setLoggedIn(logging_status: Int, user_id: Long)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User) : Long


    @Delete
    fun delete(user: User)

}