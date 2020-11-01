package paolo.rossi.tottustest.data.daos

import androidx.room.*
import paolo.rossi.tottustest.data.models.Member


@Dao
interface MemberDAO {

    @Query("SELECT * FROM member WHERE status = 1")
    fun retrieveDetailedActive() : List<Member>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member)


    @Delete
    fun delete(member: Member)

}