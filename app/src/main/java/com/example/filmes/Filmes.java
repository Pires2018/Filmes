package com.example.filmes;

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

import androidx.annotation.NonNull;

import java.util.Calendar

public class Filmes {
    var Nome_Filme: String,
    var Ator_Principal: String,
    var id.toString()
}

fun toContentValues() : ContentValues {
    val valores = ContentValues()
        valores.put(TabelaFilmes.CAMPO_Nome_Filme, nome_filme)
        valores.put(TabelaFilmes.CAMPO_Ator_Principal
        valores.put(TabelaFilmes.CAMPO_Ano_Lancamento?.timeInMillis)
        valores.put(TabelaFilmes.CAMPO_Realizador,idRealizador)

        return valores

        }





