package com.tirexmurina.facevolume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tirexmurina.facevolume.features.editScreen.EditScreen
import com.tirexmurina.facevolume.features.infoScreen.InfoScreen
import com.tirexmurina.facevolume.features.mainScreen.MainScreen
import com.tirexmurina.facevolume.features.searchScreen.SearchScreen
import com.tirexmurina.facevolume.features.settingsScreen.SettingsScreen
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaceVolumeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FaceVolumeApp()
                }
            }
        }
    }
}

@Composable
fun FaceVolumeApp() {
    val systemDarkTheme = isSystemInDarkTheme()
    var darkThemeEnabled by rememberSaveable { mutableStateOf(systemDarkTheme) }

    FaceVolumeTheme(darkTheme = darkThemeEnabled) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "main"){
            composable("main") {
                MainScreen(
                    onItemClick = { contactId -> navController.navigate("info/$contactId") },
                    onSettingsClick = { navController.navigate("settings") },
                    onSearchClick = { navController.navigate("search") },
                    onAddClick = { navController.navigate("edit/-1") }
                )
            }
            composable(
                "info/{contactId}",
                arguments = listOf(navArgument("contactId") { type = NavType.LongType })
            ) { backStackEntry ->
                val contactId = backStackEntry.arguments?.getLong("contactId")
                InfoScreen(
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { id -> navController.navigate("edit/${id}") },
                    contactId = contactId
                )
            }
            composable(
                "edit/{contactId}",
                arguments = listOf(navArgument("contactId") {
                    type = NavType.LongType
                    defaultValue = -1L
                })
            ) { backStackEntry ->
                val contactId = backStackEntry.arguments?.getLong("contactId")
                EditScreen(
                    onBackClick = {
                        val previousRoute = navController.previousBackStackEntry?.destination?.route
                        if (previousRoute?.startsWith("info/") == true) {
                            navController.popBackStack()  // удаляем EditScreen
                            navController.popBackStack()  // удаляем старый InfoScreen
                            navController.navigate("info/$contactId")
                        } else {
                            navController.navigate("main") {
                                popUpTo("main") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    },
                    contactId = contactId
                )
            }
            composable("search") {
                SearchScreen(
                    onBackClick = { navController.popBackStack() },
                    onItemClick = { contactId -> navController.navigate("info/$contactId") },
                )
            }
            composable("settings") {
                SettingsScreen(
                    onBackClick = { navController.popBackStack() },
                    isDarkThemeEnabled = darkThemeEnabled,
                    onModeSwitchClick = { darkThemeEnabled = it }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FaceVolumeTheme {
        Greeting("Android")
    }
}