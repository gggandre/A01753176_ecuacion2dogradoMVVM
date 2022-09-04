package mx.itesm.ag.a01753176_ecuacion2dogradomvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import mx.itesm.ag.a01753176_ecuacion2dogradomvvm.databinding.ActivityMainBinding
import mx.itesm.ag.a01753176_ecuacion2dogradomvvm.viewmodel.Verificar


class MainActivity : AppCompatActivity() {
    /**
     *@author: Gilberto André García Gaytán
     * El código @MainActivity funciona para mostrar la parte visual e el .xml
     * y hacer que funcione la interfaz y sus componentes con ayuda de los programas
     * hechos en clase
     */
    private  lateinit var binding: ActivityMainBinding
    private val Verificar: Verificar by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrarEventos()
    }
//Se usa binding para facilitarnos la vida
    private fun registrarEventos() {
        binding.btnResolver.setOnClickListener {
            val a = binding.a.text.toString().toFloat()
            val b = binding.b.text.toString().toFloat()
            val c = binding.c.text.toString().toFloat()
            Verificar.checar(a,b,c)
            comprobarValores(a,b,c)
        }
    }
    private fun comprobarValores(a : Float,b  : Float,c  : Float){
        if (Verificar.comprobacion.value == 1.0){
            val builder =AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("No es una ecuacion de 2do Grado")
                .setCancelable(false)
                .setPositiveButton("Aceptar"){_, _->
                    Verificar.reset()
                }
            builder.show()
        } else if (Verificar.comprobacion.value == 2.0) {
            //Alert Dialog HAce un cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Esta ecuación es de dos raíces")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    Verificar.x1(a,b,c,true)
                    Verificar.x2(a,b,c,true)
                    registrarObservadoresx1(true)
                    registrarObservadoresx2(true)
                }
            builder.show()

        }else if (Verificar.comprobacion.value == 3.0)
        {
            //Sirve para mandar el cuadro con una alerta al usuario
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Esta ecuación tiene dos raíces complejas")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    Verificar.x1(a,b,c,false)
                    Verificar.x2(a,b,c, false)
                    registrarObservadoresx1(false)
                    registrarObservadoresx2(false)
                }
            builder.show()
        }else if (Verificar.comprobacion.value == 4.0)
        {
            //Sirve para mandar el cuadro con una alerta al usuario
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion lineal solamente tiene una raiz: " + (-c/b))
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    Verificar.reset()
                }
            builder.show()
        }
    }
    private fun registrarObservadoresx1(condition: Boolean) {
        //Se registran con estas dos funciones los valores digitados por los
        //Usuarios
        if (condition){
            Verificar.x1.observe(this) {
                binding.x1.setText(it.toString())
            }}else{
            Verificar.x1.observe(this) {
                binding.x1.setText(it.toString() + " i")
            }
        }
    }
    private fun registrarObservadoresx2(condition:Boolean){
        if (condition){
            Verificar.x2.observe(this) {
                binding.x2.setText(it.toString())
            }}else {
            Verificar.x2.observe(this) {
                binding.x2.setText(it.toString() + " i")
            }
        }
    }
}