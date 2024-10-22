package screens.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime

@Composable
fun CreateGroup(
    viewModel: GroupsViewModel,
    modifier: Modifier
) {
    Column(modifier.padding(20.dp)) {

        var name by remember { mutableStateOf("") }
        var startYear by remember { mutableStateOf(LocalDateTime.now().year.toString()) }

        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                viewModel.setCreatedGroup(false)
            }.padding(8.dp)
        )

        Column {
            Row {
                OutlinedTextField(
                    name,
                    onValueChange = { name = it },
                    Modifier.padding(vertical = 8.dp).weight(1f),
                    label = { Text("Наименование группы (класс, рота, взвод)") }
                )
            }

            Row {
                OutlinedTextField(
                    value = startYear,
                    onValueChange = { value ->
                        startYear = value.filter { it.isDigit() }
                    },
                    modifier = Modifier.padding(vertical = 8.dp).weight(1f),
                    label = { Text("Год начала обучения") }
                )
            }

            Row {
                Button(onClick = {
                    viewModel.createGroup(name, startYear)
                }, enabled = name.isNotBlank() && startYear.isNotBlank(),
                    modifier = Modifier.padding(vertical = 8.dp).weight(1f)
                ) {
                    Text("Создать")
                }
            }
        }
    }
}
