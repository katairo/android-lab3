package hse.course.android_lab3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hse.course.android_lab3.adapter.AdapterImpl
import hse.course.android_lab3.api.NewsApi
import hse.course.android_lab3.model.ApiResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_activity_layout)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = AdapterImpl(ArrayList())

        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val searchEditText: EditText = findViewById(R.id.search_edit_text)
        val search: String = searchEditText.text.toString()

        if (search.isEmpty()) {
            return
        }

        val newsApi = NewsApi.create()
        newsApi.getNewsData("pub_34302ec70200c4219b84392d2a5c6a759d99c", search, "ru").enqueue(object : Callback<ApiResponseData> {

            override fun onResponse(
                call: Call<ApiResponseData>,
                response: Response<ApiResponseData>
            ) {
                val apiResponseData: ApiResponseData? = response.body()

                if (apiResponseData?.results != null) {
                    Log.v("INFO", "yes")
                    recyclerView.adapter = AdapterImpl(apiResponseData.results)
                }
            }

            override fun onFailure(call: Call<ApiResponseData>, t: Throwable) {
                Log.v("INFO", "no")
            }
        })
    }
}