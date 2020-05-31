package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class BasicDetilesActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] listBranch,listSemester,listField;
    private EditText InfoName, InfoContact, InfoEmail, ProjectInfo,edt_contact;
    private TextView txtInfoBranch, txtInfoSemester, txtInfoField;
    private Button btnInfo;
    private String Branch,Semester,Field;
    String parseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detiles);
        edt_contact = findViewById(R.id.edt_contact);
        InfoName = findViewById(R.id.InfoName);
        InfoEmail = findViewById(R.id.InfoEmail);
        InfoEmail.setText(ParseUser.getCurrentUser().getEmail());
        InfoContact = findViewById(R.id.InfoContact);
        ProjectInfo = findViewById(R.id.ProjectInfo);
        txtInfoBranch = findViewById(R.id.txtInfoBranch);
        txtInfoSemester = findViewById(R.id.txtInfoSemester);
        txtInfoField = findViewById(R.id.txtInfoField);
        btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(BasicDetilesActivity.this);
        txtInfoBranch.setOnClickListener(BasicDetilesActivity.this);
        txtInfoSemester.setOnClickListener(BasicDetilesActivity.this);
        txtInfoField.setOnClickListener(BasicDetilesActivity.this);
    }

    @Override
    public void onClick(View v) {
try {

    switch (v.getId()) {

        case R.id.btnInfo:
            final ParseObject users = new ParseObject("Member");
            users.put("Name", InfoName.getText().toString());
            users.put("Email", ParseUser.getCurrentUser().getEmail());
            users.put("Contact_6", Integer.parseInt(InfoContact.getText().toString()));
            users.put("Contact_4", Integer.parseInt(edt_contact.getText().toString()));
            users.put("Workshop", ProjectInfo.getText().toString());
            users.put("Branch", Branch);
            users.put("Semester", Semester);
            users.put("Interest", Field);

            final ProgressDialog progressDialog = new ProgressDialog(BasicDetilesActivity.this);
            progressDialog.setMessage("Please Wait ...");
            progressDialog.show();

            users.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Message(users.get("Name") + "   Your Details Are Fetched Successfully", "S");
                        Message(parseID,"I");

                        Intent intent = new Intent(BasicDetilesActivity.this, MainInterfaceActivity.class);
                        startActivity(intent);
                    } else {
                        Message(e.getMessage(), "E");
                    }
                    progressDialog.dismiss();
                    progressDialog.setCancelable(false);

                }
            });
            break;
        case R.id.txtInfoBranch:
            listBranch = new String[]{"Mechanical", "Civil", "Electrical",
                    "Computer Science &", "Electronics and Telecommunication", "Information Technology", "Electronics & Electrical"};
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(BasicDetilesActivity.this);
            mBuilder.setTitle("Branch");
            mBuilder.setIcon(R.drawable.tech);
            mBuilder.setSingleChoiceItems(listBranch, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txtInfoBranch.setText(listBranch[which] + " Engineering");
                    Branch = txtInfoBranch.getText().toString();
                    dialog.dismiss();

                }
            });
            AlertDialog alertDialogB = mBuilder.create();
            alertDialogB.show();
            alertDialogB.setCancelable(false);
            break;
        case R.id.txtInfoSemester:
            listSemester = new String[]{"1st", "2nd", "3rd",
                    "4th", "5th", "6th", "7th", "8th"};
            AlertDialog.Builder mBuilderS = new AlertDialog.Builder(BasicDetilesActivity.this);
            mBuilderS.setTitle("Semester");
            mBuilderS.setIcon(R.drawable.tech);
            mBuilderS.setSingleChoiceItems(listSemester, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txtInfoSemester.setText(listSemester[which] + " Semester");
                    Semester = txtInfoSemester.getText().toString();
                    dialog.dismiss();


                }
            });
            AlertDialog alertDialog = mBuilderS.create();
            alertDialog.show();
            Semester = txtInfoSemester.getText().toString();

            alertDialog.setCancelable(false);
            break;
        case R.id.txtInfoField:
            listField = new String[]{"Management", "Technical", "Both"};
            AlertDialog.Builder mBuilderF = new AlertDialog.Builder(BasicDetilesActivity.this);
            mBuilderF.setTitle("Branch");
            mBuilderF.setIcon(R.drawable.tech);
            mBuilderF.setSingleChoiceItems(listField, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txtInfoField.setText(listField[which]);
                    dialog.dismiss();
                    Field = txtInfoField.getText().toString();

                }
            });
            AlertDialog alertDialogF = mBuilderF.create();
            alertDialogF.show();
            alertDialogF.setCancelable(false);
            break;

    }
}catch (Exception E){
    Message(E.getMessage(),"E");
}

    }

    public void Message(String Message, String act){
        switch (act){
            case "S":
                FancyToast.makeText(BasicDetilesActivity.this,Message,FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
                break;
            case "E":
                FancyToast.makeText(BasicDetilesActivity.this,Message,FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();
                break;
            case "I":
                FancyToast.makeText(BasicDetilesActivity.this,Message,FancyToast.INFO,FancyToast.LENGTH_LONG,true).show();
                break;
        }

    }

}