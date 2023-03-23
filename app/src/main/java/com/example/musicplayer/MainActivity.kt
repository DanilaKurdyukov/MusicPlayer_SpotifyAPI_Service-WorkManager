package com.example.musicplayer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var navController : NavController? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
        navController = navHostFragment!!.navController
        val appBarConfiguration = AppBarConfiguration.Builder(navController!!.graph)
            .build()
        val mainAppBar = findViewById<Toolbar>(R.id.mainAppBar)

        mainAppBar.navigationIcon = null

        setupWithNavController(mainAppBar, navController!!, appBarConfiguration)

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.searchFragment -> mainAppBar.navigationIcon = null
                R.id.myPlaylistsFragment -> mainAppBar.navigationIcon = null
                R.id.mainFragment -> mainAppBar.navigationIcon = null
            }
        }

        val navView = findViewById<BottomNavigationView>(R.id.mainNavigationView)
        navView.setupWithNavController(navController!!)
    }
}