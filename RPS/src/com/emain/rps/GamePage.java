package com.emain.rps;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.database.helper.PersonDbHelper;
import com.entity.Player;
import com.utils.Constants;
import com.utils.shake.ShakeDetector;
import com.utils.shake.ShakeDetector.OnShakeListener;

public class GamePage extends ActionBarActivity {

	Player player;

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_page);
		player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);

		// ShakeDetector initialization
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector();
		TextView tx = (TextView) findViewById(R.id.textView1);
		tx.setText("The default choice is Rock. To change your input, shake the phone.");
		mShakeDetector.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub // method you would use to setup whatever you want done once the device has been shook.
				 */
				TextView tx = (TextView) findViewById(R.id.textView1);

				if (count % 3 == 0)
					tx.setText("Your selection is Rock.\nTo select SCISSOR shake again.");
				else if (count % 3 == 1)
					tx.setText("Your selection is Scissor.\nTo select PAPER shake again.");
				else if (count % 3 == 2)
					tx.setText("Your selection is Paper.\nTo select ROCK shake again.");
				counter = count;
			}
		});
		String choice = "";
		if (counter % 3 == 0)
			choice = "ROCK";
		else if (counter % 3 == 1)
			choice = "SCISSOR";
		else if (counter % 3 == 2)
			choice = "PAPER";
		addListnerOnButton();

	}

	public void addListnerOnButton() {
		Button button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent finalizeResult = 
				//		new Intent();
				//startActivity(finalizeResult);
				Random ra = new Random();
				int Low = 10;
				int High = 100;
				int Random = ra.nextInt(3 - 1) + 1;
				Random = Random % 3;
				String cpuChoice = "";
				TextView txM2 = (TextView) findViewById(R.id.textView2);
				if (Random == 0)
					cpuChoice = "CPU Chose : ROCK";
				else if (Random == 1)
					cpuChoice = "CPU Chose : SCISSOR";
				else if (Random == 2)
					cpuChoice = "CPU Chose : PAPER";
				txM2.setText(cpuChoice);

				TextView txr = (TextView) findViewById(R.id.textView1);

				if ((counter % 3) == Random) {
					txr.setText("Its a Draw.");
					draw();
				} else if (counter % 3 == 0 && (Random == 2)) {
					txr.setText("You lost.");
					lose();
				} else if (counter % 3 == 1 && Random == 0) {
					txr.setText("You lost.");
					lose();
				} else if (counter % 3 == 2 && Random == 1) {
					txr.setText("You lost.");
					lose();
				} else {
					txr.setText("You win.");
					win();
				}
				Button btn = (Button) findViewById(R.id.button);
				btn.setEnabled(false);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_page, menu);
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

	public void playAnother(View view) {
		Intent intent = getIntent();
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		PersonDbHelper pDbHelper = new PersonDbHelper(getBaseContext());
		SQLiteDatabase personDb = pDbHelper.getWritableDatabase();
		pDbHelper.updateUser(personDb, player);
		startActivity(intent);
		finish();
		System.exit(0);

	}

	public void view_statistics(View view) {
		Intent intent = new Intent(this, StatisticsPage.class);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		PersonDbHelper pDbHelper = new PersonDbHelper(getBaseContext());
		SQLiteDatabase personDb = pDbHelper.getWritableDatabase();
		pDbHelper.updateUser(personDb, player);
		startActivity(intent);
		finish();
		System.exit(0);

	}

	public void win() {
		player.setNbOfWins(player.getNbOfWins() + 1);
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}

	public void lose() {
		player.setNbOfLosses(player.getNbOfLosses() + 1);
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}

	public void draw() {
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}

	@Override
	public void onResume() {
		super.onResume();
		// Add the following line to register the Session Manager Listener onResume
		mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		// Add the following line to unregister the Sensor Manager onPause
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}

	@Override
	public void onBackPressed() {
	}
}
