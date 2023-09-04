# ** Android Kotlin Crypto project - 2023 **

The Android Crypto project show information about the top 10 Cryptos

### **Purpose**
To show good practices using Kotlin features and latest Android libraries from Jetpack.

### **Description**
Application connects to coingecko API to download a list of top 15 Cryptos.

Data always comes from the local persistence (offline-first approach) and updates when necessary.

Clicking on each Crypto item navigates user to read more information on new view. ( An empty view with a message is currently implemented. An api implementation is coming soon... )

Use swipe-down gesture to refresh home Cryptos data.

Supports light/dark mode theming automatically.

/!\ this project contains 3 branches :
* master - contains the first commit
* feature/HiltImplementation - Implemented with Hilt for Dependency Injection pattern
* feature/KoinImplementation - Implemented with Koin for Dependency Injection pattern

### **Libraries/concepts used**

* Gradle modularised project by layers ( platform / business / data )
* The Clean Architecture with MVVM / MVI pattern in platform layer
* Jetpack Compose with Material3 design - for UI layer
* Kotlin Coroutines
* Retrofit - for networking
* Glide - for image loading
* JUnit and MockK - for unit tests
