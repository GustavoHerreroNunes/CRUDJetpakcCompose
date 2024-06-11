package br.com.fiap.aplicacaovazia

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import br.com.fiap.aplicacaovazia.databinding.ActivityRegisterBinding
import br.com.fiap.aplicacaovazia.DiveInActivity
import br.com.fiap.aplicacaovazia.MainActivity

class RegisterActivity : ComponentActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            Log.d("LoginActivity", "Comming back to Login")
            Toast.makeText(this, "Coming back to login", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DiveInActivity::class.java)
            startActivity(intent)
        }
    }
}