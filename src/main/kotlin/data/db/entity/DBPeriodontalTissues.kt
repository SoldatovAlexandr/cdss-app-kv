package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object PeriodontalTissuesTable : IntIdTable("periodontal_tissues_table") {
    val checkup = reference("checkup", CheckupTable)
    val value: Column<String> = varchar("value", 3)
    val number: Column<String> = varchar("number", 3)
}

class DBPeriodontalTissues(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBPeriodontalTissues>(PeriodontalTissuesTable)

    var checkup by DBCheckup referencedOn  PeriodontalTissuesTable.checkup
    var value by PeriodontalTissuesTable.value
    var number by PeriodontalTissuesTable.number

}
