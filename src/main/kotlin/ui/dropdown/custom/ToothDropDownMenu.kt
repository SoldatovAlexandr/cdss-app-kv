package ui.dropdown.custom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun ToothDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    isTemp: Boolean = false,
    modifier: Modifier = Modifier
) {
    val suggestions = if (isTemp) {
        listOf(
            CustomDropDownItem("Интактный", "0"),
            CustomDropDownItem("Интактный (Временный)", "A"),
            CustomDropDownItem("Кариес", "1"),
            CustomDropDownItem("Кариес (Временный)", "B"),
            CustomDropDownItem("Пломба, с кариесом", "2"),
            CustomDropDownItem("Пломба, с кариесом (Временный)", "C"),
            CustomDropDownItem("Пломба, без кариеса", "3"),
            CustomDropDownItem("Пломба, без кариеса (Временный)", "D"),
            CustomDropDownItem("Удаление из-за осложнений кариеса", "4"),
            CustomDropDownItem("Удаление из-за осложнений кариеса (Временный)", "E"),
            CustomDropDownItem("Удаление по другим причинам", "5"),
            CustomDropDownItem("Герметизированная фиссура", "6"),
            CustomDropDownItem("Герметизированная фиссура (Временный)", "F"),
            CustomDropDownItem("Опорный зуб мостовидн. протез/коронка, винир", "7"),
            CustomDropDownItem("Опорный зуб мостовидн. протез/коронка, винир (Временный)", "G"),
            CustomDropDownItem("Не прорезавшийся зуб", "8"),
            CustomDropDownItem("Не регистрируется", "9")
        )
    } else {
        listOf(
            CustomDropDownItem("Интактный", "0"),
            CustomDropDownItem("Кариес", "1"),
            CustomDropDownItem("Пломба, с кариесом", "2"),
            CustomDropDownItem("Пломба, без кариеса", "3"),
            CustomDropDownItem("Удаление из-за осложнений кариеса", "4"),
            CustomDropDownItem("Удаление по другим причинам", "5"),
            CustomDropDownItem("Герметизированная фиссура", "6"),
            CustomDropDownItem("Опорный зуб мостовидн. протез/коронка, винир", "7"),
            CustomDropDownItem("Не прорезавшийся зуб", "8"),
            CustomDropDownItem("Не регистрируется", "9")
        )
    }

    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}
