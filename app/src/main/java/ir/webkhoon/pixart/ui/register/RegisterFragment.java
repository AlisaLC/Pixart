package ir.webkhoon.pixart.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ir.webkhoon.pixart.R;
import ir.webkhoon.pixart.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRegisterBinding.bind(view);
        binding.registerButton.setOnClickListener(v -> {
            String email = binding.registerEmailEdt.getText().toString();
            if (!email.matches("\\w+@\\w+\\.\\w+")) {
                binding.registerEmailEdt.setError("email not valid!");
                return;
            }
            String password = binding.registerPasswordEdt.getText().toString();
            if (password.length() < 4) {
                binding.registerPasswordEdt.setError("password too short!");
                return;
            }
            AppDatabase db = AppDatabase.getDatabase(getContext());
            UserDao userDao = db.userDao();
            if (userDao.getByEmail(email) != null) {
                binding.registerEmailEdt.setError("email already used!");
                return;
            }
            userDao.registerUser(new UserEntity(email, password));
            long userId = userDao.getByEmail(email).user_id;
            ProfileDao profileDao = db.profileDao();
            profileDao.addProfile(new ProfileEntity(userId, "", email, ""));
            SettingDao settingDao = db.settingDao();
            settingDao.addSetting(new SettingEntity(userId, 0, 4, 9));
            Snackbar.make(view, "User registered successfully", Snackbar.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.mainFragment);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
