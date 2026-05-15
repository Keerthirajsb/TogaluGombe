package com.togalugombe.app.ui.screens.historyfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
fun HistoryFeedScreen(
    viewModel: HistoryViewModel = viewModel()
) {
    val videos by viewModel.filteredVideos.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val isKannada by viewModel.isKannada.collectAsState()

    val categoryLabels = mapOf(
        "leather_curing" to ("Leather Curing" to "ಚರ್ಮ ಸಂಸ್ಕರಣೆ"),
        "dyeing" to ("Dyeing" to "ಬಣ್ಣ"),
        "cutting" to ("Cutting" to "ಕತ್ತರಿಸುವಿಕೆ"),
        "assembly" to ("Assembly" to "ಜೋಡಣೆ")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = { Text(if (isKannada) "ಇತಿಹಾಸ ಫೀಡ್" else "History Feed", color = WarmAmber) },
            actions = {
                IconButton(onClick = { viewModel.toggleLanguage() }) {
                    Icon(Icons.Filled.Translate, contentDescription = "Language", tint = WarmAmber)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        // Category chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { viewModel.setCategory(null) },
                    label = { Text(if (isKannada) "ಎಲ್ಲಾ" else "All") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = WarmAmber,
                        selectedLabelColor = DeepBlack,
                        containerColor = DarkSurfaceVariant,
                        labelColor = SoftCream
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = DividerColor,
                        selectedBorderColor = WarmAmber,
                        enabled = true,
                        selected = selectedCategory == null
                    )
                )
            }
            items(categories) { cat ->
                val label = categoryLabels[cat]
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { viewModel.setCategory(cat) },
                    label = { Text(if (isKannada) (label?.second ?: cat) else (label?.first ?: cat)) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = WarmAmber,
                        selectedLabelColor = DeepBlack,
                        containerColor = DarkSurfaceVariant,
                        labelColor = SoftCream
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = DividerColor,
                        selectedBorderColor = WarmAmber,
                        enabled = true,
                        selected = selectedCategory == cat
                    )
                )
            }
        }

        // Video list
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(videos) { video ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column {
                        // Video thumbnail placeholder
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(BurntUmber.copy(alpha = 0.4f), DeepBlack)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.PlayCircle,
                                contentDescription = "Play video",
                                tint = WarmAmber.copy(alpha = 0.8f),
                                modifier = Modifier.size(56.dp)
                            )
                            // Duration badge
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(DeepBlack.copy(alpha = 0.7f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                val mins = video.durationSeconds / 60
                                val secs = video.durationSeconds % 60
                                Text(
                                    text = "${mins}:${secs.toString().padStart(2, '0')}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = WarmWhite
                                )
                            }
                            // Category badge
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(WarmAmber.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                val label = categoryLabels[video.category]
                                Text(
                                    text = if (isKannada) (label?.second ?: video.category) else (label?.first ?: video.category),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = WarmAmber
                                )
                            }
                        }

                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (isKannada) video.titleKn else video.titleEn,
                                style = MaterialTheme.typography.titleMedium,
                                color = WarmWhite
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isKannada) video.descriptionKn else video.descriptionEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = SoftCream
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}
