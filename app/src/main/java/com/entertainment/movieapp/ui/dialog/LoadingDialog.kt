/*
 * Created by Chaiwut Maneechot on 9/26/18 5:54 PM
 * Copyright © 2018 2C2P. All rights reserved.
 * Last modified 9/26/18 5:34 PM
 */

package com.entertainment.movieapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.entertainment.movieapp.R
import javax.inject.Singleton

@Singleton
open class LoadingDialog(private val context: Context) {

    private var mDialog: Dialog = Dialog(context)
    private var view: View? = null

    open fun loading(isLoading: Boolean) {
        if (isLoading) {
            show()
        } else {
            hide()
        }
    }

    fun setLoaderColor(color: Int) {
        if (view != null) {
            val progress = view?.findViewById<ProgressBar>(R.id.progressDialog)
            progress!!.indeterminateDrawable.setColorFilter(
                ContextCompat.getColor(context, color),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun show() {
        if (!mDialog.isShowing) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            mDialog.setCancelable(false)
            mDialog.setContentView(view!!)
            mDialog.show()
        }
    }

    private fun hide() {
        if (mDialog.isShowing) {
            mDialog.dismiss()
            mDialog = Dialog(context)
        }
    }
}