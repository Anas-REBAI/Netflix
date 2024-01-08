package tn.esprit.document.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @DrawableRes
    val image:Int,

    val title: String,
    val description: String,
)
