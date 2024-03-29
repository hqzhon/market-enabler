package com.androidiani.MarketEnabler.view;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidiani.MarketEnabler.R;
import com.androidiani.MarketEnabler.presenter.CustomPresenter;
import com.androidiani.MarketEnabler.presenter.ICustomView;

public class CustomView implements ICustomView {
	
	/** UI elements **/
	private TextView simNumeric, operatorNumeric, simISO, operatorISO,
			simAlpha, operatorAlpha;
	private Button setValues;

	private StartUpView startup;
	private CustomPresenter presenter;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// This Means that we've not finished the work ..
			Log.d("MarketEnabler", "progress msg["+ msg.arg1 + ", "+ msg.arg2 + "] getprogress["+presenter.getPd().getProgress()+"]");
			
			if (msg.arg2 != 0) {
				Log.d("MarketEnabler", "increment progress msg["+ msg.arg1 + ", "+ msg.arg2 + "] getprogress["+presenter.getPd().getProgress()+"]");
				//presenter.getPd().incrementProgressBy(msg.arg1 - pd.getProgress());
				presenter.getPd().setProgress(msg.arg1);
			} else {
				Log.d("MarketEnabler", "dismiss progress msg["+ msg.arg1 + ", "+ msg.arg2 + "] getprogress["+presenter.getPd().getProgress()+"]");
				//pd.setProgress(8);
				presenter.getPd().dismiss();
				//Toast.makeText(startup, "Done ;)", Toast.LENGTH_LONG).show();
				if (msg.arg1 == 0) 
					Toast.makeText(startup, " Done and all set", Toast.LENGTH_LONG)
							.show();
				else
					Toast.makeText(startup, "We Got a Problem Houston :(",
							Toast.LENGTH_LONG).show();
				startup.getTabHost().setCurrentTabByTag("actual");

			}

		}
	};
	
	public Handler getHandler() {
		return handler;
	}

	public CustomView(StartUpView startup) {
		this.startup = startup;
		
		Log.i("MarketEnabler", "Start getting UI elements");
		/** get UI elements **/
		simNumeric = (TextView) startup
				.findViewById(R.id.customsimNumericValue);
		operatorNumeric = (TextView) startup
				.findViewById(R.id.customoperatorNumericValue);
		simISO = (TextView) startup.findViewById(R.id.customsimISOValue);
		operatorISO = (TextView) startup
				.findViewById(R.id.customoperatorISOValue);
		simAlpha = (TextView) startup.findViewById(R.id.customsimAlphaValue);
		operatorAlpha = (TextView) startup
				.findViewById(R.id.customoperatorAlphaValue);
		
		/** setting actual values as default text **/
		simNumeric.setText(startup.getTelephonyManager().getSimOperator());
		operatorNumeric.setText(startup.getTelephonyManager()
				.getNetworkOperator());
		simISO.setText(startup.getTelephonyManager().getSimCountryIso());
		operatorISO.setText(startup.getTelephonyManager()
				.getNetworkCountryIso());
		operatorAlpha.setText(startup.getTelephonyManager()
				.getNetworkOperatorName());
		simAlpha.setText(startup.getTelephonyManager().getSimOperatorName());
		
		presenter = new CustomPresenter(this);
		
		
		setValues = (Button) startup.findViewById(R.id.customsetValues);
		setValues.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.i("MarketEnabler", "calling CustomPresenter.setValues()");
				presenter.setValues();
			}
		});
		 
	}

	public void displayError(String error) {
		// TODO Auto-generated method stub

	}

	public String getOperatorAlpha() {
		return operatorAlpha.getText().toString();
	}

	public String getOperatorISO() {
		return operatorISO.getText().toString();
	}

	public String getOperatorNumeric() {
		return operatorNumeric.getText().toString();
	}

	public String getSimAlpha() {
		return simAlpha.getText().toString();
	}

	public String getSimISO() {
		return simISO.getText().toString();
	}

	public String getSimNumeric() {
		return simNumeric.getText().toString();
	}

	public void displayResult(boolean result) {
		// TODO Auto-generated method stub

	}

	public StartUpView getStartup() {
		return startup;
	}

}
