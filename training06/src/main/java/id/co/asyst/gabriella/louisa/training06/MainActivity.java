package id.co.asyst.gabriella.louisa.training06;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.training06.adapter.PersonAdapter;
import id.co.asyst.gabriella.louisa.training06.fragment.BlankFragment;
import id.co.asyst.gabriella.louisa.training06.model.Person;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnSubmitButtonListener {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    EditText etNameEdit;
    ListView listView;
    EditText etName;
    Button bSubmit;
    ArrayList<String> listNama = new ArrayList<>();
    ArrayList<Person> listPerson = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    String EditName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lisView);
        etName = findViewById(R.id.editTextName);
        bSubmit = findViewById(R.id.buttonSubmit);
        listView.setLongClickable(true);
        listNama.add("Satu");
        listNama.add("Dua");
        listNama.add("Tiga");
        listNama.add("Empat");

        for (int i = 0; i < 10; i++) {
            Person person = new Person("Nama Ke- " + i, "Alamat " + i);
            listPerson.add(person);
        }
        PersonAdapter personAdapter = new PersonAdapter(this, listPerson);
        listView.setAdapter(personAdapter);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNama);

        listView.setAdapter(arrayAdapter);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().equalsIgnoreCase("")) {
                    listNama.add(etName.getText().toString());
                    etName.setText("");
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("person", listPerson.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final String selectedText = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("Are You Sure?")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BlankFragment blankFragment = BlankFragment.newInstance(selectedText, position);
                                blankFragment.show(getSupportFragmentManager(), "inputButtonSheet");
                            }
                        }).setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayAdapter.remove(selectedText);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    public void onClickSubmitButton(String name, int position) {
        listNama.set(position, name);
        arrayAdapter.notifyDataSetChanged();
    }
}
