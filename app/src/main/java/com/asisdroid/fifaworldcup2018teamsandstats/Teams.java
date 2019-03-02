package com.asisdroid.fifaworldcup2018teamsandstats;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;


public class Teams implements Parcelable {

    String mTeamName;
    String mTeamRank;
    String mTeamTitles;
    String mTeamAppearances;
    String mTeamContinent;
    String mWikiLink;
    int mTeamFlag;

    Players[] mPlayerList;

    public Teams(String teamName, String teamRank, String teamTitles, String teamApps, String teamCOntinent, String wikiLink, int teamFlag, Players[] playerList){
        mTeamName = teamName;
        mTeamRank = teamRank;
        mTeamFlag = teamFlag;
        mTeamTitles = teamTitles;
        mTeamAppearances = teamApps;
        mTeamContinent = teamCOntinent;
        mPlayerList = playerList;
        mWikiLink = wikiLink;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getTeamRank() {
        return mTeamRank;
    }

    public int getTeamFlag() {
        return mTeamFlag;
    }

    public Players[] getPlayerList() {
        return mPlayerList;
    }

    public String getTeamTitles() {
        return mTeamTitles;
    }

    public String getTeamAppearances() {
        return mTeamAppearances;
    }

    public String getTeamContinent() {
        return mTeamContinent;
    }

    public void setPlayerList(Players[] mPlayerList) {
        this.mPlayerList = mPlayerList;
    }

    public String getmWikiLink() {
        return mWikiLink;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    //write object values to parcel for storage
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mTeamName);
        parcel.writeString(mTeamRank);
        parcel.writeString(mTeamTitles);
        parcel.writeString(mTeamAppearances);
        parcel.writeString(mTeamContinent);
        parcel.writeString(mWikiLink);
        parcel.writeInt(mTeamFlag);
        parcel.writeTypedArray(mPlayerList, 1);
    }

    //constructor used for parcel
    public Teams(Parcel parcel){
        //read and set saved values from parcel
        mTeamName = parcel.readString();
        mTeamRank = parcel.readString();
        mTeamTitles = parcel.readString();
        mTeamAppearances = parcel.readString();
        mTeamContinent = parcel.readString();
        mWikiLink = parcel.readString();
        mTeamFlag = parcel.readInt();
        mPlayerList = new Players[23];
        parcel.readTypedArray(mPlayerList, Players.CREATOR);
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Teams> CREATOR = new Parcelable.Creator<Teams>(){

        @Override
        public Teams createFromParcel(Parcel parcel) {
            return new Teams(parcel);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[0];
        }
    };
}
