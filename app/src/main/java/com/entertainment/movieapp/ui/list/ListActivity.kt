package com.entertainment.movieapp.ui.list

import android.os.Bundle
import com.ccpp.shared.util.inTransaction
import com.entertainment.movieapp.R
import com.entertainment.movieapp.base.BaseActivity


class ListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportFragmentManager.inTransaction {
            replace(R.id.listContainer,ListFragment())
        }
    }
}