package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object GroupTable : IntIdTable("group_table") {
    val name: Column<String> = varchar("name", 255)
    val yearStart: Column<Int> = integer("year_start")
}

class DBGroup(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBGroup>(GroupTable)

    var name by GroupTable.name
    var yearStart by GroupTable.yearStart
}
