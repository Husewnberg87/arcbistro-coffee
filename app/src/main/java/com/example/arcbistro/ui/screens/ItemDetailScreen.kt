package com.example.arcbistro.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.arcbistro.R
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.LightGray04
import com.example.arcbistro.ui.viewmodels.CartViewModel
import com.example.arcbistro.ui.viewmodels.CartViewModelFactory
import com.example.arcbistro.ui.viewmodels.HomeViewModel
import com.example.arcbistro.ui.viewmodels.HomeViewModelFactory
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.roundToInt


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
fun ItemDetailScreen(
    itemId: Int, 
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory),
    cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory)
) {
    val menuItems by homeViewModel.menuItems.collectAsState()
    val item = menuItems.find { it.id == itemId } ?: return
    
    // Size state hoisted from SizeSelector
    var selectedSize by remember { mutableStateOf("M") }
    
    // Observe quantity from the Database via CartViewModel
    val cartItems by cartViewModel.cartItems.collectAsState()
    val currentCartItem = cartItems.find { it.itemId == itemId && it.size == selectedSize }
    val quantity = currentCartItem?.quantity ?: 0

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val fabSizePx = with(density) { 72.dp.toPx() }
    val paddingPx = with(density) { 16.dp.toPx() }
    val topBarHeightPx = with(density) { (56.dp + 16.dp + 48.dp).toPx() }
    val bottomBarHeightPx = with(density) { 80.dp.toPx() }

    val fabOffsetX = remember { Animatable(0f) }
    val fabOffsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    fun snapToNearestEdge(currentX: Float, currentY: Float) {
        coroutineScope.launch {
            val leftEdge = paddingPx
            val rightEdge = screenWidthPx - fabSizePx - paddingPx
            val topEdge = topBarHeightPx + paddingPx
            val bottomEdge = screenHeightPx - fabSizePx - paddingPx - bottomBarHeightPx

            val targetX = if (currentX < screenWidthPx / 2) leftEdge else rightEdge
            val targetY = currentY.coerceIn(topEdge, bottomEdge)

            launch { fabOffsetX.animateTo(targetX, animationSpec = spring(stiffness = 300f)) }
            launch { fabOffsetY.animateTo(targetY, animationSpec = spring(stiffness = 300f)) }
        }
    }

    LaunchedEffect(Unit) {
        val initialX = screenWidthPx - fabSizePx - paddingPx
        val initialY = screenHeightPx - fabSizePx - paddingPx - bottomBarHeightPx
        fabOffsetX.snapTo(initialX)
        fabOffsetY.snapTo(initialY)
    }

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
            BottomAppBar(containerColor = MaterialTheme.colorScheme.background, modifier = Modifier.padding(vertical = 8.dp) ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Price", color = LightGray04,style = MaterialTheme.typography.labelMedium)
                        Spacer(modifier=Modifier.height(4.dp))
                        val totalPrice = item.price * (if (quantity > 0) quantity else 1)
                        Text(
                            text = "$ ${String.format(Locale.US, "%.2f", totalPrice)}",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp, color = Brown01),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Box(modifier = Modifier.fillMaxWidth(0.7f).animateContentSize()) {
                        Crossfade(targetState = (quantity == 0)) { isZero ->
                            if (isZero) {
                                Button(
                                    onClick = { cartViewModel.updateQuantity(item, selectedSize, 1) },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = ButtonDefaults.buttonColors(containerColor = Brown01),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(text = "Buy Now", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
                                }
                            } else {
                                QuantitySelector(
                                    quantity = quantity,
                                    onIncrease = { cartViewModel.updateQuantity(item, selectedSize, quantity + 1) },
                                    onDecrease = { cartViewModel.updateQuantity(item, selectedSize, quantity - 1) },
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp)

            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
//                    placeholder = painterResource(R.drawable.coffee_placeholder),
//                    error = painterResource(R.drawable.coffee_placeholder)
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
                    text = item.subtitle,
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
                SizeSelector(
                    selectedSize = selectedSize,
                    onSizeSelected = { selectedSize = it }
                )
            }

            if (quantity > 0) {
                Surface(
                    modifier = Modifier
                        .offset { IntOffset(fabOffsetX.value.roundToInt(), fabOffsetY.value.roundToInt()) }
                        .size(72.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = { snapToNearestEdge(fabOffsetX.value, fabOffsetY.value) },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    coroutineScope.launch {
                                        val clampedX = (fabOffsetX.value + dragAmount.x).coerceIn(paddingPx, screenWidthPx - fabSizePx - paddingPx)
                                        val clampedY = (fabOffsetY.value + dragAmount.y).coerceIn(topBarHeightPx + paddingPx, screenHeightPx - fabSizePx - paddingPx - bottomBarHeightPx)
                                        fabOffsetX.snapTo(clampedX)
                                        fabOffsetY.snapTo(clampedY)
                                    }
                                }
                            )
                        },
                    shape = CircleShape,
                    color = Brown01,
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp
                ) {
                    IconButton(
                        onClick = { navController.navigate("basket") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bag),
                            contentDescription = "Basket",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(quantity: Int, onIncrease: () -> Unit, onDecrease: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Brown01)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp).fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onDecrease) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "-", tint = Color.White)
            }

            Text(
                text = quantity.toString(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            if (quantity < 999) {
                IconButton(onClick = onIncrease) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "+", tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun SizeSelector(selectedSize: String, onSizeSelected: (String) -> Unit) {
    val sizes = listOf("S", "M", "L")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        sizes.forEach { size ->
            val isSelected = selectedSize == size
            
            Button(
                onClick = { onSizeSelected(size) },
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        brush = if (isSelected) Brush.horizontalGradient(listOf(Brown01, Color.Red)) else SolidColor(LightGray04),
                        shape = RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFFFFF5EE) else Color.Transparent,
                    contentColor = if (isSelected) Brown01 else Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
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
            modifier = Modifier.padding(8.dp).size(24.dp),
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
