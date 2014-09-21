package com.database.contracts;

import android.provider.BaseColumns;

public final class PlayerReaderContract {

	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public PlayerReaderContract() {
	}

	/* Inner class that defines the table contents */
	public static abstract class FeedEntry implements BaseColumns {
		public static final String TABLE_NAME = "Player";
		public static final String COLUMN_NAME_PLAYER_ID = "Id";
		public static final String COLUMN_NAME_USERID = "UserId";
		public static final String COLUMN_NAME_GAMES_TOTAL = "Total";
		public static final String COLUMN_NAME_GAMES_WON = "Wins";
		public static final String COLUMN_NAME_GAMES_LOST = "Losses";

	}

}
