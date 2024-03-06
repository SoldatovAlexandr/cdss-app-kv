package logging

import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs
import java.io.File

object Logger : SqlLogger {

    private val logger by lazy {
        return@lazy File("/Users/asoldatov/projects/cdss-app-kv/src/main/resources/log.txt")
    }

    fun write(text: String) {
        logger.appendText("\n$text")
    }

    override fun log(context: StatementContext, transaction: Transaction) {
        write("SQL: ${context.expandArgs(transaction)}")
    }
}