package ca.finlay.musichub.model;

/**
 * Created by James on 3/12/14.
 */
public class BluetoothNotSupportedException extends Exception {

    public BluetoothNotSupportedException() {
        super("Device does not support bluetooth!");
    }
}
