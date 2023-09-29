package data.db.impl

import data.db.DatabaseFactory
import data.db.GroupDao
import data.db.entity.DBGroup
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object GroupDaoImpl : GroupDao {

    override suspend fun getGroup(id: Int): DBGroup? {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            DBGroup.findById(id)
        }
    }

    override suspend fun getGroups(): List<DBGroup> {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            DBGroup.all().toList()
        }
    }

    override suspend fun create(name: String, year: String): DBGroup {
        return transaction(DatabaseFactory.db) {
            DBGroup.new {
                this.name = name
                this.yearStart = Integer.parseInt(year)
            }
        }
    }
}
