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
import com.google.firebase.auth.FirebaseAuth;

import c.chaitu.myapplication.R;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText emailreset;
    Button reset;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        emailreset=findViewById(R.id.emailreset);
        reset=findViewById(R.id.resetbtn);
        tv3=findViewById(R.id.gotologinpage);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            String email=emailreset.getText().toString();
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPasswordActivity.this, "A mail has been sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
