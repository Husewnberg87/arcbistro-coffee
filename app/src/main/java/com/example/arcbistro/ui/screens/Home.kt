package com.example.arcbistro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.arcbistro.R
import com.example.arcbistro.data.MenuItem
import com.example.arcbistro.data.menuItems
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04
import com.example.arcbistro.ui.theme.NormalGray
import com.example.arcbistro.ui.theme.White06
import com.example.arcbistro.ui.theme.homeGradient1
import com.example.arcbistro.ui.theme.homeGradient2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            val screenWidthPx = this.constraints.maxWidth.toFloat()
            val screenHeightPx = this.constraints.maxHeight.toFloat()

            val diagonalGradient = Brush.linearGradient(
                colors = listOf(homeGradient2, homeGradient1),
                start = Offset(x = 0f, y = screenHeightPx * 0.7f),
                end = Offset(x = screenWidthPx, y = 0f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .background(brush = diagonalGradient)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp,start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Location",
                    color = LightGray04,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Blitzen,Tanjungbalai",
                        color = White06,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 16.sp
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = "Open dropdown",
                        tint = Color.White,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { /* TO DO */ }
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var text by remember { mutableStateOf("") }
                    TextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        placeholder = {
                            Text(
                                "Search coffee",
                                color = LightGray04,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 14.sp
                                )
                            )
                        },
                        leadingIcon = { Icon(painter = painterResource(R.drawable.search), contentDescription = "Search") },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = NormalGray,
                            unfocusedContainerColor = NormalGray,
                            disabledContainerColor = NormalGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedLeadingIconColor = White06
                        )
                    )

                    Button(
                        onClick = { /* TODO */ },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.size(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Brown01
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Filter",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.promo),
                        contentDescription = "Promo Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp, vertical = 15.dp)
                            .align(Alignment.CenterStart),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFED5151)
                            ),
                        ) {
                            Text(
                                "Promo",
                                color = White06,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
                            )
                        }
                        Row {
                            Text(
                                "Buy one ",
                                color = White06,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                "get",
                                color = White06,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .background(
                                        color = DarkGray03,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 8.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "one ",
                                color = White06,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "FREE",
                                color = White06,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .background(
                                        color = DarkGray03,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val coffeeCategories = listOf("All Coffee", "Machiato", "Latte", "Americano", "Cappuccino")
                var selectedCategory by remember { mutableStateOf("All Coffee") }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(coffeeCategories) { category ->
                        CategoryChip(
                            name = category,
                            isSelected = category == selectedCategory,
                            onSelect = { selectedCategory = category }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val filteredMenuItems = if (selectedCategory == "All Coffee") {
                    menuItems
                } else {
                    menuItems.filter { it.category == selectedCategory }
                }

                val chunkedItems = filteredMenuItems.chunked(2)

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(chunkedItems) { rowItems ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            for (item in rowItems) {
                                Box(modifier = Modifier.weight(1f)) {
                                    CoffeeCard(item = item)
                                }
                            }
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Favorites", "Cart", "Notifications")
    val routes = listOf("home", "favorites", "basket", "notifications")
    val icons = listOf(
        R.drawable.home,
        R.drawable.heart,
        R.drawable.bag,
        R.drawable.notification
    )

    NavigationBar(
        containerColor = Color.White,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = icons[index]), contentDescription = item) },
                selected = selectedItem == index,
                onClick = { 
                    selectedItem = index
                    navController.navigate(routes[index])
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Brown01,
                    unselectedIconColor = LightGray04,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun CategoryChip(name: String, isSelected: Boolean, onSelect: () -> Unit) {
    val backgroundColor = if (isSelected) Brown01 else Color(0xFFEDEDED)
    val textColor = if (isSelected) Color.White else DarkGray03

    Card(
        modifier = Modifier.clickable(onClick = onSelect),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Text(
            text = name,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun CoffeeCard(item: MenuItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd) 
                        .padding(8.dp)
                        .background(Color(0x99000000), RoundedCornerShape(12.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.rating.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightGray04,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$ ${String.format("%.2f", item.price)}",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = { /* TODO: Add to cart */ },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Brown01)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    ArcBistroTheme {
        HomeScreen(navController = rememberNavController())
    }
}
