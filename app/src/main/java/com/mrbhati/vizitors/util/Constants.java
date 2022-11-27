//package com.mrbhati.vizitors.util;
//
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.telephony.TelephonyManager;
//import android.util.DisplayMetrics;
//import android.util.Xml;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//
//import com.mrbhati.vizitors.data.ApplicationData;
//
//import org.xmlpull.v1.XmlPullParser;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//
//
//public class Constants
//{
//
//	public static enum CUSTOM_DLG_TYPE
//	{
//		CUSTOM_DLG_TYPE_SHOW_DLG_INFO,
//		CUSTOM_DLG_TYPE_RETURN_DLG_INFO,
//		CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION,
//	}
//
//
//
//    public static String  getCurrentDateWithFormate(String aDateFormat)
//    {
//        String formatedDate = "";
//
//        final Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat(aDateFormat);
//        formatedDate = df.format(c.getTime());
//
//        return formatedDate.toLowerCase();
//
//    }
//
//    public static String getIMEIId(Context context)  {
//
//        String strImei = null;
//        try {
//
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            strImei = tm.getDeviceId();
//
//            if(ApplicationData.IS_DEBUGGING_ON)
//                Logs.v(ApplicationData.packName, "The Imei id is  " + strImei, true, true);
//
//        } catch (Exception ex) {
//
//        }
//        if (strImei == null)
//            strImei = "";
//        else if (strImei.startsWith("0000000000000"))
//            strImei = "";
//
//
//        return strImei;
//
//    }
//
//    public static Dialog showWisepadConnectionDialog(Context context, String title) {
//        Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.customwisepadconnectiondlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(false);
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.customwisepadconnectiondlg_TXT_title);
//        txttitle.setText(title);
//        return dialog;
//
//    }
//
//	public static void showDialog(Context context, String title, String msg) {
//
//		Dialog dlg = showDialog(context, title, msg,
//				CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO, "Ok","Cancel");
//		dlg.show();
//    }
//
//    public static Dialog showDialog(Context context, String title, String msg, CUSTOM_DLG_TYPE customDlgType) {
//
//		Dialog dlg = showDialog(context, title, msg,
//				customDlgType, "Ok","Cancel");
//
//
//		return dlg;
//    }
//
//
//    public static Dialog showDialog(Context context, String title, String msg,
//    		CUSTOM_DLG_TYPE customDlgType, String firstBtnText, String secondBtnText)
//    {
//
//    	final Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.customdlg);
//        dialog.setCanceledOnTouchOutside(false);
//
//        dialog.setCancelable(true);
//
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.customdlg_TXT_title);
//        txttitle.setText(title);
//
//
//        // to set the message
//        TextView message = (TextView) dialog.findViewById(R.id.customdlg_TXT_Info);
//        message.setText(msg);
//
//        Button yes = (Button) dialog.findViewById(R.id.customdlg_BTN_yes);
//        yes.setText(firstBtnText);
//
//        Button no = (Button) dialog.findViewById(R.id.customdlg_BTN_no);
//        no.setText(secondBtnText);
//
//        if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO) {
//            no.setVisibility(View.GONE);
//            yes.setOnClickListener(new OnClickListener() {
//
//                public void onClick(View v) {
//                    dialog.dismiss();
//
//                }
//            });
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_INFO) {
//        	no.setVisibility(View.GONE);
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION) {
//
//        }
//
//        return dialog;
//
//    }
//
//
//    public static void showActivityDialog(Context context, String title, String msg) {
//
//        final Dialog dialog = showActivityDialog(context, title, msg, CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO,
//                context.getString(R.string.ok),
//                context.getString(R.string.cancel));
//
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey (DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK &&
//                        event.getAction() == KeyEvent.ACTION_UP &&
//                        !event.isCanceled()) {
//
//                    dialog.dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
//        dialog.show();
//    }
//
//
//    public static Dialog showActivityDialog(final Context context, String title, String msg,
//                                    CUSTOM_DLG_TYPE customDlgType, String firstBtnText, String secondBtnText)
//    {
//
//        final Dialog dialog = new Dialog(context, R.style.StyleCustomDlg_white);
//
//
//        dialog.setContentView(R.layout.activity_customdlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        // set the title
//        TextView txtTitle = (TextView) dialog.findViewById(R.id.customdlg_sdk_TXT_title);
//        txtTitle.setText(title);
//        txtTitle.setTypeface(applicationData.font);
//
//        // to set the message
//        TextView message = (TextView) dialog.findViewById(R.id.customdlg_sdk_TXT_Info);
//        message.setText(msg);
//        message.setTypeface(applicationData.font);
//
//        RelativeLayout yes = (RelativeLayout) dialog.findViewById(R.id.customdlg_sdk_RLT_yes);
//        final  RelativeLayout no = (RelativeLayout) dialog.findViewById(R.id.customdlg_sdk_RLT_no);
//        ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_yes)).setText(firstBtnText);
//        ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_no)).setText(secondBtnText);
//
//        if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.GONE);
//            ((View) dialog.findViewById(R.id.customdlg_sdk_view)).setVisibility(View.GONE);
//            ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_yes)).setText(firstBtnText);
//            yes.setOnClickListener(new OnClickListener() {
//
//                public void onClick(View v) {
//                    dialog.dismiss();
//
//                }
//            });
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_INFO) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.GONE);
//            ((View) dialog.findViewById(R.id.customdlg_sdk_view)).setVisibility(View.GONE);
//
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.VISIBLE);
//
//            no.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    dialog.dismiss();
//                }
//            });
//
//        }
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        dialog.show();
//        return dialog;
//
//    }
//
//
//    public static Dialog shwoAppCustionDialog(Context context, String title, String firstBtnText, String secondBtnText) {
//
//        Dialog dialog = new Dialog(context,R.style.styleCustDlg);
//       /* dialog.setContentView(R.layout.application_cust_dialog);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.application_customdlg_TXT_Info);
//        txttitle.setText(title);
//        txttitle.setTypeface(applicationData.fontBold);
//
//        Button firstBtn = (Button) dialog.findViewById(R.id.application_customdlg_BTN_yes);
//        firstBtn.setText(firstBtnText);
//        firstBtn.setTypeface(applicationData.font);
//
//        Button secondBtn = (Button) dialog.findViewById(R.id.application_customdlg_BTN_no);
//        secondBtn.setText(secondBtnText);
//        secondBtn.setTypeface(applicationData.font);
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/
//
//        return dialog;
//
//    }
//
//
//    public static Dialog showEmiOptionsDialog(Context context) {
//
//        Dialog dialog = new Dialog(context,R.style.styleCustDlg);
//        dialog.setContentView(R.layout.emi_options_dialog);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        return dialog;
//
//    }
//
//
//
//    public static Dialog showEmiTermsAndConditionDialog(Context context) {
//
//        Dialog dialog = new Dialog(context,R.style.styleCustDlg);
//        dialog.setContentView(R.layout.emi_dlg_termconditionview);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        return dialog;
//
//    }
//
//    public static Dialog showDialogPin(Context context, String title, String msg,
//                                        String firstBtnText, String secondBtnText, String thirdText) {
//         Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.custompindlg);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.custompindlg_TXT_title);
//        txttitle.setText(title);
//
//
//        // to set the message
//        TextView message = (TextView) dialog.findViewById(R.id.custompindlg_TXT_info);
//        message.setText(msg);
//
//        Button accept = (Button) dialog.findViewById(R.id.custompindlg_BTN_accept);
//        accept.setText(firstBtnText);
//
//
//        Button bypass = (Button) dialog.findViewById(R.id.custompindlg_BTN_bypass);
//        bypass.setText(secondBtnText);
//
//        Button no = (Button) dialog.findViewById(R.id.custompindlg_BTN_no);
//        no.setText(thirdText);
//        if (thirdText.length() == 0) {
//            no.setVisibility(View.GONE);
//        }
//        return dialog;
//
//    }
//
//    public static Dialog showAppCustomDialog(Context context, String title) {
//        Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.customappdlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.customapplicationdlg_TXT_title);
//        txttitle.setText(title);
//
//        return dialog;
//
//    }
//
//    /* *
//     * this method will take credit card number as input and will return the type of card.
//     * @param creditCardNumber card number
//     * @return CARDTYPE*/
//
//    public static CreditSaleEnumClass.CARDTYPE checkCardType(String creditCardNumber, String creditFirstSixDigits)
//    {
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName,  "creditCardNumber " + creditCardNumber + " creditFirstSixDigits " +creditFirstSixDigits, true, true);
//
//        String cardNumber = replaceCreditCardAlphaToNumeric(creditCardNumber);
//        String cardFirstSixDigits = replaceCreditCardAlphaToNumeric(creditFirstSixDigits);
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName,  "cardNumber " + cardNumber + " cardFirstSixDigits " + cardFirstSixDigits, true, true);
//
//        String visaRegex= "^4[0-9]{6,}$";
//        String mastroRegex = "^(5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390|6220)[0-9]{8,15}$";
//        String masterRegex = "^5[1-5][0-9]{5,}$";
//        String expressRegex = "^3[47][0-9]{5,}$";
//        String dinerRegex = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
//        String discoverRegex = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
//        String jcbRegex = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
//
//        try
//        {
//            if(Integer.parseInt(creditFirstSixDigits) >= 222100 && Integer.parseInt(creditFirstSixDigits) <= 272099){
//
//                return CreditSaleEnumClass.CARDTYPE.MASTER;
//            }
//        }
//        catch (Exception e){
//
//        }
//
//        if(cardNumber.startsWith("11") || cardNumber.startsWith("22")
//                || cardNumber.startsWith("36")|| cardNumber.startsWith("50")
//                || cardNumber.startsWith("60")|| cardNumber.startsWith("64")
//                || cardNumber.startsWith("65")){
//            return CreditSaleEnumClass.CARDTYPE.RUPAY;
//        } else if(cardNumber.matches(visaRegex)){
//            return CreditSaleEnumClass.CARDTYPE.VISA;
//        }else if(cardNumber.matches(masterRegex)){
//            return CreditSaleEnumClass.CARDTYPE.MASTER;
//        }else if(cardNumber.matches(expressRegex)){
//            return CreditSaleEnumClass.CARDTYPE.EXPRESS;
//        }else if(cardNumber.matches(dinerRegex)){
//            return CreditSaleEnumClass.CARDTYPE.DINERS;
//        }else if(cardNumber.matches(discoverRegex)){
//            return CreditSaleEnumClass.CARDTYPE.DISCOVER;
//        }else if(cardNumber.matches(jcbRegex)){
//            return CreditSaleEnumClass.CARDTYPE.JCB;
//        }else if(cardNumber.matches(mastroRegex)){
//            return CreditSaleEnumClass.CARDTYPE.MAESTRO;
//        }else{
//            return CreditSaleEnumClass.CARDTYPE.UNKNOWN;
//        }
//    }
//
//
//    private static String replaceCreditCardAlphaToNumeric(String aCardNo){
//
//        String cardNo = aCardNo;
//
//        try{
//
//            cardNo = cardNo.replace("X", "0");
//            cardNo = cardNo.replace("F", "0");
//            cardNo = cardNo.replace("x", "0");
//            cardNo = cardNo.replace("f", "0");
//
//        }catch(Exception e){
//
//            cardNo = aCardNo;
//        }
//
//        return cardNo;
//    }
//
//    public static Dialog showProgDialog(Context context, String msg)
//    {
//        // new AlertDialogMsg(LoginView.this,
//        // "Please enter valid User Id and Password", "Login").show();
//        Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.progressdlg);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.tvmessagedialogtitle);
//        txttitle.setText(msg);
//
//        return dialog;
//
//    }
//    public static int dpToPx(int dp, Context aContext) {
//        DisplayMetrics displayMetrics = aContext.getResources().getDisplayMetrics();
//        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
//        return px;
//    }
//
//
//
//  /*  public static Bitmap encodeAsBitmap(String str, Context aContext) throws WriterException {
//
//        int WHITE = 0xFFFFFFFF;
//        int BLACK = 0xFF000000;
//        int WIDTH = 400;
//        int HEIGHT = 400;
//
//        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
//        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // H = 30% damage
//        hintMap.put(EncodeHintType.MARGIN, 0);
//
//        int dppixelswidth =  dpToPx(WIDTH, aContext);
//        int dppixelsheigth = dpToPx(HEIGHT, aContext);
//
//
//        BitMatrix result;
//        try {
//            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, dppixelswidth, dppixelsheigth, hintMap);
//        } catch (IllegalArgumentException iae) {
//            // Unsupported format
//            return null;
//        }
//
//        int width = result.getWidth();
//        int height = result.getHeight();
//        int[] pixels = new int[width * height];
//        for (int y = 0; y < height; y++) {
//            int offset = y * width;
//            for (int x = 0; x < width; x++) {
//                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//            }
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        return bitmap;
//    }
//*/
//
//    public final static boolean isValidEmail(CharSequence target)
//    {
//        if (target == null) {
//            return false;
//        } else {
//            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
//        }
//    }
//
//    public static void parseXml(String xmlString,String[][] strTags ) throws Exception {
//
//        XmlPullParser parser = Xml.newPullParser();
//
//        try {
//            parser.setInput(new StringReader(xmlString));
//            int eventType = XmlPullParser.START_TAG;
//            boolean leave = false;
//
//            while (!leave && eventType != XmlPullParser.END_DOCUMENT) {
//                eventType = parser.next();
//                switch (eventType) {
//                    case XmlPullParser.START_TAG:
//                        String xmlTag = parser.getName().toString();
//                        for (int iTagIndex = 0; iTagIndex < strTags.length; iTagIndex++) {
//                            if (strTags[iTagIndex][0].equals(xmlTag)) {
//                                eventType = parser.next();
//                                if (eventType == XmlPullParser.TEXT) {
//                                    String xmlText = parser.getText();
//                                    //Log.v(ApplicationData.packName, "Constatnts " + xmlTag + " message " + xmlText);
//
//                                    strTags[iTagIndex][1] = ( xmlText == null ? "" : xmlText);// store the key
//                                } else if (eventType == XmlPullParser.END_TAG) {
//                                    //Log.v(ApplicationData.packName, "Constatnts " + xmlTag + " message is blank");
//                                    strTags[iTagIndex][1]  =  "";
//                                }
//                                break;
//                            }
//                        }
//                        break;
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw e;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            if (parser != null) {
//                parser = null;
//            }
//        }
//
//    }
//
//
//    /**
//     *
//     * getDateWithFormate
//     * returns the formated date from the required formate
//     *
//     * @param aDate
//     * current format date
//     *
//     * @param aParseFormat
//     * current date formate which we need to convert to system date
//     *
//     * @param aDisplayFormat
//     * required date fomate to display
//     *
//     * @return
//     * return the formated dare
//     *
//     */
//
//    public static String getAmountWithComma(String amount){
//
//        String stAmt = amount;
//        String stOrg = removeChar(stAmt, '.');
//        stOrg = removeChar(stOrg, ',');
//        stOrg = removeChar(stOrg, '-');
//
//        if(stOrg.indexOf("0") == 0)
//        {
//            if(stOrg.equals("0"))
//                stOrg = stOrg.substring(0);
//            else if(stOrg.equals("00000"))
//                stOrg = stOrg.substring(2);
//            else
//                stOrg = stOrg.substring(1);
//        }
//
//        int ilen = stOrg.length();
//
//        if (ilen == 2 || ilen ==1)
//        {
//            stOrg = "0." + stOrg;
//        }
//        else if (ilen > 2)
//        {
//            stOrg = stOrg.substring(0, ilen - 2) + "." + stOrg.substring(ilen - 2, ilen);
//        }
//
//        ilen = stOrg.length();
//        if (ilen > 6)
//        {
//            stOrg = stOrg.substring(0, ilen - 6)+","+ stOrg.substring(ilen - 6, ilen);
//        }
//
//        ilen = stOrg.length();
//        if (ilen > 9)
//        {
//            stOrg = stOrg.substring(0, ilen - 9)+","+ stOrg.substring(ilen - 9, ilen);
//        }
//
//        if (!stOrg.equals(stAmt))
//        {
//            return stOrg;
//        }
//
//        return stOrg;
//    }
//
//    public static String removeChar(String s, char c) {
//
//        String r = "";
//
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != c) r += s.charAt(i);
//        }
//
//        return r;
//    }
//
//
//
//    public static String  getDateWithFormate(String aDate, String aParseFormat, String aDisplayFormat)
//    {
//        String formatedDate = "";
//
//        try {
//
//            SimpleDateFormat displayFormat = new SimpleDateFormat(aDisplayFormat);
//            SimpleDateFormat parseFormat = new SimpleDateFormat(aParseFormat);
//            Date date = parseFormat.parse(aDate);
//            formatedDate = displayFormat.format(date);
//
//        } catch (final Exception e) {
//            formatedDate = aDate;
//            e.printStackTrace();
//
//        }
//
//        return formatedDate.toLowerCase();
//
//    }
//
//
//    public static ArrayList<String> splitStringByTwoChars(String strText, char delimiter) {
//        ArrayList<String> extractStrings = new ArrayList<String>();
//        try {
//            StringBuffer extractString = new StringBuffer();
//            for (int iCtr = 0; iCtr < strText.length(); iCtr++) {
//                char ch = strText.charAt(iCtr);
//                if (ch == delimiter && iCtr + 1 < strText.length() && strText.charAt(iCtr + 1) == delimiter) {
//                    iCtr++;
//                    extractStrings.add(extractString.toString());
//                    extractString.delete(0, extractString.length());
//                } else {
//                    extractString.append(ch);
//                }
//
//            }
//            extractStrings.add(extractString.toString());
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//        }
//        return extractStrings;
//    }
//
//    // change password and Login
//    public static final String PWD_DIALOG_MSG = "Change Password";
//    public static final String PWD_ERROR_INVALIDPWD = "Invalid Password! Cannot be blank.";
//    public static final String PWD_ERROR_INVALIDPWDLENGTH = "Minimum length of the password should be 6 characters.";
//    public static final String PWD_ERROR_INVALIDPWDMAXLENGTH = "Length of the password should be between 6 and 10 characters.";
//
//    public static final String PWD_ERROR_INVALIDPWDNEWLENGTH = "Minimum length of the new password should be 6 characters.";
//    public static final String PWD_ERROR_INVALIDPWDMAXNEWLENGTH = "Length of the new password should be between 6 and 10 characters.";
//    public static final String PWD_ERROR_INVALIDPWDRETYPELENGTH = "Minimum length of the re-entered password should be 6 characters.";
//    public static final String PWD_ERROR_INVALIDNEWANDRETYPE = "New passwords do not match.";
//    public static final String PWD_CHANGEPWD_CONFIRMATION = "Would you like to change the password?";
//    public static final String PWD_ERROR_PROCESSIING_DATA = "Error in processing your change password request.";
// // cash sale
//    public static final String CASHSALE_DIALOG_MSG = "Cash Sale";
//    // bank sale
//    public static final String BANKSALE_DIALOG_MSG = "Bank Sale";
//    public static final String CASHSALE_ERROR_invalidChequeNO = "Required length of the Cheque no. is 6 digits";
//    public static final String BANKSALE_ALERT_AMOUNTMSG = "The total  bank sale amount is %s ";
//
//    // card sale
//    public static final String CARDSALE_DIALOG_MSG = "Card Sale";
//    public static final String CARDSALE_ALERT_AMOUNTMSG = "The total card sale amount is %s ";
//    public static final String CARDSALE_ERROR_INVALIDAMT = "Invalid amount! Minimum amount should be " + AppSharedPrefrences.getAppSharedPrefrencesInstace().getCurrencyCode() + " 1.00 to proceed.";
//    public static final String CARDSALE_ERROR_INVALIDCARDDIGITS = "Invalid last 4 digits.";
//    public static final String CARDSALE_ERROR_salecash_max= "Invalid Sale cash amount!  Amount should be between " + AppSharedPrefrences.getAppSharedPrefrencesInstace().getCurrencyCode() + " 1.00 to 1000.00 to proceed.";
//    public static final String CARDSALE_ERROR_salecash= "amount or sale cash is mandatory for this transaction";
//
//
//    public static final String CASHSALE_ALERT_AMOUNTMSG = "The total cash sale amount is %s ";
//
//    public static final String CARDSALE_ALERT_swiperAMOUNTMSG = "Total amount of this transaction \nis %s ";
//    public static final String CARDSALE_ERROR_mobilenolen = "Required length of the mobile number is %d digits.";
//    public static final String CARDSALE_ERROR_mobileformat = "The mobile number cannot start with 0.";
//    public static final String CARDSALE_ERROR_receiptvalidation = "Invoice No. can not be blank.";
//    public static final String CARDSALE_ERROR_receiptmandatory = "Receipt mandatory for this transaction, please un check the field to proceed";
//
//
//    public static final String CARDSALE_ERROR_email = "Invalid e-mail address.";
//    public static final String CARDSALE_ERROR_LstFourDigitsNotMatched = "Last 4 digits don't match, bad card.";
//    public static final String CARDSALE_ERROR_PROCESSIING_DATA = "Error in processing Card Sale.";
//
//    public static final String CARDSALE_AMEX_Validation = "Invalid Amex card security code.";
//
//    public static final String CARDSALE_Sign_Validation = "Receipt needs to be signed to proceed.";
//    public static final String CARDSALE_Sign_ERROR_PROCESSIING_DATA = "Error in uploading the receipt to " + ApplicationData.SERVER_NAME + " server.";
//    public static final String CARDSALE_Sign_SUCCESS_Msg = "Receipt successfully uploaded to " + ApplicationData.SERVER_NAME + " server.";
//
//    public static final String CARDSALE_AUTO_REVERSAL = "Auto Reversal successfull.";
//    public static final String CARDSALE_ERROR_FO35 = "Error in processing Card Sale.";
//
//
//    public static final String CARDSALE_Device_Connect_Msg = "WisePad not connected, please make sure the WisePad is switched on.";
//    public static final String CARDSALE_Device_Connecting_Msg = "Connecting to  Wisepad, if its taking longer then usual, please restart the WisePad and try re-connecting.";
//    // for Card history
//    public static final String CARDHISTORYLIST_DIALOG_MSG = "History";
//    public static final String CARDHISTORYLIST_GET_HISTROYDATA = "Fetch the latest sale history?";
//    public static final String CARDHISTORYLIST_ERROR_PROCESSIING_DATA = "Error in processing sale history, please try again.";
//    public static final String CARDHISTORYLIST_ERROR_NODATA_FOUND = "No sale history found on the " + ApplicationData.SERVER_NAME + " server.";
//
//    // last transaction
//    public static final String LSTTRXST_DIALOG_MSG = "Last Tx Status";
//    public static final String LSTTRXST_ERROR_FETCHING_DATA = "Error in fetching last tx details.";
//    public static final String LSTTRXST_ERROR_Processing_DATA = "Error in processing the last transaction request.";
//    public static final String LSTTRXST_Success_msg = "The receipt was successfully resent.";
//    public static final String LSTTRXST_NODATAFOUND = "No  transaction found on " + ApplicationData.SERVER_NAME + " server.";
//    // Login
//    public static final String LOGIN_DIALOG_MSG = "Login";
//    public static final String LOGIN_ERROR_ValidUserMsg = "Please enter a valid User Id and Password.";
//    public static final String LOGIN_ERROR_Processing_DATA = "Unable to login, please try again.";
//
//    // void
//    public static final String VOID_DIALOG_MSG = "Void Sale";
//    public static final String VOID_ERROR_Processing_DATA = "Error in processing the void sale request.";
//    public static final String VOID_ERROR_Processing_FLAG = "Error in updating the void sale flag.";
//    public static final String VOID_ALERT_AMOUNTMSG = "Proceed to void card sale of %s %s for the card ending with last 4 digits %s?";
//    public static final String VOID_ALERT_FORVOID = "Would you like to VOID the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";
//
//    public static final String VOID_NODATAFOUND = "No matching transaction found on " + ApplicationData.SERVER_NAME + " server.";
//  //Pre auth completion
//    public static final String PREAUTHVOID_DIALOG_MSG = "PreAuth Completion";
//    public static final String PREAUTHVOID_ERROR_Processing_DATA = "Error in processing the PreAuth sale request.";
//    public static final String PREAUTHVOID_ERROR_Processing_FLAG = "Error in updating the PreAuth sale flag.";
//    public static final String PREAUTHVOID_ALERT_AMOUNTMSG = "Proceed to Pre auth compeltion  of %s %s for the card ending with last 4 digits %s?";
//   //
//    public static final String PREAUTHVOID_ALERT_FORVOID = "Would you like to use Preauth Completion for the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";
//    public static final String PREAUTH_ERROR_NODATA_FOUND = "No PreAuth sale data found on the " + ApplicationData.SERVER_NAME + " server.";
//    public static final String PREAUTH_ERROR_INVALIDAMT = "Invalid amount ! PreAuth completion amount cannot exceed the PreAuth sale amount.";
//
//
////Refund trx
//      public static final String REFUNDVOID_DIALOG_MSG = "Refund";
//      public static final String REFUNDVOID_ERROR_Processing_DATA = "Error in processing the refund sale request.";
//      public static final String REFUNDVOID_ERROR_Processing_FLAG = "Error in updating the refund sale flag.";
//      public static final String REFUNDVOID_ALERT_AMOUNTMSG = "Proceed to Refund Trx  of %s %s for the card ending with last 4 digits %s?";
//      public static final String REFUNDVOID_ALERT_FORVOID = "Would you like to Refund the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";
//
//      public static final String DEVICEINFO_DIALOG_MSG = "Device Info";
//
//      //Error
//      public static final String  MSWIPE_ERROR_INENRYPTINGKEY ="Data encryption error.";
//}
