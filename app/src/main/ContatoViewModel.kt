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
import com.google.gson.Gson
import android.util.Log
import com.google.gson.reflect.TypeToken

class ContatoViewModel : ViewModel(){
    
    //Tracks any changes in the list of contacts
    var contactsList : ArrayList<Contato> by mutableStateListOf(arrayListOf())
    private val clientHttp = OkHttpClient.Builder().build()
    private val gson = Gson()

    fun saveContact(contact : Contato){
        viewModelScope.launch(Dispatchers.IO) {
            saveContactWeb(contact)
            //Future update -> Update the list of contacts
            getContacts()
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
            .url(Constants.FIREBASE_URL)
            .post(body)
            .build()

        clientHttp.newCall(request).await()
    }

    fun getContacts(){
        viewModelScope.launch(Dispatchers.IO) {
            contactsList = getContactsWeb()
        }
    }

    private suspend fun getContactsWeb() : ArrayList<Contato>{
        val tempList = arrayListOf<Contato>()
        
        val request = Request.Builder()
            .url(Constants.FIREBASE_URL)
            .get()
            .build()

        val response = clientHttp.newCall(request).await()
        
        //Hashmap with the contacts
        val jsonResponse = response.body?.string()

        Log.d(Constants.CONTACT_VIEWMODEL_TAG, "Response: $jsonResponse")

        //Convert the JSON to a hashmap
        //Getting the object, not the class
        val type = object : TypeToken<HashMap<String?, Contato?>>(){
        }.type

        val contactMap : HashMap<String?, Contato> = gson.fromJson(jsonResponse, HashMap<String, Contato>)

        contactMap.forEach { (key, value) ->
            Log.d(Constants.CONTACT_VIEWMODEL_TAG, "Key: $key, Value: $value!!")
            tempList.add(value!!)
        }

        return tempList
    
    }

}
