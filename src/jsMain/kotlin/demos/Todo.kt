/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package demos

import androidx.compose.*
import demos.tictactoe.GameState
import html.Checkbox
import html.Div
import html.Input
import html.Text

@Composable
fun Todo() {
    var todos by state { listOf<TodoItem>(
        TodoItem("Run Jetpack Compose in a browser", done = true),
        TodoItem("Create a simple todo", done = true),
        TodoItem("Win at Hackathon!"),
    ) }
    val current = state { "" }

    Div (className = "todo-input") {
        Input(
            current,
            placeholder = "What needs to be done?",
            onKeypress = {
                if (it.keyCode == 13) {
                    todos += TodoItem(current.value)
                    current.value = ""
                }
            }
        )
    }

    Div(className = "todo-list") {
        for (t in todos) {
            Div {
                Checkbox(t.text, t.done)
            }
        }
    }
    Div {
        Text("All: ${todos.size}")
    }
}

@Model
class TodoItem(val text: String, val done: Boolean = false)