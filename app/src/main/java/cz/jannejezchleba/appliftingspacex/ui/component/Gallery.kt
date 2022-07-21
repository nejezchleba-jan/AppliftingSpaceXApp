package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun Gallery(imgUrls: List<String>) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.M),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.ROCKET_GALLERY),
            style = CustomMaterialTheme.typography.h6
        )
        imgUrls.forEach {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ship_placeholder),
                contentDescription = stringResource(id = R.string.DESC_ROCKET_IMG),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(300.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
        }
    }
}