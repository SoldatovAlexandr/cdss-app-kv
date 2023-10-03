package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun FluoroseDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Норма", "0"),
        CustomDropDownItem("Сомнительный", "1"),
        CustomDropDownItem("Очень слабый", "2"),
        CustomDropDownItem("Слабый", "3"),
        CustomDropDownItem("Умеренный", "4"),
        CustomDropDownItem("Тяжелый", "5"),
        CustomDropDownItem("Исключенный (коронка, пломба, брекет)", "8"),
        CustomDropDownItem("Не регистрируется", "9"),
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}