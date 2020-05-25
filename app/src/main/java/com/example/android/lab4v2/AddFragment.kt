package com.example.android.lab4v2

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.lab4v2.databinding.FragmentAddBinding
import com.example.android.lab4v2.databinding.FragmentInvitedBinding
import com.example.android.lab4v2.game.GameViewModel
import com.example.android.lab4v2.utilities.Utils
import kotlinx.android.synthetic.main.fragment_add.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentAddBinding
    private var count = 0
    var personas: MutableList<Guest> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater,
            R.layout.fragment_add, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[GameViewModel::class.java]
        } ?: throw Exception("Invalid Actibity")
        (activity as AppCompatActivity).supportActionBar?.title = "Agregar Invitado"
        setHasOptionsMenu(true)



        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.save_button -> {
                Utils.lista.add(Guest(editText.text.toString(), editText2.text.toString(), editText3.text.toString()))
                viewModel.addList(personas)
                //Toast.makeText(activity, viewModel.people1.value?.get(0)?.name, Toast.LENGTH_SHORT).show()
                viewModel.namelist.value += "\nInvitado" + "\nNombre: " + editText.text.toString()+"\nTelefono: " + editText2.text.toString()+"\nMail: "+editText3.text.toString()+"\n"
                view?.findNavController()?.navigate(R.id.action_addFragment_to_invitedFragment)
            }



        }
        return super.onOptionsItemSelected(item)
    }




}
