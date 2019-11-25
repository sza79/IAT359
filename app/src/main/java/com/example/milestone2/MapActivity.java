package com.example.milestone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    //initialize googlemap
    GoogleMap map;
    private Marker firstHospital;
    private Marker secondSpot;



    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static final int LOCATION_REQUEST= 1340;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    //setting map
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //asking for permission
        requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);

        LatLng surreySFU = new LatLng(49.1867, -122.8494);
//        map.animateCamera( CameraUpdateFactory.zoomTo( 15.0f ) );
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(surreySFU, 15.0f));

        if (map != null) {
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.marker_info, null);
                    ImageView img = v.findViewById(R.id.imageView);
                    TextView title = v.findViewById(R.id.title);
                    TextView description = v.findViewById(R.id.description);
                    TextView address = v.findViewById(R.id.address);

                    if (marker.getTitle().equals("Surrey Memorial Hospital")) {
                        img.setImageResource(R.drawable.fraser_health);
                    } else if (marker.getTitle().equals("Canadian Blood Services")) {
                        img.setImageResource(R.drawable.cbs);
                    }
                    title.setText(marker.getTitle());
                    String rawString = marker.getSnippet();
                    String[] split = rawString.split("&");
                    description.setText(split[0]);
                    address.setText(split[1]);
                    return v;
                }
            });
        }

        firstHospital = map.addMarker(new MarkerOptions()
                .position(new LatLng(49.176088, -122.842598 ))
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.surrey_memorial_hospital))
                .snippet("Publicly funded hospital owned and operated by Fraser Health&13750 96 Ave, Surrey, BC V3V 1Z2")
                .title("Surrey Memorial Hospital"));

        secondSpot = map.addMarker(new MarkerOptions()
                .position(new LatLng(49.186689, -122.798725))
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.surrey_memorial_hospital))
                .snippet("Not-for-profit charitable organization that operates independently from government&15285 101 Ave, Surrey, BC V3R 8X8")
                .title("Canadian Blood Services"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        map.setMyLocationEnabled(true);
    }


}
