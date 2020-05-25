package com.example.android.lab4v2.roles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.lab4v2.Adapter
import com.example.android.lab4v2.ItemDataClass
import com.example.android.lab4v2.R
import com.example.android.lab4v2.databinding.FragmentRoleBinding
import com.example.android.lab4v2.game.GameViewModel
import kotlinx.android.synthetic.main.fragment_role.*

/**
 * A simple [Fragment] subclass.
 */
class RoleFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentRoleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Roles"

        binding = DataBindingUtil.inflate<FragmentRoleBinding>(inflater,
            R.layout.fragment_role, container, false)

        val adapter = Adapter()
        binding.itemList.adapter = adapter


        viewModel = activity?.run {
            ViewModelProviders.of(this)[GameViewModel::class.java]
        } ?: throw Exception("Invalid Activity")


        viewModel.roll.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        /*viewModel.roll.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }

        })
        */




        binding.fabBtn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_roleFragment_to_addroleFragment)
        }

        return binding.root
    }


}
