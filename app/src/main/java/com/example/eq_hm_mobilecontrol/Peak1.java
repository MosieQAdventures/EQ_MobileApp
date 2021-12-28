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
 * Use the {@link Peak1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Peak1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PEAK1FREQ = "argPeak1Freq";
    private static final String ARG_PEAK1GAIN = "argPeak1Gain";
    private static final String ARG_PEAK1Q = "argPeak1Q";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView peak1F_TextView;
    private SeekArc peak1F_SeekArc;
    private TextView peak1G_TextView;
    private SeekArc peak1G_SeekArc;
    private TextView peak1Q_TextView;
    private SeekArc peak1Q_SeekArc;

    int freq_progress;
    int gain_progress;
    int quality_progress;

    public Peak1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Peak1 newInstance(List<ProgressDescription> singList, int p1fp, int p1gp, int p1qp) {
        Peak1 fragment = new Peak1();
        Bundle args = new Bundle();
        args.putInt(ARG_PEAK1FREQ, p1fp);
        args.putInt(ARG_PEAK1GAIN, p1gp);
        args.putInt(ARG_PEAK1Q, p1qp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            freq_progress = getArguments().getInt(ARG_PEAK1FREQ);
            gain_progress = getArguments().getInt(ARG_PEAK1GAIN);
            quality_progress = getArguments().getInt(ARG_PEAK1Q);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_PEAK1FREQ, freq_progress);
        outState.putInt(ARG_PEAK1GAIN, gain_progress);
        outState.putInt(ARG_PEAK1Q, quality_progress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_peak1, container, false);

        //---- peak 1 freq ----
        peak1F_TextView = (TextView) view.findViewById(R.id.peak1FrequencyText);

        peak1F_SeekArc = (SeekArc) view.findViewById(R.id.peak1FrequencySeekArc);

        //Singleton values
        freq_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(2).progress;

        peak1F_SeekArc.setProgress(freq_progress);
        peak1F_TextView.setText(String.valueOf(20 + (freq_progress)) + " Hz");

        peak1F_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress);

                peak1F_TextView.setText(String.valueOf(progress) + " Hz");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p1fp", 2, freq_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 1 gain ----
        peak1G_TextView = (TextView) view.findViewById(R.id.peak1GainText);

        peak1G_SeekArc = (SeekArc) view.findViewById(R.id.peak1GainSeekArc);

        //Singleton values
        gain_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(3).progress;

        peak1G_SeekArc.setProgress(gain_progress);
        peak1G_TextView.setText(String.valueOf((-240 + (gain_progress))/10.0) + " dB");

        peak1G_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                gain_progress = progress;

                progress = -240 + (progress);

                double dprogress = (progress)/10.0;

                peak1G_TextView.setText(String.valueOf(dprogress) + " dB");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p1gp", 3, gain_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 1 quality ----
        peak1Q_TextView = (TextView) view.findViewById(R.id.peak1QText);

        peak1Q_SeekArc = (SeekArc) view.findViewById(R.id.peak1QSeekArc);

        //Singleton values
        quality_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(4).progress;

        peak1Q_SeekArc.setProgress(quality_progress);
        peak1Q_TextView.setText(String.valueOf((quality_progress)/10.0));

        peak1Q_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                quality_progress = progress;

                double dprogress = (progress)/10.0;

                peak1Q_TextView.setText(String.valueOf(dprogress));

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p1qp", 4, quality_progress);
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