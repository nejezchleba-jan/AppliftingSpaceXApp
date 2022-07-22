package cz.jannejezchleba.appliftingspacex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.jannejezchleba.appliftingspacex.data.model.NextLaunch
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NextLaunchViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {
    var nextLaunch by mutableStateOf<NextLaunch?>(null)
    var isNextScheduled by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getNextLaunch() {
        isError = false
        viewModelScope.launch {
            try {
                val nextFoundLaunch = spaceXRepository.getNextLaunch()
                nextLaunch =
                    if (nextFoundLaunch.getLaunchLocalDateTime().isBefore(LocalDateTime.now())) {
                        //Check if launch already past in cache and force request for new
                        val nextFoundLaunchWithoutCache = spaceXRepository.getNextLaunch(true)
                        if (nextFoundLaunch.id == nextFoundLaunchWithoutCache.id) {
                            //If API wrongly returns same already past launch take next from upcoming
                            spaceXRepository.getUpcomingLaunches().first {
                                it.getLaunchLocalDateTime()
                                    .isAfter(nextFoundLaunchWithoutCache.getLaunchLocalDateTime())
                            }
                        } else {
                            nextFoundLaunchWithoutCache
                        }
                    } else {
                        nextFoundLaunch
                    }
                isNextScheduled = nextLaunch != null
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}