package com.emain.rps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.entity.Player;
import com.utils.Constants;

public class StatisticsPage extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics_page);

		Player player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);
		if (null != player && null != player.getUserId()) {
			TextView welcome = (TextView) findViewById(R.id.welcomeText);
			welcome.setText("Welcome to the Rock Paper Scissor game. Your statistics are shown below: ");
			TextView totalGames = (TextView) findViewById(R.id.totalGames);
			totalGames.setText("Total Played: " + Integer.toString(player.getTotalNbofGames()));
			TextView nbWins = (TextView) findViewById(R.id.nbWins);
			nbWins.setText("Wins: " + Integer.toString(player.getNbOfWins()));
			TextView nbLosses = (TextView) findViewById(R.id.nbLosses);
			nbLosses.setText("Lost: " + Integer.toString(player.getNbOfLosses()));
			TextView nbDraws = (TextView) findViewById(R.id.nbDraws);
			nbDraws.setText("Draws: " + Integer.toString(player.getTotalNbofGames() - player.getNbOfWins() - player.getNbOfLosses()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics_page, menu);
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

	public void backToSecond(View view) {
		Intent intent = new Intent(this, SecondPage.class);
		Player player = getIntent().getParcelableExtra(Constants.PLAYER_PARCELABLE);
		intent.putExtra(Constants.PLAYER_PARCELABLE, player);
		startActivity(intent);
		finish();
		System.exit(0);

	}
}
