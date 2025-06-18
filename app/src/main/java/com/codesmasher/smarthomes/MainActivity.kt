package com.codesmasher.smarthomes

import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: MaterialToolbar
    var bluetoothSocket: BluetoothSocket?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close



        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        // Load HomeFragment by default
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.content_frame, HomeFragment())
//            .commit()
        if (savedInstanceState == null) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction().addToBackStack(null)
            transaction.replace(R.id.fragment_container, BluetoothFragment())
            transaction.commit()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // handle Home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment()).addToBackStack(null)
                        .commit()
                }
                R.id.nav_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AboutFragment()).addToBackStack(null).commit()

                    // handle Settings
                }
                R.id.nav_bluetooth->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BluetoothFragment())
                        .commit()
                }
                R.id.nav_buttons->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                        ButtonFragment()).addToBackStack(null).commit()
                }
            }
            drawerLayout.closeDrawers()
            true
        }


    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    fun sendCommand(command:String){
        try {
            bluetoothSocket?.outputStream?.write((command + "\n").toByteArray())
            bluetoothSocket?.outputStream?.flush()
        }catch(e: Exception) {
        Toast.makeText(this, "Failed to send command", Toast.LENGTH_SHORT).show()
    }
    }
}