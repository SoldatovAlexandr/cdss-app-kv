package data.db

import data.model.UiCheckup

interface CheckupDao {

    suspend fun getCheckupsByCadetId(cadetId: Int): List<UiCheckup>

    suspend fun create(cadetId: Int, date: String, uiCheckup: UiCheckup)

    suspend fun findLastByCadetId(cadetId: Int): UiCheckup?

}
