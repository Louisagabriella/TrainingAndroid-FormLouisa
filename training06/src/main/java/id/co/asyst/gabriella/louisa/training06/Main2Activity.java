package id.co.asyst.gabriella.louisa.training06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.training06.model.Person;

public class Main2Activity extends AppCompatActivity {

    TextView tvName, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvName = findViewById(R.id.textViewName);
        tvAddress = findViewById(R.id.textViewAddress);

        if (getIntent().getExtras() != null) {
            Person person = getIntent().getExtras().getParcelable("person");
            tvName.setText(person.getName());
            tvAddress.setText(person.getAddress());
        }
    }
}
