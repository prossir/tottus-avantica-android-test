package paolo.rossi.tottustest.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_team.view.*
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.data.models.Team


class TeamsAdapter(private var teams: List<Team>,
                   private var on_item_clicked_function: ((Int) -> Unit)?,
                   private var on_add_member_click_listener: ((Int) -> Unit)?
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_team, parent, false)
        return ViewHolder(view.v_holder)
    }


    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_catchphrase.text = teams[position].catchphrase
        holder.tv_name.text = teams[position].name
        holder.tv_members_count.text = "${teams[position].members_count} miembros"

        if (!holder.v_holder.hasOnClickListeners()) {
            holder.v_holder.setOnClickListener {
                on_item_clicked_function?.invoke(position)
            }
        }

        if (!holder.ib_add_team_member.hasOnClickListeners()) {
            holder.ib_add_team_member.setOnClickListener {
                on_add_member_click_listener?.invoke(position)
            }
        }
    }


    override fun getItemCount(): Int {
        return teams.size
    }



    fun reload(teams: List<Team>?) {
        if (teams != null) {
            this.teams = teams!!
            notifyDataSetChanged()
        }

    }

    fun reloadMembers(team_position: Int) {
        teams[team_position].members_count++
        notifyItemChanged(team_position)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var v_holder: View = view.v_holder
        var tv_catchphrase: TextView = view.tv_catchphrase
        var tv_members_count: TextView = view.tv_members_count
        var tv_name: TextView = view.tv_name
        var ib_add_team_member : ImageButton = view.ib_add_team_member
    }
}