package com.example.fire_app;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle; 
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusActivity extends Activity {

	private ImageView mimage_fire, mimage_smoke;
	private ImageView mimage_pump_functionality;
	private ImageView mimage_buzzer_functionality;

	private Handler mHandler;

	private boolean _is_wating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_status);

		mimage_fire = (ImageView) findViewById(R.id.ID_STATUS_SPARK);
		mimage_smoke = (ImageView) findViewById(R.id.ID_STATUS_SMOKE);

		mimage_pump_functionality = (ImageView)findViewById(R.id.ID_FIRE_PUMP);
		mimage_buzzer_functionality = (ImageView)findViewById(R.id.ID_FIRE_BUZZER);

		get_info_from_server();

		mHandler = new Handler();
		GetInfoThread _info_thread = new GetInfoThread(true);

		_info_thread.start();
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
		unregisterReceiver(mBroadCastReceiver);
	} 

	@Override
	public synchronized void onResume() {
		super.onResume(); 
		registerReceiver(mBroadCastReceiver, makeIntentFilter());
	}

	private void get_info_from_server()
	{
		if(!_is_wating)
		{
			_is_wating = true;
			TCPClient.getInstance().sendMessage(IoTUtility.mkcommand_get_status());
		}
	}

	private static IntentFilter makeIntentFilter() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Constants.INTENTFILTER_DATA);
		return intentFilter;
	}


	private final BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			final String action = intent.getAction();

			if(Constants.INTENTFILTER_DATA.equals(action))
			{
				Bundle bundle = intent.getExtras();

				if(bundle != null)
				{
					String[] _data = IoTUtility.get_status(bundle.getString(Constants.INTENTEXTRA_RECEIVED_DATA));
					if(_data != null) {


						if (Float.parseFloat(_data[0]) == 0) {
							mimage_fire.setImageResource(R.drawable.status_normal);
						} else {
							mimage_fire.setImageResource(R.drawable.status_unnormal);
						}
						if (Float.parseFloat(_data[1]) == 0)
						{
							mimage_smoke.setImageResource(R.drawable.status_normal);
						}
						else {
							mimage_smoke.setImageResource(R.drawable.status_unnormal);
						}

						if(Integer.parseInt(_data[0]) == 0)
							mimage_pump_functionality.setImageResource(R.drawable.status_normal);
						else
							mimage_pump_functionality.setImageResource(R.drawable.status_unnormal);

						if((Integer.parseInt(_data[0]) == 0) || (Integer.parseInt(_data[1]) == 0) )
							mimage_buzzer_functionality.setImageResource(R.drawable.status_normal);
						else
							mimage_buzzer_functionality.setImageResource(R.drawable.status_unnormal);

						_is_wating = false;
					}
				}

			}

		}
	};

	class GetInfoThread extends Thread {
		private boolean isPlay = false;

		public GetInfoThread(boolean isPlay){
			this.isPlay = isPlay;
		}

		public void stopThread(){
			isPlay = !isPlay;
		}
		@Override
		public void run() {
			super.run();
			while (isPlay) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						get_info_from_server();
					}
				});
			}
		}
	}
}
