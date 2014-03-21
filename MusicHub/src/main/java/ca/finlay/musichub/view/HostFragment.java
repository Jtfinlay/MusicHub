package ca.finlay.musichub.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ca.finlay.musichub.R;

/**
 * Created by James on 3/12/14.
 */
public class HostFragment extends Fragment {

    public HostFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_host, container, false);

        return rootView;
    }
}
