package com.tirexmurina.facevolume.features.infoScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import com.tirexmurina.facevolume.R
import com.tirexmurina.facevolume.ui.theme.MainAccentColor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import com.tirexmurina.facevolume.ui.theme.MainBackgroundColor

@Composable
fun InfoScreenContent(
    onBackClick:() -> Unit,
    onEditClick: (String) -> Unit,
    onDialerClick:() -> Unit,
    onEmailClick: () -> Unit,
    contact: Contact
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        InfoToolbar(
            onBackClick = { onBackClick() },
            onEditClick = { onEditClick(it) }
        )
        InfoScreenContainer(
            onDialerClick = { onDialerClick() },
            onEmailClick = { onEmailClick() },
            contact
        )
    }
}

@Composable
fun InfoToolbar(
    onBackClick: () -> Unit,
    onEditClick: (String) -> Unit
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
                onClick = { /*onEditClick()*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
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

@Composable
fun InfoScreenContainer(
    onDialerClick:() -> Unit,
    onEmailClick: () -> Unit,
    contact: Contact
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            /*Image(
                painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                contentDescription = "Аватар контакта",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )*/
            if (contact.pic != null){
                Image(
                    painter = rememberAsyncImagePainter(contact.pic),
                    contentDescription = "Item avatar",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                    contentDescription = "Item avatar placeholder",
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }

        // Имя контакта
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Блок Email
        if (contact.email != null){
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(start = 18.dp)) {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = contact.email,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable { onEmailClick() }
                )
            }
        }

        // Блок Телефон
        if (contact.phone != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(start = 18.dp)) {
                Text(
                    text = "Телефон",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = contact.phone,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable { onDialerClick() }
                )
            }
        }
        // Блок Локация
        if (contact.location != null){
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(start = 18.dp)) {
                Text(
                    text = "Локация",
                    style = MaterialTheme.typography.bodySmall
                )
                if (contact.location.country != null){
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contact.location.country,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (contact.location.city != null){
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contact.location.city,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (contact.location.address != null){
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contact.location.address,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (contact.location.timezone != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contact.location.timezone.zoneName,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Блок Заметки
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
            Text(
                text = "Заметки",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            var notes by remember { mutableStateOf(contact.note ?: "") }
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 150.dp),
                placeholder = {
                    Text(
                        "Введите заметки...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                singleLine = false,
                minLines = 3,
                maxLines = Int.MAX_VALUE
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview() {
    FaceVolumeTheme {
        InfoScreenContent(
            { },
            { },
            { },
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