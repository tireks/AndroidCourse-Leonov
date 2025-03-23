package com.tirexmurina.facevolume.features.settingsScreen

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    isDarkThemeEnabled: Boolean,
    onModeSwitchClick: (Boolean) -> Unit
){
    SettingsScreenContent(
        onBackClick = onBackClick,
        isDarkThemeEnabled = isDarkThemeEnabled,
        onModeSwitchClick = onModeSwitchClick
    )
}