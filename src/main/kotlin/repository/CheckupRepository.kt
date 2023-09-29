package repository

import data.Resource
import data.db.CheckupDao
import data.db.entity.DBCheckup
import data.db.impl.CheckupDaoImpl
import data.model.UiCheckup

object CheckupRepository {

    private val checkupDao: CheckupDao = CheckupDaoImpl

    suspend fun getCheckupsByCadetId(cadetId: Int): Resource<List<UiCheckup>> {
        return try {
            Resource.success(checkupDao.getCheckupsByCadetId(cadetId).map { it.toUiCheckup() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }
}

fun DBCheckup.toUiCheckup() = UiCheckup(
    id.value,
    cadetId.value,
    date
)
