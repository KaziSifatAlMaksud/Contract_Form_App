package edu.ewubd.a2019_3_60_050_assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContreactList extends AppCompatActivity {

    private Button btnExit, btnAddNew, btnDeleteAll;
    private ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contreact_list);

        eventList = findViewById(R.id.eventList);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnExit = findViewById(R.id.btnExit);

        List<String> list = new ArrayList<>();

        KeyValueDB db = new KeyValueDB(ContreactList.this);
        Cursor keys = db.getAllKeyValues();
        while(keys.moveToNext()) {
            int index;
            index = keys.getColumnIndexOrThrow("keyname");
            list.add(keys.getString(index));
            System.out.println(keys.getString(index));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        eventList.setAdapter(arrayAdapter);

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String key = (String) eventList.getItemAtPosition(pos);
                System.out.println(key);
                Intent i = new Intent(ContreactList.this, MainActivity.class);
                i.putExtra("ADDRESS_KEY", key);
                startActivity(i);
               // finish();
            }
        });



        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContreactList.this, MainActivity.class);
                startActivity(i);
               // finish();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }
}