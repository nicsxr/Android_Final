package com.example.android_final

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.api.ApiInterface
import com.example.android_final.api.CoinsAdapter
import com.example.android_final.models.api.Coins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() , ConnectivityReceiver.ConnectivityReceiverListener{
    private val baseUrl = "https://api.coingecko.com/api/v3/"

    lateinit var resourcesAdapter: CoinsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getCoins(requireActivity())
    }

    private fun getCoins(context: Context){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(ApiInterface::class.java)

        val data = retrofitBuilder.getCoins()

        data.enqueue(object : Callback<Coins?> {
            override fun onResponse(
                call: Call<Coins?>,
                response: Response<Coins?>
            ) {
                val body = response.body()!!
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView = requireActivity().findViewById(R.id.coinsRecyclerView)
                if (recyclerView.visibility == View.INVISIBLE)
                    recyclerView.visibility = View.VISIBLE
                recyclerView.layoutManager = linearLayoutManager
                resourcesAdapter = CoinsAdapter(requireContext(), body)
                resourcesAdapter.notifyDataSetChanged()
                recyclerView.adapter = resourcesAdapter
                println(body)
            }

            override fun onFailure(call: Call<Coins?>, t: Throwable) {
                println(t)
                println("------------------------------------------")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(!isConnected){
            val toast = Toast.makeText(activity, "You are offline", Toast.LENGTH_SHORT)
            println("------------Disconn-------------")
            recyclerView.visibility = View.INVISIBLE
            toast.show()
        }else{
            getCoins(requireActivity())
            println("-------------Conn---------------")
        }
    }
}