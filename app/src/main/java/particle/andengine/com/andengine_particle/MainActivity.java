package particle.andengine.com.andengine_particle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andengine.particle.EntityFactory;
import com.andengine.particle.ParticleSystem;
import com.andengine.particle.ParticleView;
import com.andengine.particle.emmit.RectangleParticleEmitter;
import com.andengine.particle.init.VelocityParticleInitializer;
import com.andengine.particle.modifier.AlphaParticleModifier;
import com.andengine.particle.modifier.ScaleParticleModifier;

public class MainActivity extends AppCompatActivity {
    ParticleView particleView;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        particleView = (ParticleView) findViewById(R.id.particle_view);
        particleView.setAnimationTime(10000);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_small_icon);
        EntityFactory entityFactory = new EntityFactory(bitmap);
        RectangleParticleEmitter emitter = new RectangleParticleEmitter(400, 800, 400, 400);
        ParticleSystem particleSystem = new ParticleSystem(entityFactory, emitter, 5, 10, 20);
        particleSystem.addParticleInitializer(new VelocityParticleInitializer(0, 80, -100, 0));
        particleSystem.addParticleModifier(new ScaleParticleModifier(1, 4, 1, 1.4f));
        particleSystem.addParticleModifier(new ScaleParticleModifier(4, 1, 1.4f, 1.1f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(1, 3, 1, 0.4f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(3, 4, 0.4f, 0.5f));
        particleSystem.addParticleModifier(new AlphaParticleModifier(4, 5, 0.5f, 0.0f));
//        particleSystem.addParticleModifier(new ColorParticleModifier(1, 3, 255, 0, 255, 255, 255, 125));
        particleView.setParticleSystem(particleSystem);
        // Example of a call to a native method
//        tv.setText(stringFromJNI());
    }

    @Override
    protected void onResume() {
        super.onResume();
        particleView.startAnimation();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
