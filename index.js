/* © IBS Group. See LICENSE file for full copyright & licensing details. */

import { NativeModules, DeviceEventEmitter } from "react-native";

/** The following method listens to the codebar sensor reader event
 * whenever it emitted, and execute the callback.
 */

const barCodeScanned = ( callback ) => {
    DeviceEventEmitter.addListener( "io.ibsgroup.codeCaptured", callback );
};

/** This method removes all the codebar sensor listeners. */

const detroyListeners = () => {
    DeviceEventEmitter.removeAllListeners( "io.ibsgroup.codeCaptured" );
};

module.exports = {
    barCodeScanned,
    detroyListeners
};
