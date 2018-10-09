package id.co.asyst.gabriella.louisa.training06.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import id.co.asyst.gabriella.louisa.training06.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    BlankFragment.OnSubmitButtonListener listener;
    EditText etName;
    Button bEdit;
    int position;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String name, int position) {
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putInt("position", position);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        etName = view.findViewById(R.id.editTextName);
        bEdit = view.findViewById(R.id.buttonEdit);
        etName.setText(getArguments().getString("nama"));
        bEdit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        position = getArguments().getInt("position");
        String name = etName.getText().toString();
        switch (v.getId()) {
            case R.id.buttonEdit:
                listener.onClickSubmitButton(name, position);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BlankFragment.OnSubmitButtonListener) {
            listener = (BlankFragment.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "activity harus implement interface OnSubmitButtonListener");
        }
    }

    public interface OnSubmitButtonListener {
        void onClickSubmitButton(String name, int position);
    }
}
