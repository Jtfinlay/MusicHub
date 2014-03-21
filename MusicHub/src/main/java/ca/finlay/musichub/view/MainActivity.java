package ca.finlay.musichub.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import ca.finlay.musichub.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .commit();
        }


    }

}
