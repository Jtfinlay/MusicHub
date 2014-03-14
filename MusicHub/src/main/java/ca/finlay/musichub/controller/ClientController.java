package ca.finlay.musichub.controller;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * Created by James on 3/14/14.
 */
public class ClientController extends BluetoothController {


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
