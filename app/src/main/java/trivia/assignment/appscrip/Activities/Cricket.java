package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import trivia.assignment.appscrip.Constants;
import trivia.assignment.appscrip.R;

public class Cricket extends AppCompatActivity {


    String user_name;

    // UI Fields
    TextView next;
    RadioGroup radioGroup;
    RadioButton player_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket);

        getData();
        initViews();
    }

    // method to initialize views
    private void initViews() {
        radioGroup = findViewById(R.id.radio_group);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the selected id
                int selectedId = radioGroup.getCheckedRadioButtonId();
                player_button = findViewById(selectedId);

                // if nothing selected
                if (selectedId == -1)
                {
                    Toast.makeText(Cricket.this, Constants.SELECT_ANY_ONE_OPTION, Toast.LENGTH_SHORT).show();
                }else
                {
                    // navigate to indian flag activity by passing data
                    Intent intent = new Intent(Cricket.this, IndianFlag.class);
                    intent.putExtra(Constants.USER_NAME, user_name);
                    intent.putExtra(Constants.BEST_PLAYER, player_button.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }

    // method to retrieve the data passed to this activity through the intent from previous activity
    private void getData() {
        user_name = getIntent().getStringExtra(Constants.USER_NAME);
    }
}