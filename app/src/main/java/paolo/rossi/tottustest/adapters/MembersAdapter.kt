package paolo.rossi.tottustest.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_member.view.*
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.data.models.Member


class MembersAdapter(private var members: List<Member>,
                   private var on_delete_member_click_listener: ((Int) -> Unit)?
) : RecyclerView.Adapter<MembersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_member, parent, false)
        return ViewHolder(view.v_holder)
    }


    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_name.text = members[position].name
        holder.tv_email.text = members[position].email

        if (!holder.ib_delete.hasOnClickListeners()) {
            holder.ib_delete.setOnClickListener {
                on_delete_member_click_listener?.invoke(position)
            }
        }
    }


    override fun getItemCount(): Int {
        return members.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var v_holder: View = view.v_holder
        var tv_name: TextView = view.tv_name
        var tv_email: TextView = view.tv_email

        var ib_delete : ImageButton = view.ib_delete
    }
}