package com.vineetnet.stockprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button sub, subl;
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
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        sub = (Button) findViewById(R.id.subR);
        subl = (Button) findViewById(R.id.logs);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = (EditText) findViewById(R.id.em);
                pw = (EditText) findViewById(R.id.pw);



                if(emcheck(em.getText().toString()) && pwcheck(pw.getText().toString()))
                   createAccount(em.getText().toString(),pw.getText().toString());
                else
                    Toast.makeText(MainActivity.this, "Email address or password is improper, please enter correctly", Toast.LENGTH_SHORT).show();




            }
        });

        subl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });
    }



//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            Intent i = new Intent(this,Main3Activity.class);
            i.putExtra("user","HOLA AMIGO!");
            startActivity(i);

        }
    }


    

    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG5", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("TAG4", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}
