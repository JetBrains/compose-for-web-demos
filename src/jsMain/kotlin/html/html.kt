package html

import androidx.compose.Composable
import androidx.compose.DomComposer
import androidx.compose.SourceLocation
import androidx.compose.currentComposer
import androidx.compose.*
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.events.Event
import org.w3c.dom.events.InputEvent
import org.w3c.dom.events.KeyboardEvent

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

val input = SourceLocation("input")

@Composable
fun Input(
    state: MutableState<String>,
    placeholder: String? = null,
    onChange: ((e: Event) -> Unit)? = null,
    onKeypress: ((e: KeyboardEvent) -> Unit)? = null
) {
    val composer = (currentComposer as DomComposer)
    composer.emit(
        input,
        {
            composer.document.createElement("input").also { element ->
                placeholder?.let { element.setAttribute("placeholder", placeholder) }

                element.asDynamic().value = state.value

                element.addEventListener("input", { e ->
                    state.value = (e as InputEvent).target.asDynamic().value ?: ""
                })

                onChange?.let { element.addEventListener("change", { e -> onChange(e) }) }

                onKeypress?.let { element.addEventListener("keypress", { e -> onKeypress(e as KeyboardEvent) }) }
            }
        },
        { update(state.value) { asDynamic().value = it } }
    )
}

@Composable
fun Input(value: String, onChange: ((e: Event) -> Unit)? = null) {
    Input(state { value }, onChange = onChange)
}

@Composable
fun Checkbox(
    label: String,
    state: MutableState<Boolean>,
    onChange: ((e: Event) -> Unit)? = null,
) {
    val composer = (currentComposer as DomComposer)
    composer.emit(
        input,
        {
            composer.document.createElement("input").also { element ->
                element.setAttribute("type", "checkbox")

                element.asDynamic().checked = state.value

                element.addEventListener("change", { e ->
                    state.value = e.target.asDynamic().checked
                    if (onChange != null) onChange(e)
                })
            }
        },
        { update(state.value) { asDynamic().checked = it } }
    )
    Text(label)
}

@Composable
fun Checkbox(
    label: String,
    value: Boolean,
    onChange: ((e: Event) -> Unit)? = null,
) {
    Checkbox(label, state { value }, onChange)
}