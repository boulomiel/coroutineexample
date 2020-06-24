package com.rubenmimoun.coroutinemovieexample.model

import com.google.gson.annotations.SerializedName

class Movie(val title : String,
            val image : String,
            val rating : Double,
            @SerializedName("releaseYear")
            val releaseYear : Int,
            val genre : List<String>
            ){

    override fun toString(): String {
        return "Movie(title='$title', image='$image', rating=$rating, releaseYear=$releaseYear, genre=$genre)"
    }
}



