package com.togalugombe.app.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigateToLiveAssist: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onNavigateToArtist: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val isKannada by viewModel.isKannada.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // Hero Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(DarkSurfaceVariant, DarkLeather, DeepBlack)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Filled.TheaterComedy,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                        tint = WarmAmber
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isKannada) "ತೊಗಲು ಗೊಂಬೆ" else "Togalu Gombe",
                        style = MaterialTheme.typography.displayLarge,
                        color = WarmAmber
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isKannada) "ಡಿಜಿಟಲ್ ನೆರಳು-ರಂಗಮಂದಿರ ಸಹಚರ" else "Digital Shadow-Theater Companion",
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftCream,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(
                        onClick = { viewModel.toggleLanguage() },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = WarmAmber),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(listOf(WarmAmber, DeepSaffron))
                        )
                    ) {
                        Icon(Icons.Filled.Translate, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (isKannada) "English" else "ಕನ್ನಡ", fontSize = 13.sp)
                    }
                }
            }
        }

        item {
            Text(
                text = if (isKannada) "ಕಲೆಯನ್ನು ಅನ್ವೇಷಿಸಿ" else "Explore the Art",
                style = MaterialTheme.typography.headlineMedium,
                color = WarmWhite,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )
        }

        item {
            FeatureCard(
                title = if (isKannada) "ಲೈವ್ ಅಸಿಸ್ಟ್" else "Live Assist",
                subtitle = if (isKannada) "ನಾಟಕದ ದೃಶ್ಯ-ಮಾರ್ಗದರ್ಶಿ" else "Scene-by-Scene Story Guide",
                icon = Icons.Filled.TheaterComedy,
                count = "${uiState.playCount} ${if (isKannada) "ನಾಟಕಗಳು" else "Plays"}",
                gradient = listOf(BurntUmber, DarkSurfaceVariant),
                onClick = onNavigateToLiveAssist
            )
        }
        item {
            FeatureCard(
                title = if (isKannada) "ಬೊಂಬೆ ಗ್ಯಾಲರಿ" else "Puppet Gallery",
                subtitle = if (isKannada) "ಪಾತ್ರಗಳ ಸಂಕೇತ ತಿಳಿಯಿರಿ" else "Discover Character Symbolism",
                icon = Icons.Filled.Explore,
                count = "${uiState.puppetCount} ${if (isKannada) "ಬೊಂಬೆಗಳು" else "Puppets"}",
                gradient = listOf(ForestGreen, DarkSurfaceVariant),
                onClick = onNavigateToGallery
            )
        }
        item {
            FeatureCard(
                title = if (isKannada) "ಕಲಾವಿದ ಸಂಪರ್ಕ" else "Artist Connect",
                subtitle = if (isKannada) "ಕಾರ್ಯಾಗಾರ ಬುಕ್ ಮಾಡಿ" else "Book Workshops & Buy Puppets",
                icon = Icons.Filled.Groups,
                count = "${uiState.artistCount} ${if (isKannada) "ಕಲಾವಿದರು" else "Artists"}",
                gradient = listOf(DeepSaffron.copy(alpha = 0.6f), DarkSurfaceVariant),
                onClick = onNavigateToArtist
            )
        }
        item {
            FeatureCard(
                title = if (isKannada) "ಇತಿಹಾಸ ಫೀಡ್" else "History Feed",
                subtitle = if (isKannada) "ಚರ್ಮದ ಕಲೆಯ ಪ್ರಕ್ರಿಯೆ" else "Leather Crafting Process",
                icon = Icons.Filled.History,
                count = "${uiState.videoCount} ${if (isKannada) "ವೀಡಿಯೊಗಳು" else "Videos"}",
                gradient = listOf(MutedGold.copy(alpha = 0.5f), DarkSurfaceVariant),
                onClick = onNavigateToHistory
            )
        }
    }
}

@Composable
private fun FeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    count: String,
    gradient: List<androidx.compose.ui.graphics.Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(gradient))
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(DeepBlack.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = title, tint = WarmAmber, modifier = Modifier.size(28.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, style = MaterialTheme.typography.titleLarge, color = WarmWhite)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = SoftCream)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = count, style = MaterialTheme.typography.labelMedium, color = OilLampGlow)
                }
                Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = WarmAmber)
            }
        }
    }
}
