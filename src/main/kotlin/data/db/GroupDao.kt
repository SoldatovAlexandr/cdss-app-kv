package data.db

import data.db.entity.DBGroup

interface GroupDao {

    suspend fun getGroup(id: Int): DBGroup?
    suspend fun getGroups(): List<DBGroup>
    suspend fun create(name: String, year: String): DBGroup

}
