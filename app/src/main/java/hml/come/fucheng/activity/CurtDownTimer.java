package hml.come.fucheng.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import hml.come.fucheng.R;

/**
 * Created by TX on 2017/9/6.
 */

public class CurtDownTimer extends CountDownTimer {
    private TextView showText;
    /**
     * @param identifyingCode
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
 *                          {@link #onTick(long)} callbacks.
     */
    public CurtDownTimer(TextView identifyingCode, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.showText = identifyingCode;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTick(long millisUntilFinished) {
        showText.setClickable(false);
        showText.setText(millisUntilFinished/1000 + " s后可重新发送");
        //showText.setBackgroundResource(R.drawable.price_select);
        //showText.setTextColor(Color.BLACK);
        SpannableString spannableString = new SpannableString(showText.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        showText.setText(spannableString);
    }

    @Override
    public void onFinish() {
        showText.setText("重新获取验证码");
        showText.setClickable(true);
        //showText.setBackgroundResource(R.drawable.button_select);
        //showText.setTextColor(Color.WHITE);
    }
}
