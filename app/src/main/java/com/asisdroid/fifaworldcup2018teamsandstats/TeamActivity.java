package com.asisdroid.fifaworldcup2018teamsandstats;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class TeamActivity extends AppCompatActivity {

    GridView playerGridView;
    private AllPlayersAdapter allPlayersViewAdapter;
    final String playerUrlPrefix = "https://docs.google.com/uc?id=";
    Teams teamObj;
    Players[] playerObjArray;

    public static TextView txtHeading, txtTeamDetails, wikiLink;
    public static ImageView  imgFlag, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        getSupportActionBar().hide();

        txtHeading = (TextView) findViewById(R.id.txt_heading);
        txtTeamDetails = (TextView) findViewById(R.id.txt_team_details);

        wikiLink = (TextView) findViewById(R.id.wiki_link);
        imgFlag = (ImageView) findViewById(R.id.img_flag);
        btnBack = (ImageView) findViewById(R.id.btn_back);

        //collect our intent
        Intent intent = getIntent();
        teamObj = intent.getParcelableExtra("teamDetailsObj");
        playerObjArray = teamObj.getPlayerList();

        txtHeading.setText(teamObj.getTeamName()+"\n("+teamObj.getTeamContinent()+")");
        txtTeamDetails.setText("Rank : "+teamObj.getTeamRank()+"\n"+"Apps : "+teamObj.getTeamAppearances()
                +"\n"+"Trophies : "+teamObj.getTeamTitles());

        wikiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(teamObj.getmWikiLink()));
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        wikiLink.bringToFront();

        imgFlag.setImageDrawable(getResources().getDrawable(teamObj.getTeamFlag()));

        playerGridView = (GridView) findViewById(R.id.playersGridView);
        allPlayersViewAdapter = new AllPlayersAdapter();
        playerGridView.setAdapter(allPlayersViewAdapter);
    }

    public class AllPlayersAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public AllPlayersAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return 23;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final PlayerViewHolder holder;
            if (convertView == null) {
                holder = new PlayerViewHolder();
                convertView = mInflater.inflate(
                        R.layout.player_view_layout, null);
                holder.txtPlayerApps = (TextView) convertView.findViewById(R.id.txt_teamrankings);
                holder.txtPlayerGoals = (TextView) convertView.findViewById(R.id.txt_teamwins);
                holder.txtPlayerName = (TextView) convertView.findViewById(R.id.txt_colroname);
                holder.teamPlayerPic = (ImageView) convertView.findViewById(R.id.color_demo);
                holder.colorFullLayout = (CardView) convertView.findViewById(R.id.color_layout);

                convertView.setTag(holder);
            }
            else {
                holder = (PlayerViewHolder) convertView.getTag();
            }

            holder.txtPlayerApps.setTag(R.id.txt_teamrankings,position);
            holder.txtPlayerGoals.setTag(R.id.txt_teamwins,position);
            holder.txtPlayerName.setTag(R.id.txt_colroname,position);
            holder.teamPlayerPic.setTag(R.id.color_demo,position);
            holder.colorFullLayout.setTag(R.id.color_layout,position);

            final Players playerCurrntObj = playerObjArray[position];
            final String playerName = playerCurrntObj.getmPlayerName();
            holder.txtPlayerApps.setText(playerCurrntObj.getmPlayerAppearances());
           // if(playerName.length()<=9) {
                holder.txtPlayerName.setText(playerName + " [ " + playerCurrntObj.getmPlayerPosition() + " ]" + "\n( Age : " + playerCurrntObj.getmPlayerAge() + " )");
           /* }
            else{
                holder.txtPlayerName.setText(playerName.substring(0,7) + "..[ " + playerCurrntObj.getmPlayerPosition() + " ]" + "\n( Age : " + playerCurrntObj.getmPlayerAge() + " )");
            }*/
            holder.txtPlayerGoals.setText(playerCurrntObj.getmPlayerGoals());

            Log.d("loadplayer", playerUrlPrefix+playerCurrntObj.getmPlayerImage());

            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.loading_avatar)
                    .error(R.drawable.avatar)
                    .skipMemoryCache(true)
                    .priority(Priority.HIGH);
            try {
                Glide.with(TeamActivity.this).load(playerUrlPrefix + playerCurrntObj.getmPlayerImage())
                        .apply(options)  // any image in case of error
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .thumbnail(0.1f)
                        .into(holder.teamPlayerPic);
            }
            catch (Exception e){

            }
            holder.colorFullLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent teamIntent = new Intent(TeamActivity.this, PlayerActivity.class);
                    teamIntent.putExtra("playerDetailsObj",playerCurrntObj);
                    startActivity(teamIntent);
                }
            });

            return convertView;
        }
    }

    class PlayerViewHolder {
        TextView txtPlayerName, txtPlayerApps, txtPlayerGoals;
        ImageView teamPlayerPic;
        CardView colorFullLayout;
    }
}
