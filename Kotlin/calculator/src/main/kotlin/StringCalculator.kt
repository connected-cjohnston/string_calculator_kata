class StringCalculator {

    fun add(str: String): Int {
        if (str == "") return 0

        return validInts(str).sumOf { it.toInt() }
    }

    private fun validInts(str: String): List<Int> {
        val ints: List<Int> = Delimiter.create(str).split().map { it.toInt() }
        ensurePositiveIntegers(ints)
        return excludeLargeInts(ints)
    }

    private fun ensurePositiveIntegers(ints: List<Int>) {
        val negatives: List<Int> = ints.filter { it < 0 }
        if (negatives.isNotEmpty()) {
            throw Exception(
                "negatives not allowed: ${negatives.joinToString(",")}"
            )
        }
    }

    private fun excludeLargeInts(ints: List<Int>) = ints.filter { it <= 1000 }
}

open class Delimiter(private val str: String) {
    open fun split() = str.split(",", "\n")

    companion object {
        fun create(str: String) = if (str.contains("//")) {
            CustomDelimiter(str)
        } else {
            Delimiter(str)
        }
    }
}

class CustomDelimiter(private val str: String) : Delimiter(str) {
    override fun split(): List<String> {
        val parts = str.replace("//", "").split("\n")
        return parts[1].split(parts[0])
    }
}
