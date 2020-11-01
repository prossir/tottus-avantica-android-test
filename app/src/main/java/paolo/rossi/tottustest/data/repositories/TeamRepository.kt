package paolo.rossi.tottustest.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import paolo.rossi.tottustest.data.AppDatabase
import paolo.rossi.tottustest.data.daos.MemberDAO
import paolo.rossi.tottustest.data.daos.TeamDAO
import paolo.rossi.tottustest.data.models.Team


class TeamRepository(context: Context) {
    var teamDAO: TeamDAO

    init {
        val db = AppDatabase.getInstance(context)
        teamDAO = db.teamDAO()
    }


    fun retrieveUserTeams(user_id: Long): LiveData<List<Team>> {
        val teams = teamDAO.retrieveByUserId(user_id)
        return teams
    }


    fun create(name: String, catchphrase: String, user_id: Long): Long {
        val team = Team(name = name, catchphrase = catchphrase, status = 1, created_at = System.currentTimeMillis(), user_id = user_id)
        return teamDAO.insert(team)
    }

}
