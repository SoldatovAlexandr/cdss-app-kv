package ui.dropdown.custom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun AttachmentLossDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Потеря прикрепления не более 3 мм", "0"),
        CustomDropDownItem("Потеря прикрепления 4-5 мм", "1"),
        CustomDropDownItem("Потеря прикрепления 6-8 мм", "2"),
        CustomDropDownItem("Потеря прикрепления 9-11 мм", "3"),
        CustomDropDownItem("Потеря прикрепления 12 мм и более", "4"),
        CustomDropDownItem("Не регистрируется", "9"),
        CustomDropDownItem("Исключенный если в секстанте меньше 2 зубов", "X")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}
