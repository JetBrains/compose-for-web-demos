[![JetBrains team project](https://jb.gg/badges/team-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

**Disclaimer**: This is the result of a project for the [8th Annual JetBrains Hackathon](https://blog.jetbrains.com/blog/2020/07/17/jetbrains-8th-annual-hackathon-home-edition/). It's only a proof of concept, and the JetBrains team doesn't have any plans to support or develop this project and doesn't recommend using it in production.
It is not related to Google, except insofar as it is based on [Jetpack Compose](https://developer.android.com/jetpack/compose). As far as we know, Google does not have any plans to support or develop it.

## What is Jetpack Compose
[Jetpack Compose](https://developer.android.com/jetpack/compose) is Android’s modern toolkit for building native UIs using the [Kotlin language](https://kotlinlang.org/). All components are rewritten from scratch and painted directly on the canvas. It was announced and open-sourced at [Google I/O 2019](https://events.google.com/io2019/). This talk on [Declarative UI Patterns](https://www.youtube.com/watch?v=VsStyq4Lzxo) presents an overview of the motivations and goals behind the project.

**JetPack Compose has 2 separate parts:**
- The Kotlin compiler plugin and runtime, which provide a language feature to perform incremental computations on trees using Positional Memoization. This makes it possible to describe the UI in a declarative way as a transform function on the state. It is actually able to maintain not only the Android UI components tree but also any other tree-like structure, such as HTML DOM and SVG.
- The UI framework, which implements layout, text shaping, painting, rasterization, compositing, and a set of completely rewritten Android components.

It currently doesn't support platforms other than Android, but the core was developed without a dependency on Android and the UI framework depends only on things like canvas, font, input, and other basics.

## Jetpack Compose for Web
With this project we tried to adapt Jetpack Compose for the JavaScript world. The main idea is to allow you to share code and expertise between platforms, build web applications in pure Kotlin.

**Jetpack Compose for Web consists of:**

* The compiler plugin, adapted for the [new Kotlin/JS compiler backend](https://kotlinlang.org/docs/reference/js-ir-compiler.html).
* The runtime library, ported to Kotlin/JS.
* A simple UI library to work with DOM-elements.

Feel free to ask any questions and share your feedback. Join [#compose-web](https://kotlinlang.slack.com/archives/C01F2HV7868) channel in the official [Kotlin Slack](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up).

P.S. JetBrains plans to continue experimenting with this technology and we are looking for [UI Framework Developer (JetBrains Compose Team)](https://www.jetbrains.com/careers/jobs/ui-framework-developer-400/).


## Online demo

https://zal.im/h8/

## Running demos
`./gradlew jsRun`
