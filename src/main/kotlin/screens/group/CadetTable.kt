package screens.group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.UiGroup

@Composable
fun CadetTable(
    viewModel: GroupViewModel,
    group: UiGroup,
    modifier: Modifier
) {

    val cadets by viewModel.cadets.collectAsState()

    //TODO спросить как правильно это должно происходить
    if (cadets.data == null) {
        viewModel.getCadetsByGroupId(group.id)
    }

    Column {
        Button({}) {
            Text(
                "Добавить кадета",
                modifier = Modifier.clickable { viewModel.setCreatedCadet(true) })
        }

        LazyColumn(modifier.fillMaxWidth()) {
            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }
            item {
                Row(Modifier.fillMaxWidth()) {
                    Text("Фамилия", Modifier.weight(1f), maxLines = 1)
                    Text("Имя", Modifier.weight(1f), maxLines = 1)
                    Text("Отчество", Modifier.weight(1f), maxLines = 1)
                    Text("Дата рождения", Modifier.weight(1f), maxLines = 1)
                }
            }

            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }

            items(cadets.data.orEmpty()) {
                CadetItem(it, Modifier.fillMaxWidth().clickable {
                    viewModel.setSelectCadet(it)
                })
            }
        }
    }
}
