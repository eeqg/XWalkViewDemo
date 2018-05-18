package com.example.az.xwalkviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkWebResourceRequest;
import org.xwalk.core.XWalkWebResourceResponse;

public class MainActivity extends AppCompatActivity {
	XWalkView xWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initXWalk();
		observeContent();
	}
	
	private void initXWalk() {
		//添加对javascript支持
		XWalkPreferences.setValue("enable-javascript", true);
		
		//开启调式,支持谷歌浏览器调式
		XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
		
		//置是否允许通过file url加载的Javascript可以访问其他的源,包括其他的文件和http,https等其他的源
		// XWalkPreferences.setValue(XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE, true);
		
		//JAVASCRIPT_CAN_OPEN_WINDOW
		XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);
		
		// enable multiple windows.
		XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, true);
	}
	
	private void observeContent() {
		this.xWebView = (XWalkView) findViewById(R.id.xWalkView);
		
		XWalkSettings settings = xWebView.getSettings();
		// settings.setJavaScriptEnabled(true);
		
		xWebView.setUIClient(new XWalkUIClient(xWebView) {
			@Override
			public void onPageLoadStarted(XWalkView view, String url) {
				super.onPageLoadStarted(view, url);
			}
			
			@Override
			public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
				super.onPageLoadStopped(view, url, status);
			}
		});
		
		xWebView.setResourceClient(new XWalkResourceClient(xWebView) {
			@Override
			public void onLoadStarted(XWalkView view, String url) {
				super.onLoadStarted(view, url);
			}
			
			@Override
			public void onLoadFinished(XWalkView view, String url) {
				super.onLoadFinished(view, url);
			}
			
			@Override
			public void onProgressChanged(XWalkView view, int progressInPercent) {
				super.onProgressChanged(view, progressInPercent);
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}
			
			@Override
			public XWalkWebResourceResponse shouldInterceptLoadRequest(XWalkView view, XWalkWebResourceRequest request) {
				return super.shouldInterceptLoadRequest(view, request);
			}
			
			@Override
			public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
				super.onReceivedLoadError(view, errorCode, description, failingUrl);
			}
		});
		
		
		//load.
		// xWebView.loadUrl("https://www.baidu.com/");
		xWebView.loadUrl("http://tckeno.kenobox.com/m/lotto-h5/t1/trad/games/14?token=f3482f00-6687-43ca-80ce-69ed017c47f4&merchant=hezonyl&css=CSS&prize_mode=Lott");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			if (xWebView.getNavigationHistory().canGoBack()) {
				xWebView.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1); //返回上一页面
			} else {
				finish();
			}
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		if (xWebView != null) {
			xWebView.onNewIntent(intent);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (xWebView != null) {
			xWebView.resumeTimers();
			xWebView.onShow();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if (xWebView != null) {
			xWebView.pauseTimers();
			xWebView.onHide();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (xWebView != null) {
			xWebView.onDestroy();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
