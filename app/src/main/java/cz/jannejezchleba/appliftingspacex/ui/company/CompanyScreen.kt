package cz.jannejezchleba.appliftingspacex.ui.company


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cz.jannejezchleba.appliftingspacex.ui.component.ErrorItem
import cz.jannejezchleba.appliftingspacex.ui.component.LoadingItem
import cz.jannejezchleba.appliftingspacex.viewmodel.CompanyViewModel

@Composable
fun CompanyScreen(viewModel: CompanyViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getCompanyInfo()
    }
    if (viewModel.isError) {
        ErrorItem(modifier = Modifier.fillMaxSize()) { viewModel.getCompanyInfo() }
    } else if (viewModel.companyData == null) {
        LoadingItem(modifier = Modifier.fillMaxSize())
    } else {
        CompanyInfo(viewModel.companyData!!)
    }
}



