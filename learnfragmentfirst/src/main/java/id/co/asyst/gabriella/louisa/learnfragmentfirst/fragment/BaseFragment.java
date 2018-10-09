package id.co.asyst.gabriella.louisa.learnfragmentfirst.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.co.asyst.gabriella.louisa.learnfragmentfirst.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    Button buttonFragmentA, buttonFragmentB, buttonFragmentC;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this id.co.asyst.gabriella.louisa.learnfragmentfirst.fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        buttonFragmentA = view.findViewById(R.id.btnFragmentA);
        buttonFragmentB = view.findViewById(R.id.btnFragmentB);
        buttonFragmentC = view.findViewById(R.id.btnFragmentC);

        buttonFragmentA.setOnClickListener(this);
        buttonFragmentB.setOnClickListener(this);
        buttonFragmentC.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btnFragmentA:
                fragment = new AFragment();
                break;
            case R.id.btnFragmentB:
                fragment = new BFragment();
                break;
            case R.id.btnFragmentC:
                fragment = new CFragment();
                break;
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
