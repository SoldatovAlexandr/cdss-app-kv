package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object CheckupTable : IntIdTable("checkup") {
    val cadetId = reference("cadet_id", CadetTable.id)
    val date: Column<String> = varchar("date", 255)
}

class DBCheckup(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBCheckup>(CheckupTable)

    var cadetId by CheckupTable.cadetId
    var date by CheckupTable.date
}
