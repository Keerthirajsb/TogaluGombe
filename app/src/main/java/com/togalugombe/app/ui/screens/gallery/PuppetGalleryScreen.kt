package com.togalugombe.app.ui.screens.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuppetGalleryScreen(
    viewModel: GalleryViewModel = viewModel(),
    onPuppetSelected: (Int) -> Unit
) {
    val puppets by viewModel.filteredPuppets.collectAsState()
    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isKannada by viewModel.isKannada.collectAsState()

    val filters = listOf("all" to "All", "hero" to "Hero", "villain" to "Villain", "deity" to "Deity", "support" to "Support")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = { Text(if (isKannada) "ಬೊಂಬೆ ಗ್ಯಾಲರಿ" else "Puppet Gallery", color = WarmAmber) },
            actions = {
                IconButton(onClick = { viewModel.toggleLanguage() }) {
                    Icon(Icons.Filled.Translate, contentDescription = "Language", tint = WarmAmber)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.setSearchQuery(it) },
            placeholder = { Text(if (isKannada) "ಬೊಂಬೆಗಳನ್ನು ಹುಡುಕಿ…" else "Search puppets…", color = SoftCream) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = MutedGold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = WarmAmber,
                unfocusedBorderColor = DividerColor,
                cursorColor = WarmAmber,
                focusedTextColor = WarmWhite,
                unfocusedTextColor = WarmWhite
            ),
            singleLine = true
        )

        // Filter chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(filters) { (key, label) ->
                FilterChip(
                    selected = selectedFilter == key,
                    onClick = { viewModel.setFilter(key) },
                    label = { Text(label) },
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
                        selected = selectedFilter == key
                    )
                )
            }
        }

        // Puppet grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(puppets) { puppet ->
                PuppetGridCard(
                    nameEn = puppet.nameEn,
                    nameKn = puppet.nameKn,
                    descEn = puppet.descriptionEn,
                    descKn = puppet.descriptionKn,
                    type = puppet.characterType,
                    playName = puppet.playName,
                    isKannada = isKannada,
                    onClick = { onPuppetSelected(puppet.id) }
                )
            }
        }
    }
}

@Composable
private fun PuppetGridCard(
    nameEn: String,
    nameKn: String,
    descEn: String,
    descKn: String,
    type: String,
    playName: String,
    isKannada: Boolean,
    onClick: () -> Unit
) {
    val typeColor = when (type) {
        "hero" -> ForestGreen
        "villain" -> VermillionRed
        "deity" -> WarmAmber
        else -> MutedGold
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            // Puppet image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(typeColor.copy(alpha = 0.3f), DeepBlack)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null,
                    tint = typeColor.copy(alpha = 0.6f),
                    modifier = Modifier.size(48.dp)
                )
                // Type badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(typeColor.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = type.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.labelSmall,
                        color = typeColor
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = if (isKannada) nameKn else nameEn,
                    style = MaterialTheme.typography.titleMedium,
                    color = WarmWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isKannada) descKn else descEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftCream,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = playName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MutedGold
                )
            }
        }
    }
}
