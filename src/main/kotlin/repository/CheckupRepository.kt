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
        "Ортогнатический",
        "0",
        "0",
        "0",
        "0",
        "0.00",
        "Проф осмотр",
        listOf(
            UITooth("0", "1.7"),
            UITooth("0", "1.6"),
            UITooth("0", "1.5"),
            UITooth("0", "1.4"),
            UITooth("0", "1.3"),
            UITooth("0", "1.2"),
            UITooth("0", "1.1"),
            UITooth("0", "2.1"),
            UITooth("0", "2.2"),
            UITooth("0", "2.3"),
            UITooth("0", "2.4"),
            UITooth("0", "2.5"),
            UITooth("0", "2.6"),
            UITooth("0", "2.7")
        ),
        listOf(
            UITooth("0", "4.7"),
            UITooth("0", "4.6"),
            UITooth("0", "4.5"),
            UITooth("0", "4.4"),
            UITooth("0", "4.3"),
            UITooth("0", "4.2"),
            UITooth("0", "4.1"),
            UITooth("0", "3.1"),
            UITooth("0", "3.2"),
            UITooth("0", "3.3"),
            UITooth("0", "3.4"),
            UITooth("0", "3.5"),
            UITooth("0", "3.6"),
            UITooth("0", "3.7")
        ),
        listOf(
            UIPeriodontalTissues("0", "1.7"),
            UIPeriodontalTissues("0", "1.6"),
            UIPeriodontalTissues("0", "1.5"),
            UIPeriodontalTissues("0", "1.4"),
            UIPeriodontalTissues("0", "1.3"),
            UIPeriodontalTissues("0", "1.2"),
            UIPeriodontalTissues("0", "1.1"),
            UIPeriodontalTissues("0", "2.1"),
            UIPeriodontalTissues("0", "2.2"),
            UIPeriodontalTissues("0", "2.3"),
            UIPeriodontalTissues("0", "2.4"),
            UIPeriodontalTissues("0", "2.5"),
            UIPeriodontalTissues("0", "2.6"),
            UIPeriodontalTissues("0", "2.7")
        ),
        listOf(
            UIPeriodontalTissues("0", "4.7"),
            UIPeriodontalTissues("0", "4.6"),
            UIPeriodontalTissues("0", "4.5"),
            UIPeriodontalTissues("0", "4.4"),
            UIPeriodontalTissues("0", "4.3"),
            UIPeriodontalTissues("0", "4.2"),
            UIPeriodontalTissues("0", "4.1"),
            UIPeriodontalTissues("0", "3.1"),
            UIPeriodontalTissues("0", "3.2"),
            UIPeriodontalTissues("0", "3.3"),
            UIPeriodontalTissues("0", "3.4"),
            UIPeriodontalTissues("0", "3.5"),
            UIPeriodontalTissues("0", "3.6"),
            UIPeriodontalTissues("0", "3.7")
        ),
        listOf(
            UIAttachmentLoss("0", "1.7/1.6"),
            UIAttachmentLoss("0", "1.1"),
            UIAttachmentLoss("0", "2.6/2.7"),
            UIAttachmentLoss("0", "4.7/4.6"),
            UIAttachmentLoss("0", "3.1"),
            UIAttachmentLoss("0", "3.6/3.7")
        ),
        listOf(
            UIEnamelSpotting("0", "4.6"),
            UIEnamelSpotting("0", "1.4"),
            UIEnamelSpotting("0", "1.3"),
            UIEnamelSpotting("0", "1.2"),
            UIEnamelSpotting("0", "1.1"),
            UIEnamelSpotting("0", "2.1"),
            UIEnamelSpotting("0", "2.2"),
            UIEnamelSpotting("0", "2.3"),
            UIEnamelSpotting("0", "2.4"),
            UIEnamelSpotting("0", "3.6")
        ),
        listOf(
            UIOralDamages("0", "0"),
            UIOralDamages("0", "0"),
            UIOralDamages("0", "0")
        ),
        "",
        "",
        "0",
        "0",
        listOf(
                UIOhisTooth("0", "0", "1.6"),
                UIOhisTooth("0", "0", "1.1"),
                UIOhisTooth("0", "0", "2.6"),
                UIOhisTooth("0", "0", "3.1"),
                UIOhisTooth("0", "0", "3.6"),
                UIOhisTooth("0", "0", "4.6")
        ),
        "I"
    )
}
