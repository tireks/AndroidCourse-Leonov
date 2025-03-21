package com.tirexmurina.facevolume.features.searchScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
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
import com.tirexmurina.facevolume.features.editScreen.EditScreenContent
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenContent
import com.tirexmurina.facevolume.features.util.CustomSearchField
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.ui.theme.FaceVolumeTheme
import com.tirexmurina.facevolume.ui.theme.MainAccentColor
import com.tirexmurina.facevolume.ui.theme.MainBackgroundColor
import com.tirexmurina.facevolume.ui.theme.SecondaryBackgroundColor

@Composable
fun SearchScreenContent(
    onItemClick: (Long) -> Unit,
    onBackClick:() -> Unit,
    onQueryChange: (String) -> Unit,
    contacts: List<Contact>
) {
    var searchQuery by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        SearchScreenToolbar(
            searchQuery = searchQuery,
            onQueryChange = { onQueryChange(it) },
            onBackClick = { onBackClick() }
        )
        SearchScreenContainer(
            contacts = contacts,
            onItemClick = { onItemClick(it) }
        )
    }
}

@Composable
fun SearchScreenToolbar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Назад" отдельно от SearchBar
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Назад"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            CustomSearchField(
                query = searchQuery,
                onQueryChange = { onQueryChange(it) },
                onClear = { /*TODO*/ }
            )
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
fun SearchScreenContainer(
    contacts: List<Contact>,
    onItemClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(contacts) { contact ->
            SearchContactCard(
                contact = contact,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun SearchContactCard(
    contact: Contact,
    onItemClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(contact.id) }
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SecondaryBackgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Верхняя строка: аватар и имя
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                if (contact.pic != null) {
                    Image(
                        painter = rememberAsyncImagePainter(contact.pic),
                        contentDescription = "Аватар контакта",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                        contentDescription = "Placeholder аватара",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    // Блок с именем фиксированной высоты, чтобы оно центрировалось по аватарке
                    Box(modifier = Modifier.height(48.dp)) {
                        Text(
                            text = contact.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    // Дополнительные поля – под именем
                    if (contact.email != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = contact.email,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (contact.phone != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = contact.phone,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    contact.location?.let { location ->
                        if (location.country != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = location.country,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (location.city != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = location.city,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (location.address != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = location.address,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (location.timezone != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = location.timezone.zoneName,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    if (contact.note != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = contact.note,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview() {
    FaceVolumeTheme {
        SearchScreenContent(
            onItemClick = { },
            onBackClick = { },
            onQueryChange = { },
            contacts = listOf(
                Contact(
                    id = 1,
                    name = "Туллий Цицерон"
                ),
                Contact(
                    id = 2,
                    name = "Имя Фамилия",
                    location = Contact.Location(city = "Тула")
                ),
                Contact(
                    id = 3,
                    name = "Имениум Фамилиум",
                    email = "tulla_u@example.com",
                    location = Contact.Location(country = "Тульвания")
                ),
                Contact(
                    id = 4,
                    name = "Им Фам",
                    location = Contact.Location(
                        country = "Тульвания",
                        city = "Тульград"),
                    note = "Строка заметки 1. \n" +
                            "Строка заметки 2 \n" +
                            "Тулиум ту тулиум\n" +
                            "Строка заметки 4. \n" +
                            "Пум пурум."
                ),
            )
        )
    }
}