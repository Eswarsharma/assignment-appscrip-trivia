package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import trivia.assignment.appscrip.Constants;
import trivia.assignment.appscrip.R;

public class IndianFlag extends AppCompatActivity {

    String user_name, best_player;
    int answers_count = 0;

    // UI Labels
    CheckBox white, yellow, orange, green;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_flag);

        getData();
        initViews();
    }

    // method to initialize views by their id's
    private void initViews() {
        white = findViewById(R.id.chk_white);
        yellow = findViewById(R.id.chk_yellow);
        orange = findViewById(R.id.chk_orange);
        green = findViewById(R.id.chk_green);

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // no options selected
                if (answers_count == 0)
                {
                    Toast.makeText(IndianFlag.this, "Select options", Toast.LENGTH_SHORT).show();
                }else
                {
                    // options selceted
                    ArrayList<String> list = new ArrayList<>();

                    if (white.isChecked())
                    {
                        list.add(white.getText().toString().trim());
                    }

                    if (yellow.isChecked())
                    {
                        list.add(yellow.getText().toString().trim());
                    }

                    if (orange.isChecked())
                    {
                        list.add(orange.getText().toString().trim());
                    }

                    if (green.isChecked())
                    {
                        list.add(green.getText().toString().trim());
                    }

                    // selected more than one option
                    if (list.size() > 1)
                    {
                        // navigate to summary
                        Intent intent = new Intent(IndianFlag.this, Summary.class);
                        intent.putExtra(Constants.USER_NAME, user_name);
                        intent.putExtra(Constants.BEST_PLAYER, best_player);
                        intent.putStringArrayListExtra(Constants.FLAG_LIST, list);
                        startActivity(intent);

                    }else
                    {
                        // only one option selected
                        Toast.makeText(IndianFlag.this, Constants.SELECT_MORE, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    // method to retrieve the data passed to this activity through the intent from previous activity
    private void getData() {
        user_name = getIntent().getStringExtra(Constants.USER_NAME);
        best_player = getIntent().getStringExtra(Constants.BEST_PLAYER);
    }

    // method to check whether any of given check boxes clicked or not
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        answers_count = checked? 1 : 0;
    }
}