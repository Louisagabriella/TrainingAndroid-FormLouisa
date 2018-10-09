package id.co.asyst.gabriella.louisa.formlouisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.formlouisa.utility.Constant;

public class Training extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener {

    EditText editText1;
    TextView textView1;
    Button button1;
    RadioGroup radioGender;
    String selectedGender;
    CheckBox checkBoxSoto, checkBoxBakso, checkBoxGulai;
    String soto = "", bakso = "", gulai = "";
    Spinner citySpinner;
    String selectedcity;
    ArrayList<String> listCity = new ArrayList<>();
    ArrayList<String> listFood = new ArrayList<>();
    String foods = "";
    String editTextString;
    Switch switch_button;
    ToggleButton toggle_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);
        radioGender = findViewById(R.id.radioGander);
        checkBoxSoto = findViewById(R.id.checkboxSoto);
        checkBoxBakso = findViewById(R.id.checkboxBakso);
        checkBoxGulai = findViewById(R.id.checkboxGulai);
        citySpinner = findViewById(R.id.spinner_kota);
        switch_button = findViewById(R.id.switch_button);
        toggle_button = findViewById(R.id.toggle_button);

        button1.setOnClickListener(this);
        radioGender.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radioButtonMale)).setChecked(true);
        checkBoxSoto.setOnCheckedChangeListener(this);
        checkBoxBakso.setOnCheckedChangeListener(this);
        checkBoxGulai.setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkboxSoto)).setChecked(true);
        citySpinner.setOnItemSelectedListener(this);
        listCity.add("Tulungagung");
        listCity.add("Malang");
        listCity.add("Jakarta");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listCity);
        citySpinner.setAdapter(cityAdapter);
        switch_button.setOnCheckedChangeListener(this);
        toggle_button.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        editTextString = editText1.getText().toString();
        int id = v.getId();
        switch (id) {
            case R.id.button1:
                if (!TextUtils.isEmpty(editTextString)) {
//                    Toast.makeText(getApplicationContext(), "welcome "+ editTextString, Toast.LENGTH_SHORT).show();
                    foods = "";
                    for (int i = 0; i < listFood.size(); i++) {
                        foods = foods + " " + listFood.get(i);
                    }
                    textView1.setText("Welcome " + editTextString + "\n" + "Gender " + selectedGender + "\n" + "Makanan Kesukaan : \n" + foods + "\n" + "Kota Asal: " + selectedcity + "\n");
                    //alert
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are You Sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Training.this, ResultActivity.class);
                                    String result = "Welcome " + editTextString + "\n" + "Gender " + selectedGender + "\n" + "Makanan Kesukaan : \n" + foods + "\n" + "Kota Asal: " + selectedcity + "\n";
                                    intent.putExtra(Constant.KEY_RESULT, result);
                                    startActivity(intent);
                                    finish();
                                }
                            }).setNegativeButton("No", null).show();
                    //pindah halaman
//                    Intent intent = new Intent(this, ResultActivity.class);
//                    String result = "Welcome "+editTextString+"\n"+"Gender "+selectedGender+"\n"+"Makanan Kesukaan : \n"+foods+"\n"+"Kota Asal: "+selectedcity+"\n";
//                    intent.putExtra(Constant.KEY_RESULT,result);
//                    startActivity(intent);
//                    finish();
                    break;
                }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonMale:
                selectedGender = "Male";
                break;
            case R.id.radioButtonFamale:
                selectedGender = "Famale";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.checkboxSoto:
                if (isChecked) {
//                    soto = "soto";
                    listFood.add("- Soto\n");
                } else {
//                    soto = "";
                    listFood.remove("- Soto\n");
                }
                break;
            case R.id.checkboxBakso:
                if (isChecked) {
//                    soto = "soto";
                    listFood.add("Bakso");
                } else {
//                    soto = "";
                    listFood.remove("Bakso");
                }
                break;
            case R.id.checkboxGulai:
                if (isChecked) {
//                    soto = "soto";
                    listFood.add("Gulai");
                } else {
//                    soto = "";
                    listFood.remove("Gulai");
                }
                break;
            case R.id.switch_button:
                Log.d("MainAct testSwi tch", String.valueOf(isChecked));
                break;
            case R.id.toggle_button:
                Log.d("MainAct textToggle", String.valueOf(toggle_button.isChecked()));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedcity = citySpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
