package com.example.android.lab4v2.results

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.lab4v2.R
import com.example.android.lab4v2.databinding.FragmentInvitedBinding
import com.example.android.lab4v2.databinding.FragmentResultsBinding
import com.example.android.lab4v2.game.GameViewModel
import com.example.android.lab4v2.utilities.Utils

/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentResultsBinding>(inflater,
            R.layout.fragment_results, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[GameViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding.textView7.text = "Invitados: " + Utils.invitados.toString()
        binding.textView8.text = "Registrados: " + Utils.registrados.toString()





        binding.reiniciar.setOnClickListener{v:View->
            v.findNavController().navigate(R.id.action_resultsFragment_to_gameFragment)
        }


        (activity as AppCompatActivity).supportActionBar?.title = "Resultados"

        setHasOptionsMenu(true)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val lista = sharedPref?.getString("listado", "")

        binding.button3.setOnClickListener{
            Toast.makeText(activity, Utils.listado, Toast.LENGTH_LONG).show()
        }

        return binding.root
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.share, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        when (item.itemId) {
            R.id.share_button -> {
                val lista = Utils.listado
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, lista);
                startActivity(Intent.createChooser(shareIntent, "Share via"))

            }


        }
        return super.onOptionsItemSelected(item)
    }
}
