package ca.finlay.musichub.controller;

import android.app.Activity;
import android.bluetooth.*;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ca.finlay.musichub.model.BluetoothNotEnabledException;
import ca.finlay.musichub.model.BluetoothNotSupportedException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by James on 3/12/14.
 */
public class BluetoothController {
    private static final String TAG = "BluetoothManager";

    private static final int REQUEST_ENABLE_BT = 1;

    protected static BluetoothController manager;
    protected static BluetoothAdapter adapter;
    protected static UUID MY_UUID;



    /**
     * Tries to create a new instance of the Controller if it does not exist.
     * @return BluetoothController instance
     * @throws BluetoothNotSupportedException
     * @throws BluetoothNotEnabledException
     */
    public static BluetoothController getInstance() throws BluetoothNotSupportedException, BluetoothNotEnabledException {
        if (manager != null)
            return manager;
        else {
            adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter == null)
                throw new BluetoothNotSupportedException();

            if (!adapter.isEnabled())
                throw new BluetoothNotEnabledException();

            MY_UUID = UUID.randomUUID();

            manager = new BluetoothController();

            return manager;
        }
    }

    /**
     * Launches Activity for user to connect to bluetooth
     * @param activity
     * @throws BluetoothNotSupportedException
     */
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

