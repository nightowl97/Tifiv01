package youssefkhar.prototype1;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.content.Intent;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        TextView tv = (TextView)findViewById(R.id.welcometxtview);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/robotothin.ttf");
        tv.setTypeface(tf);

        RelativeLayout rlayout = (RelativeLayout)findViewById(R.id.lrlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}
