package edu.chapman.jennifer.classrecord;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static edu.chapman.jennifer.classrecord.NewClass.sendName;

public class listClass extends Activity {

    static String TAG = "listClass";

    ListView lv;
    Button newClass;

    public static final String sendName = "className";
    public static final String sendClassNum = "classNumber";

    ArrayList<String> classNames = new ArrayList<>();
    ArrayList<String> classNums = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listclasses);

        Log.i(TAG, "ListClass");

        lv = (ListView) findViewById(R.id.lvClassList);
        newClass = findViewById(R.id.bnAddNewClass);

        Intent intent = getIntent();
        //final ArrayList<String> names = intent.getStringArrayListExtra(sendName);
        //final ArrayList<String> num = intent.getStringArrayListExtra(sendClassNum);
        String names = intent.getStringExtra(sendName);
        String nums = intent.getStringExtra(sendClassNum);

        classNames.add(names);
        classNums.add(nums);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPostion = position;
                String itemValue = (String) lv.getItemAtPosition(position);

                Intent showClassActivity = new Intent(listClass.this, ShowClass.class);
                showClassActivity.putExtra(sendName, classNames.get(itemPostion));
                showClassActivity.putExtra(sendClassNum, classNums.get(itemPostion));

                startActivity(showClassActivity);
            }
        });

        newClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newClassActivity = new Intent(listClass.this, NewClass.class);

                startActivity(newClassActivity);
            }
        });
    }

}
