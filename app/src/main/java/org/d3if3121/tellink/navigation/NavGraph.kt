package org.d3if3121.tellink.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3121.tellink.ui.screen.HomePage
import org.d3if3121.tellink.ui.screen.LoginPage
import org.d3if3121.tellink.ui.screen.RegisterPage
import org.d3if3121.tellink.viewmodel.UserViewModel

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    val viewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Login.route){
            LoginPage(navController)
        }

        composable(route = Screen.Register.route){
            RegisterPage(navController)
        }

        composable(route = Screen.Home.route){
            HomePage(navController)
        }
    }
}