package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Date;

public class UpdateNote extends AppCompatActivity {
    ActivityUpdateNoteBinding binding;
    NotesViewModel model;
    private String npriority, uTitle, uSubtitle, uNote;
    private String sTitle, sSubtitle, sNote, sPri;
    private int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid = getIntent().getIntExtra("id", 0);
        sTitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subtitle");
        sNote = getIntent().getStringExtra("note");
        sPri = getIntent().getStringExtra("priority");

        binding.EdtTitleUpdate.setText(sTitle);
        binding.EdtSubtitleUpdate.setText(sSubtitle);
        binding.EdtNotesUpdate.setText(sNote);

        model = new ViewModelProvider(this).get(NotesViewModel.class);

        if ("1".equals(sPri)) {
            binding.upgreen.setImageResource(R.drawable.ic_baseline_done_24);
        } else if ("2".equals(sPri)) {
            binding.upyellow.setImageResource(R.drawable.ic_baseline_done_24);
        } else if ("3".equals(sPri)) {
            binding.upred.setImageResource(R.drawable.ic_baseline_done_24);
        }

        binding.upgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.upgreen.setImageResource(R.drawable.ic_baseline_done_24);
                binding.upyellow.setImageResource(0);
                binding.upred.setImageResource(0);
                npriority = "1";
            }
        });

        binding.upyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.upgreen.setImageResource(0);
                binding.upyellow.setImageResource(R.drawable.ic_baseline_done_24);
                binding.upred.setImageResource(0);
                npriority = "2";
            }
        });

        binding.upred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.upgreen.setImageResource(0);
                binding.upyellow.setImageResource(0);
                binding.upred.setImageResource(R.drawable.ic_baseline_done_24);
                npriority = "3";
            }
        });

        binding.FloatBTUpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uTitle = binding.EdtTitleUpdate.getText().toString();
                uSubtitle = binding.EdtSubtitleUpdate.getText().toString();
                uNote = binding.EdtNotesUpdate.getText().toString();

                updateNote(uTitle, uSubtitle, uNote);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateNote(String uTitle, String uSubtitle, String uNote) {

        Date date = new Date();
        CharSequence sequence = DateFormat.getDateInstance().format(date);

        Entity e = new Entity();
        if (uTitle.isEmpty()) {
            Toast.makeText(this, "Enter the title", Toast.LENGTH_SHORT).show();
        } else {
            e.notesTitle = uTitle;
            e.id = iid;
            e.notesSubtitle = uSubtitle;
            e.notes = uNote;
            e.priority = npriority;
            e.date = sequence.toString();
            model.updateNote(e);

            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_meunu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.deleteIcon) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNote.this, R.style.BottomSheetStyle);

            View view = LayoutInflater.from(this).inflate(R.layout.delete_layout, findViewById(R.id.ll));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            TextView yesDelete, noDelete;

            yesDelete = view.findViewById(R.id.yesDelete);
            noDelete = view.findViewById(R.id.noDelete);

            yesDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            noDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.deleteNote(iid);
                    bottomSheetDialog.cancel();
                    finish();
                }
            });
        }
        return true;
    }
}