package com.adorah.catchthefruits.ui.theme.screens.action

//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.adorah.catchthefruits.models.Basket
//import com.adorah.catchthefruits.models.Fruitype
//import com.adorah.catchthefruits.models.GameState
//import kotlinx.coroutines.delay
//
//@Composable
//fun Action(navController: NavHostController) {
//    val screenWidth = LocalConfiguration.current.screenWidthDp
//    val screenHeight = LocalConfiguration.current.screenHeightDp
//
//    // Game state using your GameState model
//    val gameState = remember { GameState() }
//
//    // Basket instance
//    val basket = remember {
//        Basket(
//            x = screenWidth / 2f,
//            y = screenHeight * 0.85f,
//            width = 120f,
//            height = 40f
//        )
//    }
//
//    // Game loop effect
//    LaunchedEffect(Unit) {
//        while (!gameState.GameOver()) {
//            gameState.addFruit(screenWidth.toFloat())
//
//            val iterator = gameState.fruits.iterator()
//            while (iterator.hasNext()) {
//                val fruit = iterator.next()
//                fruit.y += fruit.speed
//
//                if (basket.checkCollision(fruit)) {
//                    gameState.score += 10
//                    iterator.remove()
//                } else if (fruit.y > screenHeight) {
//                    gameState.lives -= 1
//                    iterator.remove()
//                }
//            }
//
//            delay(16L)
//        }
//
//        navController.navigate("gameover")
//    }
//
//    // Game UI
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Brush.verticalGradient(colors = listOf(Color.Magenta, Color.Blue)))
//            .pointerInput(Unit) {
//                detectTapGestures {
//                    basket.x = it.x.coerceIn(0f, screenWidth - basket.width)
//                }
//            }
//    ) {
//        // Score
//        Text(
//            text = "Score: ${gameState.score}",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Italic,
//            fontFamily = FontFamily.Cursive,
//            color = Color.Yellow,
//            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)
//        )
//
//        // Basket
//        Box(
//            modifier = Modifier
//                .offset {
//                    IntOffset(basket.x.roundToInt(), basket.y.roundToInt())
//                }
//                .size(width = basket.width.dp, height = basket.height.dp)
//                .background(Color(0xFF8D6E63), shape = RoundedCornerShape(12.dp))
//                .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
//        )
//
//        // Fruits
//        for (fruit in gameState.fruits) {
//            Box(
//                modifier = Modifier
//                    .offset { IntOffset(fruit.x.roundToInt(), fruit.y.roundToInt()) }
//                    .size(40.dp)
//                    .background(
//                        color = when (fruit.type) {
//                            Fruitype.APPLE -> Color.Red
//                            Fruitype.BANANA -> Color.Yellow
//                            Fruitype.ORANGE -> Color(0xFFFFA500)
//                            Fruitype.PAIR -> Color.Green
//                            Fruitype.GUAVA -> Color(0xFF90EE90)
//                            Fruitype.STRAWBERRY -> Color.Magenta
//                        },
//                        shape = CircleShape
//                    )
//                    .border(2.dp, Color.DarkGray, CircleShape)
//            )
//        }
//    }
//}


import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adorah.catchthefruits.models.Basket
import com.adorah.catchthefruits.models.Fruit
import com.adorah.catchthefruits.models.Fruitype
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.random.Random


//
@Composable
fun Action(navController: NavHostController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val basket = remember { Basket(300f, screenHeight.value * 0.8f, 120f, 40f, 20f) }
    val fruits = remember { mutableStateListOf<Fruit>() }
    val score = remember { mutableStateOf(0) }

    // Start the fruit spawner
    LaunchedEffect(Unit) {
        while (true) {
            val type = Fruitype.values().random()
            fruits.add(Fruit(Random.nextInt(0, screenWidth.value.toInt() - 40).toFloat(), 0f, 5f, type))
            delay(1000L)
        }
    }

    // Game loop
    LaunchedEffect(Unit) {
        while (true) {
            fruits.forEach { it.y += it.speed }

            val iterator = fruits.iterator()
            while (iterator.hasNext()) {
                val fruit = iterator.next()
                if (basket.checkCollision(fruit)) {
                    score.value += 10
                    iterator.remove()
                } else if (fruit.y > screenHeight.value) {
                    iterator.remove()
                }
            }

            delay(16L)
        }
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(Color.Magenta, Color.Blue))
            )
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    basket.x = offset.x.coerceIn(0f, screenWidth.value - basket.width)
                }
            }
    ) {
        // Score

        val gameState = null
        Text(
            text = "Score: ${score.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            color = Color.Yellow,
            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)
        )

        // Basket
        var basketX by remember { mutableStateOf(0f) }

        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    basketX += dragAmount.x
                }
            }
        Image(
            painter = painterResource(id = R.drawable.basket),
            contentDescription = "Basket",
            modifier = Modifier
                .offset { IntOffset(basket.x.roundToInt(), basket.y.roundToInt()) }
                .size(width = basket.width.dp, height = basket.height.dp)
        )


        // Fruits
        fruits.forEach { fruit ->
            Text(
                text = when (fruit.type) {
                    Fruitype.APPLE -> "🍎"
                    Fruitype.BANANA -> "🍌"
                    Fruitype.ORANGE -> "🍊"
                    Fruitype.PAIR -> "🍐"
                    Fruitype.GUAVA -> "🥝"
                    Fruitype.STRAWBERRY -> "🍓"
                },
                fontSize = 28.sp,
                modifier = Modifier
                    .offset { IntOffset(fruit.x.roundToInt(), fruit.y.roundToInt()) }
            )
        }
    }
}
@Preview
@Composable
private fun homepreview() {
    Action(rememberNavController())
}
