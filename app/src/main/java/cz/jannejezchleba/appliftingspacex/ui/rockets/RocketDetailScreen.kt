package cz.jannejezchleba.appliftingspacex.ui.rockets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.viewmodel.RocketsViewModel

@Composable
fun RocketDetailScreen(
    id: String,
    viewModel: RocketsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getRocketById(id)
    }

    if (viewModel.isError) {
        ErrorItem(modifier = Modifier.fillMaxSize()) {
            viewModel.getRocketById(id)
        }
    } else if (viewModel.loadedRocketDetail == null) {
        LoadingItem(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn(
            modifier = Modifier.padding(0.dp)
        ) {
            item {
                viewModel.loadedRocketDetail?.let { it1 -> RocketDetail(it1) }
            }
        }
    }
}

