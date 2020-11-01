package paolo.rossi.tottustest.ui.activities.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.repositories.UserRepository


class SplashViewModel(application: Application)
    : AndroidViewModel(application) {

    var user_repository: UserRepository

    init {
        user_repository = UserRepository(application)
    }

    fun isLoggedIn() : Boolean {
        return user_repository.retrieveIfLoggedIn() != null
    }
}