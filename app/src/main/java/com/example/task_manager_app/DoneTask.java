package com.example.task_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DoneTask extends AppCompatActivity {

    private ListView list_Done;
    private List<Task> doneTasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_task);


        list_Done = findViewById(R.id.list_Done);
        doneTasksList = getDoneTasksList();




        ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, doneTasksList);
        list_Done.setAdapter(arrayAdapter);
    }


    private List<Task> getDoneTasksList() {
        List<Task> doneTasks = new ArrayList<>();

        Gson gson = new Gson();
        String str = MainActivity.prefs.getString(MainActivity.DATA, "");

        if (!str.equals("")) {
            Task[] tasks = gson.fromJson(str, Task[].class);

            for (Task task : tasks) {
                if ("done".equals(task.getStatus())) {
                    doneTasks.add(task);
                }
            }
        }

        return doneTasks;
    }


    public void btn_HomePageDone(View view) {
        Intent i = new Intent(DoneTask.this, Statistics.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}