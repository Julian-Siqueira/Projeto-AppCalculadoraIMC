package devandroid.julian.projetocalculadoraimc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcurar: Button
    private lateinit var inputPeso: TextInputLayout
    private lateinit var editPeso: TextInputEditText
    private lateinit var inputAltura: TextInputLayout
    private lateinit var editAltura: TextInputEditText
    private lateinit var textPreencha: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentesMain()
        btnCalcurar.setOnClickListener{

            val peso = editPeso.text.toString()
            val altura = editAltura.text.toString()

            //Método para validar se os campos estão vazios
            val camposValidados = validarCampos(peso, altura)

            if (camposValidados){
                val intent = Intent(this, ResultadoActivity::class.java)

                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura", altura.toDouble())

                startActivity(intent)
            }else{
                textPreencha.visibility = View.VISIBLE
            }
        }
    }

    //Validação dos campos
    private fun validarCampos(valuePeso: String, valueAltura: String): Boolean {

        inputPeso.error = null
        inputAltura.error = null

        if (valuePeso.isEmpty()){
            //Log.i("Peso", "Peso vazio")
            inputPeso.error = "Insira um valor para peso"
            return false
        }else if (valueAltura.isEmpty()){
            //Log.i("Altura", "Altura vazia")
            inputAltura.error = "Insira um valor para altura"
            return false
        }
        return true
    }

    private fun iniciarComponentesMain() {
        btnCalcurar = findViewById(R.id.btn_calcular)
        inputPeso = findViewById(R.id.input_peso)
        editPeso = findViewById(R.id.edit_peso)
        inputAltura = findViewById(R.id.input_altura)
        editAltura = findViewById(R.id.edit_altura)
        textPreencha = findViewById(R.id.text_preencha)
    }

    override fun onStart() {
        textPreencha.visibility = View.INVISIBLE
        super.onStart()
    }

}