package com.example.kreatorzamowieniakawy  // Sprawdź, czy nazwa pakietu jest poprawna

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kreatorzamowieniakawy.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("MainActivity", "onCreate called")

        // Inicjalizacja widoków
        val espressoRadioButton: RadioButton = findViewById(R.id.espresso)
        val cappuccinoRadioButton: RadioButton = findViewById(R.id.cappuccino)
        val latteRadioButton: RadioButton = findViewById(R.id.latte)

        val cukierCheckBox: CheckBox = findViewById(R.id.cukier)
        val mlekoCheckBox: CheckBox = findViewById(R.id.mleko)

        val seekBar: SeekBar = findViewById(R.id.ilosc)
        val seekBarValue: TextView = findViewById(R.id.seekBarValue)

        val zamowButton: Button = findViewById(R.id.zamow_button)

        // Ustaw nasłuchiwacz na SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Zaktualizuj wartość w TextView
                seekBarValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Nasłuchiwacz dla przycisku Zamów
        zamowButton.setOnClickListener {
            // Sprawdź, która kawa została wybrana
            val selectedCoffee = when {
                espressoRadioButton.isChecked -> "Espresso"
                cappuccinoRadioButton.isChecked -> "Cappuccino"
                latteRadioButton.isChecked -> "Latte"
                else -> "Brak wyboru"
            }

            // Sprawdź wybrane dodatki
            val dodatki = mutableListOf<String>()
            if (cukierCheckBox.isChecked) {
                dodatki.add("Cukier")
            }
            if (mlekoCheckBox.isChecked) {
                dodatki.add("Mleko")
            }

            // Sprawdź ilość kawy
            val iloscKawy = seekBar.progress

            // Tworzymy wiadomość z zamówieniem
            val zamowienie = "Zamówienie:\nKawa: $selectedCoffee\nDodatki: ${dodatki.joinToString(", ")}\nIlość: $iloscKawy"

            // Wyświetl komunikat z zamówieniem
            Toast.makeText(this, zamowienie, Toast.LENGTH_LONG).show()
        }
    }
}
