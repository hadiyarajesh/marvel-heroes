package com.hadiyarajesh.marvel_heroes.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hadiyarajesh.marvel_heroes.navigation.AppNavigation
import com.hadiyarajesh.marvel_heroes.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
