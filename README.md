# Marvel’s Comic Characters App

This repository contains the code for Marvel’s Comic Characters App. It is created with MVVM architecture using Kotlin and includes the following popular libraries:

- [Hilt](https://dagger.dev/hilt) - Hilt is a dependency injection library for Android that reduces
  the boilerplate of doing manual dependency injection in your project.
- [Room](https://developer.android.com/training/data-storage/room) - Room persistence library
  provides an abstraction layer over SQLite to allow fluent database access while harnessing the
  full power of SQLite.
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and the JVM.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil) - Image loading for Android backed by Kotlin Coroutines.

## How to run
To run this project,

1. Visit [Marvel’s developer API](https://developer.marvel.com/docs) and create your API KEY
2. Add following entry to your `local.properties` file

```properties
apiPublicKey=YOUR_API_PUBLIC_KEY
apiPrivateKey=YOUR_API_PRIVATE_KEY
```

## Visuals
See the video of app walkthrough [here](https://drive.google.com/file/d/10LLqC_uFY-aTlknuYT-FvXW9FE52vkmS/view)

## Release build
Get ready-to-install relase build of the app [here](https://drive.google.com/file/d/1W_9hlEe_RM7urL1oqNYbgXSugWXEMAW9/view)

## Annotation Processing

This project uses [Kotlin Symbol Processing (KSP)](https://kotlinlang.org/docs/ksp-overview.html)
for annotation processing, which provides faster build times compared
to [KAPT](https://kotlinlang.org/docs/kapt.html).

## Build and Configuration Caching

This project also takes advantage of Gradle's [Build Cache](https://docs.gradle.org/current/userguide/build_cache.html) and [Configuration Cache](https://docs.gradle.org/current/userguide/configuration_cache.html) features to speed up builds and reduce build times. 
Note that these features may not always provide significant improvements in build times depending on the project structure and build complexity.

## Contribution

Contributions to this project are welcome! If you encounter any problems or have suggestions for improvement, feel free to submit a pull request or open an issue.

## License

This project is licensed under the [MIT License](https://github.com/hadiyarajesh/compose-template/blob/master/LICENSE).
