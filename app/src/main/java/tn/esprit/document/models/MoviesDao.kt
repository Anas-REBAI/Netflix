package tn.esprit.document.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert
    fun insertMovie(movie: Movie): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>


    @Delete
    fun deleteMovie(movie: Movie)

}