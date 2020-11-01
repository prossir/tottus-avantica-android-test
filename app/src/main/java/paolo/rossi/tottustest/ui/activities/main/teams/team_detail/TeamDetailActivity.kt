package paolo.rossi.tottustest.ui.activities.main.teams.team_detail


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import paolo.rossi.tottustest.R


fun Context.TeamDetailIntent(): Intent {
    return Intent(this, TeamDetailActivity::class.java).apply {

    }
}


class TeamDetailActivity : AppCompatActivity() {

    private lateinit var view_model : TeamDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
    }
}