package com.example.eq_hm_mobilecontrol;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.lang.Integer;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class BackgroundAsSingleton {

    // holds created instance of the singleton class
    private static BackgroundAsSingleton Instance;
    public List<ProgressDescription> progressDescriptionList;
    int server_port = 54000;
    boolean acc_open = false;

    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    private static final DecimalFormat df = new DecimalFormat("0.00", dfs);

    String comparator = "";

    // private constructor
    private BackgroundAsSingleton() {

    }

    public static BackgroundAsSingleton getInstance() {
        // return the created instance on all subsequent calls
        // checks if instance is created
        if (Instance == null)
        {
            // if not - create one
            Instance = new BackgroundAsSingleton();
        }
        // return the created instance on every subsequent call
        return Instance;
    }

    public List<ProgressDescription> getProgressDescriptionList() {
        return this.progressDescriptionList;
    }

    public void setProgressDescriptionList(List<ProgressDescription> progressDescriptionList) {
        this.progressDescriptionList = progressDescriptionList;
    }

    public void setProgressDescriptionIndividualListValue(String pdField_name, int index, int value) {
        getProgressDescriptionList().set(index, new ProgressDescription(pdField_name, value));
    }

    public int getServer_port() {
        return this.server_port;
    }

    public boolean get_acc_state() {
        return this.acc_open;
    }
    public void t_acc_state() { this.acc_open = true; }
    public void f_acc_state() { this.acc_open = false; }

    //---------------------

    public void executeAsyncTask()
    {
        SendControls sendControls = new SendControls();
        String json_test = createJsonFromData();
        if (json_test.equals(comparator)) {}
        else {
            sendControls.execute(json_test);
        }
        comparator = json_test;
    }



    public String createJsonFromData() {
        //progresses
        int lcfp = getProgressDescriptionList().get(0).progress;
        int lcsp = getProgressDescriptionList().get(1).progress;
        int p1fp = getProgressDescriptionList().get(2).progress;
        int p1gp = getProgressDescriptionList().get(3).progress;
        int p1qp = getProgressDescriptionList().get(4).progress;
        int p2fp = getProgressDescriptionList().get(5).progress;
        int p2gp = getProgressDescriptionList().get(6).progress;
        int p2qp = getProgressDescriptionList().get(7).progress;
        int p3fp = getProgressDescriptionList().get(8).progress;
        int p3gp = getProgressDescriptionList().get(9).progress;
        int p3qp = getProgressDescriptionList().get(10).progress;
        int hcfp = getProgressDescriptionList().get(11).progress;
        int hcsp = getProgressDescriptionList().get(12).progress;

        //convertion
        int hcf = 20 + hcfp;
        int hcs = hcsp;
        int lcf = 20 + lcfp;
        int lcs = lcsp;
        int p1f = 20 + p1fp;
        float p1g = -24 + ((float) p1gp/10);
        float p1q = ((float) p1qp /10);
        int p2f = 20 + p2fp;
        float p2g = -24 + ((float) p2gp/10);
        float p2q = ((float) p2qp /10);
        int p3f = 20 + p3fp;
        float p3g = -24 + ((float) p3gp/10);
        float p3q = ((float) p3qp /10);

        //strings
        String json_hcf = "\"HighCut Frequency\"" + ": " + String.valueOf(hcf);
        String json_hcs = "\"HighCut Slope\"" + ": " + String.valueOf(hcs);
        String json_lcf = "\"LowCut Frequency\"" + ": " + String.valueOf(lcf);
        String json_lcs = "\"LowCut Slope\"" + ": " + String.valueOf(lcs);
        //String json_p1f = "\"Peak1 Frequency\"" + ": " + String.valueOf(p1f);
        //String json_p1g = "\"Peak1 Gain\"" + ": " + String.valueOf(p1g);
        //String json_p1q = "\"Peak1 Q\"" + ": " + String.valueOf(p1q);
        //String json_p2f = "\"Peak2 Frequency\"" + ": " + String.valueOf(p2f);
        //String json_p2g = "\"Peak2 Gain\"" + ": " + String.valueOf(p2g);
        //String json_p2q = "\"Peak2 Q\"" + ": " + String.valueOf(p2q);
        //String json_p3f = "\"Peak3 Frequency\"" + ": " + String.valueOf(p3f);
        //String json_p3g = "\"Peak3 Gain\"" + ": " + String.valueOf(p3g);
        //String json_p3q = "\"Peak3 Q\"" + ": " + String.valueOf(p3q);

        String json_p1f = "\"Peak1 Frequency\"" + ": " + String.valueOf(p1f);
        String json_p1g = "\"Peak1 Gain\"" + ": " + df.format(p1g);
        String json_p1q = "\"Peak1 Q\"" + ": " + df.format(p1q);
        String json_p2f = "\"Peak2 Frequency\"" + ": " + String.valueOf(p2f);
        String json_p2g = "\"Peak2 Gain\"" + ": " + df.format(p2g);
        String json_p2q = "\"Peak2 Q\"" + ": " + df.format(p2q);
        String json_p3f = "\"Peak3 Frequency\"" + ": " + String.valueOf(p3f);
        String json_p3g = "\"Peak3 Gain\"" + ": " + df.format(p3g);
        String json_p3q = "\"Peak3 Q\"" + ": " + df.format(p3q);



        //json
        String json_full = "{" + "\n"
                + "    " + json_hcf + "," + "\n"
                + "    " + json_hcs + "," + "\n"
                + "    " + json_lcf + "," + "\n"
                + "    " + json_lcs + "," + "\n"
                + "    " + json_p1f + "," + "\n"
                + "    " + json_p1g + "," + "\n"
                + "    " + json_p1q + "," + "\n"
                + "    " + json_p2f + "," + "\n"
                + "    " + json_p2g + "," + "\n"
                + "    " + json_p2q + "," + "\n"
                + "    " + json_p3f + "," + "\n"
                + "    " + json_p3g + "," + "\n"
                + "    " + json_p3q + "\n"
                + "}" + "\n";

        return json_full;
    }
}


