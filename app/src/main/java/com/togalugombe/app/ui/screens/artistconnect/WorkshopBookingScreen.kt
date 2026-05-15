package com.togalugombe.app.ui.screens.artistconnect

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.togalugombe.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkshopBookingScreen(
    workshopId: Int,
    viewModel: ArtistViewModel = viewModel(),
    onBack: () -> Unit
) {
    LaunchedEffect(workshopId) { viewModel.loadWorkshop(workshopId) }

    val workshop by viewModel.selectedWorkshop.collectAsState()
    val bookingSuccess by viewModel.bookingSuccess.collectAsState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        TopAppBar(
            title = { Text("Book Workshop", color = WarmAmber) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = WarmAmber)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
        )

        workshop?.let { ws ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Workshop info card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(DarkSurfaceVariant, CardBackground)))
                            .padding(20.dp)
                    ) {
                        Text(ws.title, style = MaterialTheme.typography.headlineMedium, color = WarmAmber)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(ws.description, style = MaterialTheme.typography.bodyLarge, color = SoftCream)
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRow(Icons.Filled.CalendarToday, ws.date)
                        InfoRow(Icons.Filled.LocationOn, ws.location)
                        InfoRow(Icons.Filled.CurrencyRupee, "₹${ws.price.toInt()}")
                        InfoRow(Icons.Filled.EventSeat, "${ws.seatsAvailable} seats available")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Booking form
                Text("Your Details", style = MaterialTheme.typography.titleLarge, color = WarmWhite)
                Spacer(modifier = Modifier.height(12.dp))

                BookingField(value = name, onValueChange = { name = it }, label = "Your Name", icon = Icons.Filled.Person)
                Spacer(modifier = Modifier.height(12.dp))
                BookingField(value = email, onValueChange = { email = it }, label = "Email Address", icon = Icons.Filled.Email)
                Spacer(modifier = Modifier.height(12.dp))
                BookingField(value = phone, onValueChange = { phone = it }, label = "Phone Number", icon = Icons.Filled.Phone)
                Spacer(modifier = Modifier.height(24.dp))

                // Book button
                Button(
                    onClick = {
                        if (name.isNotBlank() && ws.seatsAvailable > 0) {
                            viewModel.bookWorkshop(ws.id)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WarmAmber,
                        contentColor = DeepBlack
                    ),
                    enabled = name.isNotBlank() && ws.seatsAvailable > 0
                ) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Confirm Booking", style = MaterialTheme.typography.titleMedium)
                }

                // Success message
                if (bookingSuccess) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = ForestGreen.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = ForestGreen)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Booking Confirmed! 🎉", style = MaterialTheme.typography.titleMedium, color = ForestGreen)
                        }
                    }
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(3000)
                        viewModel.resetBookingStatus()
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MutedGold, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = SoftCream)
    }
}

@Composable
private fun BookingField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = SoftCream) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MutedGold) },
        modifier = Modifier.fillMaxWidth(),
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
}
