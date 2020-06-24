package com.rubenmimoun.coroutinemovieexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubenmimoun.coroutinemovieexample.model.Movie
import com.rubenmimoun.coroutinemovieexample.retrofit.Service
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<Movie>> {

    companion object{

        private val job  =  Job()
        private val frontDispatcher =  Dispatchers.Main
        private val exception =
            CoroutineExceptionHandler{_,throwable->
                GlobalScope.launch(Dispatchers.Main) { Log.e("error", "CoroutineManager  $throwable") }
            }

        private var coroutineScope : CoroutineScope? =null

        fun newInstance() : CoroutineScope {
            return if (coroutineScope  != null){
                coroutineScope!!
            }else{
                coroutineScope = CoroutineScope(frontDispatcher + job + exception )
                coroutineScope!!
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        launchDownload()


    }
    override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
        Log.e("error retrofit", t.toString())
    }

    override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
        val res =  response.body()
        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapterImages = AdapterImages(res!!, this)
        recycler_view.adapter = adapterImages
        Log.i("response retrofit", res.toString())
    }



    fun launchDownload(){
        newInstance().launch (Dispatchers.IO){
            Service().getMovies(this@MainActivity)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
