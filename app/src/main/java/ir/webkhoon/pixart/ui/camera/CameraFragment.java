package ir.webkhoon.pixart.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.ByteArrayOutputStream;

import ir.webkhoon.pixart.databinding.FragmentCameraBinding;
import ir.webkhoon.pixart.model.Post;
import ir.webkhoon.pixart.model.User;

public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    Bitmap cameraBitmap;
    ImageView imgCamera;
    Button btnCapture;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CameraViewModel cameraViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imgCamera = binding.imgCamera;
        btnCapture = binding.btnCapture;
        btnCapture.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,
                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        });
        Button btnGray = binding.btnGray;
        Button btnBright = binding.btnBright;
        Button btnDark = binding.btnDark;
        Button btnGamma = binding.btnGamma;
        Button btnGreen = binding.btnGreen;
        Button btnBlue = binding.btnBlue;
        btnGray.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = gray(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        btnBright.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = bright(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        btnDark.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = dark(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        btnGamma.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = gama(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        btnGreen.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = green(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        btnBlue.setOnClickListener(view -> {
            if (cameraBitmap == null) {
                return;
            }
            cameraBitmap = blue(cameraBitmap);
            imgCamera.setImageBitmap(cameraBitmap);
        });
        EditText edtDescription = binding.edtDescription;
        Button btnPublish = binding.btnPublish;
        btnPublish.setOnClickListener(view -> {
            Post.posts.add(new Post(User.currentUser, cameraBitmap, edtDescription.getText().toString()));
            cameraBitmap = null;
            imgCamera.setImageBitmap(null);
            edtDescription.setText("");
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                cameraBitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
                imgCamera.setImageBitmap(cameraBitmap);

            }
        }
    }

    public Bitmap gray(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        double red = 0.33;
        double green = 0.59;
        double blue = 0.11;
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                r = (int) (red * r);
                g = (int) (green * g);
                b = (int) (blue * b);
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        return operation;
    }

    public Bitmap bright(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                r = 100 + r;
                g = 100 + g;
                b = 100 + b;
                alpha = 100 + alpha;
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        return operation;
    }

    public Bitmap dark(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                r = r - 50;
                g = g - 50;
                b = b - 50;
                alpha = alpha - 50;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        return operation;
    }

    public Bitmap gama(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                r = r + 150;
                g = 0;
                b = 0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        return operation;
    }

    public Bitmap green(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                r = 0;
                g = g + 150;
                b = 0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        return operation;
    }

    public Bitmap blue(Bitmap bmp) {
        Bitmap operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                r = 0;
                g = 0;
                b = b + 150;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        return operation;
    }
}