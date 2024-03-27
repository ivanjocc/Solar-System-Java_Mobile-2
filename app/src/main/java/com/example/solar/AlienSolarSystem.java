package com.example.solar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSystem extends View {
    private List<AstreCeleste> planetas;
    private List<Bitmap> imagenesPlanetas;
    private Random random = new Random();

    public AlienSolarSystem(Context context) {
        super(context);
        init();
    }

    public AlienSolarSystem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlienSolarSystem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        planetas = new ArrayList<>();
        imagenesPlanetas = new ArrayList<>();
        cargarPlanetas();
        cargarImagenesPlanetas();
    }

    private void cargarPlanetas() {
        for (int i = 0; i < 8; i++) {
            AstreCeleste planeta = new AstreCeleste();
            planeta.setName("Planeta " + i);
            planeta.setSize(50 + random.nextInt(50));
            planeta.setColor("#" + Integer.toHexString(random.nextInt(256)) + Integer.toHexString(random.nextInt(256)) + Integer.toHexString(random.nextInt(256)));
            planeta.setX(random.nextInt(getWidth() - 100) + 50);
            planeta.setY(random.nextInt(getHeight() - 100) + 50);
            planeta.setImageName("planet_" + i);
            planetas.add(planeta);
        }
    }

    private void cargarImagenesPlanetas() {
        for (AstreCeleste planeta : planetas) {
            int resourceId = getResources().getIdentifier(planeta.getImageName(), "drawable", getContext().getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            imagenesPlanetas.add(bitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < planetas.size(); i++) {
            AstreCeleste planeta = planetas.get(i);
            Bitmap imagenPlaneta = imagenesPlanetas.get(i);
            if (imagenPlaneta != null) {
                canvas.drawBitmap(imagenPlaneta, planeta.getX() - (imagenPlaneta.getWidth() / 2), planeta.getY() - (imagenPlaneta.getHeight() / 2), null);
            }
        }
    }
}
