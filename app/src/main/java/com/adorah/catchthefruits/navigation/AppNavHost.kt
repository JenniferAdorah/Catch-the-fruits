package com.adorah.catchthefruits.navigation


import Splashscreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adorah.catchthefruits.ui.theme.screens.action.Action


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_LOGIN) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASH){
            Splashscreen(navController)
        }
        composable(ROUTE_HOME){
            Homescreen(navController)
        }
        composable(ROUTE_ACTION){
            Action(navController)
        }
        composable(ROUTE_GAMEOVER){
            Gameover(navController)
        }


    }

}