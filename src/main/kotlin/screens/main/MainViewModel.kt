package screens.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.GroupRepository
import utils.ViewModel

class MainViewModel(val groupRepository: GroupRepository) : ViewModel() {

    private val _state = MutableStateFlow(MainState.GROUPS)
    val state = _state.asStateFlow()

    fun postState(state: MainState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }
}

enum class MainState {
    ABOUT,
    GROUPS
}
