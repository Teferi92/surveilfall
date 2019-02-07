package es.santirivera.surveilfall.util.spanner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.HashMap;

import es.santirivera.surveilfall.R;

public class Spanner {

    private static HashMap<String, Integer> symbolToResource = new HashMap<String, Integer>();

    static {
        // Colors
        symbolToResource.put("{W}", R.drawable.ic_w);
        symbolToResource.put("{U}", R.drawable.ic_u);
        symbolToResource.put("{B}", R.drawable.ic_b);
        symbolToResource.put("{R}", R.drawable.ic_r);
        symbolToResource.put("{G}", R.drawable.ic_g);
        // Phyrexian
        symbolToResource.put("{P}", R.drawable.ic_p);
        symbolToResource.put("{W/P}", R.drawable.ic_pw);
        symbolToResource.put("{U/P}", R.drawable.ic_pu);
        symbolToResource.put("{B/P}", R.drawable.ic_pb);
        symbolToResource.put("{R/P}", R.drawable.ic_pr);
        symbolToResource.put("{G/P}", R.drawable.ic_pg);
        // Colorless
        symbolToResource.put("{C}", R.drawable.ic_c);
        // Snow
        symbolToResource.put("{S}", R.drawable.ic_s);
        // Energy
        symbolToResource.put("{E}", R.drawable.ic_e);
        // Tap and Untap
        symbolToResource.put("{T}", R.drawable.ic_t);
        symbolToResource.put("{Q}", R.drawable.ic_q);
        // Hybrid
        symbolToResource.put("{W/U}", R.drawable.ic_wu);
        symbolToResource.put("{W/B}", R.drawable.ic_wb);
        symbolToResource.put("{B/R}", R.drawable.ic_br);
        symbolToResource.put("{B/G}", R.drawable.ic_bg);
        symbolToResource.put("{U/B}", R.drawable.ic_ub);
        symbolToResource.put("{U/R}", R.drawable.ic_ur);
        symbolToResource.put("{R/G}", R.drawable.ic_rg);
        symbolToResource.put("{R/W}", R.drawable.ic_rw);
        symbolToResource.put("{G/W}", R.drawable.ic_gw);
        symbolToResource.put("{G/U}", R.drawable.ic_gu);
        symbolToResource.put("{2/W}", R.drawable.ic_2w);
        symbolToResource.put("{2/U}", R.drawable.ic_2u);
        symbolToResource.put("{2/B}", R.drawable.ic_2b);
        symbolToResource.put("{2/R}", R.drawable.ic_2r);
        symbolToResource.put("{2/G}", R.drawable.ic_2g);
        // Numeral
        symbolToResource.put("{X}", R.drawable.ic_x);
        symbolToResource.put("{0}", R.drawable.ic_0);
        symbolToResource.put("{1}", R.drawable.ic_1);
        symbolToResource.put("{2}", R.drawable.ic_2);
        symbolToResource.put("{3}", R.drawable.ic_3);
        symbolToResource.put("{4}", R.drawable.ic_4);
        symbolToResource.put("{5}", R.drawable.ic_5);
        symbolToResource.put("{6}", R.drawable.ic_6);
        symbolToResource.put("{7}", R.drawable.ic_7);
        symbolToResource.put("{8}", R.drawable.ic_8);
        symbolToResource.put("{9}", R.drawable.ic_9);
        symbolToResource.put("{10}", R.drawable.ic_10);
        symbolToResource.put("{11}", R.drawable.ic_11);
        symbolToResource.put("{12}", R.drawable.ic_12);
        symbolToResource.put("{13}", R.drawable.ic_13);
        symbolToResource.put("{14}", R.drawable.ic_14);
        symbolToResource.put("{15}", R.drawable.ic_15);
        symbolToResource.put("{16}", R.drawable.ic_16);
        symbolToResource.put("{17}", R.drawable.ic_17);
        symbolToResource.put("{18}", R.drawable.ic_18);
        symbolToResource.put("{19}", R.drawable.ic_19);
        symbolToResource.put("{20}", R.drawable.ic_20);

    }

    public static Spannable addManaCostsToText(Context ctx, String str, int limit) {
        SpannableString spannable = new SpannableString(str);
        limit-=2;
        for (String key : symbolToResource.keySet()){
            if (str.contains(key)){
                int lastIndex = 0;
                Drawable icon = ctx.getResources().getDrawable(symbolToResource.get(key), ctx.getTheme());
                icon.setBounds(0,0,limit,limit);
                while (lastIndex != -1){
                    lastIndex = str.indexOf(key, lastIndex);
                    if (lastIndex != -1){
                        ImageSpan span = new ImageSpan(icon, ImageSpan.ALIGN_BOTTOM);
                        spannable.setSpan(span, lastIndex, lastIndex + key.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        lastIndex++;
                    }
                }
            }
        }
        return spannable;
    }

}
