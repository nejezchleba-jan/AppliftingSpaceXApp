package cz.jannejezchleba.appliftingspacex.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun SocialsIconButton(iconResourceId: Int, contentDesc: String, context: Context, intent: Intent) {
    IconButton(onClick = {
        context.startActivity(intent)
    }) {
        Image(
            painter = painterResource(iconResourceId),
            colorFilter = ColorFilter.tint(CustomMaterialTheme.colors.secondary),
            contentDescription = contentDesc,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        )
    }
}