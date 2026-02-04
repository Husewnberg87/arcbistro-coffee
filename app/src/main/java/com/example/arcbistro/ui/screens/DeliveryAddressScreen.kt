package com.example.arcbistro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcbistro.R
import com.example.arcbistro.ui.components.AddressCard
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04

/**
 * Delivery Address Screen
 * Purpose: Select delivery address from list
 */
@Composable
fun DeliveryAddressScreen(
    onBackClick: () -> Unit = {},
    onAddNewAddress: () -> Unit = {},
    onAddressSelected: () -> Unit = {}
) {
    // UI-only state for address selection
    var selectedAddressIndex by remember { mutableIntStateOf(0) }

    // Static placeholder addresses
    val addresses = listOf(
        AddressData("Home", "123 Main Street, Apt 4B\nNew York, NY 10001"),
        AddressData("Work", "456 Office Tower, Floor 12\nNew York, NY 10002"),
        AddressData("Other", "789 Downtown Plaza, Suite 301\nNew York, NY 10003")
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
                        text = "Delivery Address",
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
                    onClick = onAddressSelected,
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
                        text = "Confirm Address",
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
            // ============ Address List ============
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(addresses.size) { index ->
                    AddressCard(
                        addressType = addresses[index].type,
                        fullAddress = addresses[index].address,
                        isSelected = selectedAddressIndex == index,
                        onSelect = { selectedAddressIndex = index }
                    )
                }

                // ============ Add New Address Button ============
                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = onAddNewAddress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Brown01
                        ),
                        border = BorderStroke(1.dp, Brown01),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Brown01
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Text(
                            text = "Add New Address",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Simple data holder for address - UI only
 */
private data class AddressData(
    val type: String,
    val address: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DeliveryAddressScreenPreview() {
    ArcBistroTheme {
        DeliveryAddressScreen()
    }
}
