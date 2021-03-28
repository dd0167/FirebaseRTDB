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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebasertdb.FBref.refStudent;

/**
 * The type Display data.
 */
public class DisplayData extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView list_students;
    TextView tvname;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayAdapter<String> adp;
    String str1,str2,str3,text;
    int p;
    TextView tv_fv,tv_sv,tvplace1,tvdate1,tvplace2,tvdate2,tvgrade,tvclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        list_students=(ListView) findViewById(R.id.list_students);
        tvname=(TextView) findViewById(R.id.tvname);
        tvdate1=(TextView) findViewById(R.id.tvdate1);
        tvplace1=(TextView) findViewById(R.id.tvplace1);
        tvdate2=(TextView) findViewById(R.id.tvdate2);
        tvplace2=(TextView) findViewById(R.id.tvplace2);
        tv_fv=(TextView) findViewById(R.id.tv_fv);
        tv_sv=(TextView) findViewById(R.id.tv_sv);
        tvgrade=(TextView) findViewById(R.id.tvgrade);
        tvclass=(TextView) findViewById(R.id.tvclass);

        text="Student's Name";
        tvname.setText(text);
        tvgrade.setText("Grade");
        tvclass.setText("Class");
        tv_fv.setText("First Vaccine");
        tv_sv.setText("Second Vaccine");
        tvdate1.setText("Date");
        tvdate2.setText("Date");
        tvplace1.setText("Place");
        tvplace2.setText("Place");

        list_students = (ListView) findViewById(R.id.list_students);
        list_students.setOnItemClickListener(this);
        list_students.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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
                adp = new ArrayAdapter<String>(DisplayData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                list_students.setAdapter(adp);
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
        text="Name: "+stuValues.get(position).getFirstName()+"  "+stuValues.get(position).getLastName()+", ID: "+stuValues.get(position).getStudentID();
        tvname.setText(text);
        p=position;

        tvclass.setText(String.valueOf(stuValues.get(position).getStuClass()));
        tvgrade.setText(String.valueOf(stuValues.get(position).getStuGrade()));

        String tof=stuValues.get(position).getCanGetVaccine();
        if (tof.equals("false"))
        {
            tv_fv.setText("Can't");
            tv_sv.setText("Can't");
            tvdate1.setText("Vaccine");
            tvdate2.setText("Vaccine");
            tvplace1.setText("Get");
            tvplace2.setText("Get");
        }
        else if (tof.equals("true"))
        {
            tv_fv.setText("First Vaccine");
            tv_sv.setText("Second Vaccine");
            tvdate1.setText(stuValues.get(position).getFirstVaccine().getDate());
            tvdate2.setText(stuValues.get(position).getSecondVaccine().getDate());
            tvplace1.setText(stuValues.get(position).getFirstVaccine().getPlace());
            tvplace2.setText(stuValues.get(position).getSecondVaccine().getPlace());
        }
    }

    /**
     * Update student data.
     *
     * @param view the view
     */
    public void update_student_data(View view) {
        if (text.equals("Student's Name"))
        {
            Toast.makeText(this, "Choose student!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent t= new Intent(DisplayData.this,StudentDataIn.class);
            t.putExtra("studentFirstName",stuValues.get(p).getFirstName());
            t.putExtra("studentLastName",stuValues.get(p).getLastName());
            t.putExtra("studID",stuValues.get(p).getStudentID());
            t.putExtra("studentGrade",String.valueOf(stuValues.get(p).getStuGrade()));
            t.putExtra("studentClass",String.valueOf(stuValues.get(p).getStuClass()));
            t.putExtra("switchcgv",String.valueOf(stuValues.get(p).getCanGetVaccine()));
            t.putExtra("v1date",stuValues.get(p).getFirstVaccine().getDate());
            t.putExtra("v2date",stuValues.get(p).getSecondVaccine().getDate());
            t.putExtra("v1place",stuValues.get(p).getFirstVaccine().getPlace());
            t.putExtra("v2place",stuValues.get(p).getSecondVaccine().getPlace());
            finish();
            startActivity(t);
        }
    }

    /**
     * Delete student.
     *
     * @param view the view
     */
    public void delete_student(View view) {
        if (text.equals("Student's Name"))
        {
            Toast.makeText(this, "Choose student!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            refStudent.child(stuValues.get(p).getStudentID()).removeValue();
            text="Student's Name";
            tvname.setText(text);
            tv_fv.setText("First Vaccine");
            tv_sv.setText("Second Vaccine");
            tvdate1.setText("Date");
            tvdate2.setText("Date");
            tvplace1.setText("Place");
            tvplace2.setText("Place");
            tvgrade.setText("Grade");
            tvclass.setText("Class");
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
                    adp = new ArrayAdapter<String>(DisplayData.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                    list_students.setAdapter(adp);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            refStudent.addValueEventListener(stuListener);
        }
    }

    /**
     * Update vaccines data.
     *
     * @param view the view
     */
    public void update_vaccines_data(View view) {
        if (text.equals("Student's Name"))
        {
            Toast.makeText(this, "Choose student!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent t= new Intent(DisplayData.this,VaccinesDataIn.class);
            t.putExtra("stuFirstName",stuValues.get(p).getFirstName());
            t.putExtra("studentID",stuValues.get(p).getStudentID());
            t.putExtra("v1place",stuValues.get(p).getFirstVaccine().getPlace());
            t.putExtra("v2place",stuValues.get(p).getSecondVaccine().getPlace());
            t.putExtra("v1date",stuValues.get(p).getFirstVaccine().getDate());
            t.putExtra("v2date",stuValues.get(p).getSecondVaccine().getDate());
            finish();
            startActivity(t);
        }
    }
}