package id.co.asyst.gabriella.louisa.lernsession;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import id.co.asyst.gabriella.louisa.lernsession.utility.SessionUtility;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etAddress;
    Spinner sEducation;
    Button bSubmit;
    RadioGroup rgGender;
    RadioButton rbFemale, rbMale;
    SessionUtility sessionUtility;
    String gender;
    String education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextViewName);
        etAddress = findViewById(R.id.editTextAddress);
        sEducation = findViewById(R.id.spinnerPendidikan);
        bSubmit = findViewById(R.id.buttonSubmit);
        rgGender = findViewById(R.id.radioGroupGender);
        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);
        sessionUtility = new SessionUtility(this);
        bSubmit.setOnClickListener(this);
        rbMale.setChecked(true);

        String name = sessionUtility.loadName();
        etName.setText(name);

        String gender = sessionUtility.loadGender();
        if (gender.equalsIgnoreCase("Male")) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }

        String address = sessionUtility.loadAddress();
        etAddress.setText(address);

        String education = sessionUtility.loadEducation();
        for (int i = 0; i < sEducation.getAdapter().getCount(); i++) {
            if (sEducation.getAdapter().getItem(i).toString().equalsIgnoreCase(education)) {
                sEducation.setSelection(i);
                break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit:
                if (!TextUtils.isEmpty(etName.getText().toString()) || !TextUtils.isEmpty(etAddress.getText().toString())) {
                    education = sEducation.getSelectedItem().toString();
                    if (rbMale.isChecked()) {
                        gender = "Male";
                    } else if (rbFemale.isChecked()) {
                        gender = "Female";
                    }
                    sessionUtility.saveName(etName.getText().toString(), etAddress.getText().toString(), gender, education);
                    Toast.makeText(getApplicationContext(), "Data Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Boleh Ada Yang Kosong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
