package com.example.fire_app;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private enum MENU_TYPE {STATUS, SETTING};


    private ImageView mbtn_connect, mbtn_status, mbtn_setting;

    private Handler mHandler;

    private NotificationManager _notificationManager;
    private Notification _noNotification;

    private final BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();

            if(Constants.INTENTFILTER_DATA.equals(action))
            {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mbtn_connect = (ImageView) findViewById(R.id.BTN_POPUP_CONNECT);
        mbtn_setting = (ImageView) findViewById(R.id.BTN_POPUP_SETTING);
        mbtn_status = (ImageView) findViewById(R.id.BTN_POPUP_STATUS);

        mHandler = new Handler();

        _notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        TCPClient.getInstance().setContext(this.getApplicationContext());

        mbtn_connect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                get_connect_intent();
            }
        });

        mbtn_status.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                open_popup(MENU_TYPE.STATUS);
            }
        });

        mbtn_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                open_popup(MENU_TYPE.SETTING);
            }
        });

    }

    @Override
    public synchronized void onStop() {
        super.onStop();
        unregisterReceiver(mBroadCastReceiver);
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        registerReceiver(mBroadCastReceiver, makeIntentFilter());
    }

    private static IntentFilter makeIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.INTENTFILTER_CONNECTION);
        intentFilter.addAction(Constants.INTENTFILTER_DATA);
        return intentFilter;
    }

    private void get_connect_intent()
    {
        Intent intent = new Intent(this, ConnectActivity.class);
        startActivityForResult(intent, 0);
    }

    private void open_popup(MENU_TYPE _type)
    {
        Intent intent;

        if(!TCPClient.getInstance().is_connected())
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    get_connect_intent();
                }
            });
            alert.setMessage("기능을 사용하기 전에 타겟보드를 연결하셔야합니다.");
            alert.show();
            return;
        }

        switch(_type)
        {
            case STATUS :
                intent = new Intent(this, StatusActivity.class);
                startActivityForResult(intent, 0);
                break;
            case SETTING :
                intent = new Intent(this, SettingActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }
}
