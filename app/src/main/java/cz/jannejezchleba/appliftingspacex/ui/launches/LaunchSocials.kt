package cz.jannejezchleba.appliftingspacex.ui.launches

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.ui.component.SocialsIconButton

@Composable
fun LaunchSocials(links: Launch.LaunchLinks) {
    val context = LocalContext.current
    val websiteIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.article ?: "")) }
    val youTubeIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.webcast ?: "")) }
    val wikipediaIntent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.wikipedia ?: "")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        links.article?.let {
            SocialsIconButton(R.drawable.web, stringResource(id = R.string.DESC_LINK_WEBSITE), context, websiteIntent)
        }
        links.webcast?.let {
            SocialsIconButton(R.drawable.youtube, stringResource(id = R.string.DESC_LINK_WEBCAST), context, youTubeIntent)
        }
        links.wikipedia?.let {
            SocialsIconButton(R.drawable.wiki, stringResource(id = R.string.DESC_LINK_WIKI), context, wikipediaIntent)
        }
    }
}