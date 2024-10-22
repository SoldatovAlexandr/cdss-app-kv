package data.db.impl

import data.db.CheckupDao
import data.db.DatabaseFactory
import data.db.entity.*
import data.model.*
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object CheckupDaoImpl : CheckupDao {
    override suspend fun getCheckupsByCadetId(cadetId: Int): List<UiCheckup> {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            val result = ArrayList<UiCheckup>()
            for (id in DBCheckup.find { CheckupTable.cadetId eq cadetId }.map { it.id }) {
                val checkup =
                    DBCheckup.findById(id)?.load(
                        DBCheckup::enamelSpotting,
                        DBCheckup::teeth,
                        DBCheckup::attachmentLoss,
                        DBCheckup::oralDamages,
                        DBCheckup::periodontalTissues
                    )
                checkup?.let { result.add(it.toUiCheckup()) }
            }
            result
        }
    }

    override suspend fun create(
        cadetId: Int, date: String, uiCheckup: UiCheckup
    ) {
        return transaction(DatabaseFactory.db) {
            var checkup = DBCheckup.new {
                this.cadetId = DBCadet.findById(cadetId)?.id!!
                this.date = date
                this.fluorose = uiCheckup.fluorose
                this.levelOfHygiene = uiCheckup.levelOfHygiene
                this.tjpClicking = uiCheckup.tjpClicking
                this.tjpSymptoms = uiCheckup.tjpSymptoms
                this.tjpSoreness = uiCheckup.tjpSoreness
                this.tjpLimitedMobility = uiCheckup.tjpLimitedMobility
                this.byteType = uiCheckup.byteType
                this.erosion = uiCheckup.erosion
                this.erosionCount = uiCheckup.erosionCount
                this.trauma = uiCheckup.trauma
                this.traumaCount = uiCheckup.traumaCount
                this.predictedCpu = uiCheckup.traumaCount
                this.type = uiCheckup.type
                this.lprNeed = uiCheckup.lprNeed
                this.lprDetails = uiCheckup.lprDetails
                this.height = uiCheckup.height
                this.weight = uiCheckup.weight
                this.healthGroup = uiCheckup.healthGroup
            }
            for (tooth in uiCheckup.topTeeth) {
                DBTooth.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.downTeeth) {
                DBTooth.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.enamelSpotting) {
                DBEnamelSpotting.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.oralDamages) {
                DBOralDamages.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.topPeriodontalTissues) {
                DBPeriodontalTissues.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.downPeriodontalTissues) {
                DBPeriodontalTissues.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.attachmentLoss) {
                DBAttachmentLoss.new {
                    this.checkup = checkup
                    this.value = tooth.value
                    this.number = tooth.number
                }
            }
            for (tooth in uiCheckup.ohise) {
                DBOhis.new {
                    this.checkup = checkup
                    this.plaque = tooth.plaque
                    this.stone = tooth.stone
                    this.number = tooth.number
                }
            }
        }
    }

    override suspend fun findLastByCadetId(cadetId: Int): UiCheckup? {
        return transaction(DatabaseFactory.db) {
            addLogger(StdOutSqlLogger)
            val id = DBCheckup.find { CheckupTable.cadetId eq cadetId }.maxOfOrNull { it.id }
            id?.let { DBCheckup.findById(it)?.toUiCheckup() }
        }
    }
}

fun DBCheckup.toUiCheckup() = UiCheckup(
    date,
    fluorose,
    levelOfHygiene,
    tjpClicking,
    tjpSymptoms,
    tjpSoreness,
    tjpLimitedMobility,
    byteType,
    erosion,
    erosionCount,
    trauma,
    traumaCount,
    predictedCpu,
    type,
    teeth.filter { it.number.startsWith("1") || it.number.startsWith("2") }
        .map { UITooth(it.value, it.number) },
    teeth.filter { it.number.startsWith("3") || it.number.startsWith("4") }
        .map { UITooth(it.value, it.number) },
    periodontalTissues.filter { it.number.startsWith("1") || it.number.startsWith("2") }
        .map { UIPeriodontalTissues(it.value, it.number) },
    periodontalTissues.filter { it.number.startsWith("3") || it.number.startsWith("4") }
        .map { UIPeriodontalTissues(it.value, it.number) },
    attachmentLoss.map { UIAttachmentLoss(it.value, it.number) },
    enamelSpotting.map { UIEnamelSpotting(it.value, it.number) },
    oralDamages.map { UIOralDamages(it.value, it.number) },
    lprNeed,
    lprDetails,
    height,
    weight,
    ohise.map { UIOhisTooth(it.plaque, it.stone, it.number) },
    healthGroup
)
