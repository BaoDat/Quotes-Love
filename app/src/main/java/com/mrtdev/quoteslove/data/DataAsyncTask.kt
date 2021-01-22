package com.mrtdev.quoteslove.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mrtdev.quoteslove.utils.getJsonDataFromAsset
import io.reactivex.Observable
import java.io.IOException
import javax.inject.Inject

class DataAsyncTask @Inject constructor() {

    fun <T> getJsonData(context: Context, jsonFileName: String): Observable<MutableList<T>> {
        return Observable.create<MutableList<T>> { subscriber ->
            try {
                val jsonFileString = getJsonDataFromAsset(context, "$jsonFileName.json")
                val gSon = Gson()
                val listDataType = object : TypeToken<List<T>>() {}.type

                val dataList: MutableList<T> = gSon.fromJson(jsonFileString, listDataType)
                subscriber.onNext(dataList)
            } catch (e: IOException) {
                subscriber.onError(e)
            } finally {
                subscriber.onComplete()
            }
        }
    }
}