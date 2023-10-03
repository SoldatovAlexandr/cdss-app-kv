package screens.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.model.UiGroup
import ui.dropdown.DropDownItem
import ui.dropdown.DropDownMenu

@Composable
fun CreateCadet(
    group: UiGroup, viewModel: GroupViewModel, modifier: Modifier
) {

    Column(Modifier.padding(20.dp)) {

        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var patronymic by remember { mutableStateOf("") }
        var dateOfBirthday by remember { mutableStateOf("") }
        var ethnicGroup by remember { mutableStateOf("") }
        var placeOfBirthday by remember { mutableStateOf("") }
        var previousPlaceOfLiving by remember { mutableStateOf("") }
        var byteType by remember { mutableStateOf("") }
        var healthGroup by remember { mutableStateOf("") }

        fun isValid(): Boolean {
            return firstName.isNotBlank() && lastName.isNotBlank() && patronymic.isNotBlank() && dateOfBirthday.isNotBlank()
        }

        Icon(
            painterResource("img/ic_close.svg"), "", Modifier.align(Alignment.End).clickable {
                viewModel.setCreatedCadet(false)
            }.padding(8.dp)
        )

        LazyColumn(modifier.fillMaxWidth()) {

            item {
                OutlinedTextField(
                    lastName,
                    onValueChange = { lastName = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Фамилия") },
                )
            }

            item {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Имя") },
                )
            }

            item {
                OutlinedTextField(
                    value = patronymic,
                    onValueChange = { patronymic = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Отчество") },
                )
            }

            item {
                //todo
                OutlinedTextField(
                    value = dateOfBirthday,
                    onValueChange = { dateOfBirthday = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Дата рождения") },
                )
            }
            item {
                OutlinedTextField(
                    value = ethnicGroup,
                    onValueChange = { ethnicGroup = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Этническая группа") },
                )
            }

            item {
                OutlinedTextField(
                    value = placeOfBirthday,
                    onValueChange = { placeOfBirthday = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Город рождения") },
                )
            }

            item {
                OutlinedTextField(
                    value = previousPlaceOfLiving,
                    onValueChange = { previousPlaceOfLiving = it },
                    Modifier.padding(vertical = 8.dp),
                    label = { Text("Предыдущее место жительства") },
                )
            }

            item {
                val byteTypes = listOf(
                    DropDownItem("Ортогнатический", "ORTHOGNATHIC"),
                    DropDownItem("Глубокий", "DEEP"),
                    DropDownItem("Дистальный", "DISTAL"),
                    DropDownItem("Мезиальный", "MESIAL"),
                    DropDownItem("Перекрестный", "CROSS"),
                    DropDownItem("Прямой", "STRAIGHT"),
                    DropDownItem("Открытый", "OPEN"),
                    DropDownItem("Тортоаномалия", "TORTOANOMALY"),
                    DropDownItem("Скученность", "CROWDING"),
                    DropDownItem("Диастема/Тремы", "DIASTIMA")
                )
                DropDownMenu(
                    byteTypes,
                    { v -> byteType = v },
                    "Выберите прикус кадета",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                val healthGroups = listOf(
                    DropDownItem("1", "1"),
                    DropDownItem("2", "2"),
                    DropDownItem("3", "3")
                )
                DropDownMenu(
                    healthGroups,
                    { v -> healthGroup = v },
                    "Выберите группу здоровья кадета",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                Button(onClick = {
                    viewModel.createCadet(
                        group.id,
                        firstName,
                        lastName,
                        patronymic,
                        dateOfBirthday,
                        ethnicGroup,
                        placeOfBirthday,
                        previousPlaceOfLiving,
                        byteType,
                        healthGroup
                    )
                }, enabled = isValid(),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("Создать")
                }
            }
        }
    }
}
