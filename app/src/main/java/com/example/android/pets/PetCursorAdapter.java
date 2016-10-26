package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //  return the list item view (instead of null)
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find views
        TextView nameText = (TextView) view.findViewById(R.id.name);
        TextView summaryText = (TextView) view.findViewById(R.id.summary);
        // Find the columns of pet attributes
        int nameColumn = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedColumn = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

        //Read the pet attributes from cursor
        String petName = cursor.getString(nameColumn);
        String petBreed = cursor.getString(breedColumn);

        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed);
        }


        //Update the TextViews with attributes
        nameText.setText(petName);
        summaryText.setText(petBreed);

    }
}