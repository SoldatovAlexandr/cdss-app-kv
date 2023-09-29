package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object CadetTable : IntIdTable("cadet_table") {

    val groupId = reference("group_id", GroupTable.id)
    val firstName: Column<String> = varchar("first_name", 255)
    val lastName: Column<String> = varchar("last_name", 255)
    val patronymic: Column<String> = varchar("patronymic", 255)
    val dateOfBirthday: Column<String> = varchar("date_of_birthday", 255)
    val ethnicGroup: Column<String> = varchar("ethnic_group", 255)
    val placeOfBirthday: Column<String> = varchar("place_of_birthday", 255)
    val previousPlaceOfLiving: Column<String> = varchar("previous_place_of_living", 255)
    val byteType: Column<String> = varchar("byte_type", 255)
    val healthGroup: Column<String> = varchar("health_group", 255)
}

class DBCadet(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBCadet>(CadetTable)

    var groupId by CadetTable.groupId
    var firstName by CadetTable.firstName
    var lastName by CadetTable.lastName
    var patronymic by CadetTable.patronymic
    var dateOfBirthday by CadetTable.dateOfBirthday
    var ethnicGroup by CadetTable.ethnicGroup
    var placeOfBirthday by CadetTable.placeOfBirthday
    var previousPlaceOfLiving by CadetTable.previousPlaceOfLiving
    var byteType by CadetTable.byteType
    var healthGroup by CadetTable.healthGroup
}
