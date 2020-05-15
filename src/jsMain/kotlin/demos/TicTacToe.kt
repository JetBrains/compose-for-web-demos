/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package demos.tictactoe

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import html.Button
import html.Div
import html.Text
import org.w3c.dom.events.Event

data class Player(val x: Boolean) {
    val next get() = Player(!x)

    override fun toString() = when {
        x -> "X"
        else -> "O"
    }
}

typealias BoardCells = Array<Player?>

class GameState(
    val player: Player,
    val board: BoardCells,
    val cell: Int?
) {
    fun copy(player: Player, cell: Int) = GameState(
        player,
        board.copyOf().also { it[cell] = player },
        cell
    )

    val winner = findWinner()

    private fun findWinner(): Player? {
        winPositions.forEach { cells ->
            val player = board[cells[0]]
            if (player != null &&
                board[cells[1]] == player &&
                board[cells[2]] == player
            ) {
                return player
            }
        }

        return null
    }

    val cellPos get() = cell?.let { "${it / 3 + 1} x ${it % 3 + 1}" }

    override fun toString(): String =
        "GameState(player=$player, board=${board.contentToString()}, winner=$winner)"

    companion object {
        val winPositions = arrayOf(
            arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8),
            arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8),
            arrayOf(0, 4, 8), arrayOf(2, 4, 6)
        )
    }
}

val initial = GameState(
    Player(true),
    arrayOfNulls<Player?>(9),
    null
)

@Composable
fun TicTacToeView() {
    var history by state { listOf<GameState>() }
    var nextStep by state { 0 }

    fun current() =
        history.getOrElse(nextStep - 1) { initial }

    fun doNextStep(cell: Int) {
        val current = current()

        if (current.winner != null) return
        if (current.board[cell] != null) return

        val next = current.copy(player = current.player.next, cell = cell)

        history = history.subList(0, nextStep) + next
        nextStep++
    }

    // Board
    with(current()) {
        Div(className = "game") {
            Div {
                Board(cells = board, onClick = { doNextStep(it) })
            }

            Div {
                when {
                    winner != null -> Text("Winner: $winner")
                    else -> Text("Next player: $player")
                }
            }
        }
    }

    // History
    Div {
        Button(onClick = { nextStep = 0 }) {
            Text("Go to game start")
        }
        history.forEachIndexed { index, it ->
            Div {
                Button(onClick = { nextStep = index + 1 }) {
                    Text("#$index: ${it.player} at ${it.cellPos}")
                }
            }
        }
    }
}

@Composable
fun Board(
    cells: BoardCells,
    onClick: (Int) -> Unit
) {
    Div {
        for (y in 0..2) {
            Div {
                for (x in 0..2) {
                    val i = y * 3 + x
                    Cell(cells[i], onClick = { onClick(i) })
                }
            }
        }
    }
}

@Composable
fun Cell(
    value: Player?,
    onClick: (Event) -> Unit
) {
    Button(className = "square", onClick = onClick) {
        if (value != null) {
            Text(value.toString())
        }
    }
}