class StringCalculator {
    fun add(str: String): Int {
        if (str == "") return 0

        var ints: List<Int>? = null

        ints = if (str.contains("//")) {
            val parts = str.replace("//", "").split("\n")
            parts[1].split(parts[0]).map { it.toInt() }
        } else {
            val intStrings = str.split(",", "\n")
            intStrings.map { it.toInt() }
        }

        val negatives = ints.filter { it < 0 }
        if (negatives.isNotEmpty()) {
            throw Exception(
                "negatives not allowed: ${negatives.joinToString(",")}"
            )
        }

        return ints.filter { it <= 1000 }.sumOf { it.toInt() }
    }
}