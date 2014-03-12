package ca.finlay.musichub.model;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

/**
 * Created by James on 3/12/14.
 */
public class BluetoothManager {
    private static final String TAG = "BluetoothManager";

    private static final int REQUEST_ENABLE_BT = 1;

    private static BluetoothManager manager;
    private static BluetoothAdapter adapter;


    public BluetoothManager getInstance() throws BluetoothNotSupportedException, BluetoothNotEnabledException {
        if (manager != null)
            return manager;
        else {
            adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter == null)
                throw new BluetoothNotSupportedException();

            if (!adapter.isEnabled())
                throw new BluetoothNotEnabledException();


            return manager;
        }
    }
    public static void enableBluetooth(Activity activity) throws BluetoothNotSupportedException {
        if (adapter == null)
            adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null)
            throw new BluetoothNotSupportedException();
        if (!adapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
}
