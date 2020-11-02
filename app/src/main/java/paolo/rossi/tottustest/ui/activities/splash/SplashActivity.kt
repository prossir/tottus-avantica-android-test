package paolo.rossi.tottustest.ui.activities.splash

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.services.WakeUpService
import paolo.rossi.tottustest.ui.activities.main.MainIntent
import paolo.rossi.tottustest.ui.activities.login.LoginIntent


fun Context.SplashIntent(): Intent {
    return Intent(this, SplashActivity::class.java).apply {
    }
}


class SplashActivity : AppCompatActivity() {

    private lateinit var view_model : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sendToCorrectNavigation()
        startService()
    }


    override fun onResume() {
        super.onResume()
        sendToCorrectNavigation()
    }


    private fun sendToCorrectNavigation() {
        Snackbar.make(findViewById(android.R.id.content), "Inicializando ...", Snackbar.LENGTH_LONG).setAction("Ok", null).show()
        Handler().postDelayed({
            view_model = ViewModelProvider(this).get(SplashViewModel::class.java)
            if(view_model.isLoggedIn()) {
                startActivity(MainIntent())
            } else {
                startActivity(LoginIntent())
            }
        }, 3000)
    }


    private fun startService() {
        Intent(this, WakeUpService::class.java).also {
            it.action = "START"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(it)
                return
            }
            startService(it)
        }
    }

}