package sg.edu.rp.c346.id22024709.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerTasks;
    EditText etElement;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView toDoLv;
    ArrayList<String> alToDo;
    public static boolean isInteger (String index){
        if (index == null){
            return false;
        }
        try{
            int i =  Integer.parseInt(index);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerTasks = findViewById(R.id.spinnerTasks);
        etElement = findViewById(R.id.etElement);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnClear = findViewById(R.id.btnClear);
        toDoLv = findViewById(R.id.todolist);
        alToDo = new ArrayList<String>();

        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alToDo);
        toDoLv.setAdapter(aaToDo);


        spinnerTasks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        etElement.setText("");
                        etElement.setHint(getString(R.string.newTask));
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        etElement.setText("");
                        etElement.setHint(getString(R.string.indexRemove));
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etElement.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter something.", Toast.LENGTH_LONG).show();
                } else {
                    String newTask = etElement.getText().toString();
                    alToDo.add(newTask);
                    aaToDo.notifyDataSetChanged();
                    etElement.setText("");
                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (alToDo.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if ((Integer.parseInt(etElement.getText().toString())) > alToDo.size()){
                     Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                 }else {
                    alToDo.remove(Integer.parseInt(etElement.getText().toString())-1);
                    aaToDo.notifyDataSetChanged();
                    etElement.setText("");
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inflate, menu);
        return true;
    }
}