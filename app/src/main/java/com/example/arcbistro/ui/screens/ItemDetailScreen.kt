package com.example.arcbistro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.arcbistro.R
import com.example.arcbistro.data.menuItems
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.LightGray04


@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    tonalElevation: Dp = 0.dp,
    centerTitle: Boolean = false,
    navigationIcon: (@Composable () -> Unit)? = null,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding( top = 16.dp),
        color = containerColor,
        tonalElevation = tonalElevation,
        contentColor = contentColorFor(containerColor),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (navigationIcon != null) {
                Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                    navigationIcon()
                }
            }

            Box(
                modifier = Modifier
//                    .weight(1f)
                    .padding(horizontal = if (navigationIcon != null) 4.dp else 0.dp),
                contentAlignment = if (centerTitle) Alignment.Center else Alignment.CenterStart
            ) {
                title()
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                content = actions
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(itemId: Int, navController: NavController) {
    val item = menuItems.find { it.id == itemId } ?: return

    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(id = R.drawable.arrow_left), contentDescription = "Back",tint = Color.Unspecified)
                    }
                },
                title = {
                    Text("Detail", style = MaterialTheme.typography.labelMedium.copy(fontSize = 18.sp,fontWeight = FontWeight.SemiBold), color = Color.Black)
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(painterResource(id = R.drawable.heart), contentDescription = "Favorite",tint = Color.Unspecified)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.background ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Price", color = LightGray04)
                        Text(
                            text = "$ ${String.format("%.2f", item.price)}",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 18.sp,color = Brown01),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth(0.7f),
                        colors = ButtonDefaults.buttonColors(containerColor = Brown01),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Buy Now", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp)

        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ice/Hot",
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightGray04
                )

                Row(){
                    IngredientIcon(R.drawable.bike)
                    IngredientIcon(R.drawable.coffee_beans)
                    IngredientIcon(R.drawable.milk)
                }
            }
//            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Rating",
                    tint = Color(0xFFFFAA00),

                )
                Text(
                    text = " ${item.rating}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = " (230)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightGray04
                )
            }

            Spacer(modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 8.dp)
                .height(1.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
            )
            Text(
                text = "Description",
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Text(
                text = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray04
            )
            Text(
                text = ".. Read More",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Brown01
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Size",
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
            SizeSelector()
        }
    }
}

@Composable
fun SizeSelector() {
    var selectedSize by remember { mutableStateOf("M") }
    val sizes = listOf("S", "M", "L")

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        sizes.forEach { size ->
            val isSelected = selectedSize == size

            val colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color(0xFFFFF5EE) else Color.Transparent,
                contentColor = if (isSelected) Brown01 else Color.Black
            )

            val border = if (isSelected) {
                BorderStroke(1.dp, Brush.horizontalGradient(listOf(Brown01,Color.Red)))
            } else {
                // use null or a light transparent stroke depending on desired look
                BorderStroke(1.dp, LightGray04)
            }

            OutlinedButton(
                onClick = { selectedSize = size },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = colors,
                border = border
            ) {
                Text(text = size)
            }
        }
    }
}

@Composable
fun IngredientIcon(iconRes: Int) {
    Card(
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F2ED))
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp),
            tint = Brown01
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDetailScreenPreview() {
    ArcBistroTheme {
        ItemDetailScreen(itemId = 1, navController = rememberNavController())
    }
}
