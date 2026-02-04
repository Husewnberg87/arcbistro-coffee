package com.example.arcbistro.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04

/**
 * Address Card Component
 * Displays address type, full address, and selection state
 * Used in DeliveryAddressScreen
 */
@Composable
fun AddressCard(
    addressType: String,
    fullAddress: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Determine icon based on address type
    val icon: ImageVector = when (addressType.lowercase()) {
        "home" -> Icons.Outlined.Home
        "work", "office" -> Icons.Outlined.Work
        else -> Icons.Outlined.LocationOn
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                Brown01.copy(alpha = 0.08f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = if (isSelected) {
            BorderStroke(1.5.dp, Brown01)
        } else {
            BorderStroke(1.dp, LightGray04.copy(alpha = 0.3f))
        },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Address Type Icon
            Icon(
                imageVector = icon,
                contentDescription = addressType,
                tint = if (isSelected) Brown01 else LightGray04,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Address Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = addressType,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = DarkGray03
                )
                Text(
                    text = fullAddress,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp
                    ),
                    color = LightGray04,
                    maxLines = 2
                )
            }

            // Selection Radio
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Brown01,
                    unselectedColor = LightGray04
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressCardSelectedPreview() {
    ArcBistroTheme {
        AddressCard(
            addressType = "Home",
            fullAddress = "123 Main Street, Apt 4B\nNew York, NY 10001",
            isSelected = true,
            onSelect = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressCardUnselectedPreview() {
    ArcBistroTheme {
        AddressCard(
            addressType = "Work",
            fullAddress = "456 Office Tower, Floor 12\nNew York, NY 10002",
            isSelected = false,
            onSelect = {}
        )
    }
}
