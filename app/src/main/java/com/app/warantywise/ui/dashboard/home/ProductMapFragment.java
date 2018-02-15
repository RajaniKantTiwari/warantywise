package com.app.warantywise.ui.dashboard.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.warantywise.R;
import com.app.warantywise.databinding.FragmentLocationMapBinding;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ProductResponse;
import com.app.warantywise.ui.dashboard.DashboardFragment;
import com.app.warantywise.ui.dashboard.adapter.MarkerInfoWindowAdapter;
import com.app.warantywise.ui.event.MerchantEvent;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.CommonUtility;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static com.app.warantywise.utility.AppConstants.PERMISSIONS_REQUEST_LOCATION;
import static com.app.warantywise.utility.AppConstants.REQUEST_CALL;


public class ProductMapFragment extends DashboardFragment implements OnMapReadyCallback,MarkerInfoWindowAdapter.MarkerInfoListener {

    private GoogleMap mMap;
    private FragmentLocationMapBinding mBinder;
    private SupportMapFragment mapFragment;
    private HashMap<Marker, ProductResponse> mMarkersHashMap;
    private Intent callIntent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_location_map, container, false);
        CommonUtility.register(this);
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.add_product));
        if (CommonUtility.checkService(getBaseActivity())) {
            initMap();
        }
        return mBinder.getRoot();
    }

    private void initMap() {
        mMarkersHashMap = new HashMap();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getCurrentLocation();

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        } else {
            SmartLocation.with(getBaseActivity()).location()
                    .start(new OnLocationUpdatedListener() {
                        @Override
                        public void onLocationUpdated(Location location) {
                            moveCamera(new LatLng(location.getLatitude(), location.getLongitude()));
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });
    }

    @Subscribe
    public void onEvent(MerchantEvent event) {
        if (event.getListMap() == AppConstants.LIST_PRODUCT) {
            mBinder.layoutMap.setVisibility(View.GONE);
        } else if (event.getListMap() == AppConstants.MAP_PRODUCT) {
            mBinder.layoutMap.setVisibility(View.VISIBLE);
            showMarkerList(event.getProductList());
        }
    }

    private void showMarkerList(ArrayList<ProductResponse> meetingEventList) {
        if (CommonUtility.isNotNull(meetingEventList)) {
            for (ProductResponse response : meetingEventList) {
                addMarker(response);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (CommonUtility.isNull(googleMap))
            return;
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //Set Custom InfoWindow Adapter
        MarkerInfoWindowAdapter adapter = new MarkerInfoWindowAdapter(getBaseActivity(),
                mMarkersHashMap,this);
        mMap.setInfoWindowAdapter(adapter);
        checkPermition();
    }

    private void checkPermition() {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

        } else {
            mMap.setMyLocationEnabled(true);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermition();
                    getCurrentLocation();
                }
                break;
            case REQUEST_CALL:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            }else {
                getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
            }

        }
    }

    private void addMarker(ProductResponse response) {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (CommonUtility.isNotNull(mMap)&&CommonUtility.isNotNull(response)) {
            LatLng latLng = new LatLng(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.camera)));
            mMarkersHashMap.put(marker, response);
            if(CommonUtility.isNotNull(response)&&response.getId().equalsIgnoreCase("0")){
                ShowMarker(marker,response);
                marker.showInfoWindow();
            }
        }
    }

    private void ShowMarker(Marker marker, ProductResponse response) {
        try {
            marker.showInfoWindow();
            Geocoder geocoder = new Geocoder(getBaseActivity(), Locale.getDefault());
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()), 1);
            String address = addresses.get(0).getAddressLine(0);
            if(CommonUtility.isNotNull(address)){
                if(address.length()>AppConstants.MAX_LENGTH) {
                    marker.setTitle(address.substring(0, AppConstants.MAX_LENGTH));
                }else {
                    marker.setTitle(address);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveCamera(LatLng latLng) {
        if(CommonUtility.isNotNull(mMap)){
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.camera)));
            mMarkersHashMap.put(marker, new ProductResponse());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .bearing(AppConstants.BEARING)
                    .tilt(AppConstants.TILT)
                    .zoom(AppConstants.MAX_ZOOM)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return ProductMapFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(CommonUtility.isNotNull(mapFragment)){
            mapFragment.onLowMemory();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(CommonUtility.isNotNull(mapFragment)){
            mapFragment.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(CommonUtility.isNotNull(mapFragment)){
            mapFragment.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(CommonUtility.isNotNull(mapFragment)){
            mapFragment.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(CommonUtility.isNotNull(mapFragment)){
            mapFragment.onStop();
        }
    }

    @Override
    public void call(String mobileNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mobileNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }


    @Override
    public void view(String view) {

    }
}
