package cz.jannejezchleba.appliftingspacex.ui.rockets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Rocket
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun RocketItemInfoPanel(mass: Rocket.Mass, height: Rocket.Height, diameter: Rocket.Diameter) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(CustomMaterialTheme.padding.S)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3F)
        ) {
            Icon(
                imageVector = Icons.Filled.Height,
                modifier = Modifier.size(30.dp),
                tint = CustomMaterialTheme.colors.secondary,
                contentDescription = stringResource(id = R.string.ROCKET_HEIGHT)
            )
            Text(
                text = height.getFormattedHeight(),
                style = CustomMaterialTheme.typography.subtitle1,
                color = CustomMaterialTheme.colors.secondary,
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3F)

        ) {
            Icon(
                painter = painterResource(R.drawable.diameter),
                modifier = Modifier.size(30.dp),
                tint = CustomMaterialTheme.colors.secondary,
                contentDescription = stringResource(id = R.string.ROCKET_DIAMETER)
            )
            Text(
                text = diameter.getFormattedDiameter(),
                style = CustomMaterialTheme.typography.subtitle1,
                color = CustomMaterialTheme.colors.secondary,
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3F),
        ) {
            Icon(
                painter = painterResource(R.drawable.weight),
                modifier = Modifier.size(30.dp),
                tint = CustomMaterialTheme.colors.secondary,
                contentDescription = stringResource(id = R.string.ROCKET_MASS)
            )
            Text(
                text = mass.getFormattedMass(),
                style = CustomMaterialTheme.typography.subtitle1,
                color = CustomMaterialTheme.colors.secondary,
            )
        }
    }
}