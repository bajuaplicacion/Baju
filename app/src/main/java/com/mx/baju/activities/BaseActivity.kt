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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setUpToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        inflateMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> backPressButton()
            R.id.menu_diccionario -> goToDiccionario()
            R.id.menu_redesSociales -> goToRedesSociales()
            R.id.menu_configuracion -> goToConfiguracion()
        }
        return true
    }

    private fun setUpToolbar() {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white)
        showBackNavigationButton(true)
    }

    private fun inflateMenu(menu: Menu?) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    public fun showBackNavigationButton(show : Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setDisplayShowHomeEnabled(show)
    }

    public fun changeTitle(title:String) {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        toolbar.title = title
    }

    public fun changeSubTitle (subtTitle : String) {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        toolbar.subtitle = subtTitle
    }

    public fun showMenu(show : Boolean) {
        val toolbar : Toolbar = findViewById(R.id.base_toolbar)
        if (show) {
            inflateMenu(toolbar.menu)
        } else {
            toolbar.menu.clear()
        }
    }

    public fun goToDiccionario() {
        Toast.makeText(this, "Diccionario", Toast.LENGTH_SHORT).show()
    }

    public fun goToRedesSociales() {
        Toast.makeText(this, "Redes sociales", Toast.LENGTH_SHORT).show()
    }

    public fun goToConfiguracion() {
        Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show()
    }

    public fun backPressButton() {
        Toast.makeText(this, "Back press", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}