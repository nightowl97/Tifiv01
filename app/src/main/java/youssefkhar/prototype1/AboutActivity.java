package youssefkhar.prototype1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutText = (TextView)findViewById(R.id.aboutText);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/robotolight.ttf");
        aboutText.setTypeface(tf);
    }
}
