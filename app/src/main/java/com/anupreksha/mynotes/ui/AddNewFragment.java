package com.anupreksha.mynotes.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.anupreksha.mynotes.R;
import com.anupreksha.mynotes.database.LocalCacheManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewFragment extends DialogFragment implements AddNoteViewInterface {
    View dialogView;
    @BindView(R.id.editTexttitle)
    EditText etTitle;

    @BindView(R.id.edittextDesc)
    EditText etNote;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_new_note, null);
        builder.setView(dialogView).setTitle("Add a new note");
        ButterKnife.bind(this, dialogView);
        return builder.create();
    }

    @OnClick(R.id.button)
    public void add(){
        saveNote();
        dismiss();
    }

    @OnClick(R.id.button2)
    public void cancel(){
        dismiss();
    }



    private void saveNote(){

        String title = etTitle.getText().toString();
        String note_text = etNote.getText().toString();

        if(title.equals("") && note_text.equals("")){
            showToast("Please fill all the fields before saving");
        }else{
            //Call Method to add note

            LocalCacheManager.getInstance(getContext()).addNotes(this, title,note_text);
        }

    }


    private void showToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNoteAdded() {
        Toast.makeText(getContext(),"Note Added",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getContext(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onDataNotAvailable() {
        Toast.makeText(getContext(),"Could not add note",Toast.LENGTH_SHORT).show();
    }
}
