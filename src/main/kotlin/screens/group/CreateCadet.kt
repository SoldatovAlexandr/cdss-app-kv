package screens.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.model.UiGroup
import ui.NormalText

@Composable
fun CreateCadet(
    group: UiGroup, viewModel: GroupViewModel, modifier: Modifier
) {

    Column {

        var byteTypes = arrayOf(
            "Ортогнатический",
            "Глубокий",
            "Дистальный",
            "Мезиальный",
            "Перекрестный",
            "Прямой",
            "Открытый",
            "Тортоаномалия",
            "Скученность",
            "Диастема/Тремы"
        )

        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var patronymic by remember { mutableStateOf("") }
        var dateOfBirthday by remember { mutableStateOf("") }
        var ethnicGroup by remember { mutableStateOf("") }
        var placeOfBirthday by remember { mutableStateOf("") }
        var previousPlaceOfLiving by remember { mutableStateOf("") }
        var byteType by remember { mutableStateOf(byteTypes[0]) }
        var healthGroup by remember { mutableStateOf("|") }

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
                NormalText("Введите имя кадета", Modifier.padding(top = 16.dp))
                TextField(firstName, onValueChange = { firstName = it }, Modifier.padding(vertical = 8.dp))
            }

            item {
                NormalText("Введите фамилию кадета", Modifier.padding(top = 16.dp))
                TextField(lastName, onValueChange = { lastName = it }, Modifier.padding(vertical = 8.dp))

            }
            item {
                NormalText("Введите отчество кадета", Modifier.padding(top = 16.dp))
                TextField(patronymic, onValueChange = { patronymic = it }, Modifier.padding(vertical = 8.dp))
            }

            item {
                NormalText("Введите дату рождения кадета", Modifier.padding(top = 16.dp))
                TextField(
                    dateOfBirthday, onValueChange = { dateOfBirthday = it }, Modifier.padding(vertical = 8.dp)
                )
            }
            item {
                NormalText("Введите этническую группу кадета", Modifier.padding(top = 16.dp))
                TextField(ethnicGroup, onValueChange = { ethnicGroup = it }, Modifier.padding(vertical = 8.dp))
            }

            item {
                NormalText("Введите город рождения кадета", Modifier.padding(top = 16.dp))
                TextField(placeOfBirthday, onValueChange = { placeOfBirthday = it }, Modifier.padding(vertical = 8.dp))
            }

            item {
                NormalText("Введите предыдущее место жительства кадета", Modifier.padding(top = 16.dp))
                TextField(
                    previousPlaceOfLiving,
                    onValueChange = { previousPlaceOfLiving = it },
                    Modifier.padding(vertical = 8.dp)
                )
            }

            item {

                var expanded by remember {
                    mutableStateOf(false)
                }

                NormalText("Выберите прикус кадета", Modifier.padding(top = 16.dp))
                TextField(
                    byteType, onValueChange = { byteType = it }, Modifier.padding(vertical = 8.dp)
                )
            }


            item {
                NormalText("Выберите группу здоровья кадета", Modifier.padding(top = 16.dp))
                TextField(
                    healthGroup, onValueChange = { healthGroup = it }, Modifier.padding(vertical = 8.dp)
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
                }, enabled = isValid()) {
                    Text("Создать")
                }
            }
        }
    }
}

