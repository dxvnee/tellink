package org.d3if3121.tellink.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("LoginPage")
    data object Register: Screen("RegisterPage")
}
