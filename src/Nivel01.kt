import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    //ENTRADAS
    val nomeDoLivro = lerString("INSIRA O NOME DO LIVRO:",scanner)
    val totalDePaginas = lerInt("INSIRA O NÚMERO TOTAL DE PÁGINAS:",scanner)
    var paginasLidas = lerInt("INSIRA O NÚMERO DE PAGINAS LIDAS:",scanner)

    if (paginasLidas > totalDePaginas){
        println("O número de paginas lidas não pode exceder o número total de páginas do livro!!")
        paginasLidas = lerInt("INSIRA O NÚMERO DE PÁGINAS LIDAS:",scanner)
    }

    //SAÍDAS
    println()
    println("   INFORMAÇÕES DO LIVRO ")
    println("NOME DO LIVRO: $nomeDoLivro")
    println("PROGRESSÃO EM PORCENTAGEM: ${progressao(totalDePaginas,paginasLidas)}%")



}

fun lerString(mensagem: String,scanner: Scanner): String {
    println(mensagem)
    return scanner.nextLine()
}

fun lerInt(mensagem: String,scanner: Scanner): Int {
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
fun progressao(total:Int,lidas:Int):String{
    val progresso = lidas.toFloat()/total * 100
    return String.format("%.2f",progresso)

}