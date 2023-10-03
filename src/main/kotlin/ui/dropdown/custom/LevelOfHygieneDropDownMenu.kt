package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun LevelOfHygieneDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Хороший", "0"),
        CustomDropDownItem("Удовлетворительный", "1"),
        CustomDropDownItem("Неудовлетворительный", "2"),
        CustomDropDownItem("Плохой", "3")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}