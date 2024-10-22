package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun ErosionDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Нет признаков эрозии", "0"),
        CustomDropDownItem("Поражение эмали", "1"),
        CustomDropDownItem("Поражение дентина", "2"),
        CustomDropDownItem("Вовлечение пульпы", "3")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}
