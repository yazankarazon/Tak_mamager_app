package com.example.task_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txt_Task;

    public static final String DATA = "DATA";
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;
    public static List<Task> taskList;
    private ArrayAdapter<Task> arrayAdapter;
    ListView listView_Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupSharedPrefs();
        setupAdapter();
        loadData();

        listView_Task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TaskDetails.class);
                intent.putExtra("Task_id", (int)id);
                startActivity(intent);

            }
        });
    }



    private void setupViews(){
        txt_Task = findViewById(R.id.txt_Task);
        listView_Task = findViewById(R.id.listView_Task);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    private void setupAdapter(){
        taskList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, taskList);
        listView_Task.setAdapter(arrayAdapter);
    }

    private void loadData() {
        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");

        if (!str.equals("")) {
            Task[] tasks = gson.fromJson(str, Task[].class);
            taskList.clear();

            taskList.addAll(Arrays.asList(tasks)); // Add tasks from Gson data


            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void addNewTask(View view) {
        String title = txt_Task.getText().toString();
        if (!title.equals("")) {
            taskList.add(0, new Task(title));
            arrayAdapter.notifyDataSetChanged();

            //Gson and sharedPref
            Gson gson = new Gson();
            String tasksString = gson.toJson(taskList);
            editor.putString(DATA, tasksString);
            editor.commit();

            txt_Task.setText("");
        } else {
            txt_Task.setHint("Fill it to add");
        }
    }


    public void btn_Statistics(View view) {
        Intent i = new Intent(MainActivity.this, Statistics.class);
        startActivity(i);
    }
}