package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object OralDamagesTable : IntIdTable("oral_damages_table") {
    val checkup = reference("checkup", CheckupTable)
    val value: Column<String> = varchar("value", 2)
    val number: Column<String> = varchar("number", 10)
}

class DBOralDamages(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBOralDamages>(OralDamagesTable)

    var checkup by DBCheckup referencedOn  OralDamagesTable.checkup
    var value by OralDamagesTable.value
    var number by OralDamagesTable.number
}
