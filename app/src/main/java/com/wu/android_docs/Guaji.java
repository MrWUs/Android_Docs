package com.wu.android_docs;

import android.annotation.SuppressLint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Guaji extends Thread {

    private volatile boolean running = false;
    private final GuajiCallback callback;

    private final Date startDate;

    public ArrayList<String> selectDocs;

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final JSONObject jsonDoc;


    //    data = {"hosId": 100, "startDate": "2024-02-29", "endDate": "2024-02-29", "date": "2024-02-28", "page": 0, "size": 10, "deptCode": "1014"}


    public Guaji(Date startDate, GuajiCallback callback) {
        this.startDate = startDate;
        this.callback = callback;
        jsonDoc = new JSONObject();
        try {
            jsonDoc.put("hosId", 100);
            jsonDoc.put("startDate", "2024-02-29");
            jsonDoc.put("endDate", "2024-02-29");
            jsonDoc.put("date", "2024-02-29");
            jsonDoc.put("page", 0);
            jsonDoc.put("size", 10);
            jsonDoc.put("deptCode", "1014");
        } catch (Exception e) {
        }
    }


    @Override
    public void run() {
        runFun();
    }

    public void runFun() {
        if (selectDocs == null || selectDocs.size() == 0)
            return;
        if (running)
            return;
        else running = true;
        //    private Calendar startCalendar;
        ArrayList<String> alertList = new ArrayList<>();
        Random random = new Random();
        HttpHelper helper = new HttpHelper();
        String url = "https://gateway-jd1fy-prod.swifthealth.cn/patient/v1/offline/regist/unauth/doclist?courtyardCode=YQ01";
        while (running) {
            ArrayList<String> dateList = setDaysList(startDate, 7);
            if (dateList.size() == 0) {
                if (!delay(1000))
                    break;
            } else {
                for (String dayStr : dateList) {
                    String res;
                    try {
                        jsonDoc.put("startDate", dayStr);
                        jsonDoc.put("endDate", dayStr);
                        jsonDoc.put("date", dayStr);
                        String result = helper.doPost(url, jsonDoc.toString());
                        res = resultHandler(result);
                        if (res == "") {
                            if (alertList.contains(dayStr))
                                alertList.remove(dayStr);
                            res = "无号";
                        } else {
                            if (!alertList.contains(dayStr))
                                alertList.add(dayStr);
                        }
                    } catch (Exception e) {
                        res = "Error:" + e.getMessage();
                    }
                    callback.doCallback(res, alertList.size() > 0);
                    if (!delay(random.nextInt(1500) + 1500))
                        break;
                }
            }
        }
    }

    public void stopRun() {
        running = false;
    }

    private boolean delay(long millis) {
        try {
            long n = millis / 50;
            int k = 0;
            while (k < n) {
                Thread.sleep(50);

                k++;
                if (!running)
                    return false;
            }
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    private ArrayList<String> setDaysList(Date startDate, int period) {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < period; i++) {
            c.add(Calendar.DATE, 1);
            Date d = c.getTime();
            if (startDate.compareTo(d) > 0)
                continue;
            list.add(dateFormat.format(d));
        }
        return list;
    }

    private String resultHandler(String result) throws Exception {
        JSONObject json = new JSONObject(result);
        if (!json.getBoolean("success"))
            throw new JSONException("Post Failed");
        JSONObject jsonData = json.getJSONObject("data");
        JSONArray jsonArray = jsonData.getJSONArray("models");
        if (jsonArray.length() == 0)
            return "";
        String line = "";
        String testLine = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = jsonArray.getJSONObject(i);
            String name = j.getString("docName");
            testLine += ", " + name;
            int num = j.getInt("regOver");
            if (selectDocs.contains(name) && num > 0)
                line += " " + name + ": " + num;
        }
        System.out.println(testLine.substring(2));
        return line;
    }
}


//    private int dateDays(Date d1, Date d2) {
//        return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
//    }

