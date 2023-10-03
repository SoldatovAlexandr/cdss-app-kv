package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object EnamelSpottingTable : IntIdTable("enamel_spotting_table") {
    val checkup = reference("checkup", CheckupTable)
    val value: Column<String> = varchar("value", 2)
    val number: Column<String> = varchar("number", 2)
}

class DBEnamelSpotting(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBEnamelSpotting>(EnamelSpottingTable)

    var checkup by DBCheckup referencedOn EnamelSpottingTable.checkup
    var value by EnamelSpottingTable.value
    var number by EnamelSpottingTable.number
}
