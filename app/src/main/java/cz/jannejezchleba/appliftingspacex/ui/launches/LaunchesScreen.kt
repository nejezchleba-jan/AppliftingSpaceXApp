package cz.jannejezchleba.appliftingspacex.ui.launches


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.store.LocalPreferenceDataStore
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.appliftingspacex.viewmodel.LaunchesViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchesScreen(navController: NavController, viewModel: LaunchesViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val preferenceDataStore = remember {
        LocalPreferenceDataStore(context)
    }

    val scope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    LaunchedEffect(Unit) {
        if (!viewModel.initialLoadFinished) {
            viewModel.changeFilter(
                preferenceDataStore.getFilterFromPreferencesStore().first(),
                true
            )
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            if (viewModel.initialLoadFinished && viewModel.filterRockets.isNotEmpty() && !viewModel.resetFilter) {
                LaunchesFilter(viewModel.activeFilter,
                    viewModel.filterRockets,
                    onFilterApply = {
                        viewModel.changeFilter(it)
                        scope.launch {
                            preferenceDataStore.saveFilterToPreferencesStore(it)
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                            viewModel.resetFilter = true
                        }
                    },
                    onDismiss = {
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                            viewModel.resetFilter = true
                        }
                    })
            } else {
                LoadingItem(Modifier.fillMaxSize())
            }
        }) {
        Divider()
        FilterBox {
            scope.launch {
                viewModel.resetFilter = false
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
        }
        Divider()
        LaunchList(navController)
    }
}

@Composable
fun FilterBox(onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(CustomMaterialTheme.colors.primary)
        .clickable {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(CustomMaterialTheme.padding.M)

        ) {
            Text(
                text = stringResource(id = R.string.FILTER),
                style = CustomMaterialTheme.typography.subtitle1
            )
            Icon(
                Icons.Default.FilterAlt,
                contentDescription = stringResource(id = R.string.DESC_FILTER),
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
