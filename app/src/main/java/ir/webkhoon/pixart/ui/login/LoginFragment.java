package ir.webkhoon.pixart.ui.login;

import static android.content.Context.MODE_PRIVATE;

import static ir.webkhoon.pixart.model.Setting.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ir.webkhoon.pixart.LandingActivity;
import ir.webkhoon.pixart.R;
import ir.webkhoon.pixart.databinding.FragmentLoginBinding;
import ir.webkhoon.pixart.model.room.AppDatabase;
import ir.webkhoon.pixart.model.room.UserDao;
import ir.webkhoon.pixart.model.room.UserEntity;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        binding.loginButton.setOnClickListener(v -> {
            String email = binding.loginEmailEdt.getText().toString();
            String password = binding.loginPasswordEdt.getText().toString();
            if (!email.matches("\\w+@\\w+\\.\\w+")) {
                binding.loginEmailEdt.setError("email not valid!");
                return;
            }
            UserDao userDao = AppDatabase.getDatabase(getContext()).userDao();
            UserEntity user = userDao.getByEmail(email);
            if (user == null || !user.password.equals(password)) {
                Snackbar.make(view, "username or password incorrect!", Snackbar.LENGTH_LONG).show();
                return;
            }
            Snackbar.make(view, "User logged in successfully", Snackbar.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong("user_id", user.user_id);
            edit.apply();
            Bundle bundle = new Bundle();
            bundle.putLong("user_id", user.user_id);
            Navigation.findNavController(v).navigate(R.id.landingFragment, bundle);
        });
        if (getArguments().getLong("user_id") != 0L) {
            startActivity(new Intent(getContext(), LandingActivity.class));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
