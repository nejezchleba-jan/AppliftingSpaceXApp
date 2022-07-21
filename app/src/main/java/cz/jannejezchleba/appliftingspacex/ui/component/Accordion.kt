package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme


@Composable
fun Accordion(
    header: String = "",
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val rotateState = animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier =
            Modifier.clickable{
                  expanded = !expanded
            },
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CustomMaterialTheme.padding.S),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = header,
                    color = CustomMaterialTheme.colors.secondary,
                    modifier = Modifier.fillMaxWidth(0.92F),
                    style = CustomMaterialTheme.typography.subtitle1
                )
                Icon(
                    Icons.Default.ArrowDropDown, stringResource(id = R.string.DESC_DROPDOWNARROW),
                    modifier = Modifier.rotate(rotateState.value),
                    tint = CustomMaterialTheme.colors.secondary
                )
            }
        }
        AnimatedVisibility(
            visible = expanded,
        ) {
            Box(
                Modifier
                    .border(
                        width = 1.dp,
                        shape = CustomMaterialTheme.shapes.medium,
                        color = CustomMaterialTheme.colors.secondary
                    )
                    .fillMaxWidth()
                    .padding(CustomMaterialTheme.padding.XS),
            ) {
                content()
            }
        }
    }
}