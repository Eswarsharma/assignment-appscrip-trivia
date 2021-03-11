package trivia.assignment.appscrip.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import trivia.assignment.appscrip.Adapter.ListAdapter;
import trivia.assignment.appscrip.Database.DBHelper;
import trivia.assignment.appscrip.R;

public class ListActivity extends AppCompatActivity {

    // UI Labels
    RecyclerView recyclerView;
    TextView no_data_to_display;

    LinearLayoutManager linearLayoutManager;
    ArrayList list = new ArrayList();;
    ListAdapter adapter;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        no_data_to_display = findViewById(R.id.no_data_to_display);

        setUpRecycler();
        loadData();
    }

    // method to set up RecyclerView
    private void setUpRecycler() {
        recyclerView = findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ListAdapter(ListActivity.this, list);
        recyclerView.setAdapter(adapter);
    }

    // load the data from database
    private void loadData() {
        myDB = new DBHelper(ListActivity.this);
        list = myDB.getAllData();

        Log.d("list size","list size" + list);

        if (list.size() == 0)
        {
            no_data_to_display.setVisibility(View.VISIBLE);
        }else
        {
            no_data_to_display.setVisibility(View.GONE);
        }

        adapter.clear();
        adapter.addItems(list);
    }
}