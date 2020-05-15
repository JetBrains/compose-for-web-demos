package ui

import androidx.compose.*
import androidx.compose.frames.ModelList
import html.Div
import html.LargeDiv
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

@Composable
fun Rippable() {
    val ripples = ModelList<RippleEffect>()

    fun addRipple(e: MouseEvent) {
        ripples += RippleEffect(e.offsetX, e.offsetY, onEnd = { ripples -= it })
    }

    LargeDiv(onClick = { addRipple(it as MouseEvent) }) {
        ripples.forEach {
            it.render()
        }
    }
}

class RippleEffect(
    val x: Double,
    val y: Double,
    val onEnd: (RippleEffect) -> Unit,
) {
    @Composable
    fun render() = animate(
        length = 1000.0,
        onEnd = { onEnd(this) }
    ) { t ->
        Ripple(x, y, t * 200, (1 - t).toFloat())
    }
}

val ripple = SourceLocation("Ripple")

@Composable
fun Ripple(x: Double, y: Double, radius: Double, a: Float) {
    val composer = (currentComposer as DomComposer)
    composer.emit(
        ripple,
        { composer.document.createElement("div") },
        {
            val node = node as HTMLElement

            node.style.position = "absolute"
            node.style.background = "rgba(0,0,0,$a)"
            node.style.transform = "transform: translate3d(0);"
            node.style.asDynamic().pointerEvents = "none"

            val rx = x - radius
            val ry = y - radius

            update(rx) { node.style.left = "${rx}px" }
            update(ry) { node.style.top = "${ry}px" }
            update(radius) {
                node.style.borderRadius = "${radius}px"
                node.style.width = "${radius * 2}px"
                node.style.height = "${radius * 2}px"
            }
        }
    )
}
