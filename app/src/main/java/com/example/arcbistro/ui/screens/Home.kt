package com.example.arcbistro.ui.screens
import androidx.compose.animation.core.copy
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.homeGradient1
import com.example.arcbistro.ui.theme.homeGradient2

import com.example.arcbistro.ui.theme.LightGray04
import com.example.arcbistro.ui.theme.White06
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button

import androidx.compose.material3.Card

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.homeGradient1
import com.example.arcbistro.ui.theme.homeGradient2
import com.example.arcbistro.R
import com.example.arcbistro.ui.theme.LightGray04
import com.example.arcbistro.ui.theme.White06
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.arcbistro.data.menuItems
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.NormalGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val screenWidthPx = this.constraints.maxWidth.toFloat()
        val screenHeightPx = this.constraints.maxHeight.toFloat()

        val startTransition = screenWidthPx * 0.20f
        // Define where it ends (100% for a longer fade, or 75% for a very sharp one)
        val endTransition = screenWidthPx * 0.85f

//        val sharpGradient = Brush.horizontalGradient(
//            colors = listOf(homeGradient2, homeGradient1),
//            startX = startTransition,
//            endX = endTransition
//        )

        val diagonalGradient = Brush.linearGradient(
            colors = listOf(homeGradient2, homeGradient1),
            // Define the start point at the bottom-left
            start = Offset(x = 0f, y = screenHeightPx*0.7f),
            // Define the end point at the top-right
            end = Offset(x = screenWidthPx, y = 0f)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(
                    brush = diagonalGradient
                )
        )

        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp, horizontal = 15.dp)

        ){
            Text(
                text = "Location",
                color = LightGray04,
                style = MaterialTheme.typography.bodyLarge.copy(
                    // And override only the properties you need to change
                    fontSize = 14.sp
                )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text= "Blitzen,Tanjungbalai",
                    color = White06,
                    style = MaterialTheme.typography.titleLarge.copy(
                        // And override only the properties you need to change
                        fontSize = 16.sp
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "Open dropdown",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            // TO DO
                        }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                // Spacer yerine bu daha temiz bir yöntemdir
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                var text by remember { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),// Yüksekliği androidx . compose . material3 . Button ile eşitler
                    placeholder = {
                        Text(
                            "Search coffee",
                            color = LightGray04,
                            style = MaterialTheme.typography.bodyLarge.copy(
                            // And override only the properties you need to change
                            fontSize = 14.sp)
                        )
                    },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.search),contentDescription = "Search") },
                    shape = RoundedCornerShape(16.dp),

                    // Tek satırlık metin alanı için bunu ekleyin, böylece metin uzadığında yükseklik artmaz
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = NormalGray, // Use your desired color
                        unfocusedContainerColor = NormalGray, // Use your desired color
                        disabledContainerColor = NormalGray,
                        focusedIndicatorColor = Color.Transparent, // Hides the underline
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedLeadingIconColor = White06
                    )
                )


                Button(
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(56.dp), // Typical size for an icon button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Brown01 // Use your desired color
                    ),

                    contentPadding = PaddingValues(0.dp) // No padding
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter), // Replace with your filter icon resource
                        contentDescription = "Filter",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp) // Adjust the size as needed
                    )
                }

            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),

            ){
                Image(
                    painter = painterResource(id = R.drawable.promo),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                        .align(Alignment.CenterStart),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
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
                                // And override only the properties you need to change
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .padding(horizontal = 5.dp,vertical = 5.dp)

                        )
                    }
                    Row(

                    ){
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
                                    color = DarkGray03, // Or any color you prefer
                                    shape = RoundedCornerShape(12.dp) // Optional: for rounded corners
                                )
                                .padding(horizontal = 8.dp)

                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "one ", // Note the space
                            color = White06,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "FREE",
                            color = White06,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .background(
                                    color = DarkGray03, // Or any color you prefer
                                    shape = RoundedCornerShape(8.dp) // Optional: for rounded corners
                                )
                                .padding(horizontal = 8.dp) // Add some padding so the text isn't cramped
                        )
                    }

                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )


            val coffeeCategories = listOf("All Coffee", "Machiato", "Latte", "Americano", "Cappuccino")
            var selectedCategory by remember { mutableStateOf("All Coffee") }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ){
                items(coffeeCategories) { category ->
                    CategoryChip(
                        name = category,
                        isSelected = category == selectedCategory,
                        onSelect = { selectedCategory = category }
                    )
                }


            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            // Get your filtered list based on selectedCategory
            val filteredMenuItems = if (selectedCategory == "All Coffee") {
                menuItems
            } else {
                menuItems.filter { it.category == selectedCategory }
            }

// Chunk the list into rows of 2
            val chunkedItems = filteredMenuItems.chunked(2)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp) // Space between rows
            ) {
                items(chunkedItems) { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp) // Space between cards
                    ) {
                        // Create a card for each item in the row

                        for (item in rowItems) {
                            // The weight modifier is the key to creating the grid columns
                            Box(modifier = Modifier.weight(1f)) {
                                CoffeeCard(item = item)
                            }
                        }
                        // If the last row has only one item, we need a spacer to fill the gap
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            //bottomnavigation


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
fun CoffeeCard(item: com.example.arcbistro.data.MenuItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Image with Rating Badge
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
                // Rating badge
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
                        tint = Color(0xFFFFC107), // Yellow star color
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

            // Text and Price section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.name,
                    color = DarkGray03,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = LightGray04,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Price and Add button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        // Format the price to ensure two decimal places
                        text = "$ ${String.format("%.2f", item.price)}",
                        color = DarkGray03,
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
@Preview(showBackground = true, showSystemUi = true) // showSystemUi is also helpful
fun HomeScreenPreview(){
    // Wrap the screen in your theme
    ArcBistroTheme {
        HomeScreen()
    }
}