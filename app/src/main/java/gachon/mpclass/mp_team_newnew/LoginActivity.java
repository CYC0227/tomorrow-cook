package gachon.mpclass.mp_team_newnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_email;
    private EditText edit_password;

    String email;
    String password;

    ImageButton login;


    RetrofitClient retrofitClient = new RetrofitClient();
    Call<Boolean> call;


    public static Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_email = (EditText)findViewById(R.id.editTextId);
        edit_password = (EditText)findViewById(R.id.editTextPassword);


        login = (ImageButton)findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edit_email.getText().toString();
                password = edit_password.getText().toString();

                // login -> main
                            Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(myintent, 1);
//                System.out.println("email = " + email);
//                System.out.println("pw = " + password);
//
//                call = retrofitClient.retrofitService.login(email, password);
//
//                System.out.println(email);
//
//                call.enqueue(new Callback<Boolean>() {
//                    @Override
//                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//
//                        Boolean isSuccessful = response.body();
//
//                        if(isSuccessful){

//                            //μΈμ μμ±
//                            session = new Session(email);

//                            // login -> main
//                            Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivityForResult(myintent, 1);
//                        }
//                        else {
//                            //μ€λ₯ ν μ€νΈ λ©μΈμ§
//                            Toast.makeText(getApplicationContext(), "μ΄λ©μΌμ΄λ λΉλ°λ²νΈλ₯Ό νμΈν΄μ£ΌμΈμ", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Boolean> call, Throwable t) {
//                        System.out.println(t.getMessage());
//                        Log.d("login","μ€ν¨" + t.getMessage());
//                    }
//                });

            }
        });
    }
}