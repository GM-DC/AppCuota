package com.example.appconsultadeuda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.appconsultadeuda.Adapter.viewPagerAdapter
import com.example.appconsultadeuda.Login
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.databinding.ActyTabLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class actyTabLayout : AppCompatActivity() {
    private lateinit var binding : ActyTabLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
        eventsHandlers()
    }

    private fun eventsHandlers() {
        funcionTollbar()
    }
    private fun setupTabLayout() {
        TabLayoutMediator(binding.tlTabLayout, binding.vpViewPager) {
                tab, position ->
                    when (position) {
                        0 -> { tab.text = "INFORME"}
                        1 -> { tab.text = "DETALLE"}
                    }
        }.attach()
    }
    private fun setupViewPager() {
        val adapter = viewPagerAdapter(this, 2)
        binding.vpViewPager.adapter = adapter
    }
    override fun onBackPressed() {
        val viewPager = binding.vpViewPager
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    //************************* Tollbar ********************************
    private fun funcionTollbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = null
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_login, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_configuracion -> {
                val i = Intent(applicationContext, actyCambioContrasena::class.java)
                startActivity(i)
                super.onBackPressed()
                true
            }
            R.id.item_cerrarSesion -> {
                val i = Intent(applicationContext, Login::class.java)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
