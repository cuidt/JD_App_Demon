package jd.com.jd_app_demon.fragment.xqfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import jd.com.jd_app_demon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Toast.makeText(getActivity(), "评价正在开发中", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_evaluate, container, false);
    }

}
