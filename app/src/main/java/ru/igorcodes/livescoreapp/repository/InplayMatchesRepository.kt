package ru.igorcodes.livescoreapp.repository

import ru.igorcodes.livescoreapp.data.remote.SportsDBApiService
import ru.igorcodes.livescoreapp.data.remote.models.Match
import javax.inject.Inject

class InplayMatchesRepository @Inject constructor(private val sportsDBApiService: SportsDBApiService) {
    suspend fun getAllInplayMatches(): List<Match> { return sportsDBApiService.getInplayMatches().teams.Match }
}