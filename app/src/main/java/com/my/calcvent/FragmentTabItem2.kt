package com.my.calcvent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.math.RoundingMode

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTabItem2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTabItem2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val rootView = inflater.inflate(R.layout.fragment_tab_item2, container, false)

        val buttonCalc = rootView.findViewById<Button>(R.id.buttonCalc)
        buttonCalc.setOnClickListener(::calcDiscrepancy)
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
         * @return A new instance of fragment FragmentTabItem2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTabItem2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //вычисление невязки
    private fun calcDiscrepancy(view: View?) {
        val editTextNumberProject = activity?.findViewById<EditText>(R.id.editTextNumberProject)
        val editTextNumberFact = activity?.findViewById<EditText>(R.id.editTextNumberFact)
        val editTextNumberResult = activity?.findViewById<EditText>(R.id.editTextNumberResult)

        val proj: Double = try{ editTextNumberProject?.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите расход воздуха по проекту", Toast.LENGTH_SHORT).show()
            return
        }

        val fact: Double = try{ editTextNumberFact?.text.toString().toDouble()
        } catch (e: Exception) {
            Toast.makeText(activity, "Введите расход воздуха фактический", Toast.LENGTH_SHORT).show()
            return
        }

        var result: Double = ((fact - proj)/proj)*100
        result = result.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()
        editTextNumberResult?.setText("$result")
    }
}