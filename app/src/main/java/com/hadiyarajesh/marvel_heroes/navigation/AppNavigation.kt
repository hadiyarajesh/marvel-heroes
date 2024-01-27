package com.hadiyarajesh.marvel_heroes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.hadiyarajesh.marvel_heroes.ui.detail.CharacterDetailScreen
import com.hadiyarajesh.marvel_heroes.ui.home.HomeScreen
import com.hadiyarajesh.marvel_heroes.ui.home.HomeViewModel
import com.hadiyarajesh.marvel_heroes.utility.Constants

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
            val homeViewModel: HomeViewModel = hiltViewModel()
            val comicCharacters = homeViewModel.comicCharacters.collectAsLazyPagingItems()

            HomeScreen(
                comicCharacters = comicCharacters,
                onCharacterClick = { character ->
                    navController.navigate(TopLevelDestination.CharacterDetails.withArgs(character.id))
                }
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

            CharacterDetailScreen(
                characterId = characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
