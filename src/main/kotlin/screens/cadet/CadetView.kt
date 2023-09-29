package screens.cadet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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

@Composable
fun CadetView(
    viewModel: CadetViewModel,
    onClose: () -> Unit,
    modifier: Modifier,
    cadet: UiCadet
) {
    Column {
        val isCreateCheckout by viewModel.isCreateCheckout.collectAsState()

        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                onClose()
            }.padding(8.dp)
        )

        BigText("${cadet.lastName}  ${cadet.firstName}")

        if (isCreateCheckout) {
            CreateCheckout(cadet, viewModel, modifier)
        } else {
            CheckupsTable(viewModel, cadet, modifier)
        }
    }
}
