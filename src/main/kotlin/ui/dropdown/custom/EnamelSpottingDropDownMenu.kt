package ui.dropdown.custom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun EnamelSpottingDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Норма", "0"),
        CustomDropDownItem("Ограниченная пятнистость", "1"),
        CustomDropDownItem("Диффузная пятнистость", "2"),
        CustomDropDownItem("Гипоплазия", "3"),
        CustomDropDownItem("Другие дефекты", "4"),
        CustomDropDownItem("Огран. и диф. пятнистость", "5"),
        CustomDropDownItem("Огран. пятнистость и гипоплазия", "6"),
        CustomDropDownItem("Диф. пятнистость и гипоплазия", "7"),
        CustomDropDownItem("Сочетание всех трех типов", "8"),
        CustomDropDownItem("Не регистрируется", "9")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}