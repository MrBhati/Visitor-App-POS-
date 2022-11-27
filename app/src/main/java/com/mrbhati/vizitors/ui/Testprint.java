package com.mrbhati.vizitors.ui;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.data.ApplicationData;
import com.mrbhati.vizitors.util.Logs;
import com.mrbhati.vizitors.util.ReceiptBitmap;
import com.mswipetech.wisepad.sdk.Print;
import com.mswipetech.wisepad.sdk.device.MSWisepadDeviceController;
import com.mswipetech.wisepad.sdk.device.MSWisepadDeviceControllerResponseListener;
import com.mswipetech.wisepad.sdk.device.WisePadConnection;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;


public class Testprint extends Activity {


    public MSWisepadDeviceController mMSWisepadDeviceController = null;


    private Button mBTNPrint = null;
    private Button mBTNConnect = null;
    private TextView mTXTStatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_neodemo);

        mBTNPrint = (Button) findViewById(R.id.printdemo_BTN_print);
        mBTNConnect = (Button) findViewById(R.id.printdemo_BTN_connect);
        mTXTStatus = (TextView) findViewById(R.id.printdemo_TXT_status);

        mBTNConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(mMSWisepadDeviceController.getWisepadConnectionState() != WisePadConnection.WisePadConnection_CONNECTED ||
                        mMSWisepadDeviceController.getWisepadConnectionState() != WisePadConnection.WisePadConnection_CONNECTING)
                {
                    mMSWisepadDeviceController.connect();
                }
            }
        });

        mBTNPrint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(mMSWisepadDeviceController.getWisepadConnectionState() == WisePadConnection.WisePadConnection_CONNECTED)
                {
                    mTXTStatus.setText("printing");

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    runner.execute("");

                }
                else{
                    mTXTStatus.setText("device not connected");
                }
            }
        });

        mTXTStatus.setText("device not connected");


    }

    private ArrayList<byte[]> mPrintData;

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            if (ApplicationData.IS_DEBUGGING_ON)
                Logs.v(getPackageName(), "  ", true, true);
            mPrintData = new ArrayList<>();
            byte[] receiptData = getReceipt();
            mPrintData.add(receiptData);

            return "";
        }


        @Override
        protected void onPostExecute(String result) {

            if(mPrintData != null && mPrintData.size() > 0)
            {
                mMSWisepadDeviceController.print(mPrintData);
            }
            else{
                mTXTStatus.setText("unable to print");
            }
        }

        @Override
        protected void onPreExecute() { }

        @Override
        protected void onProgressUpdate(String... text) { }
    }

    public byte[] getReceipt()
    {


        if(ApplicationData.IS_DEBUGGING_ON)
            Logs.v(ApplicationData.packName,"getReceipt",true,true);


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.usericon);


        Typeface tf = Typeface.SERIF;


        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ReceiptBitmap receiptBitmap = new ReceiptBitmap().getInstance();

            receiptBitmap.init(600);

            receiptBitmap.setTextSize(20);
            receiptBitmap.setTypeface(Typeface.create(tf, Typeface.BOLD));

            receiptBitmap.drawImage(icon);
            receiptBitmap.drawLeftAndRightText("left","right");
            receiptBitmap.drawLeftText("sample left");
            receiptBitmap.drawLeftText("sample left");
            receiptBitmap.drawRightText("sample right");
            receiptBitmap.drawRightText("sample right");
            receiptBitmap.drawLeftAndRightText("left","right");
            receiptBitmap.drawCenterText("sample center");
            receiptBitmap.drawLeftText("sample left");
            receiptBitmap.drawLeftText("sample left");
            receiptBitmap.drawRightText("sample right");
            receiptBitmap.drawRightText("sample right");
            receiptBitmap.drawLeftAndRightText("left","right");
            receiptBitmap.drawCenterText("sample center");

            Bitmap canvasbitmap = receiptBitmap.getReceiptBitmap();

            if(ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,"getHeight: " + canvasbitmap.getHeight(),true,true);

            if(ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,"getReceiptHeight: " + receiptBitmap.getReceiptHeight(),true,true);

            Bitmap croppedBmp = Bitmap.createBitmap(canvasbitmap, 0, 0, canvasbitmap.getWidth(), canvasbitmap.getHeight());

            byte[] imageCommand = mMSWisepadDeviceController.getPrintData(croppedBmp, 150);

            baos.write(imageCommand, 0, imageCommand.length);

            if(ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,"end of reciept",true,true);

            return baos.toByteArray();


        } catch (Exception e) {
            e.printStackTrace();
            Logs.e(ApplicationData.packName, e, true, true);
        }

        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        doBindMswipeWisepadDeviceService();
    }


    @Override
    protected void onStop() {
        super.onStop();
        doUnbindMswipeWisepadDeviceService();
    }


    void doBindMswipeWisepadDeviceService()
    {
        bindService(new Intent(this, MSWisepadDeviceController.class), mMSWisepadDeviceControllerService, Context.BIND_AUTO_CREATE);
    }


    public void doUnbindMswipeWisepadDeviceService()
    {

        if (ApplicationData.IS_DEBUGGING_ON)
            Logs.v(ApplicationData.packName,  "wisePadConnection ", true, true);

        unbindService(mMSWisepadDeviceControllerService);

    }


    private ServiceConnection mMSWisepadDeviceControllerService = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            try
            {
                MSWisepadDeviceController.LocalBinder localBinder = (MSWisepadDeviceController.LocalBinder) service;
                mMSWisepadDeviceController = localBinder.getService();

                if(mMSWisepadDeviceController != null) {

                    mMSWisepadDeviceController.initMswipeWisepadDeviceController(new MSWisepadDeviceObserver(),
                            true, false, false,
                            false, null);

                }
            }
            catch (Exception e) {

                if (ApplicationData.IS_DEBUGGING_ON)
                    Logs.v(ApplicationData.packName, "exception."+e.toString(), true, true);
            }
        }

        public void onServiceDisconnected(ComponentName className)
        {
            if (ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName, "Wisepad servcie un-binded and wisepad is disconnected...", true, true);
            /**
             * This is called when the connection with the service has been
             * unexpectedly disconnected - process crashed.
             *
             */

            mMSWisepadDeviceController = null;

        }
    };


    class MSWisepadDeviceObserver implements MSWisepadDeviceControllerResponseListener
    {

        @Override
        public void onReturnWisepadConnection(WisePadConnection wisePadConntection, BluetoothDevice bluetoothDevice) {

            if (ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,  "wisePadConntection " + wisePadConntection, true, true);

            String msg = getString(R.string.unknown);

            if(wisePadConntection == WisePadConnection.WisePadConnection_CONNECTED)
            {
                msg = getString(R.string.device_connected);
            }
            else if(wisePadConntection == WisePadConnection.WisePadConnection_CONNECTING){

                msg = getString(R.string.device_connecting);
            }
            else if(wisePadConntection == WisePadConnection.WisePadConnection_NOT_CONNECTED){

                msg = getString(R.string.device_not_connected);
                // mMSWisepadDeviceController.connect();
            }
            else if(wisePadConntection == WisePadConnection.WisePadConnection_DIS_CONNECTED){

                msg = getString(R.string.device_disconnected);
            }

            mTXTStatus.setText(msg);

        }

        @Override
        public void onRequestWisePadCheckCardProcess(CheckCardProcess checkCardProcess, ArrayList<String> dataList) {

        }

        @Override
        public void onReturnWisePadOfflineCardTransactionResults(CheckCardProcessResults checkCardResults, Hashtable<String, Object> paramHashtable) {

        }

        @Override
        public void onError(Error error, String errorMsg) {

            if (ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,  "error " + error, true, true);

        }

        @Override
        public void onRequestDisplayWispadStatusInfo(DisplayText msg) {

        }

        @Override
        public void onReturnDeviceInfo(Hashtable<String, String> paramHashtable) {

        }

        @Override
        public void onReturnFunctionKeyResult(FunctionKeyResult keyType) {

        }

        @Override
        public void onReturnWispadNetwrokSettingInfo(WispadNetwrokSetting wispadNetwrokSetting, boolean status, Hashtable<String, Object> netwrokSettingInfo) {

        }

        @Override
        public void onReturnNfcDetectCardResult(NfcDetectCardResult nfcDetectCardResult, Hashtable<String, Object> hashtable) {

        }

        @Override
        public void onReturnNfcDataExchangeResult(boolean isSuccess, Hashtable<String, String> data) {

        }

        @Override
        public void onPrintResult(PrintResult printResult) {


            if (ApplicationData.IS_DEBUGGING_ON)
                Logs.v(ApplicationData.packName,  "printResult " + printResult, true, true);

            String msg = getString(R.string.unknown);

            if(printResult == PrintResult.PRINT_SUCCESS)
            {
                msg = getString(R.string.printer_command_success);

            }
            else if(printResult == PrintResult.PRINT_NO_PAPER_OR_COVER_OPENED)
            {
                msg = getString(R.string.no_paper);

            }
            else if(printResult == PrintResult.PRINT_WRONG_PRINTER_COMMAND)
            {
                msg = getString(R.string.printer_command_not_available);

            }
            else if(printResult == PrintResult.PRINT_OVERHEAT)
            {
                msg = getString(R.string.printer_overheat);

            }
            else if(printResult == PrintResult.PRINT_PRINTER_ERROR)
            {
                msg = getString(R.string.printer_command_not_available);

            }
            else if(printResult == PrintResult.PRINT_DATA_END)
            {
                msg = "print data end";

            }

            mTXTStatus.setText(msg);


        }
    }

}