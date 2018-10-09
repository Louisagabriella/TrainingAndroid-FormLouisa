package id.co.asyst.gabriella.louisa.learnfragmentfirst.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.co.asyst.gabriella.louisa.learnfragmentfirst.R;
import id.co.asyst.gabriella.louisa.learnfragmentfirst.utility.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    InputBottomSheet.OnSubmitButtonListener listener;
    EditText editTextName, editTextAddress;
    Button buttonSubmit;
    TextView tvBirthdate;
    ImageView ivDate;
    DatePickerDialog datePickerDialog;

    public InputBottomSheet() {
        // Required empty public constructor
    }

    public static InputBottomSheet newInstance(String name, String address, String birthdate) {
        InputBottomSheet inputBottomSheet = new InputBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putString("alamat", address);
        bundle.putString("tanggal", birthdate);
        inputBottomSheet.setArguments(bundle);
        return inputBottomSheet;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_bottom_sheet, container, false);
        editTextName = view.findViewById(R.id.editTextName);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);
        tvBirthdate = view.findViewById(R.id.textViewDate);
        ivDate = view.findViewById(R.id.imageViewDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

        if (getArguments() != null) {
            editTextName.setText(getArguments().getString("nama", ""));
            editTextAddress.setText(getArguments().getString("alamat", ""));
            tvBirthdate.setText(getArguments().getString("tanggal", ""));
        }

        int year = 2000, month = 0, day = 1;
        String selectedDate = tvBirthdate.getText().toString();
        if (!selectedDate.equalsIgnoreCase("")) {
            calendar.setTime(DateUtils.formatStringtoDate("dd MMMM yyyy", selectedDate));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        buttonSubmit.setOnClickListener(this);
        ivDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String etName = editTextName.getText().toString();
        String etAddress = editTextAddress.getText().toString();
        String etBirthDate = tvBirthdate.getText().toString();
        switch (v.getId()) {
            case R.id.buttonSubmit:
                if (!TextUtils.isEmpty(etName) || !TextUtils.isEmpty(etAddress)) {
                    listener.onClickSubmitButton(etName, etAddress, etBirthDate);
                    dismiss();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Tidak Boleh Kosong", Toast.LENGTH_LONG);
                }
                break;
            case R.id.imageViewDate:
                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DFragment.OnSubmitButtonListener) {
            listener = (InputBottomSheet.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "activity harus implement interface OnSubmitButtonListener");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + " " + (month + 1) + " " + year;
        tvBirthdate.setText(DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date));
    }

    public interface OnSubmitButtonListener {
        void onClickSubmitButton(String name, String address, String bithDate);
    }
}
