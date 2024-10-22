package ui.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun CustomDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    suggestions: List<CustomDropDownItem>,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(initialValue) }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column/*(modifier)*/ {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { value -> selectedText = value.filter { false } },
            readOnly = true,
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded }.size(20.dp))
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedText = item.value
                    onSelect(item.value)
                    expanded = false
                }) {
                    Text(text = item.label)
                }
            }
        }
    }
}

data class CustomDropDownItem(val label: String, val value: String)
