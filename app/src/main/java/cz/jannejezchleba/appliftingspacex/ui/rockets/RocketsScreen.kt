package cz.jannejezchleba.appliftingspacex.ui.rockets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import cz.jannejezchleba.appliftingspacex.viewmodel.RocketsViewModel


@Composable
fun RocketsScreen(navController: NavController, viewModel: RocketsViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getAllRockets()
    }

    if (viewModel.isError) {
        ErrorItem(modifier = Modifier.fillMaxSize(), onClickRetry = { viewModel.getAllRockets() })
    } else if (viewModel.rocketsList.isEmpty()) {
        LoadingItem(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.L),
            contentPadding = PaddingValues(vertical = CustomMaterialTheme.padding.M),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = CustomMaterialTheme.padding.M)
        ) {
            items(items = viewModel.rocketsList, key = { it.id }, itemContent = { item ->
                RocketsItem(item, navController)
            })
        }
    }
}

