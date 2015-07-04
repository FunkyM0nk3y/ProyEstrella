package diplomado.ccm.itesm.proyestrella;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.SeekBar;


public class Star extends ActionBarActivity {

    private Button btnDibuja;
    private ImageView contenedor;
    private SeekBar sbStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);

        btnDibuja = (Button) findViewById(R.id.buttonStar);
        contenedor = (ImageView) findViewById(R.id.imageViewStar);
        sbStar = (SeekBar) findViewById(R.id.seekBarStar);

        sbStar.setMax(50);
        btnDibuja.setOnClickListener(listenerBtnStar);
        sbStar.setOnSeekBarChangeListener(listenerSB);
    }

    private View.OnClickListener listenerBtnStar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dibujaEstrella(10);
        }
    };

    private SeekBar.OnSeekBarChangeListener listenerSB = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress = (progress <= 3) ? 3 : progress;
            dibujaEstrella(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void dibujaEstrella(int numLineas) {
        Bitmap bm = Bitmap.createBitmap(
                contenedor.getWidth(),
                contenedor.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        contenedor.setImageBitmap(bm);
        Paint pen = new Paint();
        pen.setColor(Color.BLUE);

        int x = contenedor.getWidth() / 2;
        int y = contenedor.getHeight() / 2;
        int ladoChico = contenedor.getWidth() > contenedor.getHeight() ?
                contenedor.getHeight() : contenedor.getWidth();
        ladoChico /= 2;
        int intervalo = ladoChico /numLineas;
        for (int i = 1, j = numLineas; i<= numLineas; i++, j--) {
            canvas.drawLine(x, y - i * intervalo, x + j * intervalo, y, pen);
            canvas.drawLine(x, y + i * intervalo, x - j * intervalo, y, pen);
            canvas.drawLine(x, y - i * intervalo, x - j * intervalo, y, pen);
            canvas.drawLine(x, y + i * intervalo, x + j * intervalo, y, pen);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_star, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
