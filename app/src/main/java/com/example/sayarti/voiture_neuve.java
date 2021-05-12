package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class voiture_neuve extends Fragment {
    public TextView erreur,T;
    private EditText input;
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    public static final String URL_PRODUCTS = "http://dev.goodlinks.tn/sayarti-apps/test.php";

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;


    public voiture_neuve() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_bienvenu, container, false);
       // CardView lcard = v.findViewById(R.id.logoCard);
        //lcard.setOnClickListener(v1 -> startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.sayarti.tn/"))));

        input = v.findViewById(R.id.inp);
        Button recher = v.findViewById(R.id.rech);

        //getting the recyclerview from xml
        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initializing the productlist
                productList = new ArrayList<>();
                //this method will fetch and parse json
                //to display it in recyclerview
                if(input.equals(""))
                {
                    loadProducts();
                }
                else
                {
                    productList.clear();
                    postProducts();
                }
            }
        });
        return v;


    }

    private void postProducts()
    {
        String s = input.getText().toString();

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
                            }
                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(getContext(), productList);
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
                Map<String,String> params = new HashMap<String, String>();
                params.put("data",s);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
    }
    //Toast.makeText(getApplication(),"AAAAAAAA",Toast.LENGTH_LONG).show();
    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */


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
                        }
                        //creating adapter object and setting it to recyclerview
                        ProductAdapter adapter = new ProductAdapter(getContext(), productList);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        erreur.setText(e.getMessage());
                        //Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_LONG).show();
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





