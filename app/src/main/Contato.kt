package br.com.fiap.aplicacaovazia

data class Contato(
    var id: String? = null,
    val nome: String,
    val telefone: String,
    var email: String? = ""
)
