package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.appliftingspacex.util.CountdownFormat
import kotlinx.coroutines.delay

@Composable
fun CountDownTimer(
    totalTime: Long
) {
    var currentTime by remember {
        if (totalTime <= 0) {
            mutableStateOf(0)
        } else {
            mutableStateOf(totalTime)
        }

    }

    LaunchedEffect(Unit) {
        while (currentTime > 0) {
            delay(1000L)
            currentTime -= 1000L
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = CustomMaterialTheme.colors.secondary,
                shape = CustomMaterialTheme.shapes.medium
            )
            .padding(CustomMaterialTheme.padding.S),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = CountdownFormat.formatTime(currentTime),
            style = CustomMaterialTheme.typography.h2,
            color = CustomMaterialTheme.colors.secondary
        )
    }
}