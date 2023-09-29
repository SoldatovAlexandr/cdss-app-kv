package repository

import data.Resource
import data.db.GroupDao
import data.db.impl.GroupDaoImpl
import data.db.entity.DBGroup
import data.model.UiGroup

object GroupRepository {

    private val dao: GroupDao = GroupDaoImpl

    suspend fun getGroups(): Resource<List<UiGroup>> {
        return try {
            Resource.success(dao.getGroups().map { it.toUiGroup() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }

    suspend fun create(name: String, year: String): Resource<UiGroup> {
        return try {
            Resource.success(dao.create(name, year).toUiGroup())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }

}

fun DBGroup.toUiGroup() = UiGroup(id.value, name, yearStart)
