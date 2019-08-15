package com.rafaelsouza.moviesinvolves.repository

enum class Ambiente {

    PRODUCAO, DESENVOLVIMENTO, HOMOLOGACAO;

    fun getUrl(): String {
        return when(this) {
            PRODUCAO -> ""
            DESENVOLVIMENTO -> "https://api.themoviedb.org/3/"
            HOMOLOGACAO -> ""
        }
    }
}