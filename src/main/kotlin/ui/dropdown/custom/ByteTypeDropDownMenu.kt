package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun ByteTypeDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Ортогнатический", "ORTHOGNATHIC"),
        CustomDropDownItem("Глубокий", "DEEP"),
        CustomDropDownItem("Дистальный", "DISTAL"),
        CustomDropDownItem("Мезиальный", "MESIAL"),
        CustomDropDownItem("Перекрестный", "CROSS"),
        CustomDropDownItem("Прямой", "STRAIGHT"),
        CustomDropDownItem("Открытый", "OPEN"),
        CustomDropDownItem("Тортоаномалия", "TORTOANOMALY"),
        CustomDropDownItem("Скученность", "CROWDING"),
        CustomDropDownItem("Диастема/Тремы", "DIASTIMA")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}
