package com.mx.baju.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.mx.baju.R

class BaseActivity : AppCompatActivity() {

    //private var menu : Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setUpToolbar()
        setUpUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        inflateMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> backPressButton()
        }
        return true
    }

    private fun setUpToolbar() {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white)
        showBackNavigationButton(true)
    }

    private fun setUpUI() {
        val button : Button = findViewById(R.id.btnHide)
        button.setOnClickListener {
            val toolbar : Toolbar = findViewById(R.id.base_toolbar)
            if (toolbar.menu.hasVisibleItems()) {
                showMenu(false)
            } else {
                showMenu(true)
            }
        }
    }

    private fun inflateMenu(menu: Menu?) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    fun showBackNavigationButton(show : Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setDisplayShowHomeEnabled(show)
    }

    fun changeTitle(title:String) {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        toolbar.title = title
    }

    fun changeSubTitle (subtTitle : String) {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        toolbar.subtitle = subtTitle
    }

    fun showMenu(show : Boolean) {
        val toolbar : Toolbar = findViewById(R.id.base_toolbar)
        if (show) {
            inflateMenu(toolbar.menu)
        } else {
            toolbar.menu.clear()
        }
    }

    fun backPressButton() {
        Toast.makeText(this, "Back press", Toast.LENGTH_SHORT).show()
        //onBackPressed()
    }
}