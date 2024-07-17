package devandroid.julian.projetocalculadoraimc

import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class ResultadoActivity : AppCompatActivity() {

    private lateinit var btnVoltar: Button
    private lateinit var infoPeso: TextView
    private lateinit var infoAltura: TextView
    private lateinit var resultDiagnostico: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentesResult()
        receberResultados()

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun receberResultados() {

        val bundle = intent.extras

        if (bundle!=null){
            val peso = bundle.getDouble("peso")
            val altura = bundle.getDouble("altura")

            infoPeso.text = "Peso Informado: $peso Kg"
            infoAltura.text = "Altura Informada: $altura m"

            calcularIMC(peso,altura)
        }



    }

    private fun calcularIMC(valuePeso: Double, valueAltura: Double) {
        val imc = valuePeso/(valueAltura*valueAltura)

        resultDiagnostico.text = if (imc < 18.5){
            "Baixo"
        } else if (imc in 18.5 .. 24.9){
            "Normal"
        } else if(imc in 25.0 .. 29.9){
            "Sobrepeso"
        }else{
            "Obesidade"
        }
    }

    private fun iniciarComponentesResult() {
        btnVoltar = findViewById(R.id.btn_voltar)
        infoPeso = findViewById(R.id.info_peso)
        infoAltura = findViewById(R.id.info_altura)
        resultDiagnostico = findViewById(R.id.result_diagnostico)
    }
}