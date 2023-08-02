package red.lisgar.proyecto.constants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import red.lisgar.proyecto.R;
import red.lisgar.proyecto.login.MainActivity;

public class SplashScreamActivity extends AppCompatActivity {

    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scream);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreamActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}