/* © IBS Group. See LICENSE file for full copyright & licensing details. */

import { NativeModules, DeviceEventEmitter } from "react-native";

const barcodeScanner = NativeModules.RNHoneywellBarcodeScanner;

/** This method create a new instance of the barcode reader. */
const startReader = async () => {
    await barcodeScanner.startReader();
}

/** The following method listens to the barcode sensor reader event
 * whenever it emitted, and execute the callback.
 */
const barcodeScanned = async ( callback ) => {
    await barcodeScanner.startReader();
    DeviceEventEmitter.addListener( "io.ibsgroup.codeCaptured", ( data ) => {
        if( data )
            callback( data );
    });
};

/** This method removes all the barcode sensor listeners. */
const destroyListeners = () => {
    DeviceEventEmitter.removeAllListeners( "io.ibsgroup.codeCaptured" );
    barcodeScanner.stopReader();
};

/** This method sets the barcode reader to automatic mode.  */
const setAutomaticMode = () => { 
    barcodeScanner.setReaderMode(
        barcodeScanner.AUTOMATIC 
    );
};

/** This method sets the barcode reader to manual mode.  */
const setManualMode = () => {
    barcodeScanner.setReaderMode(
        barcodeScanner.MANUAL 
    );
};

/** This method stops the barcode reader. */
const stopReader = () => barcodeScanner.stopReader()

module.exports = {
    barcodeScanned,
    destroyListeners,
    setAutomaticMode,
    setManualMode,
    startReader,
    stopReader
};
