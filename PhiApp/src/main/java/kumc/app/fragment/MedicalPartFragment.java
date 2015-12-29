package kumc.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kumc.app.phi.R;

/**
 * 진료과 프레그먼트
 */
public class MedicalPartFragment extends BaseFragment {

    public MedicalPartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_main, container, false);
        return rootView;
    }
}
