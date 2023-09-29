package screens.cadet

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
import data.model.UiCadet

@Composable
fun CheckupsTable(
    viewModel: CadetViewModel,
    cadet: UiCadet,
    modifier: Modifier
) {

    val checkups by viewModel.checkups.collectAsState()

    if (checkups.data == null) {
        viewModel.getCheckupsByCadetId(cadet.id)
    }

    Column {
        Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

        Button({}) {
            Text(
                "Новый осмотр",
                modifier = Modifier.clickable { viewModel.setCreatedCheckout(true) })
        }

        LazyColumn(modifier.fillMaxWidth()) {
            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }
            item {
                Row(Modifier.fillMaxWidth()) {
                    Text("Дата осмотра", Modifier.weight(1f), maxLines = 1)
                }
            }

            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
            }

            items(checkups.data.orEmpty()) {
                CheckupItem(it, Modifier.fillMaxWidth().clickable {
                    /*viewModel.setSelectCadet(it)*/
                })
            }
        }
    }
}
