package paolo.rossi.tottustest.data.daos

import androidx.room.*
import paolo.rossi.tottustest.data.models.Member


@Dao
interface MemberDAO {

    @Query("SELECT * FROM member WHERE status = 1")
    fun retrieveDetailedActive() : List<Member>


    @Query("SELECT count(id) FROM member WHERE team_id = :team_id")
    fun countMembers(team_id: Long) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member) : Long


    @Delete
    fun delete(member: Member)

}