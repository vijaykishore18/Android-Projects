package com.example.rockpaperscissor

import androidx.lifecycle.ViewModel

class RockPaperScissorViewModel : ViewModel() {
    var user1name: String = ""
    var user2name: String = ""
    var user1move: String = ""
    var user2move: String = ""

    fun GamePlay(): String {
        return if (user1move == user2move) {
            "It's a draw!"
        } else if (
            (user1move == "Rock" && user2move == "Scissors") ||
            (user1move == "Paper" && user2move == "Rock") ||
            (user1move == "Scissors" && user2move == "Paper")
        ) {
            "${user1name} wins!"
        } else {
            "${user2name} wins!"
        }
    }
}