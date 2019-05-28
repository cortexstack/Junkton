package com.cortexstack.android.junkton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] nameArray = {"Bob", "Alice", "Charlie", "Doops", "Eddie", "Fran", "Gary", "Harry", "India", "Jerry", "Killmonger", "Lemur", "Mad King Aerys", "Norton Anti-anti-virus"};
    //int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    boolean[] spies = {true, false, true, true, false, false, false, true, false, true, true, false, false, false};
    RecyclerView recyclerView;
    private ArrayList<Player> dataCollection;
    static int numberOfAdapters = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        dataCollection = new ArrayList<Player>();
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        for (int i = 0; i < nameArray.length - 1; i++)
            dataCollection.add(new Player(nameArray[i], 0, spies[i]));

        GAdapter adapter = new GAdapter(0, dataCollection);
        recyclerView.setAdapter(adapter);

    }


    public class GAdapter extends RecyclerView.Adapter<GHolder> {

        Context context = recyclerView.getContext();

        private int layoutId;
        private ArrayList<Player> arrayList;

        public GAdapter(int layoutId, ArrayList<Player> arrayList) {
            this.layoutId = layoutId;
            this.arrayList = arrayList;
        }2

2
        @NonNull
        @Override
        public GHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            numberOfAdapters++;
            View view = inflater.inflate(R.layout.list_item, null, false);
            GHolder holder = new GHolder(view);
            TextView viewNumber = holder.integerTextView;

//            TextView viewNumber = (TextView) findViewById(R.id.view_number_textview);
            viewNumber.setText(Integer.toString(numberOfAdapters));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull GHolder holder, int position) {
            TextView textView = holder.textView;
            Player thisData = arrayList.get(position);
            holder.isSpy = thisData.getIsSpy();
            textView.setText(thisData.name);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }


    public class GHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public TextView integerTextView;
        public boolean isSpy;


        public GHolder(View itemView) {

            super(itemView);

            Log.d("TEXTVIEW CREATE?", "GHolder Constructor Called");
            integerTextView = (TextView) itemView.findViewById(R.id.view_number_textview);
            textView = (TextView) itemView.findViewById(R.id.itemTextView);



            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            String spyMessage;
            if (isSpy) {
                spyMessage = textView.getText() + " is a spy";
            } else {
                spyMessage = textView.getText() + " is NOT a spy";
            }

            Toast newToast = Toast.makeText(getApplicationContext(), spyMessage, Toast.LENGTH_SHORT);
            newToast.show();
        }
    }


    public class Player {

        int number;
        String name;
        boolean isSpy;

        public Player(String name, int number, boolean isSpy) {

            this.name = name;
            this.number = number;
            this.isSpy = isSpy;

        }

        public int getNumber() {
            return number;
        }

        public boolean getIsSpy() {
            return isSpy;
        }

        public void setNumber(int number) {
            this.number = number;
        }


        public void setName(String text) {

            this.name = text;
        }

        public String getName() {

            return name;
        }

    }

}
