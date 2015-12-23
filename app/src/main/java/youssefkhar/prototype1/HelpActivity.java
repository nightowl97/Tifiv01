package youssefkhar.prototype1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView helpText = (TextView)findViewById(R.id.helpText);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/robotolight.ttf");
        helpText.setTypeface(tf);
    }
}
