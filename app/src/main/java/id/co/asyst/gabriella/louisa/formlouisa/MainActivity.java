package id.co.asyst.gabriella.louisa.formlouisa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextA, editTextB;
    Button button1, button2, button3, button4;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalkulator);
        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        hasil = findViewById(R.id.hasil);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String a = editTextA.getText().toString();
        String b = editTextB.getText().toString();
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
            Toast.makeText(getApplicationContext(), "Angka 1 dan Angka 2 Tidak Boleh Kosong ", Toast.LENGTH_SHORT).show();
            hasil.setText("Hasil Kalkulator");
        } else {
            int angka1 = Integer.parseInt(a);
            int angka2 = Integer.parseInt(b);
            switch (id) {
                case R.id.button1:
                    int hasilPertambahan = angka1 + angka2;
                    hasil.setText("Hasil: " + hasilPertambahan);
                    break;
                case R.id.button2:
                    int hasilPengurangan = angka1 - angka2;
                    hasil.setText("Hasil: " + hasilPengurangan);
                    break;
                case R.id.button3:
                    int hasilPerkalian = angka1 * angka2;
                    hasil.setText("Hasil: " + hasilPerkalian);
                    break;
                case R.id.button4:
                    if (angka2 == 0) {
                        Toast.makeText(getApplicationContext(), "Undefined", Toast.LENGTH_SHORT).show();
                        hasil.setText("Hasil Kalkulator");
                    } else {
                        float ab = Float.parseFloat(a);
                        float bc = Float.parseFloat(b);
                        float hasilPembagian = ab / bc;
                        hasil.setText("Hasil: " + hasilPembagian);
                    }
                    break;
            }
        }
    }
}
