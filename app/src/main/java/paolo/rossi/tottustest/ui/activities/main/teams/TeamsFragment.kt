package paolo.rossi.tottustest.ui.activities.main.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R

class TeamsFragment : Fragment() {

    private lateinit var teamsViewModel: TeamsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        teamsViewModel =
                ViewModelProvider(this).get(TeamsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_teams, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        teamsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    fun setElements(root: View) {
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}