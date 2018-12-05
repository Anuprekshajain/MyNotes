package com.anupreksha.mynotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.anupreksha.mynotes.database.LocalCacheManager;
import com.anupreksha.mynotes.database.UpdateNoteViewInterface;
import com.anupreksha.mynotes.models.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNoteFragement extends DialogFragment implements UpdateNoteViewInterface {
    View dialogView;
    @BindView(R.id.editTexttitle)
    EditText etTitle;

    @BindView(R.id.edittextDesc)
    EditText etNote;
    int pos;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_new_note, null);
        builder.setView(dialogView).setTitle("Update Note");
        ButterKnife.bind(this, dialogView);
        Bundle bundle = getArguments();
        String title = bundle.getString("title","");
        etTitle.setText(title);
        String desc=bundle.getString("description","");
        etNote.setText(desc);
        pos=bundle.getInt("id");
        return builder.create();
    }
    @OnClick(R.id.button)
    public void add(){
        updateNote();
        Intent i = new Intent(getContext(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    @OnClick(R.id.button2)
    public void cancel(){
        dismiss();
    }
    public void updateNote() {
        String title=etTitle.getText().toString();
        String des=etNote.getText().toString();
        LocalCacheManager.getInstance(getContext()).updateNote(this, title,des,pos);
    }

}
