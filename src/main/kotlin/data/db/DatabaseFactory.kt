package data.db

import androidx.compose.ui.res.useResource
import data.db.entity.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

private const val DATABASE_FILE_NAME = "cdss-app-kv.db"

object DatabaseFactory {

    val db by lazy {
        val file = File(DATABASE_FILE_NAME)

        if (!file.exists()) {
            file.createNewFile()
            useResource( DATABASE_FILE_NAME) {
                it.copyTo(file.outputStream())
            }
        }
        val db = Database.connect("jdbc:sqlite:$DATABASE_FILE_NAME", "org.sqlite.JDBC")
        transaction(db) {
            addLogger(StdOutSqlLogger)
            if (!SchemaUtils.checkCycle(GroupTable)) {
                SchemaUtils.create(GroupTable)
            }
            if (!SchemaUtils.checkCycle(CadetTable)) {
                SchemaUtils.create(CadetTable)
            }
            if (!SchemaUtils.checkCycle(CheckupTable)) {
                SchemaUtils.create(CheckupTable)
            }
            if (!SchemaUtils.checkCycle(ToothTable)) {
                SchemaUtils.create(ToothTable)
            }
            if (!SchemaUtils.checkCycle(PeriodontalTissuesTable)) {
                SchemaUtils.create(PeriodontalTissuesTable)
            }
            if (!SchemaUtils.checkCycle(OralDamagesTable)) {
                SchemaUtils.create(OralDamagesTable)
            }
            if (!SchemaUtils.checkCycle(EnamelSpottingTable)) {
                SchemaUtils.create(EnamelSpottingTable)
            }
            if (!SchemaUtils.checkCycle(AttachmentLossTable)) {
                SchemaUtils.create(AttachmentLossTable)
            }
            if (!SchemaUtils.checkCycle(OhisTable)) {
                SchemaUtils.create(OhisTable)
            }
        }
        db
    }
}
