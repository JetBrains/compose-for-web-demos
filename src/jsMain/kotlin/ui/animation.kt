package ui

import androidx.compose.*
import kotlin.browser.window

object Clock {
    val time: Double get() = window.performance.now()
    fun listen(callback: (Double) -> Unit): ClockListener = ClockListener(callback)
}

class ClockListener(val callback: (Double) -> Unit) /*: CompositionLifecycleObserver*/ {
    private var active = true
    private var scheduled = false

    init {
        schedule()
    }

//    override fun onEnter() = schedule()
//    override fun onLeave() = dispose()

    fun dispose() {
        active = false
    }

    fun schedule() {
        if (!scheduled) {
            scheduled = true
            window.requestAnimationFrame {
                scheduled = false
                callback(it)
                if (active) schedule()
            }
        }
    }
}

@Composable
fun animate(
    length: Double,
    onEnd: () -> Unit,
    frame: @Composable (t: Double) -> Unit
) {
    val t0 = remember { Clock.time }
    val t1 = t0 + length
    var t by state { t0 }
    val clock = remember { Clock.listen { t = it } }

    if (t <= t1) {
        frame((t - t0) / length)
    } else {
        clock.dispose()
        onEnd()
    }
}