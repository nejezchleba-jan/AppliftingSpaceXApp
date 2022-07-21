package cz.jannejezchleba.appliftingspacex.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.AppliftingSpaceXTheme
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun NavigationDrawer(
    onDestinationClicked: (route: String) -> Unit
) {
    val screens = listOf(
        AppScreens.NextLaunch,
        AppScreens.Launches,
        AppScreens.Rockets,
        AppScreens.Company
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(color = CustomMaterialTheme.colors.primary)

    ) {
        val logo = painterResource(R.drawable.spacex)
        Image(
            modifier = Modifier
                .aspectRatio(logo.intrinsicSize.width / logo.intrinsicSize.height)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
            painter = logo,
            contentDescription = stringResource(id = R.string.DESC_SPACEX_DRAWER)
        )
        Column(
            Modifier
                .background(color = CustomMaterialTheme.colors.primary)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            screens.forEach { screen ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    elevation = ButtonDefaults.elevation(0.dp),
                    onClick = { onDestinationClicked(screen.name) },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(screen.resourceIconId!!),
                            modifier = Modifier.size(30.dp),
                            tint = CustomMaterialTheme.colors.secondary,
                            contentDescription = stringResource(id = screen.resourceNameId)
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(CustomMaterialTheme.padding.M),
                            text = stringResource(id = screen.resourceNameId),
                            style = CustomMaterialTheme.typography.h5,
                            color = CustomMaterialTheme.colors.secondary,
                        )
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = CustomMaterialTheme.colors.primaryVariant,
                )
            }
        }
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            val moonman = painterResource(R.drawable.moonman)
            Image(
                modifier = Modifier
                    .aspectRatio(moonman.intrinsicSize.width / moonman.intrinsicSize.height)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit,
                painter = moonman,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreviewer() {
    AppliftingSpaceXTheme {
        NavigationDrawer(onDestinationClicked = {})
    }
}