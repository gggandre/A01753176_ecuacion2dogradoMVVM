package mx.itesm.ag.a01753176_ecuacion2dogradomvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.ag.a01753176_ecuacion2dogradomvvm.model.LogicaPrograma

/**
 *@author: Gilberto André García Gaytán
 * El código @Verificar nos sirve para usar el mutablelivedata
 * y de igual manera se conectan los valores de el modelo y la vista respectivamente
 *
 */

class Verificar : ViewModel()
{
    private  val modelo = LogicaPrograma()
    val x1 = MutableLiveData<String>("")
    val x2 = MutableLiveData<String>("")
    val comprobacion = MutableLiveData<Double>(0.0)
    fun x1(a: Float, b:Float, c:Float, con:Boolean){
        x1.value = modelo.x1(a,b,c,con)
    }
    fun x2(a: Float, b:Float, c:Float,con:Boolean){
        x2.value = modelo.x2(a,b,c,con)
    }
    //Comprobacion de los valores
    fun checar(a: Float, b:Float, c:Float){
        comprobacion.value = modelo.comprobarlosDatos(a,b,c)
    }
    // Se resetean los valores como se vio durante clase
    fun reset() {
        modelo.reset()
        refreshValues()
    }
    //Actualizar los valores como se vio en clase
    private fun refreshValues(){
        x1.value = modelo.x1
        x2.value = modelo.x2
    }
}