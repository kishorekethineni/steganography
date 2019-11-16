package c.chaitu.myapplication.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

import c.chaitu.myapplication.MainActivity;
import c.chaitu.myapplication.R;

public class SignupActivity extends AppCompatActivity {

    EditText e3,e4,e5,e6;
    Button b4;
    FirebaseAuth firebaseAuth;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b4=findViewById(R.id.button3);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.editText5);
        tv=findViewById(R.id.gotologin);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });

        firebaseAuth= FirebaseAuth.getInstance();
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=e4.getText().toString();
                String pass=e5.getText().toString();
                if (isValidEmailId(email)==true&&pass.length()>6)
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(SignupActivity.this, "Signup sucess", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                {
                                    Toast.makeText(SignupActivity.this, "user already exists", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(SignupActivity.this, "No connectivity", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Provide valid email and pass", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
