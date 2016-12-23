# AndroidParticle
Android Particle System from AndEngine ,works in a normal view

从AndEngine迁移过来的粒子系统 可以作为普通的view添加到自己的界面中


# Useage
## import
### gradle
    `compile 'com.zhenbeiju:particle:0.0.5'`
### maven
      `<groupId>com.zhenbeiju</groupId>
      <artifactId>particle</artifactId>
      <version>0.0.3</version>
      <type>pom</type>`

## use demo
    1. create EntityFactory with a bitmap
       EntityFactory entityFactory = new EntityFactory(bitmap);
    2. create EntityEmitter
       RectangleParticleEmitter emitter = new RectangleParticleEmitter(540, 1000, 200, 800);
    3. create ParticleSystem
       ParticleSystem particleSystem = new ParticleSystem(entityFactory, emitter, 10, 10, 50);
    4. add ParticleSystem to ParticleView
       particleView.setParticleSystem(particleSystem);
    5. start animation
       particleView.startAnimation();

## bad things
    no double cache , probably draw slowly