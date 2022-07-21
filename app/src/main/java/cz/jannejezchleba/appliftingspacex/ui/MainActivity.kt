package cz.jannejezchleba.appliftingspacex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import cz.jannejezchleba.appliftingspacex.ui.navigation.AppNavigation
import cz.jannejezchleba.appliftingspacex.ui.theme.AppliftingSpaceXTheme
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            val navController = rememberNavController()
            AppliftingSpaceXTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CustomMaterialTheme.colors.primary
                ) {
                    AppNavigation(navController)
                }
            }
        }
    }
}