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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.activity.viewModels

import br.com.fiap.aplicacaovazia.Contato
import br.com.fiap.aplicacaovazia.ContatoViewModel
import br.com.fiap.aplicacaovazia.Constants

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ContatoViewModel>()

    fun add(contact : Contato){
        viewModel.contactsList.add(contact)
        Log.d(Constants.MAIN_TAG, "Contact added: $contact")
        Log.d(Constants.MAIN_TAG, "Contacts.size: ${viewModel.contactsList.size}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacaoVaziaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Form(add)
                }
            }

        }
    }
}

@Composable
fun Form( viewModel: ContatoViewModel ){
    var name by remember { 
        mutableStateOf("Emmet Brown")
    }
    var phone by remember { 
        mutableStateOf("(11) 99999-9999")
    }
    var email by remember { 
        mutableStateOf("emmetbrown@email.com")
    }

    //Like React, we need to use a function to change the state
    fun setName(name: String){
        Log.d("Form", "Name: $name")
        name = name
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Text(
            text = "Hello, World!",
            fontSize = 24.sp,
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = name,
            onValueChange = { phone = it },
            label = { Text("Phone") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = name,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Button(
            onClick = {
                val contact = Contato(
                    nome = name,
                    telefone = phone,
                    email = email
                )
                viewModel.saveContact(contact)
                // viewModel.add(contact)
                
                Log.d("Form", "Button clicked")
                Toast.makeText(
                    LocalContext.current,
                    "Hello, $name",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text("Salvar")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    Form()
}
