package cz.jannejezchleba.appliftingspacex.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.jannejezchleba.appliftingspacex.ui.component.TopMenuBar
import cz.jannejezchleba.appliftingspacex.ui.navigation.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun AppMainScreen(
    navController: NavController = NavController(LocalContext.current),
    headerTitle: String = "",
    displayBackButton: Boolean = false,
    content: @Composable (NavController) -> Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopMenuBar(headerTitle, displayBackButton,
                onBackPressed = {
                navController.popBackStack()
            }, onMenuPressed = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        },
        drawerShape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
        drawerContent = {
            NavigationDrawer(
                onDestinationClicked = { route ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(route) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                })
        }
    ) {
        content(navController)
    }

    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}