package screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import screens.about.AboutView
import screens.groups.GroupsView
import screens.groups.GroupsViewModel

@Composable
fun MainView(viewModel: MainViewModel) {

    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {

        Row(Modifier.fillMaxWidth().shadow(8.dp).background(Color.White)) {

            Box {
                var expanded by remember { mutableStateOf(false) }
                Text("Помощь", modifier = Modifier.clickable { expanded = !expanded }.padding(2.dp))
                DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                    Text("О программе", modifier = Modifier.clickable {
                        expanded = !expanded
                        viewModel.postState(MainState.ABOUT)
                    })
                }
            }
        }

        Row(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxHeight().width(1.dp).background(Color.Gray))

            when (state) {
                MainState.GROUPS -> {
                    print("Попытка открыть список групп")
                    GroupsView(GroupsViewModel(viewModel.groupRepository))
                }

                MainState.ABOUT -> {
                    AboutView(Modifier.fillMaxSize()) {
                        viewModel.postState(MainState.GROUPS)
                    }
                }
            }
        }
    }
}
