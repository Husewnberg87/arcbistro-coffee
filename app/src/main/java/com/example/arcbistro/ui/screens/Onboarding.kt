package com.example.arcbistro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcbistro.R
import com.example.arcbistro.ui.theme.ArcBistroTheme
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.theme.LightGray04
import com.example.arcbistro.ui.theme.White06

@Composable
fun OnboardingScreen(onGetStartedClicked: () -> Unit){
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
    ){

        val screenHeightPx = this.constraints.maxHeight.toFloat()

        Image(
            painter = painterResource(id = R.drawable.coffee_onboarding),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .scale(1.2f)
                .offset(y = -100.dp),
            contentScale = ContentScale.Crop
        )

        val startTransition = screenHeightPx * 0.50f
        // Define where it ends (100% for a longer fade, or 75% for a very sharp one)
        val endTransition = screenHeightPx * 0.55f

        val sharpGradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = startTransition,
            endY = endTransition
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = sharpGradient
                ),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp,vertical = 64.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Fall in Love with Coffee in Blissful Delight!",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier.height(40.dp)
                )
                Text(
                    text = "Welcome to our cozy coffee corner,where every cup is a delightful moment",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onGetStartedClicked,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Get Started",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true) // showSystemUi is also helpful
fun OnboardingScreenPreview(){
    // Wrap the screen in your theme
    ArcBistroTheme {
        OnboardingScreen(onGetStartedClicked = {})
    }
}