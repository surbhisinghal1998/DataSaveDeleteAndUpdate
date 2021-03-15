package com.solution.datasaveanddelete;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> data = new ArrayList<>();
    private RecyclerView recyclerView;
    EditText ed1;
    Button save, delete,update;
    TextView txtview;
    private AdapterClass adapterClass;
    private int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        // data to populate the RecyclerView with

        recyclerView = findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        save = findViewById(R.id.savebutton);
        ed1 = findViewById(R.id.edit1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString().trim();

                if (pos==-1){
                    data.add(name);
                }else {
                    Toast.makeText(MainActivity.this, ""+pos, Toast.LENGTH_SHORT).show();
                    data.set(pos,name);
                     }


                adapterClass=new AdapterClass(MainActivity.this, data, new AdapterClass.GetItem() {
                    @Override
                    public void getItemValue(int position, String name) {
                        ed1.setText(name);
                        pos=position;
                    }
                });
                recyclerView.setAdapter(adapterClass);
                adapterClass.notifyDataSetChanged();
                ed1.setText("");

            }
        });
        update=findViewById(R.id.updatabutton);

    }
}