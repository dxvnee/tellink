package org.d3if3121.tellink.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.d3if3121.tellink.ui.screen.HomePage
import org.d3if3121.tellink.ui.screen.LoginPage
import org.d3if3121.tellink.ui.screen.ProfilePage
import org.d3if3121.tellink.ui.screen.ProjectPage
import org.d3if3121.tellink.ui.screen.RegisterPage
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel


@Composable
fun SetupNavGraph(){

    val navController = rememberNavController()
    val mahasiswalistviewmodel: MahasiswaListViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ) {
        composable(route = Screen.Login.route){
            LoginPage(navController, mahasiswalistviewmodel)
        }

        composable(route = Screen.Register.route){
            RegisterPage(navController, mahasiswalistviewmodel)
        }

        composable(route = Screen.Home.route){
            HomePage(navController, mahasiswalistviewmodel)
        }

        composable(route = Screen.Project.route){
            ProjectPage(navController)
        }

        composable(route = Screen.Profile.route){
            ProfilePage(navController)
        }
    }
}