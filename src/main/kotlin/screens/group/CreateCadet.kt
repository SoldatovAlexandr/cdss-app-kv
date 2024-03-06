package screens.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
                Row {
                    OutlinedTextField(
                        lastName,
                        onValueChange = { lastName = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Фамилия") },
                    )
                }
            }

            item {
                Row {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Имя") },
                    )
                }
            }

            item {
                Row {
                    OutlinedTextField(
                        value = patronymic,
                        onValueChange = { patronymic = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Отчество") },
                    )
                }
            }

            item {
                Row {
                    OutlinedTextField(
                        value = dateOfBirthday,
                        onValueChange = { dateOfBirthday = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Дата рождения") },
                    )
                }
            }
            item {
                Row {
                    OutlinedTextField(
                        value = ethnicGroup,
                        onValueChange = { ethnicGroup = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Этническая группа") },
                    )
                }
            }

            item {
                Row {
                    OutlinedTextField(
                        value = placeOfBirthday,
                        onValueChange = { placeOfBirthday = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Место (населенный пункт) рождения") },
                    )
                }
            }

            item {
                Row {
                    OutlinedTextField(
                        value = previousPlaceOfLiving,
                        onValueChange = { previousPlaceOfLiving = it },
                        Modifier.padding(vertical = 8.dp).weight(1f),
                        label = { Text("Последнее место жительства") },
                    )
                }
            }

            item {
                Row {
                    Button(
                        onClick = {
                            viewModel.createCadet(
                                group.id,
                                firstName,
                                lastName,
                                patronymic,
                                dateOfBirthday,
                                ethnicGroup,
                                placeOfBirthday,
                                previousPlaceOfLiving
                            )
                        },
                        enabled = isValid(),
                        modifier = Modifier.padding(vertical = 8.dp).weight(1f),
                ) {
                    Text("Создать")
                }
            }
            }
        }
    }
}
