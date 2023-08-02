package com.example.pomodortimer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.CountDownTimer

class TimerViewModel : ViewModel() { // dziedziczenie
    private val timer: CountDownTimer // deklaracja
    var timeLeft = MutableLiveData<Long>()
    var timerState = MutableLiveData<TimerState>()

    init {
        timer = object : CountDownTimer(POMODORO_TIME, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.postValue(millisUntilFinished / 1000) // konertowanie na sekundy
            }

            override fun onFinish() {
                timerState.postValue(TimerState.FINISHED)
            }
        }
    }

    fun startPomodoro() {
        timerState.value = TimerState.RUNNING // wywołanie startu na timerze
        timer.start()
    }

    fun stopPomodoro() {
        timerState.value = TimerState.STOPPED // wywołanie stopu na timerze
        timer.cancel()
    }

    enum class TimerState { // Typ wyliczeniowy - 3 możliwe stany running, finished, stopped
        RUNNING, FINISHED, STOPPED // przyjmuje jedną z 3
    }

    companion object {
        const val POMODORO_TIME = 25 * 60 * 1000L // 25 minut
        const val INTERVAL = 1000L // 1 sekunda
    }
}
