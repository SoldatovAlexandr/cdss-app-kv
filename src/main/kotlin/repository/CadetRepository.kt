package repository

import data.Resource
import data.db.CadetDao
import data.db.impl.CadetDaoImpl
import data.db.entity.DBCadet
import data.model.UiCadet

object CadetRepository {

    private val cadetDao: CadetDao = CadetDaoImpl

    suspend fun getCadetsByGroupId(groupId: Int): Resource<List<UiCadet>> {
        return try {
            Resource.success(cadetDao.getCadetsByGroupId(groupId).map { it.toUiCadet() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }

    suspend fun create(
        groupId: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        dateOfBirthday: String,
        ethnicGroup: String,
        placeOfBirthday: String,
        previousPlaceOfLiving: String,
        byteType: String,
        healthGroup: String
    ): Resource<UiCadet> {
        return try {
            Resource.success(
                cadetDao.create(
                    groupId,
                    firstName,
                    lastName,
                    patronymic,
                    dateOfBirthday,
                    ethnicGroup,
                    placeOfBirthday,
                    previousPlaceOfLiving,
                    byteType,
                    healthGroup
                ).toUiCadet()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }
}


fun DBCadet.toUiCadet() = UiCadet(
    id.value,
    groupId.value,
    firstName,
    lastName,
    patronymic,
    dateOfBirthday,
    ethnicGroup,
    placeOfBirthday,
    previousPlaceOfLiving,
    byteType,
    healthGroup
)
