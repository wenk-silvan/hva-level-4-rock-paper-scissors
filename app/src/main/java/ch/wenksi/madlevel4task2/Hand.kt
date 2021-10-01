package ch.wenksi.madlevel4task2

enum class Hand(val imageResourceId: Int, val text: String) {
    Rock(R.drawable.rock, "Rock"),
    Paper(R.drawable.paper, "Paper"),
    Scissors(R.drawable.scissors, "Scissors")
}