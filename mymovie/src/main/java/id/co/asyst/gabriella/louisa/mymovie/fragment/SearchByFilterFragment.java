package id.co.asyst.gabriella.louisa.mymovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.mymovie.R;
import id.co.asyst.gabriella.louisa.mymovie.constant.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByFilterFragment extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    //    InputBottomSheet.OnSubmitButtonListener listener
    SearchByFilterFragment.OnSubmitButtonClickListener listener;
    Spinner sBy, sYear;
    ArrayList<String> listBy = new ArrayList<>();
    ArrayList<String> listYear = new ArrayList<>();
    String selectedBy = "", selectedYear = "";
    Button bSearch;

    public SearchByFilterFragment() {
        // Required empty public constructor
    }

    public static SearchByFilterFragment newInstance(String year, String sort_by) {
        SearchByFilterFragment searchByFilterFragment = new SearchByFilterFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_YEAR, year);
        bundle.putString(Constant.KEY_SEARCH, sort_by);
        searchByFilterFragment.setArguments(bundle);
        return searchByFilterFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_by_year, container, false);
        sBy = view.findViewById(R.id.spinnerBy);
        sYear = view.findViewById(R.id.spinnerYear);
        bSearch = view.findViewById(R.id.buttonSearch);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listBy);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listYear);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listBy.add("popularity.desc");
        listBy.add("popularity.asc");
        listBy.add("release_date.desc");
        listBy.add("release_date.asc");
        listYear.add("none");
        for (int i = 2020; i >= 1900; i--) {
            listYear.add((i) + "");
        }
        sBy.setAdapter(arrayAdapter);
        sYear.setAdapter(arrayAdapter1);
        bSearch.setOnClickListener(this);
        sBy.setOnItemSelectedListener(this);
        sYear.setOnItemSelectedListener(this);
        selectedYear = getArguments().getString(Constant.KEY_YEAR);
        int position = arrayAdapter1.getPosition(selectedYear);
        sYear.setSelection(position);
        selectedBy = getArguments().getString(Constant.KEY_SEARCH);
        int position1 = arrayAdapter1.getPosition(selectedBy);
        sBy.setSelection(position1);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBy = sBy.getSelectedItem().toString();
        selectedYear = sYear.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSearch:
                listener.onSubmitButton(selectedYear, selectedBy);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchByFilterFragment.OnSubmitButtonClickListener) {
            listener = (SearchByFilterFragment.OnSubmitButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + "activity harus implement interface OnSubmitButtonListener");
        }
    }

    public interface OnSubmitButtonClickListener {
        void onSubmitButton(String year, String search);
    }
}
