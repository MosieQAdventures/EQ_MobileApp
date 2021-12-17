package com.example.eq_hm_mobilecontrol;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class SendValuesHere extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM0 = "lcfp";
    private static final String ARG_PARAM1 = "lcsp";
    private static final String ARG_PARAM2 = "p1fp";
    private static final String ARG_PARAM3 = "p1gp";
    private static final String ARG_PARAM4 = "p1qp";
    private static final String ARG_PARAM5 = "p2fp";
    private static final String ARG_PARAM6 = "p2gp";
    private static final String ARG_PARAM7 = "p2qp";
    private static final String ARG_PARAM8 = "p3fp";
    private static final String ARG_PARAM9 = "p3gp";
    private static final String ARG_PARAM10 = "p3qp";
    private static final String ARG_PARAM11 = "hcfp";
    private static final String ARG_PARAM12 = "hcsp";

    private TextView lcf_text_TV;
    private TextView lcs_text_TV;
    private TextView p1f_text_TV;
    private TextView p1g_text_TV;
    private TextView p1q_text_TV;
    private TextView p2f_text_TV;
    private TextView p2g_text_TV;
    private TextView p2q_text_TV;
    private TextView p3f_text_TV;
    private TextView p3g_text_TV;
    private TextView p3q_text_TV;
    private TextView hcf_text_TV;
    private TextView hcs_text_TV;

    private TextView lcf_prog_TV;
    private TextView lcs_prog_TV;
    private TextView p1f_prog_TV;
    private TextView p1g_prog_TV;
    private TextView p1q_prog_TV;
    private TextView p2f_prog_TV;
    private TextView p2g_prog_TV;
    private TextView p2q_prog_TV;
    private TextView p3f_prog_TV;
    private TextView p3g_prog_TV;
    private TextView p3q_prog_TV;
    private TextView hcf_prog_TV;
    private TextView hcs_prog_TV;

    int lcfp;
    int lcsp;
    int p1fp;
    int p1gp;
    int p1qp;
    int p2fp;
    int p2gp;
    int p2qp;
    int p3fp;
    int p3gp;
    int p3qp;
    int hcfp;
    int hcsp;

    // TODO: Rename and change types and number of parameters
    public static SendValuesHere newInstance() {
        SendValuesHere fragment = new SendValuesHere();
        Bundle args = new Bundle();
        args.putInt(Singleton.getInstance().getProgressDescriptionList().get(0).name, Singleton.getInstance().getProgressDescriptionList().get(0).progress);
        args.putInt(ARG_PARAM1, Singleton.getInstance().getProgressDescriptionList().get(1).progress);
        args.putInt(ARG_PARAM2, Singleton.getInstance().getProgressDescriptionList().get(2).progress);
        args.putInt(ARG_PARAM3, Singleton.getInstance().getProgressDescriptionList().get(3).progress);
        args.putInt(ARG_PARAM4, Singleton.getInstance().getProgressDescriptionList().get(4).progress);
        args.putInt(ARG_PARAM5, Singleton.getInstance().getProgressDescriptionList().get(5).progress);
        args.putInt(ARG_PARAM6, Singleton.getInstance().getProgressDescriptionList().get(6).progress);
        args.putInt(ARG_PARAM7, Singleton.getInstance().getProgressDescriptionList().get(7).progress);
        args.putInt(ARG_PARAM8, Singleton.getInstance().getProgressDescriptionList().get(8).progress);
        args.putInt(ARG_PARAM9, Singleton.getInstance().getProgressDescriptionList().get(9).progress);
        args.putInt(ARG_PARAM10, Singleton.getInstance().getProgressDescriptionList().get(10).progress);
        args.putInt(ARG_PARAM11, Singleton.getInstance().getProgressDescriptionList().get(11).progress);
        args.putInt(ARG_PARAM12, Singleton.getInstance().getProgressDescriptionList().get(12).progress);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*lcfp = getArguments().getInt(ARG_PARAM0);
            lcsp = getArguments().getInt(ARG_PARAM1);
            p1fp = getArguments().getInt(ARG_PARAM2);
            p1gp = getArguments().getInt(ARG_PARAM3);
            p1qp = getArguments().getInt(ARG_PARAM4);
            p2fp = getArguments().getInt(ARG_PARAM5);
            p2gp = getArguments().getInt(ARG_PARAM6);
            p2qp = getArguments().getInt(ARG_PARAM7);
            p3fp = getArguments().getInt(ARG_PARAM8);
            p3gp = getArguments().getInt(ARG_PARAM9);
            p3qp = getArguments().getInt(ARG_PARAM10);
            hcfp = getArguments().getInt(ARG_PARAM11);
            hcfp = getArguments().getInt(ARG_PARAM12);*/

            lcfp = Singleton.getInstance().getProgressDescriptionList().get(0).progress;
            lcsp = Singleton.getInstance().getProgressDescriptionList().get(1).progress;
            p1fp = Singleton.getInstance().getProgressDescriptionList().get(2).progress;
            p1gp = Singleton.getInstance().getProgressDescriptionList().get(3).progress;
            p1qp = Singleton.getInstance().getProgressDescriptionList().get(4).progress;
            p2fp = Singleton.getInstance().getProgressDescriptionList().get(5).progress;
            p2gp = Singleton.getInstance().getProgressDescriptionList().get(6).progress;
            p2qp = Singleton.getInstance().getProgressDescriptionList().get(7).progress;
            p3fp = Singleton.getInstance().getProgressDescriptionList().get(8).progress;
            p3gp = Singleton.getInstance().getProgressDescriptionList().get(9).progress;
            p3qp = Singleton.getInstance().getProgressDescriptionList().get(10).progress;
            hcfp = Singleton.getInstance().getProgressDescriptionList().get(11).progress;
            hcsp = Singleton.getInstance().getProgressDescriptionList().get(12).progress;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_values_here, container, false);

        lcf_text_TV = view.findViewById(R.id.testLowCutFreqText);
        lcs_text_TV = view.findViewById(R.id.testLowCutSlopeText);
        p1f_text_TV = view.findViewById(R.id.testPeak1FreqText);
        p1g_text_TV = view.findViewById(R.id.testPeak1GainText);
        p1q_text_TV = view.findViewById(R.id.testPeak1QText);
        p2f_text_TV = view.findViewById(R.id.testPeak2FreqText);
        p2g_text_TV = view.findViewById(R.id.testPeak2GainText);
        p2q_text_TV = view.findViewById(R.id.testPeak2QText);
        p3f_text_TV = view.findViewById(R.id.testPeak3FreqText);
        p3g_text_TV = view.findViewById(R.id.testPeak3GainText);
        p3q_text_TV = view.findViewById(R.id.testPeak3QText);
        hcf_text_TV = view.findViewById(R.id.testHighCutFreqText);
        hcs_text_TV = view.findViewById(R.id.testHighCutSlopeText);

        lcf_prog_TV = view.findViewById(R.id.testLowCutFreqProg);
        lcs_prog_TV = view.findViewById(R.id.testLowCutSlopeProg);
        p1f_prog_TV = view.findViewById(R.id.testPeak1FreqProg);
        p1g_prog_TV = view.findViewById(R.id.testPeak1GainProg);
        p1q_prog_TV = view.findViewById(R.id.testPeak1QProg);
        p2f_prog_TV = view.findViewById(R.id.testPeak2FreqProg);
        p2g_prog_TV = view.findViewById(R.id.testPeak2GainProg);
        p2q_prog_TV = view.findViewById(R.id.testPeak2QProg);
        p3f_prog_TV = view.findViewById(R.id.testPeak3FreqProg);
        p3g_prog_TV = view.findViewById(R.id.testPeak3GainProg);
        p3q_prog_TV = view.findViewById(R.id.testPeak3QProg);
        hcf_prog_TV = view.findViewById(R.id.testHighCutFreqProg);
        hcs_prog_TV = view.findViewById(R.id.testHighCutSlopeProg);

        lcf_prog_TV.setText(String.valueOf(lcfp));
        lcs_prog_TV.setText(String.valueOf(lcsp));
        p1f_prog_TV.setText(String.valueOf(p1fp));
        p1g_prog_TV.setText(String.valueOf(p1gp));
        p1q_prog_TV.setText(String.valueOf(p1qp));
        p2f_prog_TV.setText(String.valueOf(p2fp));
        p2g_prog_TV.setText(String.valueOf(p2gp));
        p2q_prog_TV.setText(String.valueOf(p2qp));
        p3f_prog_TV.setText(String.valueOf(p3fp));
        p3g_prog_TV.setText(String.valueOf(p3gp));
        p3q_prog_TV.setText(String.valueOf(p3qp));
        hcf_prog_TV.setText(String.valueOf(hcfp));
        hcs_prog_TV.setText(String.valueOf(hcsp));

        return view;
    }
}
