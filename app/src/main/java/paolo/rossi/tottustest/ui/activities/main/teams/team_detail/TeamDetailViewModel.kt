package paolo.rossi.tottustest.ui.activities.main.teams.team_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import paolo.rossi.tottustest.data.models.Team
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.data.repositories.TeamRepository
import paolo.rossi.tottustest.data.repositories.UserRepository


class TeamDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var team_repository: TeamRepository = TeamRepository(application)
    private var user_repository: UserRepository = UserRepository(application)

    private var user : User = user_repository.retrieveIfLoggedIn()!!
    var teams : LiveData<List<Team>> = team_repository.retrieveUserTeams(user.id)

    fun createTeam(name: String, catchphrase: String): Long {
        return team_repository.create(name, catchphrase, user.id)
    }

}