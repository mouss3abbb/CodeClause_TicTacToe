package com.example.codeclause_tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.codeclause_tictactoe.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.btn1.setOnClickListener { viewModel.plays(it) }
        binding.btn2.setOnClickListener { viewModel.plays(it) }
        binding.btn3.setOnClickListener { viewModel.plays(it) }
        binding.btn4.setOnClickListener { viewModel.plays(it) }
        binding.btn5.setOnClickListener { viewModel.plays(it) }
        binding.btn6.setOnClickListener { viewModel.plays(it) }
        binding.btn7.setOnClickListener { viewModel.plays(it) }
        binding.btn8.setOnClickListener { viewModel.plays(it) }
        binding.btn9.setOnClickListener { viewModel.plays(it) }
        binding.back.setOnClickListener {
            it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToMainScreenFragment())
        }
        binding.reset.setOnClickListener {
            viewModel.totalPlays = 0
            viewModel.scoreX.value = 0
            viewModel.scoreO.value = 0
            binding.btn1.text = ""
            binding.btn2.text = ""
            binding.btn3.text = ""
            binding.btn4.text = ""
            binding.btn5.text = ""
            binding.btn6.text = ""
            binding.btn7.text = ""
            binding.btn8.text = ""
            binding.btn9.text = ""
        }
        viewModel.scoreX.observe(viewLifecycleOwner){
            binding.scoreX.text = "Score X:" + it.toString()
        }
        viewModel.scoreO.observe(viewLifecycleOwner){
            binding.scoreO.text ="Score O:"+ it.toString()
        }
    }


}