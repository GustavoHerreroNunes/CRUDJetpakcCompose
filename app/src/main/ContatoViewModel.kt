package br.com.fiap.aplicacaovazia

import androidx.lifecycle.ViewModel
import br.com.fiap.aplicacaovazia.Contato
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType
import gildor.coroutines.okhttp.await

class ContatoViewModel : ViewModel(){
    
    //Tracks any changes in the list of contacts
    var contactsList : ArrayList<Contato> by mutableStateListOf(arrayListOf())
    private val clientHttp = OkHttpClient.Builder().build()

    fun saveContact(contact : Contato){
        viewModelScope.launch(Dispatchers.IO) {
            saveContactWeb(contact)
            //Future update -> Update the list of contacts
        }
    }

    //Run in the background
    private suspend fun saveContactWeb( contact : Contato ){
        val jsonContact = """
            {
                "nome": "${contact.nome}",
                "telefone": "${contact.telefone}",
                "email": "${contact.email}"
            }
        """.trimIndent()

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            jsonContact
        )

        val request = Request.Builder()
            .url("https://api.com.br/contatos")
            .post(body)
            .build()

        clientHttp.newCall(request).await()
    }

}
