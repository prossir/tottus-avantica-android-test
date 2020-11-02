package paolo.rossi.tottustest.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import paolo.rossi.tottustest.data.models.Member


@Dao
interface MemberDAO {

    @Query("SELECT count(id) FROM member WHERE team_id = :team_id AND status = 1")
    fun countMembers(team_id: Long) : Int


    @Query("SELECT * FROM member WHERE team_id = :team_id AND status = 1")
    fun retrieveByTeamId(team_id: Long): List<Member>


    @Query("SELECT * FROM member WHERE id = :member_id")
    fun retrieveById(member_id: Long): Member


    @Query("UPDATE member SET status = 0 WHERE id = :member_id ")
    fun softDelete(member_id: Long)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member) : Long


    @Delete
    fun delete(member: Member)

}