package tn.esprit.document

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tn.esprit.document.databinding.ActivityHome2Binding
import tn.esprit.document.fragments.MoviesFragment
import tn.esprit.document.fragments.ProfileFragment

class HomeActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_home_2)

        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding mtaa toolbar bch ngedo LogOut
        val toolbar: Toolbar = binding.toolbar.appBar
        setSupportActionBar(toolbar)

        // Navigation entre les fragments
        binding.btnNews.setOnClickListener(){
            changeFragment(MoviesFragment(), "")
        }

        binding.btnProfile.setOnClickListener(){
            changeFragment(ProfileFragment(), "")
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MoviesFragment()).commit()

    }


    // charge fragment
    private fun changeFragment(fragment: Fragment, name: String) {
        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit()
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