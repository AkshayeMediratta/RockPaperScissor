package com.emain.rps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.entity.Player;
import com.utils.Constants;

public class SecondPage extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_page, menu);
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

	public void moveToStats(View view) {
		Intent intent = new Intent(this, StatisticsPage.class);
		Player player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		startActivity(intent);
	}

	public void startGame(View view) {
		Intent intent = new Intent(this, GamePage.class);
		Player player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		startActivity(intent);

	}

}
