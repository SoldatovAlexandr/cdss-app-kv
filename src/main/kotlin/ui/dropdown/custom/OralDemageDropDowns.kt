package ui.dropdown.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun OralDamageStateDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Нет поражения", "0"),
        CustomDropDownItem("Стоматит (афтозный, герпетический, травматический)", "1"),
        CustomDropDownItem("Острый язвенно-некротический гингивит (ANUG)", "2"),
        CustomDropDownItem("Кандидоз", "3"),
        CustomDropDownItem("Абсцесс", "4"),
        CustomDropDownItem("Другое поражение", "8"),
        CustomDropDownItem("Не регистрируется", "9")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}

@Composable
fun OralDamageLocaleDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Красная кайма губ", "0"),
        CustomDropDownItem("Переходные складки", "1"),
        CustomDropDownItem("Губы", "2"),
        CustomDropDownItem("Уздечки губ", "3"),
        CustomDropDownItem("Щеки", "4"),
        CustomDropDownItem("Дно полости рта", "5"),
        CustomDropDownItem("Язык", "6"),
        CustomDropDownItem("Твердое и/или мягкое небо", "7"),
        CustomDropDownItem("Альвеолярные гребни/десна", "8")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}
