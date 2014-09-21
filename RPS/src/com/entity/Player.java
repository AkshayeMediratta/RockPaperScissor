package com.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

	String userId;
	int totalNbOfGames;
	int nbOfWins;
	int nbOfLosses;

	//Default constructor for Player Object that initializes it to a basic state
	public Player() {
		userId = null;
		totalNbOfGames = 0;
		nbOfWins = 0;
		nbOfLosses = 0;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { this.userId, String.valueOf(this.totalNbOfGames), String.valueOf(this.nbOfWins), String.valueOf(this.nbOfLosses) });
	}

	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
		public Player createFromParcel(Parcel source) {
			return new Player(source);
		}

		public Player[] newArray(int size) {
			return new Player[size];
		}

	};

	//Constructor to be used with the Parcelable Interface
	public Player(Parcel in) {
		String[] data = new String[4];

		in.readStringArray(data);
		this.userId = data[0];
		this.totalNbOfGames = Integer.parseInt(data[1]);
		this.nbOfWins = Integer.parseInt(data[2]);
		this.nbOfLosses = Integer.parseInt(data[3]);

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalNbofGames() {
		return totalNbOfGames;
	}

	public void setTotalNbofGames(int totalNbOfGames) {
		this.totalNbOfGames = totalNbOfGames;
	}

	public int getNbOfWins() {
		return nbOfWins;
	}

	public void setNbOfWins(int nbOfWins) {
		this.nbOfWins = nbOfWins;
	}

	public int getNbOfLosses() {
		return nbOfLosses;
	}

	public void setNbOfLosses(int nbOfLosses) {
		this.nbOfLosses = nbOfLosses;
	}

	public int getNbOfDraws() {
		return totalNbOfGames - nbOfWins - nbOfLosses;
	}
}
