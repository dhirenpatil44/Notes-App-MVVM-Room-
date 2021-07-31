package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addNote;
    NotesViewModel model;
    RecyclerView recyclerView;
    RVAdapter adapter;
    List<Entity> filterNotesList;
    LinearLayout linear;

    TextView ltoh, htol, nofilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = findViewById(R.id.addNote);
        recyclerView = findViewById(R.id.recyclerView);
        htol = findViewById(R.id.htol);
        ltoh = findViewById(R.id.ltoh);
        nofilter = findViewById(R.id.nofilter);
        linear = findViewById(R.id.linear);

        nofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nofilter.setBackgroundResource(R.drawable.filter_select_shape);
                htol.setBackgroundResource(R.drawable.filter_un_shape);
                ltoh.setBackgroundResource(R.drawable.filter_un_shape);
                orderData(1);
            }
        });

        htol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderData(2);
                htol.setBackgroundResource(R.drawable.filter_select_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
                ltoh.setBackgroundResource(R.drawable.filter_un_shape);
            }
        });

        ltoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderData(3);
                htol.setBackgroundResource(R.drawable.filter_un_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
                ltoh.setBackgroundResource(R.drawable.filter_select_shape);
            }
        });

        model = new ViewModelProvider(this).get(NotesViewModel.class);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateNote.class));
            }
        });

        model.getAllNote.observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(List<Entity> entityList) {
                setAdapter(entityList);
                filterNotesList = entityList;

            }
        });
    }

    private void orderData(int i) {
        if (i == 1) {
            model.getAllNote.observe(this, new Observer<List<Entity>>() {
                @Override
                public void onChanged(List<Entity> entityList) {
                    setAdapter(entityList);
                    filterNotesList = entityList;
                }
            });
        } else if (i == 2) {
            model.hightolow.observe(this, new Observer<List<Entity>>() {
                @Override
                public void onChanged(List<Entity> entityList) {
                    setAdapter(entityList);
                    filterNotesList = entityList;
                }
            });
        } else if (i == 3) {
            model.lowtohigh.observe(this, new Observer<List<Entity>>() {
                @Override
                public void onChanged(List<Entity> entityList) {
                    setAdapter(entityList);
                    filterNotesList = entityList;
                }
            });
        }
    }

    private void setAdapter(List<Entity> entityList) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new RVAdapter(MainActivity.this, entityList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void notesFilter(String searchText) {
        ArrayList<Entity> filternote = new ArrayList<>();

        for (Entity e : this.filterNotesList) {
            if (e.notesTitle.contains(searchText) || e.notesSubtitle.contains(searchText)) {
                filternote.add(e);
            }
        }
        this.adapter.searchNote(filternote);
    }
}