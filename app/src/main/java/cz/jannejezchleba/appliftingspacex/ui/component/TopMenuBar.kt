package cz.jannejezchleba.appliftingspacex.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun TopMenuBar(
    title: String,
    displayBackButton: Boolean = false,
    onBackPressed: () -> Unit = {},
    onMenuPressed: () -> Unit = {},
) {
    TopAppBar(
        backgroundColor = CustomMaterialTheme.colors.primary,
        contentColor = CustomMaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.height(32.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    if (displayBackButton) {
                        IconButton(
                            onClick = {
                                onBackPressed()
                            }) {
                            Icon(Icons.Filled.ChevronLeft, stringResource(id = R.string.DESC_BACK))
                        }
                    } else {
                        IconButton(
                            onClick = {
                                onMenuPressed()
                            }) {
                            Icon(Icons.Filled.Menu, stringResource(id = R.string.DESC_MENU))
                        }

                    }
                }
            }

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = CustomMaterialTheme.typography.h6,
                        maxLines = 1,
                        text = title
                    )
                }
            }
        }
    }
}

@Composable
fun TopMenuBarWithFilter(
    title: String,
    onFilterPressed: () -> Unit = {},
    onMenuPressed: () -> Unit = {},
) {
    TopAppBar(
        backgroundColor = CustomMaterialTheme.colors.primary,
        contentColor = CustomMaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.height(32.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(
                        onClick = {
                            onMenuPressed()
                        }) {
                        Icon(Icons.Filled.Menu, stringResource(id = R.string.DESC_MENU))
                    }
                }
            }

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = CustomMaterialTheme.typography.h6,
                        maxLines = 1,
                        text = title
                    )
                }
            }

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
            ) {
                IconButton(
                    onClick = {
                        onFilterPressed()
                    }) {
                    Icon(Icons.Default.FilterAlt, stringResource(id = R.string.DESC_FILTER))
                }
            }
        }
    }
}