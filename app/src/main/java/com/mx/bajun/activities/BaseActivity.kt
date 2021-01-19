package com.mx.bajun.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.mx.bajun.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
        setUpToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflateMenu(menu)
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

    override fun onBackPressed() {
        Toast.makeText(this, "Back press", Toast.LENGTH_SHORT).show()
        super.onBackPressed()
    }

    override fun setContentView(view: View?) {
        /*super.setContentView(view)*/
        val params : ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(view, params)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        /*super.setContentView(view, params)*/
        val activityContainer : FrameLayout = findViewById(R.id.activity_container)
        activityContainer.addView(view, params)
    }

    override fun setContentView(layoutResID: Int) {
        /*super.setContentView(layoutResID)*/
        val activityContainer : FrameLayout = findViewById(R.id.activity_container)
        val inflater : LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val params : ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val stubView : View = inflater.inflate(layoutResID, activityContainer, false)
        activityContainer.addView(stubView, params)
    }

    private fun setUpToolbar() {
        val toolbar:Toolbar = findViewById(R.id.base_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white)
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
        onBackPressed()
    }
}