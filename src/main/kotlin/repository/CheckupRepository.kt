package repository

import data.Resource
import data.db.CheckupDao
import data.db.impl.CheckupDaoImpl
import data.model.*
import java.time.LocalDateTime

object CheckupRepository {

    private val checkupDao: CheckupDao = CheckupDaoImpl

    suspend fun getCheckupsByCadetId(cadetId: Int): Resource<List<UiCheckup>> {
        return try {
            Resource.success(checkupDao.getCheckupsByCadetId(cadetId))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }

    suspend fun create(cadetId: Int, checkup: UiCheckup, date: LocalDateTime) {
        try {
            checkupDao.create(cadetId, date.toString(), checkup)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun findLastByCadetId(cadetId: Int): Resource<UiCheckup> {
        return try {
            val result = checkupDao.findLastByCadetId(cadetId)
            if (result == null) {
                return Resource.success(createDefaultUICheckup())
            } else {
                return Resource.success(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e)
        }
    }
}


fun createDefaultUICheckup(): UiCheckup {
    return UiCheckup(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0.00",
        "Проф осмотр",
        listOf(
            UITooth("0", "17"),
            UITooth("0", "16"),
            UITooth("0", "15"),
            UITooth("0", "14"),
            UITooth("0", "13"),
            UITooth("0", "12"),
            UITooth("0", "11"),
            UITooth("0", "21"),
            UITooth("0", "22"),
            UITooth("0", "23"),
            UITooth("0", "24"),
            UITooth("0", "25"),
            UITooth("0", "26"),
            UITooth("0", "27")
        ),
        listOf(
            UITooth("0", "47"),
            UITooth("0", "46"),
            UITooth("0", "45"),
            UITooth("0", "44"),
            UITooth("0", "43"),
            UITooth("0", "42"),
            UITooth("0", "41"),
            UITooth("0", "31"),
            UITooth("0", "32"),
            UITooth("0", "33"),
            UITooth("0", "34"),
            UITooth("0", "35"),
            UITooth("0", "36"),
            UITooth("0", "37")
        ),
        listOf(
            UIPeriodontalTissues("0", "17"),
            UIPeriodontalTissues("0", "16"),
            UIPeriodontalTissues("0", "15"),
            UIPeriodontalTissues("0", "14"),
            UIPeriodontalTissues("0", "13"),
            UIPeriodontalTissues("0", "12"),
            UIPeriodontalTissues("0", "11"),
            UIPeriodontalTissues("0", "21"),
            UIPeriodontalTissues("0", "22"),
            UIPeriodontalTissues("0", "23"),
            UIPeriodontalTissues("0", "24"),
            UIPeriodontalTissues("0", "25"),
            UIPeriodontalTissues("0", "26"),
            UIPeriodontalTissues("0", "27")
        ),
        listOf(
            UIPeriodontalTissues("0", "47"),
            UIPeriodontalTissues("0", "46"),
            UIPeriodontalTissues("0", "45"),
            UIPeriodontalTissues("0", "44"),
            UIPeriodontalTissues("0", "43"),
            UIPeriodontalTissues("0", "42"),
            UIPeriodontalTissues("0", "41"),
            UIPeriodontalTissues("0", "31"),
            UIPeriodontalTissues("0", "32"),
            UIPeriodontalTissues("0", "33"),
            UIPeriodontalTissues("0", "34"),
            UIPeriodontalTissues("0", "35"),
            UIPeriodontalTissues("0", "36"),
            UIPeriodontalTissues("0", "37")
        ),
        listOf(
            UIAttachmentLoss("0", "17/16"),
            UIAttachmentLoss("0", "11"),
            UIAttachmentLoss("0", "26/27"),
            UIAttachmentLoss("0", "47/46"),
            UIAttachmentLoss("0", "31"),
            UIAttachmentLoss("0", "36/37")
        ),
        listOf(
            UIEnamelSpotting("0", "46"),
            UIEnamelSpotting("0", "14"),
            UIEnamelSpotting("0", "13"),
            UIEnamelSpotting("0", "12"),
            UIEnamelSpotting("0", "11"),
            UIEnamelSpotting("0", "21"),
            UIEnamelSpotting("0", "22"),
            UIEnamelSpotting("0", "23"),
            UIEnamelSpotting("0", "24"),
            UIEnamelSpotting("0", "36")
        ),
        listOf(
            UIOralDamages("0", "0"),
            UIOralDamages("0", "0"),
            UIOralDamages("0", "0")
        )
    )
}
