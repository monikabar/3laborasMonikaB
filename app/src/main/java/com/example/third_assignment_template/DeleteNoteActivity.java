package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner spSelectionForDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spSelectionForDelete = findViewById(R.id.spSelectionForDelete);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectionForDelete.setAdapter(listAdapter);
    }

    public void onDeleteNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> savedNotesList = sp.getStringSet("notes", new HashSet<String>());

        String selectedNote = spSelectionForDelete.getSelectedItem().toString();

        Set<String> removedStrSet = new HashSet<String>();

        for (String savedNote : savedNotesList) {
            if (!savedNote.equalsIgnoreCase(selectedNote)) {
                removedStrSet.add(savedNote);
            }
        }

        spEd.putStringSet("notes", removedStrSet);
        spEd.apply();

        finish();
    }
}
