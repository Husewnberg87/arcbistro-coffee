package com.example.arcbistro.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcbistro.R
import com.example.arcbistro.ui.components.PaymentMethodCard
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03

/**
 * Payment Method Screen
 * Purpose: Select payment method
 */
@Composable
fun PaymentMethodScreen(
    onBackClick: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    // UI-only state for payment method selection
    var selectedMethodIndex by remember { mutableIntStateOf(0) }

    // Static placeholder payment methods
    val paymentMethods = listOf(
        PaymentMethodData(
            icon = Icons.Outlined.CreditCard,
            title = "Credit Card",
            subtitle = "**** **** **** 4242"
        ),
        PaymentMethodData(
            icon = Icons.Outlined.Payment,
            title = "Debit Card",
            subtitle = "**** **** **** 8901"
        ),
        PaymentMethodData(
            icon = Icons.Outlined.AccountBalance,
            title = "Bank Transfer",
            subtitle = "Direct bank payment"
        ),
        PaymentMethodData(
            icon = Icons.Outlined.Money,
            title = "Cash on Delivery",
            subtitle = null
        )
    )

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
                        text = "Payment Method",
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
            // Confirm Button
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 8.dp
            ) {
                Button(
                    onClick = onConfirm,
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
                        text = "Confirm",
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
        ) {
            // ============ Payment Methods List ============
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(paymentMethods.size) { index ->
                    PaymentMethodCard(
                        icon = paymentMethods[index].icon,
                        title = paymentMethods[index].title,
                        subtitle = paymentMethods[index].subtitle,
                        isSelected = selectedMethodIndex == index,
                        onSelect = { selectedMethodIndex = index }
                    )
                }
            }
        }
    }
}

/**
 * Simple data holder for payment methods - UI only
 */
private data class PaymentMethodData(
    val icon: ImageVector,
    val title: String,
    val subtitle: String?
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PaymentMethodScreenPreview() {
    ArcBistroTheme {
        PaymentMethodScreen()
    }
}
