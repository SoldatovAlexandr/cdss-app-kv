package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ToothTable : IntIdTable("tooth_table") {
    val checkup = reference("checkup", CheckupTable)
    val value: Column<String> = varchar("value", 2)
    val number: Column<String> = varchar("number", 2)
}

class DBTooth(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBTooth>(ToothTable)

    var checkup by DBCheckup referencedOn  ToothTable.checkup
    var value by ToothTable.value
    var number by ToothTable.number

}
