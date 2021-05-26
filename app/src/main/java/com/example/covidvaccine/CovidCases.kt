package com.example.covidvaccine

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class CovidCases : AppCompatActivity() {
    lateinit var WorldCases: TextView
    lateinit var WorldRecovered: TextView
    lateinit var WorldDeaths: TextView
    lateinit var countryCases: TextView
    lateinit var countryRecovered: TextView
    lateinit var countryDeaths: TextView
    lateinit var stateRV: RecyclerView
    lateinit var stateRVAdapter: stateRVAdapter
    lateinit var stateList: List<StateModel>
    lateinit var loadingPB: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_cases)
        WorldCases = findViewById(R.id.WorldCases)
        WorldRecovered = findViewById(R.id.WorldRecovered)
        WorldDeaths = findViewById(R.id.WorldDeaths)
        countryCases = findViewById(R.id.IndianCases)
        countryRecovered = findViewById(R.id.IndianRecovered)
        countryDeaths = findViewById(R.id.IndianDeaths)
        stateRV = findViewById(R.id.RvState)
        stateList = ArrayList<StateModel>()
        //loadingPB=findViewById(R.id.PbLoading)
        getStateinfo()
        getWotldinfo()
    }

    private fun getStateinfo() {
        val url = "https://api.rootnet.in/covid19-in/stats/latest"
        val queue = Volley.newRequestQueue(this@CovidCases)
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val dataobj = response.getJSONObject("data")
                    val summary = dataobj.getJSONObject("summary")
                    val cases: Int = summary.getInt("total")
                    val recovered = summary.getInt("discharged")
                    val deaths = summary.getInt("deaths")

                    countryCases.text = cases.toString()
                    countryRecovered.text = recovered.toString()
                    countryDeaths.text = deaths.toString()

                    val regionalarray = dataobj.getJSONArray("regional")
                    for (i in 0 until regionalarray.length()) {
                        val regionalobj = regionalarray.getJSONObject(i)
                        val stateName: String = regionalobj.getString("loc")
                        val cases: Int = regionalobj.getInt("totalConfirmed")
                        val deaths: Int = regionalobj.getInt("deaths")
                        val recvered: Int = regionalobj.getInt("discharged")

                        val statemodal = StateModel(stateName, recovered, deaths, cases)
                        stateList += statemodal
                    }
                    stateRVAdapter = stateRVAdapter(stateList)
                    stateRV.layoutManager = LinearLayoutManager(this)
                    stateRV.adapter = stateRVAdapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Fail to get data", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)

    }

    private fun getWotldinfo() {
        val url = "https://disease.sh/v3/covid-19/all"
        val queue = Volley.newRequestQueue(this@CovidCases)
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val worldcases: Int = response.getInt("cases")
                val worldrecovered: Int = response.getInt("recovered")
                val worldDeaths: Int = response.getInt("deaths")
                WorldRecovered.text = worldrecovered.toString()
                WorldDeaths.text = worldDeaths.toString()
                WorldCases.text = worldcases.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            Toast.makeText(this, "Fail to get data", Toast.LENGTH_SHORT).show()
        })
        queue.add(request)
    }

}