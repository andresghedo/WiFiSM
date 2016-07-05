package com.sm.app.alert.sghedoni.andrea.dev.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sm.app.alert.sghedoni.andrea.dev.R;
import com.sm.app.alert.sghedoni.andrea.dev.service.PositionService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected static final String TAG = "[DebApp]ServiceFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Switch switchGeofenceGoogleAPIs = null;
    private Switch switchPolling5s = null;
    private Switch switchBetterApproach = null;
    private View view;

    public ServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceFragment newInstance(String param1, String param2) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_service, container, false);

        this.switchGeofenceGoogleAPIs = (Switch) view.findViewById(R.id.switchGoogleAPIsStrategy);
        this.switchGeofenceGoogleAPIs.setOnCheckedChangeListener(this);
        this.switchPolling5s = (Switch) view.findViewById(R.id.switchPollingStrategy);
        this.switchPolling5s.setOnCheckedChangeListener(this);
        this.switchBetterApproach = (Switch) view.findViewById(R.id.switchBetterApproachStrategy);
        this.switchBetterApproach.setOnCheckedChangeListener(this);
        // Inflate the layout for this fragment
        return this.view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "Strategy switch " + (buttonView.getId() == R.id.switchGoogleAPIsStrategy) + " checked with value: " + isChecked);
        switch(buttonView.getId()){
            case R.id.switchGoogleAPIsStrategy:
                Log.d(TAG, "Event on switch GoogleAPIs Strategy!");
                break;
            case R.id.switchPollingStrategy:
                Log.d(TAG, "Event on switch Polling 5 sec Strategy!");
                this.managePollingStrategyService(isChecked);
                break;
            case R.id.switchBetterApproachStrategy:
                Log.d(TAG, "Event on switch Better Approach Strategy!");
                break;
        }
    }

    private void managePollingStrategyService(boolean status) {
        if (status) {
            getContext().startService(new Intent(getContext(), PositionService.class));
            Log.d(TAG, "Starting PollingService ....... ");
        } else {
            getContext().stopService(new Intent(getContext(), PositionService.class));
            Log.d(TAG, "Stopping PollingService ....... ");
        }
    }
}
