package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = CustomMaterialTheme.colors.secondary)
    }
}

@Composable
fun EmptyItem(modifier: Modifier = Modifier, emptyText: String) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emptyText, textAlign = TextAlign.Center, style = CustomMaterialTheme.typography.subtitle1)
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

@Composable
fun ErrorItem(modifier: Modifier = Modifier, onClickRetry: () -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(id = R.string.APP_ERROR_MSG))
            Button(onClick = { onClickRetry() }) {
                Text(stringResource(id = R.string.APP_RETRY_BTN))
            }
        }
    }
}