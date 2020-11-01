package paolo.rossi.tottustest.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.ui.activities.splash.SplashIntent
import paolo.rossi.tottustest.ui.dialogs.WarningDialog


fun Context.MainIntent(): Intent {
    return Intent(this, MainActivity::class.java).apply {
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var view_model: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_teams, R.id.nav_out
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setElements()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun setElements() {
        view_model = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    /** On click function bound to an onclick event on a layout. Do not erase this is NOT an unused item*/
    fun logout(item: MenuItem) {
        val dialog = WarningDialog(
            context = this,
            dismissible = false,
            with_deny_option = true,
            title = "Finalizar sesión",
            message = "¿Estas seguro que deseas terminar tu sesión?",
            acceptance_title = "Sí",
            acceptance_function = {
                view_model.logoutCurrentUser()
                val logout_intent = SplashIntent()
                logout_intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(logout_intent)
            },
            denial_title = "No",
            denial_function = null
        )
        dialog.show()
    }
}