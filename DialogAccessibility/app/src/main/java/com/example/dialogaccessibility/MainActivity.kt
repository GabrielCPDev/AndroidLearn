package com.example.dialogaccessibility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialogaccessibility.databinding.ActivityMainBinding
import com.example.dialogaccessibility.databinding.DialogBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener{
            teste()
        }

    }

    private fun teste() {
        val dialog = CustomDialog()
        dialog.show(supportFragmentManager,"tomanocu")
//        var  dialogBinding = DialogBinding.inflate(layoutInflater)
//        val bulder = AlertDialog.Builder(applicationContext).apply {
//            setContentView(dialogBinding.root)
//            setCancelable(true)
//        }.create()
//        dialogBinding.btnPositive.setOnClickListener {
//            Toast.makeText(applicationContext,"botao positivo", Toast.LENGTH_LONG).show()
//        }
//        dialogBinding.btnNegative.setOnClickListener {
//            Toast.makeText(applicationContext,"potao negativo", Toast.LENGTH_LONG).show()
//        }
//        bulder.show()
    }
}