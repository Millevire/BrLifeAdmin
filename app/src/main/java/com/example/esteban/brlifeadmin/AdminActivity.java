package com.example.esteban.brlifeadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;

public class AdminActivity extends AppCompatActivity {
    private Button btnTipoProducto,btnRegiones,btnInteres,btnRol,btnHorarioComida,btnProducto,btnSabor,btnMarca,btnNutriente,btnProvincia,btnComuna, btnTipoPersona, btnObjetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //Hola 40333
        btnTipoProducto=(Button)findViewById(R.id.btnTipoProducto);
        btnRegiones=(Button)findViewById(R.id.btnRegiones);
        btnRol=(Button)findViewById(R.id.btnRol);
        btnInteres=(Button)findViewById(R.id.btnInteres);
        btnHorarioComida=(Button)findViewById(R.id.btnHorarioComida);
        btnProducto=(Button)findViewById(R.id.btnProducto);
        btnSabor=(Button)findViewById(R.id.btnSabor);
        btnMarca=(Button)findViewById(R.id.btnMarca);
        btnNutriente=(Button)findViewById(R.id.btnNutriente);
        btnProvincia=(Button)findViewById(R.id.btnProvincia);
        btnComuna=(Button)findViewById(R.id.btnComuna);
        btnTipoPersona=(Button)findViewById(R.id.btnTipoPersona);
        btnObjetivo=(Button)findViewById(R.id.btnObjetivo);
        final Intent intent =new Intent(this,CrudActivity.class);

        btnTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("mantenedor", SelccionMantenedor.TipoProducto.getSeleccion());
                startActivity(intent);
            }
        });


        btnRegiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Region.getSeleccion());
                startActivity(intent);
            }
        });

        btnRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Rol.getSeleccion());
                startActivity(intent);
            }
        });

        btnInteres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Interes.getSeleccion());
                startActivity(intent);
            }
        });
        btnHorarioComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.HorarioComida.getSeleccion());
                startActivity(intent);
            }
        });
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Producto.getSeleccion());
                startActivity(intent);
            }
        });
        btnSabor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Sabor.getSeleccion());
                startActivity(intent);
            }
        });
        btnMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Marca.getSeleccion());
                startActivity(intent);
            }
        });
        btnNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Nutriente.getSeleccion());
                startActivity(intent);
            }
        });
        btnProvincia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Provincia.getSeleccion());
                startActivity(intent);
            }
        });

        btnComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Comuna.getSeleccion());
                startActivity(intent);
            }
        });

        btnTipoPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.TipoPersona.getSeleccion());
                startActivity(intent);
            }
        });
        btnObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mantenedor",SelccionMantenedor.Objetivo.getSeleccion());
                startActivity(intent);
            }
        });
    }
}
