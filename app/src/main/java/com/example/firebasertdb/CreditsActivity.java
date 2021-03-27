package com.example.firebasertdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public void click(View view) {
        finish();
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