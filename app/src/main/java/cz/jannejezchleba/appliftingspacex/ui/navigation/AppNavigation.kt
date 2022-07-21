package cz.jannejezchleba.appliftingspacex.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.jannejezchleba.appliftingspacex.ui.AppMainScreen
import cz.jannejezchleba.appliftingspacex.ui.company.CompanyScreen
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.launch.NextLaunchScreen
import cz.jannejezchleba.appliftingspacex.ui.launches.LaunchesScreen
import cz.jannejezchleba.appliftingspacex.ui.rockets.RocketDetailScreen
import cz.jannejezchleba.appliftingspacex.ui.rockets.RocketsScreen
import cz.jannejezchleba.appliftingspacex.ui.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.Splash.name) {
        composable(AppScreens.Splash.name) {
            SplashScreen(navController)
        }
        composable(AppScreens.NextLaunch.name) {
            AppMainScreen(
                navController,
                headerTitle = stringResource(AppScreens.NextLaunch.resourceNameId)
            ) {
                NextLaunchScreen()
            }
        }
        composable(AppScreens.Launches.name) {
            AppMainScreen(
                navController,
                headerTitle = stringResource(AppScreens.Launches.resourceNameId)
            ) {
                LaunchesScreen(navController)
            }
        }
        composable(AppScreens.Company.name) {
            AppMainScreen(
                navController,
                headerTitle = stringResource(AppScreens.Company.resourceNameId)
            ) {
                CompanyScreen()
            }
        }
        composable(AppScreens.Rockets.name) {
            AppMainScreen(
                navController,
                headerTitle = stringResource(AppScreens.Rockets.resourceNameId)
            ) {
                RocketsScreen(navController)
            }
        }
        composable(
            AppScreens.RocketDetail.name + "/{rocketId}",
            arguments = listOf(navArgument("rocketId") {
                type = NavType.StringType
            })
        ) {
            val id = it.arguments?.getString("rocketId")
            if (id.isNullOrEmpty()) {
                AppMainScreen(
                    navController,
                    headerTitle = stringResource(AppScreens.RocketDetail.resourceNameId)
                ) {
                    ErrorItem(Modifier.fillMaxSize()) {
                        navController.navigate(AppScreens.RocketDetail.name + "/" + id) {
                            popUpTo(AppScreens.RocketDetail.name + "/" + id)
                        }
                    }
                }
            } else {
                AppMainScreen(
                    navController,
                    headerTitle = stringResource(AppScreens.RocketDetail.resourceNameId),
                    displayBackButton = true
                ) {
                    RocketDetailScreen(id)
                }
            }
        }
    }
}