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
 * Use the {@link Peak3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Peak3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PEAK3FREQ = "argPeak3Freq";
    private static final String ARG_PEAK3GAIN = "argPeak3Gain";
    private static final String ARG_PEAK3Q = "argPeak3Q";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView peak3F_TextView;
    private SeekArc peak3F_SeekArc;
    private TextView peak3G_TextView;
    private SeekArc peak3G_SeekArc;
    private TextView peak3Q_TextView;
    private SeekArc peak3Q_SeekArc;

    int freq_progress;
    int gain_progress;
    int quality_progress;

    public Peak3() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Peak3 newInstance(List<ProgressDescription> singList, int p3fp, int p3gp, int p3qp) {
        Peak3 fragment = new Peak3();
        Bundle args = new Bundle();
        args.putInt(ARG_PEAK3FREQ, p3fp);
        args.putInt(ARG_PEAK3GAIN, p3gp);
        args.putInt(ARG_PEAK3Q, p3qp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            freq_progress = getArguments().getInt(ARG_PEAK3FREQ);
            gain_progress = getArguments().getInt(ARG_PEAK3GAIN);
            quality_progress = getArguments().getInt(ARG_PEAK3Q);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_PEAK3FREQ, freq_progress);
        outState.putInt(ARG_PEAK3GAIN, gain_progress);
        outState.putInt(ARG_PEAK3Q, quality_progress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peak3, container, false);

        //---- peak 3 freq ----
        peak3F_TextView = (TextView) view.findViewById(R.id.peak3FrequencyText);

        peak3F_SeekArc = (SeekArc) view.findViewById(R.id.peak3FrequencySeekArc);

        //Singleton values
        freq_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(8).progress;

        peak3F_SeekArc.setProgress(freq_progress);
        peak3F_TextView.setText(String.valueOf(20 + (freq_progress)) + " Hz");

        peak3F_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                freq_progress = progress;

                progress = 20 + (progress);

                peak3F_TextView.setText(String.valueOf(progress) + " Hz");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p3fp", 8, freq_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 3 gain ----
        peak3G_TextView = (TextView) view.findViewById(R.id.peak3GainText);

        peak3G_SeekArc = (SeekArc) view.findViewById(R.id.peak3GainSeekArc);

        //Singleton values
        gain_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(9).progress;

        peak3G_SeekArc.setProgress(gain_progress);
        peak3G_TextView.setText(String.valueOf((-240 + (gain_progress))/10.0) + " dB");

        peak3G_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                gain_progress = progress;

                progress = -240 + (progress);

                double dprogress = (progress)/10.0;

                peak3G_TextView.setText(String.valueOf(dprogress) + " dB");

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p3gp", 9, gain_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {}
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                BackgroundAsSingleton.getInstance().executeAsyncTask();
            }
        });

        //---- peak 3 quality ----
        peak3Q_TextView = (TextView) view.findViewById(R.id.peak3QText);

        peak3Q_SeekArc = (SeekArc) view.findViewById(R.id.peak3QSeekArc);

        //Singleton values
        quality_progress = BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(10).progress;

        peak3Q_SeekArc.setProgress(quality_progress);
        peak3Q_TextView.setText(String.valueOf((quality_progress)/10.0));

        peak3Q_SeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                quality_progress = progress;

                double dprogress = (progress)/10.0;

                peak3Q_TextView.setText(String.valueOf(dprogress));

                BackgroundAsSingleton.getInstance().setProgressDescriptionIndividualListValue("p3qp", 10, quality_progress);
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