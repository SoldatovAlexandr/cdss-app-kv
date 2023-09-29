package screens.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.NormalText
import java.time.LocalDateTime

@Composable
fun CreateGroup(
    viewModel: GroupsViewModel,
    modifier: Modifier
) {
    Column(modifier) {

        var name by remember { mutableStateOf("") }
        var startYear by remember { mutableStateOf(LocalDateTime.now().year.toString()) }

        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                viewModel.setCreatedGroup(false)
            }.padding(8.dp)
        )


        NormalText("Введите наименование группы", Modifier.padding(top = 16.dp))
        TextField(name, onValueChange = { name = it }, Modifier.padding(vertical = 8.dp))

        NormalText("Введите год начала обучения", Modifier.padding(top = 16.dp))

        TextField(
            value = startYear,
            onValueChange = { value ->
                startYear = value.filter { it.isDigit() }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(onClick = {
            viewModel.createGroup(name, startYear)
        }, enabled = name.isNotBlank() && startYear.isNotBlank()) {
            Text("Создать")
        }
    }
}
