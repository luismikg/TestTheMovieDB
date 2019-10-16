package com.experiment.testthemoviedb.Data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.experiment.testthemoviedb.Data.api.TheMovieDBInterface
import com.experiment.testthemoviedb.Data.models.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkDataSource (private val apiService:TheMovieDBInterface, private val composeDisposable: CompositeDisposable){


    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadMovieDetailsResponse:MutableLiveData<MovieDetails>
    get() = _downloadMovieDetailsResponse

    fun fetchMovieDetails(movieid: Int){

        _networkState.postValue(NetworkState.LOADING)

        //Network calls:
        try {
            composeDisposable.add(
                apiService.getMovieDetails( movieid )
                    .subscribeOn(Schedulers.io()) //Schedulers.io() sera el escuchador
                    .subscribe( //Una vez se reciba la respuesta se ejecuta para exito:
                        {
                            _downloadMovieDetailsResponse.postValue( it )
                            _networkState.postValue( NetworkState.LOADED )
                        }, // para error:
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message)
                        }
                    )
            )
        }catch ( e:Exception){
            Log.e("MovieDetailsDataSource", e.message)
        }
    }


}