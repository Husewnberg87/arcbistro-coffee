package com.example.arcbistro.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcbistro.R
import com.example.arcbistro.ui.components.OrderItemCard
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04

/**
 * Order Screen - Checkout UI
 * Purpose: Review order before placing it
 */
@Composable
fun OrderScreen(
    onBackClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentMethodClick: () -> Unit = {},
    onOrderClick: () -> Unit = {}
) {
    // UI-only state for tab selection
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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
                        text = "Order",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = DarkGray03
                    )
                }
            )
        },
        bottomBar = {
            // Primary CTA - Order Button
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 8.dp
            ) {
                Button(
                    onClick = onOrderClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Brown01,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Order",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // ============ Tab Row - Delivery Only ============
            DeliveryTabRow(
                selectedIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ============ Delivery Address Block ============
            DeliveryAddressBlock(
                onClick = onAddressClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Ordered Items Section ============
            SectionTitle(
                title = "Order Items",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Order Item Cards - Static placeholder data
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OrderItemCard(
                    imageRes = R.drawable.coffee_1,
                    title = "Cappuccino",
                    subtitle = "with Oat Milk",
                    price = "$4.50",
                    initialQuantity = 1
                )
                OrderItemCard(
                    imageRes = R.drawable.coffee_2,
                    title = "Americano",
                    subtitle = "Double Shot",
                    price = "$3.50",
                    initialQuantity = 2
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Discount Row ============
            DiscountRow(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Payment Summary ============
            PaymentSummary(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ============ Payment Method Row ============
            PaymentMethodRow(
                onClick = onPaymentMethodClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * Tab Row showing only "Delivery" tab (active)
 */
@Composable
private fun DeliveryTabRow(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.padding(horizontal = 16.dp),
        containerColor = Color.Transparent,
        contentColor = Brown01,
        indicator = { tabPositions ->
            if (selectedIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    height = 3.dp,
                    color = Brown01
                )
            }
        },
        divider = {}
    ) {
        Tab(
            selected = selectedIndex == 0,
            onClick = { onTabSelected(0) },
            text = {
                Text(
                    text = "Delivery",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = if (selectedIndex == 0) FontWeight.SemiBold else FontWeight.Normal
                    )
                )
            },
            selectedContentColor = Brown01,
            unselectedContentColor = LightGray04
        )
    }
}

/**
 * Delivery Address Block - Tappable address display
 */
@Composable
private fun DeliveryAddressBlock(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Location Icon
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Location",
                tint = Brown01,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Two-line Address
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Delivery Address",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGray04
                )
                Text(
                    text = "123 Main Street, Apt 4B, New York",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    color = DarkGray03,
                    maxLines = 1
                )
            }

            // Trailing Chevron
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Change address",
                tint = LightGray04,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Section Title Component
 */
@Composable
private fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        color = DarkGray03,
        modifier = modifier
    )
}

/**
 * Discount Row - Label + discount code + edit icon
 */
@Composable
private fun DiscountRow(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Brown01.copy(alpha = 0.08f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Discount Icon
            Icon(
                imageVector = Icons.Outlined.LocalOffer,
                contentDescription = "Discount",
                tint = Brown01,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Discount Code
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "1 Discount is applied",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = DarkGray03
                )
            }

            // Edit Icon
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit discount",
                tint = Brown01,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Payment Summary - Subtotal, Delivery Fee, Discount, Total
 */
@Composable
private fun PaymentSummary(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Payment Summary",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = DarkGray03
            )

            // Subtotal
            PaymentRow(label = "Subtotal", value = "$11.50")

            // Delivery Fee
            PaymentRow(label = "Delivery Fee", value = "$2.00")

            // Discount
            PaymentRow(label = "Discount", value = "-$1.50", valueColor = Brown01)

            HorizontalDivider(
                color = LightGray04.copy(alpha = 0.3f),
                thickness = 1.dp
            )

            // Total (Emphasized)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = DarkGray03
                )
                Text(
                    text = "$12.00",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Brown01
                )
            }
        }
    }
}

/**
 * Helper composable for payment rows
 */
@Composable
private fun PaymentRow(
    label: String,
    value: String,
    valueColor: Color = DarkGray03
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = LightGray04
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            color = valueColor
        )
    }
}

/**
 * Payment Method Row - Clickable payment method selector
 */
@Composable
private fun PaymentMethodRow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Payment Icon
            Icon(
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = "Payment method",
                tint = Brown01,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Payment Method Text
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Payment Method",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGray04
                )
                Text(
                    text = "Credit Card •••• 4242",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    color = DarkGray03
                )
            }

            // Trailing Chevron
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Change payment method",
                tint = LightGray04,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OrderScreenPreview() {
    ArcBistroTheme {
        OrderScreen()
    }
}
