package com.mrtdev.quoteslove.storage

import android.content.Context
import com.mrtdev.quoteslove.app.SessionStorage
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(
    context: Context
) : BaseStorage(context, PREFS_NAME), SessionStorage {

    companion object {
        private const val PREFS_NAME = "SessionPreferences"

        private const val PREFS_APP_RUN_FIRST = "appRunFirstTime"
    }

    override var isAppRunFirst: Boolean
        get() = get(PREFS_APP_RUN_FIRST) ?: true
        set(value) = set(PREFS_APP_RUN_FIRST, value)

}
