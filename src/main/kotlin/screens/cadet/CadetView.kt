package screens.cadet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.model.UiCadet
import ui.BigText
import ui.NormalText

@Composable
fun CadetView(
    viewModel: CadetViewModel,
    onClose: () -> Unit,
    modifier: Modifier,
    cadet: UiCadet
) {
    Column {
        val isCreateCheckout by viewModel.isCreateCheckout.collectAsState()
        viewModel.findLastByCadetId(cadet.id)

        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                onClose()
            }.padding(8.dp)
        )

        Row {
            BigText("${cadet.lastName}  ${cadet.firstName}  ${cadet.patronymic}", Modifier.weight(1f))
        }

        if (isCreateCheckout) {
            CreateCheckout(cadet, viewModel, modifier)
        } else {
            CheckupsTable(viewModel, cadet, modifier)
        }
    }
}
