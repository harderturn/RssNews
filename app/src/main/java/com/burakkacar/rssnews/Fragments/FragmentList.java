package com.burakkacar.rssnews.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burakkacar.rssnews.Adapters.RecyclerListAdapter;
import com.burakkacar.rssnews.Model.Haber;
import com.burakkacar.rssnews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by MuhammedBurak on 29.12.2016.
 */

public class FragmentList extends android.support.v4.app.Fragment
{

    public String xml = "";
    Document doc = null;
    List<Haber> haberler = new ArrayList<Haber>();
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ntv.com.tr/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        MyInterface service = retrofit.create(MyInterface.class);

        Call<NewModel> mModel = service.getMyNews();
*/
        new MyTask().execute();

        return view;
    }

    public class MyTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try
            {
                doc = Jsoup.connect("http://www.ntv.com.tr/gundem.rss")
                        .userAgent("Chrome")
                        .get();
                Elements titles = doc.select("title");
                Elements summaries = doc.select("summary");
                int i = 1;
                for (Element element2 : summaries)
                {
                    Haber haber = new Haber();
                    haber.setBaslik(titles.get(i).text());
                    haber.setOzet(element2.text());
                    haberler.add(haber);
                    i++;
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(),haberler);
            recyclerView.setAdapter(adapter);
        }
    }
}
