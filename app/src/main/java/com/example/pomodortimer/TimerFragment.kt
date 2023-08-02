package com.example.pomodortimer

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.pomodortimer.TimerViewModel
import com.example.pomodortimer.R

class TimerFragment : Fragment(R.layout.fragment_timer) { // dziedziczenie
    private val viewModel: TimerViewModel by viewModels() // Tworzymy prywatną, właściwość viewModel typu TimerViewModel.
    // Używamy delegata viewModels() do
    // automatycznego utworzenia i przypisania instancji TimerViewModel do tej właściwości.

    private lateinit var timerText: TextView
    private lateinit var startButton: Button // deklaracja prywatnych właściwości "timerText" ...
    private lateinit var stopButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Nadpisujemy metodę onViewCreated, która jest
        // wywoływana po utworzeniu widoku fragmentu. Wywołujemy najpierw metodę onViewCreated klasy nadrzędnej.
        super.onViewCreated(view, savedInstanceState)
        timerText = view.findViewById(R.id.timer_text) // Inicjalizacja wcześniej zadeklarowanych elementów
        startButton = view.findViewById(R.id.start_button) // za pomocą metody finViewById
        stopButton = view.findViewById(R.id.stop_button)

        startButton.setOnClickListener { viewModel.startPomodoro() } // "słuchacz" dla przycisku startButton
        stopButton.setOnClickListener { viewModel.stopPomodoro() } // Gdy wciśnięty wywołuję metodę startTimer

        viewModel.timeLeft.observe(viewLifecycleOwner, Observer { timeLeft ->
            timerText.text = timeLeft.toString()
        })

        viewModel.timerState.observe(viewLifecycleOwner, Observer { timerState ->
            when (timerState) {
                TimerViewModel.TimerState.RUNNING -> {
                }
                TimerViewModel.TimerState.FINISHED -> {
                }
                TimerViewModel.TimerState.STOPPED -> {
                }
                // obserwacja stanu timera, wykorzystywana do zmiany stanu w interfejsie
                // when do sprawdzenia aktualnego stanu
                // gdy running to timer aktywny - analogicznie finished oraz stopped
            }
        })
    }
}