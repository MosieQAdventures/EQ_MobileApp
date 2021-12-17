package com.example.eq_hm_mobilecontrol;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.slider.Slider.OnSliderTouchListener;
import com.google.android.material.snackbar.Snackbar;
import com.triggertrap.seekarc.SeekArc;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighCut#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighCut extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HIGHCUTFREQ = "argHighCutFreq";
    private static final String ARG_HIGHCUTSLOPE = "argHighCutSlope";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView highCutF_TextView;
    private SeekArc highCutF_SeekArc;
    private TextView highCutS_TextView;
    private SeekArc highCutS_SeekArc;

    int freq_progress;
    int slope_progress;

    public HighCut() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HighCut newInstance(List<ProgressDescription> singList, int hcfp, int hcsp) {
        HighCut fragment = new HighCut();
        Bundle args = new Bundle();
        args.putInt(ARG_HIGHCUTFREQ, hcfp);
        args.putInt(ARG_HIGHCUTSLOPE, hcsp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            freq_progress = getArguments().getInt(ARG_HIGHCUTFREQ);
            slope_progress = getArguments().getInt(ARG_HIGHCUTSLOPE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_HIGHCUTFREQ, freq_progress);
        outState.putInt(ARG_HIGHCUTSLOPE, slope_progress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_high_cut, container, false);

        //---- high cut freq ----
        highCutF_TextView = (TextView) view.findViewById(R.id.highCutFrequencyText);

        highCutF_SeekArc = (SeekArc) view.findViewById(R.id.highCutFrequencySeekArc);

        //Singleton values
        freq_progress = Singleton.getInstance().getProgressDescriptionList().get(11).progress;

        highCutF_SeekArc.setProgress(freq_progress);
        highCutF_TextView.setText(String.valueOf(20 + (freq_progress * 10)) + " Hz");

        highCutF_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress * 10);

                highCutF_TextView.setText(String.valueOf(progress) + " Hz");

                Singleton.getInstance().setProgressDescriptionIndividualListValue("hcfp", 11, freq_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {}
        });

        //---- high cut slope ----
        highCutS_TextView = (TextView) view.findViewById(R.id.highCutSlopeText);

        highCutS_SeekArc = (SeekArc) view.findViewById(R.id.highCutSlopeSeekArc);

        //Singleton values
        slope_progress = Singleton.getInstance().getProgressDescriptionList().get(12).progress;

        highCutS_SeekArc.setProgress(slope_progress);
        highCutS_TextView.setText(String.valueOf(12 + (slope_progress * 12)) + " dB/Oct");

        highCutS_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                slope_progress = progress;

                progress = 12 + (progress * 12);

                highCutS_TextView.setText(String.valueOf(progress) + " dB/Oct");

                Singleton.getInstance().setProgressDescriptionIndividualListValue("hcsp", 12, slope_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {}
        });

        return view;
    }
}