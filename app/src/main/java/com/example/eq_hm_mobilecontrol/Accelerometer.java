package com.example.eq_hm_mobilecontrol;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Accelerometer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Accelerometer extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LOWCUTFREQ = "argLowCutFreq";
    private static final String ARG_LOWCUTSLOPE = "argLowCutSlope";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //--------------------------
    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    //private static final DecimalFormat df = new DecimalFormat("0", dfs);

    private SensorManager sensorManager;
    Sensor accelerometer;

    private TextView acc_x_text;
    private TextView acc_y_text;
    private TextView acc_z_text;

    public float x_acc_val;
    public float y_acc_val;
    public float z_acc_val;
    //---------------------------

    private TextView acclowCutF_TextView;
    private SeekArc acclowCutF_SeekArc;
    private TextView acclowCutS_TextView;
    private SeekArc acclowCutS_SeekArc;

    int freq_progress = 50;
    int slope_progress = 1;

    int counter = 0;
    boolean ifopen = false;

    public Accelerometer() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        ifopen = false;
        BackgroundAsSingleton.getInstance().t_acc_state();
        super.onStart();
    }

    @Override
    public void onResume() {
        ifopen = true;
        BackgroundAsSingleton.getInstance().t_acc_state();
        super.onResume();
    }

    @Override
    public void onPause() {
        ifopen = false;
        BackgroundAsSingleton.getInstance().f_acc_state();
        super.onPause();
    }

    @Override
    public void onStop() {
        ifopen = false;
        BackgroundAsSingleton.getInstance().f_acc_state();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        ifopen = false;
        BackgroundAsSingleton.getInstance().f_acc_state();
        super.onDestroy();
    }

    // TODO: Rename and change types and number of parameters
    public static Accelerometer newInstance(List<ProgressDescription> singList, int acc_lcfp, int acc_lcsp) {
        Accelerometer fragment = new Accelerometer();
        Bundle args = new Bundle();
        args.putInt(ARG_LOWCUTFREQ, acc_lcfp);
        args.putInt(ARG_LOWCUTSLOPE, acc_lcsp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //freq_progress = getArguments().getInt(ARG_LOWCUTFREQ);
            //slope_progress = getArguments().getInt(ARG_LOWCUTSLOPE);
        }

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Accelerometer.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x_acc_val = event.values[0]; //zbieranie wartości
        y_acc_val = event.values[1];
        z_acc_val = event.values[2];

        if (x_acc_val > 9f) x_acc_val = 10f; //zaokraglenia
        if (x_acc_val < -9f) x_acc_val = -10f;
        if (y_acc_val > 9f) y_acc_val = 10f;
        if (y_acc_val < -9f) y_acc_val = -10f;
        if (z_acc_val > 9f) z_acc_val = 10f;
        if (z_acc_val < -9f) z_acc_val = -10f;
        acc_x_text.setText("X: " + df.format(x_acc_val));
        acc_y_text.setText("Y: " + df.format(y_acc_val));
        acc_z_text.setText("Z: " + df.format(z_acc_val));

        //---- acc param test ---
        int acc_f_prog = 1000 - (int)x_acc_val * 100;
        int acc_s_prog = 2 - (int)(y_acc_val * 0.2);
        if (acc_s_prog < 0) acc_s_prog = 0;
        if (acc_s_prog > 3) acc_s_prog = 3;
        if (acc_f_prog < 20) acc_f_prog = 0;
        if (acc_f_prog > 20000) acc_f_prog = 19980;

        acclowCutF_SeekArc.setProgress(acc_f_prog); //ustawianie kóleczka w aplikacji
        acclowCutS_SeekArc.setProgress(acc_s_prog);

        if (ifopen && BackgroundAsSingleton.getInstance().acc_open) {
            BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("lcfp", 0, acc_f_prog);
            BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("lcsp", 1, acc_s_prog);
            BackgroundAsSingleton.getInstance().executeAsyncTask(); //wysylka na serwer
        }
        else { }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        //---- ACCELEROMETER ----
        acc_x_text = (TextView) view.findViewById(R.id.accelerometer_x_val);
        acc_y_text = (TextView) view.findViewById(R.id.accelerometer_y_val);
        acc_z_text = (TextView) view.findViewById(R.id.accelerometer_z_val);

        //---- low cut freq ----
        acclowCutF_TextView = (TextView) view.findViewById(R.id.acclowCutFrequencyText);

        acclowCutF_SeekArc = (SeekArc) view.findViewById(R.id.acclowCutFrequencySeekArc);

        acclowCutF_SeekArc.setProgress(freq_progress);
        acclowCutF_TextView.setText(String.valueOf(20 + (freq_progress)) + " Hz");

        acclowCutF_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress);

                acclowCutF_TextView.setText(String.valueOf(progress) + " Hz");
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {}
        });

        //---- low cut slope ----
        acclowCutS_TextView = (TextView) view.findViewById(R.id.acclowCutSlopeText);

        acclowCutS_SeekArc = (SeekArc) view.findViewById(R.id.acclowCutSlopeSeekArc);

        acclowCutS_SeekArc.setProgress(slope_progress);
        acclowCutS_TextView.setText(String.valueOf(12 + (slope_progress * 12)) + " dB/Oct");

        acclowCutS_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                slope_progress = progress;

                progress = 12 + (progress * 12);

                acclowCutS_TextView.setText(String.valueOf(progress) + " dB/Oct");
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {}
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        ifopen = false;
        super.onDestroyView();
    }
}