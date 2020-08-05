package dev.olaore.recipeze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_recipeze.*
import kotlinx.android.synthetic.main.app_bar.*

class RecipezeActivity : AppCompatActivity() {

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

        navigationView.setNavigationItemSelectedListener {
            val id = it.itemId

            // make selection

            false
        }

    }
}
