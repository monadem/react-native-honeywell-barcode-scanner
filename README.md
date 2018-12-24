
# react-native-honeywell-barcode-scanner

## Getting started

`$ npm install react-native-honeywell-barcode-scanner --save`

### Mostly automatic installation

`$ react-native link react-native-honeywell-barcode-scanner`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNHoneywellBarcodeScannerPackage;` to the imports at the top of the file
  - Add `new RNHoneywellBarcodeScannerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
      ```
        	include ":react-native-honeywell-barcode-scanner"
        	project( ":react-native-honeywell-barcode-scanner" ).projectDir = new File( rootProject.projectDir, 	"../node_modules/react-native-honeywell-barcode-scanner/android" )
      ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
     ```
      compile project( ":react-native-honeywell-barcode-scanner" )
     ```



   ```


​```javascript
import { getCodeData } from "react-native-honeywell-barcode-scanner";

//use this method whenever you need it

getCodeData( ( data ) => {
      ...
    })
    
//use this method to destroy all listeners
destroyListener();
   ```

### More configuration:

1. Append the following line to `android/settings.gradle`:

   ```
   include ":DataCollection"
   ```

2.  Cut `DataCollection` folder and paste in in `android`

3. Open up `AndroidManifest.xml` and add :

   ```
    <manifest xmlns:tools="http://schemas.android.com/tools"
      <application
            tools:node="replace"
   ```
