package cz.jannejezchleba.appliftingspacex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.jannejezchleba.appliftingspacex.data.model.Rocket
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {
    var rocketsList by mutableStateOf<List<Rocket.RocketThumbnail>>(listOf())
    var loadedRocketDetail by mutableStateOf<Rocket?>(null)
    var isError by mutableStateOf(false)

    fun getAllRockets() {
        isError = false
        viewModelScope.launch {
            try {
                rocketsList = spaceXRepository.getAllRockets()
            } catch (e: Exception) {
                isError = true
            }
        }
    }

    fun getRocketById(id: String) {
        loadedRocketDetail = null
        isError = false
        viewModelScope.launch {
            try {
                loadedRocketDetail = spaceXRepository.getRocketById(id)
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}