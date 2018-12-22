/* © IBS Group. See LICENSE file for full copyright & licensing details. */

package com.reactlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.ScannerNotClaimedException;
import com.honeywell.aidc.ScannerUnavailableException;
import com.honeywell.aidc.TriggerStateChangeEvent;
import com.honeywell.aidc.UnsupportedPropertyException;

import java.util.HashMap;
import java.util.Map;

public class RNHoneywellBarcodeScannerModule extends ReactContextBaseJavaModule implements BarcodeReader.BarcodeListener,
    BarcodeReader.TriggerListener {

    private final ReactApplicationContext reactContext;
    private BarcodeReader reader;
    private AidcManager manager;
    private boolean triggerState = false;
    String barcodeData;

    public RNHoneywellBarcodeScannerModule( ReactApplicationContext reactContext ) {
        super( reactContext );
        this.reactContext = reactContext;

		AidcManager.create( RNHoneywellBarcodeScannerModule.this.reactContext, new AidcManager.CreatedCallback() {

            @Override
            public void onCreated( AidcManager aidcManager ) {
                manager = aidcManager;
                reader = manager.createBarcodeReader();

                try{
                    if( reader != null ) {
                        reader.claim();
                    }
                    reader.setProperty( BarcodeReader.PROPERTY_TRIGGER_CONTROL_MODE,
                            BarcodeReader.TRIGGER_CONTROL_MODE_CLIENT_CONTROL );
                } catch ( ScannerUnavailableException e ) {
                    e.printStackTrace();
                } catch ( UnsupportedPropertyException e ) {
                    e.printStackTrace();
                }

                reader.addTriggerListener( RNHoneywellBarcodeScannerModule.this );

                Map<String, Object> properties = new HashMap<String, Object>();

                properties.put( BarcodeReader.PROPERTY_CODE_128_ENABLED, true );
                properties.put( BarcodeReader.PROPERTY_GS1_128_ENABLED, true );
                properties.put( BarcodeReader.PROPERTY_QR_CODE_ENABLED, true );
                properties.put( BarcodeReader.PROPERTY_CODE_39_ENABLED, true );
                properties.put( BarcodeReader.PROPERTY_DATAMATRIX_ENABLED, true );
                properties.put( BarcodeReader.PROPERTY_UPC_A_ENABLE, true );
                properties.put( BarcodeReader.PROPERTY_EAN_13_ENABLED, false );
                properties.put( BarcodeReader.PROPERTY_AZTEC_ENABLED, false );
                properties.put( BarcodeReader.PROPERTY_CODABAR_ENABLED, false );
                properties.put( BarcodeReader.PROPERTY_INTERLEAVED_25_ENABLED, false );
                properties.put( BarcodeReader.PROPERTY_PDF_417_ENABLED, false );
                properties.put( BarcodeReader.PROPERTY_CODE_39_MAXIMUM_LENGTH, 10 );
                properties.put( BarcodeReader.PROPERTY_CENTER_DECODE, true );
                properties.put( BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED, false );

                reader.setProperties( properties );
                reader.addBarcodeListener( RNHoneywellBarcodeScannerModule.this );

            }
        } );
    }

    @Override
    public String getName() {
    	return "RNHoneywellBarcodeScanner";
    }

	@Override
    public void onBarcodeEvent( BarcodeReadEvent barcodeReadEvent ) {
        String barcodeData = barcodeReadEvent.getBarcodeData();

        this.reactContext.getJSModule( DeviceEventManagerModule.RCTDeviceEventEmitter.class )
            .emit( "io.ibsgroup.codeCaptured", barcodeData );
    }

    @Override
    public void onFailureEvent( BarcodeFailureEvent barcodeFailureEvent ) {
        this.reactContext.getJSModule( DeviceEventManagerModule.RCTDeviceEventEmitter.class )
                .emit( "io.ibsgroup.codeCaptured", "Error" );
    }

    @Override
    public void onTriggerEvent( TriggerStateChangeEvent triggerStateChangeEvent ) {

        try {
            if ( triggerStateChangeEvent.getState() ) {
                reader.aim( !triggerState );
                reader.light( !triggerState );
                reader.decode( !triggerState );
                triggerState = !triggerState;
            }
        } catch ( ScannerUnavailableException e ) {
            e.printStackTrace();
        } catch ( ScannerNotClaimedException e ) {
            e.printStackTrace();
        }

    }

}
