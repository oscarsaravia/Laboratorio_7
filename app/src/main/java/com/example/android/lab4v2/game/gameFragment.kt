package com.example.android.lab4v2.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.lab4v2.Guest
import com.example.android.lab4v2.R
import com.example.android.lab4v2.databinding.FragmentGameBinding
import com.example.android.lab4v2.utilities.Utils
import kotlinx.android.synthetic.main.fragment_game.*

/**
 * A simple [Fragment] subclass.
 */
class gameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding
    private var guest = Guest()
    private var listado = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game, container, false)

        binding.guest = guest
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        Utils.registrados = 0
        Utils.invitados = 0
        Utils.listado = ""

        viewModel.name.observe(viewLifecycleOwner, Observer { newName ->
            binding.textView3.text = newName
        })
        viewModel.phone.observe(viewLifecycleOwner, Observer { newPhone ->
            binding.textView4.text = newPhone
        })
        viewModel.mail.observe(viewLifecycleOwner, Observer { newMail ->
            binding.textView5.text = newMail
        })
        viewModel.index.observe(viewLifecycleOwner, Observer { newIndex ->
            (activity as AppCompatActivity).supportActionBar?.title = "Registrando (" + (newIndex + 1) +"/" + viewModel.listSize + ")"
        })



        if(viewModel.people.size == 0){
            Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()


        }else{
            viewModel.setFirst()
            updateGuestText()
            (activity as AppCompatActivity).supportActionBar?.title = "Registrando (1/" + viewModel.people.size + ")"
        }




        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.answer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.index.value = viewModel.index.value?.plus(1)
        when(item.itemId){

            R.id.check_button -> {
                if(viewModel.index.value!! < viewModel.people.size){


                    //(activity as AppCompatActivity).supportActionBar?.title = "Registrando (" + (viewModel.index + 1) +"/" + viewModel.listSize + ")"
                    viewModel.isAssisting()
                    Utils.listado += viewModel.name.value + " si"
                    updateGuestText()
                    Utils.invitados ++
                    Utils.registrados ++

                }else{
                    viewModel.isAssistingLast()
                    Utils.listado += viewModel.name.value + " si"
                    Utils.invitados ++
                    Utils.registrados ++
                    viewModel.index.value = 0
                    view?.findNavController()?.navigate(R.id.action_gameFragment_to_resultsFragment)



                }
            }



            R.id.deny_button -> {
                if(viewModel.index.value!! < viewModel.people.size){

                    //(activity as AppCompatActivity).supportActionBar?.title = "Registrando (" + (viewModel.index + 1) +"/" + viewModel.listSize + ")"
                    viewModel.notAssisting()
                    Utils.registrados ++
                    Utils.listado += viewModel.name.value + " no"
                    updateGuestText()

                }else{
                    viewModel.notAssistingLast()
                    Utils.registrados ++
                    viewModel.index.value = 0
                    Utils.listado += viewModel.name.value + " no"
                    view?.findNavController()?.navigate(R.id.action_gameFragment_to_resultsFragment)



                }

            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateGuestText(){
        //binding.textView3.text = viewModel.name
        //binding.textView4.text = "Teléfono: " + viewModel.phone
        //binding.textView5.text = "E-mail: " + viewModel.phone
    }

    fun listFinished(){
        view?.findNavController()?.navigate(R.id.action_gameFragment_to_resultsFragment)
    }











}
