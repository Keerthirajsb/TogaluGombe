package com.togalugombe.app.ui.screens.artistconnect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistConnectScreen(
    viewModel: ArtistViewModel = viewModel(),
    onWorkshopSelected: (Int) -> Unit
) {
    val artists by viewModel.artists.collectAsState()
    val workshops by viewModel.workshops.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = { Text("Artist Connect", color = WarmAmber) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Artists section
            item {
                Text("Master Puppeteers", style = MaterialTheme.typography.headlineMedium, color = WarmWhite)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(artists) { artist ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.horizontalGradient(listOf(DarkSurfaceVariant, CardBackground)))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(BurntUmber.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = null, tint = WarmAmber, modifier = Modifier.size(28.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(artist.name, style = MaterialTheme.typography.titleMedium, color = WarmWhite)
                            Text(artist.specialization, style = MaterialTheme.typography.bodySmall, color = MutedGold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.LocationOn, contentDescription = null, tint = SoftCream, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(artist.location, style = MaterialTheme.typography.bodySmall, color = SoftCream)
                            }
                        }
                    }
                }
            }

            // Workshops section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Available Workshops", style = MaterialTheme.typography.headlineMedium, color = WarmWhite)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(workshops) { workshop ->
                Card(
                    modifier = Modifier.clickable { onWorkshopSelected(workshop.id) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.horizontalGradient(listOf(DeepSaffron.copy(alpha = 0.15f), CardBackground)))
                            .padding(16.dp)
                    ) {
                        Text(workshop.title, style = MaterialTheme.typography.titleLarge, color = WarmAmber)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(workshop.description, style = MaterialTheme.typography.bodyMedium, color = SoftCream)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.CalendarToday, contentDescription = null, tint = MutedGold, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(workshop.date, style = MaterialTheme.typography.labelMedium, color = MutedGold)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.LocationOn, contentDescription = null, tint = MutedGold, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(workshop.location, style = MaterialTheme.typography.labelMedium, color = MutedGold)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("₹${workshop.price.toInt()}", style = MaterialTheme.typography.titleLarge, color = WarmAmber)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(ForestGreen.copy(alpha = 0.2f))
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text("${workshop.seatsAvailable} seats", style = MaterialTheme.typography.labelMedium, color = ForestGreen)
                            }
                        }
                    }
                }
            }

            // Bottom padding for nav bar
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}
