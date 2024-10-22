package ui.dropdown.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun ByteTypeDropDownMenu(
    onSelect: (String) -> Unit,
    initialValue: String,
    modifier: Modifier = Modifier,
    ageByteType: String
) {
    var isFisByteType by remember {
        mutableStateOf(
            true
        )
    }

    Row {
        Column(Modifier.weight(1f)) {
            AgeByteTypeDropDownMenu(
                {
                },
                "Тип прикуса согласно возрасту",
                ageByteType
            )
        }

        Column(Modifier.weight(1f)) {
            FisByteTypeDropDownMenu({
                isFisByteType = (it == "Физиологический")
            }, "Тип прикуса по физиологичности", "Патологический")//todo
        }

        Column(Modifier.weight(1f)) {
            ToothPlaceByteTypeDropDownMenu(
                onSelect,
                isFisByteType,
                "Тип прикуса по расположения зубов",
                initialValue
            )
        }

        //todo
        Column(Modifier.weight(1f)) {
            ToothAnomaliesDropDownMenu(
                onSelect,
                "Зубочелюстные аномалии",
                "-"
            )
        }
    }
}

@Composable
fun AgeByteTypeDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Временный", "Временный"),
        CustomDropDownItem("Смешанный", "Смешанный"),
        CustomDropDownItem("Постоянный", "Постоянный")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}

@Composable
fun FisByteTypeDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Физиологический", "Физиологический"),
        CustomDropDownItem("Патологический", "Патологический")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}

@Composable
fun ToothPlaceByteTypeDropDownMenu(
    onSelect: (String) -> Unit,
    isPathological: Boolean,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = if (isPathological) {
        listOf(
            CustomDropDownItem("Ортогнатический", "Ортогнатический"),
            CustomDropDownItem("Физиологическая прогнатия", "Физиологическая прогнатия"),
            CustomDropDownItem("Физиологическая прогения", "Физиологическая прогения"),
            CustomDropDownItem("Бипрогнатия", "Бипрогнатия"),
            CustomDropDownItem("Прямой прикус", "Прямой прикус")
        )
    } else {
        listOf(
            CustomDropDownItem("Глубокий прикус", "Глубокий прикус"),
            CustomDropDownItem("Открытый прикус", "Открытый прикус"),
            CustomDropDownItem("Мезиальный прикус", "Мезиальный прикус"),
            CustomDropDownItem("Перекрестный прикус", "Перекрестный прикус"),
            CustomDropDownItem("Дистальный прикус", "Дистальный прикус"),
        )
    }

    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}

@Composable
fun ToothAnomaliesDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("Аномалия положения зубов", "К07.3"),
        CustomDropDownItem("-", "-")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier)
}