package com.nm.solitario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_selector.*

class Selector : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)
        MapCruz.setOnClickListener(this)
        MapPiramide.setOnClickListener(this)
        backSelector.setOnClickListener(this)

    }
    fun openPiramide(){
        val intent = Intent(this, TableroPiramide::class.java)
        startActivity(intent)
        finish()
    }
    fun openCruz(){
        val intent = Intent(this, TableroCruz::class.java)
        startActivity(intent)
        finish()
    }
    override fun onClick(v: View?) {
        if(v?.getId() == R.id.MapCruz) openCruz()
        if(v?.getId() == R.id.MapPiramide) openPiramide()
        if(v?.getId() == R.id.backSelector) openHome()
    }

    fun openHome(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }
}