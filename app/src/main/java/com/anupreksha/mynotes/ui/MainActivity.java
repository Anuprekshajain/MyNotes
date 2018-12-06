package com.anupreksha.mynotes.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anupreksha.mynotes.R;
import com.anupreksha.mynotes.adapters.NotesAdapter;
import com.anupreksha.mynotes.database.LocalCacheManager;
import com.anupreksha.mynotes.models.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    @BindView(R.id.rvNotes)
    RecyclerView rvNotes;

    static RecyclerView.Adapter adapter;
    static List<Note> notesList;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        initViews();
        loadNotes();
    }
    private void initViews() {

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNotes(){

        //Call Method to get Notes
        LocalCacheManager.getInstance(this).getNotes(this);


    }

    @OnClick(R.id.fab)
    public void addNote(){
        AddNewFragment dialog = new AddNewFragment();
        dialog.show(getSupportFragmentManager(),"New");
    }

    @Override
    public void onNotesLoaded(final List<Note> notes) {
        notesList = notes;

        if(notesList.size() == 0){
            onDataNotAvailable();
        }else {
            adapter = new NotesAdapter(this, notes);
            rvNotes.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }


    @Override
    public void onNoteAdded() {
        Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataNotAvailable() {
        Toast.makeText(this,"No Notes Yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
