package com.example.administrator.fta8;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    @RequiresApi(api = Build.VERSION_CODES.M)
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
        checkFilePermissions();

        lvJIntenOpen = findViewById(R.id.lvIntentOpen);
        lvJIntenOpen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(intentAppOpent.this, MainActivity.class);
//                intent.putExtra("Return", processDir.get(position));
                globalValue2.Process = processDir.get(position);
                startActivity(intent);
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

    // Check Permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }else{
            Log.d("Huynhbv", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }
}
