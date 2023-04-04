fun main() {
    println("Hello World!")
    val str = "1\n2"
    val thing = str.replace("\n", ",").split(",").sumOf { it.toInt() }
    println(thing)
}
