package screens.cadet

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.UiCheckup

@Composable
fun CheckupItem(checkup: UiCheckup, modifier: Modifier) {
    Row(modifier) {
        Text(checkup.date, Modifier.weight(1f), maxLines = 1)
        Text(checkup.type, Modifier.weight(1f), maxLines = 1)
    }
}
