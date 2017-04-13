package com.example.demomap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.lang.reflect.Array;
import java.util.ArrayList;






import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class
MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Polygon polygon,polygon2;
    private Polyline polyline;
    private Polyline line;
    private Double pLat, pLng;
    private int flag=0;
    private int kLng,kLat;
    ArrayList<String> imagePath = new ArrayList<String>();
    Connection con = new Connection();


    int day , month , year , minute , hour , d ;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    ArrayList<String> dateTemp = new ArrayList<String>();
    private GoogleApiClient client;


    public static final String url = "http://localhost:58994/untitled1/.idea/connect.php";




    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




        Button Btnview = (Button) this.findViewById(R.id.ButtonView);
        TextView T1 = (TextView) this.findViewById(R.id.Lat);
        TextView T2 = (TextView) this.findViewById(R.id.Long);
        Button setDate = (Button)this.findViewById(R.id.btnDate);
        final TextView date = (TextView) this.findViewById(R.id.TvDate);



        setDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
                d = c.get(Calendar.DAY_OF_WEEK);


                DatePickerDialog dpd = new DatePickerDialog(MapsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                date.setText(new SimpleDateFormat("EEEE dd-MM-yyyy").format(calendar.getTime()));

                                dateTemp.clear();


                            }
                        }, year, month, day);

                dpd.show();

            }


        });



// show Dialog
        Btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                new FetchDataFromServer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            }
        });




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final TextView textLat = (TextView) this.findViewById(R.id.TextLat);
        final TextView textLong = (TextView) this.findViewById(R.id.TextLng);


        polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(15, 95),
                        new LatLng(15, 105.08),
                        new LatLng(4.92, 105.08),
                        new LatLng(4.92, 95))
                .strokeColor(Color.RED).strokeWidth(1)
                .fillColor(Color.parseColor("#30000000")));

        float lat = 15;
        float lng = 95;
        int i = 0;
        for (i = 0; i < 122; i++) {
            line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(lat, 95), new LatLng(lat, 105.08))
                    .width(1)
                    .color(Color.RED));

            line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(15, lng), new LatLng(4.92, lng))
                    .width(1)
                    .color(Color.RED));

            lat = (float) (lat - 0.0826);
            lng = (float) (lng + 0.0826);
        }

        LatLng coordinateThailand = new LatLng(14, 100);
        mMap.addMarker(new MarkerOptions().position(coordinateThailand).title("Thailand"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinateThailand));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinateThailand,10));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng latLng) {

                Log.e("latlong", latLng.latitude + "-" + latLng.longitude);
                textLat.setText(""+latLng.latitude);
                textLong.setText(""+latLng.longitude);

                Double f1 = latLng.latitude;
                Double f2 = latLng.longitude;


                pLng = f2%95;
                pLat = f1%15;
                Log.i("test", pLat + "-" + pLng);

               pLng=pLng/0.0826;
                pLat=pLat/0.0826;
                kLng =pLng.intValue();
                 kLat =pLat.intValue();
                kLat=kLat-60;
                Log.i("test2", kLat + "-" + kLng);

                if (flag>0)
                {polygon2.remove();}


                if(kLat>=0&kLat<=121&kLng>=0&kLng<=121) {
                    polygon2 = mMap.addPolygon(new PolygonOptions()
                            .add(new LatLng(4.92 + (0.0826 * kLat), 95 + (0.0826 * kLng)),
                                    new LatLng(4.92 + (0.0826 * kLat), 95 + (0.0826 * kLng) + 0.0826),
                                    new LatLng(4.92 + (0.0826 * kLat) + 0.0826, 95 + (0.0826 * kLng) + 0.0826),
                                    new LatLng(4.92 + (0.0826 * kLat) + 0.0826, 95 + (0.0826 * kLng)))
                            .strokeColor(Color.BLUE).strokeWidth(1)
                            .fillColor(Color.parseColor("#283593")));
                }
                flag=flag+1;


                kLat=121-kLat; //point array




            }



        });


    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.demomap/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.demomap/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }




















    public class FetchDataFromServer extends AsyncTask<Void , Void , Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(MapsActivity.this);



        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }


        @Override
        protected Void doInBackground(Void... params)
        {
            con.getData();
            imagePath=con.ExtractDataFromJson();
            for(int i = 0 ; i <imagePath.size();i++)
            {
                Log.e("Data",imagePath.get(i).toString() );
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            final Dialog dialog = new Dialog(MapsActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.customdialog);
            dialog.setCancelable(true);


            Button button1 = (Button)dialog.findViewById(R.id.btn1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Close dialog",Toast.LENGTH_SHORT);
                    dialog.cancel();
                }
            });


            Log.e("After loaded image",imagePath.size()+"");

            TextView textView1 = (TextView)dialog.findViewById(R.id.dialogtext);
            TextView pv1 = (TextView)dialog.findViewById(R.id.point1);
            TextView pv2 = (TextView)dialog.findViewById(R.id.point2);
            ImageView image = (ImageView) dialog.findViewById(R.id.imagePath);
            image.setImageBitmap(con.loadBitmap(imagePath.get(0).toString() ) );

            if(kLat>=0&kLat<=121&kLng>=0&kLng<=121)
            { //point array
                pv1.setText("column :" + kLng);
                pv2.setText("row :" + kLat);
            }
            else
            { pv1.setText("column :"+"false");
                pv2.setText("row :"+"false");
            }
            pdLoading.dismiss();
            pdLoading.cancel();
            super.onPostExecute(aVoid);
            dialog.show();

        }
    }















































}
