package com.mrbhati.vizitors.data;//package com.mswipetech.wisepos.demo.sdk.data;
//
//import static com.mswipetech.wisepad.sdk.data.InvoiceDwnldDataStore.CREATE_INVOICE_DOWLD_TABLE_COMMAND;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.mswipetech.sdk.network.util.Logs;
//import com.mswipetech.wisepad.sdk.data.ApplicationData;
//
//
//public class DataBaseHelper extends SQLiteOpenHelper
////{
//	public final static String log_tab = "DataBaseHelper=>";
//
//	Context context = null;
//	private static DataBaseHelper mDBConnection;
//	public static String Lock = "dblock";
//	public static final int db_version = 4;
//
//	private DataBaseHelper(Context context)
//	{
//		super(context, "data.db", null, db_version);
//		this.context = context;
//	}
///**/
//	public static synchronized DataBaseHelper getDBAdapterInstance(Context context)
//	{
//		synchronized(Lock)
//		{
//			if (mDBConnection == null) {
//				mDBConnection = new DataBaseHelper(context);
//	    }
//	    return mDBConnection;
//		}
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db)
//	{
//
//		db.execSQL("create table settings ( config varchar(1000));");
//		db.execSQL("create table cardhistory ( _id int,data varchar(2000));");
//		db.execSQL("create table batchhistory ( _id int,data varchar(2000),batch_no varchar(20));");
//		db.execSQL("create table signature (carddata varchar(2000),singature BLOB);");
//
//		//this we have to include if the app was completely removed and installed again
//		//then this would have to be created to.
//		//for the app update the above tables will be already there and wont call on Create
//		//and just call update and this tables would be added under version 2
//
//		//version 2 tables
//
//		db.execSQL("create table cashhistory ( _id int,data varchar(2000));");
//		db.execSQL("create table bankhistory ( _id int,data varchar(2000));");
//
//		//version 4 tables
//
//		db.execSQL("create table transactionshistory ( type varchar(20), data varchar(2000), trxdate DATETIME, order_type int(1));");
//		db.execSQL("create table lasttransaction( data varchar(2000));");
//		db.execSQL("create table multiusers( userId INTEGER PRIMARY KEY, data varchar(2000));");
//
//		db.execSQL("create table alerts(messageid varchar(20) NOT NULL UNIQUE, messagetype varchar(20), messagedata varchar(2000), isread int(1), messagedate DATETIME, userid varchar(10), messagesubtype varchar(20));");
//		db.execSQL("create table banners(bannerid varchar(20) NOT NULL UNIQUE, imageurl varchar(200), messagetext varchar(400), buttontype int(1), listnumbers varchar(400), listtype varchar(10), listtext varchar(400), validfrom DATETIME, validto DATETIME, userid varchar(10), messagesubtype varchar(20));");
//
//		db.execSQL("create table barcodeitemhistory (itemid varchar(50) PRIMARY KEY,itemname varchar(50),itemdes varchar(200),itemprice varchar(50));");
//		db.execSQL("create table xmpptrxnotificaitons( type varchar(10), identifier varchar(100));");
//
//		//version 5 tables
//
//		db.execSQL("ALTER TABLE banners ADD COLUMN bannertype varchar(20);");
//		db.execSQL("ALTER TABLE banners ADD COLUMN bannersubtype varchar(20);");
//		db.execSQL("ALTER TABLE banners ADD COLUMN bannersubsubtype varchar(20);");
//
//
//		//version 6 tables
//
//		db.execSQL("create table kyc_firm_list_master (_id varchar(50) PRIMARY KEY, type_of_firm varchar(50),document varchar(200),business_entity_id varchar(200),required varchar(50));");
//		db.execSQL("create table mcc_group_list (mcc varchar(50),merchant varchar(200),mcc_group varchar(200),mcc_subgroup varchar(50));");
//		db.execSQL("create table kyc_image_data (_id varchar(50) PRIMARY KEY,image_data varchar(200));");
//
//		db.execSQL("ALTER TABLE alerts ADD COLUMN trxtype varchar(20);");
//		db.execSQL("ALTER TABLE alerts ADD COLUMN cardno varchar(20);");
//		db.execSQL("ALTER TABLE alerts ADD COLUMN amount varchar(20);");
//		db.execSQL("ALTER TABLE alerts ADD COLUMN status varchar(20);");
//		db.execSQL("ALTER TABLE alerts ADD COLUMN sortdate varchar(20);");
//
//		db.execSQL("create table history_month_summary(TrxType varchar(20), MonthId varchar(20), MonthAlias varchar(20), " +
//				"TrxAmount varchar(20), TrxCount varchar(20));");
//
//		db.execSQL("create table history_day_summary(TrxType varchar(20), MonthId varchar(20), TrxDate DATETIME, " +
//				"TrxAmount varchar(20), TrxCount varchar(20));");
//
//		db.execSQL("create table card_history_details(trxtype varchar(20), trxdate DATETIME, data varchar(2000));");
//
//		db.execSQL("create table soa_month_summary ( MonthAlias varchar(20),  TrxAmt varchar(20), PaidTrxAmt varchar(20), " +
//				"PaidTrxCount varchar(20),  MonthId varchar(20),UNIQUE (MonthAlias));");
//
//		db.execSQL("create table soa_day_summary( TrxDate DATETIME, TrxAmt varchar(20), mdr varchar(20),SalesTax varchar(20), " +
//				"Rent varchar(20),Deduction varchar(20), Addition varchar(20)," +
//				"TrxPaidAmt varchar(20), TotalTrxCount varchar(20),PaidTrxCount varchar(20),HoldTrxCount varchar(20), " +
//				"MonthId varchar(20),UNIQUE (TrxDate));");
//
//		//version 7 tables
//
//		db.execSQL("DROP TABLE IF EXISTS soa_month_summary");
//		db.execSQL("DROP TABLE IF EXISTS soa_day_summary");
//
//		db.execSQL("create table soa_month_summary ( MonthAlias varchar(20),  TrxAmt varchar(20), PaidTrxAmt varchar(20), " +
//				"PaidTrxCount varchar(20),  MonthId varchar(20));");
//
//		db.execSQL("create table soa_day_summary( TrxDate DATETIME, TrxAmt varchar(20), mdr varchar(20),SalesTax varchar(20), " +
//				"Rent varchar(20),Deduction varchar(20), Addition varchar(20)," +
//				"TrxPaidAmt varchar(20), TotalTrxCount varchar(20),PaidTrxCount varchar(20),HoldTrxCount varchar(20), " +
//				"MonthId varchar(20), TaxType varchar(20), IGST varchar(20), SGST varchar(20), CGST varchar(20), dateId varchar(20),yearId varchar(20));");
//
//		//version 8 tables
//
//		db.execSQL("create table mbins ( bin_range_low varchar(20), bin_range_high varchar(20), crdbflag varchar(20), " +
//				"card_type varchar(20),description varchar(20), bank_name varchar(50), id varchar(10));");
//
//
//		db.execSQL("create table signup_application_details (" +
//				"user_id varchar(100) NOT NULL, " +
//				"app_no varchar(100) NOT NULL UNIQUE, " +
//				"entity varchar(100), " +
//				"first_name varchar(100) DEFAULT ' ', " +
//				"last_name varchar(100) DEFAULT ' ', " +
//				"mobile_no varchar(100) DEFAULT ' ', " +
//				"app_json_data varchar(5000), " +
//				"check_point integer DEFAULT 0, " +
//				"upload_request integer DEFAULT 1, " +
//				"is_refresh_reqired integer DEFAULT 1, "+
//				"is_rejected varchar(20));");
//
//		try {
//
//			insertBinNumbersData(db);
//		}
//		catch (Exception e){
//
//		}
//
//		db.execSQL("create table emitenure (" +
//				"tenure integer DEFAULT 0, " +
//				"bank_name varchar(100), " +
//				"bank_emi_code varchar(100), " +
//				"emi_rate varchar(100), " +
//				"payable_amt varchar(100), " +
//				"emi_amt varchar(100), " +
//				"cash_back_rate varchar(100), " +
//				"cash_back_amt varchar(100), " +
//				"item_file_name varchar(100), " +
//				"minAmount varchar(100), " +
//				"maxAmount varchar(100), " +
//				"extraone varchar(5000), " +
//				"extratwo varchar(5000), " +
//				"extrathree varchar(5000), " +
//				"extrafour varchar(5000), " +
//				"extrafive varchar(5000), " +
//				"extrasix varchar(5000)," +
//				"extraseven varchar(5000)," +
//				"extraeight varchar(5000)," +
//				"extranine varchar(5000)," +
//				"extraten varchar(5000))");
//
//		db.execSQL("create table brandemi_categories (" +
//			"offer_id varchar(100), " +
//			"item_code varchar(100), " +
//			"itcat_code varchar(100), " +
//			"itcat_desc varchar(100), " +
//			"itsubcat_code varchar(100), " +
//			"itsubcat_desc varchar(100), " +
//			"itsubsubcat_code varchar(100), " +
//			"itsubsubcat_desc varchar(100));");
//
//
//		db.execSQL("create table brand_products (" +
//				"item_Code varchar(100), " +
//				"item_Desc varchar(100), " +
//				"offer_ID varchar(100), " +
//				"offer_Title varchar(100), " +
//				"mrp varchar(100), " +
//				"discount varchar(100), " +
//				"item_Image varchar(500), " +
//				"cash_Back_MaxAmt varchar(100), " +
//				"item_Lot_Type varchar(100), " +
//				"offer_type varchar(100), " +
//				"item_OEM_Code varchar(100), " +
//				"id varchar(100), " +
//				"cust_Location_Code varchar(100), " +
//				"item_Purc_Price_min varchar(100), " +
//				"item_Purc_Price_max varchar(100), " +
//				"cust_State varchar(100), " +
//				"cust_City varchar(100), " +
//				"cust_PinCode varchar(100), " +
//				"mid varchar(100), " +
//				"extraone varchar(1000), " +
//				"extratwo varchar(1000), " +
//				"extrathree varchar(1000), " +
//				"extrafour varchar(1000), " +
//				"extrafive varchar(1000), " +
//				"extrasix varchar(1000), " +
//				"extraseven varchar(1000), " +
//				"extraeight varchar(1000), " +
//				"extranine varchar(1000), " +
//				"extraten varchar(1000));" );
//
//		db.execSQL("create table ticket_types (" +
//				"ticketCode varchar(100), " +
//				"ticketDesp varchar(100), " +
//				"ticketSubCode varchar(100), " +
//				"ticketSubDesp varchar(100), " +
//				"ticketSubSubCode varchar(100), " +
//				"ticketSubSubDesp varchar(100), " +
//				"erringUnit varchar(100), " +
//				"type varchar(100), " +
//				"extraone varchar(1000), " +
//				"extratwo varchar(1000), " +
//				"extrathree varchar(1000), " +
//				"extrafour varchar(1000), " +
//				"extrafive varchar(100));");
//
//		// change : 29-04-2021
//		// new Table for Invoice downloads added due to google policy change
//		db.execSQL(CREATE_INVOICE_DOWLD_TABLE_COMMAND);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
//	{
//		//if the database file deos not exists in the device this funcatin does not get called, and if so
//		//and on changing the version no to the original this will get called but what if when
//
//        if(com.mswipetech.wisepad.sdk.data.ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(context.getPackageName(), log_tab + "onUpgrade => Old Version " + oldVersion + " New Version " + newVersion,false, true);
//
//		if(newVersion == 2){
//
//			db.execSQL("DROP TABLE IF EXISTS emitenure");
//
//			db.execSQL("create table emitenure (" +
//					"tenure integer DEFAULT 0, " +
//					"bank_name varchar(100), " +
//					"bank_emi_code varchar(100), " +
//					"emi_rate varchar(100), " +
//					"payable_amt varchar(100), " +
//					"emi_amt varchar(100), " +
//					"cash_back_rate varchar(100), " +
//					"cash_back_amt varchar(100), " +
//					"item_file_name varchar(100), " +
//					"minAmount varchar(100), " +
//					"maxAmount varchar(100), " +
//					"extraone varchar(5000), " +
//					"extratwo varchar(5000), " +
//					"extrathree varchar(5000), " +
//					"extrafour varchar(5000), " +
//					"extrafive varchar(5000), " +
//					"extrasix varchar(5000));" );
//
//			db.execSQL("create table brand_products (" +
//					"item_Code varchar(100), " +
//					"item_Desc varchar(100), " +
//					"offer_ID varchar(100), " +
//					"offer_Title varchar(100), " +
//					"mrp varchar(100), " +
//					"discount varchar(100), " +
//					"item_Image varchar(500), " +
//					"cash_Back_MaxAmt varchar(100), " +
//					"item_Lot_Type varchar(100), " +
//					"offer_type varchar(100), " +
//					"item_OEM_Code varchar(100), " +
//					"id varchar(100), " +
//					"cust_Location_Code varchar(100), " +
//					"item_Purc_Price_min varchar(100), " +
//					"item_Purc_Price_max varchar(100), " +
//					"cust_State varchar(100), " +
//					"cust_City varchar(100), " +
//					"cust_PinCode varchar(100), " +
//					"mid varchar(100), " +
//					"extraone varchar(1000), " +
//					"extratwo varchar(1000), " +
//					"extrathree varchar(1000), " +
//					"extrafour varchar(1000), " +
//					"extrafive varchar(1000), " +
//					"extrasix varchar(1000), " +
//					"extraseven varchar(1000), " +
//					"extraeight varchar(1000), " +
//					"extranine varchar(1000), " +
//					"extraten varchar(1000));" );
//
//		}
//
//		if(oldVersion < 3){
//
//			db.execSQL("create table ticket_types (" +
//					"ticketCode varchar(100), " +
//					"ticketDesp varchar(100), " +
//					"ticketSubCode varchar(100), " +
//					"ticketSubDesp varchar(100), " +
//					"ticketSubSubCode varchar(100), " +
//					"ticketSubSubDesp varchar(100), " +
//					"erringUnit varchar(100), " +
//					"type varchar(100), " +
//					"extraone varchar(1000), " +
//					"extratwo varchar(1000), " +
//					"extrathree varchar(1000), " +
//					"extrafour varchar(1000), " +
//					"extrafive varchar(100));");
//		}
//
//		if (oldVersion < 4){
//			// change : 29-04-2021
//			// new Table for Invoice downloads added due to google policy change
//			db.execSQL(CREATE_INVOICE_DOWLD_TABLE_COMMAND);
//
//			if(ApplicationData.IS_DEBUGGING_ON)
//				Logs.v(context.getPackageName(), log_tab + "created new table for invoice downloads ",false, true);
//
//		}
//
//		if(newVersion == 4){
//
//			db.execSQL("DROP TABLE IF EXISTS emitenure");
//
//			db.execSQL("create table emitenure (" +
//					"tenure integer DEFAULT 0, " +
//					"bank_name varchar(100), " +
//					"bank_emi_code varchar(100), " +
//					"emi_rate varchar(100), " +
//					"payable_amt varchar(100), " +
//					"emi_amt varchar(100), " +
//					"cash_back_rate varchar(100), " +
//					"cash_back_amt varchar(100), " +
//					"item_file_name varchar(100), " +
//					"minAmount varchar(100), " +
//					"maxAmount varchar(100), " +
//					"extraone varchar(5000), " +
//					"extratwo varchar(5000), " +
//					"extrathree varchar(5000), " +
//					"extrafour varchar(5000), " +
//					"extrafive varchar(5000), " +
//					"extrasix varchar(5000)," +
//					"extraseven varchar(5000)," +
//					"extraeight varchar(5000)," +
//					"extranine varchar(5000)," +
//					"extraten varchar(5000));");
//
//		/*	db.execSQL("create table brand_products (" +
//					"item_Code varchar(100), " +
//					"item_Desc varchar(100), " +
//					"offer_ID varchar(100), " +
//					"offer_Title varchar(100), " +
//					"mrp varchar(100), " +
//					"discount varchar(100), " +
//					"item_Image varchar(500), " +
//					"cash_Back_MaxAmt varchar(100), " +
//					"item_Lot_Type varchar(100), " +
//					"offer_type varchar(100), " +
//					"item_OEM_Code varchar(100), " +
//					"id varchar(100), " +
//					"cust_Location_Code varchar(100), " +
//					"item_Purc_Price_min varchar(100), " +
//					"item_Purc_Price_max varchar(100), " +
//					"cust_State varchar(100), " +
//					"cust_City varchar(100), " +
//					"cust_PinCode varchar(100), " +
//					"mid varchar(100), " +
//					"extraone varchar(1000), " +
//					"extratwo varchar(1000), " +
//					"extrathree varchar(1000), " +
//					"extrafour varchar(1000), " +
//					"extrafive varchar(1000), " +
//					"extrasix varchar(1000), " +
//					"extraseven varchar(1000), " +
//					"extraeight varchar(1000), " +
//					"extranine varchar(1000), " +
//					"extraten varchar(1000));" );
//*/
//		}
//
//
//	}
//
//	private void insertBinNumbersData(SQLiteDatabase db)
//	{
//		try {
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(637513, 637513,'X','SODEXO','SODEXO','SODEXO',5)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(400000, 499999,'X','VISA','VISA','VISA',15)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(510000, 599999,'X','MASTERCARD','MASTERCARD','MASTERCARD',40)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(600000,699999,'X','MAESTRO','MAESTRO','MAESTRO',50)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(500000,509999,'X','MAESTRO','MAESTRO','MAESTRO',50)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(508500,508999,'X','RUPAY','RUPAY','RUPAY',20)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(340000,349999,'X','AMEX','AMEX','AMEX',30)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(370000,379999,'X','AMEX','AMEX','AMEX',30)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(606985,607984,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(608001,608200,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(608201,608300,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(608301,608350,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(608351,608500,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(652150,652849,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(652850,653049,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(653050,653149,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(352800,358999,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(300000,305999,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(309500,309599,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(360000,369999,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(380000,399999,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601100,601103,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601105,601109,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601120,601149,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601174,601174,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601177,601179,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(601186,601199,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(644000,652149,'X','RUPAY','RUPAY','RUPAY',20)");
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(653150,659999,'X','RUPAY','RUPAY','RUPAY',20)");
//
//			db.execSQL("INSERT INTO mbins (bin_range_low, bin_range_high, crdbflag, card_type, description,bank_name, id) VALUES(901001,901001,'X','MCARD','MCARD','MCARD',60)");
//		}
//		catch (Exception e){
//		}
//	}
//}