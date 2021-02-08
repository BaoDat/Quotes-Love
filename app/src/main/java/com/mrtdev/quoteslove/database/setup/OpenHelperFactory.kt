package com.mrtdev.quoteslove.database.setup

import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.AssetOpenHelper

class OpenHelperFactory : SupportSQLiteOpenHelper.Factory {

    override fun create(configuration: SupportSQLiteOpenHelper.Configuration): SupportSQLiteOpenHelper =
            AssetOpenHelper(
                    configuration.context,
                    configuration.name,
                    configuration.callback
            )
}
