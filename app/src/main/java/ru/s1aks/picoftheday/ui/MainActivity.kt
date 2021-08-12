package ru.s1aks.picoftheday.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.s1aks.picoftheday.R
import ru.s1aks.picoftheday.ui.main.MainFragment


class MainActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(App.APP_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences?.let {
            val themeId = it.getInt(App.PREF_THEME_KEY, R.style.AppThemeLight)
            setTheme(themeId)
        }
        setContentView(R.layout.main_activity)
        savedInstanceState ?: let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}