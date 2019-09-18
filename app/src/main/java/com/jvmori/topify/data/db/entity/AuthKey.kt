package com.jvmori.topify.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topify_key")
data class AuthKey (
    @PrimaryKey(autoGenerate = false)
    val id : Int = 1,
    var key : String = "",
    var timestamp: Long
)