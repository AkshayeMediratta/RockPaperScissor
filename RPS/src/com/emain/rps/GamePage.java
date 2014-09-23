package com.emain.rps;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.database.helper.PersonDbHelper;
import com.entity.Player;
import com.utils.Constants;

public class GamePage extends ActionBarActivity {

	Player player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_page);
		player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);
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
		Intent intent = new Intent(this, GamePage.class);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		PersonDbHelper pDbHelper = new PersonDbHelper(getBaseContext());
		SQLiteDatabase personDb = pDbHelper.getWritableDatabase();
		pDbHelper.updateUser(personDb, player);
		startActivity(intent);

	}

	public void view_statistics(View view) {
		Intent intent = new Intent(this, StatisticsPage.class);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		PersonDbHelper pDbHelper = new PersonDbHelper(getBaseContext());
		SQLiteDatabase personDb = pDbHelper.getWritableDatabase();
		pDbHelper.updateUser(personDb, player);
		startActivity(intent);

	}

	public void win(View view) {
		player.setNbOfWins(player.getNbOfWins() + 1);
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}

	public void lose(View view) {
		player.setNbOfLosses(player.getNbOfLosses() + 1);
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}

	public void draw(View view) {
		player.setTotalNbofGames(player.getTotalNbofGames() + 1);
	}
}
