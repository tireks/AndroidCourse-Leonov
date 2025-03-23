package com.tirexmurina.facevolume.features.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tirexmurina.facevolume.R
import com.tirexmurina.facevolume.shared.domain.entity.Contact

@Composable
fun CustomSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE0E0E0),
    cornerRadius: Dp = 16.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
            .padding(contentPadding),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (query.isEmpty()) {
                        Text(
                            text = "Поиск",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onClear()

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = "Очистить",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimezoneDropdown(
    timezones: List<Contact.Timezone>,
    selectedTimezone: Contact.Timezone?,
    onTimezoneSelected: (Contact.Timezone) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedTimezone?.zoneName ?: "",
            onValueChange = { /* readOnly */ },
            readOnly = true,
            label = {
                Text(
                    text = "Часовой пояс",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .focusProperties { canFocus = false }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            timezones.forEach { tz ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = tz.zoneName,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onTimezoneSelected(tz)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}
