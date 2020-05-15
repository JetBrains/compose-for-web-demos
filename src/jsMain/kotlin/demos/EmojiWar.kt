/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package demos.tictactoe

import androidx.compose.frames.ModelList
import html.Div
import html.Text
import ui.animate

val emojis = listOf("🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼", "🐨", "🐯", "🦁")

fun EmojiWar() {
    val emoji = ModelList<String>()
    repeat(70) { emoji.add(emojis.random()) }

    animate(
        length = 1000000.0,
        onEnd = { }
    ) {
        emoji[emoji.indices.random()] = emoji.random()

        // Battlefield
        Div { emoji.forEach { Text(it) } }

        Div { Text("Leaderboard:") }
        emoji
            .groupBy { it }
            .mapValues { it.value.count() }
            .toList()
            .sortedByDescending { it.second }
            .forEach {
                Div {
                    Text("${it.first} : ${it.second}")
                }
            }
    }
}


