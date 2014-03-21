package ca.finlay.musichub.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import ca.finlay.musichub.R;

/**
 * Created by James on 3/12/14.
 */
public class MainFragment extends Fragment {

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnHost = (Button) rootView.findViewById(R.id.btnHost);
        Button btnClient = (Button) rootView.findViewById(R.id.btnClient);

        btnHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment hostFragment = new HostFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, hostFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment clientFragment = new ClientFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, clientFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return rootView;
    }
}
