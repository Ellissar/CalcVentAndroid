package com.my.calcvent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Group
import java.math.RoundingMode
import java.util.logging.Logger
import kotlin.math.pow


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTabItem1.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTabItem1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editTextNumberSpeed: EditText
    private lateinit var radioButtonCirc: RadioButton
    private lateinit var editTextNumberFlow: EditText
    private lateinit var radioButtonRect: RadioButton
    private lateinit var editTextNumberCirc: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_tab_item1, container, false)

        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radioGroup)
        val groupRect = rootView.findViewById<Group>(R.id.groupRect)
        val groupCirc = rootView.findViewById<Group>(R.id.groupCirc)

        editTextNumberSpeed = rootView.findViewById(R.id.editTextNumberSpeed)
        radioButtonCirc = rootView.findViewById(R.id.radioButtonCirc)
        editTextNumberFlow = rootView.findViewById(R.id.editTextNumberFlow)
        radioButtonRect = rootView.findViewById(R.id.radioButtonRect)
        editTextNumberCirc = rootView.findViewById(R.id.editTextNumberCirc)

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

        val buttonFloat = rootView.findViewById<Button>(R.id.buttonFloat)
        buttonFloat.setOnClickListener(::calcFlow)

        val buttonSpeed = rootView.findViewById<Button>(R.id.buttonSpeed)
        buttonSpeed.setOnClickListener(::calcSpeed)

        // Inflate the layout for this fragment
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTabItem1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTabItem1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    //Вычисление площади квадратного воздуховода
    private fun SRect (): Double {
        val editTextNumberRectA = activity?.findViewById<EditText>(R.id.editTextNumberRectA)
        val editTextNumberRectB = activity?.findViewById<EditText>(R.id.editTextNumberRectB)

        val a: Double = try{ editTextNumberRectA?.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите длину стороны а", Toast.LENGTH_SHORT).show()
            return 0.0
        }

        val b: Double = try{ editTextNumberRectB?.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите длину стороны b", Toast.LENGTH_SHORT).show()
            return 0.0
        }
        return (a / 1000.0) * ( b / 1000.0)
    }


    //Вычисление площади круглого воздуховода
    private fun SCirc (): Double {
        val d: Double = try{ editTextNumberCirc.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите диаметр воздуховода", Toast.LENGTH_SHORT).show()
            return 0.0
        }
        return Math.PI * (d / 1000.0).pow(2) / 4.0
    }


    //Вычисление расхода воздуха
    private fun calcFlow (view: View){
        //L = 3600 * S * V
        //for circle S = Pi * d^2 / 4     d должен быть в метрах
        var L: Double
        val V: Double = try {
            editTextNumberSpeed.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите скорость воздуха в воздуховоде, м/с", Toast.LENGTH_SHORT).show()
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
    private fun calcSpeed (view: View){
        //V = G / S     G should be in m^3/s     S should be in m^2
        var V: Double
        var G: Double = try {
            editTextNumberFlow.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите расход воздуха в воздуховоде, м^3/ч", Toast.LENGTH_SHORT).show()
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
}