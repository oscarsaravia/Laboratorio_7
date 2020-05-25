package com.example.android.lab4v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.lab4v2.databinding.FragmentGameBinding
import com.example.android.lab4v2.databinding.FragmentInvitedBinding
import com.example.android.lab4v2.game.GameViewModel
import kotlinx.android.synthetic.main.fragment_invited.*

/**
 * A simple [Fragment] subclass.
 */
class InvitedFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentInvitedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentInvitedBinding>(inflater,
            R.layout.fragment_invited, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[GameViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.namelist.observe(viewLifecycleOwner, Observer { newList ->
            binding.textView9.text = newList
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Invitados"

        binding.fabBtn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_invitedFragment_to_addFragment)
        }

        return binding.root
    }

}
