package data.db

import data.db.entity.DBCadet

interface CadetDao {

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
    ): DBCadet

    suspend fun getCadetsByGroupId(groupId: Int): List<DBCadet>

}
