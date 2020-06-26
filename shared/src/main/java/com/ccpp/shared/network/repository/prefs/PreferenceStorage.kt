
package com.ccpp.shared.network.repository.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Storage for app and user preferences.
 */
interface PreferenceStorage {
    var token: String?

    var deviceToken: String?

    var appBundle: String?

    var login: String?

    var locale: String?

    var isFirstTimeAskingPermissionForMap: Boolean
    var isFirstTimeAskingPermissionForReadStorage: Boolean
    var isFirstTimeAskingPermissionForWriteStorage: Boolean
    var isFirstTimeAskingPermissionForCamera: Boolean
    var notificationsPreferenceShown: Boolean

    var userName: String?
    var sound: Boolean
    var password: String?

    var profileImage: String?

    var isTouchIdEnabled: Boolean

    var isAlreadyDisplayed: Boolean

    var isAlreadyLogin: Boolean
}

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
class SharedPreferenceStorage @Inject constructor(context: Context) :
    PreferenceStorage {


    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    override var profileImage by StringPreference(prefs, PROFILE_IMAGE, "")

    override var sound by BooleanPreference(prefs, SOUND, true)


    override var token by StringPreference(prefs, PREF_TOKEN, "")

    override var deviceToken by StringPreference(prefs, DEV_TOKEN, "")

    override var appBundle by StringPreference(prefs, PREF_APP_BUNDLE, "")

    override var locale by StringPreference(prefs, PREF_LOCALE, "")

    override var login by StringPreference(prefs, PREF_LOGIN, "")

    override var password by StringPreference(prefs, PREF_PASSWORD, "")


    override var userName by StringPreference(prefs, PREF_USERNAME, "")

    override var isTouchIdEnabled by BooleanPreference(prefs, PREF_TOUCH_ID, false)

    override var isAlreadyDisplayed by BooleanPreference(prefs, ALREADY_DISPLAYED, false)

    override var isAlreadyLogin by BooleanPreference(prefs, ALREADY_Login, false)

    override var isFirstTimeAskingPermissionForMap by BooleanPreference(
        prefs,
        PREF_PERMISSION_MAP,
        true
    )
    override var isFirstTimeAskingPermissionForReadStorage by BooleanPreference(
        prefs,
        PREF_PERMISSION_READ_STORAGE,
        true
    )
    override var isFirstTimeAskingPermissionForWriteStorage by BooleanPreference(
        prefs,
        PREF_PERMISSION_WRITE_STORAGE,
        true
    )
    override var isFirstTimeAskingPermissionForCamera by BooleanPreference(
        prefs,
        PREF_PERMISSION_CAMERA,
        true
    )

    override var notificationsPreferenceShown
            by BooleanPreference(prefs, PREF_NOTIFICATIONS_SHOWN, false)

    companion object {
        const val PREFS_NAME = "msw"
        const val PREF_LOCALE = "pref_locale"
        const val PREF_TOKEN = "pref_token"
        const val DEV_TOKEN = "dev_token"
        const val PREF_APP_BUNDLE = "pref_app_bundle"

        const val PROFILE_IMAGE = "profile_image"

        const val PREF_PASSWORD = "pref_password"

        const val PREF_USERNAME = "pref_username"

        const val PREF_LOGIN = "pref_login"
        const val PREF_TOUCH_ID = "pref_touch_id"
        const val ALREADY_DISPLAYED = "already_displayed"
        const val ALREADY_Login = "already_login"
        const val SOUND = "sound"

        const val PREF_PERMISSION_MAP = "pref_permission_map"
        const val PREF_PERMISSION_READ_STORAGE = "pref_permission_read_storage"
        const val PREF_PERMISSION_WRITE_STORAGE = "pref_permission_write_storage"
        const val PREF_PERMISSION_CAMERA = "pref_permission_camera"
        const val PREF_NOTIFICATIONS_SHOWN = "pref_notifications_shown"
    }

    fun registerOnPreferenceChangeListener(listener: OnSharedPreferenceChangeListener) {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    fun cleanPreferenceStorage() {
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.clear()
        editor.apply()
    }
}

class BooleanPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.edit { putBoolean(name, value) }
    }
}

class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit { putString(name, value) }
    }
}
