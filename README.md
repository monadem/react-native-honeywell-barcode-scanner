# react-native-honeywell-barcode-scanner

## Installation (Android only, iOS is not supported)

`$ npm install react-native-honeywell-barcode-scanner --save`


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
    - Add `import com.reactlibrary.RNHoneywellBarcodeScannerPackage;` to the imports at the top of the file
    - Add `new RNHoneywellBarcodeScannerPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
      ```
      include ":react-native-honeywell-barcode-scanner"
      project( ":react-native-honeywell-barcode-scanner" ).projectDir = new File( rootProject.projectDir, "../node_modules/react-native-honeywell-barcode-scanner/android" )
      ```
  
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
     ```
      implementation project( ":react-native-honeywell-barcode-scanner" )
     ```

### More configuration:

1. Append the following line to `android/settings.gradle`:

   ```
   include ":DataCollection"
   ```

2.  Cut `DataCollection` folder and paste in in `android`

3. Open up `AndroidManifest.xml` and set `tools:node` value to `replace`:

   ```xml
   <manifest xmlns:tools="http://schemas.android.com/tools"
       <application tools:node="replace" ... >
           ...
       </application>
       ...
   </manifest>
   ```

## Usage

   ```javascript
import {
    StartReader,
    StopReader,
    OnBarcodeScan,
    DestroyListener,
    SetAutomaticMode,
    SetManualMode
} from "react-native-honeywell-barcode-scanner";

/* Starts the barcode reader. */
StartReader();

/* Stops the barcode reader. */
StopReader();

/* Listens to the barcode sensor reader event. */
OnBarcodeScan( callback );

/* Removes the barcode sensor listener. */
DestroyListener();

/* Sets the barcode reader's mode to manual. */
SetManualMode();

/* Sets the barcode reader's mode to automatic. */
SetAutomaticMode();
   ```

### 