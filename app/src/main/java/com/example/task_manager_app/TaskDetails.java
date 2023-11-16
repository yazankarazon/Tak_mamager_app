package com.example.task_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

public class TaskDetails extends AppCompatActivity {

    private EditText txt_Title;
    private Spinner sp_Status;
    private TextView lbl_Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        setupViews();

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("Task_id");

        Task task = MainActivity.taskList.get(id);

        txt_Title.setText(task.getTitle());


        if ("done".equals(task.getStatus())){
            sp_Status.setSelection(1);
            lbl_Status.setTextColor(Color.GREEN);

        }else{
            sp_Status.setSelection(0);
            lbl_Status.setTextColor(Color.RED);

        }
    }


    private void setupViews(){
        txt_Title = findViewById(R.id.txt_Title);
        sp_Status = findViewById(R.id.sp_Status);
        lbl_Status = findViewById(R.id.lbl_Status);
    }

    public void btnSave(View view) {
        String updatedTitle = txt_Title.getText().toString();
        String updatedStatus = sp_Status.getSelectedItem().toString();

        // Update the task in the taskList
        Intent intent = getIntent();
        int id = (int) intent.getExtras().get("Task_id");

        Task task = MainActivity.taskList.get(id);
        task.setTitle(updatedTitle);
        task.setStatus(updatedStatus);


        //for UI change color of the lbl_Status
        if ("done".equals(task.getStatus())){
            lbl_Status.setTextColor(Color.GREEN);

        }else{
            lbl_Status.setTextColor(Color.RED);
        }

        // Save the updated task list to SharedPreferences
        Gson gson = new Gson();
        String tasksString = gson.toJson(MainActivity.taskList);
        MainActivity.editor.putString(MainActivity.DATA, tasksString);
        MainActivity.editor.commit();

    }

    public void btnBack(View view) {
        Intent i = new Intent(TaskDetails.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}