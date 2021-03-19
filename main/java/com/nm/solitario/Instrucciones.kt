package com.nm.solitario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_instrucciones.*

class Instrucciones : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrucciones)
        backbutton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.getId()==R.id.backbutton) openHome()
    }

    fun openHome(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }
}