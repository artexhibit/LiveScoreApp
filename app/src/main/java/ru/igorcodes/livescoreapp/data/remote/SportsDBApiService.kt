package ru.igorcodes.livescoreapp.data.remote

import retrofit2.http.GET
import ru.igorcodes.livescoreapp.data.remote.models.InplayMatchesResponse
import ru.igorcodes.livescoreapp.util.GET_INPLAY_FIXTURES

interface SportsDBApiService {
    @GET(GET_INPLAY_FIXTURES)
    suspend fun getInplayMatches(): InplayMatchesResponse
}