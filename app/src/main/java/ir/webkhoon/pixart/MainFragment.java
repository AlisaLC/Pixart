package ir.webkhoon.pixart;

import static android.content.Context.MODE_PRIVATE;

import static ir.webkhoon.pixart.model.Setting.SHARED_PREFS;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import ir.webkhoon.pixart.databinding.FragmentMainBinding;
import ir.webkhoon.pixart.model.Setting;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMainBinding.bind(view);
        binding.mainLoginButton.setOnClickListener(
                v -> Navigation.findNavController(v).navigate(R.id.loginFragment));
        binding.mainRegisterButton.setOnClickListener
                (v -> Navigation.findNavController(v).navigate(R.id.registerFragment));
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        long userId = sharedPreferences.getLong("user_id", 0L);
        if (userId != 0L) {
            Bundle bundle = new Bundle();
            bundle.putLong("user_id", userId);
            UserDao userDao = AppDatabase.getDatabase(getContext()).userDao();
            UserEntity user = userDao.getById(userId);

            Navigation.findNavController(view).navigate(R.id.loginFragment, bundle);
        }
    }
}
