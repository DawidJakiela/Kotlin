package com.example.pomodortimer

import android.os.Bundle // klasa do przechowywania danych
import androidx.appcompat.app.AppCompatActivity // klasa bazowa
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() { // dziedziczenie
    override fun onCreate(savedInstanceState: Bundle?) { // nadpisywanie metody "onCreate"
        super.onCreate(savedInstanceState) // metoda przyjmuje argument savedInstanceState
        setContentView(R.layout.activity_main) // wywoływanie metody onCreate w klasie nadrzędnej,
        // a następnie ustawianie layout za pomocą metody setContentView.

        // Jeżeli jest to pierwsze uruchomienie activity (czyli savedInstanceState == null),
        // dodajemy nasz TimerFragment do kontenera.
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, TimerFragment::class.java, null)
            }
        }
    }
}