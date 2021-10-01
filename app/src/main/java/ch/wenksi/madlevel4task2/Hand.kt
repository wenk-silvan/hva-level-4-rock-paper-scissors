package ch.wenksi.madlevel4task2

enum class Hand(val imageResourceId: Int, val text: String) {
    Rock(R.drawable.rock, "Rock"),
    Paper(R.drawable.paper, "Paper"),
    Scissors(R.drawable.scissors, "Scissors")
}

class HandHelper {
    companion object {
        fun getFromString(value: String): Hand {
            return when(value) {
                "Rock" -> Hand.Rock
                "Paper" -> Hand.Paper
                "Scissors" -> Hand.Scissors
                else -> Hand.Rock // TODO: Improve with snackbar
            }
        }
    }
}