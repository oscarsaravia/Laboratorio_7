package com.example.android.lab4v2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invited_people_table")
data class People (

    @PrimaryKey(autoGenerate = true)
    var personId: Long = 0L,

    @ColumnInfo
    val name: String,

    val mail: String,

    val phone: String
)