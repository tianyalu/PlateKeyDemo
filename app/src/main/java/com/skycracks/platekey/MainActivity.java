package com.skycracks.platekey;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.sql.SQLOutput;

public class MainActivity extends Activity implements View.OnClickListener{
	
	private Activity mActivity;
	private EditText mEdit;
	private RelativeLayout relativeLayout;
	private KeyboardUtil keyboardUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActivity = this;
		mEdit = (EditText) findViewById(R.id.id_keyboard);
		relativeLayout = (RelativeLayout) findViewById(R.id.traceroute_rootview);
		mEdit.setOnClickListener(this);
		relativeLayout.setOnClickListener(this);
		
		mEdit.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(keyboardUtil == null){
					keyboardUtil = new KeyboardUtil(mActivity, mEdit);
					keyboardUtil.hideSoftInputMethod();
					keyboardUtil.showKeyboard();
				}
				return false;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(keyboardUtil.isShow()){
				keyboardUtil.hideKeyboard();
			}else{
				finish();
			}
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.traceroute_rootview:
				if(keyboardUtil == null){

				}else if(keyboardUtil.isShow()){
					keyboardUtil.hideKeyboard();
				}
				break;
			case R.id.id_keyboard:
				if(keyboardUtil == null){
					keyboardUtil = new KeyboardUtil(mActivity, mEdit);
					keyboardUtil.hideSoftInputMethod();
					keyboardUtil.showKeyboard();
				}else if(!keyboardUtil.isShow()){
					keyboardUtil.showKeyboard();
				}
				break;
			default:
					break;
		}
	}
}
