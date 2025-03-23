package com.tirexmurina.facevolume.features.editScreen

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import com.tirexmurina.facevolume.features.util.TimezoneDropdown
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import com.tirexmurina.facevolume.ui.theme.MainAccentColor

@Composable
fun EditScreenContent(
    onBackClick:() -> Unit,
    contact : Contact? = null,
    onSave: (Contact) -> Unit
) {
    val formState = remember { EditFormState(contact) }
    var showExitDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        EditScreenToolbar(
            onBackClick = {
                if (formState.unsavedChanges) {
                    showExitDialog = true
                } else {
                    onBackClick()
                }
            },
            onSaveClick = {
                val newContact = formState.save()
                if (newContact != null) {
                    onSave(newContact)
                    onBackClick()
                }
            }
        )
        EditScreenContainer(formState = formState)
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Несохранённые изменения") },
            text = { Text("Введённые изменения не сохранены. Сохранить изменения?") },
            confirmButton = {
                Button(onClick = {
                    val newContact = formState.save()
                    if (newContact != null) {
                        onSave(newContact)
                        showExitDialog = false
                        onBackClick()
                    }
                }) {
                    Text(
                        text = "Сохранить",
                        color = MainAccentColor
                    )
                }
            },
            dismissButton = {
                Button(onClick = {
                    showExitDialog = false
                    onBackClick()
                }) {
                    Text(
                        text = "Не сохранять",
                        color = MainAccentColor
                    )
                }
            }
        )
    }

    BackHandler {
        if (formState.unsavedChanges) {
            showExitDialog = true
        } else {
            onBackClick()
        }
    }
}

@Composable
fun EditScreenToolbar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
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
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Назад",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onSaveClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "Сохранить",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun EditScreenContainer(
    formState: EditFormState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (formState.pic != null) {
                Image(
                    painter = rememberAsyncImagePainter(formState.pic),
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

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.name,
            onValueChange = { formState.name = it },
            label = {
                Text(
                    text = "Имя (обязательно)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            isError = formState.nameError != null,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (formState.nameError != null) {
            Text(
                text = formState.nameError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.email ?: "",
            onValueChange = { formState.email = it },
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.phone ?: "",
            onValueChange = { formState.phone = it },
            label = {
                Text(
                    text = "Телефон",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.country ?: "",
            onValueChange = { formState.country = it },
            label = {
                Text(
                    text = "Страна",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.city ?: "",
            onValueChange = { formState.city = it },
            label = {
                Text(
                    text = "Город",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.address ?: "",
            onValueChange = { formState.address = it },
            label = {
                Text(
                    text = "Адрес",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        var expanded by remember { mutableStateOf(false) }
        val timezones = Contact.Timezone.entries
        TimezoneDropdown(
            timezones = timezones,
            selectedTimezone = formState.selectedTimezone,
            onTimezoneSelected = { formState.selectedTimezone = it },
            expanded = expanded,
            onExpandedChange = { expanded = it},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = formState.notes ?: "",
            onValueChange = { formState.notes = it },
            label = {
                Text(
                    text = "Заметки",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            singleLine = false,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
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
            ),
            { }
        )
    }
}