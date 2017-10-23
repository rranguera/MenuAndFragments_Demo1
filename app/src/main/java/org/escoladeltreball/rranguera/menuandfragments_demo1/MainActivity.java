package org.escoladeltreball.rranguera.menuandfragments_demo1;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    static boolean yaPuntuado = false;

    private Button btnAdd;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    private void setup() {
        btnAdd = (Button) findViewById(R.id.btn1_carregaFragm);
        btnRemove = (Button) findViewById(R.id.btn2_treuFragm);

        btnAdd.setOnClickListener(v -> afegirFragment());
        btnRemove.setOnClickListener(v -> eliminarFragment());
    }

    private void afegirFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer_desti, BlankFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }

    private void eliminarFragment() {
        FragmentManager mngr = getSupportFragmentManager();

        if(mngr.getBackStackEntryCount() > 0){
            mngr.popBackStack();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_main_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_about:
                Toast.makeText(this, "Soy la pera de App!", Toast.LENGTH_SHORT).show();
                // **Indica que hem consumit l'event:
                return true;

            case R.id.item_hola:
                Toast.makeText(this, "Bon dia tingui", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_rate:
                Toast.makeText(this, "Give us your stars :D", Toast.LENGTH_SHORT).show();
                yaPuntuado = true;
                return true;

        }

        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        //Podem desactivar algun element, per exemple quan ja haguéssin valorat la App:
        if (yaPuntuado) menu.findItem(R.id.item_rate).setEnabled(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
