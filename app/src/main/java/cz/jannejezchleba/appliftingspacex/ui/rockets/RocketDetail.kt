package cz.jannejezchleba.appliftingspacex.ui.rockets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Rocket
import cz.jannejezchleba.appliftingspacex.ui.component.Accordion
import cz.jannejezchleba.appliftingspacex.ui.component.Gallery
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemColumn
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemRow
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun RocketDetail(rocket: Rocket) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(rocket.images.first())
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ship_placeholder),
            contentDescription = stringResource(id = R.string.DESC_ROCKET_IMG),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(300.dp)
        )
        Column(
            modifier = Modifier
                .padding(CustomMaterialTheme.padding.L)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S)
        ) {
            InfoItemRow(stringResource(id = R.string.ROCKET_NAME), rocket.name)
            InfoItemRow(
                stringResource(id = R.string.ROCKET_FIRST_FLIGHT),
                rocket.getFormattedFirstFlight()
            )
            InfoItemRow(
                stringResource(id = R.string.ROCKET_HEIGHT),
                rocket.height.getFormattedHeight()
            )
            InfoItemRow(
                stringResource(id = R.string.ROCKET_DIAMETER),
                rocket.diameter.getFormattedDiameter()
            )
            InfoItemRow(
                stringResource(id = R.string.ROCKET_MASS),
                rocket.mass.getFormattedMass()
            )
            InfoItemRow(stringResource(id = R.string.ROCKET_STAGES), rocket.stages.toString())
            InfoItemRow(
                stringResource(id = R.string.ROCKET_BOOSTERS),
                rocket.boosters.toString()
            )
            InfoItemRow(
                stringResource(id = R.string.ROCKET_COST_PER_LAUNCH),
                "$${rocket.getFormattedCostPerLaunch()} ${
                    stringResource(
                        id = R.string.UNIT_MILLION
                    )
                }"
            )
            InfoItemRow(
                stringResource(id = R.string.ROCKET_SUCCESS),
                "${rocket.successRate.toInt()}%"
            )
            InfoItemColumn(stringResource(id = R.string.ROCKET_DESCRIPTION), rocket.description)
            if (rocket.payloadWeights.isNotEmpty()) {
                Accordion(stringResource(id = R.string.ROCKET_PAYLOADS)) {
                    Payloads(rocket.payloadWeights)
                }
            }

            if (rocket.images.size > 1) {
                Gallery(rocket.images.drop(1))
            }

            WikipediaLink(rocket.wikipedia)
        }

    }
}


@Composable
fun Payloads(payloads: List<Rocket.PayloadWeight>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.XS),
    ) {
        payloads.forEach {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = it.name,
                    style = CustomMaterialTheme.typography.body2,
                    fontWeight = FontWeight.W800
                )
                Text(text = "${it.kg}" + stringResource(id = R.string.UNIT_KG))
            }
        }
    }
}

@Composable
fun WikipediaLink(link: String) {
    val context = LocalContext.current
    val wikiIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(link)) }
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.XS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.ROCKET_LEARN_MORE),
            style = CustomMaterialTheme.typography.subtitle1,
        )
        IconButton(onClick = {
            context.startActivity(wikiIntent)
        }) {
            Image(
                painter = painterResource(R.drawable.wiki),
                colorFilter = ColorFilter.tint(CustomMaterialTheme.colors.secondary),
                contentDescription = stringResource(id = R.string.DESC_LINK_WIKI),
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
        }
    }

}