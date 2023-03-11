package com.example.anmpweek2kpc

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmitAnswer = view.findViewById<Button>(R.id.btnSubmitAnswer)
        val txtQuestion = view.findViewById<TextView>(R.id.txtQuestion)
        val txtAnswer = view.findViewById<EditText>(R.id.txtAnswer)
        val playerTurn  = view.findViewById<TextView>(R.id.txtTurn)

        var playerScore = 0

        if(arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            playerTurn.text = "$playerName's Turn"
        }

        var number1 = (0..100).random()
        var number2 = (0..100).random()
        txtQuestion.text = "$number1 + $number2"

        btnSubmitAnswer.setOnClickListener {
            var answer = txtAnswer.text.toString().toInt()
            if(answer == (number1 + number2)) {
                number1 = (0..100).random()
                number2 = (0..100).random()
                txtQuestion.text = "$number1 + $number2"

                playerScore++

                txtAnswer.setText("")
                txtAnswer.requestFocus()
            }
            else {
                val action = GameFragmentDirections.actionResultFragment(playerScore)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}