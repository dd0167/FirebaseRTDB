package com.example.firebasertdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebasertdb.FBref.refStudent;

public class VaccinesDataIn extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText et_place_v1;
    EditText et_date_v1;
    EditText et_place_v2;
    EditText et_date_v2;
    TextView tv_name;
    ListView students_list;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayAdapter<String> adp;
    String str1,str2,cgv;
    Vaccine v1,v2;
    int pos;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_data_in);

        et_place_v1=(EditText) findViewById(R.id.et_place_v1);
        et_date_v1=(EditText) findViewById(R.id.et_date_v1);
        et_place_v2=(EditText) findViewById(R.id.et_place_v2);
        et_date_v2=(EditText) findViewById(R.id.et_date_v2);
        tv_name=(TextView) findViewById(R.id.tv_name);
        students_list=(ListView) findViewById(R.id.students_list);

        students_list = (ListView) findViewById(R.id.students_list);
        students_list.setOnItemClickListener(this);
        students_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        text="Student's Name";
        tv_name.setText(text);

        try {
            Intent gi=getIntent();
            String sfn=gi.getStringExtra("stuFirstName");
            String stid=gi.getStringExtra("studentID");
            if (sfn!=null || stid!=null)
            {
                text="Name: "+sfn+", ID: "+stid;
                tv_name.setText(text);
            }
            String v1d,v2d,v1p,v2p;
            v1d= gi.getStringExtra("v1date");
            v2d=gi.getStringExtra("v2date");
            v1p=gi.getStringExtra("v1place");
            v2p=gi.getStringExtra("v2place");
            if (v1d.equals("Null") && v2d.equals("Null") && v1p.equals("Null") && v2p.equals("Null"))
            {
                et_date_v1.setText("");
                et_date_v2.setText("");
                et_place_v1.setText("");
                et_place_v2.setText("");
            }
            else {
                et_date_v1.setText(v1d);
                et_date_v2.setText(v2d);
                et_place_v1.setText(v1p);
                et_place_v2.setText(v2p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    cgv=stuTmp.getCanGetVaccine();
                    if (cgv.equals("true"))
                    {
                        stuValues.add(stuTmp);
                        str2 = stuTmp.getFirstName();
                        stuList.add(str1+" "+str2);
                    }
                }
                adp = new ArrayAdapter<String>(VaccinesDataIn.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                students_list.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        refStudent.addValueEventListener(stuListener);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("Main menu"))
        {
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);
        }
        if (st.equals("Student data"))
        {
            Intent in=new Intent(this,StudentDataIn.class);
            startActivity(in);
        }
        if (st.equals("Vaccines data"))
        {
            Intent in=new Intent(this,VaccinesDataIn.class);
            startActivity(in);
        }
        if (st.equals("Data display"))
        {
            Intent in=new Intent(this,DisplayData.class);
            startActivity(in);
        }
        if (st.equals("Data sorting"))
        {
            Intent in=new Intent(this,SortData.class);
            startActivity(in);
        }
        if (st.equals("Credits"))
        {
            Intent in=new Intent(this,CreditsActivity.class);
            startActivity(in);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        text="Name: "+stuValues.get(position).getFirstName()+", ID: "+stuValues.get(position).getStudentID();
        tv_name.setText(text);
        pos=position;
    }

    public void vaccines_datain(View view) {
        if (text.equals("Student's Name"))
        {
            Toast.makeText(this, "Choose student!", Toast.LENGTH_SHORT).show();
        }
        else {
            String first_name,last_name,sid,cgv;
            int igrade,iclass;
            first_name=stuValues.get(pos).getFirstName();
            last_name=stuValues.get(pos).getLastName();
            sid=stuValues.get(pos).getStudentID();
            igrade=stuValues.get(pos).getStuGrade();
            iclass=stuValues.get(pos).getStuClass();
            cgv=stuValues.get(pos).getCanGetVaccine();
            String place_v1, date_v1, place_v2, date_v2;
            place_v1 = et_place_v1.getText().toString();
            date_v1 = et_date_v1.getText().toString();
            place_v2 = et_place_v2.getText().toString();
            date_v2 = et_date_v2.getText().toString();
            v1 = new Vaccine(place_v1, date_v1);
            v2 = new Vaccine(place_v2, date_v2);
            Student student=new Student(first_name,last_name,sid,igrade,iclass,cgv,v1,v2);
            refStudent.child(sid).setValue(student);
            refStudent.child(sid).setValue(student);
            finish();
        }
    }
}