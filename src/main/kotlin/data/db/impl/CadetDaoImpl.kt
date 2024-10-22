package data.db.impl

import data.db.CadetDao
import data.db.DatabaseFactory
import data.db.entity.CadetTable
import data.db.entity.DBCadet
import data.db.entity.DBGroup
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object CadetDaoImpl : CadetDao {

    override suspend fun create(
        groupId: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        dateOfBirthday: String,
        ethnicGroup: String,
        placeOfBirthday: String,
        previousPlaceOfLiving: String
    ): DBCadet {
        return transaction(DatabaseFactory.db) {
            DBCadet.new {
                this.groupId = DBGroup.findById(groupId)?.id!! //TODO погуглить как это правильно делается
                this.firstName = firstName
                this.lastName = lastName
                this.patronymic = patronymic
                this.dateOfBirthday = dateOfBirthday
                this.ethnicGroup = ethnicGroup
                this.placeOfBirthday = placeOfBirthday
                this.previousPlaceOfLiving = previousPlaceOfLiving
            }
        }
    }

    override suspend fun getCadetsByGroupId(groupId: Int): List<DBCadet> {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            DBCadet.find { CadetTable.groupId eq groupId }.toList()
        }
    }
}
