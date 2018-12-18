package com.example.administrator.fta8;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class intentAppOpent extends AppCompatActivity {

    ListView lvJIntenOpen;
    ArrayList<String> processDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_app_opent);
        getSupportActionBar().hide();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*0.5), (int) (height*0.5));

        lvJIntenOpen = findViewById(R.id.lvIntentOpen);
        lvJIntenOpen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("Return", processDir.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        getProcessDir();

    }

    private void getProcessDir(){
        File [] files = new  File(Environment.getExternalStorageDirectory().getPath() + "/" + getString(R.string.RootFolder)).listFiles();
        processDir = new ArrayList<>();
        for (File infile:files) {
            if (infile.isDirectory()){
                processDir.add(infile.getName());
            }
        }

        if (processDir.size() > 0){
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, processDir);
            lvJIntenOpen.setAdapter(adapter);
        }
    }

}
