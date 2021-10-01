package ch.wenksi.madlevel4task2

enum class Result(val resultId: Int, val text: String) {
    Win(1, "You win :)"),
    Draw(2, "It's a draw."),
    Lose(3, "You lose :(")
}