package com.togalugombe.app.ui.screens.liveassist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SceneDetailScreen(
    playId: Int,
    initialSceneIndex: Int = 0,
    viewModel: LiveAssistViewModel = viewModel(),
    onBack: () -> Unit
) {
    LaunchedEffect(playId) { viewModel.loadScenesForPlay(playId) }

    val play by viewModel.selectedPlay.collectAsState()
    val scenes by viewModel.scenes.collectAsState()
    val isKannada by viewModel.isKannada.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = play?.let { if (isKannada) it.titleKn else it.titleEn } ?: "",
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

        if (scenes.isNotEmpty()) {
            val pagerState = rememberPagerState(
                initialPage = initialSceneIndex,
                pageCount = { scenes.size }
            )

            // Swipe hint
            Text(
                text = if (isKannada) "ದೃಶ್ಯಗಳನ್ನು ನ್ಯಾವಿಗೇಟ್ ಮಾಡಲು ಸ್ವೈಪ್ ಮಾಡಿ ◀ ▶" else "◀ Swipe to navigate scenes ▶",
                style = MaterialTheme.typography.labelMedium,
                color = MutedGold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Page indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                scenes.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (index == pagerState.currentPage) 10.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage) WarmAmber else DividerColor
                            )
                    )
                }
            }

            // Scene pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp),
                pageSpacing = 16.dp
            ) { page ->
                val scene = scenes[page]
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(DarkSurfaceVariant, CardBackground, DeepBlack)
                                )
                            )
                            .padding(24.dp)
                    ) {
                        // Scene number badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(WarmAmber.copy(alpha = 0.15f))
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${if (isKannada) "ದೃಶ್ಯ" else "Scene"} ${scene.sceneNumber}",
                                style = MaterialTheme.typography.labelLarge,
                                color = WarmAmber
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Scene title
                        Text(
                            text = if (isKannada) scene.titleKn else scene.titleEn,
                            style = MaterialTheme.typography.headlineLarge,
                            color = WarmWhite
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Divider(color = DividerColor, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(16.dp))

                        // Scene image placeholder
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.radialGradient(
                                        listOf(BurntUmber.copy(alpha = 0.4f), DeepBlack)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.TheaterComedy,
                                contentDescription = null,
                                tint = WarmAmber.copy(alpha = 0.5f),
                                modifier = Modifier.size(64.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Summary
                        Text(
                            text = if (isKannada) scene.summaryKn else scene.summaryEn,
                            style = MaterialTheme.typography.bodyLarge,
                            color = AncientParchment,
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Characters
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.People,
                                contentDescription = null,
                                tint = MutedGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isKannada) "ಪಾತ್ರಗಳು" else "Characters",
                                style = MaterialTheme.typography.labelLarge,
                                color = MutedGold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            scene.characters.split(", ").forEach { character ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(DeepSaffron.copy(alpha = 0.15f))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = character,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = DeepSaffron
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = WarmAmber)
            }
        }
    }
}
