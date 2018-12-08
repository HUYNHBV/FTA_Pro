package com.example.administrator.fta8;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PopupActivity extends AppCompatActivity {

    TextView tvJPopup;
    PhotoView imageJPopup;

    private String tvPopupValue;
    private String ImgPopupValue;
    private int op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_popup);

        tvJPopup = findViewById(R.id.tvPopup);
        imageJPopup = findViewById(R.id.imgPopup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*0.8), (int) (height*0.8));

        Intent intent = this.getIntent();
        tvPopupValue = intent.getStringExtra("Text");
        ImgPopupValue = intent.getStringExtra("image");
        op = intent.getIntExtra("op",0);

        tvJPopup.setText(tvPopupValue);

        String pathfile = "";
        switch (op){
            case 1:
                pathfile = getString(R.string.Resource_1);
                break;
            case 2:
                pathfile = getString(R.string.Resource_2);
                break;
        }

        Picasso.get().load(new File(Environment.getExternalStorageDirectory().getPath() +"/" +
                pathfile + "/" + ImgPopupValue + ".jpg")).into(this.imageJPopup);
    }
}
