package mx.grupogarcia.soportewear.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.grupogarcia.soportewear.R;
import mx.grupogarcia.soportewear.adapters.MascotaAdapter;
import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.presenters.IListaMascotasPresenter;
import mx.grupogarcia.soportewear.presenters.ListaMascotasPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMascotasFragment extends Fragment implements  IListaMascotasFragment {

    private static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IListaMascotasPresenter presenter;
    public ListaMascotasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_lista_mascotas, container, false);
        listaMascotas=(RecyclerView)v.findViewById(R.id.listaMascotas);
        presenter=new ListaMascotasPresenter(this,getContext());
        presenter.getMascotasRest();
        return  v;
    }

    @Override
    public void generateVerticalLayout() {
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador=new MascotaAdapter(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void initAdapter(MascotaAdapter adapter) {
        listaMascotas.setAdapter(adapter);
    }
}
