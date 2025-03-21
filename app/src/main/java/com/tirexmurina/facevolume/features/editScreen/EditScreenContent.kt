package com.tirexmurina.facevolume.features.editScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tirexmurina.facevolume.R
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenContent
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import com.tirexmurina.facevolume.ui.theme.MainAccentColor
import com.tirexmurina.facevolume.ui.theme.MainBackgroundColor

@Composable
fun EditScreenContent(
    onBackClick:() -> Unit,
    contact : Contact? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        EditScreenToolbar(
            onBackClick = { onBackClick() }
        )
        EditScreenContainer(contact)
    }
}

@Composable
fun EditScreenToolbar(
    onBackClick:() -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { /*TODO тут валидацию стартуем, потом назад если ок все*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "Редактировать"
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenContainer(
    contact: Contact?
) {
    // Обертка для прокрутки
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp) // Общий горизонтальный отступ
    ) {
        // Аватарка: 100х100, по центру, отступ сверху 18dp, кликабельная
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (contact?.pic != null) {
                Image(
                    painter = rememberAsyncImagePainter(contact.pic),
                    contentDescription = "Аватар контакта",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            // TODO: обработка клика по аватарке (например, выбор новой фотографии)
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                    contentDescription = "Placeholder аватара",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            // TODO: обработка клика по аватарке
                        }
                )
            }
        }

        // Поля ввода разделены отступами 32dp сверху
        Spacer(modifier = Modifier.height(32.dp))
        // Имя (обязательно)
        var name by remember { mutableStateOf(contact?.name ?: "") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Имя (обязательно)",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Email
        var email by remember { mutableStateOf(contact?.email ?: "") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Телефон
        var phone by remember { mutableStateOf(contact?.phone ?: "") }
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = {
                Text(
                    text = "Телефон",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Страна
        var country by remember { mutableStateOf(contact?.location?.country ?: "") }
        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = {
                Text(
                    text = "Страна",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Город
        var city by remember { mutableStateOf(contact?.location?.city ?: "") }
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = {
                Text(
                    text = "Город",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Адрес
        var address by remember { mutableStateOf(contact?.location?.address ?: "") }
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = {
                Text(
                    text = "Адрес",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Часовой пояс (выпадающий список)
        var expanded by remember { mutableStateOf(false) }
        val timezones = listOf("MSK", "EST", "PST", "CET", "IST") // TODO
        var selectedTimezone by remember { mutableStateOf(contact?.location?.timezone?.zoneName ?: "") }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedTimezone,
                onValueChange = { selectedTimezone = it },
                label = {
                    Text(
                        text = "Часовой пояс",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                timezones.forEach { tz ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = tz,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            selectedTimezone = tz
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        // Заметки
        var notes by remember { mutableStateOf(contact?.note ?: "") }
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = {
                Text(
                    text = "Заметки",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            singleLine = false,
            minLines = 3
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview() {
    FaceVolumeTheme {
        EditScreenContent(
            { },
            Contact(
                id = 1,
                name = "Клавдия Пульхра",
                email = "pulk_caesar@example.com",
                phone = "+7(933)578-90-12",
                location = Contact.Location(
                    country = "Италия",
                    city = "Рим",
                    address = "Виа делла Ротонда, д. 36",
                    timezone = Contact.Timezone.MSK_0
                )
            )
        )
    }
}