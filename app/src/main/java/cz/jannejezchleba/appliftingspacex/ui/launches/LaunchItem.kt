package cz.jannejezchleba.appliftingspacex.ui.launches

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.ui.component.Accordion
import cz.jannejezchleba.appliftingspacex.ui.component.ClickableInfoItemRow
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemRow
import cz.jannejezchleba.appliftingspacex.ui.navigation.AppScreens
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun LaunchItem(navController: NavController, launch: Launch) {
    val placeholder = if (isSystemInDarkTheme()) painterResource(R.drawable.ship_flying_white) else painterResource(
        R.drawable.ship_flying)

    Card(
        elevation = 5.dp,
        backgroundColor = CustomMaterialTheme.colors.primary,
        contentColor = CustomMaterialTheme.colors.secondary
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(CustomMaterialTheme.padding.S),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(launch.links.patch?.small)
                        .crossfade(true)
                        .build(),
                    placeholder = placeholder,
                    fallback = placeholder,
                    contentDescription = stringResource(id = R.string.DESC_PATCH_IMG),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.3F)
                        .clip(CustomMaterialTheme.shapes.medium),
                )
                Column(
                    Modifier.padding(
                        horizontal = CustomMaterialTheme.padding.M,
                        vertical = CustomMaterialTheme.padding.XS
                    ),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = launch.name, style = CustomMaterialTheme.typography.h6)
                    Divider(modifier = Modifier.padding(vertical = CustomMaterialTheme.padding.S))
                    if (!launch.rocket.name.isNullOrEmpty()) {
                        ClickableInfoItemRow(
                            title = stringResource(id = R.string.LAUNCH_ROCKET),
                            value = launch.rocket.name
                        ) {
                            navController.navigate(AppScreens.RocketDetail.name + "/" + launch.rocket.id)
                        }
                    }
                    InfoItemRow(
                        title = stringResource(id = R.string.LAUNCH),
                        value = launch.getFormattedLaunchDate()
                    )
                    InfoItemRow(
                        title = stringResource(id = R.string.LAUNCH_RESULT),
                        value = launch.getTextForLaunchResult()
                    )
                }
            }
            if (!launch.details.isNullOrEmpty() || launch.links.linksArePresent()) {
                Accordion(stringResource(id = R.string.LAUNCH_DETAILS)) {
                    LaunchDetail(launch)
                }
            }
        }
    }
}