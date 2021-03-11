package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import trivia.assignment.appscrip.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    // on click for start button to navigate to home screen
    public void start(View view) {
        startActivity(new Intent(SplashScreen.this, HomeScreen.class));
        finish();
    }
}