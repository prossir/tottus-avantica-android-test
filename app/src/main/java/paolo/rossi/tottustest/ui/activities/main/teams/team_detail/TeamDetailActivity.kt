package paolo.rossi.tottustest.ui.activities.main.teams.team_detail


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.adapters.MembersAdapter
import paolo.rossi.tottustest.data.models.Team
import paolo.rossi.tottustest.databinding.ActivityTeamDetailBinding
import paolo.rossi.tottustest.interfaces.Constants
import paolo.rossi.tottustest.ui.dialogs.AddMemberBottomDialog
import paolo.rossi.tottustest.ui.dialogs.WarningDialog


fun Context.TeamDetailIntent(team: Team): Intent {
    return Intent(this, TeamDetailActivity::class.java).apply {
        putExtra(Constants.INTENT_TEAM_URL, team)
    }
}


class TeamDetailActivity : AppCompatActivity() {

    private lateinit var view_model : TeamDetailViewModel
    private lateinit var binding : ActivityTeamDetailBinding

    private var create_member_dialog : AddMemberBottomDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setElements()
    }


    override fun onDestroy() {
        super.onDestroy()
        create_member_dialog?.dismiss()
    }


    private fun setElements() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_detail)

        val team = intent.getParcelableExtra<Team>(Constants.INTENT_TEAM_URL)
        view_model = ViewModelProvider(this, TeamDetailViewModelFactory(
            application = this.application,
            team!!
        )).get(TeamDetailViewModel::class.java)

        binding.rvMembers.layoutManager = LinearLayoutManager(this)

        view_model.members.observe(this, { members ->
            binding.rvMembers.adapter = MembersAdapter(members,
                on_delete_member_click_listener = { position ->
                    val dialog = WarningDialog(
                        context = this,
                        dismissible = false,
                        with_deny_option = true,
                        title = "Eliminacion de miembro de equipo",
                        message = "¿Estas seguro que deseas eliminar a este miembro?",
                        acceptance_title = "Sí",
                        acceptance_function = {
                            view_model.deleteTeamMember(members[position].id)
                            Snackbar.make(
                                findViewById(android.R.id.content),
                                "Se elimino al miembro del equipo exitosamente",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            //(binding.rvMembers.adapter as MembersAdapter).reload(members)
                        },
                        denial_title = "No",
                        denial_function = null
                    )
                    dialog.show()
                }
            )

            if (members.isEmpty()) {
                binding.tvEmptyMembers.visibility = View.VISIBLE
                binding.rvMembers.visibility = View.GONE
            } else {
                binding.tvEmptyMembers.visibility = View.GONE
                binding.rvMembers.visibility = View.VISIBLE
            }
        })


        if(!binding.fabAddTeam.hasOnClickListeners()) {
            binding.fabAddTeam.setOnClickListener {
                if(create_member_dialog == null) {
                    create_member_dialog = AddMemberBottomDialog(true,this,
                        view_model.team.name,
                        on_member_creation_clicked = { name, email ->
                            if (view_model.createTeamMember(name, email) >= 1) {
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Se creo al miembro del equipo exitosamente",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "No se pudo crear al miembro del equipo",
                                    Snackbar.LENGTH_LONG
                                ).setAction("OK", null).show()
                            }
                        }
                    )
                }
                create_member_dialog?.show()
            }
        }
    }

}