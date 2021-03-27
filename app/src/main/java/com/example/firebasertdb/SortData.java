package com.example.firebasertdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebasertdb.FBref.refStudent;

public class SortData extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView studentlist;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayAdapter<String> adp;
    String str1,str2,str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_data);

        studentlist=(ListView) findViewById(R.id.students_list);

        studentlist = (ListView) findViewById(R.id.studentlist);
        studentlist.setOnItemClickListener(this);
        studentlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    stuValues.add(stuTmp);
                    str2 = stuTmp.getFirstName();
                    str3= stuTmp.getLastName();
                    stuList.add(str1+" "+str2+" "+str3);
                }
                adp = new ArrayAdapter<String>(SortData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                studentlist.setAdapter(adp);
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

    }

    public void sort_by_class(View view) {
        Query query=refStudent.orderByChild("stuClass");
        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    String cgv = stuTmp.getCanGetVaccine();
                    if (cgv.equals("true"))
                    {
                        stuValues.add(stuTmp);
                        str2 = stuTmp.getFirstName();
                        str3= stuTmp.getLastName();
                        stuList.add(str1+" "+str2+" "+str3);
                    }
                }
                adp = new ArrayAdapter<String>(SortData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                studentlist.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addValueEventListener(stuListener);
    }

    public void sort_by_grade(View view) {
        Query query=refStudent.orderByChild("stuGrade");
        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    String cgv = stuTmp.getCanGetVaccine();
                    if (cgv.equals("true"))
                    {
                        stuValues.add(stuTmp);
                        str2 = stuTmp.getFirstName();
                        str3= stuTmp.getLastName();
                        stuList.add(str1+" "+str2+" "+str3);
                    }
                }
                adp = new ArrayAdapter<String>(SortData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                studentlist.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addValueEventListener(stuListener);
    }

    public void sort_by_cantgetvaccine(View view) {
        Query query=refStudent.orderByChild("studentID");
        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    String cgv = stuTmp.getCanGetVaccine();
                    if (cgv.equals("false"))
                    {
                        stuValues.add(stuTmp);
                        str2 = stuTmp.getFirstName();
                        str3= stuTmp.getLastName();
                        stuList.add(str1+" "+str2+" "+str3);
                    }
                }
                adp = new ArrayAdapter<String>(SortData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                studentlist.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addValueEventListener(stuListener);
    }

    public void sort_by_cangetvaccine(View view) {
        Query query=refStudent.orderByChild("studentID");
        ValueEventListener stuListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    String cgv = stuTmp.getCanGetVaccine();
                    if (cgv.equals("true"))
                    {
                        stuValues.add(stuTmp);
                        str2 = stuTmp.getFirstName();
                        str3= stuTmp.getLastName();
                        stuList.add(str1+" "+str2+" "+str3);
                    }
                }
                adp = new ArrayAdapter<String>(SortData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                studentlist.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addValueEventListener(stuListener);
    }
}