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
fun Inputs() {
    val s = state { "aaas" }
    val t = state { "" }

    Div {
        Input(
            t,
            placeholder = "Hi! Type something!"
        )
    }
    Div {
        Input(
            s,
            placeholder = "Hi! Type something!"
        )
    }
    Div {
        Input(
            s,
            placeholder = "Hi! Type something!"
        )
    }
    Div {
        Text("value = ${s.value}")
    }
    val ch = state { true }
    Div {
        Checkbox("check me!", ch)
    }
    Div {
        Checkbox("check me!", ch)
    }
    Div {
        Text("checked = ${ch.value}")
    }
}
