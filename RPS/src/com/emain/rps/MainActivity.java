package com.emain.rps;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.database.contracts.PlayerReaderContract.FeedEntry;
import com.database.helper.PersonDbHelper;
import com.entity.Player;
import com.utils.Constants;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.sexMale:
			if (checked)
				// Pirates are the best
				break;
		case R.id.sexFemale:
			if (checked)
				// Ninjas rule
				break;
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

	// Function that executes on clicking the Login button
	public void loginPlayer(View view) {
		// TODO Add code for validating entered information
		Boolean isValid = validateData();
		if (isValid) {
			// Extract the user ID from the entered information
			String userId = extractPlayerData();

			PersonDbHelper pDbHelper = new PersonDbHelper(getBaseContext());
			SQLiteDatabase personDb = pDbHelper.getWritableDatabase();

			// Search the database to check if the user exists
			Player player = pDbHelper.findUser(personDb, userId);

			// If user does not exist, insert new Player data
			if (null == player) {
				player = new Player();
				player.setUserId(userId);
				insertNewPlayerData(personDb, userId);

			}

			Intent intent = new Intent(this, SecondPage.class);
			intent.putExtra(Constants.PLAYER_PARCELABLE, player);
			startActivity(intent);
			finish();
			System.exit(0);

		} else {
			Toast.makeText(getApplicationContext(), "Please enter a name, a valid age and select your sex. ", Toast.LENGTH_LONG).show();
		}
	}

	private String extractPlayerData() {
		StringBuilder userId = new StringBuilder();
		userId.append(((EditText) findViewById(R.id.firstName)).getText().toString());
		userId.append(((EditText) findViewById(R.id.age)).getText().toString());
		RadioGroup sex = (RadioGroup) findViewById(R.id.sex);
		userId.append(((RadioButton) this.findViewById(sex.getCheckedRadioButtonId())).getText().toString());
		return userId.toString();

	}

	private long insertNewPlayerData(SQLiteDatabase personDb, String userId) {
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_USERID, userId);

		// Insert the new row, returning the primary key value of the new row
		return personDb.insert(FeedEntry.TABLE_NAME, null, values);
	}

	private boolean validateData() {
		RadioGroup sex = (RadioGroup) findViewById(R.id.sex);
		try {
			if (null == ((EditText) findViewById(R.id.firstName)).getText() || null == ((EditText) findViewById(R.id.firstName)).getText().toString() || null == ((EditText) findViewById(R.id.age)).getText() || null == ((EditText) findViewById(R.id.age)).getText().toString() || null == ((RadioButton) this.findViewById(sex.getCheckedRadioButtonId())).getText() || null == ((RadioButton) this.findViewById(sex.getCheckedRadioButtonId())).getText().toString()) {
				return false;
			}

			Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString());
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
