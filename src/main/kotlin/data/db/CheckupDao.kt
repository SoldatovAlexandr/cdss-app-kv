package data.db

import data.db.entity.DBCheckup

interface CheckupDao {

    suspend fun getCheckupsByCadetId(cadetId: Int): List<DBCheckup>

}
