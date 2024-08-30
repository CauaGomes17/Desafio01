import java.util.Scanner
import java.util.InputMismatchException

fun main() {
    val livros: MutableList<String> = mutableListOf()
    val generos: MutableList<String> = mutableListOf()
    val autores: MutableList<String> = mutableListOf()
    val totaldepaginas: MutableList<Int> = mutableListOf()
    val paginaslidas: MutableList<Int> = mutableListOf()
    val tamanho = 20 // Ajusta a largura das colunas
    val scanner = Scanner(System.`in`)
    menuPrincipal(livros, generos, autores, totaldepaginas, paginaslidas,tamanho, scanner)
    scanner.close()
}

fun cadastrarlivros(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    scanner: Scanner
) {

    fun lerString(mensagem: String): String {
        println(mensagem)
        return scanner.nextLine()
    }


    fun lerInt(mensagem: String): Int {
        while (true) {
            println(mensagem)
            try {
                return scanner.nextInt().also { scanner.nextLine() } // Consumir nova linha após inteiro
            } catch (e: InputMismatchException) {
                println("Entrada inválida. Por favor, insira um número inteiro.")
                scanner.nextLine() // Limpar buffer do scanner
            }
        }
    }

    // ENTRADA DE DADOS
    val nome = lerString("Insira o nome do livro: ")
    if(verificacao(nome,livro)){
        println("Esse livro já foi cadastrado!")
        return
    }

    val gen = lerString("Insira o gênero do livro: ")
    val aut = lerString("Insira o autor do livro: ")
    val totalpag = lerInt("Insira o total de páginas do livro: ")
    var paglidas = lerInt("Insira o número de páginas lidas do livro: ")
    if (paglidas > totalpag){
        println("O número de páginas lidas excede o total de páginas! por favor, insira um número válido")
        paglidas = lerInt("Insira o número de páginas lidas do livro: ")
    }

    // ADICIONANDO AS INFORMAÇÕES
    livro.add(nome)
    genero.add(gen)
    autor.add(aut)
    tp.add(totalpag)
    pl.add(paglidas)

    println("Cadastro concluído com sucesso!!")
    println()
}


fun listar(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    tam: Int,
    pl: MutableList<Int>

) {
    val a = "NOME"
    val b = "GÊNERO"
    val c = "AUTOR"
    val d = "TOTAL DE PÁGINAS"
    val e = "PÁGINAS LIDAS"
    val f = "PERCENTUAL DE PÁGINAS LIDAS"
    println("---------------------------------------------- LISTA DE LIVROS ---------------------------------------------------------------------------------------")
    println("${a.padStart(tam)} | ${b.padStart(tam)} | ${c.padStart(tam)} | ${d.padStart(tam)} | ${e.padStart(tam)} \t|\t ${f.padStart(tam)}")

    for (i in livro.indices) {
        val percentual = pl[i].toFloat() / tp[i] * 100
        val progresso = String.format("%.2f", percentual)
        val totalpag = tp[i].toString()
        val readpag = pl[i].toString()
        println("${livro[i].padStart(tam)} | ${genero[i].padStart(tam)} | ${autor[i].padStart(tam)} | ${totalpag.padStart(tam)} | ${readpag.padStart(tam)} \t|\t ${progresso.padStart(tam)}%")
    }
    println("-----------------------------------------------------------------------------------------------------------------------------------------------------")
    if (livro.isEmpty()) {
        println("Ainda não há livros cadastrados!")
    }
    println()
}

fun menuPrincipal(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    tam: Int,
    scanner: Scanner
) {
    while (true) {
        try {
            println("INSIRA A OPÇÃO DESEJADA:")
            println("1. CADASTRAR LIVRO")
            println("2. VER LISTA DE LIVROS CADASTRADOS")
            println("3. CONSULTA POR NOME")
            println("4. CONSULTA POR GÊNERO")
            println("5. CONSULTA POR AUTOR")
            println("6. REMOVER LIVRO")
            println("7. SAIR")

            val opcao = if (scanner.hasNextInt()) {
                scanner.nextInt().also { scanner.nextLine() }

            } else {
                println("Entrada inválida. Por favor, insira um número inteiro válido.")
                scanner.next() // Limpa a entrada inválida
                continue
            }

            when (opcao) {
                1 -> cadastrarlivros(livro, genero, autor, tp, pl,scanner)
                2 -> listar(livro, genero, autor, tp,tam, pl)
                3 -> consultapornome(livro, genero, autor, tp, pl, tam,scanner)
                4 -> consultaporgenero(livro, genero, autor, tp, pl,tam, scanner)
                5 -> consultaporautor(livro, genero, autor, tp, pl, tam, scanner)
                6 -> removerlivro(livro, genero, autor, tp, pl, scanner)
                7 -> {
                    println("Saindo...")
                    return

                }
                else -> println("Entrada inválida. Por favor, insira um número inteiro válido.")

            }
        } catch (e: InputMismatchException) {
            scanner.next() // Limpa o buffer do scanner
        }
    }
}

fun consultapornome(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    tam: Int,
    scanner: Scanner,
    ) {
    if(estavazio(livro)){
        return
    }
    println("DIGITE O NOME DO LIVRO QUE VOCÊ PROCURA:")
    val nomedolivro = scanner.nextLine()
    if (verificacao(nomedolivro,livro)){
        val a = "NOME"
        val b = "GÊNERO"
        val c = "AUTOR"
        val d = "TOTAL DE PÁGINAS"
        val e = "PÁGINAS LIDAS"
        val f = "PERCENTUAL DE PÁGINAS LIDAS"

        for (i in livro.indices) {
            if (nomedolivro == livro[i]) {
                println("---------------------------------------------- LIVRO ENCONTRADO ---------------------------------------------------------------------------------------")
                println("${a.padStart(tam)} | ${b.padStart(tam)} | ${c.padStart(tam)} | ${d.padStart(tam)} | ${e.padStart(tam)} \t|\t ${f.padStart(tam)}")
                val percentual = pl[i].toFloat() / tp[i] * 100
                val progresso = String.format("%.2f", percentual)
                val totalpag = tp[i].toString()
                val readpag = pl[i].toString()
                println("${livro[i].padStart(tam)} | ${genero[i].padStart(tam)} | ${autor[i].padStart(tam)} | ${totalpag.padStart(tam)} | ${readpag.padStart(tam)} \t|\t ${progresso.padStart(tam)}%")
                println("-----------------------------------------------------------------------------------------------------------------------------------------------------")

            }
        }
    }
    else{
        println("Livro não cadastrado! ")
    }

    println()

}

fun consultaporgenero(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    tam: Int,
    scanner: Scanner
) {
    if(estavazio(livro)){
        return
    }
    println("DIGITE O GÊNERO DO LIVRO QUE VOCÊ PROCURA:")
    val generodolivro = scanner.nextLine()
    var encontrados = 0
    val a = "NOME"
    val b = "GÊNERO"
    val c = "AUTOR"
    val d = "TOTAL DE PÁGINAS"
    val e = "PÁGINAS LIDAS"
    val f = "PERCENTUAL DE PÁGINAS LIDAS"
    println("---------------------------------------------- LIVROS ENCONTRADOS ---------------------------------------------------------------------------------------")
    println("${a.padStart(tam)} | ${b.padStart(tam)} | ${c.padStart(tam)} | ${d.padStart(tam)} | ${e.padStart(tam)} \t|\t ${f.padStart(tam)}")
    for (i in livro.indices) {
        if (generodolivro == genero[i]) {
            val percentual = pl[i].toFloat() / tp[i] * 100
            val progresso = String.format("%.2f", percentual)
            val totalpag = tp[i].toString()
            val readpag = pl[i].toString()
            println("${livro[i].padStart(tam)} | ${genero[i].padStart(tam)} | ${autor[i].padStart(tam)} | ${totalpag.padStart(tam)} | ${readpag.padStart(tam)} \t|\t ${progresso.padStart(tam)}%")
            encontrados += 1
        }
    }
    println("-----------------------------------------------------------------------------------------------------------------------------------------------------")
    if (encontrados == 0)
        println("Não há livros pertencentes ao gênero escolhido!")
    else
        println("$encontrados livros encontrados!")
    println()
}

fun consultaporautor(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    tam: Int,
    scanner: Scanner
) {
    if(estavazio(livro)){
        return
    }
    println("DIGITE O AUTOR DO LIVRO QUE VOCÊ PROCURA:")
    val autordolivro = scanner.nextLine()
    var encontrados = 0

    val a = "NOME"
    val b = "GÊNERO"
    val c = "AUTOR"
    val d = "TOTAL DE PÁGINAS"
    val e = "PÁGINAS LIDAS"
    val f = "PERCENTUAL DE PÁGINAS LIDAS"
    println("---------------------------------------------- LIVROS ENCONTRADOS ---------------------------------------------------------------------------------------")
    println("${a.padStart(tam)} | ${b.padStart(tam)} | ${c.padStart(tam)} | ${d.padStart(tam)} | ${e.padStart(tam)} \t|\t ${f.padStart(tam)}")
    for (i in livro.indices) {
        if (autordolivro == autor[i]) {
            val percentual = pl[i].toFloat() / tp[i] * 100
            val progresso = String.format("%.2f", percentual)
            val totalpag = tp[i].toString()
            val readpag = pl[i].toString()
            println("${livro[i].padStart(tam)} | ${genero[i].padStart(tam)} | ${autor[i].padStart(tam)} | ${totalpag.padStart(tam)} | ${readpag.padStart(tam)} \t|\t ${progresso.padStart(tam)}%")
            encontrados += 1
        }
    }
    println("-----------------------------------------------------------------------------------------------------------------------------------------------------")
    if (encontrados == 0)
        println("Não há livros pertencentes ao autor escolhido!")
    else
        println("$encontrados livros encontrados!")
    println()
}
fun verificacao(bookname:String,livro: MutableList<String>):Boolean{
   for (i in livro.indices){
       if (bookname == livro[i]){
           return true
       }
   }
    return false
}
fun removerlivro(
    livro: MutableList<String>,
    genero: MutableList<String>,
    autor: MutableList<String>,
    tp: MutableList<Int>,
    pl: MutableList<Int>,
    scanner: Scanner
) {
    if(estavazio(livro)){
        return
    }
    println("Insira o nome do livro que você deseja remover:")
    val nomeDoLivro = scanner.nextLine()

    // Verifica se o livro existe
    if (livro.contains(nomeDoLivro)) {
        // Encontra o índice do livro a ser removido
        val indexToRemove = livro.indexOf(nomeDoLivro)

        // Remove todos os elementos relacionados ao livro encontrado
        livro.removeAt(indexToRemove)
        genero.removeAt(indexToRemove)
        autor.removeAt(indexToRemove)
        tp.removeAt(indexToRemove)
        pl.removeAt(indexToRemove)
        println("Livro removido com sucesso!")
    }
    else {
        println("Livro não encontrado.")
    }
    println()
}
fun estavazio(livro: MutableList<String>):Boolean{
    if (livro.isEmpty()) {
        println("Não há livros cadastrados!")
        return true
    }
    return false
}