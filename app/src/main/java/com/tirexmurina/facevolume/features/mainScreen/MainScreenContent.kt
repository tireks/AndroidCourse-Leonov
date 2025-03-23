package com.tirexmurina.facevolume.features.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import com.tirexmurina.facevolume.R
import com.tirexmurina.facevolume.ui.theme.MainAccentColor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.tirexmurina.facevolume.ui.theme.SecondaryBackgroundColor

@Composable
fun MainScreenContent(
    onItemClick: (Long) -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit,
    contactList : List<Contact>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainScreenToolbar(
            onSettingsClick = { onSettingsClick() },
            onSearchClick = { onSearchClick() } )
        Spacer(modifier = Modifier.height(10.dp))
        MainScreenContainer(
            onItemClick = { onItemClick(it)},
            onAddClick = { onAddClick() },
            contactList = contactList
        )
    }
}

@Composable
fun MainScreenContainer(
    onItemClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    contactList: List<Contact>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 0.dp)
        ) {
            items(contactList) { contact ->
                MainScreenContactCard(
                    contact,
                    onItemClick = { onItemClick(it) }
                )
            }
        }
        FloatingActionButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
                .size(48.dp),
            containerColor = MainAccentColor,
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Добавить элемент",
                tint = MainBackgroundColor
            )
        }
    }
}

@Composable
fun MainScreenToolbar(
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { onSearchClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { onSettingsClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
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
fun MainScreenContactCard(
    contact : Contact,
    onItemClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ onItemClick(contact.id) }
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            if (contact.pic != null){
                Image(
                    painter = rememberAsyncImagePainter(contact.pic),
                    contentDescription = "Item avatar",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                    contentDescription = "Item avatar placeholder"
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically),
                text = contact.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun testPreview() {
    FaceVolumeTheme {
        MainScreenContent(
            { },
            { },
            { },
            { },
            listOf(
                Contact(
                    id = 1,
                    name = "Alice Johnson",
                    email = "alice.johnson@example.com",
                    phone = "+1 555 1234",
                    note = "Met at a conference.",
                    pic = "https://example.com/avatar1.png",
                    location = Contact.Location(
                        country = "USA",
                        city = "New York",
                        address = "123 Broadway",
                        timezone = Contact.Timezone.MSK_B13
                    )
                ),
                Contact(
                    id = 2,
                    name = "Bob Williams",
                    email = "bob.williams@example.com",
                    phone = "+1 555 2345",
                    note = null,
                    pic = null,
                    location = Contact.Location(
                        country = "USA",
                        city = "Los Angeles",
                        address = "456 Hollywood Blvd",
                        timezone = null
                    )
                ),
                Contact(
                    id = 3,
                    name = "Catherine Miller",
                    email = null,
                    phone = "+44 20 7946 0958",
                    note = "Important client.",
                    pic = "https://example.com/avatar3.png",
                    location = Contact.Location(
                        country = "UK",
                        city = "London",
                        address = "789 Baker Street",
                        timezone = null
                    )
                ),
                Contact(
                    id = 4,
                    name = "David Brown",
                    email = "david.brown@example.com",
                    phone = null,
                    note = "Follow up next week.",
                    pic = "https://example.com/avatar4.png",
                    location = null
                ),
                Contact(
                    id = 5,
                    name = "Eva Green",
                    email = "eva.green@example.com",
                    phone = "+49 30 123456",
                    note = null,
                    pic = null,
                    location = Contact.Location(
                        country = "Germany",
                        city = "Berlin",
                        address = "Alexanderplatz 1",
                        timezone = Contact.Timezone.MSK_2
                    )
                ),
                Contact(
                    id = 6,
                    name = "Frank Harris",
                    email = null,
                    phone = null,
                    note = "Loyal customer.",
                    pic = "https://example.com/avatar6.png",
                    location = Contact.Location(
                        country = "Canada",
                        city = "Toronto",
                        address = "Queen Street West",
                        timezone = Contact.Timezone.MSK_0
                    )
                ),
                Contact(
                    id = 7,
                    name = "Grace Lee",
                    email = "grace.lee@example.com",
                    phone = "+81 3-1234-5678",
                    note = "Interested in new products.",
                    pic = null,
                    location = Contact.Location(
                        country = "Japan",
                        city = "Tokyo",
                        address = null,
                        timezone = null
                    )
                )
            )
        )
    }
}
