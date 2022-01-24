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
 * Use the {@link Peak2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Peak2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PEAK2FREQ = "argPeak2Freq";
    private static final String ARG_PEAK2GAIN = "argPeak2Gain";
    private static final String ARG_PEAK2Q = "argPeak2Q";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView peak2F_TextView;
    private SeekArc peak2F_SeekArc;
    private TextView peak2G_TextView;
    private SeekArc peak2G_SeekArc;
    private TextView peak2Q_TextView;
    private SeekArc peak2Q_SeekArc;

    int freq_progress;
    int gain_progress;
    int quality_progress;

    public Peak2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Peak2 newInstance(List<ProgressDescription> singList, int p2fp, int p2gp, int p2qp) {
        Peak2 fragment = new Peak2();
        Bundle args = new Bundle();
        args.putInt(ARG_PEAK2FREQ, p2fp);
        args.putInt(ARG_PEAK2GAIN, p2gp);
        args.putInt(ARG_PEAK2Q, p2qp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            freq_progress = getArguments().getInt(ARG_PEAK2FREQ);
            gain_progress = getArguments().getInt(ARG_PEAK2GAIN);
            quality_progress = getArguments().getInt(ARG_PEAK2Q);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_PEAK2FREQ, freq_progress);
        outState.putInt(ARG_PEAK2GAIN, gain_progress);
        outState.putInt(ARG_PEAK2Q, quality_progress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peak2, container, false);

        //---- peak 2 freq ----
        peak2F_TextView = (TextView) view.findViewById(R.id.peak2FrequencyText);

        peak2F_SeekArc = (SeekArc) view.findViewById(R.id.peak2FrequencySeekArc);

        //Singleton values
        freq_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(5).progress;

        peak2F_SeekArc.setProgress(freq_progress);
        peak2F_TextView.setText(String.valueOf(20 + (freq_progress)) + " Hz");

        peak2F_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress);

                peak2F_TextView.setText(String.valueOf(progress) + " Hz");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p2fp", 5, freq_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 2 gain ----
        peak2G_TextView = (TextView) view.findViewById(R.id.peak2GainText);

        peak2G_SeekArc = (SeekArc) view.findViewById(R.id.peak2GainSeekArc);

        //Singleton values
        gain_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(6).progress;

        peak2G_SeekArc.setProgress(gain_progress);
        peak2G_TextView.setText(String.valueOf((-240 + (gain_progress))/10.0) + " dB");

        peak2G_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                gain_progress = progress;

                progress = -240 + (progress);

                double dprogress = (progress)/10.0;

                peak2G_TextView.setText(String.valueOf(dprogress) + " dB");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p2gp", 6, gain_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 2 quality ----
        peak2Q_TextView = (TextView) view.findViewById(R.id.peak2QText);

        peak2Q_SeekArc = (SeekArc) view.findViewById(R.id.peak2QSeekArc);

        //Singleton values
        quality_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(7).progress;

        peak2Q_SeekArc.setProgress(quality_progress);
        peak2Q_TextView.setText(String.valueOf((quality_progress)/10.0));

        peak2Q_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                quality_progress = progress;

                double dprogress = (1+progress)/10.0;

                peak2Q_TextView.setText(String.valueOf(dprogress));

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p2qp", 7, quality_progress);
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