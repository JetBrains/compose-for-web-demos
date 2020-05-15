/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import demos.Inputs
import demos.Todo
import demos.ripple.RippleDemo
import demos.tictactoe.Counter
import demos.tictactoe.EmojiWar
import demos.tictactoe.TicTacToeView
import html.*
import kotlin.browser.document

fun main() {
    onLoad {
        document.body?.setContent {
            Demos()
        }
    }
}

class Demo(val title: String, val content: @Composable () -> Unit)

val mainMenu = Demo("") {}
val allDemos: List<Demo> = listOf(
    Demo("Simple counter") { Counter() },
    Demo("Ripple") { RippleDemo() },
    Demo("Tic-tac-toe") { TicTacToeView() },
    Demo("Emoji War") { EmojiWar() },
    Demo("Todo") { Todo() },
    Demo("Inputs") { Inputs() },
)

@Composable
fun Demos() {
    var currentDemo: Demo by state { mainMenu }

    if (currentDemo == mainMenu) {
        Html("h1") { Text("Compose for Web Demos:") }

        for (demo in allDemos) {
            Div {
                Button(onClick = { currentDemo = demo }, className = "nav") {
                    Text(demo.title)
                }
            }
        }
    } else {
        Div {
            Button(onClick = { currentDemo = mainMenu }, className = "nav") {
                Text("⬅ Back")
            }
        }
        Html("h1") { Text("Demo: ${currentDemo.title}") }

        Div {
            currentDemo.content()
        }
    }
}