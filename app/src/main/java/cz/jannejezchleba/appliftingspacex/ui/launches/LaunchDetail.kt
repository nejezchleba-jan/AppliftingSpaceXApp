package cz.jannejezchleba.appliftingspacex.ui.launches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemColumn
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun LaunchDetail(launch: Launch) {
    Column(
        modifier = Modifier.padding(CustomMaterialTheme.padding.S),
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S)
    ) {
        launch.details?.let {
            InfoItemColumn(title = stringResource(id = R.string.LAUNCH_NOTE), value = launch.details)
            Divider()
        }
        LaunchSocials(launch.links)
    }
}