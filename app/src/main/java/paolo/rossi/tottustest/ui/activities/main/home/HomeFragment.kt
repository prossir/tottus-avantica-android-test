package paolo.rossi.tottustest.ui.activities.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var view_model: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setElements()
        return binding.root
    }


    private fun setElements() {
        view_model = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.tvName.text = view_model.user.name
        binding.tvEmail.text = view_model.user.email
    }

}