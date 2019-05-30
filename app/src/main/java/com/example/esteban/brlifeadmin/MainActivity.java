package com.example.esteban.brlifeadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText etContraseña;
private Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hol
        etContraseña=(EditText)findViewById(R.id.etContraseña);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);

        etContraseña.setText("BrLife1234");

        final Intent intent =new Intent(this,AdminActivity.class);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (etContraseña.getText().toString().equals("BrLife1234")){
                startActivity(intent);
            }else
                Toast.makeText(MainActivity.this, "¡Contraseña incorrecta!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
