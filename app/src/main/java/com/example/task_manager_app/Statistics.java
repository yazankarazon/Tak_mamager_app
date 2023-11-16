package com.example.task_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {

    private TextView txt_NumOfAllTasks, txt_NumOfDoneTasks, txt_NumOfDueTasks;

    private List<Task> doneTasksList;
    private List<Task> dueTasksList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);



        setupViews();


        doneTasksList = getDoneTasksList();
        dueTasksList = getDueTasksList();


        txt_NumOfAllTasks.setText("Number of All Task(s) : " + MainActivity.taskList.size());
        txt_NumOfDoneTasks.setText("Number of Done Task(s) : " + doneTasksList.size());
        txt_NumOfDueTasks.setText("Number Of Due Task(s) : " + dueTasksList.size());





    }

    private  void setupViews(){
        txt_NumOfAllTasks = findViewById(R.id.txt_NumOfAllTasks);
        txt_NumOfDoneTasks = findViewById(R.id.txt_NumOfDoneTasks);
        txt_NumOfDueTasks = findViewById(R.id.txt_NumOfDueTasks);
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


    private List<Task> getDueTasksList() {
        List<Task> dueTasks = new ArrayList<>();

        for (Task task : MainActivity.taskList) {
            if ("due".equals(task.getStatus())) {
                dueTasks.add(task);
            }
        }

        return dueTasks;
    }



    public void AddAndShow(View view) {
        Intent intent = new Intent(Statistics.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void btnShowDone(View view) {
        Intent intent = new Intent(Statistics.this, DoneTask.class);
        startActivity(intent);
    }

    public void btnShowDue(View view) {
        Intent intent = new Intent(Statistics.this, DueTask.class);
        startActivity(intent);
    }



}
