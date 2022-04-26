package com.example.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

class DialogComponent : DialogFragment() {

    companion object{
    const val TAG = "dialogo simples"

    private const val  KEY_TITLE = "key_title"
    private const val  KEY_SUB_TITLE = "key_sub_title"

        fun newInstance (title :String, subTitle: String): DialogComponent {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUB_TITLE, subTitle)
            val fragment = DialogComponent()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_component,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListener(view)
    }

    private fun setupClickListener(view: View) {
    }

    private fun setupView(view: View) {
    }


}