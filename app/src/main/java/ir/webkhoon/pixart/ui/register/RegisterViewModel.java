package ir.webkhoon.pixart.ui.register;

public class RegisterViewModel extends androidx.lifecycle.ViewModel {

    private final androidx.lifecycle.MutableLiveData<String> mText;

    public RegisterViewModel() {
        mText = new androidx.lifecycle.MutableLiveData<>();
        mText.setValue("This is loginRegister fragment");
    }

    public androidx.lifecycle.LiveData<String> getText() {
        return mText;
    }
}
