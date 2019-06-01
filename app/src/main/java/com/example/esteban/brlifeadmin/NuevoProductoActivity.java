package com.example.esteban.brlifeadmin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NuevoProductoActivity extends AppCompatActivity {
 private Spinner spTipoProducto;
 private RadioButton rbRegistroCodigoBarra,rbRegistroNormal;
 private ArrayAdapter<TipoProducto> adapterTipoProducto;
 private Button btnOpenBarCode,btnBack;
 private EditText etBarCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        //Declaracion WIDGET
        spTipoProducto=(Spinner)findViewById(R.id.spTipoProducto);
        btnOpenBarCode=(Button)findViewById(R.id.btnOpenBarCode);
        btnBack=(Button)findViewById(R.id.btnBack);
        etBarCode=(EditText)findViewById(R.id.etBarCode);
        rbRegistroCodigoBarra=(RadioButton)findViewById(R.id.rbRegistroCodigoBarra);
        rbRegistroNormal=(RadioButton)findViewById(R.id.rbRegistroNormal);



        CargarSpinner();

        rbRegistroCodigoBarra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etBarCode.setText("");
                    etBarCode.setEnabled(true);
                    btnOpenBarCode.setEnabled(true);

                }

            }
        });

       rbRegistroNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   etBarCode.setText("0");
                   etBarCode.setEnabled(false);
                   btnOpenBarCode.setEnabled(false);
               }

           }
       });
        btnOpenBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanner();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void CargarSpinner(){
        adapterTipoProducto= new ArrayAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosTIpoProducto.getListaTipoProducto());
        spTipoProducto.setAdapter(adapterTipoProducto);

    }

    /**
     *
     */
    public void scanner(){
        IntentIntegrator intent= new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        intent.setPrompt(getString(R.string.escanearCodigoBarra));
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result !=null){
            if (result.getContents()==null){
                Toast.makeText(this, R.string.mensajeCancelasteEscaneoCodigoBarra, Toast.LENGTH_LONG).show();

            }else{

                etBarCode.setText(result.getContents().toString());

            }

        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
