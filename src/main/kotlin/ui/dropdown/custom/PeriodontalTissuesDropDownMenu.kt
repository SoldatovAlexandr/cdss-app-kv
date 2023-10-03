package ui.dropdown.custom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun PeriodontalTissuesDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Отсутствие кровоточивости", "0"),
        CustomDropDownItem("Наличие кровоточивости", "1"),
        CustomDropDownItem("Зубной камень", "2"),
        CustomDropDownItem("Карман 4-5 мм", "3"),
        CustomDropDownItem("Карман 6 мм и более", "4"),
        CustomDropDownItem("Зуб исключен", "9"),
        CustomDropDownItem("Зуб отсутствует", "X")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}
