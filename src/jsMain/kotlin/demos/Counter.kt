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
import html.Text

@Composable
fun Counter() {
    var value: Int by state { 0 }

    Button(onClick = { value-- }) { Text("-") }
    Text("$value")
    Button(onClick = { value++ }) { Text("+") }
}