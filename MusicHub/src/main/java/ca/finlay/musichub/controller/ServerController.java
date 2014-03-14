package ca.finlay.musichub.controller;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.List;

/**
 * Created by James on 3/14/14.
 */
public class ServerController extends BluetoothController {

    private static ServerThread server_thread;

    private List<BluetoothSocket> connected_clients;
    private String MY_NAME;

    /**
     * Start listening for incoming connections
     * @param name  A name for other devices to see.
     */
    public void startListen(String name) {
        MY_NAME = name;
        server_thread.run();
    }

    /**
     * Stop listening for incoming connections
     */
    public void stopListen() {
        server_thread.cancel();
    }

    /**
     * A new client has connected
     * @param socket
     */
    public void onClientConnection(BluetoothSocket socket) {
        // TODO:: Client connected, do stuff
    }

    /**
     * Asynchronous thread to manage incoming bluetooth connections when running
     * as host server.
     */
    private class ServerThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public ServerThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                tmp = adapter.listenUsingInsecureRfcommWithServiceRecord(MY_NAME, MY_UUID);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;

            // Keep listening until exception occurs or a socket is returned
            while (true) {

                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    onClientConnection(socket);
                    try {
                        mmServerSocket.close();
                    } catch (Exception e) {}
                    break;
                }
            }
        }
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }
}
