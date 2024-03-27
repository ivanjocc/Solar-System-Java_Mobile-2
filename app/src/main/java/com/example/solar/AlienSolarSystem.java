package com.example.solar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSystem extends View {
    private List<AstreCeleste> planetas;
    private List<Bitmap> imagenesPlanetas;
    private Random random = new Random();
    private Bitmap naveImagen;
    private float naveX = 100;
    private float naveY = 200;

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w != oldw || h != oldh) {
            colocarObjetosAleatoriamente();
            invalidate();
        }
    }


    private void init() {
        planetas = new ArrayList<>();
        imagenesPlanetas = new ArrayList<>();
        cargarPlanetas();
        naveImagen = BitmapFactory.decodeResource(getResources(), R.drawable.nave);

    }

    private void colocarObjetosAleatoriamente() {
        final int MAX_INTENTOS = 100;
        ArrayList<Rect> ocupado = new ArrayList<>();

        for (int i = 0; i < planetas.size(); i++) {
            AstreCeleste planeta = planetas.get(i);
            Bitmap imagenPlaneta = imagenesPlanetas.get(i);
            Rect area;
            boolean colision;
            int intentos = 0;

            do {
                colision = false;
                int x = random.nextInt(getWidth() - imagenPlaneta.getWidth());
                int y = random.nextInt(getHeight() - imagenPlaneta.getHeight());
                area = new Rect(x, y, x + imagenPlaneta.getWidth(), y + imagenPlaneta.getHeight());

                for (Rect r : ocupado) {
                    if (Rect.intersects(r, area)) {
                        colision = true;
                        break;
                    }
                }
                intentos++;
            } while (colision && intentos < MAX_INTENTOS);

            if (!colision) {
                planeta.setX(area.left + imagenPlaneta.getWidth() / 2);
                planeta.setY(area.top + imagenPlaneta.getHeight() / 2);
                ocupado.add(area);
            }
        }

    }

    private void cargarPlanetas() {
        planetas.clear();
        imagenesPlanetas.clear();

        planetas.add(new AstreCeleste(1, "Jupiter", 50, "#AAAAAA", 0, "jupiter", 100, 200));
        planetas.add(new AstreCeleste(2, "Marte", 70, "#FFFF00", 0, "marte", 300, 400));
        planetas.add(new AstreCeleste(3, "Neptuno", 80, "#0000FF", 1, "neptuno", 500, 600));

        for (AstreCeleste planeta : planetas) {
            int resourceId = getResources().getIdentifier(planeta.getImageName(), "drawable", getContext().getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            imagenesPlanetas.add(bitmap);
        }
    }


//    private void cargarImagenesPlanetas() {
//        for (AstreCeleste planeta : planetas) {
//            int resourceId = getResources().getIdentifier(planeta.getImageName(), "drawable", getContext().getPackageName());
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
//            imagenesPlanetas.add(bitmap);
//        }
//    }

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

        if (naveImagen != null) {
            canvas.drawBitmap(naveImagen, naveX, naveY, null);
        }
    }

    public void setNaveX(float x) {
        float limiteIzquierdo = 0;
        float limiteDerecho = getWidth() - (naveImagen != null ? naveImagen.getWidth() : 0);

        x = Math.max(limiteIzquierdo, Math.min(limiteDerecho, x));

        this.naveX = x;
    }

    public void setNaveY(float y) {
        float limiteSuperior = 0;
        float limiteInferior = getHeight() - (naveImagen != null ? naveImagen.getHeight() : 0);

        y = Math.max(limiteSuperior, Math.min(limiteInferior, y));

        this.naveY = y;
    }

    public float getNaveX() {
        return naveX;
    }

    public float getNaveY() {
        return naveY;
    }

}
