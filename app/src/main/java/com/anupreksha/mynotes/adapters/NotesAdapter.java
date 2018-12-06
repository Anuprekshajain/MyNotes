package com.anupreksha.mynotes.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anupreksha.mynotes.R;
import com.anupreksha.mynotes.ui.UpdateNoteFragement;
import com.anupreksha.mynotes.ui.DeleteNoteDataInterface;
import com.anupreksha.mynotes.database.LocalCacheManager;
import com.anupreksha.mynotes.ui.UpdateNoteViewInterface;
import com.anupreksha.mynotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    Context context;
    List<Note> noteList;

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }
    private DeleteNoteDataInterface deleteNoteDataInterface =new DeleteNoteDataInterface() {
        @Override
        public void onDelete() {

        }

        @Override
        public void afterdelete(List<Note> notes) {

        }
    };

    private UpdateNoteViewInterface updateNoteViewInterface =new UpdateNoteViewInterface() {

        @Override
        public void updateNote() {

        }
    };
    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_note, parent, false);
        NotesViewHolder nvh = new NotesViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(final NotesViewHolder holder, final int position) {
        final int pos=position;
        holder.tvTitle.setText(noteList.get(position).getTitle());
        holder.tvNote.setText(noteList.get(position).getNote());
        final int id=noteList.get(position).getId();
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){

                LocalCacheManager.getInstance(view.getContext()).onDelete(deleteNoteDataInterface, noteList.get(position));
                noteList.remove(position);
                notifyItemRemoved(position);
                //notifyItemRangeChanged(position,getItemCount());
            }
        });
        holder.btnupd.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){


                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fragment = activity.getSupportFragmentManager();
                UpdateNoteFragement dialog = new UpdateNoteFragement();
                Bundle bundle = new Bundle();
                bundle.putString("title",noteList.get(position).getTitle());
                bundle.putString("description",noteList.get(position).getNote());
                bundle.putInt("id",id);
                dialog.setArguments(bundle);
                dialog.show(fragment,"Update");
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        ImageButton btndel, btnupd;
        TextView tvTitle, tvNote;

        public NotesViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.textViewTitle);
            tvNote = itemView.findViewById(R.id.textViewDesc);
            btndel=itemView.findViewById(R.id.btndel);
            btnupd=itemView.findViewById(R.id.btnUpd);

        }
    }
}
