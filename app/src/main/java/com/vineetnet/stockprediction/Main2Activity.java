package com.vineetnet.stockprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button sub,regg;
    private EditText em;
    private EditText pw;

    private static boolean emcheck(String em) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(em);

        if(mat.matches()){

            return true;
        }else{

            return false;
        }
    }

    private static boolean pwcheck(String pw) {


        if(pw.length()>5){

            return true;
        }else{

            return false;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mAuth = FirebaseAuth.getInstance();

        sub = (Button) findViewById(R.id.subL);
        regg = (Button) findViewById(R.id.sup);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = (EditText) findViewById(R.id.em1);
                pw = (EditText) findViewById(R.id.pw1);

                if(emcheck(em.getText().toString()) && pwcheck(pw.getText().toString()))
                    laugin(em.getText().toString(),pw.getText().toString());
                else
                    Toast.makeText(Main2Activity.this, "Email address or password is improper, please enter correctly", Toast.LENGTH_SHORT).show();




            }
        });

        regg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });


    }


    private void updateUI(FirebaseUser currentUser) {
       if(currentUser!=null){
           Intent i = new Intent(this,Main3Activity.class);
           i.putExtra("userz","HOLA AMIGO!");
           startActivity(i);

       }
    }

    private void laugin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG6", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG7", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Main2Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


}
