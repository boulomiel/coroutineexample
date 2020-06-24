package com.rubenmimoun.coroutinemovieexample.retrofit

import com.rubenmimoun.coroutinemovieexample.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/json/movies.json")
    fun getMovies() : Call<List<Movie>>
}