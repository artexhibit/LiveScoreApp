package ru.igorcodes.livescoreapp.viewmodel.state

import ru.igorcodes.livescoreapp.data.remote.models.Match

sealed class MatchesState {
    object Empty: MatchesState()
    object Loading: MatchesState()
    class Success(val matches: List<Match>): MatchesState()
    class Error(val error: String): MatchesState()
}