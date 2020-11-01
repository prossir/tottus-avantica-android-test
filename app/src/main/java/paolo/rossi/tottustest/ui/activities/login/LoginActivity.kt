package paolo.rossi.tottustest.ui.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import paolo.rossi.tottustest.R


fun Context.LoginIntent(): Intent {
    return Intent(this, LoginActivity::class.java).apply {
    }
}


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun tryToLogin(view: View) {

    }


    fun goToAccountCreation(view: View) {

    }


}