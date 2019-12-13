package com.example.worktest;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private View ContactsFragmentView;
    private RecyclerView myContactsList;

    private DatabaseReference ContactsRef;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        ContactsFragmentView = inflater.inflate(R.layout.fragment_contacts, container, false);

        ContactsRef = FirebaseDatabase.getInstance().getReference().child("Teachers");
        myContactsList = (RecyclerView) ContactsFragmentView.findViewById(R.id.nav_contacts);
        myContactsList.setLayoutManager(new LinearLayoutManager(getContext()));

        //getActivity().setTitle("Contacts");
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        // Read from the database
        ContactsRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            String value = dataSnapshot.child("First Name").getValue().toString();

                                            Toast.makeText(getActivity().getApplicationContext(), value, Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    }

        );
        return ContactsFragmentView;
    }
}


/*
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Contacts> options =
                new FirebaseRecyclerOptions.Builder<Contacts>()
                .build();


        FirebaseRecyclerAdapter<Contacts.>
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder
    {
        TextView userFirst, userLast;


        public RequestViewHolder(@NonNull View itemView)
        {
            super(itemView);


            userFirst = itemView.findViewById(R.id

    }

}

*/