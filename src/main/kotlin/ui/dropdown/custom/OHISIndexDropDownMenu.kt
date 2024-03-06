package ui.dropdown.custom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dropdown.CustomDropDownItem
import ui.dropdown.CustomDropDownMenu

@Composable
fun OHISPlaqueIndexDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("«0» - Зубной налет не выявлен", "0"),
        CustomDropDownItem("«1» - Мягкий зубной налет, покрывающий не более 1/3 поверхности зуба, или наличие любого количества окрашенных отложений", "1"),
        CustomDropDownItem("«2» - Мягкий зубной налет, покрывающий более 1/3, но менее 2/3 поверхности зуба", "2"),
        CustomDropDownItem("«3» - Мягкий зубной налет, покрывающий более 2/3 поверхности зуба", "3")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}

@Composable
fun OHISStoneIndexDropDownMenu(
    onSelect: (String) -> Unit,
    label: String,
    initialValue: String,
    modifier: Modifier = Modifier
) {
    val suggestions = listOf(
        CustomDropDownItem("«0» - Зубной камень не выявлен", "0"),
        CustomDropDownItem("«1» - Наддесневой зубной камень, покрывающий не более 1/3 поверхности зуба", "1"),
        CustomDropDownItem("«2» - Наддесневой зубной камень, покрывающий более 1/3, но менее 2/3 поверхности зуба, или наличие отдельных отложений поддесневого зубного камня в пришеечной области зуба", "2"),
        CustomDropDownItem("«3» - наддесневой зубной камень, покрывающий более 2/3 поверхности зуба, или значительные отложения поддесневого камня вокруг пришеечной области зуба", "3")
    )
    CustomDropDownMenu(onSelect, label, initialValue, suggestions, modifier.size(80.dp))
}