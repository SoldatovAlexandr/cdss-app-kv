package screens.cadet

import data.Resource
import data.model.UiCheckup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.CheckupRepository
import utils.ViewModel
import java.time.LocalDateTime

class CadetViewModel(
    private val checkupRepository: CheckupRepository
) : ViewModel() {

    private val _checkups = MutableStateFlow<Resource<List<UiCheckup>>>(Resource.loading())
    val checkups = _checkups.asStateFlow()

    private val _lastCheckup = MutableStateFlow<Resource<UiCheckup>>(Resource.loading())
    val lastCheckup = _lastCheckup.asStateFlow()

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

    fun createCheckout(cadetId: Int, checkup: UiCheckup, date: LocalDateTime) {
        viewModelScope.launch {
            checkupRepository.create(cadetId, checkup, date)
            _isCreateCheckout.emit(false)
        }
    }

    fun findLastByCadetId(cadetId: Int) {
        viewModelScope.launch {
            _lastCheckup.emit(checkupRepository.findLastByCadetId(cadetId))
            getCheckupsByCadetId(cadetId)
        }
    }
}
