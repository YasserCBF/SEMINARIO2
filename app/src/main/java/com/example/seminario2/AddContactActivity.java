package com.example.seminario2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import missing.namespace.R;

public class AddContactActivity extends AppCompatActivity {
    private ContactDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        dbHelper = new ContactDatabaseHelper(this);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.edtName)).getText().toString();
            String phone_number = ((EditText) findViewById(R.id.edtPhoneNumber)).getText().toString();

            if (!name.isEmpty() && !phone_number.isEmpty()) {
                long id = dbHelper.addContact(name, phone_number);
                Contact newContact = new Contact(id, name, phone_number);
                Intent intent = new Intent();
                intent.putExtra("newContact", newContact);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(AddContactActivity.this, "Please enter both name and phone number.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}