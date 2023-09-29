package data.db.impl

import data.db.CheckupDao
import data.db.DatabaseFactory
import data.db.entity.CheckupTable
import data.db.entity.DBCheckup
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object CheckupDaoImpl : CheckupDao {
    override suspend fun getCheckupsByCadetId(cadetId: Int): List<DBCheckup> {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            DBCheckup.find { CheckupTable.cadetId eq cadetId }.toList()
        }
    }
}
