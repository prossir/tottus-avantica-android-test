package paolo.rossi.tottustest.ui.activities.main.teams

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import paolo.rossi.tottustest.data.models.Team
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.data.repositories.MemberRepository
import paolo.rossi.tottustest.data.repositories.TeamRepository
import paolo.rossi.tottustest.data.repositories.UserRepository


class TeamsViewModel(application: Application) : AndroidViewModel(application) {

    private var user_repository: UserRepository = UserRepository(application)
    private var team_repository: TeamRepository = TeamRepository(application)
    private var member_repository: MemberRepository = MemberRepository(application)

    private var user : User = user_repository.retrieveIfLoggedIn()!!
    var teams : LiveData<List<Team>> = team_repository.retrieveUserTeams(user.id)


    fun createTeam(name: String, catchphrase: String): Long {
        return team_repository.create(name, catchphrase, user.id)
    }


    fun createTeamMember(team_position: Int, name: String, email: String): Long {
        return member_repository.create(name, email, teams.value!![team_position].id)
    }


    fun countTeamMembers(team_id: Long): Int {
        return member_repository.countOfTeam(team_id)
    }


    fun refreshTeamMembers() {
        if(teams.value != null) {
            for(team in teams.value!!) {
                team.members_count = member_repository.countOfTeam(team.id)
            }
        }
    }
}