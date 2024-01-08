package tn.esprit.document.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.document.R
import tn.esprit.document.models.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

  //  var movieList: ArrayList<Movie> = ArrayList()

    var movieList: List<Movie> = ArrayList()

    fun setList(list: List<Movie>){
       // this.movieList = list

        // hedhom bl room
        this.movieList = list
        notifyDataSetChanged()
    }

    inner class MovieHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        // eli f single item
        var image: ImageView = itemView.findViewById(R.id.movieImage)
        var title: TextView = itemView.findViewById(R.id.movieTitle)
        var description: TextView = itemView.findViewById(R.id.movieDescription)

        fun bind(movie: Movie){
            with(itemView) {
                // Static
                image.setImageResource(movie.image)
                title.text = movie.title
                description.text = movie.description
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_item_movie_home, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        // Configurez les vues à l'intérieur de chaque élément
        var movie: Movie = movieList.get(position)
        holder.bind(movie)
    }

}