package id.co.asyst.gabriella.louisa.formlouisa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.formlouisa.utility.Constant;

public class ResultActivity extends AppCompatActivity {
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultTV = findViewById(R.id.textViewResult);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            resultTV.setText(bundle.getString(Constant.KEY_RESULT));
        } else {
            resultTV.setText("Tidak Ada Data Masuk");
        }
    }
}
