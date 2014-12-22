package br.com.tairoroberto.testechronometer;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends ActionBarActivity {
	private Chronometer cronometro;
	private long milliseconds;
	private long millisecondsStop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cronometro = (Chronometer)findViewById(R.id.chronometer1);
		cronometro.setText(DateFormat.format("kk:mm:ss", 0));
		milliseconds = 0;
		millisecondsStop = 0;
	}
	
	public void startChronometer(View view) {
		//verifica de o tempo do systema
		millisecondsStop=  millisecondsStop > 0 ? System.currentTimeMillis() - millisecondsStop : 0;
		cronometro.setBase(SystemClock.elapsedRealtime() - (milliseconds + millisecondsStop));
		cronometro.start();
		millisecondsStop = 0;
	}

	public void pauseChronometer(View view) {
		millisecondsStop = System.currentTimeMillis();
		milliseconds = SystemClock.elapsedRealtime() - cronometro.getBase();
		cronometro.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
