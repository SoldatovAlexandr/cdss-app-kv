package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun TraumaDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Нет повреждения", "0"),
        CustomDropDownItem("Леченное повреждение", "1"),
        CustomDropDownItem("Скол эмали", "2"),
        CustomDropDownItem("Скол эмали и дентина", "3"),
        CustomDropDownItem("Вовлечение пульпы", "4"),
        CustomDropDownItem("Зуб удаленный вследствие травмы", "5"),
        CustomDropDownItem("Другое поражение", "6"),
        CustomDropDownItem("Исключенный зуб", "9")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}
