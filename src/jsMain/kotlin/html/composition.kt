package html

import androidx.compose.*
import org.w3c.dom.Node
import kotlin.browser.document
import kotlin.browser.window

fun Node.setContent(
    parent: CompositionReference? = null,
    body: @Composable () -> Unit
) {
    FrameManager.ensureStarted()
    val recomposer = JSRecomposer()
    val composition = compositionFor(document, recomposer, null) { st, r ->
        DomComposer(document, this, st, r)
    }
    composition.setContent(body)
}

fun onLoad(block: () -> Unit) {
    window.addEventListener("load", {
        block()
    })
}