package com.example.dialogaccessibility

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dialogaccessibility.databinding.DialogBinding

class CustomDialog : DialogFragment() {
    private lateinit var dialogBinding: DialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogBinding = DialogBinding.inflate(layoutInflater)


        dialogBinding.btnPositive.setOnClickListener {
            Toast.makeText(context, "positivo", Toast.LENGTH_LONG).show()
            dismiss()
        }
        dialogBinding.btnNegative.setOnClickListener {
            Toast.makeText(context, "negativo", Toast.LENGTH_LONG).show()
            dismiss()
        }
        return activity?.let { it ->
            val bulder = AlertDialog.Builder(it).apply {
                setView(dialogBinding.root)
            }
            bulder.create()
        }?: throw IllegalStateException("deu merda")
    }
}