package screens.groups

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

@Composable
fun GroupTable(
    viewModel: GroupsViewModel,
    modifier: Modifier
) {

    val groups by viewModel.groups.collectAsState()

    if (groups.data == null) {
        viewModel.getGroups()
    }

    Column {
        Button({}) {
            Text(
                "Добавить группу",
                modifier = Modifier.clickable { viewModel.setCreatedGroup(true) })
        }

        LazyColumn(modifier.fillMaxWidth()) {
            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }
            item {
                Row(Modifier.fillMaxWidth()) {
                    Text("Название", Modifier.weight(1f), maxLines = 1)
                    Text("Год старта обучения", Modifier.weight(1f), maxLines = 1)
                }
            }

            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }

            items(groups.data.orEmpty()) {
                GroupItem(it, Modifier.fillMaxWidth().clickable {
                    viewModel.selectGroup(it)
                })
            }
        }
    }
}
