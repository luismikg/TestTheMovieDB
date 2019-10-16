package com.experiment.testthemoviedb.Data.repository

import android.content.res.Resources
import com.experiment.testthemoviedb.R


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {

    //Variables staticas:
    companion object{
        //val por que no queremos que cambie su valor
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState( Status.SUCCESS, "SUCCESS")//Resources.getSystem().getString(R.string.success))
            LOADING = NetworkState( Status.RUNNING, "RUNNING") //Resources.getSystem().getString(R.string.running))
            ERROR = NetworkState( Status.FAILED, "FAILED") //Resources.getSystem().getString(R.string.wrog))
        }
    }
}