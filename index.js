/* © IBS Group. See LICENSE file for full copyright & licensing details. */

import { NativeModules, DeviceEventEmitter } from "react-native";

const BarcodeScanner = NativeModules.RNHoneywellBarcodeScanner;

/* Creates a new instance of the barcode reader. */
const StartReader = async () => {
    await BarcodeScanner.startReader();
}

/* Listens to the barcode sensor reader event */
const OnBarcodeScan = async ( callback ) => {
    DeviceEventEmitter.addListener( "io.ibsgroup.codeCaptured", ( data ) => {
        if( data )
            callback( data );
    });
};

/* Removes the barcode sensor listener. */
const DestroyListener = () => {
    DeviceEventEmitter.removeAllListeners( "io.ibsgroup.codeCaptured" );
};

/* Sets the barcode reader's mode to automatic. */
const SetAutomaticMode = () => { 
    BarcodeScanner.setReaderMode(
        BarcodeScanner.AUTOMATIC 
    );
};

/* Sets the barcode reader's mode to manual. */
const SetManualMode = () => {
    BarcodeScanner.setReaderMode(
        BarcodeScanner.MANUAL 
    );
};

/* Stops the barcode reader. */
const StopReader = () => BarcodeScanner.stopReader()

module.exports = {
    OnBarcodeScan,
    DestroyListener,
    SetAutomaticMode,
    SetManualMode,
    StartReader,
    StopReader
};
