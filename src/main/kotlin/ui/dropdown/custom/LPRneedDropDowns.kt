package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun LPRneedDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Не нуждается в медицинской помощи", "Не нуждается в медицинской помощи"),
        CustomDropDownItem("Нуждается в профилактической стоматологической помощи", "Нуждается в профилактической стоматологической помощи"),
        CustomDropDownItem("Нуждается в терапевтической стоматологической помощи", "Нуждается в терапевтической стоматологической помощи"),
        CustomDropDownItem("Нуждается в хирургической стоматологической помощи", "Нуждается в хирургической стоматологической помощи"),
        CustomDropDownItem("Нуждается в ортодонтической стоматологической помощи", "Нуждается в ортодонтической стоматологической помощи")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}