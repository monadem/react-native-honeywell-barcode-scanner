/* © IBS Group. See LICENSE file for full copyright & licensing details. */

import { NativeModules, DeviceEventEmitter } from "react-native";

const BarcodeScanner = NativeModules.RNHoneywellBarcodeScanner;

/** This method create a new instance of the barcode reader. */
const startReader = async () => {
    await BarcodeScanner.startReader();
}

/** The following method listens to the barcode sensor reader event
 * whenever it emitted, and execute the callback.
 */
const barcodeScanned = async ( callback ) => {
    await BarcodeScanner.startReader();
    DeviceEventEmitter.addListener( "io.ibsgroup.codeCaptured", ( data ) => {
        if( data )
            callback( data );
    });
};

/** This method removes all the barcode sensor listeners. */
const destroyListeners = () => {
    DeviceEventEmitter.removeAllListeners( "io.ibsgroup.codeCaptured" );
    BarcodeScanner.stopReader();
};

/** This method sets the barcode reader to automatic mode.  */
const setAutomaticMode = () => { 
    BarcodeScanner.setReaderMode(
        BarcodeScanner.AUTOMATIC 
    );
};

/** This method sets the barcode reader to manual mode.  */
const setManualMode = () => {
    BarcodeScanner.setReaderMode(
        BarcodeScanner.MANUAL 
    );
};

/** This method stops the barcode reader. */
const stopReader = () => BarcodeScanner.stopReader()

module.exports = {
    barcodeScanned,
    destroyListeners,
    setAutomaticMode,
    setManualMode,
    startReader,
    stopReader
};
