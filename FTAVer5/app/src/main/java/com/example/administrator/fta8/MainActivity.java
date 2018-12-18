package com.example.administrator.fta8;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements ClickListener{

////////////////////////////////////////////////// Global value Start////////////////////////////////////////////////////////////
    // Core Value
    String xlsxFile;
    String imgResource_1;
    String imgResource_2;
    excelReader xlsxReadder;
    String [][] dataBase;
    String [][] dataHeader;

    ArrayList<outputItem> arrayListOutput = new ArrayList<>();
    ArrayList<filter6Item> arrayListFilter6 = new ArrayList<>();
    ArrayList<stackItem> stack = new ArrayList<>();

    public static final int REQUEST_CODE = 1412;

    String [][] arrLvFilter1;  // Stt
    String [][] arrLvFilter2; // Date
    String [][] arrLvFilter3; // filter1: list view "Model"
    String [][] arrLvFilter4; // filter2: list view "Lens"
    String [][] arrLvFilter5; // filter3: list view "Loi"
    String [][] arrImgFilter6; // filter4: list src image "Anh mo ta Loi"
    String [][] arrLvFilter7; // filter5: list view "Vi Tri"
    String [][] arrLvFilter8; // filter6: List view  "Bien Dang"
    String [][] arrLvFilter9; // filter7: List view "New Filter"
    String [][] arrLvCause; // Cause

    int indexFilter_1;
    int indexFilter_2;
    int indexFilter_3;
    int indexFilter_4;
    int indexFilter_5;
    int indexFilter_6;
    int indexFilter_7;
    int indexFilter_8;
    int indexFilter_9;
    int indexCause_10;
    int indexDescrip_11;
    int indexDescripImg_12;
    int indexAction_13;
    int indexDetail_14;

    // UI value
    Button btnJHome;
    Button btnJBack;
    Button btnJRegister;

    TextView tvJFilter3;
    TextView tvJFilter4;
    TextView tvJFilter5;
    TextView tvJFilter7;
    TextView tvJFilter8;
    TextView tvJFilter9;

    ListView lvJFilter3;
    ListView lvJFilter4;
    ListView lvJFilter5;
    ListView lvJFilter7;
    ListView lvJFilter8;
    ListView lvJFilter9;

    RecyclerView recyclerViewOutput;
    RecyclerView recyclerViewFilter6;

////////////////////////////////////////////////// Global value end ////////////////////////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        checkFilePermissions();
//        ((GlobalValue) this.getApplication()).setProcess("ASSY");
        open();
        initial();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            ((GlobalValue) this.getApplication()).setProcess(data.getStringExtra("Return"));
        }
    }

    ////////////////////////////////////////////////// My Function Start ////////////////////////////////////////////////////////////////

    private void open(){
        Intent intent = new Intent(this, intentAppOpent.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
    // Initial Value
    private void initial() {
        xlsxFile =  getString(R.string.RootFolder) + "/" +  ((GlobalValue) this.getApplication()).getProcess() +  "/" + getString(R.string.FileName);
        imgResource_1 = getString(R.string.RootFolder) + "/" +  ((GlobalValue) this.getApplication()).getProcess() + "/" + getString(R.string.Resource_1);
        imgResource_2 = getString(R.string.RootFolder) + "/" +  ((GlobalValue) this.getApplication()).getProcess() + "/" + getString(R.string.Resource_2);

        indexFilter_1 = 0;
        indexFilter_2 = 1;
        indexFilter_3 = 2;
        indexFilter_4 = 3;
        indexFilter_5 = 4;
        indexFilter_6 = 5;
        indexFilter_7 = 6;
        indexFilter_8 = 7;
        indexFilter_9 = 8;
        indexCause_10 = 9;
        indexDescrip_11 = 10;
        indexDescripImg_12 = 11;
        indexAction_13 = 12;
        indexDetail_14 = 13;

        xlsxReadder = new excelReader(this, xlsxFile,14);
        xlsxReadder.readExcelData();
        dataBase = xlsxReadder.getDatabaseArr();
        dataHeader = xlsxReadder.getHeaderarr();

        btnJHome = (Button) findViewById(R.id.btnHome);
        btnJHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHome();
            }
        });

        btnJBack = (Button) findViewById(R.id.btnBack);
        btnJBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stack.size() > 1) {
                    stack.remove(stack.size() - 1);
                    arrLvFilter1 = stack.get(stack.size() - 1).getArrLvFilter1();
                    arrLvFilter2 = stack.get(stack.size() - 1).getArrLvFilter2();
                    arrLvFilter3 = stack.get(stack.size() - 1).getArrLvFilter3();
                    arrLvFilter4 = stack.get(stack.size() - 1).getArrLvFilter4();
                    arrLvFilter5 = stack.get(stack.size() - 1).getArrLvFilter5();
                    arrImgFilter6 = stack.get(stack.size() - 1).getArrImgFilter6();
                    arrLvFilter7 = stack.get(stack.size() - 1).getArrLvFilter7();
                    arrLvFilter8 = stack.get(stack.size() - 1).getArrLvFilter8();
                    arrLvFilter9 = stack.get(stack.size() - 1).getArrLvFilter9();

                    coreUpdate(arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,arrImgFilter6,
                            arrLvFilter7,arrLvFilter8,arrLvFilter9,null,null,null,null,null);
                    uiUpdate();
                }
            }
        });

        //btnJRegister = (Button) findViewById(R.id.btnRegister);

        tvJFilter3 = (TextView) findViewById(R.id.tvFilter3);
        tvJFilter4 = (TextView) findViewById(R.id.tvFilter4);
        tvJFilter5 = (TextView) findViewById(R.id.tvFilter5);
        tvJFilter7 = (TextView) findViewById(R.id.tvFilter7);
        tvJFilter8 = (TextView) findViewById(R.id.tvFilter8);
        tvJFilter9 = (TextView) findViewById(R.id.tvFilter9);

        lvJFilter3 = (ListView) findViewById(R.id.lvFilter3);
        lvJFilter3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter3[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter3[position][0],indexFilter_3);
            }
        });

        lvJFilter4 = (ListView) findViewById(R.id.lvFilter4);
        lvJFilter4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter4[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter4[position][0],indexFilter_4);
            }
        });

        lvJFilter5 = (ListView) findViewById(R.id.lvFilter5);
        lvJFilter5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter5[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter5[position][0],indexFilter_5);
            }
        });

        lvJFilter7 = (ListView) findViewById(R.id.lvFilter7);
        lvJFilter7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter7[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter7[position][0],indexFilter_7);
            }
        });

        lvJFilter8 = (ListView) findViewById(R.id.lvFilter8);
        lvJFilter8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter8[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter8[position][0],indexFilter_8);
            }
        });

        lvJFilter9 = (ListView) findViewById(R.id.lvFilter9);
        lvJFilter9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrLvFilter9[position][0],Toast.LENGTH_SHORT).show();
                newFilter(arrLvFilter9[position][0],indexFilter_9);
            }
        });

        // RecycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        recyclerViewOutput = (RecyclerView) findViewById(R.id.rcvOutput);
        recyclerViewOutput.setHasFixedSize(true);
        recyclerViewOutput.setLayoutManager(linearLayoutManager);
        //recyclerViewOutput.addItemDecoration(dividerItemDecoration);

        recyclerViewFilter6 = (RecyclerView) findViewById(R.id.rcvFilter6);
        recyclerViewFilter6.setHasFixedSize(true);
        recyclerViewFilter6.setLayoutManager(linearLayoutManager1);
        //recyclerViewFilter6.addItemDecoration(dividerItemDecoration1);

        // Update
        coreUpdate(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        stack.add(new stackItem(arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,
                                arrImgFilter6,arrLvFilter7,arrLvFilter8,arrLvFilter9));
        uiUpdate();
    }

////////////////////////////////////////////////// CORE ////////////////////////////////////////////////////////////////
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

    // When Button Home Click
    private void setHome()
    {
        coreUpdate(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        stack.clear();
        stack.add(new stackItem(arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,
                arrImgFilter6,arrLvFilter7,arrLvFilter8,arrLvFilter9));
        uiUpdate();
    }

    // get image description
    private String getImgeDescription(String Causefilter, String DesFilter){
        String rtn = "";
        String [][] arrCausefilter = new String[1][2];
        arrCausefilter[0][0] = Causefilter;
        String [][] arrDesfilter = new String[1][2];
        arrDesfilter[0][0] = DesFilter;

        String [][] arr = dataBase;
        arr = filterFuns(arr, arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,arrImgFilter6,
                arrLvFilter7,arrLvFilter8,arrLvFilter9,arrCausefilter,arrDesfilter,null,null,null);

        rtn = uniqueSorting(arr,indexDescripImg_12)[0][0];

        return rtn;
    }
    // When ListView Selected
    private void newFilter(String filterValue, int index)
    {
        String [][] filter = new String[1][2];
        filter[0][0] = filterValue;

        switch (index){
            case 0:
                arrLvFilter1 = filter;
                break;
            case 1:
                arrLvFilter2 = filter;
                break;
            case 2:
                arrLvFilter3 = filter;
                break;
            case 3:
                arrLvFilter4 = filter;
                break;
            case 4:
                arrLvFilter5 = filter;
                break;
            case 5:
                arrImgFilter6 = filter;
                break;
            case 6:
                arrLvFilter7 = filter;
                break;
            case 7:
                arrLvFilter8 = filter;
                break;
            case 8:
                arrLvFilter9 = filter;
                break;
        }

        coreUpdate(arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,arrImgFilter6,
                arrLvFilter7,arrLvFilter8,arrLvFilter9,null,null,null,null,null);

        stack.add(new stackItem(arrLvFilter1,arrLvFilter2,arrLvFilter3,arrLvFilter4,arrLvFilter5,
                arrImgFilter6,arrLvFilter7,arrLvFilter8,arrLvFilter9));

        uiUpdate();

    }

    // Update list data
    private void coreUpdate(String[][] filterArr1, String[][] filterArr2,String[][] filterArr3,String[][] filterArr4,
                            String[][] filterArr5, String[][] filterArr6,String[][] filterArr7,String[][] filterArr8,String[][] filterArr9,
                            String[][] filterArr10, String[][] filterArr11,String[][] filterArr12,String[][] filterArr13,String[][] filterArr14){
        String [][] arr = dataBase;
        arr = filterFuns(arr, filterArr1, filterArr2,filterArr3,filterArr4,filterArr5,filterArr6,filterArr7,
                        filterArr8,filterArr9,filterArr10,filterArr11,filterArr12,filterArr13,filterArr14);

        arrLvFilter1 = uniqueSorting(arr,indexFilter_1);
        arrLvFilter2 = uniqueSorting(arr,indexFilter_2);
        arrLvFilter3 = uniqueSorting(arr,indexFilter_3);
        arrLvFilter4 = uniqueSorting(arr,indexFilter_4);
        arrLvFilter5 = uniqueSorting(arr,indexFilter_5);
        arrImgFilter6 = uniqueSorting(arr,indexFilter_6);
        arrLvFilter7 = uniqueSorting(arr,indexFilter_7);
        arrLvFilter8 = uniqueSorting(arr,indexFilter_8);
        arrLvFilter9 = uniqueSorting(arr,indexFilter_9);
        arrLvCause = uniqueSorting(arr,indexCause_10);

        arrayListFilter6.clear();
        for (int i =0; i < arrImgFilter6.length; i++){
            filter6Item filter6 = new filter6Item(arrImgFilter6[i][0]
                    ,Environment.getExternalStorageDirectory().getPath() +"/" + imgResource_1);
            arrayListFilter6.add(filter6);
        }

        String [][] temp = new String [1][2];
        arrayListOutput.clear();
        for (int i = 0; i< arrLvCause.length; i++){

            temp[0][0] = arrLvCause[i][0];
            temp[0][1] = arrLvCause[i][1];
            String[][] arrLvDescrip  = uniqueSorting(filterFuns(arr,null,null,null,null,null,null,null,null,null,temp,null,null,null,null),indexDescrip_11);
            String[][] arrImgDescrip = uniqueSorting(filterFuns(arr,null,null,null,null,null,null,null,null,null,temp,null,null,null,null),indexDescripImg_12);
            String[][] arrLvAction = uniqueSorting(filterFuns(arr,null,null,null,null,null,null,null,null,null,temp,null,null,null,null),indexAction_13);
            String[][] arrLvDetail = uniqueSorting(filterFuns(arr,null,null,null,null,null,null,null,null,null,temp,null,null,null,null),indexDetail_14);

            outputItem output = new outputItem( "[" + temp[0][1] + "] " + temp[0][0],mergeImg2dto1d(arrImgDescrip),
                    Environment.getExternalStorageDirectory().getPath() +"/" +  imgResource_2,
                    mergeLv2dto1d(arrLvDescrip),mergeLv2dto1d(arrLvAction),mergeLv2dto1d(arrLvDetail));

            arrayListOutput.add(output);
        }

    }

    // Filters Multi condition return arr database
    private String [][] filterFuns (String[][] database, String[][] filterArr1, String[][] filterArr2,String[][] filterArr3,String[][] filterArr4,
                             String[][] filterArr5, String[][] filterArr6,String[][] filterArr7,String[][] filterArr8,String[][] filterArr9,
                             String[][] filterArr10, String[][] filterArr11,String[][] filterArr12,String[][] filterArr13,String[][] filterArr14){
        String [][] rtn;
        rtn = database;
        rtn = filterFun(rtn, indexFilter_1, filterArr1);
        rtn = filterFun(rtn, indexFilter_2, filterArr2);
        rtn = filterFun(rtn, indexFilter_3, filterArr3);
        rtn = filterFun(rtn, indexFilter_4, filterArr4);
        rtn = filterFun(rtn, indexFilter_5, filterArr5);
        rtn = filterFun(rtn, indexFilter_6, filterArr6);
        rtn = filterFun(rtn, indexFilter_7, filterArr7);
        rtn = filterFun(rtn, indexFilter_8, filterArr8);
        rtn = filterFun(rtn, indexFilter_9, filterArr9);
        rtn = filterFun(rtn, indexCause_10, filterArr10);
        rtn = filterFun(rtn, indexDescrip_11, filterArr11);
        rtn = filterFun(rtn, indexDescripImg_12, filterArr12);
        rtn = filterFun(rtn, indexAction_13, filterArr13);
        rtn = filterFun(rtn, indexDetail_14, filterArr14);

        return rtn;
    }

    // Filter single Criteria
    private String [][] filterFun(String [][] arrDatabase, int index, String [][] arrCriteria){

        String [][] rtn = new String[1][14];
        int rtnLengh;

        if(arrDatabase == null ||  index < 0 || index > arrDatabase[0].length) {return null;}

        for(int i =0; i < arrDatabase.length; i++){
            if(checkExist(arrCriteria, arrDatabase[i][index]) == true){
                rtn = addData(rtn, arrDatabase, i);
            }
        }
        return rtn;
    }

    // Check exist
    private boolean checkExist(String [][] arrCriteria, String value){

        if (arrCriteria == null) {return true;}

        for(int i = 0; i < arrCriteria.length; i++){
            if(arrCriteria[i][0].equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Add new Item
    private String[][] addData(String [][] arrSource, String [][] arrCopy, int row){

        String [][] rtn;

        if (arrSource[0][0] == null){
            rtn = new String[1][arrCopy[0].length];
        }else {
            rtn = new String[arrSource.length + 1][arrCopy[0].length];
        }

        for (int r = 0; r < arrSource.length; r++){
            for(int c = 0; c < arrSource[0].length; c++){
                rtn[r][c] = arrSource[r][c];
            }
        }

        for(int j = 0; j< arrCopy[0].length; j++){
            rtn[rtn.length - 1][j] = arrCopy[row][j];
        }
        return rtn;
    }

    // Filter Unique value and Sorting Highest to Small of single Column
    private String[][] uniqueSorting( String [][] arrSource, int column){
        String [][] rtn;
        rtn = new String[1][2];

        if (arrSource == null) {return null;}

        rtn = getUnigue(arrSource, column);
        rtn = sorting(arrSource,column, rtn);

        return rtn;
    }

    // Get unique value
    private String[][] getUnigue(String [][] arrSource, int column){
        String [][] rtn;
        String [][] tmp = new String[1][2];
        rtn = new String[1][2];
        rtn[0][0] = arrSource[0][column];

        for(int i = 0; i < arrSource.length; i++){
            if (checkExist(rtn,arrSource[i][column]) == false){
                tmp[0][0] = arrSource[i][column];
                rtn = addData(rtn,tmp,0);
            }
        }
        return rtn;
    }

    // Sorting Arr 2d
    private String[][] sorting(String [][] arrSource, int column, String [][] arr){
        String [][] rtn;
        int count;
        rtn = arr;
        count = 0;

        for(int i = 0; i < rtn.length; i++){
            for(int j = 0; j < arrSource.length; j++){
                if ( arrSource[j][column].equals(rtn[i][0])){
                    count +=1;
                }
            }
            rtn[i][1] = Integer.toString(count);
            count = 0;
        }

        for (int i = 0; i < rtn.length; i++){
            for (int j = i +1; j < rtn.length; j++){
                if ( Integer.parseInt(rtn[j][1]) > Integer.parseInt(rtn[i][1])){
                    String c1 = rtn[j][0];
                    String c2 = rtn[j][1];

                    rtn[j][0] = rtn[i][0];
                    rtn[j][1] = rtn[i][1];

                    rtn[i][0] = c1;
                    rtn[i][1] = c2;
                }
            }
        }

        return rtn;
    }

    private String[] mergeLv2dto1d(String[][] arr) {
        String [] rtn = new String[arr.length];

        for (int i = 0; i < arr.length; i++){
            rtn[i] = "[" + arr[i][1] + "] " + arr[i][0];
        }
        return rtn;
    }

    private String[] mergeImg2dto1d(String[][] arr) {
        String [] rtn = new String[arr.length];

        for (int i = 0; i < arr.length; i++){
            rtn[i] = arr[i][0];
        }
        return rtn;
    }
////////////////////////////////////////////////// CORE ////////////////////////////////////////////////////////////////

////////////////////////////////////////////////// UI Star ////////////////////////////////////////////////////////////////
    private void uiUpdate(){

        tvJFilter3.setText(dataHeader[0][indexFilter_3]);
        tvJFilter4.setText(dataHeader[0][indexFilter_4]);
        tvJFilter5.setText(dataHeader[0][indexFilter_5]);
        tvJFilter7.setText(dataHeader[0][indexFilter_7]);
        tvJFilter8.setText(dataHeader[0][indexFilter_8]);
        tvJFilter9.setText(dataHeader[0][indexFilter_9]);

        ArrayAdapter filter3Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter3));
        ArrayAdapter filter4Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter4));
        ArrayAdapter filter5Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter5));
        ArrayAdapter filter7Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter7));
        ArrayAdapter filter8Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter8));
        ArrayAdapter filter9Adapter = new ArrayAdapter(MainActivity.this, R.layout.mytextview, mergeLv2dto1d(arrLvFilter9));

        outputAdapter outputAdt = new outputAdapter(arrayListOutput, MainActivity.this, this);
        filter6Adapter filter6adt = new filter6Adapter(arrayListFilter6, MainActivity.this, this);

        lvJFilter3.setAdapter(filter3Adapter);
        lvJFilter4.setAdapter(filter4Adapter);
        lvJFilter5.setAdapter(filter5Adapter);
        lvJFilter7.setAdapter(filter7Adapter);
        lvJFilter8.setAdapter(filter8Adapter);
        lvJFilter9.setAdapter(filter9Adapter);
        recyclerViewOutput.setAdapter(outputAdt);
        recyclerViewFilter6.setAdapter(filter6adt);
    }

    private void intentUpdate(String text, String img, int op){
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("Text", text);
        intent.putExtra("image", img);
        intent.putExtra("op",op);
        this.startActivity(intent);
    }

    @Override
    public void onFilter6LongClick(int itemFilter6SelectedPosition) {
//        Toast.makeText(MainActivity.this,"Long" + arrayListFilter6.get(itemFilter6SelectedPosition).getImgFilter6Dir(),Toast.LENGTH_SHORT).show();
        intentUpdate("", arrayListFilter6.get(itemFilter6SelectedPosition).getImgFilter6Dir(),1);
    }

    @Override
    public void onFilter6Click(int itemFilter6SelectedPosition) {
//        Toast.makeText(MainActivity.this,"Click" + arrayListFilter6.get(itemFilter6SelectedPosition).getImgFilter6Dir(),Toast.LENGTH_SHORT).show();
        newFilter(arrImgFilter6[itemFilter6SelectedPosition][0],indexFilter_6);
    }

    @Override
    public void onOutputImgLongClicked(int itemOutputPosition) {
//        Toast.makeText(MainActivity.this,arrayListOutput.get(itemOutputPosition).getDescripPic()[0],Toast.LENGTH_SHORT).show();
        intentUpdate("", arrayListOutput.get(itemOutputPosition).getDescripPic()[0],2);
    }

    @Override
    public void onOutputDescripSelected(int itemOutputPosition, int itemDescriptionSelect) {
//        Toast.makeText(MainActivity.this,arrayListOutput.get(itemOutputPosition).getDescrip()[itemDescriptionSelect],Toast.LENGTH_SHORT).show();
        String img = getImgeDescription(arrayListOutput.get(itemOutputPosition).getCause().split("] ",2)[1],arrayListOutput.get(itemOutputPosition).getDescrip()[itemDescriptionSelect].split("] ",2)[1]);
        intentUpdate(arrayListOutput.get(itemOutputPosition).getDescrip()[itemDescriptionSelect].split("] ",2)[1],img,2);
    }

    @Override
    public void onOutputActionSelected(int itemOutputPosition, int itemActionSelect) {
//        Toast.makeText(MainActivity.this,arrayListOutput.get(itemOutputPosition).getAction()[itemActionSelect],Toast.LENGTH_SHORT).show();
        intentUpdate(arrayListOutput.get(itemOutputPosition).getAction()[itemActionSelect].split("] ",2)[1],"",0);
    }

    @Override
    public void onOutputDetailSelected(int itemOutputPosition, int itemDetailSelect) {
//        Toast.makeText(MainActivity.this,arrayListOutput.get(itemOutputPosition).getDetail()[itemDetailSelect],Toast.LENGTH_SHORT).show();
        intentUpdate(arrayListOutput.get(itemOutputPosition).getDetail()[itemDetailSelect].split("] ",2)[1],"",0);
    }

////////////////////////////////////////////////// UI End ////////////////////////////////////////////////////////////////

////////////////////////////////////////////////// My Function end ////////////////////////////////////////////////////////////////
}
