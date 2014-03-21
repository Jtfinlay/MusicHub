package ca.finlay.musichub.controller;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.IOException;

/**
 * Created by James on 3/14/14.
 */
public class ClientController extends BluetoothController {

    private BroadcastReceiver mReceiver;

    public void startDeviceDiscovery(Context c) {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get bluetooth device from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // TODO:: Do something with the device
                }
            }
        };
        // Register the Broadcast Receiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        c.registerReceiver(mReceiver, filter);
    }
    public void stopDeviceDiscovery(Context c) {
        c.unregisterReceiver(mReceiver);
    }


    /**
     * Asynchronous thread to connect to remote server device as client.
     * Requires the server bluetooth device in order to run.
     */
    private class ClientThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ClientThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {}
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            adapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException e) {
                // Unable to connect; close socket and get out
                try {
                    mmSocket.close();
                } catch (IOException e1) {}
            }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {}
        }
    }
}
