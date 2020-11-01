package paolo.rossi.tottustest.ui.activities.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.repositories.UserRepository


class SplashViewModel(application: Application)
    : AndroidViewModel(application) {

    private var user_repository: UserRepository = UserRepository(application)

    fun isLoggedIn() : Boolean {
        return user_repository.retrieveIfLoggedIn() != null
    }
}