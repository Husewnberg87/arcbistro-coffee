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
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Payment
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
 * Payment Method Card Component
 * Displays payment method icon, title, subtitle, and selection state
 * Used in PaymentMethodScreen
 */
@Composable
fun PaymentMethodCard(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            // Payment Method Icon
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isSelected) Brown01 else LightGray04,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Payment Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = DarkGray03
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        ),
                        color = LightGray04
                    )
                }
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
private fun PaymentMethodCardSelectedPreview() {
    ArcBistroTheme {
        PaymentMethodCard(
            icon = Icons.Outlined.CreditCard,
            title = "Credit Card",
            subtitle = "**** **** **** 4242",
            isSelected = true,
            onSelect = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentMethodCardUnselectedPreview() {
    ArcBistroTheme {
        PaymentMethodCard(
            icon = Icons.Outlined.Money,
            title = "Cash on Delivery",
            subtitle = null,
            isSelected = false,
            onSelect = {}
        )
    }
}
