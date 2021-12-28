package com.example.eq_hm_mobilecontrol;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eq_hm_mobilecontrol.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    TabLayout tabLayout;
    ViewPager viewPager;

    private LowCut fragmentLowCut;
    private Peak1 fragmentPeak1;
    private Peak2 fragmentPeak2;
    private Peak3 fragmentPeak3;
    private HighCut fragmentHighCut;
    private Accelerometer fragmentAccelerometer;
    private SendValuesHere fragmentSendValuesHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //working version below

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = binding.viewPager;
        //viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = binding.tabs;
        //tabs.setupWithViewPager(viewPager);

        //new version

        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);

        List<ProgressDescription> progressDescriptions = new ArrayList<ProgressDescription>();
        progressDescriptions = CreateProgressList(progressDescriptions);

        BackgroundAsSingleton.getInstance().setProgressDescriptionList(progressDescriptions);

        // ^^ singleton testing

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //------------------ tutaj fragmenty ------------------
        //viewPagerAdapter.addFragment(LowCut.newInstance(progressDescriptions.get(0).progress,
        //                                                progressDescriptions.get(1).progress), "Low Cut");
        viewPagerAdapter.addFragment(LowCut.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(0).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(1).progress), "Low Cut");
        viewPagerAdapter.addFragment(Peak1.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(2).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(3).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(4).progress), "Peak no.1");
        viewPagerAdapter.addFragment(Peak2.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(5).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(6).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(7).progress), "Peak no.2");
        viewPagerAdapter.addFragment(Peak3.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(8).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(9).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(10).progress), "Peak no.3");
        viewPagerAdapter.addFragment(HighCut.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(11).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(12).progress), "HighCut");
        viewPagerAdapter.addFragment(Accelerometer.newInstance(BackgroundAsSingleton.getInstance().getProgressDescriptionList(),
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(13).progress,
                BackgroundAsSingleton.getInstance().getProgressDescriptionList().get(14).progress), "ACC - Low Cut");
        //test fragment
        viewPagerAdapter.addFragment(SendValuesHere.newInstance(), "SVH");

        //----------------------------------------------------------

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //test sending message to server:

        SendControls sendControls = new SendControls();
        String json_test = BackgroundAsSingleton.getInstance().createJsonFromData();
        //sendControls.execute(json_test);

    }

    //default values
    public List<ProgressDescription> CreateProgressList(List<ProgressDescription> progressList) {
        progressList.add(new ProgressDescription("lcfp", 0)); //0-19980        //index 0
        progressList.add(new ProgressDescription("lcsp", 0)); //0-3           //index 1
        progressList.add(new ProgressDescription("p1fp", 1980)); //0-19980      //index 2
        progressList.add(new ProgressDescription("p1gp", 240)); //0-489       //index 3
        progressList.add(new ProgressDescription("p1qp", 10)); //0-99         //index 4
        progressList.add(new ProgressDescription("p2fp", 3980)); //0-19980      //index 5
        progressList.add(new ProgressDescription("p2gp", 240)); //0-489       //index 6
        progressList.add(new ProgressDescription("p2qp", 10)); //0-99         //index 7
        progressList.add(new ProgressDescription("p3fp", 9980)); //0-19980      //index 8
        progressList.add(new ProgressDescription("p3gp", 240)); //0-489       //index 9
        progressList.add(new ProgressDescription("p3qp", 10)); //0-99         //index 10
        progressList.add(new ProgressDescription("hcfp", 19980)); //0-19980     //index 11
        progressList.add(new ProgressDescription("hcsp", 0)); //0-3           //index 12
        progressList.add(new ProgressDescription("acc_lcfp", 500)); //0-1998  //index 13
        progressList.add(new ProgressDescription("acc_lcsp", 1)); //0-3       //index 14

        return progressList;
    }
}