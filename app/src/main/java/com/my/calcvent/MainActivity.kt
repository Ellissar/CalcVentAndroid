package com.my.calcvent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonCirc){
                groupRect.visibility = View.INVISIBLE
                groupCirc.visibility = View.VISIBLE
            }

            if (checkedId == R.id.radioButtonRect){
                groupRect.visibility = View.VISIBLE
                groupCirc.visibility = View.INVISIBLE
            }
        })
    }


    //Вычисление площади квадратного воздуховода
    fun SRect (): Double {
        val a: Double = try{ editTextNumberRectA.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "Введите длину стороны а", Toast.LENGTH_SHORT).show()
            return 0.0
        }

        val b: Double = try{ editTextNumberRectB.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "Введите длину стороны b", Toast.LENGTH_SHORT).show()
            return 0.0
        }
        return (a / 1000.0) * ( b / 1000.0)
    }


    //Вычисление площади круглого воздуховода
    fun SCirc (): Double {
        val d: Double = try{ editTextNumberCirc.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "Введите диаметр воздуховода", Toast.LENGTH_SHORT).show()
            return 0.0
        }
        return Math.PI * (d / 1000.0).pow(2) / 4.0
    }


    //Вычисление расхода воздуха
    fun calcFlow (view: View){
        //L = 3600 * S * V
        //for circle S = Pi * d^2 / 4     d должен быть в метрах
        var L: Double
        val V: Double = try {
            editTextNumberSpeed.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "Введите скорость воздуха в воздуховоде, м/с", Toast.LENGTH_SHORT).show()
            return
        }


        if (radioButtonCirc.isChecked){
            L = 3600.0 * SCirc() * V
            L = L.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
            editTextNumberFlow.setText("$L")
        }

        if (radioButtonRect.isChecked){
            L = 3600.0 * SRect() * V
            L = L.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
            editTextNumberFlow.setText("$L")
        }
    }


    //Вычисление скорости воздуха
    fun calcSpeed (view: View){
        //V = G / S     G should be in m^3/s     S should be in m^2
        var V: Double
        var G: Double = try {
            editTextNumberFlow.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "Введите расход воздуха в воздуховоде, м^3/ч", Toast.LENGTH_SHORT).show()
            return
        }
        G = G / 3600 //m^3/h to m^3/s

        if (radioButtonCirc.isChecked){
            V = G / SCirc()
            V = V.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
            editTextNumberSpeed.setText("$V")
        }

        if (radioButtonRect.isChecked){
            V = G / SRect()
            V = V.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
            editTextNumberSpeed.setText("$V")
        }
    }


    fun randomMe (view: View){
        val randomIntent = Intent(this, SecondActivity::class.java)
        startActivity(randomIntent)
    }
}