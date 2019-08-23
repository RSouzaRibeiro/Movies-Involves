package com.rafaelsouza.moviesinvolves.repository.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class Converters {

    @TypeConverter
    fun stringToArrayList(value: String): ArrayList<Genre> {
        val listType = object : TypeToken<ArrayList<Genre>>() {

        }.type
        return Gson().fromJson(value, listType)
        // return value == null ? null : new Date(value);
    }

    @TypeConverter
    fun arraylistToString(list: ArrayList<Genre>): String {
        val gson = Gson()

        return gson.toJson(list)
        // return date == null ? null : date.getTime();
    }
}