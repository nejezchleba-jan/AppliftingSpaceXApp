package cz.jannejezchleba.appliftingspacex.ui.launch

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.NextLaunch
import cz.jannejezchleba.appliftingspacex.ui.component.CountDownTimer
import cz.jannejezchleba.appliftingspacex.ui.launches.LaunchSocials
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun NextLaunchInfo(launch: NextLaunch) {
    val timeToLaunch = remember {
        Duration.between(LocalDateTime.now(), launch.getLaunchLocalDateTime()).toMillis()
    }

    val placeholder =
        if (isSystemInDarkTheme()) painterResource(R.drawable.ship_flying_white) else painterResource(
            R.drawable.ship_flying
        )

    Column(
        Modifier
            .fillMaxSize()
            .padding(CustomMaterialTheme.padding.S),
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.LAUNCH_NAME),
            style = CustomMaterialTheme.typography.subtitle1
        )
        Text(text = launch.name, style = CustomMaterialTheme.typography.h4)
        Divider()
        CountDownTimer(totalTime = timeToLaunch)
        Divider()
        Text(
            text = stringResource(id = R.string.LAUNCH_LOCAL_DATE),
            style = CustomMaterialTheme.typography.subtitle1
        )
        Text(
            text = launch.getFormattedLaunchDate(),
            style = CustomMaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Divider()
        if (launch.links.linksArePresent()) {
            LaunchSocials(launch.links)
            Divider()
        }
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(launch.links.patch?.large)
                    .crossfade(true)
                    .build(),
                placeholder = placeholder,
                fallback = placeholder,
                contentDescription = stringResource(id = R.string.DESC_PATCH_IMG),
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(CustomMaterialTheme.shapes.medium),
            )
        }
    }
}