package com.asisdroid.fifaworldcup2018teamsandstats;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    FlipperLayout flipperLayout;
    GridView teamGridView;
    private AllTeamsAdapter allTeamViewAdapter;
    public ArrayList<Teams> teamDetailsListobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //INITIALIZE TEASMS AND PLAYERS
        //initPlayers();
        initAllDatas();

        flipperLayout = findViewById(R.id.flipper_layout);

        setLayout();

        teamGridView = (GridView) findViewById(R.id.teamsGridView);
        allTeamViewAdapter = new AllTeamsAdapter();
        teamGridView.setAdapter(allTeamViewAdapter);
    }

    private void setLayout() {
        int url[] = new int[]{
                R.drawable.ronaldomessi,
                R.drawable.failed_teams,
                R.drawable.buffonmissing,
                R.drawable.fixtures
        };

        String urlheadings[] = new String[]{
          "Last chance for two best players of the world",
           "Best teams missing from Fifa World Cup 2018",
           "World Cup 2018 Missing XI",
            "World Cup 2018 All Fixtures"
        };

        for (int i = 0; i < url.length; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageDrawable(url[i])
                    .setDescription(urlheadings[i]);
            view.setImageScaleType(ImageView.ScaleType.CENTER_CROP); //You can use any ScaleType
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    int x = flipperLayout.getCurrentPagePosition();
                        if(x==0){
                            Intent firstSlide = new Intent(MainActivity.this, FirstslideActivity.class);
                            startActivity(firstSlide);
                        }
                        else if(x==1){
                            Intent firstSlide = new Intent(MainActivity.this, SecslideActivity.class);
                            startActivity(firstSlide);
                        }
                        else if(x==2){
                            Intent firstSlide = new Intent(MainActivity.this, ThirdslideActivity.class);
                            startActivity(firstSlide);
                        }
                        else if(x==3){
                            Intent firstSlide = new Intent(MainActivity.this, FourthslideActivity.class);
                            startActivity(firstSlide);
                        }
                    }
            });
        }
    }

    public class AllTeamsAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public AllTeamsAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return 32;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(
                        R.layout.colors_layout, null);
                holder.txtteamRank = (TextView) convertView.findViewById(R.id.txt_teamrankings);
                holder.txtTeamWins = (TextView) convertView.findViewById(R.id.txt_teamwins);
                holder.txtTeamName = (TextView) convertView.findViewById(R.id.txt_colroname);
                holder.teamFLagDemo = (ImageView) convertView.findViewById(R.id.color_demo);
                holder.colorFullLayout = (CardView) convertView.findViewById(R.id.color_layout);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtteamRank.setTag(R.id.txt_teamrankings,position);
            holder.txtTeamWins.setTag(R.id.txt_teamwins,position);
            holder.txtTeamName.setTag(R.id.txt_colroname,position);
            holder.teamFLagDemo.setTag(R.id.color_demo,position);
            holder.colorFullLayout.setTag(R.id.color_layout,position);

            final Teams teamObj = teamDetailsListobj.get(position);

            holder.txtteamRank.setText(teamObj.getTeamRank());
           // if(teamObj.getTeamName().length()<=9) {
                holder.txtTeamName.setText(teamObj.getTeamName()+"\n"+"(Apps:"+teamObj.getTeamAppearances()+")");
           /* }
            else{
                holder.txtTeamName.setText(teamObj.getTeamName().substring(0,8)+".."+"\n"+"(Apps:"+teamObj.getTeamAppearances()+")");
            }*/
            holder.txtTeamWins.setText(teamObj.getTeamTitles());
            try {
                Glide.with(MainActivity.this).load(getResources().getDrawable(teamObj.getTeamFlag())).transition(DrawableTransitionOptions.withCrossFade()).
                        into(holder.teamFLagDemo);
            }
            catch (Exception e){

            }
            holder.colorFullLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //INITIALIZING PPLAYERS LIST
                    if(position == 0){
                        teamObj.setPlayerList(initArgentinaPlayers());
                    }
                    else if(position == 1){
                        teamObj.setPlayerList(initAustraliaPlayers());
                    }
                    else if(position == 2){
                        teamObj.setPlayerList(initBelgiumPlayers());
                    }
                    else if(position == 3){
                        teamObj.setPlayerList(initBrazilPlayers());
                    }
                    else if(position == 4){
                        teamObj.setPlayerList(initColombiaPlayers());
                    }
                    else if(position == 5){
                        teamObj.setPlayerList(initCostaRicaPlayers());
                    }
                    else if(position == 6){
                        teamObj.setPlayerList(initCroatiaPlayers());
                    }
                    else if(position == 7){
                        teamObj.setPlayerList(initDenmarkPlayers());
                    }
                    else if(position == 8){
                        teamObj.setPlayerList(initEgyptPlayers());
                    }
                    else if(position == 9){
                        teamObj.setPlayerList(initEnglandPlayers());
                    }
                    else if(position == 10){
                        teamObj.setPlayerList(initFrancePlayers());
                    }
                    else if(position == 11){
                        teamObj.setPlayerList(initGermanyPlayers());
                    }
                    else if(position == 12){
                        teamObj.setPlayerList(initIcelandPlayers());
                    }
                    else if(position == 13){
                        teamObj.setPlayerList(initIranPlayers());
                    }
                    else if(position == 14){
                        teamObj.setPlayerList(initJapanPlayers());
                    }
                    else if(position == 15){
                        teamObj.setPlayerList(initKoreaRepublicPlayers());
                    }
                    else if(position == 16){
                        teamObj.setPlayerList(initMexicoPlayers());
                    }
                    else if(position == 17){
                        teamObj.setPlayerList(initMoroccoPlayers());
                    }
                    else if(position == 18){
                        teamObj.setPlayerList(initNigeriaPlayers());
                    }
                    else if(position == 19){
                        teamObj.setPlayerList(initPanamaPlayers());
                    }
                    else if(position == 20){
                        teamObj.setPlayerList(initPeruPlayers());
                    }
                    else if(position == 21){
                        teamObj.setPlayerList(initPolandPlayers());
                    }
                    else if(position == 22){
                        teamObj.setPlayerList(initPortugalPlayers());
                    }
                    else if(position == 23){
                        teamObj.setPlayerList(initRussiaPlayers());
                    }
                    else if(position == 24){
                        teamObj.setPlayerList(initSaudiPlayers());
                    }
                    else if(position == 25){
                        teamObj.setPlayerList(initSenegalPlayers());
                    }
                    else if(position == 26){
                        teamObj.setPlayerList(initSerbiaPlayers());
                    }
                    else if(position == 27){
                        teamObj.setPlayerList(initSpainPlayers());
                    }
                    else if(position == 28){
                        teamObj.setPlayerList(initSwedenPlayers());
                    }
                    else if(position == 29){
                        teamObj.setPlayerList(initSwitzerlandPlayers());
                    }
                    else if(position == 30){
                        teamObj.setPlayerList(initTunisiaPlayers());
                    }
                    else if(position == 31){
                        teamObj.setPlayerList(initUruguayPlayers());
                    }
                    Intent teamIntent = new Intent(MainActivity.this, TeamActivity.class);
                    teamIntent.putExtra("teamDetailsObj",teamObj);
                    startActivity(teamIntent);
                }
            });

            return convertView;
        }
    }

    class ViewHolder {
        TextView txtTeamName, txtteamRank, txtTeamWins;
        ImageView teamFLagDemo;
        CardView colorFullLayout;
    }

    public void initAllDatas(){
        teamDetailsListobj = new ArrayList<Teams>();
        teamDetailsListobj.add(
        new Teams("Argentina", "5", "2", "16", "South America", "https://en.wikipedia.org/wiki/Argentina_national_football_team", R.drawable.argentina, null));
        teamDetailsListobj.add(
        new Teams("Australia", "40", "0", "4", "Asia", "https://en.wikipedia.org/wiki/Australia_national_soccer_team", R.drawable.australia, null));
        teamDetailsListobj.add(
        new Teams("Belgium", "3", "0", "12", "Europe", "https://en.wikipedia.org/wiki/Belgium_national_football_team", R.drawable.belgium, null));
        teamDetailsListobj.add(
        new Teams("Brazil", "2", "5", "20", "South America", "https://en.wikipedia.org/wiki/Brazil_national_football_team", R.drawable.brazil, null));
        teamDetailsListobj.add(
        new Teams("Colombia", "16", "0", "5", "South America", "https://en.wikipedia.org/wiki/Ecuador_national_football_team", R.drawable.colombia, null));
        teamDetailsListobj.add(
        new Teams("Costa Rica", "25", "0", "4", "North America", "https://en.wikipedia.org/wiki/Costa_Rica_national_football_team", R.drawable.costa_rica, null));
        teamDetailsListobj.add(
        new Teams("Croatia", "18", "0", "4", "Europe", "https://en.wikipedia.org/wiki/Croatia_national_football_team", R.drawable.croatia, null));
        teamDetailsListobj.add(
        new Teams("Denmark", "12", "0", "4", "Europe", "https://en.wikipedia.org/wiki/Denmark_national_football_team", R.drawable.denmark, null));
        teamDetailsListobj.add(
        new Teams("Egypt", "46", "0", "2", "Africa", "https://en.wikipedia.org/wiki/Egypt_national_football_team", R.drawable.egypt, null));
        teamDetailsListobj.add(
        new Teams("England", "13", "1", "14", "Europe", "https://en.wikipedia.org/wiki/England_national_football_team", R.drawable.england, null));
        teamDetailsListobj.add(
        new Teams("France", "7", "1", "14", "Europe", "https://en.wikipedia.org/wiki/France_national_football_team", R.drawable.france, null));
        teamDetailsListobj.add(
        new Teams("Germany", "1", "4", "18", "Europe", "https://en.wikipedia.org/wiki/Germany_national_football_team", R.drawable.germany, null));
        teamDetailsListobj.add(
        new Teams("Iceland", "22", "0", "0", "Europe", "https://en.wikipedia.org/wiki/Iceland_national_football_team", R.drawable.iceland, null));
        teamDetailsListobj.add(
        new Teams("Iran", "36", "0", "4", "Asia", "https://en.wikipedia.org/wiki/Iran_national_football_team", R.drawable.iran, null));
        teamDetailsListobj.add(
        new Teams("Japan", "60", "0", "5", "Asia", "https://en.wikipedia.org/wiki/Japan_national_football_team", R.drawable.japan, null));
        teamDetailsListobj.add(
        new Teams("Korea Republic", "61", "0", "9", "Asia", "https://en.wikipedia.org/wiki/South_Korea_national_football_team", R.drawable.south_korea, null));
        teamDetailsListobj.add(
        new Teams("Mexico", "15", "0", "15", "North America", "https://en.wikipedia.org/wiki/Mexico_national_football_team", R.drawable.mexico, null));
        teamDetailsListobj.add(
        new Teams("Morocco", "42", "0", "4", "Africa", "https://en.wikipedia.org/wiki/Morocco_national_football_team", R.drawable.morocco, null));
        teamDetailsListobj.add(
        new Teams("Nigeria", "47", "0", "5", "Africa", "https://en.wikipedia.org/wiki/Nigeria_national_football_team", R.drawable.nigeria, null));
        teamDetailsListobj.add(
        new Teams("Panama", "55", "0", "0", "North America", "https://en.wikipedia.org/wiki/Panama_national_football_team", R.drawable.panama, null));
        teamDetailsListobj.add(
        new Teams("Peru", "11", "0", "4", "South America", "https://en.wikipedia.org/wiki/Peru_national_football_team", R.drawable.peru, null));
        teamDetailsListobj.add(
        new Teams("Poland", "10", "0", "7", "Europe", "https://en.wikipedia.org/wiki/Poland_national_football_team", R.drawable.poland, null));
        teamDetailsListobj.add(
        new Teams("Portugal", "4", "0", "6", "Europe", "https://en.wikipedia.org/wiki/Portugal_national_football_team", R.drawable.portugal, null));
        teamDetailsListobj.add(
        new Teams("Russia", "66", "0", "10", "Europe", "https://en.wikipedia.org/wiki/Russia_national_football_team", R.drawable.russia, null));
        teamDetailsListobj.add(
        new Teams("Saudi Arabia", "67", "0", "4", "Asia", "https://en.wikipedia.org/wiki/Saudi_Arabia_national_football_team", R.drawable.saudi_arabia, null));
        teamDetailsListobj.add(
        new Teams("Senegal", "28", "0", "1", "Africa", "https://en.wikipedia.org/wiki/Senegal_national_football_team", R.drawable.senegal, null));
        teamDetailsListobj.add(
        new Teams("Serbia", "35", "0", "11", "Europe", "https://en.wikipedia.org/wiki/Serbia_national_football_team", R.drawable.serbia, null));
        teamDetailsListobj.add(
        new Teams("Spain", "8", "1", "14", "Europe", "https://en.wikipedia.org/wiki/Spain_national_football_team", R.drawable.spain, null));
        teamDetailsListobj.add(
        new Teams("Sweden", "23", "0", "11", "Europe", "https://en.wikipedia.org/wiki/Sweden_national_football_team", R.drawable.sweden, null));
        teamDetailsListobj.add(
        new Teams("Switzerland", "6", "0", "10", "Europe", "https://en.wikipedia.org/wiki/Switzerland_national_football_team", R.drawable.switzerland, null));
        teamDetailsListobj.add(
        new Teams("Tunisia", "14", "0", "4", "Africa", "https://en.wikipedia.org/wiki/Tunisia_national_football_team", R.drawable.tunisia, null));
        teamDetailsListobj.add(
        new Teams("Uruguay", "17", "2", "12", "South America", "https://en.wikipedia.org/wiki/Uruguay_national_football_team", R.drawable.uruguay, null));
    }

    public Players[] initArgentinaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Argentina";
        argPlayersList[0] = new Players("MESSI", "Lionel MESSI", "FW",
                "10","5'7", "30", "124",
                "64", "FC Barcelona (ESP)", nation, "1P1BUZOix5lZw4h1LIDk6_6Ka6AFcOdJV");
        argPlayersList[1] = new Players("MASCHERANO", "Javier MASCHERANO", "DF",
                "14","5'8", "33", "143",
                "3", "Hebei China Fortune FC (CHN)", nation, "1ah7veuIUFLFTPz7EoZ7fQr9DRz4kexVw");
        argPlayersList[2] = new Players("DI MARÍA", "DI MARIA Angel", "MF",
                "11","5'11", "30", "93",
                "19", "Paris Saint-Germain FC (FRA)", nation, "1AjMu63a77U-XQCm5BpmD-zR9b0msZfnV");
        argPlayersList[3] = new Players("AGÜERO", "AGUERO Sergio", "FW",
                "19","5'8", "30", "85",
                "37", "Manchester City FC (ENG)", nation, "13HyWzIkVsu9_MjNCgUZJ1CvOWS_8Cj24");
        argPlayersList[4] = new Players("GUZMÁN", "GUZMAN Nahuel", "GK",
                "1","6'3", "32", "6",
                "0", "Tigres UANL (MEX)", nation, "1d3EsylnCnCM60UVuQMSt1tKu0ji0Wh1t");
        argPlayersList[5] = new Players("MERCADO", "MERCADO Gabriel", "DF",
                "2","5'11", "31", "20",
                "3", "Sevilla FC (ESP) ", nation, "1o_wjZW9VqIOo1a8zDM-K46imfBYLdNMd");
        argPlayersList[6] = new Players("TAGLIAFICO", "TAGLIAFICO Nicolas", "DF",
                "3","5'7", "25", "4",
                "0", "AFC Ajax (NED)", nation, "14h-9QhVzPyz5-7tjo9UHUg1yLW6UpEEh");
        argPlayersList[7] = new Players("ANSALDI", "ANSALDI Cristian", "DF",
                "4","5'11", "31", "5",
                "1", "Torino FC (ITA)", nation, "1bLAbD272IGib7gk004HSyeGhSW2kYYen");
        argPlayersList[8] = new Players("BIGLIA", "BIGLIA Lucas", "MF",
                "5","5'10", "32", "57",
                "1", "AC Milan (ITA) ", nation, "1sDaUVD_MGiw-LjhJ8o6z4j8tzrQJ_EjK");
        argPlayersList[9] = new Players("FAZIO", "FAZIO Federico", "DF",
                "6","6'5", "31", "9",
                "1", "AS Roma (ITA)", nation, "1FFMKn43K24EXwDDyzai0LGXN1LATIQDm");
        argPlayersList[10] = new Players("BANEGA", "BANEGA Ever", "MF",
                "7","5'8", "29", "62",
                "6", "Sevilla FC (ESP)", nation, "1wk7s-3cWGbS0ChKefZ83lp8Y8UHDkIca");
        argPlayersList[11] = new Players("ACUÑA", "ACUNA Marcos", "DF",
                "8","5'7", "26", "8",
                "1", "Sporting CP (POR)", nation, "1hXSRA8p3bV3YWS4uaux19wFfv6qo8ntb");
        argPlayersList[12] = new Players("HIGUAÍN", "HIGUAIN Gonzalo", "FW",
                "9","6'1", "30", "70",
                "31", "Juventus FC (ITA)", nation, "1weCClUc-J5g3IFGpzwCFDsLKEqtTGXQd");
        argPlayersList[13] = new Players("ARMANI", "ARMANI Franco", "GK",
                "12","6'2", "31", "0",
                "0", "CA River Plate (ARG)", nation, "1fBkpP5UnaMlv2_-I3y8bVSAoGcJjNou4");
        argPlayersList[14] = new Players("MEZA", "MEZA Maximiliano", "MF",
                "13","5'11", "25", "1",
                "0", "CA Independiente (ARG)", nation, "1P1XHa3jeIsg-jpV92YhsxgCyHmTlMwAU");
        argPlayersList[15] = new Players("LANZINI", "LANZINI Manuel", "MF",
                "15","5'6", "25", "4",
                "1", "West Ham United FC (ENG)", nation, "18yt8QjeZZ9-KthHogndkV1YvxFU13pC_");
        argPlayersList[16] = new Players("ROJO", "ROJO Marcos", "DF",
                "16","6'2", "28", "56",
                "2", "Manchester United FC (ENG)", nation, "13O7qhzas-jQYEq0GsyDIEdi-1zS4BbuK");
        argPlayersList[17] = new Players("OTAMENDI", "OTAMENDI Nicolas", "DF",
                "17","6'0", "30", "54",
                "4", "Manchester City FC (ENG) ", nation, "1eNRmik0wJkOIwebHxFJ7dyPtn9NAIefH");
        argPlayersList[18] = new Players("SALVIO", "SALVIO Eduardo", "DF",
                "18","5'7", "27", "8",
                "0", "SL Benfica (POR) ", nation, "1_XXExYy9pxtx0Pjj-QXVkigJi1rkJcv9");
        argPlayersList[19] = new Players("LO CELSO ", "LO CELSO Giovani", "MF",
                "20","5'9", "22", "4",
                "0", "Paris Saint-Germain FC (FRA)", nation, "1ROnCmkbWoMq0QSOrnq97S68uxTzrW4pY");
        argPlayersList[20] = new Players("DYBALA", "DYBALA Paulo", "FW",
                "21","5'9", "24", "12",
                "0", "Juventus FC (ITA)", nation, "1YAzVuftWRQl_z0sQJjb069pid2rseMqs");
        argPlayersList[21] = new Players("PAVÓN", "PAVON Cristian", "MF",
                "22","5'5", "22", "5",
                "0", "CA Boca Juniors (ARG)", nation, "110XnfHD8Lh-L4W8Vt-8Tfr69S9-sF1m1");
        argPlayersList[22] = new Players("CABALLERO", "CABALLERO Wilfredo", "GK",
                "23","6'1", "36", "3",
                "0", "Chelsea FC (ENG)", nation, "1TvBsSuiswTl5S0RnTGGw-cJWjnpFYWtl");
   return argPlayersList;
    }

    public Players[] initAustraliaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Australia";
        argPlayersList[0] = new Players("RYAN", "RYAN Mathew", "GK",
                "1","6'1", "26", "43",
                "0", "Brighton & Hove Albion FC (ENG)", nation, "1NlSVKCwkf-ZKqhtj1sWiLoDhQ1tT_4SG");
        argPlayersList[1] = new Players("DEGENEK", "DEGENEK Milos", "DF",
                "2","6'2", "24", "18",
                "0", "Yokohama F-Marinos (JPN)", nation, "166SN4HAOHZRBFkJdfclhMuSEuPVEM8qu");
        argPlayersList[2] = new Players("MEREDITH", "MEREDITH James", "DF",
                "3","5'10", "30", "2",
                "0", "Millwall FC (ENG) ", nation, "143XeGnPgVvMbLKcKKVFlYuHKH_DAt3gv");
        argPlayersList[3] = new Players("CAHILL", "CAHILL Tim", "FW",
                "4","5'11", "38", "105",
                "50", "Millwall FC (ENG)", nation, "1MkZXknBOX-fXPIpnfBLYN9gYHl0leOQR");
        argPlayersList[4] = new Players("MILLIGAN", "MILLIGAN Mark", "DF",
                "5","5'10", "32", "70",
                "6", "Al Ahli SC (KSA)", nation, "1BxD_vad001oM9G_NOvfvzPAQiEi3TuEr");
        argPlayersList[5] = new Players("JURMAN", "JURMAN Matthew", "DF",
                "6","6'3", "28", "4",
                "0", "Suwon Samsung Bluewings FC (KOR)", nation, "1j3DIbXWheb0gsxgCQDHeKw0RanZCjnaw");
        argPlayersList[6] = new Players("LECKIE", "LECKIE Mathew", "FW",
                "7","5'11", "27", "52",
                "8", "Hertha BSC (GER)", nation, "11r_var2jWryLu4Fiw8SI3ZOGVxkZiYpt");
        argPlayersList[7] = new Players("LUONGO", "LUONGO Massimo", "MF",
                "8","5'9", "25", "35",
                "5", "Queens Park Rangers FC (ENG)", nation, "1-D5FhtfFA8AAkTIawwb8QvoQ2hvWzat_");
        argPlayersList[8] = new Players("JURIC", "JURIC Tomi", "FW",
                "9","6'3", "26", "34",
                "8", "FC Luzern (SUI)", nation, "1m2YsGsX-DW918Uj5HM4dsJcTj3Ctv50K");
        argPlayersList[9] = new Players("KRUSE", "KRUSE Robbie", "FW",
                "10","5'11", "29", "63",
                "5", "VfL Bochum (GER)", nation, "1RygYt6kM4QEEtSFspDpoMTAFa4WZ2Q1W");
        argPlayersList[10] = new Players("NABBOUT", "NABBOUT Andrew", "FW",
                "11","5'10", "25", "3",
                "1", "Urawa Reds (JPN)", nation, "1BoCK-bTHiOaFQMXbCBOKgHuxNOyfvKvh");
        argPlayersList[11] = new Players("JONES", "JONES Brad", "GK",
                "12","6'4", "36", "5",
                "0", "Feyenoord Rotterdam (NED)", nation, "14aCfhbqOT5hKMp-peh0mddwf58MGXto6");
        argPlayersList[12] = new Players("ROGIC", "ROGIC Tom", "MF",
                "13","6'2", "25", "36",
                "7", "Celtic FC (SCO)", nation, "1qBNC2bVv9G2kdspUREEdFF68hWy-987B");
        argPlayersList[13] = new Players("MOOY", "MOOY Aaron", "MF",
                "14","5'8", "27", "33",
                "5", "Huddersfield Town FC (ENG)", nation, "1IDDPB6T0RF1zMlCzshTb6aR_s38u1Awm");
        argPlayersList[14] = new Players("MacLAREN", "MacLAREN Jamie", "FW",
                "15","5'10", "24", "6",
                "0", "Hibernian FC (SCO)", nation, "1Ba_-tPpAm_9WmepVvuis8pubuSDWNF7O");
        argPlayersList[15] = new Players("JEDINAK", "JEDINAK Mile", "MF",
                "16","6'2", "33", "75",
                "18", "Aston Villa FC (ENG)", nation, "1ShDd_9Z7sf1M2dPSj7HrZUB2OFMVomSw");
        argPlayersList[16] = new Players("BEHICH", "BEHICH Aziz", "DF",
                "17","5'7", "27", "22",
                "2", "Bursaspor (TUR)", nation, "17G_5399iXg88fOt33-NX-ckU41ZQBo1o");
        argPlayersList[17] = new Players("ARZANI", "ARZANI Daniel", "FW",
                "18","5'7", "19", "1",
                "0", "Melbourne City FC (AUS)", nation, "1JR7y-Qa_qecyn4ulI8gNjuTGrk3H33Rl");
        argPlayersList[18] = new Players("VUKOVIC", "VUKOVIC Danny", "GK",
                "19","6'1", "33", "1",
                "0", "KRC Genk (BEL)", nation, "1tNiszz2PLEXilg-_bgRcYwcy-UMTTRdw");
        argPlayersList[19] = new Players("RISDON", "RISDON Joshua", "DF",
                "20","5'6", "25", "7",
                "0", "WS Wanderers FC (AUS)", nation, "10BCN5HGcpAVCHmCJkDh5LTEJzBw_kEGc");
        argPlayersList[20] = new Players("SAINSBURY", "SAINSBURY Trent", "DF",
                "21","6'0", "26", "124",
                "64", "Grasshopper Club (SUI)", nation, "1D30vQcJc9hYJYE1W3pQWvXbLm51Uovps");
        argPlayersList[21] = new Players("PETRATOS", "PETRATOS Dimitrios", "FW",
                "22","5'9", "25", "2",
                "0", "Newcastle United Jets FC (AUS)", nation, "1JyxSzSk3a4rA35gbQ-z_2165LWUpNsTk");
        argPlayersList[22] = new Players("IRVINE", "IRVINE Jackson", "MF",
                "23","5'11", "25", "18",
                "2", "Hull City FC (ENG)", nation, "15jrlBcD9yVti7RtymGRJrvMJ9wcMIWxX");
        return argPlayersList;
    }

    public Players[] initBelgiumPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Belgium";
        argPlayersList[0] = new Players("COURTOIS", "COURTOIS Thibaut", "GK",
                "1","6'6", "26", "56",
                "0", "Chelsea FC (ENG)", nation, "1wcK90NvxRT_uCJ4yHxd8vBsbo8ocL2x6");
        argPlayersList[1] = new Players("ALDERWEIRELD", "ALDERWEIRELD Toby", "DF",
                "2","6'2", "29", "75",
                "3", "Tottenham Hotspur FC (ENG)", nation, "1ZiQ9uNv1LglZiKJT5GmL9x7-Zze8AwNS");
        argPlayersList[2] = new Players("VERMAELEN", "VERMAELEN Thomas", "DF",
                "3","6'0", "32", "63",
                "1", "FC Barcelona (ESP)", nation, "1BvI7uLUKmPFyQvJpF7rbqQCYKx_NrdSF");
        argPlayersList[3] = new Players("KOMPANY", "KOMPANY Vincent", "DF",
                "4","6'4", "32", "77",
                "4", "Manchester City FC (ENG)", nation, "1KxFJOdmskAzpx26RqbHlF2V3_014ksJ7");
        argPlayersList[4] = new Players("VERTONGHEN", "VERTONGHEN Jan", "DF",
                "5","6'2", "31", "100",
                "8", "Tottenham Hotspur FC (ENG)", nation, "1RoMR-89dQCAyHM8TQza6MxnWhSj0inGy");
        argPlayersList[5] = new Players("WITSEL", "WITSEL Axel", "MF",
                "6","6'1", "29", "88",
                "9", "Tianjin Quanjian FC (CHN)", nation, "1Ey_Giab-ji3Ao9opFrNAdetgF_AFaKDM");
        argPlayersList[6] = new Players("DE BRUYNE", "DE BRUYNE Kevin", "MF",
                "7","5'11", "26", "60",
                "14", "Manchester City FC (ENG) ", nation, "1ybWIOeXuGBi5HsmHi_W7IBuRCluhdXhW");
        argPlayersList[7] = new Players("FELLAINI", "FELLAINI Marouane", "MF",
                "8","6'4", "30", "81",
                "16", "Manchester United FC (ENG)", nation, "1uDXCqPOCV5je9jLrMMERLcA6hJkIgm5s");
        argPlayersList[8] = new Players("LUKAKU", "LUKAKU Romelu", "FW",
                "9","6'3", "25", "67",
                "33", "Manchester United FC (ENG)", nation, "1ta6GoOobleg9McrDFV7_komE4LbTK1WR");
        argPlayersList[9] = new Players("HAZARD", "HAZARD Eden", "FW",
                "10","5'8", "27", "83",
                "21", "Chelsea FC (ENG)", nation, "1sna_dQ40phwYp2JOVIUMcnsaRKu26aSg");
        argPlayersList[10] = new Players("CARRASCO", "CARRASCO Yannick", "MF",
                "11","5'11", "24", "24",
                "5", "Dalian Yifang FC (CHN)", nation, "19z4QTXAokgF0Jl6wd4c8Y0HzLzeosyrx");
        argPlayersList[11] = new Players("MIGNOLET", "MIGNOLET Simon", "GK",
                "12","6'4", "30", "20",
                "0", "Liverpool FC (ENG) ", nation, "1VwgMjq7vOmK-7S5CkezZoUdFYq0iZGH2");
        argPlayersList[12] = new Players("CASTEELS", "CASTEELS Koen", "GK",
                "13","6'5", "25", "9",
                "0", "VfL Wolfsburg (GER)", nation, "1HkvVmjTo6UhS2uIl88KAFfi4ovVSN6CR");
        argPlayersList[13] = new Players("MERTENS", "MERTENS Dries", "FW",
                "14","5'7", "31", "67",
                "13", "SSC Napoli (ITA)", nation, "1EFUKSPsFv_gjggNQ0FnxmIA_-Zqvymyb");
        argPlayersList[14] = new Players("MEUNIER", "MEUNIER Thomas", "DF",
                "15","6'3", "26", "23",
                "5", "Paris Saint-Germain FC (FRA)", nation, "1asaD_nW8D6CtqEri6gIwU5z_wcFftD0o");
        argPlayersList[15] = new Players("HAZARD", "HAZARD Thorgan", "MF",
                "16","5'8", "25", "9",
                "1", "VfL Borussia Mönchengladbach (GER)", nation, "1TkAqyaWa6LYrE5nSPO93GXbA4DopMIuW");
        argPlayersList[16] = new Players("TIELEMANS", "TIELEMANS Youri", "MF",
                "17","5'9", "21", "7",
                "0", "AS Monaco (FRA)", nation, "1ag-ml3EZHNlx2hMtQe8rshjUJbII3pFH");
        argPlayersList[17] = new Players("JANUZAJ", "JANUZAJ Adnan", "FW",
                "18","6'1", "23", "7",
                "0", "Real Sociedad (ESP)", nation, "1KuL7oVUgs57uXjuPQ-OKU8QQbMC5wHfO");
        argPlayersList[18] = new Players("DEMBELE", "DEMBELE Moussa", "MF",
                "19","6'1", "30", "75",
                "5", "Tottenham Hotspur FC (ENG)", nation, "131UX74denbRXWb2pOXregf5Y0fQQcgxI");
        argPlayersList[19] = new Players("BOYATA", "BOYATA Dedryck", "DF",
                "20","6'2", "27", "5",
                "0", "Celtic FC (SCO)", nation, "1oEL2p_uhXtLCLKwNT2I5et-z_Ep_QVFc");
        argPlayersList[20] = new Players("BATSHUAYI", "BATSHUAYI Michy", "FW",
                "21","6'0", "24", "14",
                "6", "Borussia Dortmund (GER)", nation, "1OINqrn9S2n85lnj2ETzuCvyVY8lhFkrQ");
        argPlayersList[21] = new Players("CHADLI", "CHADLI Nacer", "MF",
                "22","6'2", "28", "43",
                "5", "West Bromwich Albion FC (ENG)", nation, "1uE25jITAEyGm_Umq3l-U5dbY_UOe92T8");
        argPlayersList[22] = new Players("DENDONCKER", "DENDONCKER Leander", "DF",
                "23","6'2", "23", "4",
                "1", "RSC Anderlecht (BEL)", nation, "1RLrE5VHGCN3X0csM3c6t_ytBO-x0lW8B");
        return argPlayersList;
    }

    public Players[] initBrazilPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Brazil";
        argPlayersList[0] = new Players("A. BECKER", "ALISSON", "GK",
                "10","6'3", "25", "25",
                "0", "AS Roma (ITA)", nation, "15jL0XhqFycvYonM1Odd3OD2e3gKOAzhQ");
        argPlayersList[1] = new Players("T. SILVA", "THIAGO SILVA", "DF",
                "10","6'0", "33", "70",
                "5", "Paris Saint-Germain FC (FRA)", nation, "1WOVMhBSIwj4q1lKDdj8DJ1o8gbC-9Wdq");
        argPlayersList[2] = new Players("MIRANDA", "MIRANDA", "DF",
                "10","6'1", "33", "46",
                "2", "FC Internazionale (ITA) ", nation, "1eZOxjnzRJwN3BgM7sEzwtmRXq6GfmJlQ");
        argPlayersList[3] = new Players("GEROMEL", "PEDRO GEROMEL", "DF",
                "10","6'3", "32", "2",
                "0", "Grêmio FBPA (BRA)", nation, "1ypi1M4FYLp0brTaU0EAWnqSShSH1HBdM");
        argPlayersList[4] = new Players("CASEMIRO", "CASEMIRO", "MF",
                "10","6'0", "26", "23",
                "0", "Real Madrid CF (ESP)", nation, "1lKLo5IH9RDOXPBwthnhA1_X8HfQ0iT7L");
        argPlayersList[5] = new Players("FILIPE LUIS", "FILIPE LUIS", "DF",
                "10","6'0", "32", "32",
                "2", "Atletico Madrid (ESP)", nation, "1o6GkN7ePON7zqi6myGrGiUyhQC--KbkW");
        argPlayersList[6] = new Players("D. COSTA", "DOUGLAS COSTA", "FW",
                "10","5'8", "27", "23",
                "3", "Juventus FC (ITA)", nation, "1Y0XE9XMOfMMyxWc9zxZgWE1teg-C8G70");
        argPlayersList[7] = new Players("R. AUGUSTO", "RENATO AUGUSTO", "MF",
                "10","6'1", "30", "28",
                "5", "Beijing Guoan (CHN)", nation, "1i69w0opSLaVfEuvbH_gNsAMucqfTfUUJ");
        argPlayersList[8] = new Players("G. JESUS", "GABRIEL JESUS", "FW",
                "10","5'9", "21", "16",
                "9", "Manchester City FC (ENG) ", nation, "1dc74stNZQ5gYpzOr0M8AWTvZdCU45VzV");
        argPlayersList[9] = new Players("NEYMAR JR", "NEYMAR", "FW",
                "10","5'9", "26", "84",
                "54", "Paris Saint-Germain FC (FRA)", nation, "1oS9Bmia06FJwcFNDZ34sr0BCiyuA4pB6");
        argPlayersList[10] = new Players(" P. COUTINHO", "PHILIPPE COUTINHO", "MF",
                "10","5'8", "25", "36",
                "9", "FC Barcelona (ESP) ", nation, "1kjabbVtjHBIKCX1IOhNawtU4IJe_vko_");
        argPlayersList[11] = new Players("MARCELO", "MARCELO", "DF",
                "10","5'9", "30", "52",
                "6", "Real Madrid CF (ESP)", nation, "1Sc9vQ2P-t1Bzs6a5-KCwSyXZcdUQnzVq");
        argPlayersList[12] = new Players("MARQUINHOS", "MARQUINHOS", "DF",
                "10","6'0", "24", "25",
                "0", "Paris Saint-Germain FC (FRA)", nation, "1SfQ6sgA1ucQiSDiMmo0mTxZmAHmnmJEI");
        argPlayersList[13] = new Players("DANILO", "DANILO", "DF",
                "10","6'0", "26", "17",
                "0", "Manchester City FC (ENG)", nation, "19AGwSYBw8koA5FoIupnyAQzYPnExs6_O");
        argPlayersList[14] = new Players("PAULINHO", "PAULINHO", "MF",
                "10","5'11", "29", "48",
                "12", "FC Barcelona (ESP) ", nation, "18-g4bKqlF87LnjjXiixBC6gFNLroGLlF");
        argPlayersList[15] = new Players("CASSIO", "CASSIO", "GK",
                "10","6'5", "30", "2",
                "0", "SC Corinthians (BRA) ", nation, "1egbv6lRw_0n013dMeIHw9Zz5QCNHnJsM");
        argPlayersList[16] = new Players("FERNANDINHO", "FERNANDINHO", "MF",
                "10","5'10", "33", "42",
                "2", "Manchester City FC (ENG)", nation, "1P9JpyWLDym73jd_t29erNwrTxGY3Vidd");
        argPlayersList[17] = new Players("FRED", "FRED", "MF",
                "10","5'7", "25", "8",
                "0", "FC Shakhtar Donetsk (UKR)", nation, "1KlqplSO8EKjKSv4KZyT62gFqkH4cD6Q2");
        argPlayersList[18] = new Players("EDERSON", "EDERSON", "GK",
                "10","6'2", "24", "1",
                "0", "Manchester City FC (ENG)", nation, "19k8fGvx_NBLRUxkLzON8BuIYrlbVC_Vn");
        argPlayersList[19] = new Players("WILLIAN", "WILLIAN", "MF",
                "10","5'9", "29", "55",
                "8", "Chelsea FC (ENG) ", nation, "1GtJ4PmIdceZfN5noJzIiV6poK3fBXPkz");
        argPlayersList[20] = new Players("FIRMINO", "ROBERTO FIRMINO", "FW",
                "10","5'11", "26", "20",
                "6", "Liverpool FC (ENG)", nation, "1dITkngNi9T5nGGUUvzAK_dZT0HRqF2kQ");
        argPlayersList[21] = new Players("TAISON", "TAISON", "FW",
                "10","5'7", "30", "7",
                "1", "FC Shakhtar Donetsk (UKR)", nation, "1SaawZQ4HYOH1KvcpgKgUDnPBW3ANZjeX");
        argPlayersList[22] = new Players("FAGNER", "FAGNER", "DF",
                "10","5'6", "28", "4",
                "0", "SC Corinthians (BRA)", nation, "1KGuazLcvOYqQbgSBP_UR945YjVLk5FbQ");
        return argPlayersList;
    }

    public Players[] initColombiaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Colombia";
        argPlayersList[0] = new Players("OSPINA", "OSPINA David", "GK",
                "10","6'0", "29", "86",
                "0", "Arsenal FC (ENG) ", nation, "1-u7iPmE7momIMksZHUV_MjpVoehf5Qtj");
        argPlayersList[1] = new Players("C. ZAPATA", "ZAPATA Cristian", "DF",
                "10","6'1", "31", "55",
                "2", "AC Milan (ITA)", nation, "18x5YB1mcFKap0UPakA9IoYSbMaf-fVIk");
        argPlayersList[2] = new Players("O. MURILLO", "MURILLO Oscar", "DF",
                "10","6'0", "30", "12",
                "0", "CF Pachuca (MEX)", nation, "18jjhbL_nf5PmQr6SbEIiouZ4wTfEeyzL");
        argPlayersList[3] = new Players("ARIAS", "ARIAS Santiago", "DF",
                "10","5'9", "26", "41",
                "0", "PSV Eindhoven (NED)", nation, "1FOvOlDCSa_fb2ANMpy4EFLQ3tmw2xgcW");
        argPlayersList[4] = new Players("BARRIOS", "BARRIOS Wilmar", "MF",
                "10","5'10", "24", "10",
                "0", "CA Boca Juniors (ARG)", nation, "1PqKfMBg-hDvy_8rcRZrbkY4j6yKE91tp");
        argPlayersList[5] = new Players("C. SANCHEZ", "SANCHEZ Carlos", "MF",
                "10","6'0", "32", "84",
                "0", "RCD Espanyol (ESP)", nation, "1ufzdI5xhOYSNfbPqTgzDEwxKAv9C820A");
        argPlayersList[6] = new Players("J.F. CUADRADO", "CUADRADO Jose", "GK",
                "10","5'11", "33", "1",
                "0", "CD Once Caldas (COL)", nation, "1TU2xDhPQA5DaiU1jjYuHBNFq_lIds0bv");
        argPlayersList[7] = new Players("D. SANCHEZ", "SANCHEZ Davinson", "DF",
                "10","6'2", "21", "9",
                "0", "Tottenham Hotspur FC (ENG)", nation, "1pCzwcMi2KGjgqAU46GmjwGB3Nav_0BvK");
        argPlayersList[8] = new Players("BACCA", "BACCA Carlos", "FW",
                "10","5'11", "31", "44",
                "14", "Villarreal CF (ESP) ", nation, "11-p1K-Jys639m77H9DKlIlOqxzpuDwUR");
        argPlayersList[9] = new Players("AGUILAR T.", "AGUILAR Abe", "MF",
                "10","6'1", "33", "70",
                "7", "AC Deportivo Cali (COL)", nation, "1pnktRQwxiCmc5IO675ZCVmb8qz4AINQx");
        argPlayersList[10] = new Players("FALCAO", "FALCAO Radame", "FW",
                "10","5'0", "32", "71",
                "29", "AS Monaco (FRA) ", nation, "1N5vUpVgXurSI0TGe1CshxdSmOsCvSmT4");
        argPlayersList[11] = new Players("JAMES", "RODRIGUEZ James", "MF",
                "10","5'11", "26", "62",
                "21", "FC Bayern München (GER)", nation, "1TftecV1e7qHEAjAq8IAC5_qJx_snA5Xu");
        argPlayersList[12] = new Players("CUADRADO", "CUADRADO Juan", "MF",
                "10","5'10", "30", "69",
                "7", "Juventus FC (ITA)", nation, "1eSOEanR4e0HOZ50DCiAoATUIHWIOsYJO");
        argPlayersList[13] = new Players("C. VARGAS", "VARGAS Camilo", "GK",
                "10","6'1", "29", "5",
                "0", "AC Deportivo Cali (COL)", nation, "1H6WQ_cjlCZKr5zJCk7MyGsqTdsj6Kqkr");
        argPlayersList[14] = new Players("Y. MINA", "MINA Yerry", "DF",
                "10","6'5", "23", "12",
                "3", "FC Barcelona (ESP)", nation, "1YRC8ROrfVlsRt3XFUPSUzlEl495EtwMk");
        argPlayersList[15] = new Players("L. MURIEL ", "MURIEL Luis", "FW",
                "10","5'10", "27", "18",
                "2", "Sevilla FC (ESP)", nation, "1bQJOMOSYrg9duc0f6Fdtdeogy9tRGEHv");
        argPlayersList[16] = new Players("M. URIBE", "URIBE Mateus", "MF",
                "10","6'0", "27", "8",
                "0", "Club América (MEX)", nation, "1H0VO-A1Phau5OVr68LKJf9xVIAsm4p3b");
        argPlayersList[17] = new Players("J. LERMA", "LERMA Jefferson", "MF",
                "10","6'0", "23", "5",
                "0", "Levante UD (ESP)", nation, "127NJVb7sxTKTbRbTHAJgEIqrd6aknfrV");
        argPlayersList[18] = new Players("J. MOJICA", "MOJICA Johan", "DF",
                "10","5'10", "25", "4",
                "1", "Girona FC (ESP)", nation, "1IUB3xj8y6ygQA7V0XLKu6WSFZ2l_FIu4");
        argPlayersList[19] = new Players("FABRA", "FABRA Frank", "DF",
                "10","5'7", "27", "19",
                "1", "CA Boca Juniors (ARG)", nation, "1q7OHVZZtFyw3uK9FZ3n9opbQqBEsYdVd");
        argPlayersList[20] = new Players("M. BORJA", "BORJA Miguel", "ST",
                "10","6'0", "25", "7",
                "2", "SE Palmeiras (BRA)", nation, "1mRjy6H1obpfgt_rdrRC5aBeK5R6MEUz7");
        argPlayersList[21] = new Players("J. QUINTERO", "QUINTERO Juan", "MF",
                "10","5'6", "25", "14",
                "2", "CA River Plate (ARG)", nation, "17Fr7GpaJfAL80wcdkNdXxRDa5P2ABE2S");
        argPlayersList[22] = new Players("IZQUIERDO", "IZQUIERDO Jose", "FW",
                "10","5'7", "25", "5",
                "1", "Brighton & Hove Albion FC (ENG)", nation, "12U_YHV4EiuHqG1ZKtN01ILhcwY8xvgYT");
        return argPlayersList;
    }

    public Players[] initCostaRicaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Costa Rica";
        argPlayersList[0] = new Players("K. NAVAS", "NAVAS Keylor", "GK",
                "10","6'1", "31", "76",
                "0", "Real Madrid CF (ESP)", nation, "1nJEyMfYDi7lbjQmAzmQwYdkuWvXxKg70");
        argPlayersList[1] = new Players("J. ACOSTA", "ACOSTA Johnny", "DF",
                "10","5'9", "34", "45",
                "2", "Rionegro Águilas (COL)", nation, "1RZRnE_EQ5EwoR5ymeI38YIvHwJEPbO23");
        argPlayersList[2] = new Players("G. GONZALEZ", "GONZALEZ Giancarlo", "DF",
                "10","6'1", "30", "54",
                "2", "Bologna FC (ITA) ", nation, "18j9a6aA4hzUM_y-onXL6WCtbWnIrkp3t");
        argPlayersList[3] = new Players(" I. SMITH", "SMITH Ian", "DF",
                "10","5'10", "20", "1",
                "0", "IFK Norrkoping FK (SWE) ", nation, "1Yw0PFl95YwKtFD8YJFrclXWP6_sse0c9");
        argPlayersList[4] = new Players("C. BORGES", "BORGES Celso", "MF",
                "10","6'1", "30", "100",
                "21", "Deportivo La Coruña (ESP)", nation, "1_KDn1sFw3Utddb69ibnZ9danQlrz58jR");
        argPlayersList[5] = new Players(" O. DUARTE", "DUARTE Oscar", "DF",
                "10","6'1", "29", "31",
                "2", "RCD Espanyol (ESP)", nation, "1n7d-5hSvRs76cvWl3a7Yf4VZEz3sxhkH");
        argPlayersList[6] = new Players("C. BOLAÑOS", "BOLANOS Christian", "MF",
                "10","5'9", "34", "71",
                "6", "Deportivo Saprissa (CRC)", nation, "1dK0CAxzsUVycpViB093ThujYugif9OKr");
        argPlayersList[7] = new Players("B. OVIEDO", "OVIEDO Bryan", "DF",
                "10","5'8", "28", "31",
                "1", "Sunderland AFC (ENG)", nation, "18rQM5ZBUEpaGWOmxvG3hH11fkCXUIBKb");
        argPlayersList[8] = new Players("D. COLINDRES", "COLINDRES Daniel", "MF",
                "10","5'11", "33", "7",
                "0", "Deportivo Saprissa (CRC) ", nation, "1G9V3xYyKJoUuYn4zM3EvyXlwSnzNVztk");
        argPlayersList[9] = new Players("BRYAN", "RUIZ Bryan", "MF",
                "10","6'2", "32", "101",
                "23", "Sporting CP (POR) ", nation, "1KQxQDhkh4gjhTCKtUs5JIfP2DEEf9UfU");
        argPlayersList[10] = new Players("J. VENEGAS", "VENEGAS Johan", "FW",
                "10","6'0", "29", "45",
                "10", "Deportivo Saprissa (CRC)", nation, "1O9WqKkub3-NbxAJYElAja64SZalCGNEI");
        argPlayersList[11] = new Players("J. CAMPBELL", "CAMPBELL Joel", "FW",
                "10","5'10", "25", "75",
                "15", "Real Betis (ESP)", nation, "1m65tw0Hl3EHCnFbgx_y3f7S8ZygSuAg9");
        argPlayersList[12] = new Players("R. WALLACE", "WALLACE Rodney", "MF",
                "10","5'11", "29", "24",
                "4", "New York City FC (USA)", nation, "1o_v-dEs4RvGC60YSiuaM2wiSszQUbM45");
        argPlayersList[13] = new Players(" R. AZOFEIFA", "AZOFEIFA Randall", "MF",
                "10","6'0", "33", "57",
                "3", "CS Herediano (CRC) ", nation, "1h_-xjg0CJ0TsLNMgSLq8OO1vM33Ne4X6");
        argPlayersList[14] = new Players("F. CALVO", "CALVO Francisco", "DF",
                "10","5'11", "25", "36",
                "4", "Minnesota United FC (USA)", nation, "1PhOkC4VWxnh-S5CU8-uUJ6kTfpMuH00K");
        argPlayersList[15] = new Players("C. GAMBOA", "GAMBOA Cristian", "DF",
                "10","5'8", "28", "62",
                "3", "Celtic FC (SCO)", nation, "1ipZvZrnQzQBHE2BI6mqvOpGPcu59IRas");
        argPlayersList[16] = new Players("Y. TEJEDA", "TEJEDA Yeltsin", "MF",
                "10","5'10", "26", "39",
                "0", "FC Lausanne-Sport (SUI)", nation, "1NO-Orfb5iRriVFZCvuM7ByWxBqKH5Tfk");
        argPlayersList[17] = new Players("P. PEMBERTON", "PEMBERTON Patrick", "GK",
                "10","5'10", "36", "34",
                "0", "LD Alajuelense (CRC)", nation, "1-4Jgeu7vtlMwFRDa9bI6g1DxJ56lsdN0");
        argPlayersList[18] = new Players("K. WASTON", "WASTON Kendall", "DF",
                "10","6'5", "30", "24",
                "3", "Vancouver Whitecaps FC (CAN)", nation, "1wAulGtoz67OVTBXmj61IFeX3ltDKpvAc");
        argPlayersList[19] = new Players("D. GUZMAN", "GUZMAN David", "MF",
                "10","5'9", "28", "26",
                "0", "Portland Timbers (USA)", nation, "1eo8GWfK3G1DRYid3ByR435Dw3RkIrbm4");
        argPlayersList[20] = new Players("M. UREÑA", "URENA Marcos", "FW",
                "10","5'10", "28", "60",
                "15", "Los Angeles FC (USA)", nation, "1oc0ISAf1P7AhaJw89uaJyVWmP4GAdQF-");
        argPlayersList[21] = new Players("R. MATARRITA", "MATARRITA Ronald", "DF",
                "10","5'9", "23", "18",
                "2", "New York City FC (USA)", nation, "1kpnwj10lkrpb3b3bNT1ZSLtIsiiOl4P1");
        argPlayersList[22] = new Players("L. MOREIRA", "MOREIRA Leonel", "GK",
                "10","5'11", "28", "5",
                "0", "CS Herediano (CRC)", nation, "1LkkT3DNaDqyhePvyaLdxnSjceblm0TIj");
        return argPlayersList;
    }

    public Players[] initCroatiaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Croatia";
        argPlayersList[0] = new Players("LIVAKOVIC", "LIVAKOVIC Dominik", "GK",
                "10","6'2", "23", "1",
                "0", "GNK Dinamo Zagreb (CRO)", nation, "1E2fwow0m_NXEC-jZPIAwIt3di-9lSsss");
        argPlayersList[1] = new Players("VRSALJKO", "VRSALJKO Sime", "DF",
                "10","6'0", "26", "35",
                "0", "Atletico Madrid (ESP)", nation, "17sGDrQITLGJzNDaAOjdwMsVKkslDuFJ2");
        argPlayersList[2] = new Players("STRINIC", "STRINIC Ivan", "DF",
                "10","6'1", "30", "42",
                "0", "UC Sampdoria (ITA)", nation, "1nUvyv2rF8q1g2JOeWaw870LUwIBUdURj");
        argPlayersList[3] = new Players("PERISIC", "PERISIC Ivan", "FW",
                "10","6'1", "29", "65",
                "17", "FC Internazionale (ITA) ", nation, "1qajejuFe63QbHyvGXP5YKccwvxMUZCqz");
        argPlayersList[4] = new Players("ĆORLUKA", "CORLUKA Vedran", "DF",
                "10","6'3", "32", "98",
                "4", "FC Lokomotiv Moscow (RUS)", nation, "1TV8uN7xllMjFc_A4EXbXTw2YzoOeMpZ0");
        argPlayersList[5] = new Players("LOVREN", "LOVREN Dejan", "DF",
                "10","6'2", "28", "38",
                "2", "Liverpool FC (ENG)", nation, "1_Uo4VMUJsqGypI1nzc0QQpu4BoPgNRHL");
        argPlayersList[6] = new Players("RAKITIC", "RAKITIC Ivan", "MF",
                "10","6'0", "30", "91",
                "14", "FC Barcelona (ESP)", nation, "1bNnZByUYHew4-Vabc1tohI4R9x7cRz8l");
        argPlayersList[7] = new Players("KOVACIC", "KOVACIC Mateo", "MF",
                "10","5'10", "24", "40",
                "1", "Real Madrid CF (ESP)", nation, "1nG6t7uffbgjbynu48ORY8-1V68yCX3Rw");
        argPlayersList[8] = new Players("KRAMARIC", "KRAMARIC Andrej", "FW",
                "10","5'11", "26", "30",
                "8", "TSG 1899 Hoffenheim (GER)", nation, "1GwjYn6xrp9SdDwtOUhiHQ_iGvsilyha9");
        argPlayersList[9] = new Players("MODRIC", "MODRIC Luka", "MF",
                "10","5'8", "32", "105",
                "12", "Real Madrid CF (ESP)", nation, "1PqKQMES_rDph6zU1iUmxfhiR5PJFjgG8");
        argPlayersList[10] = new Players("BROZOVIC", "BROZOVIC Marcelo", "MF",
                "10","5'11", "25", "34",
                "6", "FC Internazionale (ITA)", nation, "15W4DGHZxloMIK28P9TijRKG3RQxp2NdG");
        argPlayersList[11] = new Players("KALINIC", "KALINIC Lovre", "GK",
                "10","6'7", "28", "10",
                "0", "KAA Gent (BEL)", nation, "1J6NbNvSUowBu71MMPJiQF_TkaPyiybFh");
        argPlayersList[12] = new Players("JEDVAJ", "JEDVAJ Tin", "DF",
                "10","6'0", "22", "11",
                "0", "Bayer 04 Leverkusen (GER)", nation, "1fTJ8entxsIsWNPN5_Tx7X4oTJxX5c92_");
        argPlayersList[13] = new Players("BRADARIĆ", "BRADARIC Filip", "MF",
                "10","6'1", "26", "4",
                "0", "HNK Rijeka (CRO)", nation, "1_PalF5saOuM_vAcWmlv7TURyPdTR4dyv");
        argPlayersList[14] = new Players("N. KALINIĆ", "KALINIC Nikola", "FW",
                "10","6'1", "30", "41",
                "15", "AC Milan (ITA)", nation, "17Shic1h9pNffO_ebVVK1APMy2dgVzio4");
        argPlayersList[15] = new Players("CALETA-CAR", "CALETA-CAR Duje", "DF",
                "10","6'4", "21", "1",
                "0", "FC Red Bull Salzburg (AUT)", nation, "1VbQNfEynyxvlMqID2QYxaHodEy8agiYU");
        argPlayersList[16] = new Players("MANDZUKIC", "MANDZUKIC Mario", "FW",
                "10","6'3", "32", "82",
                "30", "Juventus FC (ITA)", nation, "1skRdCSRSZvbjygSYFauWNT5V7AF4ONcN");
        argPlayersList[17] = new Players("REBIC", "REBIC Ante", "FW",
                "10","6'1", "24", "15",
                "1", "Eintracht Frankfurt (GER)", nation, "1vVTmtPhx-uEieKezzlraY-gsBc1nqcXh");
        argPlayersList[18] = new Players("BADELJ", "BADELJ Milan", "MF",
                "10","6'1", "29", "37",
                "1", "ACF Fiorentina (ITA) ", nation, "1qOe3laYNUVNWwLu4hdPKSEQm-uFMDPnL");
        argPlayersList[19] = new Players("PJACA", "PJACA Marko", "FW",
                "10","6'1", "23", "16",
                "1", "FC Schalke 04 (GER)", nation, "1DPhREY8_46fiZ25KplxCOOCPo9wWLe32");
        argPlayersList[20] = new Players("VIDA", "VIDA Domagoj", "DF",
                "10","6'0", "58", "2",
                "64", "Besiktas JK (TUR)", nation, "130yEVjErVCElUKZpUrJunZ32loS1CUQq");
        argPlayersList[21] = new Players("PIVARIC", "PIVARIC Josip", "DF",
                "10","5'9", "29", "19",
                "0", "FC Dynamo Kyiv (UKR)", nation, "1ibt8ziwvP-1N2JgeF0ZvRRVDHYTtAjXA");
        argPlayersList[22] = new Players("SUBASIC", "SUBASIC Danijel", "GK",
                "10","6'3", "33", "37",
                "0", "AS Monaco (FRA)", nation, "1fElSv1GlJO4A8c5-ZmJJDTZt641Zo1xO");
        return argPlayersList;
    }

    public Players[] initDenmarkPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Denmark";
        argPlayersList[0] = new Players("SCHMEICHEL", "SCHMEICHEL Kasper", "GK",
                "10","6'2", "31", "31",
                "0", "Leicester City FC (ENG)", nation, "1Idvk21t_BM5KVNpfOSkVn-2CURjMu42l");
        argPlayersList[1] = new Players("KROHN-DEHLI", "KROHN-DEHLI Michael", "MF",
                "10","5'7", "35", "57",
                "6", "Deportivo La Coruña (ESP)", nation, "1y9Dr5oD_Gq62QrO0Ux1__lkNOY1aNzLb");
        argPlayersList[2] = new Players("VESTERGAARD", "VESTERGAARD Jannik", "DF",
                "10","6'6", "25", "10",
                "0", "VfL Borussia Mönchengladbach (GER)", nation, "1_BH-JC97gHY9HmxKV1lJg1IvAmsjZmip");
        argPlayersList[3] = new Players("KJAER", "KJAER Simon", "DF",
                "10","6'3", "29", "74",
                "3", "Sevilla FC (ESP)", nation, "10A45c5_h1n9t1evuzjL9mQFCRv7lxBQU");
        argPlayersList[4] = new Players("KNUDSEN", "KNUDSEN Jonas", "DF",
                "10","6'1", "25", "3",
                "0", "Ipswich Town FC (ENG)", nation, "1RlD20YUPQTLqSXav-k9Jx2vOvV0Y6tn2");
        argPlayersList[5] = new Players("CHRISTENSEN", "CHRISTENSEN Andreas", "DF",
                "10","6'2", "22", "14",
                "1", "Chelsea FC (ENG) ", nation, "1ttKVbfZlWR4s64oqu_il2o5chHSur02W");
        argPlayersList[6] = new Players("KVIST", "KVIST William", "MF",
                "10","6'0", "33", "77",
                "22", "FC Kobenhavn (DEN)", nation, "13pJrtxVBHUTEHXJ0sHRwFnxHxZy2IFa6");
        argPlayersList[7] = new Players("DELANEY", "DELANEY Thomas", "MF",
                "10","6'0", "26", "26",
                "4", "SV Werder Bremen (GER)", nation, "18aFYICPTUqBZ2FcXIn2De0BS8g7j57hr");
        argPlayersList[8] = new Players("JORGENSEN", "JORGENSEN Nicolai", "FW",
                "10","6'3", "27", "28",
                "8", "Feyenoord Rotterdam (NED)", nation, "1JVF41VQ2NszdPtL7sFm_azuUzRe5Q6cA");
        argPlayersList[9] = new Players("ERIKSEN", "ERIKSEN Christian", "MF",
                "10","6'0", "26", "77",
                "21", "Tottenham Hotspur FC (ENG)", nation, "1mkfVcMB1tiv5h_IKyjztYetM5W23t7bf");
        argPlayersList[10] = new Players("BRAITHWAITE", "BRAITHWAITE Martin", "FW",
                "10","5'11", "27", "18",
                "1", "FC Girondins Bordeaux (FRA) ", nation, "1LTJLJTPqxBCYBvjvMfBNa4tHZ5-Mc4I2");
        argPlayersList[11] = new Players("DOLBERG", "DOLBERG Kasper", "FW",
                "10","6'1", "20", "4",
                "1", "AFC Ajax (NED)", nation, "10Vt5qI4cWWBuNXn8tuC-ZhrvhSTrTKYf");
        argPlayersList[12] = new Players("JORGENSEN", "JORGENSEN", "DF",
                "10","6'3", "28", "12",
                "0", "Huddersfield Town FC (ENG)", nation, "1U5Xvqr02E46nNI8x1hasDEP84IpxNT2Y");
        argPlayersList[13] = new Players("DALSGAARD", "DALSGAARD Henrik", "DF",
                "10","6'3", "28", "11",
                "0", "Brentford FC (ENG)", nation, "1SggtkeAuM4IZIgkjNmJoMTIYn8_RjIr0");
        argPlayersList[14] = new Players("FISCHER", "FISCHER Viktor", "FW",
                "10","5'11", "23", "17",
                "3", "FC Kobenhavn (DEN)", nation, "1N30X1HcYObd27iueVS0NhalDG85sK6GW");
        argPlayersList[15] = new Players("LOSSL", "LOSSL Jonas", "GK",
                "10","6'5", "29", "1",
                "0", "Huddersfield Town FC (ENG)", nation, "1rJRVOl9E5bMowJH4iZtIvROiCM6YZJ4l");
        argPlayersList[16] = new Players("STRYGER LARSEN", "STRYGER LARSEN Jens", "DF",
                "10","5'11", "27", "11",
                "1", "Udinese Calcio (ITA) ", nation, "1ij99uteTI1ksvfFhZMGWbH5Q8_8R0A3Z");
        argPlayersList[17] = new Players("LERAGER", "LERAGER Lukas", "MF",
                "10","6'0", "24", "2",
                "0", "FC Girondins Bordeaux (FRA)", nation, "12TgJ0P-9B0q5QofWkb2NqBmx8Olwn2uj");
        argPlayersList[18] = new Players("SCHONE", "SCHONE Lasse", "MF",
                "10","5'10", "32", "32",
                "3", "AFC Ajax (NED)", nation, "1gMT_rIIIEcdyqNPMbTYbi86IQvAo3X9a");
        argPlayersList[19] = new Players("YURARY", "POULSEN Yussuf Yurary", "FW",
                "10","6'4", "23", "24",
                "3", "RB Leipzig (GER)", nation, "1QHEn_30mRK1Uagmj6t0hyKm1lgw-5cWU");
        argPlayersList[20] = new Players("CORNELIUS", "CORNELIUS Andreas", "FW",
                "10","6'4", "25", "19",
                "8", "Atalanta Bergamo (ITA)", nation, "1YOVManOF5F7yS61LrsfPhnR3XD4rc8GZ");
        argPlayersList[21] = new Players("RONNOW", "RONNOW Frederik", "GK",
                "10","6'3", "25", "7",
                "0", "Brondby IF (DEN)", nation, "1m_Gzy3iCOJbHfFnBJuOhsfkf1Xirwjvn");
        argPlayersList[22] = new Players("SISTO", "SISTO Pione", "FW",
                "10","5'7", "23", "12",
                "1", "Celta Vigo (ESP)", nation, "1H2uBWmh5-UqVlecGDg7ypkin6PytCqvy");
        return argPlayersList;
    }

    public Players[] initEgyptPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Egypt";
        argPlayersList[0] = new Players("EL HADARY", "ESSAM EL HADARY", "GK",
                "10","6'2", "45", "158",
                "0", "Al Taawoun FC (KSA)", nation, "1bC1z7Aww67vNwax5CPFFmWbvJ8UBcNbf");
        argPlayersList[1] = new Players("ALI GABR", "ALI GABR", "DF",
                "10","6'4", "29", "18",
                "1", "West Bromwich Albion FC (ENG)", nation, "1yS8aIjb8VIno0dvWgh8HGiL1tYfDjf9p");
        argPlayersList[2] = new Players("ELMOHAMADY", "AHMED ELMOHAMADY", "DF",
                "10","6'0", "30", "74",
                "2", "Aston Villa FC (ENG)", nation, "1uHFjhas4W17F4MNeiOGjYF-4ec4qZJ5H");
        argPlayersList[3] = new Players("O. GABER", "OMAR GABER", "MF",
                "10","5'8", "26", "22",
                "0", "Los Angeles FC (USA)", nation, "1w7GYdw961DWLH_X7xvefmmeWt3ZW5S2A");
        argPlayersList[4] = new Players("MORSY", "SAM MORSY", "MF",
                "10","5'9", "26", "5",
                "0", "Wigan Athletic FC (ENG)", nation, "1PGNcKaYCNpyp48ezLxNJrEcZTDHWWB9T");
        argPlayersList[5] = new Players("A. HEGAZY", "AHMED HEGAZY", "DF",
                "10","6'4", "27", "43",
                "1", "West Bromwich Albion FC (ENG)", nation, "1TkWVMRvfBMzc_E23ADvCHMGfSOsYdgMd");
        argPlayersList[6] = new Players("A. FATHI", "AHMED FATHI", "DF",
                "10","5'9", "33", "120",
                "3", "Al Ahly SC (EGY)", nation, "1hHQmRXS1ryF7J287Zp_6wrH8ygw_dkWU");
        argPlayersList[7] = new Players("TAREK HAMED", "TAREK HAMED", "MF",
                "10","5'5", "29", "21",
                "0", "Zamalek (EGY)", nation, "1mG9wywHSYbShAC9nOJDc6fvSGGy9JgQm");
        argPlayersList[8] = new Players("MARWAN", "MARWAN MOHSEN", "FW",
                "10","6'1", "29", "17",
                "4", "Al Ahly SC (EGY)", nation, "1xKEyDSi1PG22PmFl0RWLfM9Rqiuoitpc");
        argPlayersList[9] = new Players("A. ASHRAF", "AYMAN ASHRAF", "DF",
                "10","5'9", "27", "5",
                "1", "Al Ahly SC (EGY) ", nation, "1tyOc8vqNr7sJ6vOcnEjr36rN4eLIhMCv");
        argPlayersList[10] = new Players("ABDELSHAFY", "MOHAMED ABDELSHAFY", "DF",
                "10","5'9", "32", "48",
                "1", "Al Fateh FC (KSA", nation, "147cfr_BDNH5zWt7_cDqiXw-uwcD5rMce");
        argPlayersList[11] = new Players("M SALAH", "MOHAMED SALAH", "FW",
                "10","5'9", "25", "57",
                "33", "Liverpool FC (ENG)", nation, "12Gau_sIkkouN6SVB9c3YbmEp8w1GK9AT");
        argPlayersList[12] = new Players("KAHRABA", "KAHRABA", "FW",
                "10","5'11", "24", "16",
                "2", "Al-Ittihad FC (KSA)", nation, "1z99UMvRbAsDWQ2K0KHtFhgiSo03UXmCE");
        argPlayersList[13] = new Players("RAMADAN", "RAMADAN SOBHY", "FW",
                "10","6'0", "21", "19",
                "1", "Stoke City FC (ENG)", nation, "14PxJBs7oR8_08xGPWj9DVeAetNSMW5AO");
        argPlayersList[14] = new Players("M. HAMDY", "MAHMOUD HAMDY", "DF",
                "10","5'7", "23", "0",
                "0", "Zamalek (EGY) ", nation, "1Pc7gWZ3RMTReIrrl6Z7hC-7otnu4VeWz");
        argPlayersList[15] = new Players("EKRAMY", "SHERIF EKRAMY", "GK",
                "10","6'3", "34", "21",
                "0", "Al Ahly SC (EGY)", nation, "19cZp15Acf8e_WZBbK7HJ1ThVFSYEVlXx");
        argPlayersList[16] = new Players("M. ELNENY", "MOHAMED ELNENY", "MF",
                "10","5'6", "25", "57",
                "5", "Arsenal FC (ENG)", nation, "1S14haLvRpWw2CC2Z8TrAmBnLisnkyF44");
        argPlayersList[17] = new Players("SHIKABALA", "SHIKABALA", "FW",
                "10","6'0", "32", "28",
                "1", "Al Raed SC (KSA)", nation, "1YHq-j9RgxfD7_d1wKpo0T5vnt0VMXjuG");
        argPlayersList[18] = new Players("ABDALLA", "ABDALLA SAID", "MF",
                "10","5'9", "32", "33",
                "6", "Kuopion PS (FIN)", nation, "1POKwWoscB0q2pTZG5SJ2LtDYSUeyxq4r");
        argPlayersList[19] = new Players("S. SAMIR", "SAMIR SAAD", "DF",
                "10","6'1", "29", "9",
                "0", "Al Ahly SC (EGY)", nation, "13F-V81Dvko-BHMmztcS9u3wAkU4poQaO");
        argPlayersList[20] = new Players("M. TREZEGUET", "Mahmoud Ibrahim Hassan(TREZEGUET)", "MF",
                "10","5'10", "23", "18",
                "2", "Kasimpasa SK (TUR) ", nation, "1FRp86erln8gd7HVZLv2J4GpOOijW6zm1");
        argPlayersList[21] = new Players("WARDA", "AMR WARDA", "FW",
                "10","5'9", "24", "14",
                "0", "Atromitos FC (GRE) ", nation, "1et_ubB0En18GNGscySdpWOVI9AGQrJVf");
        argPlayersList[22] = new Players("M. ELSHENAWY", "MOHAMED ELSHENAWY", "GK",
                "10","6'3", "29", "0",
                "0", "Al Ahly SC (EGY) ", nation, "1V0c9HeDK4QD7XJh7GuMRcGtU1jzgI0II");
        return argPlayersList;
    }

    public Players[] initEnglandPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "England";
        argPlayersList[0] = new Players("PICKFORD", "PICKFORD Jordan", "GK",
                "10","6'1", "24", "3",
                "0", "Everton FC (ENG)", nation, "1vaertzxQ5hiUupZzLG-45gxb_b46K3pH");
        argPlayersList[1] = new Players("WALKER", "WALKER Kyle", "DF",
                "10","5'10", "28", "35",
                "0", "Manchester City FC (ENG)", nation, "1hbT393m_L20uTRohTaMsaicOGrgT06TH");
        argPlayersList[2] = new Players("ROSE", "ROSE Danny", "DF",
                "10","5'8", "27", "17",
                "0", "Tottenham Hotspur FC (ENG)", nation, "1JrGyani9XgMIkDfq_zx27g8JqFS3Z-_m");
        argPlayersList[3] = new Players("DIER", "DIER Eric", "MF",
                "10","6'2", "24", "26",
                "3", "Tottenham Hotspur FC (ENG)", nation, "1okn0MqOl3BZKboYiOewLjjz6HKfLIqYT");
        argPlayersList[4] = new Players("STONES", "STONES John", "DF",
                "10","6'2", "24", "25",
                "0", "Manchester City FC (ENG)", nation, "1J840EzjaaUzsEAvxw9NJ0LgtwczBkhlR");
        argPlayersList[5] = new Players("MAGUIRE", "MAGUIRE Harry", "DF",
                "10","6'4", "25", "4",
                "0", "Leicester City FC (ENG) ", nation, "1a9ecpjRZ2oPsGO0pPChPpZB9C_SM1xbh");
        argPlayersList[6] = new Players("LINGARD", "LINGARD Jesse", "MF",
                "10","5'9", "25", "11",
                "1", "Manchester United FC (ENG)", nation, "1tPn8obnCPfBBkQqu39awmDaXaRaxbsik");
        argPlayersList[7] = new Players("HENDERSON", "HENDERSON Jordan", "MF",
                "10","6'0", "27", "38",
                "0", "Liverpool FC (ENG) ", nation, "13wiNyC4XQv95jkvWgPSBoizhdJrqODsd");
        argPlayersList[8] = new Players("KANE", "KANE Harry", "FW",
                "10","6'2", "24", "24",
                "13", "Tottenham Hotspur FC (ENG)", nation, "17icwstGmIjxwo3gUksunIdNT8452vZcX");
        argPlayersList[9] = new Players("STERLING", "STERLING Raheem", "FW",
                "10","5'7", "23", "38",
                "2", "Manchester City FC (ENG)", nation, "1jvtEDiywGd8yHD8dWQfiFMIapQY9gwXp");
        argPlayersList[10] = new Players("VARDY", "VARDY Jamie", "FW",
                "10","5'10", "31", "21",
                "7", "Leicester City FC (ENG)", nation, "1bgA1x665xZzdkpXNxoOxTCha_n6v3KZL");
        argPlayersList[11] = new Players("TRIPPIER", "TRIPPIER Kieran", "DF",
                "10","5'10", "27", "6",
                "0", "Tottenham Hotspur FC (ENG) 1", nation, "1wjvROiojWKoGRgGzb-Us1276RLRvwRVu");
        argPlayersList[12] = new Players("BUTLAND", "BUTLAND Jack", "GK",
                "10","6'5", "25", "7",
                "0", "Stoke City FC (ENG)", nation, "14EC7xfLcUhfrbiJqxEzFF_xZQq97s3QF");
        argPlayersList[13] = new Players("WELBECK", "WELBECK Danny", "FW",
                "10","6'1", "27", "38",
                "15", "Arsenal FC (ENG)", nation, "1F0kb2GHsTaqbHYt6amAdp5FA2bXDK1yH");
        argPlayersList[14] = new Players("CAHILL", "CAHILL Gary", "DF",
                "10","6'4", "32", "59",
                "5", "Chelsea FC (ENG)", nation, "1oYOjZyz9W3Eu5E6VU0eZjORtFDpK7Ib0");
        argPlayersList[15] = new Players("JONES", "JONES Phil", "DF",
                "10","6'1", "26", "24",
                "0", "Manchester United FC (ENG)", nation, "1BYG4FGCbdZjpy5QsA-2DmlhEJ9FOj3ZB");
        argPlayersList[16] = new Players("DELPH", "DELPH Fabian", "DF",
                "10","5'9", "28", "10",
                "0", "Manchester City FC (ENG)", nation, "15jzH8snROuh79rfxZBGm_sWxyHZzxTv5");
        argPlayersList[17] = new Players("YOUNG", "YOUNG Ashley", "DF",
                "10","5'9", "32", "34",
                "7", "Manchester United FC (ENG)", nation, "12zvLCmhmoqXFyLSE39QOZwjgEBjJJ1rM");
        argPlayersList[18] = new Players("RASHFORD", "RASHFORD Marcus", "FW",
                "10","5'11", "20", "18",
                "2", "Manchester United FC (ENG)", nation, "1U8FF13VrivjDTkwCbnJiEFd6hX9_JjP_");
        argPlayersList[19] = new Players("ALLI", "ALLI Dele", "MF",
                "10","6'2", "22", "24",
                "2", "Tottenham Hotspur FC (ENG) ", nation, "1bZzvyUPbwzIjrs7qXwLN9xyQRl8QuIAJ");
        argPlayersList[20] = new Players("LOFTUS-CHEEK", "LOFTUS-CHEEK Ruben", "MF",
                "10","6'3", "22", "3",
                "0", "Chelsea FC (ENG)", nation, "1Af2vi_ocvtse3vjNQUavSduIJe8NBqE5");
        argPlayersList[21] = new Players("ALEXANDER-ARNOLD", "ALEXANDER-ARNOLD Trent", "DF",
                "10","5'9", "19", "0",
                "0", "Liverpool FC (ENG)", nation, "1aix578ANt75LqcTpHU0Bhq7hKH40dQjm");
        argPlayersList[22] = new Players("POPE", "POPE Nick", "GK",
                "10","6'3", "26", "0",
                "0", "Burnley FC (ENG)", nation, "1CIwKEeOrYxAQCsjStJZf2LgQWipmoib_");
        return argPlayersList;
    }

    public Players[] initFrancePlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "France";
        argPlayersList[0] = new Players("LLORIS", "LLORIS Hugo", "GK",
                "10","6'2", "31", "97",
                "0", "Tottenham Hotspur FC (ENG)", nation, "1XeeDrCQ7o2Pripqq1bukz7FJvfnQSGny");
        argPlayersList[1] = new Players("PAVARD", "PAVARD Benjamin", "DF",
                "10","6'1", "22", "5",
                "0", "VfB Stuttgart (GER)", nation, "1qIlsx4B6ZhUGOH4Cjf02cByUWx0GDEoP");
        argPlayersList[2] = new Players("KIMPEMBE", "KIMPEMBE Presnel", "DF",
                "10","6'2", "22", "2",
                "0", "Paris Saint-Germain FC (FRA) ", nation, "15sCXp9t3iIM2MR74GP74wPoloGB03PPh");
        argPlayersList[3] = new Players("VARANE", "VARANE Raphael", "DF",
                "10","6'3", "25", "41",
                "2", "Real Madrid CF (ESP)", nation, "1pE8ZGxOfzjkXR7aOJTylwX45lGrddE3Y");
        argPlayersList[4] = new Players("UMTITI", "UMTITI Samuel", "DF",
                "10","6'0", "24", "18",
                "2", "FC Barcelona (ESP)", nation, "182x4YTbLOb4PDiIiLmfi9L3uJZfupMFZ");
        argPlayersList[5] = new Players("POGBA", "POGBA Paul", "MF",
                "10","6'3", "25", "53",
                "9", "Manchester United FC (ENG) ", nation, "1091ZD4ZB7rrNActhIcwiwbJh10_KhjeF");
        argPlayersList[6] = new Players("GRIEZMANN", "GRIEZMANN Antoine", "FW",
                "10","5'9", "27", "53",
                "20", "Atletico Madrid (ESP)", nation, "1FUgyykko75lnYl0GDphql97M4J1_zeJX");
        argPlayersList[7] = new Players("LEMAR", "LEMAR Thomas", "FW",
                "10","5'7", "22", "11",
                "3", "AS Monaco (FRA)", nation, "1joXk-xLvNj0bZOmOvEKYV9-4tTRgE8pR");
        argPlayersList[8] = new Players("GIROUD", "GIROUD Olivier", "FW",
                "10","6'4", "31", "73",
                "31", "Chelsea FC (ENG) ", nation, "1H3OJWP5hHgNZaQZlZpTKV3kmIVLduC3t");
        argPlayersList[9] = new Players("MBAPPE", "MBAPPE Kylian", "FW",
                "10","5'10", "19", "14",
                "3", "Paris Saint-Germain FC (FRA)", nation, "1xGQ9onCk1gwjU8SN5p6OPzHBIM2pBbq9");
        argPlayersList[10] = new Players("DEMBELE", "DEMBELE Ousmane", "FW",
                "10","5'10", "21", "11",
                "2", "FC Barcelona (ESP)", nation, "1fI5scj9_z8SU4dxra6kvTABTJBE8Bwhy");
        argPlayersList[11] = new Players("TOLISSO", "TOLISSO Corentin", "MF",
                "10","5'11", "23", "8",
                "0", "FC Bayern München (GER) ", nation, "1tP8sMcjjJ8E65_PZDg6sVgkLslpbdPfy");
        argPlayersList[12] = new Players("KANTE", "KANTE Ngolo", "MF",
                "10","5'6", "27", "23",
                "1", "Chelsea FC (ENG)", nation, "1N14f_NK6hmU2DCgN6XcLvDLSnCeG9BzQ");
        argPlayersList[13] = new Players("MATUIDI", "MATUIDI Blaise", "MF",
                "10","5'11", "31", "66",
                "9", "Juventus FC (ITA)", nation, "1a6cggRnUjPisQVFNDi082lYU8U9mV8tE");
        argPlayersList[14] = new Players("NZONZI", "NZONZI Steven", "MF",
                "10","6'5", "29", "4",
                "0", "Sevilla FC (ESP)", nation, "117nMv_GV6chBG2FiMF6NUUMyLtDiMEzl");
        argPlayersList[15] = new Players("MANDANDA", "MANDANDA Steve", "GK",
                "10","6'1", "33", "27",
                "0", "Olympique Marseille (FRA) ", nation, "118ivswDp-egna-XL8Uhqt3W4A2NK1KVO");
        argPlayersList[16] = new Players("RAMI", "RAMI Adil", "DF",
                "10","6'3", "32", "35",
                "1", "Olympique Marseille (FRA) ", nation, "1XXF1m-vI2f7vsmeqRvNmXwGVQ5Xvrl-i");
        argPlayersList[17] = new Players("FEKIR", "FEKIR Nabil", "FW",
                "10","5'8", "24", "11",
                "2", "Olympique Lyon (FRA)", nation, "1MBQvPnlXI9ojQmX1RwBowEqY-nN4Udu2");
        argPlayersList[18] = new Players("SIDIBE", "SIDIBE Djibril", "DF",
                "10","6'0", "25", "16",
                "1", "AS Monaco (FRA)", nation, "1waOQ7r1_QqjwKHPMT7PWzamOh4HNdXbQ");
        argPlayersList[19] = new Players("THAUVIN", "THAUVIN Florian", "FW",
                "10","5'10", "25", "4",
                "0", "Olympique Marseille (FRA)", nation, "1KC1fLMIpTs34ONZlbEV3tcXVWh5r8KVi");
        argPlayersList[20] = new Players("HERNANDEZ", "HERNANDEZ Lucas", "DF",
                "10","6'0", "22", "4",
                "0", "Atletico Madrid (ESP)", nation, "1t4KIkhm4ISvLbNSRfdiUtTpAMYureCDx");
        argPlayersList[21] = new Players("MENDY", "MENDY Benjamin", "DF",
                "10","6'1", "23", "6",
                "0", "Manchester City FC (ENG)", nation, "1QqsxcZfgxuIJDfNkFtGSjZD2W_a-_R3I");
        argPlayersList[22] = new Players("AREOLA", "AREOLA Alphonse", "GK",
                "10","6'5", "25", "0",
                "0", "Paris Saint-Germain FC (FRA)", nation, "1uC8wRAx-seJg1ffUoCrA_Bx-vn4KPuJE");
        return argPlayersList;
    }

    public Players[] initGermanyPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Germany";
        argPlayersList[0] = new Players("NEUER", "NEUER Manue", "GK",
                "10","6'4", "32", "75",
                "0", "FC Bayern München (GER)", nation, "143paUnjD-68hlMrEu_Tulurz4APbsFF0");
        argPlayersList[1] = new Players("PLATTENHARDT", "PLATTENHARDT Marvin", "DF",
                "10","5'11", "26", "6",
                "0", "Hertha BSC (GER) ", nation, "1dfIMvrCpPxb7pXgR7O1VjkzGrTtYWYYk");
        argPlayersList[2] = new Players("HECTOR", "HECTOR Jonas", "DF",
                "10","6'1", "28", "37",
                "3", "1. FC Köln (GER)", nation, "15TVeIrty8tNM5TAFZIw96NApLuuX1sLC");
        argPlayersList[3] = new Players("GINTER", "GINTER Matthias", "DF",
                "10","6'2", "24", "17",
                "0", "VfL Borussia Mönchengladbach (GER)", nation, "1w1z8BnrqAxpGJejqExjTzRg1J2loPZZt");
        argPlayersList[4] = new Players("HUMMELS", "HUMMELS Mats", "DF",
                "10","6'3", "29", "63",
                "5", "FC Bayern München (GER)", nation, "1AWTbo2gVcaDtINtjYsmj8fgcoA4hazsT");
        argPlayersList[5] = new Players("KHEDIRA", "KHEDIRA Sami", "MF",
                "10","6'2", "31", "74",
                "7", "Juventus FC (ITA)", nation, "1UwNG3VQ_qK4vFqdKKqKytAmNMylViPhh");
        argPlayersList[6] = new Players("DRAXLER", "DRAXLER Julian", "MF",
                "10","6'2", "24", "43",
                "6", "Paris Saint-Germain FC (FRA)", nation, "1s9yHzu_wNk3KwSiyzyPkLYzhuLcK1S65");
        argPlayersList[7] = new Players("KROOS", "KROOS Toni", "MF",
                "10","6'0", "28", "82",
                "12", "Real Madrid CF (ESP)", nation, "1ZIXhxtcGnHnEBtbOZwJ5Rw3umD_6ie2T");
        argPlayersList[8] = new Players("WERNER", "WERNER Timo", "FW",
                "10","5'11", "22", "13",
                "7", "RB Leipzig (GER)", nation, "1jk93gQ4pezSEQCOV2xGapyTNhdVnVWmR");
        argPlayersList[9] = new Players("OZIL", "OEZIL Mesut", "MF",
                "10","5'11", "29", "90",
                "23", "Arsenal FC (ENG)", nation, "1rK68kzfSd_AjZg0jUz_ne1WzgDpSBRSd");
        argPlayersList[10] = new Players("REUS", "REUS Marco", "FW",
                "10","5'11", "29", "30",
                "9", "Borussia Dortmund (GER)", nation, "1TubfdBD72i6QxpA-oDprBEGexpoFQ1DP");
        argPlayersList[11] = new Players("TRAPP", "TRAPP Kevin", "GK",
                "10","6'2", "27", "3",
                "0", "Paris Saint-Germain FC (FRA)", nation, "1E_19mHgARkYwBfbDWFRCrPjgg0zGSgNt");
        argPlayersList[12] = new Players("MULLER", "MUELLER Thomas", "MF",
                "10","6'1", "28", "90",
                "38", "FC Bayern München (GER) ", nation, "1xBNIxOorY0JH30xXu0GNAzgwrn3zll_a");
        argPlayersList[13] = new Players("GORETZKA", "GORETZKA Leon", "MF",
                "10","6'2", "23", "15",
                "6", "FC Schalke 04 (GER)", nation, "1DFES4rw3Gm5AnbiQZnTwfriq5AypetiG");
        argPlayersList[14] = new Players("SUELE", "SUELE Niklas", "DF",
                "10","6'5", "22", "10",
                "0", "FC Bayern München (GER)", nation, "1Er1EcH6f37KKwStCtEqG9cseFNj29O34");
        argPlayersList[15] = new Players("RUEDIGER", "RUEDIGER Antonio", "DF",
                "10","6'3", "25", "24",
                "1", "Chelsea FC (ENG)", nation, "13Qf3lvrZGvbIKB5cT1gKQhp8_GVrRpYe");
        argPlayersList[16] = new Players("BOATENG", "BOATENG Jerome", "DF",
                "10","6'4", "29", "70",
                "1", "FC Bayern München (GER)", nation, "1Y--Rn8VW4Bocin_5nWPEMkOCbTQxpBDz");
        argPlayersList[17] = new Players("KIMMICH", "KIMMICH Joshua", "DF",
                "10","5'9", "23", "28",
                "3", "FC Bayern München (GER)", nation, "1tkG2kENHHO0688miZgkpv47DaXjXqxDi");
        argPlayersList[18] = new Players("RUDY", "RUDY Sebastian", "MF",
                "10","5'10", "28", "25",
                "1", "FC Bayern München (GER)", nation, "18wr9sZHR286yXxHmJC3VklZj0eROnXOM");
        argPlayersList[19] = new Players("BRANDT", "BRANDT Julian", "MF",
                "10","6'1", "22", "15",
                "1", "Bayer 04 Leverkusen (GER)", nation, "1RPKpX2QFa_wTQ6H8HOYbcMp1eey_Kct0");
        argPlayersList[20] = new Players("GÜNDOGAN", "GUENDOGAN Ilkay", "MF",
                "10","5'11", "27", "25",
                "4", "Manchester City FC (ENG)", nation, "1WhjXL2rnIPkfZTo8wImBlfuVGpr_-WZr");
        argPlayersList[21] = new Players("TER STEGEN", "TER STEGEN Marc-Andre", "GK",
                "10","6'2", "26", "19",
                "0", "FC Barcelona (ESP)", nation, "1GdbKRNpWNV-nJ6l_H9MUDQ8wecwnWK33");
        argPlayersList[22] = new Players("GOMEZ", "GOMEZ Mario", "FW",
                "10","6'2", "32", "74",
                "31", "VfB Stuttgart (GER)", nation, "18mNBJSzVPnU_nr6Sl5AUj7959iifj0ct");
        return argPlayersList;
    }

    public Players[] initIcelandPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Iceland";
        argPlayersList[0] = new Players("HALLDORSSON", "HALLDORSSON Hannes", "GK",
                "10","6'4", "34", "48",
                "0", "Randers FC (DEN)", nation, "15eIv7IZrGP-0VAaBjtNktBbMBswKjyY6");
        argPlayersList[1] = new Players("SAEVARSSON", "SAEVARSSON Birkir", "DF",
                "10","6'2", "33", "78",
                "1", "Valur Reykjavik (ISL)", nation, "1J5s-S1F37IerUJKk-QtDEOi2yTXfPBWy");
        argPlayersList[2] = new Players("FRIDJONSSON", "FRIDJONSSON Samuel", "MF",
                "10","6'1", "22", "3",
                "0", "Valerenga IF (NOR)", nation, "1KK9C8dmYIIELz_qLDNFJibRc992POsXI");
        argPlayersList[3] = new Players("GUDMUNDSSON", "GUDMUNDSSON Albert", "MF",
                "10","5'9", "20", "4",
                "3", "PSV Eindhoven (NED)", nation, "1OpYUL6_lzs4joJNhpVpBvy3N4x0jRG70");
        argPlayersList[4] = new Players("INGASON", "INGASON Sverrir", "DF",
                "10","6'2", "24", "18",
                "3", "FC Rostov (RUS)", nation, "1drQ0jUv74-v30P65qoB3vvI-NESXa8GW");
        argPlayersList[5] = new Players("R. SIGURDSSON", "SIGURDSSON Ragnar", "DF",
                "10","6'1", "31", "75",
                "3", "FC Rostov (RUS)", nation, "1An-RAjGf9NbsYsTu3Mu_tTICv9JTPYWB");
        argPlayersList[6] = new Players("GUDMUNDSSON", "GUDMUNDSSON Johann", "MF",
                "10","5'10", "27", "66",
                "7", "Burnley FC (ENG)", nation, "1pPlexfexSuKUkuMZtNwafWYH4cJzES6D");
        argPlayersList[7] = new Players("BJARNASON", "BJARNASON Birkir", "MF",
                "10","5'11", "30", "65",
                "9", "Aston Villa FC (ENG) ", nation, "1Z8EFsqU8crUoA4lgDsIgvLsVYAmw3IU_");
        argPlayersList[8] = new Players("SIGURDARSON", "SIGURDARSON Bjorn", "FW",
                "10","6'1", "27", "10",
                "1", "FC Rostov (RUS)", nation, "1ngHvntbSe8Xeer1wHabAuj0mMu5lEuYN");
        argPlayersList[9] = new Players("SIGURDSSON", "SIGURDSSON Gylfi", "MF",
                "10","6'1", "28", "56",
                "19", "Everton FC (ENG)", nation, "1oeJSUnZH2Qg28nlb7WFrlfs-eLIwblm1");
        argPlayersList[10] = new Players("FINNBOGASON", "FINNBOGASON Alfred", "FW",
                "10","6'1", "29", "46",
                "12", "FC Augsburg (GER)", nation, "1FG5CWeJtwlKY3UJVKazZDkjm9Gl3s-iK");
        argPlayersList[11] = new Players("SCHRAM", "SCHRAM Frederik", "GK",
                "10","6'6", "23", "3",
                "0", "Roskilde BK (DEN)", nation, "1hWW-jKbbHrx_cb1jlZo2NuspPD1kj_Jb");
        argPlayersList[12] = new Players("RUNARSSON", "RUNARSSON Runar", "GK",
                "10","6'2", "23", "3",
                "0", "FC Nordsjaelland (DEN)", nation, "1mvc-7XRjS88Ci7uycL6MLs_ALvpiU_5X");
        argPlayersList[13] = new Players("ARNASON", "ARNASON Kari", "DF",
                "10","6'3", "35", "65",
                "4", "Aberdeen FC (SCO)", nation, "1GYcxNU2MWvo00OkFBL_nZoqvaGUpKRm0");
        argPlayersList[14] = new Players("EYJOLFSSON", "EYJOLFSSON Holmar", "DF",
                "10","6'2", "27", "9",
                "1", "Levski Sofia (BUL)", nation, "1Kc59XV1dDP2Hp9BUQbmTYQqE-6xS_CIr");
        argPlayersList[15] = new Players("SKULASON", "SKULASON Olafur", "MF",
                "10","6'0", "35", "35",
                "1", "Karabukspor (TUR)", nation, "1uMu402XpKI54x-68X-at3kG0eq-8Rv6s");
        argPlayersList[16] = new Players("MAGNÚSSON", "MAGNUSSON Hordur", "DF",
                "10","6'3", "25", "15",
                "2", "Bristol City FC (ENG)", nation, "19zvYZM4FTuo1V2EPJa4X4vbbH_J7CpsQ");
        argPlayersList[17] = new Players("GUNNARSSON", "GUNNARSSON Aron", "MF",
                "10","5'10", "29", "77",
                "2", "Cardiff City FC (WAL)", nation, "1bKHTKikvYxRLPFEIo9p4luu5jmsiczA9");
        argPlayersList[18] = new Players("GISLASON", "GISLASON Rurik", "MF",
                "10","6'0", "30", "45",
                "3", "SV Sandhausen (GER)", nation, "1J-ZsD9zjvesycxAM-QCQ4fj8S5W5G03N");
        argPlayersList[19] = new Players("HALLFREDSSON", "HALLFREDSSON Emil", "MF",
                "10","6'1", "1", "62",
                "64", "Udinese Calcio (ITA)", nation, "1q5P2cpoocMMo70ytcd6GuMJagEwm2i9q");
        argPlayersList[20] = new Players("TRAUSTASON", "TRAUSTASON Arnor", "MF",
                "10","6'1", "25", "18",
                "5", "Malmö FF (SWE)", nation, "1VezF3G9C_6hWUaxjm4nH3dwkbF--sbQQ");
        argPlayersList[21] = new Players("BODVARSSON", "BODVARSSON Jon", "FW",
                "10","6'3", "26", "36",
                "2", "Reading FC (ENG)", nation, "1R23m9Zbv6Zhe1_nO7VO5TAhnNQUtsqz9");
        argPlayersList[22] = new Players("A. SKÚLASON", "SKULASON Ari", "DF",
                "10","5'7", "31", "54",
                "0", "KSC Lokeren (BEL)", nation, "1RWYjvfqRl-XQ-xjQ3cIJ-p2Xi3i1Lo_V");
        return argPlayersList;
    }

    public Players[] initIranPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Iran";
        argPlayersList[0] = new Players("A. BEIRANVAND", "BEIRANVAND Ali", "GK",
                "10","6'3", "25", "21",
                "0", "Persepolis FC (IRN)", nation, "1JuySOH-cQPavYLHrTRQ4voT30MLvSqtT");
        argPlayersList[1] = new Players("M. TORABI", "TORABI Mehdi", "MF",
                "10","6'0", "23", "16",
                "4", "Saipa Alborz FC (IRN)", nation, "1Cw5_Xmwob3x6Eq_7ESG7UjUi3XhwcN6l");
        argPlayersList[2] = new Players("HAJI SAFI", "HAJI SAFI Ehsan", "DF",
                "10","5'9", "28", "93",
                "6", "Olympiacos Piraeus FC (GRE) ", nation, "1fubfA7voRbkck_GygszaLvj0kWdohWZl");
        argPlayersList[3] = new Players("R. CHESHMI", "CHESHMI Roozbeh", "DF",
                "10","6'3", "24", "9",
                "1", "Esteghlal Tehran FC (IRN)", nation, "1IoHvVLVfIZthOpXZVgW9Aio1W-yk41ph");
        argPlayersList[4] = new Players("M. MOHAMMADI", "MOHAMMADI Milad", "DF",
                "10","5'8", "24", "18",
                "0", "FC Akhmat Grozny (RUS)", nation, "1lxrREbrB5flKT3lWWyvkAbTtATl0GU8H");
        argPlayersList[5] = new Players("S. EZATOLAHI ", "EZATOLAHI Saeid", "MF",
                "10","6'3", "21", "25",
                "1", "FK Amkar Perm (RUS)", nation, "1vY19VZFsoveVC_b8HpbJPJ5RnCnoboub");
        argPlayersList[6] = new Players("SHOJAEI M.", "SHOJAEI Masoud", "MF",
                "10","6'1", "33", "73",
                "8", "AEK Athens (GRE) ", nation, "1L4d8aOQj2ATjMlqWZFgpyuUeBr5ZiLm_");
        argPlayersList[7] = new Players("M. POURALIGANJI", "POURALIGANJI Morteza", "DF",
                "10","6'1", "26", "26",
                "2", "Al Sadd SC (QAT)", nation, "1EEla-vw4-8RLlDCSI0LrFggcvFlqKbxC");
        argPlayersList[8] = new Players("OMID", "EBRAHIMI Omid", "MF",
                "10","5'10", "30", "29",
                "0", "Esteghlal Tehran FC (IRN)", nation, "12iAiUPYenSyrJAcy2IqrpRf0A_bCkpUA");
        argPlayersList[9] = new Players("KARIM", "ANSARIFARD Karim", "FW",
                "10","6'1", "28", "63",
                "16", "Olympiacos Piraeus FC (GRE) ", nation, "1v5Q--bxs9-T0nQxvyzx8AnQHeVybReRR");
        argPlayersList[10] = new Players("V. AMIRI", "AMIRI Vahid", "MF",
                "10","5'10", "30", "35",
                "1", "Persepolis FC (IRN)", nation, "1IHduk-bDjPiDbhdcUv5l39G8h4RR64li");
        argPlayersList[11] = new Players("RASHID", "MAZAHERI Rashid", "GK",
                "10","6'3", "29", "3",
                "0", "Zob Ahan Isfahan FC (IRN)", nation, "1y_IMJcVXFdczG8nqYjoa0hjVR3AFRZVj");
        argPlayersList[12] = new Players("M.R. KHANZADEH", "KHANZADEH Mohammad Reza", "DF",
                "10","6'1", "27", "11",
                "1", "Padideh FC (IRN)", nation, "1W9Zs7BdSJqXha7gP3ujQS-6InH4ziBwV");
        argPlayersList[13] = new Players("GHODDOS", "GHODDOS Saman", "FW",
                "10","5'9", "24", "7",
                "1", "IFK Östersund FK (SWE)", nation, "1pOl687tt5KsXYY6IhYhag8DDHJF7w2OI");
        argPlayersList[14] = new Players("P. MONTAZERI", "MONTAZERI Pejman", "DF",
                "10","6'1", "34", "46",
                "2", "Esteghlal Tehran FC (IRN)", nation, "1kAhIjV69jKNOoNotXD1--cmkMHlOjl3F");
        argPlayersList[15] = new Players("REZA", "GHOOCHANNEJHAD Reza", "FW",
                "10","5'11", "30", "42",
                "17", "SC Heerenveen (NED) ", nation, "1dPUowFvaAKWTbCIjGQYZAc8o4oZO-USH");
        argPlayersList[16] = new Players("MEHDI", "TAREMI Mehdi", "FW",
                "10","6'1", "25", "25",
                "11", "Al Gharafa SC (QAT", nation, "1YsZDnOdUeXdLTShTWyQkKZCq2JdTXWfv");
        argPlayersList[17] = new Players("A. JAHANBAKHSH", "JAHANBAKHSH Alireza", "FW",
                "10","5'11", "24", "37",
                "4", "AZ Alkmaar (NED)", nation, "11_kF9mvNbX7IalK0GZ_eBWTAofGCfQIA");
        argPlayersList[18] = new Players("M. HOSSEINI", "HOSSEINI Majid", "DF",
                "10","6'0", "21", "1",
                "0", "Esteghlal Tehran FC (IRN) ", nation, "1jdTW2tIoM8QAme5aPePl6quNCjuHHQpW");
        argPlayersList[19] = new Players("SARDAR", "AZMOUN Sardar", "FW",
                "10","6'1", "23", "32",
                "23", "FC Rubin Kazan (RUS)", nation, "1ZTGZVoLIf7pa7JGoiUpkQCD1bGiOn_ej");
        argPlayersList[20] = new Players("DEJAGAH", "DEJAGAH Ashkan", "FW",
                "10","5'11", "31", "45",
                "9", "Nottingham Forest FC (ENG)", nation, "11NliAokH9JEsCvc9IrJQsY_cLbO0HX3_");
        argPlayersList[21] = new Players("ABEDZADEH", "ABEDZADEH Amir", "GK",
                "10","6'1", "25", "1",
                "0", "CS Maritimo (POR)", nation, "1od6WDX-BaYWwoVGEZRBGF63PnMj7Nzg3");
        argPlayersList[22] = new Players("RAMIN", "REZAEIAN Ramin", "DF",
                "10","6'1", "28", "27",
                "2", "KV Oostende (BEL)", nation, "12mrl6uK5k3gT_f60QnlYl1bQE18Z0GcM");
        return argPlayersList;
    }

    public Players[] initJapanPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Japan";
        argPlayersList[0] = new Players("KAWASHIMA", "KAWASHIMA Eiji", "GK",
                "10","6'1", "35", "82",
                "0", "FC Metz (FRA)", nation, "1eQ3qIvvrPI659ntIv3W4937_PF-_7heY");
        argPlayersList[1] = new Players("UEDA", "UEDA Naomichi", "DF",
                "10","6'1", "23", "3",
                "0", "Kashima Antlers (JPN)", nation, "1NgLiL7gGUpb2RXUlp92N0T0RVrD-iIxB");
        argPlayersList[2] = new Players("SHOJI", "SHOJI Gen", "DF",
                "10","5'11", "25", "11",
                "1", "Kashima Antlers (JPN)", nation, "1WmVfWZ3HyVC22OTUpABVAosLMZd61u_p");
        argPlayersList[3] = new Players("HONDA", "HONDA Keisuke", "MF",
                "10","5'11", "31", "94",
                "36", "CF Pachuca (MEX)", nation, "1VKw5OGsx_m2R-GjCsQstemoeUOENdydA");
        argPlayersList[4] = new Players("NAGATOMO", "NAGATOMO Yuto", "DF",
                "10","5'7", "31", "103",
                "3", "Galatasaray SK (TUR)", nation, "1hb241o6QX1KqeMNoeS3usu9TNntjvuYl");
        argPlayersList[5] = new Players("ENDO", "ENDO Wataru", "DF",
                "10","5'10", "25", "11",
                "0", "Urawa Reds (JPN) ", nation, "1_2cD0iqrjvLbpM6S2uhl75vJphEeC699");
        argPlayersList[6] = new Players("SHIBASAKI", "SHIBASAKI Gaku", "MF",
                "10","5'9", "26", "15",
                "3", "Getafe CF (ESP)", nation, "10WiDR3GPQXqRu-52IgGZCT-qTuVjnVQo");
        argPlayersList[7] = new Players("HARAGUCHI", "HARAGUCHI Genki", "MF",
                "10","5'10", "27", "30",
                "6", "Fortuna Düsseldorf (GER)", nation, "1mKJ_g3z1sdXSmJO-3Q4v1MalRocjLzDD");
        argPlayersList[8] = new Players("OKAZAKI", "OKAZAKI Shinji", "FW",
                "10","5'9", "32", "112",
                "50", "Leicester City FC (ENG)", nation, "1pyIkaEtlrjM0K5CC3n1rq4BwzjHoAlJR");
        argPlayersList[9] = new Players("USAMI", "USAMI Takashi", "MF",
                "10","5'10", "26", "21",
                "3", "Fortuna Düsseldorf (GER)", nation, "1AkuQs2N59V6wFPRvJoeq8iovTKbUnA3E");
        argPlayersList[10] = new Players("KAGAWA", "KAGAWA Shinji", "MF",
                "10","5'8", "29", "89",
                "29", "Borussia Dortmund (GER)", nation, "1ocvGFZxy6UDuxbBJ0_jDZcTejIF75VSv");
        argPlayersList[11] = new Players("HIGASHIGUCHI", "HIGASHIGUCHI Masaaki", "GK",
                "10","6'1", "32", "4",
                "0", "Gamba Osaka (JPN)", nation, "1EmYA_E-IZDWYYlQlOSWAaaL8jim0G7vn");
        argPlayersList[12] = new Players("MUTO", "MUTO Yoshinori", "FW",
                "10","5'10", "25", "21",
                "2", "FSV Mainz 05 (GER)", nation, "1ZcjEO4cOlwhC97exAfGkciy2VVscSLF2");
        argPlayersList[13] = new Players("INUI", "INUI Takash", "MF",
                "10","5'7", "30", "25",
                "2", "SD Eibar (ESP)", nation, "1fTU-CsP5EWKIZ72QFYYVZgw-yCOihmuW");
        argPlayersList[14] = new Players("OSAKO", "OSAKO Yuya", "FW",
                "10","6'0", "28", "26",
                "7", "1. FC Köln (GER)", nation, "1JkDE43BJEH1oKTequPgyrZI54jGOMyOQ");
        argPlayersList[15] = new Players("YAMAGUCHI", "YAMAGUCHI Hotaru", "MF",
                  "10","5'8", "27", "40",
                "2", "Cerezo Osaka (JPN)", nation, "1SfmEtvrQ1rHTGQGjBK5DwYEmKXB9axAe");
        argPlayersList[16] = new Players("HASEBE", "HASEBE Makoto", "MF",
                "10","5'11", "34", "108",
                "2", "Eintracht Frankfurt (GER)", nation, "1ZvsNc9OEIZ_HE5h2Zyhe0O1fOqdLlQEs");
        argPlayersList[17] = new Players("OSHIMA", "OSHIMA Ryota", "MF",
                "10","5'6", "25", "3",
                "0", "Kawasaki Frontale (JPN)", nation, "1JgLkxfZWIEEnNydGjYWJOkRKyYY7Uz4E");
        argPlayersList[18] = new Players("H. SAKAI ", "SAKAI Hirok", "DF",
                "10","6'0", "28", "41",
                "0", "Olympique Marseille (FRA) ", nation, "1K6c4HZ52T2ITULdHfGkcNDoTrq2N2Y8y");
        argPlayersList[19] = new Players("MAKINO", "MAKINO Tomoak", "DF",
                "10","6'0", "31", "30",
                "4", "Urawa Reds (JPN)", nation, "1GUCiBNZuObIrYIM_7j01z51F9MEFndle");
        argPlayersList[20] = new Players("G. SAKAI", "SAKAI Gotoku", "DF",
                "10","5'9", "27", "38",
                "0", "Hamburger SV (GER)", nation, "1dikbUYHDBA8O7kFbnuaZhn0NRFmuFR5K");
        argPlayersList[21] = new Players("YOSHIDA", "YOSHIDA Maya", "DF",
                "10","6'2", "29", "80",
                "10", "Southampton FC (ENG)", nation, "1Uz27R2wfd72aB2VqLBsqn8YJe_hHyHJG");
        argPlayersList[22] = new Players("NAKAMURA", "NAKAMURA Kosuke", "GK",
                "10","6'1", "23", "3",
                "0", "Kashiwa Reysol (JPN)", nation, "167ZM4IZiFPenXSRcriE7xGFSzi_shvGa");
        return argPlayersList;
    }

    public Players[] initKoreaRepublicPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Korea Republic / South Korea";
        argPlayersList[0] = new Players("S G KIM", "KIM Seunggyu", "GK",
                "10","6'1", "27", "32",
                "0", "Vissel Kobe (JPN)", nation, "1vl7de9TSkBjE36_JZwFKbswln9z9DL8U");
        argPlayersList[1] = new Players("Y LEE", "LEE Yong", "DF",
                "10","5'11", "31", "26",
                "0", "Jeonbuk Hyundai (KOR)", nation, "18-6tF5txJTeW96_KjdatUgiPljmgZC6P");
        argPlayersList[2] = new Players("S H JUNG", "JUNG Seunghyun", "DF",
                "10","6'2", "24", "6",
                "0", "Sagan Tosu FC (JPN) ", nation, "1jWOp0nfz9yndf1qvzQadWYCX9ZClJWD0");
        argPlayersList[3] = new Players(" B S OH", "OH Bansuk", "DF",
                "10","6'2", "30", "2",
                "0", "Jeju United (KOR)", nation, "1ZbFVn1TL5WFPCkudTF5HXj6gSk2lvzcF");
        argPlayersList[4] = new Players("Y S YUN", "YUN Youngsun", "DF",
                "10","6'1", "29", "5",
                "0", "Seongnam FC (KOR)", nation, "1v06ejjcfhJyvA1viv2I3WXKwQfqwZL9A");
        argPlayersList[5] = new Players("J H PARK", "PARK Jooho", "DF",
                "10","5'9", "31", "35",
                "0", "Ulsan Hyundai FC (KOR)", nation, "1ErIq4NJ9Br5l5aHSUGLRyUkyjtM2P5aD");
        argPlayersList[6] = new Players("H M SON", "SON Heungmin", "FW",
                "10","6'0", "25", "65",
                "21", "Tottenham Hotspur FC (ENG)", nation, "1Wc-xzaTBYCsZ4ShCJGyu6Ult4gb1ifu8");
        argPlayersList[7] = new Players("S J JU", "JU Sejong", "MF",
                "10","5'9", "27", "10",
                "1", "Asan Mugunghwa FC (KOR)", nation, "1l9XB05zsOQ8p7p_MaAESP1H1QARwWcIG");
        argPlayersList[8] = new Players("S W KIM", "KIM Shinwook", "FW",
                "10","6'5", "30", "48",
                "10", "Jeonbuk Hyundai (KOR)", nation, "1JCLwtyjypb_JiOojXg7-K5Qv_rsVvUO0");
        argPlayersList[9] = new Players("S W LEE", "LEE Seungwoo", "MF",
                "10","5'7", "20", "2",
                "0", "Hellas Verona FC (ITA)", nation, "1EyXyUrsrWE3fgyjerIaikT--yYLvkKbT");
        argPlayersList[10] = new Players("H C HWANG", "HWANG Heechan", "FW",
                "10","5'9", "22", "13",
                "2", "FC Red Bull Salzburg (AUT)", nation, "1O9mabHRc4Si7rtR_nKHbH4uSMgLNG0uB");
        argPlayersList[11] = new Players("M W KIM", "KIM Minwoo", "DF",
                "10","5'7", "28", "18",
                "1", "Sangju Sangmu FC (KOR)", nation, "16sHp1Os7L1STHTRd9zIXIFlXG7eLL-aR");
        argPlayersList[12] = new Players("J C KOO", "KOO Jacheol", "MF",
                "10","6'0", "29", "66",
                "19", "FC Augsburg (GER)", nation, "1gHgR8BA_nVSAZq6hi1SB32urPQoS8a0s");
        argPlayersList[13] = new Players("C HONG", "HONG Chul", "DF",
                "10","5'9", "27", "14",
                "0", "Sangju Sangmu FC (KOR)", nation, "1VV7OW0VLIGhIWP7Ri3i7iBAKBlnmbqFB");
        argPlayersList[14] = new Players("W Y JUNG", "JUNG Wooyoung", "MF",
                "10","6'1", "28", "124",
                "64", "Vissel Kobe (JPN)", nation, "1yfaKboKe-6fVAToCw2UKTjqarK8_m2EM");
        argPlayersList[16] = new Players("S Y KI", "KI Sungyueng", "MF",
                "10","6'2", "29", "100",
                "10", "Swansea City AFC (WAL)", nation, "1pFx1uA-TlsBcRH-w0SlyY_ap0UXhRklP");
        argPlayersList[17] = new Players("J S LEE", "LEE Jaesung", "MF",
                "10","5'11", "25", "33",
                "6", "Jeonbuk Hyundai (KOR)", nation, "1CC4d6QMfV5z87JH_HPogpCuHbfgNi32U");
        argPlayersList[18] = new Players("S M MOON", "MOON Seonmin", "MF",
                "10","5'7", "25", "2",
                "1", "Incheon United FC (KOR)", nation, "1jrCJfsj4Tm4tIkgFy2zLhZzrMaKyW2AS");
        argPlayersList[15] = new Players("Y G KIM", "KIM Younggwon", "DF",
                "10","6'1", "28", "51",
                "2", "Guangzhou Evergrande FC (CHN)", nation, "1ExSYVg2R3msgYYN1Z0_Ut0IQTOUb7RVm");
        argPlayersList[19] = new Players("H S JANG", "JANG Hyunsoo", "DF",
                "10","6'1", "26", "49",
                "3", "FC Tokyo (JPN)", nation, "1BhhQywRgKRthqx7ecAOt7TQtHOX0fEGs");
        argPlayersList[20] = new Players("J H KIM", "KIM Jinhyeon", "GK",
                "10","6'3", "30", "14",
                "0", "Cerezo Osaka (JPN)", nation, "1_z2OgUVGuOdPsAaYuHEcNqbaf2bS0Cib");
        argPlayersList[21] = new Players("Y H GO", "GO Yohan", "DF",
                "10","5'7", "30", "19",
                "0", "FC Seoul (KOR)", nation, "11PfZfulGk5A9PLrCIpxQPfoLa3KHLMa8");
        argPlayersList[22] = new Players("H W JO", "JO Hyeonwoo", "GK",
                "10","6'2", "26", "5",
                "0", "Daegu FC (KOR)", nation, "1efKwzjtKyQEZSm-mzDhF3EI1TudNFWj9");
        return argPlayersList;
    }

    public Players[] initMexicoPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Mexico";
        argPlayersList[0] = new Players("J. CORONA", "CORONA Jose", "GK",
                "10","6'0", "37", "52",
                "0", "Cruz Azul (MEX)", nation, "1rr7cfXCXudGXayIQ5Mt21h3afjLmeOQx");
        argPlayersList[1] = new Players("H. AYALA", "AYALA Hugo", "DF",
                "10","6'0", "31", "42",
                "1", "Tigres UANL (MEX)", nation, "1XGwNZpUdvrsfVHqkMSkrrTlAqktTIlMj");
        argPlayersList[2] = new Players("C. SALCEDO", "SALCEDO Carlos", "DF",
                "10","6'1", "24", "20",
                "0", "Eintracht Frankfurt (GER)", nation, "1qWEuUX1HuksvqHtzn-Hd3kg5OzdLlJXY");
        argPlayersList[3] = new Players("D. REYES", "REYES Diego", "DF",
                "10","6'2", "25", "53",
                "1", "FC Porto (POR)", nation, "1WG-D5RGQBZKOX15JQJ0eSETRYaSooilt");
        argPlayersList[4] = new Players("R. MÁRQUEZ", "MARQUEZ Rafael", "DF",
                "10","6'0", "39", "143",
                "19", "CF Atlas (MEX)", nation, "1zFASmiHHc_xKI4pQaCplFJE3V1TqhfU0");
        argPlayersList[5] = new Players("J. DOS SANTOS", "DOS SANTOS Jonathan", "MF",
                "10","5'8", "28", "35",
                "0", "LA Galaxy (USA)", nation, "1K2kpObAKvzfwP8eeEBUVnxb81S5yirDy");
        argPlayersList[6] = new Players("M. LAYÚN", "LAYUN Miguel", "MF",
                "10","5'10", "29", "63",
                "6", "Sevilla FC (ESP)", nation, "1bIdvr2DUBIcdMyvrvyj1ctLrMjk9aKxq");
        argPlayersList[7] = new Players("M. FABIÁN", "FABIAN Marco", "FW",
                "10","5'7", "28", "38",
                "9", "Eintracht Frankfurt (GER)", nation, "1e1OPqfvWlzphpYMZiWWhC_gVLe31Iosy");
        argPlayersList[8] = new Players("RAÚL", "JIMENEZ Raul", "FW",
                "10","6'2", "27", "63",
                "13", "SL Benfica (POR)", nation, "1vTySU-L90s9A_JpLzUjtjWgPNvqejkVC");
        argPlayersList[9] = new Players("G. DOS SANTOS", "DOS SANTOS Giovani", "MF",
                "10","5'9", "29", "104",
                "19", "LA Galaxy (USA)", nation, "1-zj-AkOpMmuAhpsqnKJP2pUVKMi761q1");
        argPlayersList[10] = new Players("CARLOS V", "VELA Carlos", "FW",
                "10","5'10", "29", "68",
                "18", "Los Angeles FC (USA)", nation, "13JXJ2-YWh-6bdqtmFk-VtHL02sQXLPLw");
        argPlayersList[11] = new Players("A. TALAVERA", "TALAVERA Alfredo", "GK",
                "10","6'2", "35", "27",
                "0", "Deportivo Toluca FC (MEX)", nation, "18A-ACwpPjYqrN8E0vFOz45OJZi4o0YGM");
        argPlayersList[12] = new Players("G. OCHOA", "OCHOA Guillermo", "GK",
                "10","6'0", "32", "93",
                "0", "Standard Liège (BEL)", nation, "1K8GtOCZpoFtYq1LNEVM5NOMl2d9Mb8Cn");
        argPlayersList[13] = new Players("J. HERNÁNDEZ", "HERNANDEZ Javier", "FW",
                "10","5'9", "30", "101",
                "49", "West Ham United FC (ENG)", nation, "19rfAbsWBlJSJVdi_YJ9c6IfWfbCrPWJI");
        argPlayersList[14] = new Players("H. MORENO", "MORENO Hector", "DF",
                "10","6'0", "30", "91",
                "3", "Real Sociedad (ESP)", nation, "1po4oB_vYlf4gqy2e_k9FlEzYEP6lyc7i");
        argPlayersList[15] = new Players("H. HERRERA", "HERRERA Hector", "DF",
                "10","5'11", "28", "65",
                "5", "FC Porto (POR)", nation, "1fD-WcA09YkYof7Q2ThI60qW7OC6PCJMT");
        argPlayersList[16] = new Players("JESÚS C.", "CORONA Jesus", "MF",
                "10","5'8", "25", "35",
                "7", "FC Porto (POR)", nation, "10vflfDYvJxwNQ9Fcjhce7Tk-dZt6eKZu");
        argPlayersList[17] = new Players("A. GUARDADO", "GUARDADO Andres", "MF",
                "10","5'7", "31", "144",
                "25", "Real Betis (ESP)", nation, "1_hDN7hImtfTFJGT258oPwoSuFO-JGTpC");
        argPlayersList[18] = new Players("O. PERALTA", "PERALTA Oribe", "FW",
                "10","5'10", "34", "16",
                "11", "Club América (MEX)", nation, "1Q0nOMyNpFKTPeHMkgeOntpHGnkopq1pV");
        argPlayersList[19] = new Players("J. AQUINO", "AQUINO Javier", "MF",
                "10","5'5", "28", "49",
                "0", "Tigres UANL (MEX)", nation, "1bUPNC-TfCq4rzbwSylqH4Rs6dtNtlpPl");
        argPlayersList[20] = new Players("E. ÁLVAREZ", "ALVAREZ Edson", "DF",
                "10","6'3", "20", "11",
                "1", "Club América (MEX) ", nation, "1JPOUAQtljbkvA95T4NIk2JIBH1eipp-O");
        argPlayersList[21] = new Players("H. LOZANO", "LOZANO Hirving", "FW",
                "10","5'10", "22", "26",
                "7", "PSV Eindhoven (NED)", nation, "1ifo0OcEbk1n6ciiGSBzltupOutDANniC");
        argPlayersList[22] = new Players("J. GALLARDO", "GALLARDO Jesus", "MF",
                "10","5'10", "23", "22",
                "0", "Pumas UNAM (MEX)", nation, "15-DLyt9VDaiE46aLoPnsqLYfJo6EI0vx");
        return argPlayersList;
    }

    public Players[] initMoroccoPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Morocco";
        argPlayersList[0] = new Players("BOUNOU", "BOUNOU Yassine", "GK",
                "10","6'5", "27", "11",
                "0", "Girona FC (ESP)", nation, "1VAMrbmyCApGq567NusrEHX2AyqwaSiag");
        argPlayersList[1] = new Players("HAKIMI", "HAKIMI Achraf", "DF",
                "10","5'10", "19", "9",
                "1", "Real Madrid CF (ESP)", nation, "1qikagTRnP25ho3inY6Bqs4dZw8GZYKKZ");
        argPlayersList[2] = new Players("MENDYL", "MENDYL Hamza", "DF",
                "10","5'10", "20", "6",
                "0", "Lille OSC (FRA)", nation, "1aoNfaVlAv860NFv31UH_xw0IBWKXd6Dx");
        argPlayersList[3] = new Players("DA COSTA", "DA COSTA Manue", "DF",
                "10","6'4", "32", "25",
                "1", "Istanbul Başakşehir FK (TUR)", nation, "1KWmWGZAol65Rhj1pjP8gm8U5uET2YnPT");
        argPlayersList[4] = new Players("BENATIA", "BENATIA Mehdi", "DF",
                "10","6'2", "31", "56",
                "2", "Juventus FC (ITA)", nation, "1dVpO0kaWxJrxWkdiQ3qF3VSUYTl7pQV1");
        argPlayersList[5] = new Players("SAISS", "SAISS Romain", "DF",
                "10","6'3", "28", "18",
                "1", "Wolverhampton Wanderers FC (ENG)", nation, "1WcKVAlZdyZo9HTo6ZYjsJOH9cQs3_qja");
        argPlayersList[6] = new Players("CARCELA", "CARCELA Mehdi", "MF",
                "10","5'10", "28", "19",
                "1", "Standard Liège (BEL)", nation, "1GPL7XRgHUircPXx9WstfvPvptaXSboh5");
        argPlayersList[7] = new Players("ZIYACH", "ZIYACH Hakim", "MF",
                "10","5'11", "25", "15",
                "8", "AFC Ajax (NED)", nation, "1UICc61pLTQy1AqKvgEeS2qi_JHi_i6UR");
        argPlayersList[8] = new Players("EL AHMADI", "EL AHMADI Karim", "MF",
                "10","6'1", "33", "44",
                "1", "Feyenoord Rotterdam (NED)", nation, "1pen6MbcrnMmeNCFVe20gZkfQrcYLQdbs");
        argPlayersList[9] = new Players("EL KAABI", "EL KAABI Ayoub", "FW",
                "10","5'11", "24", "3",
                "2", "RS Berkane (MAR)", nation, "1QoeWJSrUE7Nb316TMZ38E6siMbqfOtqv");
        argPlayersList[10] = new Players("BELHANDA", "BELHANDA Younes", "MF",
                "10","5'10", "28", "44",
                "3", "Galatasaray SK (TUR)", nation, "18Rqa1JDytrXY_ebDk11utOlRSPvzbFRj");
        argPlayersList[11] = new Players("FAJR", "FAJR Faycal", "MF",
                "10","5'10", "29", "17",
                "2", "Getafe CF (ESP)", nation, "1WG561mj3nK-F6cfHwJojw6GxtozL5Oxn");
        argPlayersList[12] = new Players("EL KAJOUI", "EL KAJOUI Monir", "GK",
                "10","6'3", "29", "26",
                "0", "CD Numancia (ESP)", nation, "1oYKYk-F0AFRfJBISk4nUYn7qL9FTKi_F");
        argPlayersList[13] = new Players("BOUTAIB", "BOUTAIB Khalid", "FW",
                "10","6'3", "31", "15",
                "7", "Yeni Malatyaspor (TUR)", nation, "1x8eVXbP0KILvhybhyfaDTBins65ZPAiC");
        argPlayersList[14] = new Players("BOUSSOUFA", "BOUSSOUFA Mbark", "MF",
                "10","5'5", "33", "58",
                "7", "Al Jazira (UAE)", nation, "1kc-enKQDmL7NHmpsJdt9Ir_xeKeMP0mK");
        argPlayersList[15] = new Players("AIT BENNASSER", "AIT BENNASSER Youssef", "MF",
                "10","6'0", "21", "124",
                "64", "SM Caen (FRA)", nation, "1naOG9ZpltgLfyOXfOWOzdIy3QPRiDJno");
        argPlayersList[16] = new Players("AMRABAT", "AMRABAT Noureddine", "MF",
                "10","5'10", "31", "23",
                "4", "CD Leganés (ESP)", nation, "1n27lEjeiWazzZrtrl8QYuWQzzHOCbCQh");
        argPlayersList[17] = new Players("DIRAR", "DIRAR Nabil", "DF",
                "10","6'2", "32", "26",
                "3", "Fenerbahce SK (TUR)", nation, "1lUqSIO7hH9nVYsQriuM_QH7HwxBeL0s-");
        argPlayersList[18] = new Players("HARIT", "HARIT Amine", "MF",
                "10","5'10", "20", "3",
                "0", "FC Schalke 04 (GER)", nation, "1_Ue4EAkppOk4sbP5CJyWIfPjV9K8YJ73");
        argPlayersList[19] = new Players("EN NESYRI", "EN NESYRI Youssef", "FW",
                "10","6'3", "21", "15",
                "1", "Malaga CF (ESP)", nation, "1rJTi7BNyCskq0QewBSwUZeZgACz1ZtLj");
        argPlayersList[20] = new Players("BOUHADDOUZ", "BOUHADDOUZ Aziz", "FW",
                "10","6'2", "31", "12",
                "3", "FC St. Pauli (GER)", nation, "1Zf95HHi1gjFusSXvHNg1Q4JTYr3wZ6DP");
        argPlayersList[21] = new Players("AMRABAT", "AMRABAT Sofyan", "MF",
                "10","5'8", "21", "5",
                "0", "Feyenoord Rotterdam (NED)", nation, "1ExMRlbKiJ7EPx0U4cmqjTtVVA850xtOe");
        argPlayersList[22] = new Players("TAGNAOUTI", "TAGNAOUTI Ahmed", "GK",
                "10","5'11", "22", "1",
                "0", "IRT Tanger (MAR) ", nation, "1c-iLQUl5v2XWIlx2id2EtqcaZGMeAHdw");
        return argPlayersList;
    }

    public Players[] initNigeriaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Nigeria";
        argPlayersList[0] = new Players("EZENWA", "EZENWA Ikechukwu", "GK",
                "10","6'1", "29", "24",
                "0", "Enyimba FC (NGA)", nation, "1fx_wcDY4XnODxFOtFVRjLYsOl5O9i0ZT");
        argPlayersList[1] = new Players("IDOWU", "IDOWU Bryan", "DF",
                "10","5'11", "26", "4",
                "1", "FK Amkar Perm (RUS)", nation, "1Gn5_PFpshUEQbjmQkTDTOqOP5p0n5aKl");
        argPlayersList[2] = new Players("ECHIEJILE", "ECHIEJILE Elderson", "DF",
                "10","6'0", "30", "59",
                "2", "Cercle Brugge (BEL)", nation, "1JiN48gQeK3kDhYswYV6cJhxftdAt6RI0");
        argPlayersList[3] = new Players("NDIDI", "NDIDI Onyinye", "MF",
                "10","6'2", "21", "16",
                "0", "Leicester City FC (ENG)", nation, "1tv1_u0m4gTwcRu150X0-1bzuhB6gebaG");
        argPlayersList[4] = new Players("EKONG", "EKONG William", "DF",
                "10","6'3", "24", "21",
                "1", "Bursaspor (TUR)", nation, "1wON7QkOdDPq7c8YvsCwatO5x_hPpF5px");
        argPlayersList[5] = new Players("BALOGUN", "BALOGUN Leon", "DF",
                "10","6'3", "29", "18",
                "0", "FSV Mainz 05 (GER)", nation, "1oWMP6e-v-_lMLcTP9gjTw3yo8glvOUgY");
        argPlayersList[6] = new Players("MUSA", "MUSA Ahmed", "FW",
                "10","5'7", "25", "71",
                "13", "CSKA Moscow (RUS)", nation, "1Kbdjytv7H-tLTpRZpLvYjR3xoHqW4Xlr");
        argPlayersList[7] = new Players("ETEBO", "ETEBO Oghenekaro", "MF",
                "10","5'7", "22", "14",
                "1", "UD Las Palmas (ESP)", nation, "1qLXmfZwcU7gH2tq8IES2QT9gJEj23ekL");
        argPlayersList[8] = new Players("IGHALO", "IGHALO Odion", "FW",
                "10","5'11", "28", "19",
                "4", "Changchun Yatai (CHN)", nation, "1jJIDyZR0Ny4iOYvGnkx_TqY4UgAgkese");
        argPlayersList[9] = new Players("MIKEL", "MIKEL John Obi", "MF",
                "10","6'2", "31", "84",
                "6", "Tianjin Teda (CHN)", nation, "1xm_lWf0Y9DK2smJCApgMvnGrqQ9m1KQW");
        argPlayersList[10] = new Players("MOSES", "MOSES Victor", "FW",
                "10","5'10", "27", "33",
                "11", "Chelsea FC (ENG)", nation, "1t4SXhjWk3yrvUBQ4MSvdmA_kE4f-6zAX");
        argPlayersList[11] = new Players("SHEHU", "SHEHU Abdullahi", "DF",
                "10","5'7", "25", "24",
                "0", "Bursaspor (TUR)", nation, "1m36sCDtZ1oxfzQSh03UZQyxjO74JzNL6");
        argPlayersList[12] = new Players("NWANKWO", "NWANKWO Simeon", "FW",
                "10","6'6", "26", "1",
                "0", "FC Crotone (ITA)", nation, "1L7MR6EYVDKIfSmZMRA2nlAFYjv9DzSpH");
        argPlayersList[13] = new Players("IHEANACHO", "IHEANACHO Kelechi", "FW",
                "10","6'2", "22", "17",
                "8", "Leicester City FC (ENG)", nation, "1MgCXQmucDGYDlyLuPLc4F0Zpn1jE40sG");
        argPlayersList[14] = new Players("OBI", "OBI Joel", "MF",
                "10","5'9", "27", "17",
                "0", "Torino FC (ITA)", nation, "16Br5-UMfeamUQrZVWzzscMzR3AoPHNGH");
        argPlayersList[15] = new Players("AKPEYI", "AKPEYI Daniel", "GK",
                "10","6'2", "32", "7",
                "0", "Chippa United FC (RSA) ", nation, "1ZYuneZUKFSSXMjAQ8iQHmYDi6zux2krC");
        argPlayersList[16] = new Players("ONAZI", "ONAZI Ogenyi", "MF",
                "10","5'8", "25", "52",
                "1", "Trabzonspor (TUR)", nation, "1-TsfQd_6JXscfyAkBer1Il_vGHG_8gt7");
        argPlayersList[17] = new Players("IWOBI", "IWOBI Alex", "FW",
                "10","5'11", "22", "18",
                "5", "Arsenal FC (ENG)", nation, "11fpdzvN5JadmQ4_pC2G3zKvtYJJNSHBP");
        argPlayersList[18] = new Players("OGU", "OGU John", "MF",
                "10","6'3", "30", "19",
                "2", "Hapoel Be'er Sheva FC (ISR)", nation, "1tLSkRWku5rv-7fL-53_5mb6IcApurog0");
        argPlayersList[19] = new Players("AWAZIEM", "AWAZIEM Chidozie", "DF",
                "10","6'2", "21", "0",
                "0", "FC Nantes (FRA)", nation, "1oMQl7JLW8HUaj7R7bRpfNJstEYeG4VIB");
        argPlayersList[20] = new Players("EBUEHI", "EBUEHI Tyronne", "DF",
                "10","6'1", "22", "6",
                "0", "ADO Den Haag (NED)", nation, "17BPuiV86W78-OuNm6tpcVewjrAyF-gv6");
        argPlayersList[21] = new Players("OMERUO", "OMERUO Kenneth", "DF",
                "10","6'1", "24", "38",
                "0", "Kasimpasa SK (TUR)", nation, "1MiV7pHdGiX0RYip962_rCVXRoKYhoexx");
        argPlayersList[22] = new Players("UZOHO", "UZOHO Francis", "GK",
                "10","6'5", "19", "5",
                "0", "Deportivo La Coruña (ESP)", nation, "174IDUzrfNEP2Lh0Q09GZe-qmDwa8WjI-");
        return argPlayersList;
    }

    public Players[] initPanamaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Panama";
        argPlayersList[0] = new Players("PENEDO", "PENEDO Jaime", "GK",
                "10","6'1", "36", "129",
                "0", "Dinamo Bucharest (ROU)", nation, "1FBNReQVtLzZ0GrTj4NdvCqv4cd-TtrYJ");
        argPlayersList[1] = new Players("MURILLO", "MURILLO Michael", "DF",
                "10","6'0", "22", "21",
                "2", "New York Red Bulls (USA)", nation, "127hnH2so5QX1TnrZhEFr1jlm2u3WBSWV");
        argPlayersList[2] = new Players("CUMMINGS", "CUMMINGS Harold", "DF",
                "10","5'11", "26", "51",
                "0", "San Jose Earthquakes (USA)", nation, "1UW-moSITzAC_1Q0wYCZYF_rGpDPB5ukM");
        argPlayersList[3] = new Players("ESCOBAR", "ESCOBAR Fidel", "DF",
                "10","5'11", "23", "21",
                "1", "New York Red Bulls (USA)", nation, "1Uj3eID6t6sfreNnM5e-jl1wA_f949oIV");
        argPlayersList[4] = new Players("TORRES", "TORRES Roman", "DF",
                "10","6'2", "32", "108",
                "10", "Seattle Sounders FC (USA)", nation, "1NDSG9FVkjlvM67V0bRrYePsQU-l9vL9S");
        argPlayersList[5] = new Players("GOMEZ", "GOMEZ Gabriel", "MF",
                "10","6'0", "34", "141",
                "11", "Atlético Bucaramanga (COL) ", nation, "1APfXt2U74h5rftHyRItWw9sB7KpGj549");
        argPlayersList[6] = new Players("PEREZ", "PEREZ Blas", "FW",
                "10","6'1", "37", "113",
                "43", "CSD Municipal (GUA)", nation, "1-_ZZV5DdMcHSyV8GRJBQE3Pf58X-Bc7D");
        argPlayersList[7] = new Players("BARCENAS", "BARCENAS Edgar", "MF",
                "10","5'7", "24", "27",
                "0", "Cafetaleros de Tapachula (MEX)", nation, "1CHxRI078dndsulka5HSjUMBr5Pcjd1Tb");
        argPlayersList[8] = new Players("TORRES", "TORRES Gabrie", "FW",
                "10","5'11", "29", "16",
                "1", "CD Huachipato (CHI)", nation, "1kvOPUiadcsO1KxC5J5Da_J7NBjCXjGXW ");
        argPlayersList[9] = new Players("DIAZ", "DIAZ Ismael", "FW",
                "10","5'11", "21", "10",
                "2", "Deportivo La Coruña (ESP)", nation, "13pcQVVSft1ztbPhhbChj-x-gILSm4m21");
        argPlayersList[10] = new Players("COOPER", "COOPER Armando", "MF",
                "10","5'8", "30", "93",
                "7", "Club Universidad de Chile (CHI)", nation, "1lBd6vnwaSb6XVCKjOOXNk8T4zQF0c8FO");
        argPlayersList[11] = new Players("CALDERON", "CALDERON Jose", "GK",
                "10","6'2", "32", "13",
                "0", "Chorrillo FC (PAN)", nation, "13dBmsb9PJkg1paAC_Mw5MNP2HZnDDptb");
        argPlayersList[12] = new Players("MACHADO", "MACHADO Adolfo", "DF",
                "10","6'0", "33", "65",
                "1", "Houston Dynamo (USA)", nation, "1mFW3jcLVmUBKhe7u4NcdGEhhLGnmMi0S");
        argPlayersList[13] = new Players("PIMENTEL", "PIMENTEL Valentin", "MF",
                "10","6'1", "27", "19",
                "1", "Plaza Amador (PAN) ", nation, "1Bb21iwdPIz5ULTJoTkq3OkCPw0WIArSO");
        argPlayersList[14] = new Players("DAVIS", "DAVIS Eric", "DF",
                "10","5'10", "27", "37",
                "0", "DAC Dunajska Streda (SVK)", nation, "18NdAnbqQpmDZUmhXJYSXy4qIuKzZo8mo");
        argPlayersList[15] = new Players("ARROYO", "ARROYO Abdiel", "FW",
                "10","6'1", "24", "33",
                "5", "LD Alajuelense (CRC)", nation, "1oh5bx1LmjZfkKPdx_Cp4oPpt8D1siH-t");
        argPlayersList[16] = new Players("OVALLE", "OVALLE Luis", "DF",
                "10","5'9", "29", "23",
                "0", "CD Olimpia (HON)", nation, "1vVZet8xdmjZLqZnyjGH6jyoHQRiwL9Bn");
        argPlayersList[17] = new Players("TEJADA", "TEJADA Luis", "FW",
                "10","6'1", "36", "104",
                "43", "Sport Boys (PER) ", nation, "1OzPG2A3QlHRZi8Gl-QxudD8pislJnR_U");
        argPlayersList[18] = new Players("QUINTERO", "QUINTERO Alberto", "MF",
                "10","5'5", "30", "90",
                "4", "Universitario Lima (PER)", nation, "1merEkdi1eC74qgWQIhqvXMFBdDZKaioX");
        argPlayersList[19] = new Players("GODOY", "GODOY Anibal", "MF",
                "10","5'11", "28", "55",
                "1", "San Jose Earthquakes (USA)", nation, "1rkFb9nPx2TCDmcijeA7phJW47WSLDT0u");
        argPlayersList[20] = new Players("J. RODRIGUEZ", "RODRIGUEZ Jose Luis", "MF",
                "10","5'11", "19", "2",
                "0", "KAA Gent (BEL)", nation, "1o4V6Ifb9Eczeo8avyx4Xoh-C8Dkt5w9-");
        argPlayersList[21] = new Players("RODRIGUEZ", "RODRIGUEZ Alex", "GK",
                "10","6'6", "27", "6",
                "0", "San Francisco FC (PAN)", nation, "17guzq-Oq1cRLY6LOvMZOmTVxMjdOE-f4");
        argPlayersList[22] = new Players("BALOY", "BALOY Felipe", "DF",
                "10","6'0", "37", "101",
                "3", "CSD Municipal (GUA)", nation, "1V6K-0VqhwZySOrjhJrvYHcDLn4bPh6Sp");
        return argPlayersList;
    }

    public Players[] initPeruPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Peru";
        argPlayersList[0] = new Players("GALLESE", "GALLESE Pedro", "GK",
                "10","6'2", "28", "36",
                "0", "CD Tiburones Rojos de Veracruz (MEX)", nation, "1BuSa7AKFO7p3VfBc4oz_3TctwcmkFpRf");
        argPlayersList[1] = new Players("RODRIGUEZ", "RODRIGUEZ Alberto", "DF",
                "10","6'0", "34", "72",
                "0", "Atletico Junior (COL)", nation, "160Yc3Did1_veujL6qlkK0sGx1i_OLaP3");
        argPlayersList[2] = new Players("CORZO", "CORZO Aldo", "DF",
                "10","5'7", "29", "23",
                "0", "Universitario Lima (PER)", nation, "1c-L2xzLHn_m3R1AFAwgeVqQjkTYRJzDg");
        argPlayersList[3] = new Players("SANTAMARIA", "SANTAMARIA Anderson", "DF",
                "10","6'1", "26", "2",
                "0", "Puebla FC (MEX)", nation, "1-j2deuU4YsBbD_MRZB_wDo1_FAzvGYvw");
        argPlayersList[4] = new Players("ARAUJO", "ARAUJO Miguel", "DF",
                "10","5'10", "23", "7",
                "0", "Alianza Lima (PER)", nation, "1MVfRx-ZYnRNKYTB40GLt1Hpp9i5BiOlQ");
        argPlayersList[5] = new Players("TRAUCO", "TRAUCO Miguel", "DF",
                "10","5'7", "25", "26",
                "0", "CR Flamengo (BRA)", nation, "12OuCzv7ZSDE8etJYZ_7tCagOe1XRNUL8");
        argPlayersList[6] = new Players("HURTADO", "HURTADO Paolo", "MF",
                "10","5'10", "27", "29",
                "3", "Vitoria Guimaraes (POR)", nation, "1bIRLkJ9Iu2L6ialLhvqTrLC6dhkdqsDk");
        argPlayersList[7] = new Players("CUEVA", "CUEVA Christian", "MF",
                "10","5'7", "26", "44",
                "8", "Sao Paulo FC (BRA)", nation, "1sobrWVeamskmOMG3vfX3b6YElpNaJsfv");
        argPlayersList[8] = new Players("GUERRERO", "GUERRERO Paolo", "FW",
                "10","6'1", "34", "87",
                "34", "CR Flamengo (BRA)", nation, "1-V4VeuQBkFWcyclby6oLqe0Numbs84Oa");
        argPlayersList[9] = new Players("FARFAN", "FARFAN Jefferson", "FW",
                "10","5'10", "33", "83",
                "25", "FC Lokomotiv Moscow (RUS)", nation, "1UvlARAx9Y-n6JDF0-ZQS1ewvHRBDpr0p");
        argPlayersList[10] = new Players("RUIDIAZ", "RUIDIAZ Raul", "FW",
                "10","5'5", "27", "29",
                "4", "CA Monarcas Morelia (MEX)", nation, "1Uq-XcFWaSt4n6bULvecw6B8rsVbYq14j");
        argPlayersList[11] = new Players("CACEDA", "CACEDA Carlos", "GK",
                "10","6'0", "26", "4",
                "0", "Deportivo Municipal (PER)", nation, "1htyzQDAZykVapbd1n75IeiDXpYIl4W6y");
        argPlayersList[12] = new Players("TAPIA", "TAPIA Renato", "MF",
                "10","6'1", "22", "28",
                "3", "Feyenoord Rotterdam (NED)", nation, "1NrelyT4bTbUajtlUzdu1imY-h5m6l7J1");
        argPlayersList[13] = new Players("POLO", "POLO Andy", "MF",
                "10","5'9", "23", "15",
                "1", "Portland Timbers (USA)", nation, "1Tql47_HSLNCCCdTSAzvFzRYYZb0079qv");
        argPlayersList[14] = new Players("RAMOS", "RAMOS Christian", "DF",
                "10","5'11", "29", "63",
                "3", "CD Tiburones Rojos de Veracruz (MEX)", nation, "1tSD-uRmGdbvq-CPY2jZ3Ra16X06ui-Fn");
        argPlayersList[15] = new Players("CARTAGENA", "CARTAGENA Wilder", "MF",
                "10","5'11", "23", "0",
                "0", "CD Tiburones Rojos de Veracruz (MEX)", nation, "1j9h-pDljqhTqT9L5818IB9rA5_f2BleW");
        argPlayersList[16] = new Players("ADVINCULA", "ADVINCULA Luis", "DF",
                "10","5'10", "28", "62",
                "0", "Lobos BUAP (MEX)", nation, "19D77uO-nN9hLHYlAjdKYPzRywPs1_jva");
        argPlayersList[17] = new Players("CARRILLO", "CARRILLO Andre", "FW",
                "10","5'11", "26", "45",
                "5", "Watford FC (ENG)", nation, "16Cs96_UOh-z8yY2LCFCUfGLTHt8N4SMh");
        argPlayersList[18] = new Players("YOTUN", "YOTUN Yoshimar", "MF",
                "10","5'7", "28", "70",
                "2", "Orlando City SC (USA)", nation, "14f3TA9Fu3RhtXuNYm6lAtJueM1qVAK0B");
        argPlayersList[19] = new Players("FLORES", "FLORES Edison", "FW",
                "10","5'5", "24", "26",
                "9", "Aalborg BK (DEN)", nation, "1PkhPPyY3BrGElLxc8Ct4Qt9bE5R1zpZ8");
        argPlayersList[20] = new Players("CARVALLO", "CARVALLO Jose", "GK",
                "10","6'0", "32", "5",
                "0", "Universidad Técnica de Cajamarca (PER)", nation, "1US27FkLH6yV_p4wc-rF1mxOikJoYRzpZ");
        argPlayersList[21] = new Players("LOYOLA", "LOYOLA Nilson", "DF",
                "10","5'9", "23", "3",
                "0", "FBC Melgar (PER)", nation, "18vxE-N1-LHqQ55Zy0nFj6cxsZrpWxuU0");
        argPlayersList[22] = new Players("AQUINO", "AQUINO Pedro", "MF",
                "10","5'8", "23", "10",
                "0", "Lobos BUAP (MEX)", nation, "16cIVSkp0wGZiVhVvdmfa4xeR7vSx8TaN");
        return argPlayersList;
    }

    public Players[] initPolandPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Poland";
        argPlayersList[0] = new Players("SZCZESNY", "SZCZESNY Wojciech", "GK",
                "10","6'5", "28", "33",
                "0", "Juventus FC (ITA)", nation, "18Q8k_Z79Ei5ntWHDTa9wWeqSZy_RWWr3");
        argPlayersList[1] = new Players("PAZDAN", "PAZDAN Michal", "DF",
                "10","5'11", "30", "31",
                "0", "Legia Warsaw (POL)", nation, "1mEWyv2BAB9OdLzIilTyQXgHhZ7u4DylX");
        argPlayersList[2] = new Players("JEDRZEJCZYK", "JEDRZEJCZYK Artur", "DF",
                "10","6'2", "30", "35",
                "3", "Legia Warsaw (POL)", nation, "19z09_-Fqcxf46dyHzBGtug7WarYbI2ee");
        argPlayersList[3] = new Players("CIONEK", "CIONEK Thiago", "DF",
                "10","6'0", "32", "17",
                "0", "SPAL Ferrara (ITA)", nation, "14Atsr8oxTeLQvgevzvocBVRSdJ3qsBht");
        argPlayersList[4] = new Players("BEDNAREK", "BEDNAREK Jan", "DF",
                "10","6'2", "22", "1",
                "0", "Southampton FC (ENG)", nation, "1Wx5ag3Q0qByR6i345pcOJM2X6EanLprr");
        argPlayersList[5] = new Players("GORALSKI", "GORALSKI Jacek", "MF",
                "10","5'8", "25", "3",
                "0", "PFC Ludogorets Razgrad (BUL)", nation, "1vORw32oop7uyxfiNHbZLxpGYdlBlWd_1");
        argPlayersList[6] = new Players("MILIK", "MILIK Arkadiusz", "FW",
                "10","6'1", "24", "38",
                "12", "SSC Napoli (ITA)", nation, "1iyOfu2sCZoZEDssxW8774xeTnJrtt9WM");
        argPlayersList[7] = new Players("LINETTY", "LINETTY Karol", "MF",
                "10","5'9", "23", "19",
                "1", "UC Sampdoria (ITA)", nation, "1Tj40tw946zSMI-2hJWmq_W7JaCekvxfB");
        argPlayersList[8] = new Players("LEWANDOWSKI", "LEWANDOWSKI Robert", "FW",
                "10","6'1", "29", "93",
                "52", "FC Bayern München (GER)", nation, "1lT1JPVwKhE1AEsBMnFNJsg_8CtaEuWJk");
        argPlayersList[9] = new Players("KRYCHOWIAK", "KRYCHOWIAK Grzegorz", "MF",
                "10","6'1", "28", "49",
                "2", "West Bromwich Albion FC (ENG)", nation, "123Jrz3h6UmI6a9UKmZ_Mkj4rx2g9W3Vi");
        argPlayersList[10] = new Players("GROSICKI", "GROSICKI Kami", "MF",
                "10","5'11", "29", "56",
                "12", "Hull City FC (ENG)", nation, "1TMKw6rYyqThsdcq9gDRrqiRYlTMh8yRA");
        argPlayersList[11] = new Players("BIALKOWSKI", "BIALKOWSKI Bartosz", "GK",
                "10","6'4", "30", "1",
                "0", "Ipswich Town FC (ENG)", nation, "1MAcK2I05ofmpASYFdM-jsIgQ8odq29uu");
        argPlayersList[12] = new Players("RYBUS", "RYBUS Maciej", "MF",
                "10","5'8", "28", "49",
                "2", "FC Lokomotiv Moscow (RUS)", nation, "1Ug9sMUDM9HEhYG4D8ew-YyVCli3ACVoI");
        argPlayersList[13] = new Players("TEODORCZYK", "TEODORCZYK Lukasz", "FW",
                "10","6'1", "27", "15",
                "4", "RSC Anderlecht (BEL)", nation, "1q83lJaaU187OJKEuSXHMjj1bD0GsnTnD");
        argPlayersList[14] = new Players("GLIK", "GLIK Kamil", "DF",
                "10","6'3", "30", "57",
                "4", "AS Monaco (FRA)", nation, "1-v0_xJCay5Cb-KQLWyJVdt4kOOjKeck4");
        argPlayersList[15] = new Players("BLASZCZYKOWSKI", "BLASZCZYKOWSKI Jakub", "MF",
                "10","5'9", "32", "97",
                "19", "VfL Wolfsburg (GER)", nation, "161JLqX6NM35sWjTHHvoeclIl0XtMzg32");
        argPlayersList[16] = new Players("PESZKO", "PESZKO Slawomir", "MF",
                "10","5'8", "33", "43",
                "2", "Lechia Gdansk (POL)", nation, "1s-Lac9PgQuBHOJRZs9uDPUcoU8qyIxdK");
        argPlayersList[17] = new Players("BERESZYNSKI", "BERESZYNSKI Bartosz", "DF",
                "10","5'11", "25", "6",
                "0", "UC Sampdoria (ITA)", nation, "1LzVLFsUsWaBh8S8-dtuNAUTj3ek46OB0");
        argPlayersList[18] = new Players("ZIELINSKI", "ZIELINSKI Piotr", "MF",
                "10","5'11", "24", "31",
                "4", "SSC Napoli (ITA)", nation, "1tuCR9w3hdXdZ9bEEMxaWbjbOO7gzYy3X");
        argPlayersList[19] = new Players("PISZCZEK", "PISZCZEK Lukasz", "DF",
                "10","6'1", "33", "61",
                "3", "Borussia Dortmund (GER", nation, "1_UPoevgcwXPuvaW6CdUfZjmQr3dK2fF4");
        argPlayersList[20] = new Players("KURZAWA", "KURZAWA Rafal", "MF",
                "10","6'0", "25", "3",
                "0", "Gornik Zabrze (POL)", nation, "1Vklp_tcnpkyw0iDhwM0S7DskVH_gjDGC");
        argPlayersList[21] = new Players("FABIANSKI", "FABIANSKI Lukasz", "GK",
                "10","6'3", "33", "43",
                "0", "Swansea City AFC (WAL)", nation, "1QmT4dh7aEAgWvVp76GN7DCykkYhnGWA3");
        argPlayersList[22] = new Players("KOWNACKI", "KOWNACKI Dawid", "FW",
                "10","6'2", "21", "1",
                "0", "UC Sampdoria (ITA)", nation, "11EUC9g1695jypW4qwB75fMuwDHCh6AxE");
        return argPlayersList;
    }

    public Players[] initPortugalPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Portugal";
        argPlayersList[0] = new Players("RUI PATRICIO", "RUI PATRICIO", "GK",
                "10","6'2", "30", "68",
                "0", "Sporting CP (POR)", nation, "1sinLlm3ol6CKQrb5DEyTyPm4o2OzcA8u");
        argPlayersList[1] = new Players("B. ALVES ", "BRUNO ALVES", "DF",
                "10","6'2", "36", "95",
                "11", "Rangers FC (SCO)", nation, "1KuctBEw9s-XT4addBE12nt8Cl7fJdTu-");
        argPlayersList[2] = new Players("PEPE", "PEPE", "DF",
                "10","6'2", "35", "91",
                "5", "Besiktas JK (TUR) ", nation, "14JR5V-q7eMvcqVUDqGINsCQbArWRjd96");
        argPlayersList[3] = new Players("M. FERNANDES", "MANUEL FERNANDES", "MF",
                "10","5'9", "32", "14",
                "3", "FC Lokomotiv Moscow (RUS)", nation, "1Tzit9F3WLnckHP-yjqypAuKxIa1vqJWt");
        argPlayersList[4] = new Players("RAPHAEL", "RAPHAEL GUERREIRO", "DF",
                "10","5'7", "24", "23",
                "2", "Borussia Dortmund (GER) ", nation, "1X8kcCvK58o020lu1AYI4u_cLY5uGpqG8");
        argPlayersList[5] = new Players("FONTE", "JOSE FONTE", "DF",
                "10","6'2", "34", "30",
                "0", "Dalian Yifang FC (CHN)", nation, "1Eo7dJcHgOx-7-GN00iHtkxL6rIYrEhuV");
        argPlayersList[6] = new Players("RONALDO", "CRISTIANO RONALDO", "FW",
                "10","6'2", "33", "149",
                "81", "Real Madrid CF (ESP)", nation, "1wC0oB6oS7PdHfnyzz_otxWYzatm6FFCg");
        argPlayersList[7] = new Players("J. MOUTINHO", "JOAO MOUTINHO", "MF",
                "10","5'7", "31", "109",
                "7", "AS Monaco (FRA)", nation, "1L0aQcQiRfmlg_za4QFqpindg329iVDSi");
        argPlayersList[8] = new Players("ANDRÉ SILVA", "ANDRE SILVA", "FW",
                "10","6'1", "22", "22",
                "12", "AC Milan (ITA)", nation, "1t_dKenCePoiUW6WiDJkBL7haEGlhfFhN");
        argPlayersList[9] = new Players("J. MÁRIO", "JOAO MARIO", "MF",
                "10","5'7", "25", "124",
                "64", "West Ham United FC (ENG)", nation, "1ILzc_RuO83yNzmClEM4qv5eybSdBUcUN");
        argPlayersList[10] = new Players("BERNARDO", "BERNARDO SILVA", "MF",
                "10","5'10", "30", "35",
                "2", "Manchester City FC (ENG)", nation, "1Vw7F0xl1sgsfNEe7NZN2W5PyiDTKA_7Y");
        argPlayersList[11] = new Players("LOPES", "ANTHONY LOPES", "GK",
                "10","6'0", "27", "7",
                "0", "Olympique Lyon (FRA)", nation, "1s0ocaPi-e5LerY29u1ig0VaSMP1C9Fjf");
        argPlayersList[12] = new Players("RUBEN DIAS", "RUBEN DIAS", "DF",
                "10","6'1", "21", "1",
                "0", "SL Benfica (POR)", nation, "1Oi_Ghae4uFvX2RuLm-cLFpmcEeqTM064");
        argPlayersList[13] = new Players("WILLIAM", "WILLIAM", "MF",
                "10","6'3", "26", "42",
                "2", "Sporting CP (POR)", nation, "1yOezw5U1g9GOSlzY5iEH4WvpGJyO8kzX");
        argPlayersList[14] = new Players("RICARDO", "RICARDO", "DF",
                "10","5'9", "24", "4",
                "0", "FC Porto (POR)", nation, "1aSqxYO_L76enWkikKCXMzOoZCb2B8xQg");
        argPlayersList[15] = new Players("B. FERNANDES", "BRUNO FERNANDES", "MF",
                "10","5'10", "23", "5",
                "0", "Sporting CP (POR)", nation, "1X0Pb_iscvQ9_obViM3yvzjqhkOnC8yuG");
        argPlayersList[16] = new Players("G. GUEDES", "GONCALO GUEDES", "FW",
                "10","5'10", "21", "9",
                "1", "Valencia CF (ESP)", nation, "1YYsE0mXpeZDkgrhxDQFINvvXsCKS1pSY");
        argPlayersList[17] = new Players("GELSON", "GELSON MARTINS ", "FW",
                "10","5'7", "23", "18",
                "0", "Sporting CP (POR)", nation, "1diRMoJqfG7pVcKh5Rw_fnlAlnwBPCpZt");
        argPlayersList[18] = new Players("MÁRIO RUI ", "MARIO RUI", "DF",
                "10","5'6", "27", "3",
                "0", "SSC Napoli (ITA)", nation, "1cb4Ot1aPQXRMvDZ_NGEqZHWSnvSzOGL9");
        argPlayersList[19] = new Players("QUARESMA", "RICARDO QUARESMA", "FW",
                "10","5'9", "34", "76",
                "9", "Besiktas JK (TUR)", nation, "1OFxFv58Hi3XRXTIyc07hYF9B5uWZUGp8");
        argPlayersList[20] = new Players("CÉDRIC", "CEDRIC", "DF",
                "10","5'8", "26", "28",
                "1", "Southampton FC (ENG)", nation, "1ClVDIWhlmsskM6i7ypGILfqKP-DvGMIT");
        argPlayersList[21] = new Players("BETO", "BETO", "GK",
                "10","6'0", "36", "14",
                "0", "Goztepe SK (TUR)", nation, "1QB-04DDlnslcXnDw3oWVN4xm4ppXkhtJ");
        argPlayersList[22] = new Players("ADRIEN", "ADRIEN SILVA", "MF",
                "1","5'9", "29", "22",
                "64", "Leicester City FC (ENG)", nation, "1LTX0KTitTvj9sNqo85nNOQCmxx46p0JX");
        return argPlayersList;
    }

    public Players[] initRussiaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Russia";
        argPlayersList[0] = new Players("AKINFEEV", "AKINFEEV Igor", "GK",
                "10","6'1", "32", "106",
                "0", "CSKA Moscow (RUS)", nation, "1aUZCYsYQRnHpXmvaFN4ffFWSKfiiKR1I");
        argPlayersList[1] = new Players("FERNANDES", "MARIO FERNANDES", "DF",
                "10","6'2", "27", "5",
                "0", "CSKA Moscow (RUS)", nation, "1gEaKr0F1HRF4FRi0cN9C0og-58DGnVVP");
        argPlayersList[2] = new Players("KUTEPOV", "KUTEPOV Ilya", "DF",
                "10","6'3", "24", "7",
                "0", "FC Spartak Moscow (RUS)", nation, "1X0olqShv470mF-pQFImaRa-nzegKK67j");
        argPlayersList[3] = new Players("IGNASHEVICH", "IGNASHEVICH Sergey", "DF",
                "10","6'1", "38", "123",
                "8", "CSKA Moscow (RUS)", nation, "1i_wk4DBOplhcnczqpjQFp5be4yriHZoX");
        argPlayersList[4] = new Players("SEMENOV", "SEMENOV Andrey", "DF",
                "10","6'3", "29", "6",
                "0", "FC Akhmat Grozny (RUS)", nation, "1_D00MrpzFYC-yzHBoI94VuEpHLFHtk_h");
        argPlayersList[5] = new Players("CHERYSHEV", "CHERYSHEV Denis", "MF",
                "10","5'10", "27", "11",
                "0", "Villarreal CF (ESP)", nation, "1tOYNiZ27gyia3edeEr3gBjXB-eLRnP1G");
        argPlayersList[6] = new Players("KUZIAEV", "KUZIAEV Daler", "MF",
                "10","5'9", "25", "6",
                "0", "FC Zenit St. Petersburg (RUS)", nation, "1qm7x-dPz4nwJ8yB-W-oypAMLJgXghTRw");
        argPlayersList[7] = new Players("GAZINSKY", "GAZINSKY Iury", "MF",
                "10","6'1", "28", "6",
                "0", "FC Krasnodar (RUS)", nation, "1OB7gf2d61vrbm1astbE0em-1lBmn8JTA");
        argPlayersList[8] = new Players("DZAGOEV", "DZAGOEV Alan", "MF",
                "10","5'10", "27", "57",
                "9", "CSKA Moscow (RUS)", nation, "1OLey1j4BswpsdXcwBSsWejG16v510vQD");
        argPlayersList[9] = new Players("SMOLOV", "SMOLOV Fedor", "FW",
                "10","6'1", "28", "32",
                "12", "FC Krasnodar (RUS)", nation, "123KiXtk7n5RYbPz9pWdfyJgjrl0K2c-w");
        argPlayersList[10] = new Players("ZOBNIN", "ZOBNIN Roman", "MF",
                "10","5'11", "24", "12",
                "0", "FC Spartak Moscow (RUS)", nation, "13DGGwwmU2Dz6mAenOA2EWoLBVnxgn6r7");
        argPlayersList[11] = new Players("LUNEV", "LUNEV Andrei", "GK",
                "10","6'2", "26", "3",
                "0", "FC Zenit St. Petersburg (RUS)", nation, "1u4whFaRjcT2mgYdHKP_4Vc3LXpGk1p8f");
        argPlayersList[12] = new Players("KUDRIASHOV", "KUDRIASHOV Fedor", "DF",
                "10","5'11", "31", "19",
                "0", "FC Rubin Kazan (RUS)", nation, "1OaKhg5jsl0M9M-ZeNuZrlkPj341nuufH");
        argPlayersList[13] = new Players("GRANAT", "GRANAT Vladimir", "DF",
                "10","6'1", "31", "12",
                "1", "FC Rubin Kazan (RUS)", nation, "1PnYsyJn_jm6A--3I6QZSCJJRssfu-Mkq");
        argPlayersList[14] = new Players("MIRANCHUK AL.", "MIRANCHUK Alexey", "MF",
                "10","6'0", "22", "18",
                "4", "FC Lokomotiv Moscow (RUS)", nation, "1rXrqXNrnRNGqI2qHKByWnIO-wIu5PtbV");
        argPlayersList[15] = new Players("MIRANCHUK AN.", "MIRANCHUK Anton", "MF",
                "10","5'11", "22", "6",
                "0", "FC Lokomotiv Moscow (RUS)", nation, "1m8LvlJ5kJcgZq2_gTJcJ5JO7Y4OInONf");
        argPlayersList[16] = new Players("GOLOVIN", "GOLOVIN Aleksandr", "MF",
                "10","5'11", "22", "19",
                "2", "CSKA Moscow (RUS) ", nation, "1e9v1wAVz7SDfrrFdSYruZLsP5d3SKVfZ");
        argPlayersList[17] = new Players("ZHIRKOV", "ZHIRKOV Yury", "MF",
                "10","5'11", "34", "84",
                "2", "FC Zenit St. Petersburg (RUS)", nation, "1MHkmrCFQWUqnhxuLBre_a6DLFhmJhUhG");
        argPlayersList[18] = new Players("SAMEDOV", "SAMEDOV Alexander", "MF",
                "10","5'9", "33", "48",
                "7", "FC Spartak Moscow (RUS)", nation, "14yN6M5HYz1qXGmSIzLTBiZh7yOoEtThk");
        argPlayersList[19] = new Players("GABULOV", "GABULOV Vladimir", "GK",
                "10","6'3", "34", "10",
                "0", "Club Brugge KV (BEL)", nation, "1n6Kr4L-UxLAA7eaNgt2i-ruZ5JkoES45");
        argPlayersList[20] = new Players("EROKHIN", "EROKHIN Aleksandr", "MF",
                "10","6'5", "28", "16",
                "0", "FC Zenit St. Petersburg (RUS)", nation, "1j0NEvoFQUQMrYMCptKmHImAjRDEL0w2H");
        argPlayersList[21] = new Players("DZYUBA", "DZYUBA Artem", "FW",
                "10","6'5", "29", "23",
                "11", "FC Arsenal Tula (RUS)", nation, "1LMTp7yDWRHoZWffsl5lZ093umj9Q8NfY");
        argPlayersList[22] = new Players("SMOLNIKOV", "SMOLNIKOV Igor", "DF",
                "10","5'10", "29", "27",
                "0", "FC Zenit St. Petersburg (RUS)", nation, "1scw-LaEbuH34BRgyyj4vdaWqUkc7ayhA");
        return argPlayersList;
    }

    public Players[] initSaudiPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Saudi Arabia";
        argPlayersList[0] = new Players("ABDULLAH", "ABDULLAH ALMUAIOUF", "GK",
                "10","6'2", "31", "10",
                "0", "Al Hilal SFC (KSA)", nation, "1O_GwPDgVKs1iGMkNZKePPwvTrjC6W3to");
        argPlayersList[1] = new Players("MANSOUR", "MANSOUR ALHARBI", "DF",
                "10","5'7", "30", "39",
                "1", "Al Ahli SC (KSA)", nation, "1HmZ7qY5hTry8c6uBEkZPlqN5qpvGX0zq");
        argPlayersList[2] = new Players("OSAMA", "OSAMA HAWSAWI", "DF",
                "10","6'2", "34", "134",
                "7", "Al Hilal SFC (KSA) ", nation, "1hWeQYll6W7iIhpagvEZ4WA_xH9BDDgax");
        argPlayersList[3] = new Players("ALI", "ALI ALBULAYHI", "DF",
                "10","6'1", "28", "3",
                "0", "Al Hilal SFC (KSA) ", nation, "1HhIq7fUlMgQgHC_r75z_zxtwK97UrGKZ");
        argPlayersList[4] = new Players("OMAR", "OMAR OTHMAN", "DF",
                "10","6'1", "32", "41",
                "3", "Al Nassr FC (KSA)", nation, "1iIKJvWoD9QdePvpFgtBX-zGGP6TLX-m2");
        argPlayersList[5] = new Players("ALBURAYK", "MOHAMMED ALBURAYK", "DF",
                "10","5'8", "25", "10",
                "1", "Al Hilal SFC (KSA)", nation, "1_TXi0LZdWpAs4-YoDwlIJpb_zd4z0bRN");
        argPlayersList[6] = new Players("SALMAN", "SALMAN ALFARAJ", "MF",
                "10","5'11", "28", "42",
                "3", "Al Hilal SFC (KSA)", nation, "1XYnMNF8bm-I3tqi1KIJSSWaXacztQr8Q");
        argPlayersList[7] = new Players("YAHIA", "YAHIA ALSHEHRI", "MF",
                "10","5'4", "27", "56",
                "8", "CD Leganés (ESP) ", nation, "1SdKVc50_GflnQk231Pu3Qru95XJ2eiFk");
        argPlayersList[8] = new Players("HATAN", "HATAN BAHBIR", "MF",
                "10","5'6", "25", "124",
                "64", "Al Shabab FC (KSA)", nation, "1HqYKlDfIicnMFQyIxIpSeiL7tU2Vp2Oh");
        argPlayersList[9] = new Players("ALSAHLAWI", "MOHAMMED ALSAHLAWI", "FW",
                "10","5'11", "31", "39",
                "28", "Al Nassr FC (KSA)", nation, "1TgYsnmJiz4HZGxdaRSDlkwcLh_HgL2va");
        argPlayersList[10] = new Players("ABDULMALEK", "ABDULMALEK ALKHAIBRI", "MF",
                "10","5'9", "32", "36",
                "0", "Al Hilal SFC (KSA)", nation, "1hRkF3rH5Ll0qJ_wq71HJOI7bkeNtgcXL");
        argPlayersList[11] = new Players("KANNO", "MOHAMED KANNO", "MF",
                "10","6'3", "23", "6",
                "1", "Al Hilal SFC (KSA)", nation, "1aWZrcU2-e4nnapf16l3MBv0MfWilxhaT");
        argPlayersList[12] = new Players("YASIR", "YASIR ALSHAHRANI", "DF",
                "10","5'7", "26", "36",
                "0", "Al Hilal SFC (KSA)", nation, "1EiaIA1LNoQ-TXXqXZvR2j3CGNq2fsT0y");
        argPlayersList[13] = new Players("OTAYF", "ABDULLAH OTAYF", "MF",
                "10","5'10", "25", "15",
                "1", "Al Hilal SFC (KSA)", nation, "1Sq_bQBji0MMIbFopq97gGebuXVT5bujT");
        argPlayersList[14] = new Players("ALKHAIBARI", "ABDULLAH ALKHAIBARI", "MF",
                "10","5'7", "21", "4",
                "0", "Al Shabab FC (KSA)   ", nation, "12Ye5LM4RrHGqscw53x1Or2xm9dDVUUVW");
        argPlayersList[15] = new Players("HUSSAIN", "HUSSAIN ALMOQAHWI", "MF",
                "10","5'8", "30", "17",
                "1", "Al Ahli SC (KSA)", nation, "1uCGaa8h_TrmNj3JCbmxBYLnoKknIiSVm");
        argPlayersList[16] = new Players("TAISEER", "TAISEER ALJASSAM", "MF",
                "10","5'9", "43", "131",
                "18", "Al Ahli SC (KSA)", nation, "1rLmIi9KQ6nf1mCcs4QHNE8TV-J9RGotZ");
        argPlayersList[17] = new Players("SALEM", "SALEM ALDAWSARI", "MF",
                "10","5'9", "26", "32",
                "4", "Villarreal CF (ESP)", nation, "1c6k3Yk1omJXl0t8D00hhd1johgRTuuYD");
        argPlayersList[18] = new Players("FAHAD", "FAHAD ALMUWALLAD", "FW",
                "10","5'6", "23", "44",
                "10", "Levante UD (ESP)", nation, "1NMR0a-XX-1rubAiEgHl6iRNJZQDo_Z8N");
        argPlayersList[19] = new Players("MUHANNAD", "MUHANNAD ASIRI", "FW",
                "10","6'2", "31", "18",
                "4", "Al Ahli SC (KSA)", nation, "1cjzt7i4QKabPWiHJwx0eYqcsPqf0OCHD");
        argPlayersList[20] = new Players("ALMOSAILEM", "YASSER ALMOSAILEM", "GK",
                "10","6'1", "34", "32",
                "0", "Al Ahli SC (KSA)", nation, "1IcahkFD-yYvUbGMh7PtoAhKlIVW60M1o");
        argPlayersList[21] = new Players("ALOWAIS", "MOHAMMED ALOWAIS", "GK",
                "10","6'1", "26", "6",
                "0", "Al Ahli SC (KSA)", nation, "1hUtTLHjg_viagjmmOzGAy40EiKS0GgG4");
        argPlayersList[22] = new Players("MOTAZ", "MOTAZ HAWSAW", "DF",
                "10","5'10", "26", "17",
                "0", "Al Ahli SC (KSA)", nation, "1ahaHYxEt0t9P6osQq-B6uRHGbbkYISOk");
        return argPlayersList;
    }

    public Players[] initSenegalPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Senegal";
        argPlayersList[0] = new Players("DIALLO", "DIALLO Abdoulaye", "GK",
                "10","6'2", "26", "16",
                "0", "Stade Rennais FC (FRA)", nation, "1EzAy11dSffhIYaQZxakKl7c6aCxb8i4C");
        argPlayersList[1] = new Players("CISS", "CISS Saliou", "DF",
                "10","5'8", "28", "15",
                "0", "Valenciennes FC (FRA)", nation, "1wnQby2gAua9FXq3pBZ8JRdfGaG9HyVyH");
        argPlayersList[2] = new Players("KOULIBALY", "KOULIBALY Kalidou", "DF",
                "10","6'5", "26", "22",
                "0", "SSC Napoli (ITA)", nation, "1Ymfm_FfQMV8EC0im7cN-WoVwU4Es-ePO");
        argPlayersList[3] = new Players("KARA", "MBODJI Kara", "DF",
                "10","6'4", "28", "46",
                "5", "RSC Anderlecht (BEL)", nation, "1AQEHP94AiBNllBS_neGsyb83TcH-qMlI");
        argPlayersList[4] = new Players("GANA", "GUEYE Idrissa Gana", "MF",
                "10","5'9", "28", "48",
                "1", "Everton FC (ENG)", nation, "1qX6-QeQlei9b_FhFxibPqsi_e5748Q4H");
        argPlayersList[5] = new Players("S. SANE", "SANE Salif", "MF",
                "10","6'4", "27", "14",
                "0", "Hannover 96 (GER)", nation, "1a_zD49jeb7TSpOXm1C2OeY_auQqTTvMD");
        argPlayersList[6] = new Players("SOW", "SOW Moussa", "FW",
                "10","5'11", "32", "50",
                "18", "Bursaspor (TUR)", nation, "1IzpW6RPLNVuNrUduAGLIrEsK8MS4EhEJ");
        argPlayersList[7] = new Players("C. KOUYATE", "KOUYATE Cheikhou", "MF",
                "10","6'4", "28", "40",
                "2", "West Ham United FC (ENG) ", nation, "1wXCMFT976uRDoTUax1AOFKYfk7uewTYZ");
        argPlayersList[8] = new Players("M. DIOUF", "DIOUF Mame", "FW",
                "10","6'1", "30", "47",
                "10", "Stoke City FC (ENG)", nation, "1Gpel5rnKOUZ7Cs6VJk8MQoh9CcLlKIAG");
        argPlayersList[9] = new Players("MANE", "MANE Sadio", "FW",
                "10","5'9", "26", "49",
                "14", "Liverpool FC (ENG)", nation, "1fI4iaZHbjNgwXHWo5BQnILgFBRmUP5NF");
        argPlayersList[10] = new Players("CH. NDOYE", "NDOYE Cheikh", "MF",
                "10","6'3", "32", "22",
                "2", "Birmingham City FC (ENG)", nation, "1lrE8nDKamfiRwMdXihfPyI-JI8mQbS7t");
        argPlayersList[11] = new Players("SABALY", "SABALY Youssouf", "DF",
                "10","5'9", "25", "2",
                "0", "FC Girondins Bordeaux (FRA)", nation, "1GCyo9mh66__5WtpLLEuBLXX_gePCZqSJ");
        argPlayersList[12] = new Players("A. NDIAYE", "NDIAYE Alfred", "MF",
                "10","6'2", "28", "12",
                "1", "Wolverhampton Wanderers FC (ENG)", nation, "1mO-zamyClGKVkTHMXDPRdxM1BDV2sbDw");
        argPlayersList[13] = new Players("KONATE", "KONATE Moussa", "FW",
                "10","6'0", "25", "25",
                "9", "Amiens SC (FRA)", nation, "1tz_mP3dp861cEk0TCL4x584ARdVJBJZL");
        argPlayersList[14] = new Players("SAKHO", "SAKHO Diafra", "FW",
                "10","6'0", "28", "8",
                "3", "Stade Rennais FC (FRA)", nation, "1hc_h-LQPUpAaB096uH5UJJN8IB5E3b33");
        argPlayersList[15] = new Players("KH. NDIAYE", "NDIAYE Khadim", "GK",
                "10","6'1", "33", "22",
                "1", "Horoya AC (GUI)", nation, "1vDdsVC6uQdcpqEXcXujVgFOaV2r44Drl");
        argPlayersList[16] = new Players("P.A. NDIAYE", "NDIAYE Pape Alioune", "MF",
                "10","5'10", "27", "16",
                "64", "Stoke City FC (ENG)", nation, "1yxKos8jPZNAckchR3My-fqTsjZsbFhxM");
        argPlayersList[17] = new Players("ISMAÏLA", "SARR Ismaila", "FW",
                "10","5'11", "20", "10",
                "2", "Stade Rennais FC (FRA)", nation, "14uFbDrPUamRAUkCk7BSHicrP1MQItqAT");
        argPlayersList[18] = new Players("NIANG", "NIANG Mbaye", "FW",
                "10","6'1", "23", "3",
                "0", "Torino FC (ITA) ", nation, "1N_HKQz49uOeJS9wS6KBKkdST9hD5kMIL");
        argPlayersList[19] = new Players("KEÏTA BALDE", "BALDE Keita", "FW",
                "10","6'0", "23", "17",
                "3", "AS Monaco (FRA)", nation, "18kYI_DvZo6D3Lx3uCBAzonOEp0unsx5r");
        argPlayersList[20] = new Players("GASSAMA", "GASSAMA Lamine", "DF",
                "10","5'11", "28", "36",
                "0", "Adanaspor (TUR)", nation, "1Gh4FRz7mff6-TwV-cCfWp0421OPwaxLh");
        argPlayersList[21] = new Players("M. WAGUE", "WAGUE Moussa", "DF",
                "10","5'9", "19", "8",
                "0", "KAS Eupen (BEL)", nation, "1gzNV4VTboOQOonauen8NBGSIaBCbIzd1");
        argPlayersList[22] = new Players("GOMIS", "GOMIS Alfred", "GK",
                "10","6'5", "24", "1",
                "0", "SPAL Ferrara (ITA)", nation, "1LVIU0Sq9lJCqATbt_EQgYKziqa7nmdoh");
        return argPlayersList;
    }

    public Players[] initSerbiaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Serbia";
        argPlayersList[0] = new Players("STOJKOVIC", "STOJKOVIC Vladimir", "GK",
                "10","6'5", "34", "80",
                "0", "FK Partizan (SRB)", nation, "1mtEYaUXhmF4_MZgmjPOJuzdBaNntAWmS");
        argPlayersList[1] = new Players("RUKAVINA", "RUKAVINA Antonio", "DF",
                "10","5'9", "34", "46",
                "0", "Villarreal CF (ESP)", nation, "1ocHgjTJcgf6EFSvX9vcVis01G39h6BJD");
        argPlayersList[2] = new Players("TOŠIĆ", "TOSIC Dusko", "DF",
                "10","6'1", "33", "23",
                "1", "Besiktas JK (TUR)", nation, "1Z9dOeFO3S9c6LmmvwuJBLJ0maXb91WRa");
        argPlayersList[3] = new Players("MILIVOJEVIĆ", "MILIVOJEVIC Luka", "MF",
                "10","6'1", "27", "27",
                "1", "Crystal Palace FC (ENG)", nation, "1mws4hGUEP0m1ZnY6c5W9K-Qd5_kHou0N");
        argPlayersList[4] = new Players("SPAJIĆ", "SPAJIC Uros", "DF",
                "10","6'2", "25", "5",
                "0", "RSC Anderlecht (BEL)", nation, "1_5LL7D35rFVWSzARTny4EtGGfHKHkL_i");
        argPlayersList[5] = new Players("IVANOVIĆ", "IVANOVIC Branislav", "DF",
                "10","6'1", "34", "102",
                "12", "FC Zenit St. Petersburg (RUS)", nation, "1sgwggvXNSDpA8peW9ku0kz14yMeBM9ki");
        argPlayersList[6] = new Players("ŽIVKOVIĆ", "ZIVKOVIC Andrija", "MF",
                "10","5'6", "21", "10",
                "0", "SL Benfica (POR)", nation, "18PErU4EYj0F_yCL0vsX4Q1r4GPHt_b-c");
        argPlayersList[7] = new Players("PRIJOVIĆ", "PRIJOVIC Aleksandar", "FW",
                "10","6'3", "28", "8",
                "1", "PAOK FC (GRE)", nation, "10kxMrNkD3uZlPoUTT9Dl62EnDBC58w-C");
        argPlayersList[8] = new Players("MITROVIĆ", "MITROVIC Aleksandar", "FW",
                "10","6'2", "23", "36",
                "13", "Fulham FC (ENG)", nation, "1C05Y-VrLv8G8IE_sq-hwRPCV83b4PCN6");
        argPlayersList[9] = new Players("TADIĆ", "TADIC Dusan", "MF",
                "10","5'11", "29", "52",
                "13", "Southampton FC (ENG)", nation, "191BT8lT2S6WLkF2luCwW-tAzyinsB7Jb");
        argPlayersList[10] = new Players("KOLAROV", "KOLAROV Aleksandar", "DF",
                "10","6'2", "32", "75",
                "10", "AS Roma (ITA)", nation, "1-WuWvDJz01nzWqAzFG7zdtFgbffHynZU");
        argPlayersList[11] = new Players("RAJKOVIĆ", "RAJKOVIC Predrag", "GK",
                "10","6'3", "22", "7",
                "0", "Maccabi Tel-Aviv FC (ISR)", nation, "1dbL8qCVUFMfJ7Zr_TWxK_HoJc_VUS_Oj");
        argPlayersList[12] = new Players("VELJKOVIĆ", "VELJKOVIC Milos", "DF",
                "10","6'2", "22", "2",
                "0", "SV Werder Bremen (GER)", nation, "17E68jnRzS-_s0Liu-43sOSNHXaz1Q09m");
        argPlayersList[13] = new Players("RODIĆ", "RODIC Milan", "DF",
                "10","6'1", "27", "1",
                "0", "FK Crvena Zvezda (SRB)", nation, "1MRODGEs6fmK8BuT8snGQMoxv50l0Sb_f");
        argPlayersList[14] = new Players("MILENKOVIĆ", "MILENKOVIC Nikola", "DF",
                "10","6'5", "20", "2",
                "0", "ACF Fiorentina (ITA)", nation, "1pL-K5fWk_TV6784BMqrSgYxQf3HGv9Nq");
        argPlayersList[15] = new Players("GRUJIĆ", "GRUJIC Marko", "MF",
                "10","6'3", "22", "7",
                "0", "Cardiff City FC (WAL)", nation, "1ZmNHfJqXnpgCkklubGMzEkb35LVzRdkf");
        argPlayersList[16] = new Players("KOSTIĆ", "KOSTIC Filip", "MF",
                "10","6'1", "25", "22",
                "2", "Hamburger SV (GER)", nation, "1O0wu81QtWZnGyfWk0h2Y71Rj3zIXZaFp");
        argPlayersList[17] = new Players("RADONJIĆ", "RADONJIC Nemanja", "FW",
                "10","5'11", "22", "2",
                "0", "FK Crvena Zvezda (SRB)", nation, "1bKbNmI23ua4g7_P0_B70IHHj7a1YRbcz");
        argPlayersList[18] = new Players("JOVIĆ", "JOVIC Luka", "FW",
                "10","5'11", "20", "1",
                "0", "Eintracht Frankfurt (GER)", nation, "1CGGl7q-4jUBPurBKKTMYsMEH4J5sKE5H");
        argPlayersList[19] = new Players("SERGEJ", "MILINKOVIC-SAVIC Sergej", "MF",
                "10","6'3", "23", "3",
                "0", "SS Lazio (ITA) ", nation, "15bAsy54TSXJ6ks0KctuJymoZXdL_e7q7");
        argPlayersList[20] = new Players("MATIĆ", "MATIC Nemanja", "MF",
                "10","6'4", "29", "39",
                "2", "Manchester United FC (ENG)", nation, "1yihOWX9e1QKaPsrfpkYdapK1P953joKC");
        argPlayersList[21] = new Players("LJAJIĆ", "LJAJIC Adem", "MF",
                "10","5'11", "26", "28",
                "5", "Torino FC (ITA)", nation, "1kOJNdDUlNrORRT6KBhl4v_kmEq-E4hKg");
        argPlayersList[22] = new Players("DMITROVIĆ", "DMITROVIC Marko", "GK",
                "10","6'4", "26", "2",
                "0", "SD Eibar (ESP)", nation, "1_k1NHSLxNsArmczBOQTJqswemDFz_rP0");
        return argPlayersList;
    }

    public Players[] initSpainPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Spain";
        argPlayersList[0] = new Players(" DE GEA", "DE GEA David", "GK",
                "10","6'4", "27", "28",
                "0", "Manchester United FC (ENG)", nation, "1VmuGhnknVpfJSlrWED5rr0yRYhsgPflE");
        argPlayersList[1] = new Players("CARVAJAL", "CARVAJAL Dani", "DF",
                "10","5'8", "26", "15",
                "0", "Real Madrid CF (ESP)", nation, "1lbcIj_lpWZRcFpyXnjJPvqgKUrRTagwg");
        argPlayersList[2] = new Players("PIQUÉ", "PIQUE Gerard", "DF",
                "10","6'4", "31", "97",
                "5", "FC Barcelona (ESP) ", nation, "1ru6ItMsAiMXrsO6kk7Fr6ulYpL7x9HyF");
        argPlayersList[3] = new Players("NACHO", "NACHO", "DF",
                "10","5'10", "28", "16",
                "0", "Real Madrid CF (ESP)", nation, "1hMEEoqz5UKeh2Ijh1xIWhQCEqa1HiuKS");
        argPlayersList[4] = new Players("SERGIO", "BUSQUETS Sergio", "MF",
                "10","6'2", "29", "102",
                "2", "FC Barcelona (ESP)", nation, "16XO95ukURpvk8YKHq9fzs8mVzkYclJHj");
        argPlayersList[5] = new Players("A. INIESTA", "INIESTA Andres", "MF",
                "10","5'7", "34", "126",
                "13", "FC Barcelona (ESP)", nation, "1knQmWHrA1qxWuOF8_Kh3JMNsrmN19ORN");
        argPlayersList[6] = new Players("SAÚL", "SAUL", "MF",
                "10","5'11", "23", "10",
                "0", "Atletico Madrid (ESP) ", nation, "1ZKFQKE4r3rGSDw3zgCCjya8wi5nVvsnO");
        argPlayersList[7] = new Players("KOKE", "KOKE", "MF",
                "10","5'10", "26", "39",
                "0", "Atletico Madrid (ESP) ", nation, "1vTWCParE7qJ3s0WgmE3-7NArgr-qt7MF");
        argPlayersList[8] = new Players("RODRIGO M.", "RODRIGO", "FW",
                "10","5'11", "27", "5",
                "2", "Valencia CF (ESP)", nation, "1Bt2UUumGLJjX1t4Le3Aswswk07XMl-sq");
        argPlayersList[9] = new Players("THIAGO", "THIAGO", "MF",
                "10","5'8", "27", "28",
                "2", "FC Bayern München (GER)", nation, "1TfZCUDv7nLA16aX59ufI1eXZHseFuDh-");
        argPlayersList[10] = new Players("LUCAS V.", "VAZQUEZ Lucas", "FW",
                "10","5'8", "26", "6",
                "0", "Real Madrid CF (ESP)", nation, "1yDj5sPfkCdgjHxC1O7daLRU3Dwqf6ewM");
        argPlayersList[11] = new Players("ODRIOZOLA", "ODRIOZOLA Alvaro", "DF",
                "10","5'10", "22", "3",
                "1", "Real Sociedad (ESP) ", nation, "1L1uejFfxrfBA6q9Zhd7b37ZbOlY1O7ot");
        argPlayersList[12] = new Players("ARRIZABALAGA", "ARRIZABALAGA Kepa", "GK",
                "10","6'2", "23", "1",
                "0", "Athletic Bilbao (ESP)", nation, "1aL8jd7_JDAxjr0o9Bo0vr2uXs848I9Gr");
        argPlayersList[13] = new Players("AZPILICUETA", "AZPILICUETA Cesar", "DF",
                "10","5'10", "28", "22",
                "0", "Chelsea FC (ENG)", nation, "1gK704c3CqmJ6iPdzdMcxXEWNFhmT2HIz");
        argPlayersList[14] = new Players("RAMOS", "RAMOS Sergio", "DF",
                "10","6'0", "32", "151",
                "13", "Real Madrid CF (ESP)", nation, "1PVI1WmXwBuq50sD_5BeEIKWcvXT_cGOs");
        argPlayersList[15] = new Players("MONREAL", "MONREAL Nacho", "DF",
                "10","5'10", "32", "22",
                "1", "Arsenal FC (ENG)", nation, "1Pqep-aJPMzwdImuDneLYvvuqpwnGwnwy");
        argPlayersList[16] = new Players("IAGO ASPAS", "ASPAS Iago", "FW",
                "10","5'9", "30", "9",
                "4", "Celta Vigo (ESP)", nation, "1qLkBDi1LXZK4_bWgDKvOIcX0whCS59E3");
        argPlayersList[17] = new Players("JORDI ALBA", "ALBA Jordi", "DF",
                "10","5'7", "29", "61",
                "8", "FC Barcelona (ESP)", nation, "1fIBxz_5VSWplgQvSRQRuHDfHPhzktwoL");
        argPlayersList[18] = new Players("DIEGO COSTA", "COSTA Diego", "FW",
                "10","6'2", "29", "19",
                "7", "Atletico Madrid (ESP)", nation, "1G7iBflewPKSmu8RHwf4XjNi_BJfhjE0I");
        argPlayersList[19] = new Players("ASENSIO", "ASENSIO Marco", "MF",
                "10","5'11", "22", "11",
                "0", "Real Madrid CF (ESP)", nation, "1ArhSk487wn1McW1CwaPTWGmymimga_ms");
        argPlayersList[20] = new Players("SILVA", "SILVA David", "FW",
                "10","5'8", "32", "120",
                "35", "Manchester City FC (ENG)", nation, "1b-DxBNoqrt9DvdcWDZ_48Ck8nMO-4vBl");
        argPlayersList[21] = new Players("ISCO", "ISCO", "MF",
                "10","5'9", "26", "26",
                "10", "Real Madrid CF (ESP) ", nation, "1S2SZ_c-GZHim5JhrAnFgCReJl5C4voxQ");
        argPlayersList[22] = new Players("REINA", "REINA Pepe", "GK",
                "10","6'2", "35", "36",
                "0", "SSC Napoli (ITA)", nation, "1dmpgUdctXaHFD0nMvM2VhwJDZIDpUs9S");
        return argPlayersList;
    }

    public Players[] initSwedenPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Sweden";
        argPlayersList[0] = new Players("OLSEN", "OLSEN Robin", "GK",
                "10","6'6", "28", "16",
                "0", "FC Kobenhavn (DEN)", nation, "1vr-UuM1g78w6mQEnipE6V5YQBR2ckDFb");
        argPlayersList[1] = new Players("LUSTIG", "LUSTIG Mikael", "DF",
                "10","6'2", "31", "64",
                "6", "Celtic FC (SCO)", nation, "1P08YuhwWV_EtTyhCl_Hvv25raG8sGRxW");
        argPlayersList[2] = new Players("LINDELÖF", "LINDELOF Victor", "DF",
                "10","6'2", "23", "20",
                "1", "Manchester United FC (ENG)", nation, "1KngNE77N-KkzdhPjrTLy1bCGD4Td1XRg");
        argPlayersList[3] = new Players("GRANQVIST", "GRANQVIST Andreas", "DF",
                "10","6'3", "33", "70",
                "6", "FC Krasnodar (RUS)", nation, "1MpIYg2m6UhnzDO63QOM60Wll4kMlS7Fd");
        argPlayersList[4] = new Players("OLSSON", "OLSSON Martin", "DF",
                "10","5'10", "30", "42",
                "5", "Swansea City AFC (WAL)", nation, "1ce9FBrEjpOTM34q_mxtQyuSJN5szXgAD");
        argPlayersList[5] = new Players("AUGUSTINSSON", "AUGUSTINSSON Ludwig", "DF",
                "10","5'11", "24", "14",
                "0", "SV Werder Bremen (GER)", nation, "1jjn-YCi6fatXzWDIwn6f_zdhPSulsI18");
        argPlayersList[6] = new Players("LARSSON", "LARSSON Sebastian", "MF",
                "10","5'10", "33", "98",
                "6", "Hull City FC (ENG) ", nation, "1j6Sc6u6wecHgsF6Gon7XgcmpEoR10O52");
        argPlayersList[7] = new Players("EKDAL", "EKDAL Albin", "MF",
                "10","6'1", "28", "32",
                "0", "Hamburger SV (GER) ", nation, "1CcYcg8nmprZYcIsNxEqraflpkUhi8gn9");
        argPlayersList[8] = new Players("BERG", "BERG Marcus", "FW",
                "10","6'1", "31", "55",
                "18", "Al Ain FC (UAE)", nation, "1Lg4FFsl_0AfZb3LKEoaspVZ92Jgwzie-");
        argPlayersList[9] = new Players("FORSBERG", "FORSBERG Emil", "MF",
                "10","5'10", "26", "34",
                "6", "RB Leipzig (GER)", nation, "18iU0FESpSQRft6UUjquJbZXyQZTgo9nr");
        argPlayersList[10] = new Players("GUIDETTI", "GUIDETTI John", "FW",
                "10","6'1", "26", "20",
                "1", "Deportivo Alavés (ESP)", nation, "17jRL24ktIdbezHiN3oJTR86IogGunjob");
        argPlayersList[11] = new Players("JOHNSSON", "JOHNSSON Karl-Johan", "GK",
                "10","6'1", "28", "5",
                "0", "EA Guingamp (FRA)", nation, "1o4AfgCFE4luDkMQKdHNDS3CRw5fswVFH");
        argPlayersList[12] = new Players("SVENSSON", "SVENSSON Gustav", "MF",
                "10","6'1", "31", "11",
                "0", "Seattle Sounders FC (USA)", nation, "1xSEPPMHPAhtmfdlee8M4bs89rjKCy5Q4");
        argPlayersList[13] = new Players("HELANDER", "HELANDER Filip", "DF",
                "10","6'4", "25", "4",
                "0", "Bologna FC (ITA)", nation, "1JZmZ55nvHOZUE2oRPCpN1wJpGOKOuO0V");
        argPlayersList[14] = new Players("HILJEMARK", "HILJEMARK Oscar", "MF",
                "10","6'1", "25", "20",
                "2", "Genoa CFC (ITA)", nation, "18nwkMt-stgK84S6mO10NvbMlphwn1rmP");
        argPlayersList[15] = new Players("KRAFTH", "KRAFTH Emil", "DF",
                "10","5'11", "23", "11",
                "0", "Bologna FC (ITA)", nation, "1wXKodJqiyw1oEtH2vHsGnE3EwPkv66SP");
        argPlayersList[16] = new Players("CLAESSON", "CLAESSON Viktor", "MF",
                "10","6'0", "26", "21",
                "3", "FC Krasnodar (RUS)", nation, "1yBBQ_kZddBokyDFJ9p0A7-OW0WCVMctL");
        argPlayersList[17] = new Players("JANSSON", "JANSSON Pontus", "DF",
                "10","6'5", "27", "14",
                "0", "Leeds United FC (ENG)", nation, "1XXjd_LZV_qcWV-b-UYBHblhAiSTegGHc");
        argPlayersList[18] = new Players("ROHDEN", "ROHDEN Marcus", "MF",
                "10","5'11", "27", "10",
                "1", "FC Crotone (ITA)", nation, "1D96t5Rzq5QR1TubC73tXxiNCzHm5vx2N");
        argPlayersList[19] = new Players("TOIVONEN", "TOIVONEN Ola", "FW",
                "10","6'2", "31", "57",
                "13", "Toulouse FC (FRA)", nation, "15gqScbkL6kvVHOGRd-cZpjUiUn-hr8FK");
        argPlayersList[20] = new Players("DURMAZ", "DURMAZ Jimmy", "MF",
                "10","5'11", "29", "44",
                "3", "Toulouse FC (FRA)", nation, "1LhIUi0GWaSFYtF4er4x7EqTFiMndOF0J");
        argPlayersList[21] = new Players("KIESE THELIN", "THELIN Isaac Kiese", "FW",
                "10","6'2", "25", "18",
                "2", "KV Waasland-Beveren (BEL)", nation, "1iZxQVlNJyulYp8WSIRp8CpnguaAIwGxM");
        argPlayersList[22] = new Players("NORDFELDT", "NORDFELDT Kristoffer", "GK",
                "10","5'7", "28", "8",
                "0", "Swansea City AFC (WAL)", nation, "1P1BUZOix5lZw4h1LIDk6_6Ka6AFcOdJV");
        return argPlayersList;
    }

    public Players[] initSwitzerlandPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Switzerland";
        argPlayersList[0] = new Players("SOMMER", "SOMMER Yann", "GK",
                "10","6'0", "29", "34",
                "0", "VfL Borussia Mönchengladbach (GER)", nation, "1GCjAdohW6ZenADzZhiy8mYljuilI7PNA");
        argPlayersList[1] = new Players("LICHTSTEINER", "LICHTSTEINER Stephan", "DF",
                "10","6'0", "34", "99",
                "8", "Juventus FC (ITA)", nation, "18MgbCpsisqOKj0WWavkbnfwawPlZAtMH");
        argPlayersList[2] = new Players("MOUBANDJE", "MOUBANDJE Francois", "DF",
                "10","6'0", "27", "17",
                "0", "Toulouse FC (FRA) ", nation, "1CmGTOf04Y-aEum6Pd6SiGiidm6EEP2i_");
        argPlayersList[3] = new Players("ELVEDI", "ELVEDI Nico", "DF",
                "10","6'2", "21", "5",
                "0", "VfL Borussia Mönchengladbach (GER)", nation, "1AyrTfL7KqrKP2XIzCs3S1rKsE9Z5582Q");
        argPlayersList[4] = new Players("AKANJI", "AKANJI Manuel", "DF",
                "10","6'2", "22", "6",
                "0", "Borussia Dortmund (GER) ", nation, "1oszIUYB2QUcY6L1G0lpeIOYoBoxi8H8_");
        argPlayersList[5] = new Players("LANG", "LANG Michael", "DF",
                "10","6'1", "27", "24",
                "2", "FC Basel (SUI) ", nation, "1Z3AfOH6XTSz-0yJb_T42HU6yvBivzGqg");
        argPlayersList[6] = new Players("EMBOLO", "EMBOLO Breel", "FW",
                "10","6'1", "21", "24",
                "64", "Barcelona", nation, "1GsrHAlNVMFDsVmfHi4yhAP0xZ1UuAPqy");
        argPlayersList[7] = new Players("FREULER", "FREULER Remo", "MF",
                "10","5'7", "30", "124",
                "3", "Atalanta Bergamo (ITA)", nation, "1pt_MdevovZ4jdq943O6iOa1f088H-a4D");
        argPlayersList[8] = new Players("SEFEROVIC", "SEFEROVIC Haris", "FW",
                "10","6'1", "26", "50",
                "11", "SL Benfica (POR)", nation, "1vhMhDZ2PwYoiIt7hm-lrqYUtwS24gleL");
        argPlayersList[9] = new Players("XHAKA", "XHAKA Granit", "MF",
                "10","6'1", "25", "61",
                "9", "Arsenal FC (ENG)", nation, "1rCe9ojM-2R-WFv33GMGqA2JmqyILTuOm");
        argPlayersList[10] = new Players("BEHRAMI", "BEHRAMI Valon", "MF",
                "10","6'1", "33", "78",
                "2", "Udinese Calcio (ITA)", nation, "1jq7gq5ZHTRy58-UYvIC4Q5vr_gPmQVbf");
        argPlayersList[11] = new Players("MVOGO", "MVOGO Yvon", "GK",
                "10","6'2", "24", "0",
                "0", "RB Leipzig (GER)", nation, "1mKGi8EDzXieJEQGZeVukB_mog52WeCK3");
        argPlayersList[12] = new Players("RODRIGUEZ", "RODRIGUEZ Ricardo", "DF",
                "10","5'11", "25", "52",
                "4", "AC Milan (ITA)", nation, "18FgJuZRHaP1y4pK_0p9RZv4cyk1Jbrz5");
        argPlayersList[13] = new Players("ZUBER", "ZUBER Steven", "MF",
                "10","5'11", "26", "11",
                "3", "TSG 1899 Hoffenheim (GER)", nation, "1bcD5fj68QxbTaJunP_ZnHg7p1VcPj-6i");
        argPlayersList[14] = new Players("DZEMAILI", "DZEMAILI Blerim", "MF",
                "10","5'10", "32", "64",
                "9", "Bologna FC (ITA)", nation, "1tYUnYe2BvR3lMxyoPce2B07skenvWMLx");
        argPlayersList[15] = new Players("G. FERNANDES", "FERNANDES Gelson", "MF",
                "10","6'01", "31", "67",
                "2", "Eintracht Frankfurt (GER)", nation, "1f3J2ZQ_RH5hOl_Fuy8kJJpMh8U07HEyN");
        argPlayersList[16] = new Players("ZAKARIA", "ZAKARIA Denis", "MF",
                "10","6'3", "21", "10",
                "0", "VfL Borussia Mönchengladbach (GER)", nation, "1bF0zsHSFDBwJ1JuydVRtCKB8uiGzJLbR");
        argPlayersList[17] = new Players("GAVRANOVIC", "GAVRANOVIC Mario", "FW",
                "10","5'9", "28", "13",
                "5", "GNK Dinamo Zagreb (CRO)", nation, "1e1py_LN1HoRC9yLliaFf0ne6yO3hiN11");
        argPlayersList[18] = new Players("DRMIC", "DRMIC Josip", "FW",
                "10","5'11", "25", "28",
                "9", "VfL Borussia Mönchengladbach (GER)", nation, "12EeVQxXFaXoXhHQtTZL5ZoNiUQTLyALh");
        argPlayersList[19] = new Players("DJOUROU", "DJOUROU Johan", "DF",
                "10","6'3", "31", "74",
                "2", "Antalyaspor AS (TUR)", nation, "1F5RuUHwPBWb9VX098co8nB5Zsc7wz_oc");
        argPlayersList[20] = new Players("BUERKI", "BUERKI Roman", "GK",
                "10","6'2", "27", "7",
                "0", "Borussia Dortmund (GER)", nation, "1nf8b1rKJk38qGzKlWsMhKwPktkXP56eJ");
        argPlayersList[21] = new Players("SCHAER", "SCHAER Fabian", "DF",
                "10","6'1", "26", "38",
                "7", "Deportivo La Coruña (ESP)", nation, "1OY8sg5b8AaH6HCx5CG2AouEUu4GKf0fL");
        argPlayersList[22] = new Players("SHAQIRI", "SHAQIRI Xherdan", "MF",
                "10","5'6", "26", "69",
                "20", "Stoke City FC (ENG)", nation, "1svJVszjHgDwIxtxUDQyPpTCiqlVsC1eo");
        return argPlayersList;
    }

    public Players[] initTunisiaPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Tunisia";
        argPlayersList[0] = new Players("BEN MUSTAPHA", "BEN MUSTAPHA Farouk", "GK",
                "10","6'4", "28", "13",
                "0", "Al Shabab FC (KSA)", nation, "1fFxV7ceYA8M_lxvJtRg_05MkaJXTdKvV");
        argPlayersList[1] = new Players("BEN YOUSSEF .S", "BEN YOUSSEF Syam", "DF",
                "10","6'2", "29", "41",
                "1", "Kasimpasa SK (TUR)", nation, "1QHxcOXAB1nYR0XAB4D1-HiW2bTVWWdHv");
        argPlayersList[2] = new Players("BEN ALOUANE", "BEN ALOUANE Yohan", "DF",
                "10","6'2", "31", "3",
                "0", "Leicester City FC (ENG) ", nation, "18Stc23pkrMcYDp2G0X6CczPDHPRHxEDS");
        argPlayersList[3] = new Players("MERIAH", "MERIAH Yassine", "DF",
                "10","6'2", "24", "12",
                "1", "CS Sfaxien (TUN)", nation, "10aPzs8kbtKLd2PE5H3XRB-6SIZwIm8xr");
        argPlayersList[4] = new Players("HADDADI", "HADDADI Oussama", "DF",
                "10","6'2", "26", "8",
                "0", "Dijon FCO (FRA)", nation, "1TwFDWX5TvPMafiLgjM0rFNiAuu5FXycQ");
        argPlayersList[5] = new Players("BEDOUI", "BEDOUI Rami", "DF",
                "0","6'2", "28", "7",
                "64", "Étoile du Sahel (TUN)", nation, "1PxORCijV7gGQr_K6YSTWYnPPhH4DYpUM");
        argPlayersList[6] = new Players("KHAOUI", "KHAOUI Saifeddine", "FW",
                "10","5'11", "23", "2",
                "0", "ES Troyes (FRA)", nation, "1_x9PXI6Czr2LDgVyjlFQrtApUwmVGAFI");
        argPlayersList[7] = new Players("BEN YOUSSEF .F", "BEN YOUSSEF Fakhreddine", "FW",
                "10","6'4", "26", "37",
                "5", "Al Ittifaq FC (KSA)", nation, "1H8BKX9zr_GiiXaUsOHrTqT9Q5zqEaxWm");
        argPlayersList[8] = new Players("BADRI", "BADRI Anice", "MF",
                "10","5'7", "27", "9",
                "3", "ES Tunis (TUN)", nation, "1GjlOLPopY_j0uqDsVNTPmQpb3MukyCAb");
        argPlayersList[9] = new Players("KHAZRI", "KHAZRI Wahbi ", "FW",
                "10","6'0", "27", "36",
                "12", "Stade Rennais FC (FRA)", nation, "1X63O8tgUTG2emhTlmKIZgU8vVlV0UWsG");
        argPlayersList[10] = new Players("BRONN", "BRONN Dylan", "DF",
                "10","6'1", "22", "1",
                "0", "KAA Gent (BEL)", nation, "1YUWePJbAEFI_chh4qYXxU8_uXcIvBxEa");
        argPlayersList[11] = new Players("MAALOUL", "MAALOUL Ali", "DF",
                "10","5'9", "28", "33",
                "0", "Al Ahly SC (EGY)", nation, "1IRNT4HfrLzhvYaCEw-uBL7Qwki17PH8P");
        argPlayersList[12] = new Players("SASSI", "SASSI Ferjani", "MF",
                "10","6'1", "26", "39",
                "3", "Al Nassr FC (KSA)", nation, "1OZvSiKiRTM9EkqKaAzMvgaEvhaHKDGoO");
        argPlayersList[13] = new Players("BEN AMOR", "BEN AMOR Mohamed", "MF",
                "10","5'11", "26", "24",
                "2", "Al Ahli SC (KSA)", nation, "17Z6Es8TW-s74k-NoCC2nj77GhB5R8ZLC");
        argPlayersList[14] = new Players("KHALIL", "KHALIL Ahmed", "FW",
                "10","6'1", "23", "3",
                "0", "Club Africain (TUN)", nation, "1v13i7sEB5tpKBCbqGc6qcxAGcoNPDxq_");
        argPlayersList[15] = new Players("MATHLOUTHI", "MATHLOUTHI Aymen", "GK",
                "10","6'0", "33", "70",
                "0", "Al Batin FC (KSA)", nation, "1_6AwUxJWr7CTG5eYAYa3s0g6gNCGw2xX");
        argPlayersList[16] = new Players("SKHIRI JORIS", "SKHIRI Ellyes", "MF",
                "10","6'1", "23", "2",
                "0", "Montpellier HSC (FRA)", nation, "1nv4_in3v9ajrGRXTwnrgSQ2BXd606-WV");
        argPlayersList[17] = new Players("SRARFI", "SRARFI Bassem", "FW",
                "10","5'9", "20", "2",
                "0", "OGC Nice (FRA)", nation, "1kxibrN6pGPR8m8W32To5fRoATteqMA78");
        argPlayersList[18] = new Players("CHAALELI", "CHAALELI Ghaylen", "FW",
                "10","5'10", "24", "4",
                "1", "ES Tunis (TUN)", nation, "1DrDr7fUsAohi57IrS_lgJAu35jUwISlB");
        argPlayersList[19] = new Players("NAGUEZ", "NAGUEZ Hamdi", "DF",
                "10","6'5", "25", "13",
                "0", "Zamalek (EGY)", nation, "1n4rApI0MxsY_-e0pdyto25CRf3-xO_Re");
        argPlayersList[20] = new Players("KHALIFA", "KHALIFA Saber", "FW",
                "10","5'7", "31", "41",
                "7", "Club Africain (TUN)", nation, "1gJ5HoEIzyTITc-E85_WY5wHoWM9shF8K");
        argPlayersList[21] = new Players("HASSEN", "HASSEN Mouez", "GK",
                "10","6'0", "23", "3",
                "0", "LB Chateauroux (FRA)", nation, "19qghU8ouslXlyQh3hAQhsUc5rfoX7XdR");
        argPlayersList[22] = new Players("SLITI", "SLITI Naim", "FW",
                "10","5'8", "25", "16",
                "3", "Dijon FCO (FRA)", nation, "1lJjbhJp7V1ZR4Yxokp5pNntfT_bl3NfI");
        return argPlayersList;
    }

    public Players[] initUruguayPlayers(){
        Players[] argPlayersList = new Players[23];
        String nation = "Uruguay";
        argPlayersList[0] = new Players("F. MUSLERA", "MUSLERA Fernando", "GK",
                "10","6'3", "31", "96",
                "0", "Galatasaray SK (TUR)", nation, "1Bb15mVEsqvlnrZJ3-2u7_mIGHaiQYFjv");
        argPlayersList[1] = new Players("J.M. GIMENEZ", "GIMENEZ Jose", "DF",
                "10","6'1", "23", "37",
                "4", "Atletico Madrid (ESP) ", nation, "1rypecKBz9uhLhQan6j-pSSlhjELFBUYs");
        argPlayersList[2] = new Players("D. GODIN", "GODIN Diego", "DF",
                "10","6'1", "32", "116",
                "8", "Atletico Madrid (ESP) ", nation, "11IM6L9lSXttMxvFbq2NBMCW8DNfxIQcD");
        argPlayersList[3] = new Players("G. VARELA", "VARELA Guillermo", "DF",
                "10","5'8", "25", "3",
                "0", "CA Penarol (URU)", nation, "18CEJLVAf-2zTvRkbeJgr2I1f3qwBhpDr");
        argPlayersList[4] = new Players("C. SANCHEZ", "SANCHEZ Carlos", "MF",
                "10","5'7", "33", "34",
                "1", "CF Monterrey (MEX)", nation, "1sm3BlSVGdBgzN-BvromBTsOm6gQcLAtL");
        argPlayersList[5] = new Players(" R. BENTANCUR", "BENTANCUR Rodrigo", "MF",
                "10","6'1", "20", "6",
                "0", "Juventus FC (ITA)", nation, "18R4e9VgHk3zzIv8vP85a_UgFw7w7Ad3O");
        argPlayersList[6] = new Players("C. RODRIGUEZ", "RODRIGUEZ Cristian", "MF",
                "10","5'10", "32", "103",
                "11", "CA Penarol (URU)", nation, "13Or2DLqsovbihfMYox2e095D9asc9aoi");
        argPlayersList[7] = new Players("N. NANDEZ ", "NANDEZ Nahitan", "MF",
                "10","5'7", "22", "11",
                "0", "CA Boca Juniors (ARG)", nation, "1rJleXMPM2yctlJB0zq6UrWs5WDaAVKwh");
        argPlayersList[8] = new Players("L. SUAREZ", "SUAREZ Luis", "FW",
                "10","6'0", "31", "97",
                "50", "FC Barcelona (ESP)", nation, "1fNh4faD72ZkQ_zTRKgNqH3gloWXU2Cog");
        argPlayersList[9] = new Players("G. DE ARRASCAETA", "DE ARRASCAETA Giorgian", "FW",
                "10","5'8", "24", "13",
                "1", "Cruzeiro EC (BRA)", nation, "1IexSV-ra8uqzqyseTVuc_YuUurUtleE1");
        argPlayersList[10] = new Players("C. STUANI", "STUANI Cristhian", "FW",
                "10","6'1", "31", "40",
                "5", "Girona FC (ESP)", nation, "1Wh4T0kBBYni3pkxhuUra_9aFRgRPoWyD");
        argPlayersList[11] = new Players("M. CAMPAÑA", "CAMPANA Martin", "GK",
                "10","6'2", "29", "1",
                "0", "CA Independiente (ARG)", nation, "1KaCpijLRNzX7tqYyCShmyDLaTUXpzcoD");
        argPlayersList[12] = new Players("G. SILVA", "SILVA Gaston", "DF",
                "10","6'1", "24", "12",
                "0", "CA Independiente (ARG)", nation, "1L1fu2uTsL3jOZJnM6VWZO1buyH-YpmHh");
        argPlayersList[13] = new Players("L. TORREIRA", "TORREIRA Lucas", "MF",
                "10","5'6", "22", "1",
                "0", "UC Sampdoria (ITA)", nation, "1ZR46UOZtyq1PRcordomuWyNvOKWPaKuG");
        argPlayersList[14] = new Players(" M. VECINO", "VECINO Matias", "MF",
                "10","6'2", "26", "19",
                "1", "FC Internazionale (ITA)", nation, "1JcpYFGTGXeDHLIImvBXveCPxqCEmw_qC");
        argPlayersList[15] = new Players("M. PEREIRA", "PEREIRA Maximiliano", "DF",
                "10","5'8", "33", "124",
                "3", "FC Porto (POR)", nation, "17XMktTAlyWLCwHzIWXETcMjgDxNgfiBx");
        argPlayersList[16] = new Players("D. LAXALT", "LAXALT Diego", "MF",
                "10","5'10", "25", "5",
                "0", "Genoa CFC (ITA)", nation, "1CoPy-7blR07ActWNOyh-MQQ3V70Vd6RO");
        argPlayersList[17] = new Players("M. GOMEZ", "GOMEZ Maximiliano", "FW",
                "10","6'1", "21", "2",
                "0", "Celta Vigo (ESP)", nation, "1WNaZqrC2B9iu9r3VHcxntRcZGxNQCyip");
        argPlayersList[18] = new Players("S. COATES", "COATES Sebastian", "DF",
                "10","6'5", "27", "26",
                "1", "Sporting CP (POR)", nation, "1dSjUwcpsU2_hlizp12WklaRYQgdeE9H8");
        argPlayersList[19] = new Players("J. URRETAVISCAYA", "URRETAVISCAYA Jonathan", "FW",
                "10","5'9", "28", "4",
                "0", "CF Monterrey (MEX)", nation, "10F7kB9np_5CI1WUqF0Eki0KbqgelB5h6");
        argPlayersList[20] = new Players("E. CAVANI", "CAVANI Edinson", "FW",
                "10","6'1", "31", "100",
                "42", "Paris Saint-Germain FC (FRA)", nation, "1p2xGJNgZilmjB-0tnAfTmBp-jDcOaUUE");
        argPlayersList[21] = new Players("M. CACERES", "CACERES Martin", "DF",
                "10","5'11", "31", "124",
                "64", "SS Lazio (ITA)", nation, "1VmAgCYXDnbGX0zebZQPgwZAvqA6ssbvI");
        argPlayersList[22] = new Players("M. SILVA", "SILVA Martin", "GK",
                "10","6'2", "35", "9",
                "0", "CR Vasco da Gama (BRA)", nation, "1Ep438_jUcmGK9LL3D2r8C3u1Pm0b620p");
        return argPlayersList;
    }
}
