package tn.esprit.document

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import tn.esprit.document.models.AppDatabase
import tn.esprit.document.models.Movie

class addMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        val titleEditText = findViewById<EditText>(R.id.movieNameEnter)
        val descriptionEditText = findViewById<EditText>(R.id.DescriptionEnter)
        val addButton = findViewById<Button>(R.id.addMovie)

        val imageResources = listOf(
            R.drawable.poster,
            R.drawable.dark,
            R.drawable.forrest,
            R.drawable.godfather,
            R.drawable.pulp,
            R.drawable.redemption
        )
        val randomImageRes = imageResources.random()

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val movie = Movie(0, randomImageRes, title, description)

            // Insert movie into database
            val db = AppDatabase.getInstance(this)
            db.moviesDao().insertMovie(movie)

            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

    }
}