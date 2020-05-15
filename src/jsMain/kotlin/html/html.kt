package html

import androidx.compose.Composable
import androidx.compose.DomComposer
import androidx.compose.SourceLocation
import androidx.compose.currentComposer
import org.w3c.dom.HTMLElement
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.events.Event

val button = SourceLocation("button")
val div = SourceLocation("div")
val largeDiv = SourceLocation("largeDiv")

@Composable
fun Div(
    className: String? = null,
    onClick: ((Event) -> Unit)? = null,
    block: @Composable () -> Unit
) {
    Html(
        "div",
        button,
        className = className,
        onClick = onClick,
        block = block
    )
}

@Composable
fun Button(
    className: String? = null,
    onClick: ((Event) -> Unit)? = null,
    block: @Composable () -> Unit
) {
    Html(
        "button",
        button,
        onClick = onClick,
        className = className,
        block = block,
    )
}

val element = SourceLocation("element")

@Composable
fun Html(
    tagName: String,
    sourceLocation: SourceLocation = element,
    className: String? = null,
    setStyle: (CSSStyleDeclaration.() -> Unit)? = null,
    onClick: ((Event) -> Unit)? = null,
    block: @Composable () -> Unit
) {
    val composer = (currentComposer as DomComposer)

    composer.emit(
        sourceLocation,
        {
            val element = composer.document.createElement(tagName) as HTMLElement
            if (onClick != null) {
                element.addEventListener("click", onClick)
            }
            if (setStyle != null) {
                element.style.setStyle()
            }
            element.className = className ?: ""
            element
        },
        {
            val element = node as HTMLElement
            update(className) { element.className = className ?: "" }
            update(setStyle) { if (setStyle != null) element.style.setStyle() }
        },
        { block() }
    )
}

@Composable
fun LargeDiv(
    onClick: ((Event) -> Unit)? = null,
    block: @Composable () -> Unit
) {
    Html(
        "div",
        largeDiv,
        setStyle = {
            position = "absolute"
            width = "500px"
            height = "500px"
            border ="1px solid black"
            asDynamic().overflow = "hidden"
        },
        onClick = onClick,
        block = block
    )
}

val text = SourceLocation("text")

@Composable
fun Text(value: String) {
    val composer = (currentComposer as DomComposer)
    composer.emit(
        text,
        { composer.document.createTextNode(value) },
        { update(value) { textContent = it } }
    )
}