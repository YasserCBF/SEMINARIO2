package com.example.seminario2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import missing.namespace.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts;
    private Context context;
    private ContactDatabaseHelper dbHelper;

    public ContactAdapter(List<Contact> contacts, Context context, ContactDatabaseHelper dbHelper) {
        this.contacts = contacts;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.txtName.setText(contact.getName());
        holder.txtPhoneNumber.setText(contact.getPhoneNumber());

        holder.itemView.setOnLongClickListener(v -> {
            android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context)
                    .setTitle("Delete Contact")
                    .setMessage("Are you sure you want to delete this contact?")
                    .setPositiveButton("Yes", (dialogInterface, which) -> {
                        dbHelper.deleteContact(contact.getId());
                        contacts.remove(contact);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .create();
            dialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtPhoneNumber;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
        }
    }
}