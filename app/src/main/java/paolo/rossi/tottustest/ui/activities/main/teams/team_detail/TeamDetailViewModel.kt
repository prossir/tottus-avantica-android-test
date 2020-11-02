package paolo.rossi.tottustest.ui.activities.main.teams.team_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import paolo.rossi.tottustest.data.models.Member
import paolo.rossi.tottustest.data.models.Team
import paolo.rossi.tottustest.data.repositories.MemberRepository


class TeamDetailViewModel(application: Application, val team: Team) : AndroidViewModel(application) {

    private var member_repository: MemberRepository = MemberRepository(application)
    var members : MutableLiveData<List<Member>> = MutableLiveData(member_repository.retrieveByTeamId(team.id))


    fun createTeamMember(name: String, email: String): Long {
        val result = member_repository.create(name, email, team.id)
        members.postValue(member_repository.retrieveByTeamId(team.id))
        return result
    }


    fun deleteTeamMember(member_id : Long) {
        member_repository.sofDelete(member_id)
        members.postValue(member_repository.retrieveByTeamId(team.id))
    }

}