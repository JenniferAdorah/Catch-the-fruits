package com.adorah.catchthefruits.navigation


import Splashscreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adorah.catchthefruits.ui.theme.screens.action.Action
import com.adorah.catchthefruits.ui.theme.screens.gameover.Gameover
import com.adorah.catchthefruits.ui.theme.screens.home.Homescreen


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_HOME) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASH){
            Splashscreen(
                navController,
                onStartClicked = TODO()
            )
        }
        composable(ROUTE_HOME){
            Homescreen(navController)
        }
        composable(ROUTE_ACTION){
            Action(navController)
        }
        composable(ROUTE_GAMEOVER){
            Gameover(
                navController,
                finalScore = 10,
                onPlayAgain = {ROUTE_ACTION},
                onBackToMenu = {ROUTE_HOME}
            )
        }


    }

}