package com.example.santos.mis_practicas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.text.TextWatcher;


public class MainActivity extends ActionBarActivity {
    //variables
    private static final String PAGO_TOTAL  = "PAGO_TOTAL";
    private static final String PERSONALIZAR_PORCIENTO = "PERSONALIZAR_PORCIENTO";

    private EditText EditText_propina10;
    private EditText EditText_total10;

    private EditText EditText_propina15;
    private EditText EditText_total15;

    private EditText EditText_propina20;
    private EditText EditText_total20;

    private EditText EditText_Ingrese_Pago;

    private EditText EditTextPropina_final;
    private EditText EditText_Total_Total;

    // variables auxiliares para los calculos
    //variable que guardara el total de la cuenta
    private double D_PAGOTOTAL;

    // Porcentaje establecido de la seeckback
    private int porcentaje_18;
    private TextView TextView_18;
    private SeekBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////////////////////////////////////////////
if(savedInstanceState == null){
            // Si entra aqui significa que la app esta ejecutando por primera vez
            D_PAGOTOTAL = 0;
    porcentaje_18 = 18;
        }else{
    D_PAGOTOTAL  = savedInstanceState.getDouble(PAGO_TOTAL);
            porcentaje_18 = savedInstanceState.getInt(PERSONALIZAR_PORCIENTO);
        }
        // Cargando las referencias de las vistas en sus correpondientes variables
        EditText_propina10 = (EditText)findViewById(R.id.EditText_propina10);
        EditText_total10 = (EditText)findViewById(R.id.EditText_total10);

        EditText_propina15 = (EditText)findViewById(R.id.EditText_propina15);
        EditText_total15 = (EditText)findViewById(R.id.EditText_total15);

        EditText_propina20 = (EditText)findViewById(R.id.EditText_propina20);
        EditText_total20 = (EditText)findViewById(R.id.EditText_total20);


       TextView_18 = (TextView)findViewById(R.id. TextView_18);

        EditTextPropina_final = (EditText)findViewById(R.id.EditTextPropina_final);

        EditText_Total_Total = (EditText)findViewById(R.id.EditText_Total_Total);

        EditText_Ingrese_Pago = (EditText)findViewById(R.id.EditText_Ingrese_Pago);

       barra = (SeekBar)findViewById(R.id.barra);

        // Se agrega un objeto observador que registrara el cambio en el billEditText
        //escuchadores
       EditText_Ingrese_Pago.addTextChangedListener(  EditText_Ingrese_Pago_Listener_Wacher);

        // se agrega el objeto observador para el cambio de posicion del seekBar
        barra.setOnSeekBarChangeListener(barra_Listener);
//////////////////////////////////////////
    }



    ///////////////////////////////////////////////
    private void Actualizando_valores(){
 double porcentaje_10 = D_PAGOTOTAL * 0.1;
        double porcentaje_Total_10 = D_PAGOTOTAL * 0.1;
       EditText_propina10.setText(String.format("%.02f", porcentaje_10));
        EditText_total10.setText(String.format("%.02f", porcentaje_Total_10));

        ///////////////////////////////////////////////
        double porcentaje_15 = D_PAGOTOTAL * 0.15;
        double porcentaje_Total_15 = D_PAGOTOTAL * 0.15;
        EditText_propina15.setText(String.format("%.02f", porcentaje_15));
        EditText_total15.setText(String.format("%.02f", porcentaje_Total_15));
//////////////////////////


        double porcentaje_20 = D_PAGOTOTAL * 0.20;
        double porcentaje_Total_20 = D_PAGOTOTAL * 0.20;
        EditText_propina20.setText(String.format("%.02f", porcentaje_20));
        EditText_total20.setText(String.format("%.02f", porcentaje_Total_20));
/////////////




}
    private void actualizando_Barra(){

        TextView_18.setText(porcentaje_18 + "%");
        double cantidadPorcentaje = D_PAGOTOTAL *porcentaje_18 * 0.01;
               double cantidad_total = cantidadPorcentaje + D_PAGOTOTAL;

        // Despliega los resultados calculados
       EditTextPropina_final.setText(String.format("%.02f", cantidadPorcentaje));
        EditText_Total_Total.setText(String.format("%.02f", cantidad_total));
    }



    //////////////////////////////////////////////////METODO ARA SALVAR ESTADO
    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putDouble(PAGO_TOTAL,D_PAGOTOTAL);
        outState.putInt(PERSONALIZAR_PORCIENTO,porcentaje_18);
    }
    //////////////////METODO SALVAR BARRA
    private SeekBar.OnSeekBarChangeListener barra_Listener=//metodo declarado arriba
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Este metodo se ejecuta cuando existe un cambio de progreso
                    // Es decir cuando se mueve la barra
                    // 1.- Obteniendo la posicion de la SeekBar
                    // y guardandola en la variable adecuada
                    porcentaje_18 = seekBar.getProgress();

                    // 2 .- Invocando al metodo que actualiza los calculos
                    actualizando_Barra();

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };


/////////////////////////////////////////////////


    private TextWatcher EditText_Ingrese_Pago_Listener_Wacher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence Variable_s, int start, int before, int count) {

            // Parsear el texto ingresado a un numero
            try{

                D_PAGOTOTAL = Double.parseDouble(Variable_s.toString());

            }catch(NumberFormatException e){

                D_PAGOTOTAL = 0.0;
            }

            // Actualizar todos los calculos
            actualizando_Barra();
            Actualizando_valores();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
