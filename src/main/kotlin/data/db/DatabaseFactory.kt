package data.db

import androidx.compose.ui.res.useResource
import data.db.entity.CadetTable
import data.db.entity.CheckupTable
import data.db.entity.GroupTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

object DatabaseFactory {

    val db by lazy {
        val file = File("data.db")
        if (!file.exists()) {
            file.createNewFile()
            useResource("data.db") {
                it.copyTo(file.outputStream())
            }
        }
        val db = Database.connect("jdbc:sqlite:data.db", "org.sqlite.JDBC")
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
        }
        db
    }
}
