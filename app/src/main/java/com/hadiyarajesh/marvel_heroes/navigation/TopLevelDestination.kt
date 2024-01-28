package com.hadiyarajesh.marvel_heroes.navigation

/**
 * This class represents all the main screens in the app.
 */
sealed class TopLevelDestination(val route: String) {
    data object Home : TopLevelDestination(route = "home")

    data object CharacterDetails : TopLevelDestination(route = "character_details")

    data object Bookmarks : TopLevelDestination(route = "bookmarks")

    data object Search : TopLevelDestination(route = "search")

    /**
     * This is a helper function to pass arguments to navigation destination.
     * For example, instead of using [TopLevelDestination.CharacterDetails.route]/first_argument/second_argument
     * you can use like [TopLevelDestination.Detail.withArgs(first_argument, second_argument)]
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
