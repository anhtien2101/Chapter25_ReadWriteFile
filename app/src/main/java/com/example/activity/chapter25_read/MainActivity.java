package com.example.activity.chapter25_read;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends ListActivity {
    TextView tvSelection;
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelection = (TextView) findViewById(R.id.tvSelection);
        try {
            // open file in raw and get list node in file
            InputStream is = getResources().openRawResource(R.raw.words);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is, null);
            NodeList listWord = doc.getElementsByTagName("word");
            for (int i = 0; i < listWord.getLength(); i++) {
                // get childNode in each Node
                NodeList childs = listWord.item(i).getChildNodes();
                // add name of childnode into items
                items.add(childs.item(0).getNodeValue());
            }
            is.close();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // put items to a listview
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    // if one of item of listview is clicked, set that value to textview
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        tvSelection.setText(items.get(position));
    }
}
