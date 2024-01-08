package tn.esprit.document.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tn.esprit.document.R
import tn.esprit.document.adapter.MovieAdapter
import tn.esprit.document.addMovieActivity
import tn.esprit.document.models.AppDatabase

class MoviesFragment : Fragment() {

   // private var movieList: ArrayList<Movie> = ArrayList()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        // btn add
        val btnAddProduct = view?.findViewById<FloatingActionButton>(R.id.btnAddProduct)
        btnAddProduct?.setOnClickListener {
            val intent = Intent(activity, addMovieActivity::class.java)
            startActivity(intent)
        }

        // Trouvez la référence du RecyclerView à partir du FICHIER XML
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_movies)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialisez et configurez l'adapter
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter


        // Récupérez la liste mise à jour depuis la base de données
        val db = AppDatabase.getInstance(requireContext())
        val movieList = db.moviesDao().getAllMovies()

        movieAdapter.setList(movieList)


        // Définissez les données dans l'adaptateur (hedha maghir Room DATABASE)
      /*  movieList.add(Movie(1, R.drawable.poster, "Inception", "Science Fiction"))
        movieList.add(Movie(2, R.drawable.redemption, "The Shawshank Redemption", "Drama"))
        movieList.add(Movie(3, R.drawable.dark, "The Dark Knight", "Action"))
        movieList.add(Movie(4, R.drawable.pulp, "Pulp Fiction", "Crime"))
        movieList.add(Movie(5, R.drawable.godfather, "The Godfather", "Crime"))
        movieList.add(Movie(6, R.drawable.forrest, "Forrest Gump", "Drama"))  */

        return view
    }

}