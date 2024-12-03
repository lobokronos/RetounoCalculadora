package com.uax.retounocalculadora

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uax.retounocalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        acciones()

    }


    /**Este método contendrá las "Escuchas" de cada botón de la calculadora.**/

    private fun acciones() {


        binding.button0.setOnClickListener (this)
        binding.button1.setOnClickListener (this)
        binding.button2.setOnClickListener (this)
        binding.button3.setOnClickListener (this)
        binding.button4.setOnClickListener (this)
        binding.button5.setOnClickListener (this)
        binding.button6.setOnClickListener (this)
        binding.button7.setOnClickListener (this)
        binding.button8.setOnClickListener (this)
        binding.button9.setOnClickListener (this)
        binding.buttonDecimal.setOnClickListener (this)
        binding.buttonRestar.setOnClickListener (this)
        binding.buttonMultiplicar.setOnClickListener (this)
        binding.buttonDivision.setOnClickListener (this)
        binding.buttonSumar.setOnClickListener (this)
        binding.buttonIgual.setOnClickListener (this)
        binding.buttonC.setOnClickListener(this)

    }

    /**Sobreescribiendo el metodo "onClick" le damos el valor de la accion que recibirá cada boton al
     * ser pulsado. en este caso, cada boton va asociado a que muestre y/o añada una cifra o símbolo
     * determinado en la pantalla para crear la sentencia de la operación.
     */

    override fun onClick(v: View?) {


        when (v?.id) {
            binding.button0.id -> binding.pantalla.append("0")
            binding.button1.id -> binding.pantalla.append("1")
            binding.button2.id -> binding.pantalla.append("2")
            binding.button3.id -> binding.pantalla.append("3")
            binding.button4.id -> binding.pantalla.append("4")
            binding.button5.id -> binding.pantalla.append("5")
            binding.button6.id -> binding.pantalla.append("6")
            binding.button7.id -> binding.pantalla.append("7")
            binding.button8.id -> binding.pantalla.append("8")
            binding.button9.id -> binding.pantalla.append("9")
            binding.buttonDecimal.id -> binding.pantalla.append(".")
            binding.buttonRestar.id -> binding.pantalla.append("-")
            binding.buttonMultiplicar.id -> binding.pantalla.append("x")
            binding.buttonDivision.id -> binding.pantalla.append("/")
            binding.buttonSumar.id -> binding.pantalla.append("+")
            binding.buttonIgual.id-> igual()
            binding.buttonC.id->borrar()
        }

    }

    /**En la funcion "borrar" lo unico que hacemos es que sustituya el String de la pantalla por un
     * espacio vacío*/

    fun borrar() {
        binding.pantalla.text = ""
    }


    fun igual() {

        /** Creamos una variable donde recogeremos la operacion escrita en la pantalla*/

        val operacion = binding.pantalla.text.toString()
        var num1:Double
        var num2:Double

        /** Comprobamos que clase de operación contiene mediante un condicional
        averiguandolo a traves del .contains()**/

        when {
            /**En cada caso:
             *
             * 1º Creamos una variable "partes" donde separaremos ambos numeros de la operación con
             *      un .split() que recogerá las cifras situadas a ambos lados del signo.
             * 2º Convertimos cada una de las partes en double y las guardamos en una variable
             *      "val num1= partes[0].toDouble()
             * 3º Creamos la variable "resultado" donde realizaremos la operacion correspondiente
             * 4º Mandamos el resultado como un .toString() a la pantalla
             */

            operacion.contains("+") -> {
                val partes = operacion.split("+")
                 num1 = partes[0].toDouble()
                 num2 = partes[1].toDouble()
                val resultado = num1 + num2
                binding.pantalla.text = resultado.toString()
            }

            operacion.contains("-") -> {
                val partes = operacion.split("-")
                num1 = partes[0].toDouble()
                num2 = partes[1].toDouble()
                val resultado = num1 - num2
                binding.pantalla.text = resultado.toString()
            }

            operacion.contains("x") -> {
                val partes = operacion.split("x")
                num1 = partes[0].toDouble()
                num2 = partes[1].toDouble()
                val resultado = num1 * num2
                binding.pantalla.text = resultado.toString()
            }

            operacion.contains("/") -> {
                val partes = operacion.split("/")
                num1 = partes[0].toDouble()
                num2 = partes[1].toDouble()
                if (num2 != 0.0) {
                    val resultado = num1 / num2
                    binding.pantalla.text = resultado.toString()
                } else {
                    binding.pantalla.text = "Error"
                }
            }

            else -> {
                // Si no hay operador, muestra un mensaje de error
                binding.pantalla.text = "Error"
            }
        }
    }


}

