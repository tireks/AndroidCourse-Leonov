package com.tirexmurina.facevolume.features.settingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tirexmurina.facevolume.R
import com.tirexmurina.facevolume.features.editScreen.EditScreenContainer
import com.tirexmurina.facevolume.features.editScreen.EditScreenContent
import com.tirexmurina.facevolume.features.editScreen.EditScreenToolbar
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import com.tirexmurina.facevolume.ui.theme.MainAccentColor
import com.tirexmurina.facevolume.ui.theme.MainBackgroundColor

@Composable
fun SettingsScreenContent(
    onBackClick:() -> Unit,
    onModeSwitchClick:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        SettingsScreenToolbar(
            onBackClick = { onBackClick() }
        )
        SettingsScreenContainer(
            onModeSwitchClick = { onModeSwitchClick() }
        )
    }
}

@Composable
fun SettingsScreenToolbar(
    onBackClick:() -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { onBackClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Назад"
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MainAccentColor
        )
    }
}

@Composable
fun SettingsScreenContainer(
    onModeSwitchClick: () -> Unit
) {
    // Локальное состояние для переключателя, можно заменить на состояние из ViewModel
    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 18.dp, end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Темная тема",
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = isDarkThemeEnabled,
            onCheckedChange = { newValue ->
                isDarkThemeEnabled = newValue
                onModeSwitchClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview() {
    FaceVolumeTheme {
        SettingsScreenContent(
            {  },
            {  }
        )
    }
}