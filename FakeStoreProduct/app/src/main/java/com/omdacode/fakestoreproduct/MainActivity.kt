package com.omdacode.fakestoreproduct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.*
import com.omdacode.fakestoreproduct.di.AppModule
import com.omdacode.fakestoreproduct.presentation.screens.DetailScreen
import com.omdacode.fakestoreproduct.presentation.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        viewModel = AppModule.productViewModel,
                        onProductClick = { productId ->
                            navController.navigate("detail/$productId")
                        }
                    )
                }
                composable("detail/{productId}") { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                    DetailScreen(productId)
                }
            }
        }
    }
}