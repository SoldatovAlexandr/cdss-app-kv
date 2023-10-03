package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object AttachmentLossTable : IntIdTable("attachment_loss_table") {
    val checkup = reference("checkup", CheckupTable)
    val value: Column<String> = varchar("value", 2)
    val number: Column<String> = varchar("number", 10)
}

class DBAttachmentLoss(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBAttachmentLoss>(AttachmentLossTable)

    var checkup by DBCheckup referencedOn AttachmentLossTable.checkup
    var value by AttachmentLossTable.value
    var number by AttachmentLossTable.number
}
