package cz.jannejezchleba.appliftingspacex.ui.rockets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Rocket
import cz.jannejezchleba.appliftingspacex.ui.navigation.AppScreens
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun RocketsItem(rocket: Rocket.RocketThumbnail, navController: NavController) {
    Card(
        elevation = 5.dp,
        modifier = Modifier.clickable {
            navController.navigate(AppScreens.RocketDetail.name + "/" + rocket.id)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rocket.images.first())
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ship_placeholder),
                    contentDescription = stringResource(id = R.string.DESC_ROCKET_IMG),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(300.dp)
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)),
                )
                Row(
                    Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .background(
                            color = Color.Gray.copy(alpha = 0.4f),
                        )
                        .padding(CustomMaterialTheme.padding.S),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.XS)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.DESC_INFO)
                    )
                    Text(
                        text = stringResource(id = R.string.ROCKET_MORE),
                        color = Color.White,
                        style = CustomMaterialTheme.typography.subtitle2,
                    )
                }

                Text(
                    text = rocket.name,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Black
                            )
                        )
                        .padding(CustomMaterialTheme.padding.M),
                    color = Color.White,
                    style = CustomMaterialTheme.typography.h4,
                )
            }
            RocketItemInfoPanel(rocket.mass, rocket.height, rocket.diameter)
        }
    }
}