package com.example.eq_hm_mobilecontrol;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LowCut#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LowCut extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LOWCUTFREQ = "argLowCutFreq";
    private static final String ARG_LOWCUTSLOPE = "argLowCutSlope";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView lowCutF_TextView;
    private SeekArc lowCutF_SeekArc;
    private TextView lowCutS_TextView;
    private SeekArc lowCutS_SeekArc;

    int freq_progress;
    int slope_progress;

    //------------

    public LowCut() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LowCut newInstance(List<ProgressDescription> singList, int lcfp, int lcsp) {
        LowCut fragment = new LowCut();
        Bundle args = new Bundle();
        args.putInt(ARG_LOWCUTFREQ, lcfp);
        args.putInt(ARG_LOWCUTSLOPE, lcsp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            freq_progress = getArguments().getInt(ARG_LOWCUTFREQ);
            slope_progress = getArguments().getInt(ARG_LOWCUTSLOPE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_LOWCUTFREQ, freq_progress);
        outState.putInt(ARG_LOWCUTSLOPE, slope_progress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_low_cut, container, false);

        //---- low cut freq ----
        lowCutF_TextView = (TextView) view.findViewById(R.id.lowCutFrequencyText);

        lowCutF_SeekArc = (SeekArc) view.findViewById(R.id.lowCutFrequencySeekArc);

        //Singleton values
        freq_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(0).progress;

        lowCutF_SeekArc.setProgress(freq_progress);
        lowCutF_TextView.setText(String.valueOf(20 + (freq_progress)) + " Hz");

        lowCutF_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress);

                lowCutF_TextView.setText(String.valueOf(progress) + " Hz");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("lcfp", 0, freq_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- low cut slope ----
        lowCutS_TextView = (TextView) view.findViewById(R.id.lowCutSlopeText);

        lowCutS_SeekArc = (SeekArc) view.findViewById(R.id.lowCutSlopeSeekArc);

        //Singleton values
        slope_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(1).progress;

        lowCutS_SeekArc.setProgress(slope_progress);
        lowCutS_TextView.setText(String.valueOf(12 + (slope_progress * 12)) + " dB/Oct");

        lowCutS_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                slope_progress = progress;

                progress = 12 + (progress * 12);

                lowCutS_TextView.setText(String.valueOf(progress) + " dB/Oct");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("lcsp", 1, slope_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        return view;
    }
}