package com.adorah.catchthefruits.ui.theme.screens.gameover

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.adorah.catchthefruits.ui.theme.screens.home.home

@Composable
fun Failed(
    finalScore: Int,
    onPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF3E0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Game Over",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD84315)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Score: $finalScore",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF5D4037)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onPlayAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF7043))
            ) {
                Text("Play Again", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onBackToMenu,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, Color(0xFFFF7043))
            ) {
                Text("Back to Menu", fontSize = 18.sp, color = Color(0xFFD84315))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Failed() {
    home(rememberNavController())
}
