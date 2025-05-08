package com.adorah.catchthefruits.ui.theme.screens.action



//




import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun Action(navController: NavHostController) {
    val basketX = remember { mutableStateOf(300f) }
    val fruitY = remember { Animatable(0f) }
    val score = remember { mutableStateOf(0) }
    val fruitX = remember { mutableStateOf(Random.nextInt(50, 600).toFloat()) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val basketWidth = 120f
    val basketHeight = 40f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Magenta, Color.Blue)
                )
            )
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    // control the basket
                    basketX.value = offset.x
                        .coerceIn(0f, screenWidth.value - basketWidth)
                }
            }
    ) {
        Text(
            text = "Score: ${score.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        )

        // The basket
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(basketX.value.roundToInt(), (screenHeight.value * 0.8f).roundToInt())
                }
                .size(width = basketWidth.dp, height = basketHeight.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6D4C41), Color(0xFF8D6E63))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
        )

        // The fruit
        Box(
            modifier = Modifier
                .offset { IntOffset(fruitX.value.roundToInt(), fruitY.value.roundToInt()) }
                .size(40.dp)
                .background(Color.Red, shape = CircleShape)
                .border(2.dp, Color.DarkGray, CircleShape)
        )
    }

    // Animation and game loop
    LaunchedEffect(Unit) {
        while (true) {
            // Reset the fruit position
            fruitY.snapTo(0f)
            fruitX.value = Random.nextInt(50, (screenWidth.value - 50).toInt()).toFloat()

            // Fruit falling
            fruitY.animateTo(
                targetValue = screenHeight.value * 0.85f,
                animationSpec = tween(durationMillis = 2000)
            )

            // catch
            val basketTopY = screenHeight.value * 0.8f
            val basketBottomY = basketTopY + basketHeight
            val basketLeft = basketX.value
            val basketRight = basketX.value + basketWidth

            val fruitCenterX = fruitX.value + 20f
            val fruitBottomY = fruitY.value + 40f

            val isCaught = fruitBottomY in basketTopY..basketBottomY &&
                    fruitCenterX in basketLeft..basketRight

            if (isCaught) {
                score.value += 1
            }

            delay(400L)
        }
    }
}

@Preview
@Composable
fun ActionPreview() {
    Action(navController = rememberNavController())}
