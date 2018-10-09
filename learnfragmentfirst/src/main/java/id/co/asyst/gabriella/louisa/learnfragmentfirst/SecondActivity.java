package id.co.asyst.gabriella.louisa.learnfragmentfirst;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.learnfragmentfirst.fragment.DFragment;
import id.co.asyst.gabriella.louisa.learnfragmentfirst.fragment.InputBottomSheet;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, DFragment.OnSubmitButtonListener, InputBottomSheet.OnSubmitButtonListener {
    TextView textViewName, textViewAlamat, textViewBirthday;
    Button buttonInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewName = findViewById(R.id.textViewName);
        textViewAlamat = findViewById(R.id.textViewAddress);
        textViewBirthday = findViewById(R.id.textViewBirthday);
        buttonInput = findViewById(R.id.buttonInput);
        buttonInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonInput:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                DFragment dFragment = DFragment.newInstance(textViewName.getText().toString(), textViewAlamat.getText().toString(), textViewBirthday.getText().toString());
                transaction.replace(android.R.id.content, dFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onClickSubmitButton(String name, String address, String birthdate) {
        textViewName.setText(name);
        textViewAlamat.setText(address);
        textViewBirthday.setText(birthdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_input:
                InputBottomSheet inputBottomSheet = InputBottomSheet.newInstance(textViewName.getText().toString(), textViewAlamat.getText().toString(), textViewBirthday.getText().toString());
                inputBottomSheet.show(getSupportFragmentManager(), "inputButtonSheet");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
