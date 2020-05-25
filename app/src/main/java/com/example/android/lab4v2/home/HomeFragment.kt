package com.example.android.lab4v2.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.lab4v2.R
import com.example.android.lab4v2.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)

        //Set fragment title
        (activity as AppCompatActivity).supportActionBar?.title = "Inicio"

        //ViewModel instance
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        //Listener to start
        binding.button.setOnClickListener{ v:View ->
            v.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }
        setHasOptionsMenu(true)
        activity?.title = "Inicio"
        return binding.root
    }

    //Methods for menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }




}
