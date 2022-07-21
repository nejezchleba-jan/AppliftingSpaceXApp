package cz.jannejezchleba.appliftingspacex.ui.launches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.ui.component.EmptyItem
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.appliftingspacex.viewmodel.LaunchesViewModel

@Composable
fun LaunchList(navController: NavController, viewModel: LaunchesViewModel = hiltViewModel()) {
    val launchListItems: LazyPagingItems<Launch> = viewModel.launchesFlow.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    if (viewModel.filterApply) {
        launchListItems.refresh()
        viewModel.filterApply = false
    }

    LaunchedEffect(key1 = launchListItems.loadState.refresh == LoadState.Loading) {
        listState.scrollToItem(0)
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.L),
        contentPadding = PaddingValues(vertical = CustomMaterialTheme.padding.M),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = CustomMaterialTheme.padding.M)
    ) {

        items(items = launchListItems, key = { it.id }) { item ->
            item?.let { LaunchItem(navController, it) }
        }

        launchListItems.apply {
            when {
                loadState.refresh is LoadState.NotLoading && launchListItems.itemCount == 0 -> {
                    item {
                        EmptyItem(
                            modifier = Modifier.fillParentMaxSize(),
                            stringResource(id = R.string.MISC_SORRY) + "\n" + stringResource(id = R.string.NO_LAUNCHES_FOUND)
                        )
                    }
                }
                loadState.refresh is LoadState.Loading && launchListItems.itemCount == 0 -> {
                    item {
                        LoadingItem(modifier = Modifier.fillParentMaxSize())
                    }

                }
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingItem(modifier = Modifier.fillParentMaxSize())
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
            }

        }
    }
}