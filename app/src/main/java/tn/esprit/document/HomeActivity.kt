package tn.esprit.document

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import tn.esprit.document.databinding.ActivityHomeBinding
import tn.esprit.document.fragments.MoviesFragment
import tn.esprit.document.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    // ndeclari variable de type bottom Navigation View
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // to activate any btn in toolbar (LogOut)
        val toolbar : Toolbar = binding.toolbar.appBar
        setSupportActionBar(toolbar)

        // to activate bottom navigation
        bottomNavigationView = binding.bottomNavigation  // Id mtaa bar navigation fl fichier home.xml
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> loadFragment(MoviesFragment())
                R.id.item_profile -> loadFragment(ProfileFragment())
            }
            true
        }

        // Set the default fragment : eli bch ylanci aleha
        if (savedInstanceState == null) {
            loadFragment(MoviesFragment())
        }

    }


    private fun loadFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_content_main, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        // remove alert button from News fragment //
       /* val panier = findViewById<ImageButton>(R.id.panier)
        if (fragment is storefragment) {
            panier.visibility = View.VISIBLE
        } else {
            panier.visibility = View.GONE
        } */
        //  END remove alert button from News fragment //

        // fragment title //

       /* val fragmentTitle = findViewById<TextView>(R.id.fragmenttitle)
        when (fragment) {
            is newsfragment -> fragmentTitle.text = "News"
            is userfragment -> fragmentTitle.text = "Profile"
            else -> fragmentTitle.text = "Store"
        } */
        // END fragment title //
    }


    // ayh haja f toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            /*    R.id.infoMenu -> {
                    changeFragment(AboutFragment(),"AboutMe")
                } */
            R.id.logoutMenu ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("Are you sure you want to logout ?")
                builder.setPositiveButton("Yes"){ dialogInterface, which ->
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}