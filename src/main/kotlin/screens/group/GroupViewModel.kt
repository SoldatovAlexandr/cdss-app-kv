package screens.group

import data.Resource
import data.model.UiCadet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.CadetRepository
import utils.ViewModel

class GroupViewModel(
    private val cadetRepository: CadetRepository
) : ViewModel() {

    private val _cadets = MutableStateFlow<Resource<List<UiCadet>>>(Resource.loading())
    val cadets = _cadets.asStateFlow()

    private val _isCreateCadet = MutableStateFlow(false)
    val isCreateCadet = _isCreateCadet.asStateFlow()

    private val _selectedCadet = MutableStateFlow<Resource<UiCadet?>>(Resource.loading())
    val selectedCadet = _selectedCadet.asStateFlow()

    fun getCadetsByGroupId(groupId: Int) {
        viewModelScope.launch {
            _cadets.emit(cadetRepository.getCadetsByGroupId(groupId))
        }
    }

    fun setCreatedCadet(isCreated: Boolean) {
        viewModelScope.launch {
            _isCreateCadet.emit(isCreated)
        }
    }

    fun setSelectCadet(uiCadet: UiCadet?) {
        viewModelScope.launch {
            _selectedCadet.emit(Resource.success(uiCadet))
        }
    }

    fun createCadet(
        groupId: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        dateOfBirthday: String,
        ethnicGroup: String,
        placeOfBirthday: String,
        previousPlaceOfLiving: String,
        byteType: String,
        healthGroup: String
    ) {
        viewModelScope.launch {
            cadetRepository.create(
                groupId,
                firstName,
                lastName,
                patronymic,
                dateOfBirthday,
                ethnicGroup,
                placeOfBirthday,
                previousPlaceOfLiving,
                byteType,
                healthGroup
            )
            getCadetsByGroupId(groupId)
            _isCreateCadet.emit(false)
        }
    }
}
