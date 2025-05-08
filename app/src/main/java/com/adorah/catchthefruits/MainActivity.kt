package com.adorah.catchthefruits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adorah.catchthefruits.navigation.AppNavHost
import com.adorah.catchthefruits.ui.theme.CatchTheFruitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatchTheFruitsTheme {

                    AppNavHost()


                }
            }
        }
    }


