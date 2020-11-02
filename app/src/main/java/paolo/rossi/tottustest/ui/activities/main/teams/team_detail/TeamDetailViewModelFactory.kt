package paolo.rossi.tottustest.ui.activities.main.teams.team_detail


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import paolo.rossi.tottustest.data.models.Team


@Suppress("UNCHECKED_CAST")
class TeamDetailViewModelFactory(private val application : Application,
                                 val team: Team
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailViewModel::class.java)) {
            return TeamDetailViewModel(application,
                team) as T
        }
        throw IllegalArgumentException("Class different from TeamDetailViewModel")
    }
}