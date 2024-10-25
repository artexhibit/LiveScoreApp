package ru.igorcodes.livescoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.igorcodes.livescoreapp.data.remote.models.Match
import ru.igorcodes.livescoreapp.ui.theme.LiveScoreAppTheme
import ru.igorcodes.livescoreapp.viewmodel.InplayMatchesViewModel
import ru.igorcodes.livescoreapp.viewmodel.state.MatchesState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveScoreAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        TopAppBar()
                        FetchData()
                    }
                }
            }
        }
    }
}

@Composable
fun FetchData(inplayMatchesViewModel: InplayMatchesViewModel = viewModel()) {
    Column {
        when (val state = inplayMatchesViewModel.inplayMatchesState.collectAsState().value) {
            is MatchesState.Empty -> Text(text = "No data available")
            is MatchesState.Loading -> Text(text = "Loading")
            is MatchesState.Error -> Text(text = state.error)
            is MatchesState.Success -> LiveMatches(state.matches)
        }
    }
}

@Composable
fun LiveMatches(liveMatches: List<Match>) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(
            text = "Live Matches",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        if (liveMatches.isEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "No Live Matches Currently"
                )
                Text(
                    text = "No Live Matches Currently",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        } else {
            LazyRow(
                modifier = Modifier.padding(top = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(liveMatches.size) {
                    LiveMatchItem(liveMatches[it])
                }
            }
        }
    }
}

@Composable
fun LiveMatchItem(match: Match) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .width(300.dp)
            .height(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = match.League,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.HomeTeam,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${match.HomeGoals}:${match.AwayGoals}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = match.AwayTeam,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            SuggestionChip(
                onClick = {
                    // Handle click
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color.Green,
                    labelColor = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                label = {
                    Text(text = matchStatus(match))
                }
            )
        }
    }
}

private fun matchStatus(match: Match) : String {
    return when(match.Time) {
        "45" -> "HT"
        "0" -> "Not Started"
        "90" -> "FT"
        else -> match.Time
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh Icon")
        }

        Text("LiveScores", style = MaterialTheme.typography.headlineLarge)

        IconButton(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.modeicon),
                contentDescription = "Toggle Theme Icon"
            )
        }
    }
}
