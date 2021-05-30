package com.example.sayarti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ListVoiture extends Fragment {
    RecyclerView recyclerView;

    public static final String URL_PRODUCTS = "http://dev.goodlinks.tn/sayarti-apps/test.php";
    public static final String URL_LINK_PROD = "http://dev.goodlinks.tn/sayarti-apps/linkCar.php";

    //a list to store all the products
    List<Product> productList;
    ArrayList<String> ProdcarNames = new ArrayList();
    private final String sear;
    //ArrayList<String> selectedcar = new ArrayList();
    ArrayList<String> selectedcar = new ArrayList<String>();
    //private final String selc;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ListVoiture(String sear) {
        // Required empty public constructor
        this.sear = sear;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_voiture, container, false);
        //Toast.makeText(getContext(),sear.toString(),Toast.LENGTH_LONG).show();
        productList = new ArrayList<>();
        //getting the recyclerview from xml
        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        if(sear.equals(""))
        {
            loadProducts();
        }
        else
        {
            productList.clear();
            postProducts();
        }

        this.configureOnClickRecyclerView();

        return v;
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_list_voiture)
            .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    ProductAdapter adapter = new ProductAdapter(getContext(), productList,ProdcarNames);
                    String searchedCar = adapter.getCarNames(position);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LINK_PROD,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray P_array = new JSONArray(response);


                                        //traversing through all the object
                                        for (int i = 0; i < P_array.length(); i++) {

                                            //getting product object from json array
                                            JSONObject product = P_array.getJSONObject(i);

                                            selectedcar.add(product.getString("Car_PostName"));

                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            String url = "https://www.sayarti.tn/prix-des-voitures/"+selectedcar.get(position).toString();
                                            intent.setData(Uri.parse(url));
                                            getActivity().startActivity(intent);

                                        }
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> params = new HashMap<>();
                            params.put("selectedCar",searchedCar);

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
                    requestQueue.add(stringRequest);

                }
            });
    }

    private void postProducts()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this,response.trim(),Toast.LENGTH_LONG).show();

                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            input.setText(jsonObject.getString("data"));
                            JSONArray P_array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < P_array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = P_array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(
                                        product.getString("post_title").toUpperCase(),
                                        product.getString("sale_price").toUpperCase(),
                                        product.getString("type_de_carburant").toUpperCase(),
                                        product.getString("engine").toUpperCase(),
                                        product.getString("puissance_ch_din").toUpperCase(),
                                        product.getString("boite").toUpperCase(),
                                        product.getString("path"),
                                        product.getString("trans").toUpperCase()

                                ));
                                //ProdcarNames.add(product.getString("post_title"));
                            }
                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(getContext(), productList,ProdcarNames);
                            recyclerView.setAdapter(adapter);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            //erreur.setText(e.getMessage());
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("data",sear);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
    }
    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                response -> {

                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject(i);
                            //adding the product to product list
                            productList.add(new Product(
                                    product.getString("post_title").toUpperCase(),
                                    product.getString("sale_price").toUpperCase(),
                                    product.getString("type_de_carburant").toUpperCase(),
                                    product.getString("engine").toUpperCase(),
                                    product.getString("puissance_ch_din").toUpperCase(),
                                    product.getString("boite").toUpperCase(),
                                    product.getString("path"),
                                    product.getString("trans").toUpperCase()
                            ));
                            //ProdcarNames.add(product.getString("post_title"));
                        }
                        //creating adapter object and setting it to recyclerview
                        ProductAdapter adapter = new ProductAdapter(getContext(), productList,ProdcarNames);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //erreur.setText(e.getMessage());
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(Objects.requireNonNull(getContext())).add(stringRequest);

    }
}