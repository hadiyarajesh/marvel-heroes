package com.hadiyarajesh.marvel_heroes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hadiyarajesh.marvel_heroes.ui.bookmark.BookmarkRoute
import com.hadiyarajesh.marvel_heroes.ui.detail.CharacterDetailRoute
import com.hadiyarajesh.marvel_heroes.ui.home.HomeRoute
import com.hadiyarajesh.marvel_heroes.ui.search.SearchRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopLevelDestination.Home.route
    ) {
        composable(route = TopLevelDestination.Home.route) {
            HomeRoute(
                onCharacterClick = { character ->
                    navController.navigate(TopLevelDestination.CharacterDetails.withArgs(character.characterId))
                },
                onSearchClick = { navController.navigate(TopLevelDestination.Search.route) },
                onBookmarkClick = { navController.navigate(TopLevelDestination.Bookmarks.route) }
            )
        }

        composable(route = TopLevelDestination.CharacterDetails.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("id") ?: return@composable

            CharacterDetailRoute(
                characterId = characterId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = TopLevelDestination.Bookmarks.route) {
            BookmarkRoute(onBackClick = { navController.popBackStack() })
        }

        composable(route = TopLevelDestination.Search.route) {
            SearchRoute()
        }
    }
}
