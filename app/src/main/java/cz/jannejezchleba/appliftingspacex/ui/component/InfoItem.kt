package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme


@Composable
fun InfoItemColumn(title: String, value: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.XS),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, style = CustomMaterialTheme.typography.subtitle1)
        Text(text = value)
    }
}

@Composable
fun InfoItemRow(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = CustomMaterialTheme.typography.subtitle1)
        Text(text = value)
    }
}

@Composable
fun ClickableInfoItemRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = CustomMaterialTheme.typography.subtitle1)
        Text(
            text = value,
            modifier = Modifier.border(
                1.dp,
                color = CustomMaterialTheme.colors.secondary,
                shape = CustomMaterialTheme.shapes.medium
            ).padding(CustomMaterialTheme.padding.XS)
        )
    }
}