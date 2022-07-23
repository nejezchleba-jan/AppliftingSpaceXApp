package cz.jannejezchleba.appliftingspacex.ui.launch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.component.EmptyItem
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.viewmodel.NextLaunchViewModel

@Composable
fun NextLaunchScreen(
    viewModel: NextLaunchViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getNextLaunch()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.isError) {
            ErrorItem(Modifier.fillMaxSize()) {
                viewModel.getNextLaunch()
            }
        } else if (viewModel.nextLaunch != null && viewModel.isNextScheduled) {
            NextLaunchInfo(viewModel.nextLaunch!!)
        } else if (viewModel.isNextScheduled) {
            EmptyItem(Modifier.fillMaxSize(), emptyText = stringResource(id = R.string.LAUNCH_EMPTY))
        } else {
            LoadingItem(Modifier.fillMaxSize())
        }
    }
}