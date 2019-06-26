package com.example.esteban.brlifeadmin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTipoProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;

import org.json.JSONException;

import java.io.IOException;

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

        btnTipoProducto.setEnabled(true);
        btnRegiones.setEnabled(true);
        btnRol.setEnabled(true);
        btnInteres.setEnabled(true);
        btnHorarioComida.setEnabled(true);
        btnProducto.setEnabled(true);
        btnSabor.setEnabled(true);
        btnMarca.setEnabled(true);
        btnNutriente.setEnabled(true);
        btnProvincia.setEnabled(true);
        btnComuna.setEnabled(true);
        btnTipoPersona.setEnabled(true);
        btnObjetivo.setEnabled(true);

        //Para que funcione el HttpConexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTipoProducto.setEnabled(false);
                intent.putExtra("mantenedor", SelccionMantenedor.TipoProducto.getSeleccion());
                startActivity(intent);
            }
        });


        btnRegiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegiones.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Region.getSeleccion());
                startActivity(intent);
            }
        });

        btnRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRol.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Rol.getSeleccion());
                startActivity(intent);
            }
        });

        btnInteres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInteres.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Interes.getSeleccion());
                startActivity(intent);
            }
        });
        btnHorarioComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorarioComida.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.HorarioComida.getSeleccion());
                startActivity(intent);
            }
        });
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProducto.setEnabled(false);
                try {
                    CargarMantenedorProductoHttpConecction.buscarMantenedorProducto(AdminActivity.this,SelccionMantenedor.Producto.getSeleccion());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(AdminActivity.this,SelccionMantenedor.Nutriente.getSeleccion());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    CargarMantenedorTipoProductoHttpConecction.buscarMantenedorTipoProducto(AdminActivity.this,SelccionMantenedor.TipoProducto.getSeleccion());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Limpiar listas sabor y marca
                CargarMantenedorTresAtributosHttpConecction.limpiarListaMarcaSabor();

                //Cargar listas marca y tipo medicion para spinner de ActivityNuevoProducto con listas dedicadas
                //new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Marca.getSeleccion());
                try {
                    CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(AdminActivity.this,SelccionMantenedor.Marca.getSeleccion());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(AdminActivity.this,SelccionMantenedor.Sabor.getSeleccion());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                intent.putExtra("mantenedor",SelccionMantenedor.Producto.getSeleccion());
                startActivity(intent);
            }
        });
        btnSabor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSabor.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Sabor.getSeleccion());
                startActivity(intent);
            }
        });
        btnMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMarca.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Marca.getSeleccion());
                startActivity(intent);
            }
        });
        btnNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNutriente.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Nutriente.getSeleccion());
                startActivity(intent);
            }
        });
        btnProvincia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProvincia.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Provincia.getSeleccion());
                startActivity(intent);
            }
        });

        btnComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnComuna.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Comuna.getSeleccion());
                startActivity(intent);
            }
        });

        btnTipoPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTipoPersona.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.TipoPersona.getSeleccion());
                startActivity(intent);
            }
        });
        btnObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnObjetivo.setEnabled(false);
                intent.putExtra("mantenedor",SelccionMantenedor.Objetivo.getSeleccion());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnTipoProducto.setEnabled(true);
        btnRegiones.setEnabled(true);
        btnRol.setEnabled(true);
        btnInteres.setEnabled(true);
        btnHorarioComida.setEnabled(true);
        btnProducto.setEnabled(true);
        btnSabor.setEnabled(true);
        btnMarca.setEnabled(true);
        btnNutriente.setEnabled(true);
        btnProvincia.setEnabled(true);
        btnComuna.setEnabled(true);
        btnTipoPersona.setEnabled(true);
        btnObjetivo.setEnabled(true);
    }
}
