package com.tool.simplenotepad.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.exquisite.demo04.R;
import com.exquisite.demo04.mydb.MyData;
import com.exquisite.demo04.mydb.Mydatabase;
import com.exquisite.demo04.mydb.StaticValue;
import com.exquisite.demo04.tools.Mytools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtContent;
    private ImageView back;
    private ImageView delete;
    private ImageView save;

    private TextView tvDate;
    private String date;
    private EditText edtTitle;
    private String keytitle, keycontent, keydate;
    private String ischeck = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initDate();
        initEvent();

        Intent intent = getIntent();
        keytitle = intent.getStringExtra(StaticValue.KEY_TITLE);
        keycontent = intent.getStringExtra(StaticValue.KEY_CONTENT);
        keydate = intent.getStringExtra(StaticValue.KEY_DATE);
        ischeck = intent.getStringExtra(StaticValue.KEY_Boolean);

        if (keytitle == null || keycontent == null || keydate == null || ischeck == null) {
            edtTitle.setText(getString(R.string.txt_title_hint));
            edtContent.setText(getString(R.string.txt_content_hint));
            initDate();
        } else if (ischeck.equals("true")) {
            edtTitle.setText(keytitle);
            edtContent.setText(keycontent);
            tvDate.setText(keydate);
        }
    }

    private void initEvent() {
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String timeString = sdf.format(calendar.getTime());
        tvDate.setText(timeString);

    }

    private void initView() {
        edtContent = findViewById(R.id.edtContent);
        tvDate = findViewById(R.id.textDatetime);
        edtTitle = findViewById(R.id.edtTitle);
        back = findViewById(R.id.add_back);
        save = findViewById(R.id.add_save);
        delete = findViewById(R.id.add_delete);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_back:
                finish();
                break;
            case R.id.add_save:
                saveData();
                break;
            case R.id.add_delete:
                deleteData();
                break;

        }
    }

    private void deleteData() {

    }

    private void saveData() {
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        initDate();
        date = tvDate.getText().toString();
        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, getString(R.string.fail), Toast.LENGTH_SHORT).show();
        } else {
            MyData myData = new MyData();
            myData.setContent(content);
            myData.setTitle(title);
            myData.setDate(date);
            List<MyData> myDataList = new ArrayList<>();
            myDataList.add(myData);
            Mytools.runIO(new Runnable() {
                @Override
                public void run() {
                    Mydatabase.getInstance().mydao().insertAll(myDataList);
                    finish();
                }
            });
        }


    }

}