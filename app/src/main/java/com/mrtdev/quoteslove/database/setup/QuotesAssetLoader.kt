package com.mrtdev.quoteslove.database.setup

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipInputStream

class QuotesAssetLoader(
    private val context: Context,
    private val name: String?,
    private val version: Int
) {

    companion object {
        private const val ZIP_READ_BUFFER_SIZE = 2048
        private val ZIP_FILE = "databases${File.separator}QuotesLove.zip"
    }

    fun tryMigrate() {
        if (shouldMigrate()) {
            migrate()
        }
    }

    private fun shouldMigrate(): Boolean {
        val dbFile = context.getDatabasePath(name)
        var shouldMigrate = false
        if (dbFile.exists()) {
            SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READONLY).use {
                shouldMigrate = it.version < version
            }
        } else {
            shouldMigrate = true
        }
        return shouldMigrate
    }

    private fun migrate() {
        val output = context.getDatabasePath(name)

        unzipDatabase(context.assets.open(ZIP_FILE), output)

        // HACK database from assets has version = 0,
        SQLiteDatabase.openDatabase(output.path, null, SQLiteDatabase.OPEN_READWRITE).use {
            it.version = version - 1
        }
    }

    private fun unzipDatabase(input: InputStream, output: File) {
        val buffer = ByteArray(ZIP_READ_BUFFER_SIZE)

        ZipInputStream(input).use { zipStream ->
            zipStream.nextEntry?.let {
                FileOutputStream(output).use {
                    var len = zipStream.read(buffer)
                    while (len >= 0) {
                        it.write(buffer, 0, len)
                        len = zipStream.read(buffer)
                    }
                }
            }
        }
    }
}
