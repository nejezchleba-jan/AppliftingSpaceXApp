package cz.jannejezchleba.appliftingspacex.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.navigation.AppScreens
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.appliftingspacex.viewmodel.NextLaunchViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: NextLaunchViewModel = hiltViewModel()) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        viewModel.getNextLaunch()
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        navController.navigate(AppScreens.NextLaunch.name) {
            popUpTo(AppScreens.Splash.name) {
                inclusive = true
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(CustomMaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ship_flying_white),
            colorFilter = ColorFilter.tint(CustomMaterialTheme.colors.secondary),
            contentDescription = null,
            modifier = Modifier.scale(scale.value)
        )
    }
}