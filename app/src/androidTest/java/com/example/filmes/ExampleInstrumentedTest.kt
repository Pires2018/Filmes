package com.example.filmes

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.Calendar


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.filmes", appContext.packageName)
    }

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados() {
        //getAppContext().deleteDatabase(BdLivrosOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BdFilmesOpenHelper(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)
    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdFilmesOpenHelper(getAppContext())
        return openHelper.writableDatabase
    }

    @Test
    fun consegueInserirDescricao() {
        val bd = getWritableDatabase()

        val descricaoFilmes = consegueInserirDescricao("Drama")
        insereDescricao(bd, Descricao_Filmes)
    }

    private fun insereDescricao(
        bd: SQLiteDatabase,
        descricao_Filmes: Descricao_Filmes: Descricao
    ) {
        descricao.id = TabelaDescricao(bd).insere(descricao.toContentValues())
        assertNotEquals(-1, descricao.id)
    }

    @Test
    fun consegueInserirFilmes() {
        val bd = getWritableDatabase()

        val descricao = Descricao("Acao")
        insereDescricao(bd, descricao)

        val filme1 = Filme("Velocidade Furiosa X", descricao)
        insereFilme(bd, filme1)

        val filme2 = Filme("Avatar", descricao, "Ficcao")
        insereFilme(bd, filme2)
    }

    private fun insereFilme(bd: SQLiteDatabase, filme: Filme) {
        filme.id = TabelaFilmes(bd).insere(filme.toContentValues())
        assertNotEquals(-1, filme.id)
    }

    @Test
    fun consegueLerDescricao() {
        val bd = getWritableDatabase()

        val descricaoAcao = Descricao("Acao")
        insereDescricao(bd, descricaoAcao)

        val descricaoFiccao = Descricao(Ficção Científica")
        insereDescricao(bd, descricao(Ficcao)

        val tabelaDescricao = TabelaDescricao(bd)

        val cursor = tabelaDescricao.consulta(
            TabelaDescricao.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(descricaoFiccao.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val descricaoBD = Descricao.fromCursor(cursor)

        assertEquals(descricaoFiccao, descricaoBD)

        val cursorTodasDescricoes = tabelaDescricao.consulta(
            TabelaDescricao.CAMPOS,
            null, null, null, null,
            TabelaDescricao.CAMPO_DESCRICAO
        )

        assert(cursorTodasDescricoes.count > 1)
    }

    @Test
    fun consegueLerFilmes() {
        val bd = getWritableDatabase()

        val descricaoAcao = Descricao("Acao")
        insereDescricao(bd, descricaoAcao)

        val filme1 = Filme("Velocidade Furiosa X", descricao)
        insereFilme(bd, filme1)

        val dataPub = Calendar.getInstance()
        dataPub.set(2023, 5, 14)

        val filme2 = Filme("Avatar", descricao, "Ficcao")
        insereFilme(bd, filme2)

        val dataPub = Calendar.getInstance()
        dataPub.set(2022, 2, 18)

        val descricaoFiccao = Descricao(Ficção Científica")
                insereDescricao(bd, descricao(Ficcao)

            val tabelaDescricao = TabelaDescricao(bd)

        val cursor = tabelaDescricao.consulta(
            TabelaDescricao.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(descricaoFiccao.id.toString()),
            null,
            null,
            null

        val tabelaFilmes = TabelaFilmes(bd)

        val cursor = tabelaFilmes.consulta(
            TabelaFilmes.CAMPOS,
            "${TabelaFilmes.CAMPO_ID}=?",
            arrayOf(filme1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val filmeBD = Filmes.fromCursor(cursor)

        assertEquals(filme1, filmeBD)

        val cursorTodosFilmes = tabelaFilmes.consulta(
            TabelaFilmes.CAMPOS,
            null, null, null, null,
            TabelaFilmes.CAMPO_NOME
        )

        assert(cursorTodosFilmes.count > 1)
    }

    @Test
    fun consegueAlterarDescricao() {
        val bd = getWritableDatabase()

        val descricao_Filmes = Descricao_Filmes("...")
        insereDescricao_Filmes(bd, descricao_Filmes)

        descricao_Filmes.descricao = "Acao"

        val registosAlterados = TabelaDescricao(bd).altera(
            descricao_Filmes.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(descricao_Filmes.id.toString())
        )

        assertEquals(1, registosAlterados)
    }

    @Test
    fun consegueAlterarFilmes() {
        val bd = getWritableDatabase()

        val descricaoAcao = Descricao("Acao")
        insereDescricao(bd, descricaoAcao)

        val descricaoFiccao = Descricao("Ficcao")
        insereDescricao(bd, descricaoFicca)

        val filme = Filme("...", descricaoFiccao)
        insereFilme(bd, filme)

        val novaDataPub = Calendar.getInstance()
        novaDataPub.set(2023, 1, 1)

        filme.descricao = descricaoFiccao
        filme.nome = "Avatar"
        filme.dataPublicacao = novaDataPub

        val registosAlterados = TabelaFimes(bd).altera(
            filme.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(filme.id.toString())
        )

        assertEquals(1, registosAlterados)
    }

    @Test
    fun consegueApagarDescricao() {
        val bd = getWritableDatabase()

        val descricao = Descricao("...")
        insereDescricao(bd, descricao)

        val registosEliminados = TabelaDescricao(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(descricao.id.toString())
        )

        assertEquals(1, registosEliminados)
    }

    @Test
    fun consegueApagarFilmes() {
        val bd = getWritableDatabase()

        val descricao = Descricao("Ficcao")
        insereDescricao(bd, descricao)

        val filmes = Filmes("...", descricao)
        insereFilmes(bd, filmes)

        val registosEliminados = TabelaFilmes(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(filmes.id.toString())
        )

        assertEquals(1, registosEliminados)
    }
}



