package particle.andengine.com.andengine_particle;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.andengine.particle.EntityFactory;
import com.andengine.particle.ParticleSystem;
import com.andengine.particle.ParticleView;
import com.andengine.particle.emmit.CircleOutlineParticleEmitter;
import com.andengine.particle.emmit.PointParticleEmitter;
import com.andengine.particle.emmit.RectangleParticleEmitter;
import com.andengine.particle.init.AccelerationParticleInitializer;
import com.andengine.particle.init.AlphaParticleInitializer;
import com.andengine.particle.init.ColorParticleInitializer;
import com.andengine.particle.init.RotationParticleInitializer;
import com.andengine.particle.init.ScaleParticleInitializer;
import com.andengine.particle.init.VelocityParticleInitializer;
import com.andengine.particle.modifier.AlphaParticleModifier;
import com.andengine.particle.modifier.ColorParticleModifier;
import com.andengine.particle.modifier.ExpireParticleInitializer;
import com.andengine.particle.modifier.ScaleParticleModifier;

public class MainActivity extends AppCompatActivity {
    ParticleView particleView;
    Button button;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        particleView = (ParticleView) findViewById(R.id.particle_view);
        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        particleView.setAnimationTime(50000);
        particleView.setBackgroundColor(0xff000000);
        //在屏幕下方的矩形范围内发射


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleView.clearParticleSystem();
                particleView.addParticleSystem(buildParticleSystem1());
                particleView.startAnimation();
                particleView.setAlpha(1.0f);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleView.clearParticleSystem();
                particleView.addParticleSystem(buildParticleSystem2());
                particleView.startAnimation();
                particleView.setAlpha(1.0f);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleView.clearParticleSystem();
                particleView.addParticleSystem(buildParticleSystem3());
                particleView.startAnimation();
                particleView.setAlpha(1.0f);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private ParticleSystem buildParticleSystem1() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_small_icon);
        EntityFactory entityFactory = new EntityFactory(bitmap);
        int width = 1080;
        int height = 1800;
        RectangleParticleEmitter emitter = new RectangleParticleEmitter(width / 2, height - 450, 210, 240);
        //每秒钟发射10个 最多50个
        final ParticleSystem particleSystem = new ParticleSystem(entityFactory, emitter, 10, 10, 50);
        //粒子的初始速度范围
        particleSystem.addParticleInitializer(new VelocityParticleInitializer(-150, 150, -240, -105));
        //粒子初始缩放范围
        particleSystem.addParticleInitializer(new ScaleParticleInitializer(0.1f, 1.4f));
        //粒子的初始加速度
        particleSystem.addParticleInitializer(new AccelerationParticleInitializer(-60, 60, -50, 0));
        //粒子的寿命
        particleSystem.addParticleInitializer(new ExpireParticleInitializer(5));
        //粒子的透明度转换
        particleSystem.addParticleModifier(new AlphaParticleModifier(0, 1, 0.0f, 1.0f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(1, 3, 1, 0.4f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(3, 4, 0.4f, 0.5f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(4, 5, 0.5f, 0.0f));
        return particleSystem;
    }


    private ParticleSystem buildParticleSystem2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.particle_point);
        EntityFactory entityFactory = new EntityFactory(bitmap);
        int width = 1080;
        int height = 1800;
        PointParticleEmitter emitter = new PointParticleEmitter(width / 2, height / 2);
        //每秒钟发射10个 最多50个
        final ParticleSystem particleSystem = new ParticleSystem(entityFactory, emitter, 250, 250, 500);
        //粒子的初始速度范围
        particleSystem.addParticleInitializer(new VelocityParticleInitializer(-50, 50, -100, 0));
        //粒子初始缩放范围
        particleSystem.addParticleInitializer(new ScaleParticleInitializer(1.0f, 1.0f));
        //粒子的初始加速度
        particleSystem.addParticleInitializer(new AccelerationParticleInitializer(0, 0, -150, -100));
        //粒子的寿命
        particleSystem.addParticleInitializer(new ExpireParticleInitializer(1.0f, 1.6f));
        //粒子的颜色
        particleSystem.addParticleModifier(new ColorParticleModifier(0.0f, 0.5f, 0xff, 0xf0, 0xff, 0x00, 0xff, 0x00));
        particleSystem.addParticleModifier(new ColorParticleModifier(0.5f, 1.0f, 0xff, 0xf0, 0x00, 0xd9, 0x00, 0x3f));
        particleSystem.addParticleModifier(new ColorParticleModifier(1.0f, 2.0f, 0xff, 0xff, 0xd9, 0xff, 0x3f, 0xff));
        return particleSystem;
    }


    private ParticleSystem buildParticleSystem3() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.particle_point);
        int width = 1080;
        int height = 1800;
        final CircleOutlineParticleEmitter particleEmitter = new CircleOutlineParticleEmitter(width * 0.5f, height * 0.5f + 20, 140);
        EntityFactory entityFactory = new EntityFactory(bitmap);
        final ParticleSystem particleSystem = new ParticleSystem(entityFactory, particleEmitter, 80, 80, 480);    //备注1
        particleSystem.addParticleInitializer(new ColorParticleInitializer(0xff, 0, 0));
        particleSystem.addParticleInitializer(new AlphaParticleInitializer(0));
        particleSystem.addParticleInitializer(new VelocityParticleInitializer(-2, 2, -20, -10));
        particleSystem.addParticleInitializer(new RotationParticleInitializer(0.0f, 360.0f));


        particleSystem.addParticleModifier(new ScaleParticleModifier(0, 5, 1, 2.0f));
        particleSystem.addParticleModifier(new ColorParticleModifier(0, 3, 0xff, 0xff, 0, 8f, 0, 0));
        particleSystem.addParticleModifier(new ColorParticleModifier(4, 6, 0xff, 0xff, 0x8f, 0xff, 0x00, 0xff));
        particleSystem.addParticleModifier(new AlphaParticleModifier(0, 1, 0, 1));
        particleSystem.addParticleModifier(new AlphaParticleModifier(5, 6, 1, 0));
        particleSystem.addParticleInitializer(new ExpireParticleInitializer(6, 6));

        return particleSystem;
    }


}

