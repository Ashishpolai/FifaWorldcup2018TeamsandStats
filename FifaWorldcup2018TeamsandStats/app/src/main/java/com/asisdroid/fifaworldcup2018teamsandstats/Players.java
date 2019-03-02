package com.asisdroid.fifaworldcup2018teamsandstats;

import android.os.Parcel;
import android.os.Parcelable;

public class Players implements Parcelable {

    String mPlayerName, mPlayerFullName, mPlayerPosition, mPlayerShirtNumber, mPlayerHeight, mPlayerAge,
            mPlayerAppearances, mPlayerGoals, mPlayerClub, mPlayerNationality, mPlayerImage;

    public Players(String mPlayerName, String mPlayerFullName, String mPlayerPosition, String mPlayerShirtNumber, String mPlayerHeight,
                   String mPlayerAge, String mPlayerAppearances, String mPlayerGoals, String mPlayerClub, String mPlayerNationality, String mPlayerImage){
        this.mPlayerName = mPlayerName;
        this.mPlayerFullName = mPlayerFullName;
        this.mPlayerPosition = mPlayerPosition;
        this.mPlayerShirtNumber = mPlayerShirtNumber;
        this.mPlayerHeight = mPlayerHeight;
        this.mPlayerAge = mPlayerAge;
        this.mPlayerAppearances = mPlayerAppearances;
        this.mPlayerGoals = mPlayerGoals;
        this.mPlayerClub = mPlayerClub;
        this.mPlayerNationality = mPlayerNationality;
        this.mPlayerImage = mPlayerImage;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mPlayerName);
        parcel.writeString(mPlayerFullName);
        parcel.writeString(mPlayerPosition);
        parcel.writeString(mPlayerShirtNumber);
        parcel.writeString(mPlayerHeight);
        parcel.writeString(mPlayerAge);
        parcel.writeString(mPlayerAppearances);
        parcel.writeString(mPlayerGoals);
        parcel.writeString(mPlayerClub);
        parcel.writeString(mPlayerNationality);
        parcel.writeString(mPlayerImage);
    }

    //constructor used for parcel
    public Players(Parcel parcel){
        //read and set saved values from parcel
        mPlayerName = parcel.readString();
        mPlayerFullName = parcel.readString();
        mPlayerPosition = parcel.readString();
        mPlayerShirtNumber = parcel.readString();
        mPlayerHeight = parcel.readString();
        mPlayerAge = parcel.readString();
        mPlayerAppearances = parcel.readString();
        mPlayerGoals = parcel.readString();
        mPlayerClub = parcel.readString();
        mPlayerNationality = parcel.readString();
        mPlayerImage = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Players> CREATOR = new Parcelable.Creator<Players>(){

        @Override
        public Players createFromParcel(Parcel parcel) {
            return new Players(parcel);
        }

        @Override
        public Players[] newArray(int size) {
            return new Players[0];
        }
    };

    public String getmPlayerName() {
        return mPlayerName;
    }

    public String getmPlayerFullName() {
        return mPlayerFullName;
    }

    public String getmPlayerPosition() {
        return mPlayerPosition;
    }

    public String getmPlayerShirtNumber() {
        return mPlayerShirtNumber;
    }

    public String getmPlayerHeight() {
        return mPlayerHeight;
    }

    public String getmPlayerAge() {
        return mPlayerAge;
    }

    public String getmPlayerAppearances() {
        return mPlayerAppearances;
    }

    public String getmPlayerGoals() {
        return mPlayerGoals;
    }

    public String getmPlayerClub() {
        return mPlayerClub;
    }

    public String getmPlayerNationality() {
        return mPlayerNationality;
    }

    public String getmPlayerImage() {
        return mPlayerImage;
    }
}
