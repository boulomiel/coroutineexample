package com.rubenmimoun.coroutinemovieexample.retrofit

import com.rubenmimoun.coroutinemovieexample.model.Movie
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service    {

    companion object{
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.androidhive.info/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val repository = retrofit.create(API::class.java)

    }

    fun getMovies(callback: Callback<List<Movie>>){
        val call = repository.getMovies()
        call.enqueue(callback)
    }



}