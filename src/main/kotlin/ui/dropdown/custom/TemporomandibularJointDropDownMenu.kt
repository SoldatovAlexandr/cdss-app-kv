package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun TemporomandibularJointDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Отсутствие", "0"),
        CustomDropDownItem("Проявление", "1"),
        CustomDropDownItem("Не регистрируется", "9")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}
