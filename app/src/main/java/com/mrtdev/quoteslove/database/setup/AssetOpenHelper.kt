package androidx.sqlite.db.framework

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.mrtdev.quoteslove.database.setup.QuotesAssetLoader

class AssetOpenHelper(
    context: Context,
    name: String?,
    callback: SupportSQLiteOpenHelper.Callback
) : SupportSQLiteOpenHelper {

    private val assetLoader = QuotesAssetLoader(context, name, callback.version)

    init {
        assetLoader.tryMigrate()
    }

    private val wrapped = FrameworkSQLiteOpenHelper(context, name, callback)

    override fun getDatabaseName(): String? =
        wrapped.databaseName

    override fun getWritableDatabase(): SupportSQLiteDatabase =
        wrapped.writableDatabase

    override fun getReadableDatabase(): SupportSQLiteDatabase =
        wrapped.readableDatabase

    override fun close() =
        wrapped.close()

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun setWriteAheadLoggingEnabled(enabled: Boolean) =
        wrapped.setWriteAheadLoggingEnabled(enabled)
}
