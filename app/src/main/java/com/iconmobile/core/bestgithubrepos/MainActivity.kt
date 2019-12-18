package com.iconmobile.core.bestgithubrepos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iconmobile.core.bestgithubrepos.overview.ui.RepoOverviewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RepoOverviewFragment.newInstance())
                .commitNow()
        }
    }

}
