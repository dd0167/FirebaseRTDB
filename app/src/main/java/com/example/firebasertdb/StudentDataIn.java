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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.example.firebasertdb.FBref.refStudent;

public class StudentDataIn extends AppCompatActivity {

    EditText et_first_name;
    EditText et_last_name;
    EditText et_grade;
    EditText et_class_number;
    EditText et_student_id;
    Switch switch_vaccine;
    Student student;
    String v1d,v2d,v1p,v2p;
    Vaccine v1,v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_in);

        et_first_name=(EditText) findViewById(R.id.et_first_name);
        et_last_name=(EditText) findViewById(R.id.et_last_name);
        et_grade=(EditText) findViewById(R.id.et_grade);
        et_class_number=(EditText) findViewById(R.id.et_class_number);
        et_student_id=(EditText) findViewById(R.id.et_student_id);
        switch_vaccine=(Switch) findViewById(R.id.switch_vaccine);

        try {
            Intent gi=getIntent();
            et_grade.setText(gi.getStringExtra("studentGrade"));
            et_class_number.setText(gi.getStringExtra("studentClass"));
            et_first_name.setText(gi.getStringExtra("studentFirstName"));
            et_last_name.setText(gi.getStringExtra("studentLastName"));
            et_student_id.setText(gi.getStringExtra("studID"));
            v1d=gi.getStringExtra("v1date");
            v2d=gi.getStringExtra("v2date");
            v1p=gi.getStringExtra("v1place");
            v2p=gi.getStringExtra("v2place");
            String icgv=gi.getStringExtra("switchcgv");
            if (icgv.equals("true"))
            {
                switch_vaccine.setChecked(true);
            }
            else if (icgv.equals("false"))
            {
                switch_vaccine.setChecked(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void data_in(View view) {
        String first_name, last_name, sgrade, sclass,sid,cgv;
        int igrade,iclass;
        boolean gvaccine=switch_vaccine.isChecked();
        cgv=String.valueOf(gvaccine);
        first_name = et_first_name.getText().toString();
        last_name = et_last_name.getText().toString();
        sgrade = et_grade.getText().toString();
        sclass = et_class_number.getText().toString();
        sid = et_student_id.getText().toString();
        if (first_name.equals("") || last_name.equals("") || sgrade.equals("") || sclass.equals("") || sid.equals(""))
        {
            Toast.makeText(this, "Enter all the required information!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            igrade=Integer.parseInt(sgrade);
            iclass=Integer.parseInt(sclass);
            if (v1d==null || v2d==null || v1p==null || v2p==null)
            {
                v1=new Vaccine("Null","Null");
                v2=new Vaccine("Null","Null");
            }
            else
            {
                v1=new Vaccine(v1p,v1d);
                v2=new Vaccine(v2p,v2d);
            }
            student=new Student(first_name,last_name,sid,igrade,iclass,cgv,v1,v2);
            refStudent.child(sid).setValue(student);
            finish();
        }
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
}