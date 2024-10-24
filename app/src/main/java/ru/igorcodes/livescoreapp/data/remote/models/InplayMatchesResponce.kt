package ru.igorcodes.livescoreapp.data.remote.models

data class InplayMatchesResponse(
    val teams: Teams
)

data class Teams(
    val Match: List<Match>
)

data class Match(
    val League: String,
    val HomeTeam_Id: String,
    val HomeTeam: String,
    val AwayTeam_Id: String,
    val AwayTeam: String,
    val Date: String,
    val Time: String,
    val HomeGoals: String,
    val AwayGoals: String
)
