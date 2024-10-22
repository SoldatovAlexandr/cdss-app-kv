package screens.groups

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.UiGroup

@Composable
fun GroupItem(group: UiGroup, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(group.name, Modifier.weight(1f), maxLines = 1)
        Text(group.startYear.toString(), Modifier.weight(1f), maxLines = 1)
    }
}
