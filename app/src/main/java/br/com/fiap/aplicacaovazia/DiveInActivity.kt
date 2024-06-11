package br.com.fiap.aplicacaovazia

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import android.util.Log
import android.widget.Toast
import br.com.fiap.aplicacaovazia.databinding.ActivityDiveInBinding
import br.com.fiap.aplicacaovazia.RegisterActivity
import br.com.fiap.aplicacaovazia.LoginActivity

class DiveInActivity : ComponentActivity() {

    private lateinit var binding : ActivityDiveInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityDiveInBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            Log.d("DiveInActivity", "btnRegister clicked")

            Log.d("DiveInActivity", "Moving to RegisterActivity")
            val formData = getFormData()

            Toast.makeText(this, "UserData: ${formData.joinToString()}", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            Log.d("DiveInActivity", "btnLogin clicked")

            Log.d("DiveInActivity", "Moving to LoginActivity")
            val formData = getFormData()

            Toast.makeText(this, "UserData: ${formData.joinToString()}", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun getFormData(): Array<String> {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        return arrayOf(name, email, password)
    }
}
