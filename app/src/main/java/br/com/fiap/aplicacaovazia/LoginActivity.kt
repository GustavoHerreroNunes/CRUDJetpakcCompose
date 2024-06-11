package br.com.fiap.aplicacaovazia

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import br.com.fiap.aplicacaovazia.databinding.ActivityLoginBinding
import br.com.fiap.aplicacaovazia.MainActivity

class LoginActivity : ComponentActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogout.setOnClickListener{
            Log.d("LoginActivity", "Starting Logout")
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}