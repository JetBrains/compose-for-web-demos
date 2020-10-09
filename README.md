[![JetBrains team project](https://jb.gg/badges/team-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

**Disclaimer**: This is the result of a project for the [8th Annual JetBrains Hackathon](https://blog.jetbrains.com/blog/tag/hackathon/). It's only a proof of concept, and the JetBrains team doesn't have any plans to support or develop it and doesn't recommend using it in production.
It is not related to Google, except insofar as it is based on [Jetpack Compose](https://developer.android.com/jetpack/compose). As far as we know, Google does not have any plans to support or develop it.

## What is Jetpack Compose
[Jetpack Compose](https://developer.android.com/jetpack/compose) is Android’s modern toolkit for building native UIs using the [Kotlin language](https://kotlinlang.org/). All components are rewritten from scratch and painted directly on the canvas. It was announced and open-sourced at [Google I/O 2019](https://events.google.com/io2019/). This talk on [Declarative UI Patterns](https://www.youtube.com/watch?v=VsStyq4Lzxo) presents an overview of the motivations and goals behind the project.

**JetPack Compose has 2 separate parts:**
- The Kotlin compiler plugin and runtime, which provide a language feature to perform incremental computations on trees using Positional Memoization. This makes it possible to describe the UI in a declarative way as a transform function on the state. It is actually able to maintain not only the Android UI components tree but also any other tree-like structure, such as HTML DOM and SVG.
- The UI framework, which implements layout, text shaping, painting, rasterization, compositing, and a set of completely rewritten Android components.

It currently doesn't support platforms other than Android, but the core was developed without a dependency on Android and the UI framework depends only on things like canvas, font, input, and other basics.

## Jetpack Compose for Web
With this project we tried to adapt Jetpack Compose for the JavaScript world.

**The project consists of:**
- The compiler plugin, adapted for the new Kotlin/JS compiler backend.
- The runtime library, ported to the JS platform.
- A simple UI library to work with DOM-elements.

## Online demo

https://zal.im/h8/

## Running demos
`./gradlew jsRun`
