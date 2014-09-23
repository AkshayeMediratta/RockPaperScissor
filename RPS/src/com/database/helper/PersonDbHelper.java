package com.database.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.database.contracts.PlayerReaderContract.FeedEntry;
import com.entity.Player;
import com.utils.Constants;

public class PersonDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "RPS.db";

	private static final String OPEN_BRACKET = " (";
	private static final String CLOSE_BRACKET = " )";
	private static final String DATATYPE_TEXT = " TEXT";
	private static final String DEFAULT_ZERO = " DEFAULT 0 ";
	private static final String DATATYPE_INTEGER = "INTEGER";
	private static final String COMMA_SEP = ",";
	private static final String PLAYER_TABLE_CREATE = "CREATE TABLE " + FeedEntry.TABLE_NAME + OPEN_BRACKET + FeedEntry.COLUMN_NAME_PLAYER_ID + " " + DATATYPE_INTEGER + " " + "PRIMARY KEY" + COMMA_SEP + FeedEntry.COLUMN_NAME_USERID + " " + DATATYPE_TEXT + " NOT NULL UNIQUE " + COMMA_SEP + " " + FeedEntry.COLUMN_NAME_GAMES_TOTAL + " " + DATATYPE_INTEGER + " " + DEFAULT_ZERO + COMMA_SEP + " " + FeedEntry.COLUMN_NAME_GAMES_WON + " " + DATATYPE_INTEGER + " " + DEFAULT_ZERO + COMMA_SEP + " "
			+ FeedEntry.COLUMN_NAME_GAMES_LOST + " " + DATATYPE_INTEGER + " " + DEFAULT_ZERO + CLOSE_BRACKET;

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

	public PersonDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PLAYER_TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onDelete(SQLiteDatabase db) {
		db.execSQL(SQL_DELETE_ENTRIES);
	}

	public void updateUser(SQLiteDatabase db, Player player) {
		db.execSQL("Update " + Constants.TABLE_NAME + " set " + FeedEntry.COLUMN_NAME_GAMES_WON + "=" + player.getNbOfWins() + COMMA_SEP + FeedEntry.COLUMN_NAME_GAMES_LOST + "=" + player.getNbOfLosses() + COMMA_SEP + FeedEntry.COLUMN_NAME_GAMES_TOTAL + "=" + player.getTotalNbofGames() + " where " + FeedEntry.COLUMN_NAME_USERID + "=" + "'" + player.getUserId() + "'");
	}

	public Player findUser(SQLiteDatabase db, String userName) {
		Player player = null;
		Cursor cursor = null;

		try {
			cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.COLUMN_NAME_USERID + " =?", new String[] { userName + "" });

			if (cursor.getCount() > 0) {

				cursor.moveToFirst();
				player = new Player();
				player.setUserId(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_USERID)));
				player.setTotalNbofGames(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_GAMES_TOTAL)));
				player.setNbOfWins(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_GAMES_WON)));
				player.setNbOfLosses(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_GAMES_LOST)));

			}
			return player;

		} finally {

			//cursor.close();
		}

	}

}
