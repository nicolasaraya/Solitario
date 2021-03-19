package com.nm.solitario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_creditos.*

class Creditos : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)
        exit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.getId()==R.id.exit) openHome()
    }
    fun openHome(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }
}