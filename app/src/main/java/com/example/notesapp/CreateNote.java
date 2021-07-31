package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.databinding.ActivityCreateNoteBinding;

import java.text.DateFormat;
import java.util.Date;

public class CreateNote extends AppCompatActivity {

    ActivityCreateNoteBinding binding;
    public String title, subtitle, notes;
    NotesViewModel notesViewModel;
    public String npriority = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.greenPri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPri.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowPri.setImageResource(0);
                binding.redPri.setImageResource(0);
                npriority = "1";
            }
        });

        binding.yellowPri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenPri.setImageResource(0);
                binding.yellowPri.setImageResource(R.drawable.ic_baseline_done_24);
                binding.redPri.setImageResource(0);
                npriority = "2";
            }
        });

        binding.redPri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenPri.setImageResource(0);
                binding.yellowPri.setImageResource(0);
                binding.redPri.setImageResource(R.drawable.ic_baseline_done_24);
                npriority = "3";
            }
        });

        binding.floatBTCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.EdtTitleCreate.getText().toString();
                subtitle = binding.EdtSubtitleCreate.getText().toString();
                notes = binding.EdtNotesCreate.getText().toString();

                createNote(title, subtitle, notes);

            }
        });
    }

    private void createNote(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.getDateInstance().format(date);

        Entity entity = new Entity();
        if (title.isEmpty()) {
            Toast.makeText(this, "Enter the title", Toast.LENGTH_SHORT).show();
        } else {
            entity.notesTitle = title;
            entity.notesSubtitle = subtitle;
            entity.notes = notes;
            entity.priority = npriority;
            entity.date = sequence.toString();
            notesViewModel.insertNote(entity);

            Toast.makeText(this, title + " created", Toast.LENGTH_SHORT).show();

            finish();

        }

    }
}