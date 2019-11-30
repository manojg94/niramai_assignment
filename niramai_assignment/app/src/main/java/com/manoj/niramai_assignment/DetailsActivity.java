package com.manoj.niramai_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.manoj.niramai_assignment.pojo.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    Integer position=0;
    private  DataModel datalist ;
    private TextView projectname,shortdesc,longdesc,companyname,creationdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        projectname = findViewById(R.id.projectname);
        shortdesc = findViewById(R.id.shortdesc);
        longdesc = findViewById(R.id.longdesc);
        companyname = findViewById(R.id.companyname);
        creationdate = findViewById(R.id.creationdate);



        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        datalist = (DataModel) args.getSerializable("ARRAYLIST");

        Toast.makeText(this,datalist.getCompanyname(), Toast.LENGTH_LONG).show();
        projectname.setText("Project Name : "+datalist.getProjectname());
        shortdesc.setText("Short Description : "+datalist.getShortdesc());
        longdesc.setText("Lond Description : "+datalist.getLongdesc());
        companyname.setText("Company Name : "+datalist.getCompanyname());
        creationdate.setText("Created Date : "+datalist.getCreationdate());
    }
}
