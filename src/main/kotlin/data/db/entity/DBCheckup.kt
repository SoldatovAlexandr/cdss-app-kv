package data.db.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object CheckupTable : IntIdTable("checkup") {
    val cadetId = reference("cadet_id", CadetTable.id)
    val date: Column<String> = varchar("date", 255)
    val fluorose: Column<String> = varchar("fluorose", 255)
    val levelOfHygiene: Column<String> = varchar("level_of_hygiene", 255)
    val tjpClicking: Column<String> = varchar("tjp_clicking", 255)
    val tjpSymptoms: Column<String> = varchar("tjp_symptoms", 255)
    val tjpSoreness: Column<String> = varchar("tjp_soreness", 255)
    val tjpLimitedMobility: Column<String> = varchar("tjp_limited_mobility", 255)
    val byteType: Column<String> = varchar("byte_type", 255)
    val erosion: Column<String> = varchar("erosion", 255)
    val erosionCount: Column<String> = varchar("erosion_count", 255)
    val trauma: Column<String> = varchar("trauma", 255)
    val traumaCount: Column<String> = varchar("trauma_count", 255)
    val predictedCpu: Column<String> = varchar("predicted_cpu", 255)
    val type: Column<String> = varchar("type", 255)
}

class DBCheckup(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DBCheckup>(CheckupTable)

    var cadetId by CheckupTable.cadetId
    var date by CheckupTable.date
    var fluorose by CheckupTable.fluorose
    var levelOfHygiene by CheckupTable.levelOfHygiene
    var tjpClicking by CheckupTable.tjpClicking
    var tjpSymptoms by CheckupTable.tjpSymptoms
    var tjpSoreness by CheckupTable.tjpSoreness
    var tjpLimitedMobility by CheckupTable.tjpLimitedMobility
    var byteType by CheckupTable.byteType
    var erosion by CheckupTable.erosion
    var erosionCount by CheckupTable.erosionCount
    var trauma by CheckupTable.trauma
    var traumaCount by CheckupTable.traumaCount
    var predictedCpu by CheckupTable.predictedCpu
    var type by CheckupTable.type

    val teeth by DBTooth referrersOn ToothTable.checkup
    val attachmentLoss by DBAttachmentLoss referrersOn AttachmentLossTable.checkup
    val enamelSpotting by DBEnamelSpotting referrersOn EnamelSpottingTable.checkup
    val oralDamages by DBOralDamages referrersOn OralDamagesTable.checkup
    val periodontalTissues by DBPeriodontalTissues referrersOn PeriodontalTissuesTable.checkup

}
