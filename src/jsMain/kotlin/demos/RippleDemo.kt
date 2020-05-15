/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package demos.ripple

import androidx.compose.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import ui.Rippable
import ui.ripple
import kotlin.browser.document
import kotlin.browser.window

@Composable
fun RippleDemo() {
    Rippable()
}