package com.nm.solitario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_piramide.*
import java.util.*
import kotlin.math.absoluteValue

class TableroCruz : AppCompatActivity(), View.OnClickListener  {
    private val a = TableroPiramide()
    private var  bloques= mutableMapOf<ImageButton,Casillas?>()
    private var info : Array<Casillas?> =  arrayOfNulls<Casillas>(34)
    private  var puntaje = 0
    private  var posiblecambio = 0
    private var Ibtns= arrayOfNulls<ImageButton>(34)
    private lateinit var puntajeTotal: TextView
    private  var movs : Stack<Movimientos> = Stack()

    override fun onClick(v: View?) {
        btnSelected(v as ImageButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cruz)
        var fila = 1
        puntajeTotal=findViewById(R.id.puntajeTotal)
        reset.setOnClickListener(this)
        home.setOnClickListener(this)
        skip.setOnClickListener(this)
        back.setOnClickListener(this)
        sugerencia.setOnClickListener(this)



        bloques[sugerencia] = Casillas(sugerencia,8,0,0)
        bloques[sugerencia]?.update()
        bloques[reset] = Casillas(reset,3,0,0)
        bloques[reset]?.update()
        bloques[home] = Casillas(home,6,0,0)
        bloques[home]?.update()
        bloques[back] = Casillas(back,7,0,0)
        bloques[back]?.update()
        bloques[skip] = Casillas(skip,5,0,0)
        bloques[skip]?.update()

        if(savedInstanceState!=null){

            movs = savedInstanceState.getSerializable("stack") as Stack<Movimientos>
            info = savedInstanceState.getSerializable("info") as Array<Casillas?>
            puntaje = savedInstanceState.getInt("puntaje")
            Ibtns = savedInstanceState.getSerializable("Ibtns") as Array<ImageButton?>
            for(i in 1..33){
                var Ibutton=findViewById<ImageButton>(resources.getIdentifier("btn$i","id",packageName))
                Ibutton.setOnClickListener(this)
                Ibtns[i] = Ibutton
                if(i == 4) fila = 2
                if(i == 7) fila = 3
                if(i == 14) fila = 4
                if(i == 21) fila = 5
                if(i == 28) fila = 6
                if(i == 31) fila = 7

                if(i==17) info[i] = Casillas(Ibtns[i],info[i]!!.clicks, fila,i)
                else info[i] = Casillas(Ibtns[i],info[i]!!.clicks, fila,i)
                info[i]?.update()
                info[i]?.setColumna()
                bloques[Ibutton] = info[i]
            }
            puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"

        }
        else{

            for (i in 1..33){
                var Ibutton=findViewById<ImageButton>(resources.getIdentifier("btn$i","id",packageName))
                Ibutton.setOnClickListener(this)
                Ibtns[i] = Ibutton
                if(i == 4) fila = 2
                if(i == 7) fila = 3
                if(i == 14) fila = 4
                if(i == 21) fila = 5
                if(i == 28) fila = 6
                if(i == 31) fila = 7

                if(i==17) info[i] = Casillas(Ibtns[i],0, fila,i)
                else info[i] = Casillas(Ibtns[i],2, fila,i)
                info[i]?.update()
                info[i]?.setColumna()
                bloques[Ibutton] = info[i]
            }

            puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"
        }
    }
    private  fun btnSelected(button: ImageButton) {
        juegoTerminado()
        var seleccionada = 0
        for (i in 1..33) {
            if (info[i]?.clicks!! == 1) {
                seleccionada = i
            }
            if(bloques[Ibtns[i]]?.clicks == 4){
                bloques[Ibtns[i]]?.setClick(0)
                bloques[Ibtns[i]]?.update()
                posiblecambio = 0

            }
        }



        if (seleccionada == 0){
            if(bloques[button]?.clicks == 2){
                bloques[button]?.setClick(1)
                bloques[button]?.update()
            }
        }
        else if(bloques[sugerencia] == bloques[button]) {
            Sugerencia(seleccionada,true)
            posiblecambio = 0
        }
        else if (info[seleccionada] != bloques[button]){
            EvaluarJugada(seleccionada, bloques[button]?.pos)
        }

        if(bloques[reset] == bloques[button]) Resetear()
        if(bloques[back] == bloques[button]) MovAtras()
        if(bloques[skip] == bloques[button]) openActivity()
        if(bloques[home] == bloques[button]) openHome()
    }
    private fun EvaluarJugada(seleccionada: Int, segundaSeleccion: Int?){
        var comp = info[segundaSeleccion!!]?.fila?.minus(info[seleccionada]!!.fila)
        var posicion: Int? = segundaSeleccion - seleccionada
        var mayor = segundaSeleccion
        var menor :Int? = seleccionada
        if(posicion!! < 0){
            mayor = seleccionada
            menor = segundaSeleccion
        }

        var cambio = 0
        //caso misma linea
        if(comp == 0  && bloques[Ibtns[segundaSeleccion]]?.clicks == 0){
            for (i in 1..33){
                if(i < mayor && i > menor!! && bloques[Ibtns[i]]?.clicks !=0 && (i-mayor).absoluteValue == 1 && (i-menor).absoluteValue == 1){
                    cambio = i
                    break;
                }
            }
            if(cambio !=0){
                puntaje+=10
                bloques[Ibtns[cambio]]?.setClick(0)
                bloques[Ibtns[cambio]]?.update()
                bloques[Ibtns[seleccionada]]?.setClick(0)
                bloques[Ibtns[segundaSeleccion]]?.setClick(2)
                var m = Movimientos(cambio, seleccionada, segundaSeleccion)
                movs.push(m)
                if(movs.size == 1) puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugada válida"
                else puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"
            }
            else{
                Toast.makeText(this,"Movimiento NO valido", Toast.LENGTH_SHORT).show()
                bloques[Ibtns[seleccionada]]?.setClick(2)
            }
        }
        //caso vertical
        else if(comp?.absoluteValue == 2 && bloques[Ibtns[segundaSeleccion]]?.clicks == 0){
            if(bloques[Ibtns[segundaSeleccion]]?.columna == bloques[Ibtns[seleccionada]]?.columna){
                for (i in 1..33){
                    if(bloques[Ibtns[i]]?.clicks !=0 && (bloques[Ibtns[i]]?.fila?.minus(bloques[Ibtns[seleccionada]]?.fila!!))?.absoluteValue == 1 && (bloques[Ibtns[i]]?.fila?.minus(bloques[Ibtns[segundaSeleccion]]?.fila!!))?.absoluteValue == 1){
                        if(bloques[Ibtns[i]]?.columna== bloques[Ibtns[segundaSeleccion]]?.columna){
                            cambio = i
                            break
                        }
                    }
                }
            }
            if(cambio!=0){
                puntaje+=10
                bloques[Ibtns[cambio]]?.setClick(0)
                bloques[Ibtns[cambio]]?.update()
                bloques[Ibtns[seleccionada]]?.setClick(0)
                bloques[Ibtns[segundaSeleccion]]?.setClick(2)
                var m = Movimientos(cambio, seleccionada, segundaSeleccion)
                movs.push(m)
                if(movs.size == 1) puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugada válida"
                else puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"
            }
            else{
                Toast.makeText(this,"Movimiento NO valido", Toast.LENGTH_SHORT).show()
                bloques[Ibtns[seleccionada]]?.setClick(2)
            }
        }
        else{
            Toast.makeText(this,"Movimiento NO valido", Toast.LENGTH_SHORT).show()
            bloques[Ibtns[seleccionada]]?.setClick(2)
        }
        bloques[Ibtns[seleccionada]]?.update()
        bloques[Ibtns[segundaSeleccion]]?.update()
    }

    private fun Resetear(){
        for (i in 1..33){
            if(i==17)  bloques[Ibtns[i]]?.setClick(0)
            else  bloques[Ibtns[i]]?.setClick(2)
            bloques[Ibtns[i]]?.update()

        }
        puntaje = 0
        movs.clear()
        puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"
    }

    private fun Sugerencia(seleccionada: Int, asked: Boolean) {
        posiblecambio = 0
        for (j in 1..33) {
            var comp = info[j!!]?.fila?.minus(info[seleccionada]!!.fila)
            var posicion: Int? = j - seleccionada
            var mayor = j
            var menor: Int? = seleccionada
            if (posicion!! < 0) {
                mayor = seleccionada
                menor = j
            }


            //caso misma linea
            if (comp == 0 && bloques[Ibtns[j]]?.clicks == 0) {
                for (i in 1..33) {
                    if (i < mayor && i > menor!! && bloques[Ibtns[i]]?.clicks != 0 && (i-mayor).absoluteValue == 1 && (i-menor).absoluteValue == 1) {
                        posiblecambio = j
                        break;
                    }
                }
            }
            //caso vertical
            else if (comp?.absoluteValue == 2 && bloques[Ibtns[j]]?.clicks == 0) {
                if (bloques[Ibtns[j]]?.columna == bloques[Ibtns[seleccionada]]?.columna) {
                    for (i in 1..33) {
                        if (bloques[Ibtns[i]]?.clicks != 0 && (bloques[Ibtns[i]]?.fila?.minus(bloques[Ibtns[seleccionada]]?.fila!!))?.absoluteValue == 1 && (bloques[Ibtns[i]]?.fila?.minus(bloques[Ibtns[j]]?.fila!!))?.absoluteValue == 1) {
                            if (bloques[Ibtns[i]]?.columna == bloques[Ibtns[j]]?.columna) {
                                posiblecambio = j
                                break
                            }
                        }
                    }
                }
            }
            if (posiblecambio != 0) {
                if(asked ==true){
                    bloques[Ibtns[posiblecambio]]?.setClick(4)
                    bloques[Ibtns[posiblecambio]]?.update()
                }
                break
            }
        }
        if(posiblecambio==0 && asked ==true)Toast.makeText(this,"No hay sugerencias válidas", Toast.LENGTH_SHORT).show()
    }



    private fun MovAtras(){
        if(movs.empty()){
            Toast.makeText(this,"No hay movimientos anteriores", Toast.LENGTH_SHORT).show()
            return
        }
        bloques[Ibtns[movs.peek().getn1()]]?.setClick(2)
        bloques[Ibtns[movs.peek().getn2()]]?.setClick(2)
        bloques[Ibtns[movs.peek().getn3()]]?.setClick(0)
        bloques[Ibtns[movs.peek().getn1()]]?.update()
        bloques[Ibtns[movs.peek().getn2()]]?.update()
        bloques[Ibtns[movs.peek().getn3()]]?.update()
        puntaje-=10
        movs.pop()
        if(movs.size == 1) puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugada válida"
        else puntajeTotal.text = "Puntaje = $puntaje con ${movs.size} jugadas válidas"



    }
    private fun juegoTerminado(){
        if(puntaje == 310 && bloques[Ibtns[17]]?.clicks == 2) Toast.makeText(this, "GANASTE y la pieza quedó en la posición inicial", Toast.LENGTH_LONG).show()
        else if(puntaje == 310) Toast.makeText(this, "Felicidades GANASTE", Toast.LENGTH_LONG).show()
        else {
            var cambio = 0
            for (j in 1..33) {
                if (bloques[Ibtns[j]]?.clicks == 2) {
                    Sugerencia(j, false)
                    if (posiblecambio != 0)break;
                }
            }
            if (posiblecambio == 0) puntajeTotal.text = "REINICIA NO HAY MÁS MOVIMIENTOS"
            posiblecambio = 0
        }
    }

    fun openActivity(){
        val intent = Intent(this, TableroPiramide::class.java)
        startActivity(intent)
        finish()
    }
    fun openHome(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("stack", movs)
        outState.putSerializable("info", info)
        outState.putInt("puntaje", puntaje)
        outState.putSerializable("Ibtns", Ibtns)
    }


}
