package id.co.asyst.gabriella.louisa.mymovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import id.co.asyst.gabriella.louisa.mymovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSortByFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    SearchSortByFragment.OnSubmitButtonListener listener;

    EditText et;
    Button b;

    public SearchSortByFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_sort_by, container, false);

        et = view.findViewById(R.id.editText);
        b = view.findViewById(R.id.button);

        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                listener.onClickSubmit(et.getText().toString());
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchSortByFragment.OnSubmitButtonListener) {
            listener = (SearchSortByFragment.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "activity harus implement interface OnSubmitButtonListener");
        }
    }

    public interface OnSubmitButtonListener {
        void onClickSubmit(String keyWord);
    }

}
