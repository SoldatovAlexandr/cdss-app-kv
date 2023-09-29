package screens.cadet

import data.Resource
import data.model.UiCheckup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.CadetRepository
import repository.CheckupRepository
import utils.ViewModel

class CadetViewModel(
    private val cadetRepository: CadetRepository,
    private val checkupRepository: CheckupRepository
) : ViewModel() {

    private val _checkups = MutableStateFlow<Resource<List<UiCheckup>>>(Resource.loading())
    val checkups = _checkups.asStateFlow()

    private val _isCreateCheckout = MutableStateFlow(false)
    val isCreateCheckout = _isCreateCheckout.asStateFlow()

    fun getCheckupsByCadetId(cadetId: Int) {
        viewModelScope.launch {
            _checkups.emit(checkupRepository.getCheckupsByCadetId(cadetId))
        }
    }

    fun setCreatedCheckout(isCreated: Boolean) {
        viewModelScope.launch {
            _isCreateCheckout.emit(isCreated)
        }
    }
}
