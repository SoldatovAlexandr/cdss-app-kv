package screens.groups

import data.Resource
import data.model.UiGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.GroupRepository
import utils.ViewModel

class GroupsViewModel(private val groupRepository: GroupRepository) : ViewModel() {

    private val _groups = MutableStateFlow<Resource<List<UiGroup>>>(Resource.loading())
    val groups = _groups.asStateFlow()

    private val _isCreateGroup = MutableStateFlow(false)
    val isCreateGroup = _isCreateGroup.asStateFlow()

    private val _selectedGroup = MutableStateFlow<Resource<UiGroup?>>(Resource.loading())
    val selectedGroup = _selectedGroup.asStateFlow()

    fun getGroups() {
        viewModelScope.launch {
            _groups.emit(groupRepository.getGroups())
        }
    }

    fun selectGroup(uiGroup: UiGroup?) {
        viewModelScope.launch {
            _selectedGroup.emit(Resource.success(uiGroup))
        }
    }

    fun setCreatedGroup(isCreated: Boolean) {
        viewModelScope.launch {
            _isCreateGroup.emit(isCreated)
        }
    }

    fun createGroup(name: String, startYear: String) {
        viewModelScope.launch {
            groupRepository.create(name, startYear)
            _isCreateGroup.emit(false)
            getGroups()
        }
    }
}
