package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object OhisTable : IntIdTable("ohis_table") {
    val checkup = reference("checkup", CheckupTable)
    val stone: Column<String> = varchar("stone", 3)
    val plaque: Column<String> = varchar("plaque", 3)
    val number: Column<String> = varchar("number", 3)
}

class DBOhis(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBOhis>(OhisTable)

    var checkup by DBCheckup referencedOn  OhisTable.checkup
    var stone by OhisTable.stone
    var plaque by OhisTable.plaque
    var number by OhisTable.number

}