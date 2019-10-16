package com.experiment.testthemoviedb.Data.api

import com.experiment.testthemoviedb.Data.models.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBInterface {
    //Populares: https://api.themoviedb.org/3/movie/popular?api_key=a4bb5fb9d9c1424d4c6e378f12fbc59f
    //Detalles:  https://api.themoviedb.org/3/movie/301528?api_key=a4bb5fb9d9c1424d4c6e378f12fbc59f
    //Base:      https://api.themoviedb.org/3/

    /*
    * //@Path es una etiqueta de retrofit que le dice que el parametro "id" es
    * //parte de la ruta del @GET identificado con la notacion movie_id
    * */
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path( "movie_id") id:Int): Single<MovieDetails>

}