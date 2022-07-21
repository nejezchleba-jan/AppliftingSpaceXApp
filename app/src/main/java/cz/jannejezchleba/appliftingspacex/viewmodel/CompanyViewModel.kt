package cz.jannejezchleba.appliftingspacex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.jannejezchleba.appliftingspacex.data.model.Company
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {
    var companyData by mutableStateOf<Company?>(null)
    var isError by mutableStateOf(false)

    fun getCompanyInfo() {
        isError = false
        viewModelScope.launch {
            try {
                companyData = spaceXRepository.getCompany()
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}