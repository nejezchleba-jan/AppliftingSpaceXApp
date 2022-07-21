package cz.jannejezchleba.appliftingspacex.ui.launches

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.data.model.LaunchesFilter
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import cz.jannejezchleba.appliftingspacex.ui.component.DatePicker
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme


@Composable
fun LaunchesFilter(
    currentFilter: LaunchesFilter,
    rocketList: List<Launch.LaunchRocket>,
    onFilterApply: (LaunchesFilter) -> Unit,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val listOfResults = LaunchSuccess.values().map { it.text }
    val listOfRockets by remember { mutableStateOf(rocketList) }
    val filterState by remember { mutableStateOf(currentFilter.copy()) }

    var dateFrom by remember { mutableStateOf(filterState.getLocalDateFrom()) }
    var dateTo by remember { mutableStateOf(filterState.getLocalDateTo()) }

    var expandedResultDropDown by remember { mutableStateOf(false) }
    var expandedRocketDropDown by remember { mutableStateOf(false) }
    var expandedSortDropDown by remember { mutableStateOf(false) }

    BackHandler {
        onDismiss()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(CustomMaterialTheme.padding.S),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.FILTER),
                textAlign = TextAlign.Center,
                style = CustomMaterialTheme.typography.h6
            )
            Divider()
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    onFilterApply(LaunchesFilter())
                }) {
                Text(stringResource(R.string.BUTTON_RESET))
            }

//LAUNCH TIMEFRAME FILTER
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.FILTER_LAUNCH_TIMEFRAME))
                Divider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DatePicker(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        title = stringResource(R.string.FILTER_LAUNCH_FROM),
                        value = dateFrom,
                        scope = scope
                    ) {
                        filterState.dateFrom = it
                        dateFrom = filterState.getLocalDateFrom()
                    }
                    DatePicker(
                        title = stringResource(R.string.FILTER_LAUNCH_TO),
                        value = dateTo,
                        scope = scope
                    ) {
                        filterState.dateTo = it
                        dateTo = filterState.getLocalDateTo()
                    }
                }
            }
//SORT BY DATE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CustomMaterialTheme.padding.S),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    text = stringResource(R.string.LAUNCH_SORT)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.padding.S)
                        .border(
                            width = 1.dp,
                            color = CustomMaterialTheme.colors.secondary,
                            shape = CustomMaterialTheme.shapes.medium
                        )
                        .clickable {
                            expandedSortDropDown = true
                        },
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(CustomMaterialTheme.padding.S),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(CustomMaterialTheme.padding.S),
                            text = if (filterState.launchSortAsc) stringResource(R.string.LAUNCH_SORT_ASC) else stringResource(
                                R.string.LAUNCH_SORT_DESC
                            ),
                            color = CustomMaterialTheme.colors.secondary,
                            style = CustomMaterialTheme.typography.subtitle1
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            stringResource(id = R.string.DESC_DROPDOWNARROW),
                            tint = CustomMaterialTheme.colors.secondary
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        expanded = expandedSortDropDown,
                        onDismissRequest = { expandedSortDropDown = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            filterState.launchSortAsc = true
                            expandedSortDropDown = false
                        }) {
                            Text(text = stringResource(R.string.LAUNCH_SORT_ASC))
                        }

                        DropdownMenuItem(onClick = {
                            filterState.launchSortAsc = false
                            expandedSortDropDown = false
                        }) {
                            Text(text = stringResource(R.string.LAUNCH_SORT_DESC))
                        }
                    }
                }
            }
            Divider()
//LAUNCH RESULT FILTER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CustomMaterialTheme.padding.S),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    text = stringResource(R.string.LAUNCH_RESULT)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.padding.S)
                        .border(
                            width = 1.dp,
                            color = CustomMaterialTheme.colors.secondary,
                            shape = CustomMaterialTheme.shapes.medium
                        )
                        .clickable {
                            expandedResultDropDown = true
                        },
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(CustomMaterialTheme.padding.S),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(CustomMaterialTheme.padding.S),
                            text = filterState.success.text,
                            color = CustomMaterialTheme.colors.secondary,
                            style = CustomMaterialTheme.typography.subtitle1
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            stringResource(id = R.string.DESC_DROPDOWNARROW),
                            tint = CustomMaterialTheme.colors.secondary
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        expanded = expandedResultDropDown,
                        onDismissRequest = { expandedResultDropDown = false },
                    ) {
                        listOfResults.forEachIndexed { _, text ->
                            DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                                filterState.success = LaunchSuccess.getByText(text)
                                expandedResultDropDown = false
                            }) {
                                Text(text = text, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
//ROCKET FILTER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CustomMaterialTheme.padding.S),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    text = stringResource(R.string.LAUNCH_ROCKET)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomMaterialTheme.padding.S)
                        .border(
                            width = 1.dp,
                            color = CustomMaterialTheme.colors.secondary,
                            shape = CustomMaterialTheme.shapes.medium
                        )
                        .clickable {
                            expandedRocketDropDown = true
                        },
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(CustomMaterialTheme.padding.S),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(CustomMaterialTheme.padding.S),
                            text = listOfRockets.firstOrNull { it.id == filterState.rocketId }?.name
                                ?: stringResource(R.string.MISC_ANY),
                            color = CustomMaterialTheme.colors.secondary,
                            style = CustomMaterialTheme.typography.subtitle1
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            stringResource(id = R.string.DESC_DROPDOWNARROW),
                            tint = CustomMaterialTheme.colors.secondary
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        expanded = expandedRocketDropDown,
                        onDismissRequest = { expandedRocketDropDown = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            filterState.rocketId = null
                            expandedRocketDropDown = false
                        }) {
                            Text(text = stringResource(R.string.MISC_ANY))
                        }

                        listOfRockets.forEachIndexed { _, rocket ->
                            DropdownMenuItem(onClick = {
                                filterState.rocketId = rocket.id
                                expandedRocketDropDown = false
                            }) {
                                Text(text = rocket.name!!)
                            }
                        }
                    }
                }
            }
        }
//BUTTONS
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    onClick = {
                        filterState.rocketId = currentFilter.rocketId
                        filterState.dateFrom = currentFilter.dateFrom
                        filterState.dateTo = currentFilter.dateTo
                        filterState.success = currentFilter.success
                        onDismiss()
                    }) {
                    Text(stringResource(R.string.BUTTON_CANCEL))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(1F),
                    onClick = {
                        val bothDatesSet =
                            filterState.dateFrom != null && filterState.dateTo != null
                        if (bothDatesSet && filterState.getLocalDateFrom()!!
                                .isAfter(filterState.getLocalDateTo())
                        ) {
                            Toast.makeText(
                                context,
                                context.getText(R.string.APP_ERROR_DATE_MSG),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            onFilterApply(filterState)

                        }
                    }) {
                    Text(stringResource(R.string.BUTTON_APPLY))
                }
            }
        }
    }
}