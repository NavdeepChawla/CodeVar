package com.vit.codevar.ui.TimelineFragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;
import com.vit.codevar.MainActivity;
import com.vit.codevar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


public class TimelineFragment extends Fragment
{
    private FitChart timelineChart;
    private Long startTime, endTime, currentTime, doneTime;
    private TextView currentRoundName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_timeline,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.fragmentTitle.setText(R.string.timelineTitle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int Width = displayMetrics.widthPixels;
        int Height = displayMetrics.heightPixels;

        final LinearLayout ll12=view.findViewById(R.id.timelinell12);
        final LinearLayout ll1=view.findViewById(R.id.timelinell1);
        final LinearLayout ll2=view.findViewById(R.id.timelinell2);
        final LinearLayout ll3=view.findViewById(R.id.timelinell3);
        final LinearLayout ll4=view.findViewById(R.id.timelinell4);
        final LinearLayout ll5=view.findViewById(R.id.timelinell5);
        final LinearLayout ll6=view.findViewById(R.id.timelinell6);
        final LinearLayout ll7=view.findViewById(R.id.timelinell7);
        final LinearLayout ll8=view.findViewById(R.id.timelinell8);
        final LinearLayout ll9=view.findViewById(R.id.timelinell9);
        final LinearLayout ll10=view.findViewById(R.id.timelinell10);
        final LinearLayout ll11=view.findViewById(R.id.timelinell11);

        ll12.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll1.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll2.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll3.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll4.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll5.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll6.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll7.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll8.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll9.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll10.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));
        ll11.setLayoutParams(new LinearLayout.LayoutParams(Width/3,Height/10));

        timelineChart = view.findViewById(R.id.timelineChart);
        currentRoundName = view.findViewById(R.id.currentRoundName);
        timelineChart.setMinValue(0);
        timelineChart.setMaxValue(1440);
        startTime = 1575725400000L; //7 DEC 2019 7:00 PM
        endTime = 1575811800000L; //8 DEC 2019 7:00 PM

        final Resources resources = getResources();
        final Collection<FitChartValue> values = new ArrayList<>();

        if(Calendar.getInstance().getTimeInMillis() <= endTime)
        {
            new CountDownTimer(endTime-startTime,60000) {
                @Override
                public void onTick(long l) {
                    currentTime = Calendar.getInstance().getTimeInMillis();
                    doneTime = (currentTime-startTime)/(1000*60);
                    Log.i("INFO",String.valueOf(doneTime));

                    if(doneTime < 0)
                    {
                        //Event Not Started
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));
                        TextView UpNext=view.findViewById(R.id.timelineUpNext);
                        UpNext.setVisibility(View.VISIBLE);
                        currentRoundName.setText(R.string.defaultRoundName);

                        TextView CurTV=(TextView) ll1.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll2.getParent().requestChildFocus(ll2,ll2);
                    }
                    else if(doneTime >= 0 && doneTime <= 120)
                    {
                        //Participant's Arrival
                        values.add(new FitChartValue(doneTime, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(120-doneTime, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.participantsArrival);

                        TextView prevTV=(TextView) ll1.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll2.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#02BFE9"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll3.getParent().requestChildFocus(ll3,ll3);
                    }
                    else if(doneTime > 120 && doneTime <= 300)
                    {
                        //Round 1
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime - 120, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180 - (doneTime - 120), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.roundOne);

                        TextView prevTV=(TextView) ll2.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll3.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll4.getParent().requestChildFocus(ll4,ll4);
                    }
                    else if(doneTime > 300 && doneTime <= 420)
                    {
                        //Pizza
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime - 300, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(120 - (doneTime - 300), resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.pizza);
                        TextView prevTV=(TextView) ll3.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll4.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#02BFE9"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll5.getParent().requestChildFocus(ll5,ll5);
                    }
                    else if(doneTime > 420 && doneTime <= 660)
                    {
                        //Round2
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime - 420, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(240 - (doneTime - 420), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.roundTwo);
                        TextView prevTV=(TextView) ll4.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll5.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#A400D5"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll6.getParent().requestChildFocus(ll6,ll6);
                    }
                    else if(doneTime > 660 && doneTime <= 840)
                    {
                        //Fun Event
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime - 660, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(180 - (doneTime - 660), resources.getColor(R.color.funBreakInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.funEvent);

                        TextView prevTV=(TextView) ll5.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll6.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll7.getParent().requestChildFocus(ll7,ll7);
                    }
                    else if(doneTime > 840 && doneTime <= 900)
                    {
                        //Breakfast
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(doneTime - 840, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(60 - (doneTime - 840), resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.breakfast);

                        TextView prevTV=(TextView) ll6.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll7.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#02BFE9"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll8.getParent().requestChildFocus(ll8,ll8);
                    }
                    else if(doneTime > 900 && doneTime <= 1080)
                    {
                        //Round 3
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime - 900, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180 - (doneTime - 900), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.roundThree);

                        TextView prevTV=(TextView) ll7.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll8.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#E5AB02"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll9.getParent().requestChildFocus(ll9,ll9);
                    }
                    else if(doneTime > 1080 && doneTime <= 1200)
                    {
                        //Lunch
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime - 1080, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(120 - (doneTime - 1080), resources.getColor(R.color.foodInactive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.lunch);
                        TextView prevTV=(TextView) ll8.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll9.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#02BFE9"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll10.getParent().requestChildFocus(ll10,ll10);
                    }
                    else if(doneTime > 1200 && doneTime <= 1380)
                    {
                        //Round 4
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(doneTime - 1200, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180 - (doneTime - 1200), resources.getColor(R.color.roundInactive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.roundFour);
                        TextView prevTV=(TextView) ll9.getChildAt(0);
                        prevTV.setTextColor(Color.parseColor("#748290"));
                        prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                        TextView CurTV=(TextView) ll10.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#A400D5"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        ll11.getParent().requestChildFocus(ll11,ll11);
                    }
                    else if(doneTime > 1380 && doneTime <= 1440)
                    {
                        //Break
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(120, resources.getColor(R.color.foodActive)));
                        values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                        values.add(new FitChartValue(doneTime - 1380, resources.getColor(R.color.funBreakActive)));
                        values.add(new FitChartValue(60 - (doneTime - 1380), resources.getColor(R.color.funBreakInactive)));

                        currentRoundName.setText(R.string.breakEnd);
                        TextView CurTV=(TextView) ll10.getChildAt(0);
                        CurTV.setTextColor(Color.parseColor("#A400D5"));
                        CurTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                        TextView UpNext=view.findViewById(R.id.timelineUpNext);
                        UpNext.setVisibility(View.INVISIBLE);
                        ll11.getParent().requestChildFocus(ll11,ll11);
                    }
                    timelineChart.setValues(values);
                }

                @Override
                public void onFinish() {
                    //Event Over
                    values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
                    values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
                    values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
                    values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));

                    currentRoundName.setText(R.string.eventEnd);
                    TextView prevTV=(TextView) ll10.getChildAt(0);
                    prevTV.setTextColor(Color.parseColor("#748290"));
                    prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                    ll2.getParent().requestChildFocus(ll2,ll2);
                }
            }.start();
        }
        else
        {
            //Event Over
            values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(240f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(180f, resources.getColor(R.color.funBreakActive)));
            values.add(new FitChartValue(60f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(120f, resources.getColor(R.color.foodActive)));
            values.add(new FitChartValue(180f, resources.getColor(R.color.roundActive)));
            values.add(new FitChartValue(60f, resources.getColor(R.color.funBreakActive)));

            currentRoundName.setText(R.string.eventEnd);
            TextView prevTV=(TextView) ll10.getChildAt(0);
            prevTV.setTextColor(Color.parseColor("#748290"));
            prevTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            ll2.getParent().requestChildFocus(ll2,ll2);
            timelineChart.setValues(values);
        }
    }
}
