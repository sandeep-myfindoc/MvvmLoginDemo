package com.example.mvvmlogindemo.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class Prefs internal constructor(context: Context) {
    init {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
       preferences.let { editor = it?.edit() }
    }

    // To get object from prefrences
    fun <T> getObject(key: String, a: Class<T>?): T? {
        val gson = preferences?.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, a)
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Object storaged with key "
                            + key + " is instanceof other class"
                )
            }
        }
    }

    fun save(key: String?, value: Boolean) {
        editor?.putBoolean(key, value)?.apply()
    }

    fun save(key: String?, value: String?) {
        editor?.putString(key, value)?.apply()
    }

    fun save(key: String?, value: Int) {
        editor?.putInt(key, value)?.apply()
    }

    fun save(key: String?, value: Float) {
        editor?.putFloat(key, value)?.apply()
    }

    fun save(key: String?, value: Long) {
        editor?.putLong(key, value)?.apply()
    }

    // to save object in prefrence
    fun save(key: String?, `object`: Any?) {
        requireNotNull(`object`) { "object is null" }
        require(!(key == "" || key == null)) { "key is empty or null" }
        editor?.putString(key, GSON.toJson(`object`))?.apply()
    }

    fun hasKey(key: String?): Boolean? {
        return preferences?.contains(key)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean? {
        return preferences?.getBoolean(key, defValue)
    }

    fun getString(key: String?, defValue: String?): String? {
        return preferences?.getString(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int? {
        return preferences?.getInt(key, defValue)
    }

    fun getFloat(key: String?, defValue: Float): Float? {
        return preferences?.getFloat(key, defValue)
    }

    fun getLong(key: String?, defValue: Long): Long? {
        return preferences?.getLong(key, defValue)
    }

    fun getStringSet(key: String?, defValue: Set<String?>?): Set<String>? {
        return preferences?.getStringSet(key, defValue)
    }

    fun removeAll() {
        editor?.clear()
        editor?.apply()
    }

    private class Builder(context: Context?) {
        private val context: Context

        init {
            requireNotNull(context) { "Context must not be null." }
            this.context = context.getApplicationContext()
        }

        /**
         * Method that creates an instance of Prefs
         *
         * @return an instance of Prefs
         */
        fun build(): Prefs {
            return Prefs(context)
        }
    }

    companion object {
        private const val TAG = "Prefs"
        var singleton: Prefs? = null
        var preferences: SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null
        private val GSON = Gson()
        fun with(context: Context?): Prefs? {
            if (singleton == null) {
                singleton = Builder(context).build()
            }
            return singleton
        }
    }
}