package br.com.fiap.aplicacaovazia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.aplicacaovazia.ui.theme.AplicacaoVaziaTheme
import android.util.Log
import android.widget.Toast
import android.content.Intent
import br.com.fiap.aplicacaovazia.databinding.ActivityMainBinding
import br.com.fiap.aplicacaovazia.DiveInActivity

class MainActivity : ComponentActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        
        Log.i("AppTest", "I'm working")

        binding.btnDiveIn.setOnClickListener{
            Log.i("AppTest", "Button clicked. Diving in...")
            Toast.makeText(this, "Diving in...", Toast.LENGTH_SHORT).show()
            
            //Create an Intent to navigate to the DiveInActivity
            val intent = Intent(this, DiveInActivity::class.java)
            startActivity(intent)
        }
    }
}