package com.fit.hui.listmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtSo;
    private Spinner spinSo;
    private  ListView lvwUocSo;
    ArrayList arraySo;
    ArrayList arrayUocSo;

    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSo= findViewById(R.id.edt_So);
        spinSo= findViewById(R.id.spin_So);
        lvwUocSo= findViewById(R.id.lvw_UocSo);

        arraySo= new ArrayList();
        arrayUocSo=new ArrayList();
        arraySo.add(22);
        arraySo.add(33);
        arraySo.add(44);
        LoadDaySoToSpinner(spinSo,arraySo);
        LoadDaySoToListView(lvwUocSo, arrayUocSo);
        spinSo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                int n=Integer.parseInt( arraySo.get(i).toString());
                Toast.makeText(MainActivity.this, n+"", Toast.LENGTH_SHORT).show();
                taoDayUocSo( n);
                LoadDaySoToListView(lvwUocSo, arrayUocSo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvwUocSo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                hienmyPupup(i);
                return false;
            }
        });

    }
    void hienmyPupup(final int viTriChon)
    {
        PopupMenu pop= new PopupMenu(this,lvwUocSo );
        pop.getMenuInflater().inflate(R.menu.my_popupmenu,pop.getMenu());
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                int so=0;
                switch (menuItem.getItemId())
                {

                    case R.id.mnu_XoaHet:
                        arrayUocSo.clear();
                        LoadDaySoToListView(lvwUocSo, arrayUocSo);
                        break;
                    case R.id.mnu_Xoachon:

                        arrayUocSo.remove(viTriChon);
                        LoadDaySoToListView(lvwUocSo, arrayUocSo);

                        break;

                }

                return false;
            }
        });
        pop.show();
    }
void LoadDaySoToSpinner( Spinner spin, ArrayList lst)
{
    //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lst)
    ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lst);
        spin.setAdapter(adapter );
}
    void LoadDaySoToListView(ListView lvw, ArrayList lst)
    {
        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lst)
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lst);

        lvw.setAdapter(adapter );
        adapter.notifyDataSetChanged();

    }
   void taoDayUocSo(  int n)
   {
       arrayUocSo.clear();

       for ( int i=1; i<n; i++)
           if(n%i==0)
           arrayUocSo.add(i);
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xulyuocso,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int so=0;
        switch (item.getItemId())
        {
            case R.id.mnu_Them:
                if(edtSo.getText().length()>0) {
                    so = Integer.parseInt(edtSo.getText().toString());
                    arraySo.add(so);
                }
                break;
            case R.id.mnu_XoaHet:
                arrayUocSo.clear();
                LoadDaySoToListView(lvwUocSo, arrayUocSo);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
