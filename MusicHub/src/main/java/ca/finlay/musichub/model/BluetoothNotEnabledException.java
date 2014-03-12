package ca.finlay.musichub.model;

/**
 * Created by James on 3/12/14.
 */
public class BluetoothNotEnabledException extends Exception {

    public BluetoothNotEnabledException() {
        super("Bluetooth is not enabled!");
    }
}
