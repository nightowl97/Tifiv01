package youssefkhar.prototype1;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Typeface;
import android.widget.TextView;

public class LetterPron extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profrag, container, false);
        Typeface rbttf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/robotothin.ttf");
        TextView tv = (TextView)v.findViewById(R.id.firsttext);
        tv.setTypeface(rbttf);
        return v;
    }
}
