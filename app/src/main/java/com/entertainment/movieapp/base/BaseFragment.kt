package com.entertainment.movieapp.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ccpp.shared.network.repository.prefs.PreferenceStorage
import com.entertainment.movieapp.ui.dialog.LoadingDialog
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    lateinit var loadingDialog: LoadingDialog

    lateinit var expireDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().run {
            loadingDialog = LoadingDialog(this)
        }
    }

}