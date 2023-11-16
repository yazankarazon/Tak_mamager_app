package com.example.task_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DueTask extends AppCompatActivity {

    private ListView list_Due;
    private List<Task> dueTasksList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_task);





        list_Due = findViewById(R.id.list_Due);
        dueTasksList = getDueTasksList();




        ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dueTasksList);
        list_Due.setAdapter(arrayAdapter);
    }


    private List<Task> getDueTasksList() {
        List<Task> dueTasks = new ArrayList<>();

        for (Task task : MainActivity.taskList) {
            if ("due".equals(task.getStatus())) {
                dueTasks.add(task);
            }
        }

        return dueTasks;
    }

    public void btn_HomePageDone(View view) {
        Intent i = new Intent(DueTask.this, Statistics.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}