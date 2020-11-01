package paolo.rossi.tottustest.ui.activities.account_creation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import paolo.rossi.tottustest.ui.activities.login.LoginViewModel


@Suppress("UNCHECKED_CAST")
class AccountCreationViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountCreationViewModel::class.java)) {
            return AccountCreationViewModel(application) as T
        }
        throw IllegalArgumentException("Class different from AccountCreationViewModelFactory")
    }
}