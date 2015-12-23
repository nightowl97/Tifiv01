package youssefkhar.prototype1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.view.View;
import android.view.Gravity;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.List;

import android.util.Log;
import android.widget.ViewSwitcher;


public class MainActivity extends AppCompatActivity {

    TextSwitcher textSwitcher;
    TextView letterView;
    int choice;
    ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(
            "\u2d30", "\u2d31", "\u2d33", "\u2d33\u2d6f", "\u2d37", "\u2d39", "\u2d3b", "\u2d3c", "\u2d3d", "\u2d3d\u2d6f", "\u2d40", "\u2d43", "\u2d44", "\u2d45",
            "\u2d47", "\u2d49", "\u2d4a", "\u2d4d", "\u2d4e", "\u2d4f", "\u2d53", "\u2d54", "\u2d55", "\u2d56", "\u2d59", "\u2d5a",
            "\u2d5b", "\u2d5c", "\u2d5f", "\u2d61", "\u2d62", "\u2d63", "\u2d65"));
    ArrayList<String> freqStack = new ArrayList<>();
    ArrayList<String> infStack = new ArrayList<>();
    String[] menuStr = {"Settings", "Help", "About"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////////TextSwitcher Shenanigans///////////////////////////////////
        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/tamaloutunicode.otf");
        textSwitcher = (TextSwitcher)findViewById(R.id.text_switcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(MainActivity.this);
                myText.setTextSize(60);
                myText.setTextColor(Color.parseColor("#000000"));
                myText.setGravity(Gravity.CENTER_HORIZONTAL);
                myText.setTypeface(tf);
                myText.setText("start");
                return myText;
            }
        });
        textSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        textSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        //////////////////////////////////////NavDrawer/////////////////////////////////////////////
        navFragment drawerFragment = (navFragment)getSupportFragmentManager().findFragmentById(R.id.drawerFragmentId);
        drawerFragment.setUp(R.id.drawerFragmentId, (DrawerLayout)findViewById(R.id.drawerLayoutId));
        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuStr);
        ListView menuList = (ListView)findViewById(R.id.menuList);
        menuList.setAdapter(myAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if("Settings".equals(String.valueOf(parent.getItemAtPosition(position)))) {
                    Intent i1 = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i1);
                }
                if("Help".equals(String.valueOf(parent.getItemAtPosition(position)))){
                    Intent i2 = new Intent(MainActivity.this, HelpActivity.class);
                    startActivity(i2);
                }
                if("About".equals(String.valueOf(parent.getItemAtPosition(position)))){
                    Intent i3 = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(i3);
                }
            }
        });

    }

    public void smartShuffle(int ch){
        //letterView = (TextView)findViewById(R.id.letterView);

        //Needs more elaborate shuffling method
        int num;

        if (!alphabet.isEmpty()){
            num = (int)(Math.random() * alphabet.size()) ;
            //letterView.setText(alphabet.get(num));
            textSwitcher.setText(alphabet.get(num));
            if(ch == 1) {
                infStack.add(alphabet.get(num));
                alphabet.remove(num);
            }else{
                freqStack.add(alphabet.get(num));
                alphabet.remove(num);
            }
        }else {
            if (!freqStack.isEmpty()) {
                num = (int) (Math.random() * freqStack.size());
                //letterView.setText(freqStack.get(num));
                textSwitcher.setText(freqStack.get(num));
                if (ch == 1) {
                    infStack.add(freqStack.get(num));
                    freqStack.remove(num);
                }
            } else {
                num = (int) (Math.random() * infStack.size());
                //letterView.setText(infStack.get(num));
                textSwitcher.setText(infStack.get(num));
                if (ch == 0) {
                    freqStack.add(infStack.get(num));
                    infStack.remove(num);
                }
            }
        }
    }

    public void checkButtonClick(View view){
        switch (view.getId()){
            case R.id.rbutton:
                //if right button
                choice = 1;
                smartShuffle(choice);
                break;
            case R.id.wbutton:
                //if wrong button
                choice = 0;
                smartShuffle(choice);
                break;
        }
    }

    public void showPron(String str){

        LetterPron myfrag = new LetterPron();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mainlayout, myfrag, "fragtag");
        transaction.commit();
    }

    public void switcherTouch(View view){
        TextView currentTextView = (TextView)textSwitcher.getCurrentView();
        String currentletter = currentTextView.getText().toString();
        showPron(currentletter);
    }

    public void onWrongClick(View view){

    }
}
