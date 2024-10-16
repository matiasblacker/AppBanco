package com.example.eje7_appbanco


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eje7_appbanco.R

class MainActivity : AppCompatActivity() {

    private var saldo: Double = 120000.0
    private lateinit var textViewSaldo: TextView
    private lateinit var textViewAccion: TextView
    private lateinit var editTextMonto: EditText
    private lateinit var radioGroup: RadioGroup

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSaldo = findViewById(R.id.textViewSaldo)
        textViewAccion = findViewById(R.id.textViewAccion)
        editTextMonto = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        val buttonOk: Button = findViewById(R.id.buttonOk)


        buttonOk.setOnClickListener {
            manejarAccion()
        }
    }

    private fun manejarAccion() {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val monto = editTextMonto.text.toString().toDoubleOrNull() ?: 0.0

        when (selectedRadioButtonId) {
            R.id.radioButtonVerSaldo -> {
                mostrarSaldo()
            }
            R.id.radioButtonDepositar -> {
                if (monto > 0) {
                    saldo += monto
                    mostrarSaldo()
                    textViewAccion.text = "Se han depositado: $${monto.format(2)}"
                } else {
                    textViewAccion.text = "Ingrese un monto válido para depositar."
                }
            }
            R.id.radioButtonRetirar -> {
                if (monto > 0) {
                    if (monto > saldo) {
                        textViewAccion.text = "No se puede retirar más de lo que hay en la cuenta."
                    } else {
                        saldo -= monto
                        mostrarSaldo()
                        textViewAccion.text = "Se han retirado: $${monto.format(2)}"
                    }
                } else {
                    textViewAccion.text = "Ingrese un monto válido para retirar."
                }
            }
            R.id.radioButtonSalir -> {
                finish() // Cierra la aplicación
            }
        }
    }

    private fun mostrarSaldo() {
        textViewSaldo.text = "Saldo disponible: $${saldo.format(2)}"
    }

    // Función para formatear números a dos decimales
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
