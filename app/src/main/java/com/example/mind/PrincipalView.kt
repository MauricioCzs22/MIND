package com.example.mind

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mind.chatbot2.ui.Chat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class PrincipalView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var  toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_view)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item1 -> { startActivity(Intent(this, cuenta::class.java ))}
            R.id.nav_item3 ->  { startActivity(Intent(this, ayuda::class.java ))}
            R.id.nav_item4 ->  { startActivity(Intent(this, Terminos::class.java ))}
            R.id.nav_item6 -> { startActivity(Intent(this, Chat::class.java ))}
            R.id.nav_item7 -> { startActivity(Intent(this, testUser::class.java ))}

            R.id.nav_item5 -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@PrincipalView, ingreso::class.java)
                Toast.makeText(applicationContext, "Has cerrado sesion",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                return true
            }
            else->super.onOptionsItemSelected(item)

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }






}