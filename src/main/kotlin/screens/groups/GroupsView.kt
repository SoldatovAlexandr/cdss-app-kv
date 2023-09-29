package screens.groups

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import repository.CadetRepository
import screens.group.GroupViewModel
import screens.group.GroupView

@Composable
fun GroupsView(
    viewModel: GroupsViewModel,
    modifier: Modifier = Modifier
) {
    val createdGroup by viewModel.isCreateGroup.collectAsState()
    val selectedGroup by viewModel.selectedGroup.collectAsState()

    Surface(modifier.fillMaxSize()) {
        if (createdGroup) {
            CreateGroup(viewModel, modifier)
        } else if (selectedGroup.data == null) {
            GroupTable(viewModel, modifier)
        } else {
            GroupView(
                GroupViewModel(CadetRepository),
                selectedGroup.data!!,
                { viewModel.selectGroup(null) },
                modifier
            )
        }
    }
}
