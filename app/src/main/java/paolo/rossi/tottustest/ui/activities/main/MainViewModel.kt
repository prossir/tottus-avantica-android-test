package paolo.rossi.tottustest.ui.activities.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import paolo.rossi.tottustest.data.repositories.UserRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var user_repository: UserRepository = UserRepository(application)

    fun logoutCurrentUser() : Int {
        return user_repository.logout()
    }

}