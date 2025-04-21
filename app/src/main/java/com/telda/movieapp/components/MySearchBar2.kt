package com.telda.movieapp.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MySearchBar2(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit = {},
    placeholderText: String = "Search for movie",
    content: @Composable ColumnScope.() -> Unit,
) {

    var active by remember { mutableStateOf(query.isNotEmpty()) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { active = false },
                expanded = active,
                onExpandedChange = { active = it },
                placeholder = {
                    MyText(
                        text = placeholderText,
                    )
                },
                leadingIcon = {
                    IconButton(
                        onClick = { onSearch.invoke(query) }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (query.isNotEmpty()) onQueryChange.invoke("")
                            else active = false
                        }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                },
                colors = SearchBarDefaults.inputFieldColors(
                    cursorColor = MaterialTheme.colorScheme.onBackground,
                ),
            )
        },
        expanded = active,
        onExpandedChange = { active = it },
        modifier = modifier,
        colors = colors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        content = content,
    )

}
