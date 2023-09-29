package screens.group

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.UiCadet

@Composable
fun CadetItem(cadet: UiCadet, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(cadet.lastName, Modifier.weight(1f), maxLines = 1)
        Text(cadet.firstName, Modifier.weight(1f), maxLines = 1)
        Text(cadet.patronymic, Modifier.weight(1f), maxLines = 1)
        Text(cadet.dateOfBirthday, Modifier.weight(1f), maxLines = 1)
    }
}
