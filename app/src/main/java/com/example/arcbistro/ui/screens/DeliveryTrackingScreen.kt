package com.example.arcbistro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcbistro.R
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Beige02
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04

/**
 * Delivery Tracking Screen
 * Purpose: Delivery tracking UI mock
 */
@Composable
fun DeliveryTrackingScreen(
    onBackClick: () -> Unit = {},
    onCallCourier: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.Unspecified
                        )
                    }
                },
                title = {
                    Text(
                        text = "Track Order",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = DarkGray03
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // ============ Map Placeholder ============
            MapPlaceholder(
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ============ Order Status Section ============
            OrderStatusSection(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Courier Information Card ============
            CourierInfoCard(
                onCallClick = onCallCourier,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Delivery Details ============
            DeliveryDetailsSection(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * Map Placeholder - Large box with map icon and text
 * NO real map SDK used
 */
@Composable
private fun MapPlaceholder(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Beige02.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "Map",
                    tint = Brown01,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Live Map Tracking",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    color = DarkGray03
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Your order is on the way",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGray04
                )
            }
        }
    }
}

/**
 * Order Status Section - Estimated time and progress indicator
 */
@Composable
private fun OrderStatusSection(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Estimated Delivery Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Estimated Delivery",
                        style = MaterialTheme.typography.bodySmall,
                        color = LightGray04
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "15-20 minutes",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = Brown01
                    )
                }

                // Order ID
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Order ID",
                        style = MaterialTheme.typography.bodySmall,
                        color = LightGray04
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "#AB1234",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        color = DarkGray03
                    )
                }
            }

            // Progress Indicator
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Order Status",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGray04
                )

                LinearProgressIndicator(
                    progress = { 0.65f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Brown01,
                    trackColor = Beige02
                )

                // Status Labels
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatusLabel(text = "Confirmed", isActive = true)
                    StatusLabel(text = "Preparing", isActive = true)
                    StatusLabel(text = "On the way", isActive = true)
                    StatusLabel(text = "Delivered", isActive = false)
                }
            }
        }
    }
}

/**
 * Status Label Component
 */
@Composable
private fun StatusLabel(
    text: String,
    isActive: Boolean
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 10.sp,
            fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
        ),
        color = if (isActive) Brown01 else LightGray04
    )
}

/**
 * Courier Information Card
 */
@Composable
private fun CourierInfoCard(
    onCallClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Beige02),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Courier avatar",
                    tint = Brown01,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Courier Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Your Courier",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGray04
                )
                Text(
                    text = "John Smith",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = DarkGray03
                )

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "4.9",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = DarkGray03
                    )
                    Text(
                        text = "(250+ deliveries)",
                        style = MaterialTheme.typography.bodySmall,
                        color = LightGray04
                    )
                }
            }

            // Call Button
            IconButton(
                onClick = onCallClick,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Brown01)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call courier",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

/**
 * Delivery Details Section
 */
@Composable
private fun DeliveryDetailsSection(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Delivery Details",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = DarkGray03
            )

            HorizontalDivider(color = LightGray04.copy(alpha = 0.2f))

            // Delivery Address
            DeliveryDetailRow(
                label = "Delivery Address",
                value = "123 Main Street, Apt 4B\nNew York, NY 10001"
            )

            // Items
            DeliveryDetailRow(
                label = "Items",
                value = "2x Cappuccino, 1x Americano"
            )

            // Payment Method
            DeliveryDetailRow(
                label = "Payment",
                value = "Credit Card •••• 4242"
            )
        }
    }
}

/**
 * Delivery Detail Row Component
 */
@Composable
private fun DeliveryDetailRow(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = LightGray04
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp
            ),
            color = DarkGray03
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DeliveryTrackingScreenPreview() {
    ArcBistroTheme {
        DeliveryTrackingScreen()
    }
}
