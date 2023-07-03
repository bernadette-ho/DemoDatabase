package sg.edu.rp.c346.id22023330.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ET1, ET2;
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al = new ArrayList();
    ArrayAdapter<Task> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET1 = findViewById(R.id.ET1);
        ET2 = findViewById(R.id.ET2);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                //insert a task
                //db.insertTask("Submit RJ", "25 Apr 2021");
                db.insertTask(ET1.getText().toString(), ET2.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            //variable to check order
            private boolean ascOrder = true;

            @Override
            public void onClick(View v) {
                //create the DBHelper object, passing in the activity's context
                DBHelper db = new DBHelper(MainActivity.this);

                //insert a task
                ArrayList<Task> data = db.getTasksContent();
                ArrayList<Task> data1 = db.getTasks();

                if (ascOrder) {
                    data = db.getTasksAsc();
                } else {
                    data = db.getTasksDesc();
                }

                //ArrayList<Task> data1 = db.getTasks();
                db.close();

                al.clear();
                al.addAll(data1);
                adapter.notifyDataSetChanged();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += data.get(i) + "\n";}

                tvResults.setText(txt);

                //toggle order for next button
                ascOrder = !ascOrder;

            }

        });


    }
}