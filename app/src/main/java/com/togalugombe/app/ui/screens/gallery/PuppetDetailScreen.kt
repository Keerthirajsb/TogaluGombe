package com.togalugombe.app.ui.screens.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuppetDetailScreen(
    puppetId: Int,
    viewModel: GalleryViewModel = viewModel(),
    onBack: () -> Unit
) {
    LaunchedEffect(puppetId) { viewModel.loadPuppet(puppetId) }

    val puppet by viewModel.selectedPuppet.collectAsState()
    val isKannada by viewModel.isKannada.collectAsState()

    // Zoom state
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val transformableState = rememberTransformableState { zoomChange, panChange, _ ->
        scale = (scale * zoomChange).coerceIn(1f, 5f)
        offset = if (scale > 1f) offset + panChange else Offset.Zero
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = {
                Text(
                    puppet?.let { if (isKannada) it.nameKn else it.nameEn } ?: "",
                    color = WarmAmber
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = WarmAmber)
                }
            },
            actions = {
                IconButton(onClick = { viewModel.toggleLanguage() }) {
                    Icon(Icons.Filled.Translate, contentDescription = "Language", tint = WarmAmber)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        puppet?.let { p ->
            val typeColor = when (p.characterType) {
                "hero" -> ForestGreen
                "villain" -> VermillionRed
                "deity" -> WarmAmber
                else -> MutedGold
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 100.dp)
            ) {
                // Zoomable puppet image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                        .background(
                            Brush.verticalGradient(
                                listOf(typeColor.copy(alpha = 0.3f), DeepBlack)
                            )
                        )
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .transformable(state = transformableState),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = typeColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(100.dp)
                    )
                }

                // Zoom hint
                Text(
                    text = if (isKannada) "ಝೂಮ್ ಮಾಡಲು ಪಿಂಚ್ ಮಾಡಿ" else "📌 Pinch to zoom",
                    style = MaterialTheme.typography.labelMedium,
                    color = MutedGold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )

                // Character name + type
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isKannada) p.nameKn else p.nameEn,
                        style = MaterialTheme.typography.displayMedium,
                        color = WarmWhite,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(typeColor.copy(alpha = 0.2f))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = p.characterType.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelLarge,
                            color = typeColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                DetailSection(
                    icon = Icons.Filled.Description,
                    title = if (isKannada) "ವಿವರಣೆ" else "Description",
                    content = if (isKannada) p.descriptionKn else p.descriptionEn
                )

                // Powers
                DetailSection(
                    icon = Icons.Filled.FlashOn,
                    title = if (isKannada) "ಶಕ್ತಿಗಳು" else "Powers",
                    content = if (isKannada) p.powersKn else p.powersEn
                )

                // Symbolism
                DetailSection(
                    icon = Icons.Filled.AutoAwesome,
                    title = if (isKannada) "ಸಾಂಕೇತಿಕತೆ" else "Symbolism",
                    content = if (isKannada) p.symbolismKn else p.symbolismEn
                )

                // Appears in
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.TheaterComedy, contentDescription = null, tint = MutedGold, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${if (isKannada) "ಕಾಣಿಸಿಕೊಳ್ಳುವ ನಾಟಕ" else "Appears in"}: ",
                        style = MaterialTheme.typography.labelLarge,
                        color = MutedGold
                    )
                    Text(text = p.playName, style = MaterialTheme.typography.bodyLarge, color = WarmAmber)
                }
            }
        } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = WarmAmber)
        }
    }
}

@Composable
private fun DetailSection(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MutedGold, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, style = MaterialTheme.typography.labelLarge, color = MutedGold)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = AncientParchment,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
