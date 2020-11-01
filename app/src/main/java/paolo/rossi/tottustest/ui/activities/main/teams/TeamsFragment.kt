package paolo.rossi.tottustest.ui.activities.main.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.adapters.TeamsAdapter
import paolo.rossi.tottustest.databinding.FragmentTeamsBinding
import paolo.rossi.tottustest.ui.activities.main.teams.team_detail.TeamDetailIntent
import paolo.rossi.tottustest.ui.dialogs.AddMemberBottomDialog
import paolo.rossi.tottustest.ui.dialogs.CreateTeamDialog


class TeamsFragment : Fragment() {

    private lateinit var view_model: TeamsViewModel
    private lateinit var binding: FragmentTeamsBinding

    private var create_team_dialog : CreateTeamDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)
        setElements()
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        create_team_dialog?.dismiss()
    }


    fun setElements() {
        view_model = ViewModelProvider(this).get(TeamsViewModel::class.java)
        view_model.teams.observe(viewLifecycleOwner, Observer { teams->
            for(team in teams) {
                team.members_count = view_model.countTeamMembers(team.id)
            }

            if (binding.rvTeams.adapter == null) {
                binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
                binding.rvTeams.adapter = TeamsAdapter(teams,
                    on_item_clicked_function =  {
                        startActivity(requireContext().TeamDetailIntent())
                    }, on_add_member_click_listener = { position ->
                        val create_member_dialog = AddMemberBottomDialog(requireContext(),
                            teams[position].name,
                            on_member_creation_clicked = { name, email ->
                                if (view_model.createTeamMember(position, name, email) >= 1) {
                                    Snackbar.make(
                                        requireActivity().findViewById(android.R.id.content),
                                        "Se creo al miembro del equipo exitosamente",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                    (binding.rvTeams.adapter as TeamsAdapter).reloadMembers(position)
                                } else {
                                    Snackbar.make(
                                        requireActivity().findViewById(android.R.id.content),
                                        "No se pudo crear al miembro del equipo",
                                        Snackbar.LENGTH_LONG
                                    ).setAction("OK", null).show()
                                }
                            }
                        )
                        create_member_dialog.show()
                    }
                )
            } else {
                (binding.rvTeams.adapter as TeamsAdapter).reload(teams)
            }

            if (teams.isEmpty()) {
                binding.tvEmptyTeams.visibility = View.VISIBLE
                binding.rvTeams.visibility = View.GONE
            } else {
                binding.tvEmptyTeams.visibility = View.GONE
                binding.rvTeams.visibility = View.VISIBLE
            }
        })

        if(!binding.fabAddTeam.hasOnClickListeners()) {
            binding.fabAddTeam.setOnClickListener {
                if(create_team_dialog == null) {
                    create_team_dialog = CreateTeamDialog(requireContext(),
                        on_team_creation_clicked = { name, catchphrase ->
                            if (view_model.createTeam(name, catchphrase) >= 1) {
                                Snackbar.make(
                                    requireActivity().findViewById(android.R.id.content),
                                    "Se creo el equipo exitosamente",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                Snackbar.make(
                                    requireActivity().findViewById(android.R.id.content),
                                    "No se pudo crear el equipo",
                                    Snackbar.LENGTH_LONG
                                ).setAction("OK", null).show()
                            }
                        })
                }
                create_team_dialog?.show()
            }
        }
    }
}