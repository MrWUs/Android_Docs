package com.wu.android_docs;

import android.app.DatePickerDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView tv_selectedDocs, tv_msg;
    private Button btn_start, button_datetime;
    private ProgressBar pb_loading;


    private ArrayList<String> docList;
    private Calendar startCalendar;
    private Guaji guaji;
    private GuajiCallback guajiCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        iniData();
        iniUI();
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//
//    }

    private void iniData() {
        docList = new ArrayList<String>();
        docList.addAll(Arrays.asList("施秉银", "朱本章", "王会芳", "郭辉"));
        startCalendar = Calendar.getInstance();
        guajiCallback = (msg, alert) -> {
            if (tv_msg == null) return;
            String line = tv_msg.getText().toString();
            if (line == "无")
                line = msg;
            else line = msg + "\n" + line;
            int num = line.split("\n").length;
            if (num > 5) {
                int p = line.lastIndexOf("\n");
                line = line.substring(0, p);
            }
            tv_msg.setText(line);
            playSound(alert);
        };
    }

    private void iniUI() {
        tv_selectedDocs = findViewById(R.id.tv_docs);

        tv_msg = findViewById(R.id.tv_msg);
        tv_msg.setMovementMethod(ScrollingMovementMethod.getInstance());

        pb_loading = findViewById(R.id.pb_loading);
        pb_loading.setVisibility(View.GONE);

        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 = findViewById(R.id.cb3);
        CheckBox cb4 = findViewById(R.id.cb4);
        CheckBox cb5 = findViewById(R.id.cb5);
        CheckBox cb6 = findViewById(R.id.cb6);
        CheckBox cb7 = findViewById(R.id.cb7);
        CheckBox cb8 = findViewById(R.id.cb8);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        cb5.setOnCheckedChangeListener(this);
        cb6.setOnCheckedChangeListener(this);
        cb7.setOnCheckedChangeListener(this);
        cb8.setOnCheckedChangeListener(this);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setText("开始");
        btn_start.setOnClickListener(v -> {
            boolean st = btn_start.getText() == "开始";
            start(st);
            btn_start.setText(st ? "停止" : "开始");
        });

        button_datetime = findViewById(R.id.btn_datetime);
        button_datetime.setText("起始日期: " + getDatetimeString());
        button_datetime.setOnClickListener(v -> {
            setDatetime();
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (tv_selectedDocs == null || docList == null)
            return;
        String doc = buttonView.getText().toString();
        if (buttonView.isChecked()) {
            if (!docList.contains(doc))
                docList.add(doc);
        } else {
            if (docList.contains(doc))
                docList.remove(doc);
        }
        String res = "";
        for (String d : docList
        ) {
            res += ", " + d;
        }
        tv_selectedDocs.setText(res == "" ? "未选择" : res.substring(2));
    }

    private void setDatetime() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                (view, year, month, dayOfMonth) -> {
                    startCalendar.set(Calendar.YEAR, year);
                    startCalendar.set(Calendar.MONTH, month);
                    startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    button_datetime.setText("起始日期: " + getDatetimeString());
                },
                startCalendar.get(Calendar.YEAR),
                startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String getDatetimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(startCalendar.getTime());
    }

    private void start(boolean b) {
        try {
            pb_loading.setVisibility(b ? View.VISIBLE : View.GONE);
            if (b) {
                tv_msg.setText("");
                guaji = new Guaji(startCalendar.getTime(), guajiCallback);
                guaji.selectDocs = docList;
                guaji.start();
            } else {
                guaji.stopRun();
                guaji = null;
                playSound(false);
            }
        } catch (Exception e) {
        }
    }

    private MediaPlayer mediaPlayer;

    private void playSound(boolean play) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.jinitaimei);
            mediaPlayer.setLooping(true);
        }
        if (play != mediaPlayer.isPlaying()) {
            if (play)
                mediaPlayer.start();
            else {
                mediaPlayer.stop();
                mediaPlayer = null;
            }
        }
    }

}