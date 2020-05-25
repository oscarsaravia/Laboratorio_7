package com.example.android.lab4v2.roles

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.lab4v2.Guest

import com.example.android.lab4v2.R
import com.example.android.lab4v2.databinding.FragmentAddBinding
import com.example.android.lab4v2.databinding.FragmentAddroleBinding
import com.example.android.lab4v2.game.GameViewModel
import com.example.android.lab4v2.utilities.Utils
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_addrole.*

/**
 * A simple [Fragment] subclass.
 */
class AddroleFragment : Fragment() {

    private lateinit var binding: FragmentAddroleBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentAddroleBinding>(inflater,
            R.layout.fragment_addrole, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[GameViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding.seekBar.max = 10
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView11.text = "Orden: "+ progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.textView11.text = "Orden: "+ binding.seekBar.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                binding.textView11.text = "Orden: "+ binding.seekBar.progress
            }

        })



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
                viewModel.rolelist.value += "\nRol " + "\nNombre: " + editText4.text.toString()+ "\nDescripción: "+ editText5.text.toString()+ "\nOrden: " + binding.seekBar.progress.toString()
                view?.findNavController()?.navigate(R.id.action_addroleFragment_to_roleFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
