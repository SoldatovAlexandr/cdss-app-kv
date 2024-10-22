package screens.group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.model.UiGroup
import repository.CheckupRepository
import screens.cadet.CadetView
import screens.cadet.CadetViewModel
import ui.BigText

@Composable
fun GroupView(
    viewModel: GroupViewModel,
    group: UiGroup,
    onClose: () -> Unit,
    modifier: Modifier
) {

    Column {
        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                onClose()
            }.padding(8.dp)
        )

        BigText(group.name)
        Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

        val isCreateCadet by viewModel.isCreateCadet.collectAsState()
        val selectedCadet by viewModel.selectedCadet.collectAsState()

        if (isCreateCadet) {
            CreateCadet(group, viewModel, modifier)
        } else if (selectedCadet.data == null) {
            CadetTable(viewModel, group, modifier)
        } else {
            CadetView(
                CadetViewModel(CheckupRepository),
                { viewModel.setSelectCadet(null) },
                modifier,
                selectedCadet.data!!
            )
        }
    }
}
