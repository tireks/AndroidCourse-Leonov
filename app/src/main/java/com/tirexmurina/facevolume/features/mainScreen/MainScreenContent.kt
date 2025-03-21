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
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        MainScreenToolbar(
            onSettingsClick = { onSettingsClick() },
            onSearchClick = { onSearchClick() } )
        Spacer(modifier = Modifier.height(10.dp))
        MainScreenContainer(
            onItemClick = { onItemClick(it)},
            onAddClick = { onAddClick() }
        )
    }
}

@Composable
fun MainScreenContainer(
    onItemClick: (Long) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 0.dp)
        ) {
            // Например, выводим 20 элементов
            items(11) { index ->
                MainScreenContactCard(
                    itemName = "Элемент $index",
                    null,
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
                    contentDescription = "Search"
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { onSettingsClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings"
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
fun MainScreenContactCard(
    /*contact : Contact*/
    itemName: String,
    itemPic: String?,
    onItemClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            /*.clickable{ onItemClick(contact.id) }*/
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SecondaryBackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            if (itemPic != null){
                Image(
                    painter = rememberAsyncImagePainter(itemPic),
                    contentDescription = "Item avatar",
                    contentScale = ContentScale.FillHeight
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
                text = itemName,
                style = MaterialTheme.typography.bodyMedium
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
            { }
        )
    }
}
