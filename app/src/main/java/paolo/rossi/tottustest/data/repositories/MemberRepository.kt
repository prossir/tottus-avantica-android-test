package paolo.rossi.tottustest.data.repositories

import android.content.Context
import paolo.rossi.tottustest.data.AppDatabase
import paolo.rossi.tottustest.data.daos.MemberDAO
import paolo.rossi.tottustest.data.models.Member


class MemberRepository(context: Context) {

    var memberDAO: MemberDAO

    init {
        val db = AppDatabase.getInstance(context)
        memberDAO = db.memberDAO()
    }


    fun create(name: String, email: String, team_id: Long): Long {
        val member = Member(name = name, email = email, created_at = System.currentTimeMillis(), status = 1, team_id = team_id)
        return memberDAO.insert(member)
    }


    fun countOfTeam(team_id: Long): Int {
        return memberDAO.countMembers(team_id)
    }



    fun retrieveByTeamId(team_id: Long): List<Member> {
        return memberDAO.retrieveByTeamId(team_id)
    }

    fun delete(member_id: Long) {
        val member = memberDAO.retrieveById(member_id)
        if (member != null) {
            memberDAO.delete(member)
        }
    }


    fun sofDelete(member_id: Long) {
        memberDAO.softDelete(member_id)
    }

}