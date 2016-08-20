package com.example.victor.persistenciafirebase;

import android.content.Context;
import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.victor.persistenciafirebase.util.LibraryClass;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Firebase firebase;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtEmail = (EditText)findViewById(R.id.edt_email_login);
        edtSenha = (EditText)findViewById(R.id.edt_senha_login);

        firebase = LibraryClass.getFirebase();

        VerifyUserLogged();

    }

    private void InitUser()
    {
        user = new User();
        user.setEmail(edtEmail.getText().toString());
        user.setSenha(edtSenha.getText().toString());
    }

    public void Logon(View v)
    {
     //   Progresbar.setVisibility(View.VISIBLE);
        VerifyLogged();
        InitUser();
    }



    private void VerifyUserLogged()
    {
        if(firebase.getAuth() != null)
        {
            Intent it = new Intent(MainActivity.this, UserMainActivity.class);
            startActivity(it);
            finish();
        }
        else
        {
            InitUser();

            if (user.getToken(this).isEmpty())
            {
                firebase.authWithPassword("password", user.getToken(this), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        user.SaveToken(MainActivity.this,authData.getToken());
                        Intent it = new Intent(MainActivity.this, UserMainActivity.class);
                        startActivity(it);
                        finish();

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                    }
                });
            }

        }

    }



    private void VerifyLogged()
    {
       firebase.authWithPassword(user.getEmail(), user.getSenha(), new Firebase.AuthResultHandler() {
           @Override
           public void onAuthenticated(AuthData authData) {

               user.SaveToken(MainActivity.this,authData.getToken());
              // Progresbar.setVisibility(View.GONE);
               Intent it = new Intent(MainActivity.this, UserMainActivity.class);
               startActivity(it);
                finish();
           }

           @Override
           public void onAuthenticationError(FirebaseError firebaseError) {




              // Progresbar.setVisibility(View.GONE);

           }
       });

    }

}
