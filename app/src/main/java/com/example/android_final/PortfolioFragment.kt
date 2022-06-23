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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.api.AverageCoinAdapter
import com.example.android_final.api.CoinsAdapter
import com.example.android_final.models.AverageCoins
import com.example.android_final.models.api.Coins
import com.example.android_final.models.api.CoinsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioFragment : Fragment() {
    private lateinit var db : AppDatabase
    lateinit var resourcesAdapter: AverageCoinAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = (activity as MainActivity?)!!.getDbContext()

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView = requireActivity().findViewById(R.id.averageRecycler)
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            getData()
        }
    }

    private fun getData() {
        Thread{
            val data = db.userCoinsDao().getAverageData()
            requireActivity().runOnUiThread {
                resourcesAdapter = AverageCoinAdapter(requireContext(), data)
                resourcesAdapter.notifyDataSetChanged()
                recyclerView.adapter = resourcesAdapter
            }

        }.start()
    }
}