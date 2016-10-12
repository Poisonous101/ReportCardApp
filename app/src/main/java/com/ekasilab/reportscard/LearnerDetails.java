package com.ekasilab.reportscard;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LearnerDetails extends AppCompatActivity implements View.OnClickListener {

    private TextView txtName, txtSurname, txtStudentNo, txtAddress, txtSubjectName, txtTest1, txtTest2, txtTest3, txtSubjectPercentage;
    private Button btnDelete, btnUpdate;
    LearnerReport learner;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        double yearMark;
        txtName = (TextView) findViewById(R.id.name);
        txtStudentNo = (TextView) findViewById(R.id.studentNo);
        txtSurname = (TextView) findViewById(R.id.studentSurname);
        txtAddress = (TextView) findViewById(R.id.studentAddress);
        txtSubjectName = (TextView) findViewById(R.id.subject);
        txtTest1 = (TextView) findViewById(R.id.studentTest1);
        txtTest2 = (TextView) findViewById(R.id.studentTest2);
        txtTest3 = (TextView) findViewById(R.id.studentTest3);
        txtSubjectPercentage = (TextView) findViewById(R.id.SubjectMark);


        btnDelete = (Button) findViewById(R.id.Delete);
        btnUpdate = (Button) findViewById(R.id.updateSt);
        learner = (LearnerReport) getIntent().getSerializableExtra("learner");
        txtStudentNo.setText("" + learner.getStdNo());
        txtName.setText(learner.getName());
        txtSurname.setText(learner.getSurname());
        txtSubjectName.setText(learner.getSubjectName());
        txtAddress.setText(learner.getLearnerAddress());
        txtTest1.setText(String.format("" + learner.getTest1()));
        txtTest2.setText(String.format("" + learner.getTest2()));
        txtTest3.setText(String.format("" + learner.getTest3()));
        yearMark = learner.calcSubjectMark(Double.parseDouble(txtTest1.getText().toString()), Double.parseDouble(txtTest2.getText().toString()), Double.parseDouble(txtTest3.getText().toString()));
        txtSubjectPercentage.setText(String.format("" + yearMark));

        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Delete) {
            learner = db.getLearner(txtName.getText().toString());
            db.deleteLearner(learner);

            finish();
            Intent in = new Intent(LearnerDetails.this, OptionsActivity.class);
            startActivity(in);
        } else if (v.getId() == R.id.updateSt) {
            learner = db.getLearner(txtName.getText().toString());
            Intent in = new Intent(LearnerDetails.this, UpdateActivity.class);
            in.putExtra("learner", learner);
            startActivity(in);
            finish();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
