package com.togalugombe.app.ui.screens.liveassist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveAssistScreen(
    viewModel: LiveAssistViewModel = viewModel(),
    onPlaySelected: (Int) -> Unit
) {
    val plays by viewModel.plays.collectAsState()
    val isKannada by viewModel.isKannada.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        // Top bar
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಲೈವ್ ಅಸಿಸ್ಟ್" else "Live Assist",
                    color = WarmAmber
                )
            },
            actions = {
                IconButton(onClick = { viewModel.toggleLanguage() }) {
                    Icon(Icons.Filled.Translate, contentDescription = "Toggle Language", tint = WarmAmber)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        // Header
        Text(
            text = if (isKannada) "ನಾಟಕವನ್ನು ಆಯ್ಕೆಮಾಡಿ" else "Select a Play",
            style = MaterialTheme.typography.headlineMedium,
            color = WarmWhite,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )

        // Play list
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(plays) { play ->
                PlaySelectionCard(
                    titleEn = play.titleEn,
                    titleKn = play.titleKn,
                    descEn = play.descriptionEn,
                    descKn = play.descriptionKn,
                    sceneCount = play.sceneCount,
                    isKannada = isKannada,
                    onClick = { onPlaySelected(play.id) }
                )
            }
        }
    }
}

@Composable
private fun PlaySelectionCard(
    titleEn: String,
    titleKn: String,
    descEn: String,
    descKn: String,
    sceneCount: Int,
    isKannada: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(DarkSurfaceVariant, CardBackground)
                    )
                )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Play icon
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.radialGradient(
                                listOf(WarmAmber.copy(alpha = 0.3f), DeepBlack.copy(alpha = 0.6f))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.AutoStories,
                        contentDescription = null,
                        tint = WarmAmber,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isKannada) titleKn else titleEn,
                        style = MaterialTheme.typography.titleLarge,
                        color = WarmAmber
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isKannada) descKn else descEn,
                        style = MaterialTheme.typography.bodyMedium,
                        color = SoftCream
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Layers,
                            contentDescription = null,
                            tint = MutedGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$sceneCount ${if (isKannada) "ದೃಶ್ಯಗಳು" else "Scenes"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MutedGold
                        )
                    }
                }
                Icon(Icons.Filled.PlayCircle, contentDescription = null, tint = WarmAmber, modifier = Modifier.size(32.dp))
            }
        }
    }
}
