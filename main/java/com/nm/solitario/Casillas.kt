package com.nm.solitario

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlin.math.absoluteValue

class Casillas(val boton: ImageButton?, entero: Int, val fila: Int, val pos:Int) {
    var clicks = entero
    var columna = 0
    var lineaVerticalIzqADer = 0
    var lineaVerticalDerAIzq = 0
    public fun setLineas(){
        if(pos == 1 || pos == 2|| pos == 4 || pos == 7 || pos == 11) lineaVerticalIzqADer= 1
        if(pos == 3 || pos == 5|| pos == 8 || pos == 12)lineaVerticalIzqADer= 2
        if(pos == 6 || pos == 9|| pos == 13) lineaVerticalIzqADer= 3
        if(pos == 10 || pos== 14) lineaVerticalIzqADer= 4
        if(pos == 15) lineaVerticalIzqADer= 5

        if(pos == 1 || pos == 3|| pos == 6 || pos == 10 || pos == 15)lineaVerticalDerAIzq= 1
        if(pos == 2 || pos == 5|| pos == 9 || pos == 14)lineaVerticalDerAIzq= 2
        if(pos == 4 || pos == 8|| pos == 13) lineaVerticalDerAIzq= 3
        if(pos == 7 || pos == 12) lineaVerticalDerAIzq= 4
        if(pos == 11) lineaVerticalDerAIzq= 5
    }
    public fun setClick(valor: Int){
        clicks = valor
    }
    public fun setColumna(){
        if(pos == 7 || pos == 14|| pos == 21) columna= 1
        if(pos == 8 || pos == 15|| pos == 22) columna= 2
        if(pos == 1 || pos == 4 || pos == 9 || pos == 16 || pos == 23 || pos ==28 || pos ==31 ) columna= 3
        if(pos == 2 || pos == 5 || pos == 10 || pos == 17 || pos == 24 || pos ==29 || pos ==32 ) columna= 4
        if(pos == 3 || pos == 6 || pos == 11 || pos == 18 || pos == 25 || pos ==30 || pos ==33 ) columna= 5
        if(pos == 12 || pos == 19|| pos == 26) columna= 6
        if(pos == 13 || pos == 20|| pos == 27) columna= 7

    }
    public fun update(){
        if(clicks ==0) { //vacia
            boton?.setImageResource(R.drawable.vacia)

        }
        if(clicks ==1) {
            boton?.setImageResource(R.drawable.seleccionada) //seleccionada
        }
        if(clicks ==2) {
            boton?.setImageResource(R.drawable.llena) //llena

        }
        if(clicks ==3) {
            boton?.setImageResource(R.drawable.reiniciar)
        }
        if(clicks ==4) {
            boton?.setImageResource(R.drawable.sugerencia)
        }
        if(clicks==5){
            boton?.setImageResource(R.drawable.saltarnivel)
        }
        if(clicks==6){
            boton?.setImageResource(R.drawable.home)
        }
        if(clicks==7){
            boton?.setImageResource(R.drawable.back)
        }
        if(clicks==8){
            boton?.setImageResource(R.drawable.ayuda)
        }
    }

}

