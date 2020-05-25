package com.example.android.lab4v2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ZventDatabaseDao {

    @Insert
    fun insert(person: People)

    @Update
    fun update(person:People)

    @Query("SELECT * FROM invited_people_table ORDER BY name")
    fun getPerson(): LiveData<List<People>>

}