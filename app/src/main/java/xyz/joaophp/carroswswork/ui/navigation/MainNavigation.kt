package xyz.joaophp.carroswswork.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.joaophp.carroswswork.ui.carros.TelaCarros
import xyz.joaophp.carroswswork.ui.drawer.CarrosDrawer
import xyz.joaophp.carroswswork.ui.minhalista.TelaMinhaLista
import xyz.joaophp.carroswswork.ui.topbar.CarrosTopBar

@Composable
fun MainNavigation() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CarrosTopBar(scaffoldState = scaffoldState)
        },
        drawerContent = {
            CarrosDrawer(
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    ) { scaffoldPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.TelaCarros.route
        ) {
            composable(NavigationRoutes.TelaCarros.route) {
                TelaCarros(
                    modifier = Modifier.padding(scaffoldPadding),
                    scaffoldState = scaffoldState
                )
            }
            composable(NavigationRoutes.TelaMinhaLista.route) {
                TelaMinhaLista(
                    navController = navController
                )
            }
        }
    }
}