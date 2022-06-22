package com.example.android_final

import android.R.attr
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.R.attr.right

import android.R.attr.left
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioFragment : Fragment() {
    private lateinit var db : AppDatabase
    private lateinit var testText : TextView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        db = (activity as MainActivity?)!!.getDbContext()
//
//        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-----------")
//        testText = requireActivity().findViewById(R.id.testText)
//        println(testText.text)
////        requireActivity().findViewById<TextView>(R.id.testText).text =  db.userCoinsDao().getAllData().toString()
////        println(db.userCoinsDao().getAllData().toString())
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = (activity as MainActivity?)!!.getDbContext()
//
        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-----------")
        testText = requireActivity().findViewById(R.id.testText)
        println(testText.text)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            getData()
        }
    }

    private fun getData() {
        Thread{
            val data = db.userCoinsDao().getAllData()
//            db.userCoinsDao().deleteData()
            requireActivity().runOnUiThread {
                testText.text = data.toString().takeLast(1000)
                println(data.toString())
            }

        }.start()
    }

    fun updateUI(){
//        Thread{
//            run
//        }
//        runOnUiThread(Dispatchers.Main) {
//            textView.text = it.name
//        }
    }

}