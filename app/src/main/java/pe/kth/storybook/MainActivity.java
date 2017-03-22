package pe.kth.storybook;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.markjmind.propose.Propose;

import pe.kth.storybook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ImageView firebirdView;
    int screenWidth,screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        init();
    }

    private void init(){
        firebirdView = binding.iconFirebird;
//        animFireBird();

        Display display = getWindowManager ().getDefaultDisplay ();
        Point size = new Point ();
        display.getSize (size);
        screenWidth = size.x;
        screenHeight = size.y;

        anim360Angle(firebirdView);
    }

    public void animFireBird(){
        float move = 100 * getResources().getDisplayMetrics().density;
        ObjectAnimator rightMove = ObjectAnimator.ofFloat(firebirdView, View.TRANSLATION_X, 0,move);
        rightMove.setDuration(1000);
        rightMove.setInterpolator(null);

        Propose propose = new Propose(this);
        propose.motionRight.play(rightMove);
        propose.motionRight.setMotionDistance(move);

        firebirdView.setOnTouchListener(propose);

    }

    public void anim360Angle(View targetView){
        float move = 150 * getResources().getDisplayMetrics().density;
        Logger.log("#455 move -> " + move + "," + screenWidth + "," + screenHeight);

        ObjectAnimator move_right_anim = ObjectAnimator.ofFloat( targetView , View.TRANSLATION_X , 0f , move);
        move_right_anim.setDuration(2000);
        ObjectAnimator roation_right_anim = ObjectAnimator.ofFloat(targetView , View.ROTATION_X , 0f, 360);
        roation_right_anim.setDuration(1000);

        ObjectAnimator move_left_anim = ObjectAnimator.ofFloat( targetView , View.TRANSLATION_X , move * -1);
        move_left_anim.setDuration(2000);
        ObjectAnimator roation_left_anim = ObjectAnimator.ofFloat(targetView , View.ROTATION_X , 0f, 360);
        roation_left_anim.setDuration(1000);

        ObjectAnimator move_down_anim = ObjectAnimator.ofFloat( targetView , View.TRANSLATION_Y , 0f, move);
        move_left_anim.setDuration(2000);

        ObjectAnimator move_up_anim = ObjectAnimator.ofFloat( targetView , View.TRANSLATION_Y , move * -1);
        move_left_anim.setDuration(2000);

        Propose propose = new Propose(this);
        propose.motionRight.play(move_right_anim);
        propose.motionRight.setMotionDistance(move);
        propose.motionLeft.play(move_left_anim);
        propose.motionLeft.setMotionDistance(move);
        propose.motionDown.play(move_down_anim);
        propose.motionDown.setMotionDistance(move);
        propose.motionUp.play(move_up_anim);
        propose.motionUp.setMotionDistance(move);
//        propose.motionDown.play(move_right_anim).with(roation_right_anim);
//        propose.motionUp.play(move_right_anim).with(roation_right_anim);

        targetView.setOnTouchListener(propose);
    }


}
