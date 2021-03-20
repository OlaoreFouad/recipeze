package dev.olaore.recipeze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import dev.olaore.recipeze.activities.RecipeActivity
import dev.olaore.recipeze.activities.SearchActivity
import dev.olaore.recipeze.fragments.FavoritesFragment
import dev.olaore.recipeze.fragments.HomeFragment
import dev.olaore.recipeze.fragments.SavedRecipesFragment
import dev.olaore.recipeze.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_recipeze.*
import kotlinx.android.synthetic.main.app_bar.*

class RecipezeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipeze)

        setSupportActionBar(recipeze_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        title = ""

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.recipeze_navigation_view)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, recipeze_toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.menu.findItem(R.id.nav_home))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_search -> {
                openSearch()
                true
            }
            else  -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean  {

        when(item.itemId) {
            R.id.nav_home -> setCurrentFragment(HomeFragment(), "homeFragment")
            R.id.nav_favorites -> setCurrentFragment(FavoritesFragment(), "favoritesFragment")
            R.id.nav_saved_recipes -> setCurrentFragment(SavedRecipesFragment(), "savedRecipesFragment")
            R.id.nav_settings -> setCurrentFragment(SettingsFragment(), "settingsFragment")
        }

        return true
    }

    private fun openSearch() {
        val searchIntent = Intent(this, SearchActivity::class.java)
        startActivity(searchIntent)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String) {
        drawerLayout.closeDrawer(GravityCompat.START)
        val fragmentTxn = supportFragmentManager.beginTransaction()
        fragmentTxn.replace(R.id.recipeze_fragment, fragment, tag)
        fragmentTxn.commit()
    }

    override fun onBackPressed() {
        drawerLayout.let {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT)
            } else {
                super.onBackPressed()
            }
        }
    }

}
