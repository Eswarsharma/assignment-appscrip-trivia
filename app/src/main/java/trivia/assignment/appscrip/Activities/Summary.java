package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import trivia.assignment.appscrip.Constants;
import trivia.assignment.appscrip.Database.DBHelper;
import trivia.assignment.appscrip.R;

public class Summary extends AppCompatActivity {

    // UI Label
    TextView username, answer_for_cricket, answer_for_flag, finish;

    String user_name, best_cricketer, colors;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initViews();
    }

    // method to initialize views
    @SuppressLint("SetTextI18n")
    private void initViews() {
        username = findViewById(R.id.greeting_user);
        answer_for_cricket = findViewById(R.id.answer_for_cricket);
        answer_for_flag = findViewById(R.id.answer_for_flag);
        finish = findViewById(R.id.finish);


        user_name = getIntent().getStringExtra(Constants.USER_NAME);
        best_cricketer = getIntent().getStringExtra(Constants.BEST_PLAYER);


        username.setText(getResources().getString(R.string.hello) +" "+ user_name + ",");
        answer_for_cricket.setText(getResources().getString(R.string.Answer) + " " + best_cricketer);

        ArrayList<String> answers = getIntent().getStringArrayListExtra(Constants.FLAG_LIST);
        StringBuffer sb = new StringBuffer();

        // looping through array list
        for (int i = 0; i < answers.size(); i ++)
        {
            sb.append(answers.get(i));
            if (i != answers.size() - 1)
            {
                sb.append(", ");
            }else
            {
                sb.append(".");
            }
        }

        colors = sb.toString(); // convert string builder to string

        answer_for_flag.setText(getResources().getString(R.string.answers) + " " + colors);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog dialog = new ProgressDialog(Summary.this);
                dialog.setMessage("Please wait");
                dialog.setCancelable(false);
                dialog.show();

                dbHelper = new DBHelper(Summary.this);
                boolean status = dbHelper.insert_Quiz(user_name, best_cricketer, colors, getDate(), getTime());

                // data inserted to database
                if (status)
                {
                    dialog.dismiss();
                    startActivity(new Intent(Summary.this, HomeScreen.class));
                }

            }
        });

    }

    // method to return current date in string
    private String getDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

    // method to return current time in string
    private String getTime() {
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        return String.valueOf(hour) + ":" + String.valueOf(minute);
    }
}