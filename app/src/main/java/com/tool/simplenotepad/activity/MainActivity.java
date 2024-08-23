package com.tool.simplenotepad.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.exquisite.demo04.R;
import com.exquisite.demo04.adapter.NoteAdapter;
import com.exquisite.demo04.mydb.MyData;
import com.exquisite.demo04.mydb.Mydatabase;
import com.exquisite.demo04.tools.Mytools;
import com.exquisite.demo04.tools.SpaceItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnNewNote;
    private ImageView btnSearch;
    private RecyclerView lsvNotes;
    private NoteAdapter noteAdapter;
    private List<MyData> mydataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_actiivty), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        SpaceItem spaceItem = new SpaceItem(5, 20, 5);
        noteAdapter = new NoteAdapter(this);
        updateData();
        lsvNotes.setAdapter(noteAdapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        lsvNotes.setLayoutManager(staggeredGridLayoutManager);
        staggeredGridLayoutManager.setReverseLayout(true);
        lsvNotes.addItemDecoration(spaceItem);

        initEvent();
    }

    private void updateData() {
        Mytools.runIO(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                mydataList = Mydatabase.getInstance().mydao().getAllData();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        noteAdapter.setData(mydataList);
                        noteAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initEvent() {
        btnNewNote.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void initView() {
        lsvNotes = findViewById(R.id.rv_note);
        btnNewNote = findViewById(R.id.btnNewNote);
        btnSearch = findViewById(R.id.search_image);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewNote:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.search_image:
                Intent intent1 = new Intent(this, SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }
}