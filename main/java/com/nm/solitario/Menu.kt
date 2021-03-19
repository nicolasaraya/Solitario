package com.nm.solitario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity(), View.OnClickListener {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Jugar.setOnClickListener(this)
        ComoJugar.setOnClickListener(this)
        Creditos.setOnClickListener(this)
        Salir.setOnClickListener(this)
    }
    fun openSelector(){
        val intent = Intent(this, Selector::class.java)
        startActivity(intent)
        finish()
    }
    fun openComo(){
        val intent = Intent(this, Instrucciones::class.java)
        startActivity(intent)
        finish()
    }

    fun openCreditos(){
        val intent = Intent(this, com.nm.solitario.Creditos::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        if(v?.getId() == R.id.Jugar) openSelector()
        if(v?.getId() == R.id.ComoJugar) openComo()
        if(v?.getId() == R.id.Creditos) openCreditos()
        if(v?.getId() == R.id.Salir) System.exit(0)
    }
}