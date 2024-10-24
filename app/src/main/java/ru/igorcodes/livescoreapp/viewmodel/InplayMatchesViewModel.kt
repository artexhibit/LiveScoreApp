package ru.igorcodes.livescoreapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.igorcodes.livescoreapp.repository.InplayMatchesRepository
import ru.igorcodes.livescoreapp.viewmodel.state.MatchesState
import javax.inject.Inject

@HiltViewModel
class InplayMatchesViewModel @Inject constructor(private val inplayMatchesRepository: InplayMatchesRepository): ViewModel() {
    var inplayMatchesState = MutableStateFlow<MatchesState>(MatchesState.Empty)
        private set

    init {
        getAllInplayMatches()
    }

    private fun getAllInplayMatches() {
        inplayMatchesState.value = MatchesState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inplayMatchesResponse = inplayMatchesRepository.getAllInplayMatches()
                inplayMatchesState.value = MatchesState.Success(inplayMatchesResponse)
            } catch(e: Exception) {
                inplayMatchesState.value = MatchesState.Error(e.message.toString())
            }
        }
    }
}
