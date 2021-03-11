package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import trivia.assignment.appscrip.Constants;
import trivia.assignment.appscrip.R;

public class HomeScreen extends AppCompatActivity {

    TextInputLayout name_layout;
    TextInputEditText name;
    TextView next;
    ImageView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
    }

    // method to initialize views by their id's
    private void setUpViews() {
        name_layout = findViewById(R.id.name_edit_layout);
        name = findViewById(R.id.name_edit_text);
        addListener(name_layout, name);

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_text = name.getText().toString().trim(); // get the text

                // if not empty
                if (! name_text.isEmpty())
                {
                    // navigate to the cricket activity
                    Intent intent = new Intent(HomeScreen.this, Cricket.class);
                    intent.putExtra(Constants.USER_NAME, name_text); // pass the name to the next activity entered by user
                    startActivity(intent);

                    // if empty, set error
                }else {name_layout.setError("Required");}
            }
        });

        list = findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate to list
                startActivity(new Intent(HomeScreen.this, ListActivity.class));
            }
        });
    }


    // method to listen for changes in text in TextInput Layout and respective TextInputEditText
    private void addListener(TextInputLayout layout, TextInputEditText text)
    {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditText(s,layout);
            }
        });

        layout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEditText(((TextInputEditText) v).getText(),layout);
                }
            }
        });
    }

    // validate the text inserted and remove Error from TextInputLayout
    private void validateEditText(Editable s, TextInputLayout layout) {
        if (!TextUtils.isEmpty(s)) {
            layout.setError(null);
        }
    }

    // close the application by clearing all previous tasks when back button (hardware) clicked
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}