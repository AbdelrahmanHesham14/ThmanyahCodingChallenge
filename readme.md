# Coding Challenge

<img src="https://miro.medium.com/max/4800/1*D1EvAeK74Gry46JMZM4oOQ.png" width="500">

### Flow
This app uses MVI architecture.

<img src="https://miro.medium.com/max/1400/1*3u5JnmqONR4UnwRE6tEV3Q.png" width="500">

### Modules
Modules are the collection of source files and build settings that allow you to divide your project into discrete units of functionality.

- **App Module**

  `:app` module is an [com.android.application](https://developer.android.com/studio/projects/android-library), which is needed to create the app bundle. It contains dependency graph and UI related classes. It presents data to screen and handle user interactions.

- **Base Module**

  `:base` module contains only framework related base classes that is used in other modules

- **Common Module**

  `:common` module contains code and resources which are shared between other modules

- **Data Module**

  `:data` module contains implementation of repository and local - remote data source interface adapt

- **Domain Module**

  `:domain` module contains use cases and repository interface adapt

- **Local Module**

  `:local` module contains local data source related classes not implemented yet but we can use it if we want to scale the app to save data into persistence database like [Room](https://developer.android.com/training/data-storage/room) for offline mode

- **Remote Module**

  `:remote` module contains remote data source related classes

- **Presentation Module**

  `:presentation` module contains business logic

Each module has its own test.

### Tech Stack
- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack)
  * [Compose](https://developer.android.com/jetpack/compose)
  * [Android KTX](https://developer.android.com/kotlin/ktx)
  * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Coroutines - Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- [State Flow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Shared Flow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Channels](https://kotlinlang.org/docs/channels.html#channel-basics)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://github.com/square/okhttp)
- [LeakCanary](https://square.github.io/leakcanary/)
- [Testing](https://developer.android.com/training/testing/fundamentals)
  * [MockK](https://mockk.io/)
  * [Junit4](https://junit.org/junit4/)
  * [Truth](https://github.com/google/truth)
  * [Turbine](https://github.com/cashapp/turbine)
  * [Coroutine Test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test)
  * [Compose Test](https://developer.android.com/develop/ui/compose/testing)
  * [Hilt Test](https://developer.android.com/training/dependency-injection/hilt-testing)

### Future work
- Improve UI test coverage and structure for better reliability and maintainability.
- Refine modularization by clarifying module responsibilities and reducing unnecessary dependencies between modules.
- Implement offline support by enabling local persistence for movies and offers using the `:local` module (e.g., Room or DataStore).