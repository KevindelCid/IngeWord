package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.independentsoft.office.ExtendedBoolean;
import com.independentsoft.office.vml.ConnectType;
import com.independentsoft.office.vml.HorizontalPosition;
import com.independentsoft.office.vml.Image;
import com.independentsoft.office.vml.Lock;
import com.independentsoft.office.vml.Position;
import com.independentsoft.office.vml.RelativeHorizontalPosition;
import com.independentsoft.office.vml.RelativeVerticalPosition;
import com.independentsoft.office.vml.Shape;
import com.independentsoft.office.vml.ShapePath;
import com.independentsoft.office.vml.ShapeStyle;
import com.independentsoft.office.vml.ShapeTemplate;
import com.independentsoft.office.vml.Stroke;
import com.independentsoft.office.vml.StrokeJoinStyle;
import com.independentsoft.office.vml.VerticalPosition;
import com.independentsoft.office.word.HorizontalAlignmentType;
import com.independentsoft.office.word.Paragraph;
import com.independentsoft.office.word.Run;
import com.independentsoft.office.word.VmlObject;
import com.independentsoft.office.word.WordDocument;
import com.independentsoft.office.word.headerFooter.Header;
import com.independentsoft.office.word.sections.PageSize;
import com.independentsoft.office.word.sections.Section;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class conclusion_activity extends AppCompatActivity {




    EditText edte;
    Button btnintro, cu, co;
    TextView name, printro;

    @Override
    public void onBackPressed(){




        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);
        guarda();
        intl.putExtra("nombreProyecto", proyectname());
        startActivity(intl);


    }


    private String recibirTexto(){

        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("texto");



        return texto;
    }

    private String recibirPivote(){

        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("pivote");



        return texto;
    }

    private String proyectname(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("nombreProyecto");


        return texto;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion_activity);



        printro = findViewById(R.id.prcuerpo);


        printro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intl = new Intent(conclusion_activity.this,antesconclusion.class);
                intl.putExtra("nombreProyecto", proyectname());
                startActivity(intl);
//
//                startActivity(new Intent(introduccion_activity.this,antesIntro.class));


            }
        });



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        EditText texto;
        texto = (EditText) findViewById(R.id.TextoCuerpo1);
        Button boton;
//        try {
//            texto.setText(recibirTexto());
//        }catch (Exception e) {}



        edte = (EditText)findViewById(R.id.Textoconclu);
        btnintro = (Button ) findViewById(R.id.intro);
        cu = (Button ) findViewById(R.id.cuerpo);
        co = (Button ) findViewById(R.id.conclucion);
        name = (TextView) findViewById(R.id.nameco);
        name.setText(proyectname());
        co.setEnabled(false);
        co.setBackgroundColor(Color.GRAY);
        String archivos [] =fileList();
        String p ;
        p = recibirPivote();


        if(p != null) {
            if (ArchivoExiste(archivos, proyectname() + "_conclu.txt")) {

                edte.setText(recibirTexto());
                guardarr(recibirTexto());
            }
        }else{

            if (ArchivoExiste(archivos, proyectname() + "_conclu.txt")) {
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_conclu.txt"));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine();
                    String introduccion = "";

                    while (linea != null) {
                        introduccion = introduccion + linea + "\n";
                        linea = br.readLine();

                    }
                    br.close();
                    archivo.close();
                    edte.setText(introduccion);


                } catch (IOException e) {

                }
//            try {
//                edte.setText(recibirTexto());
//            }catch (Exception e) {}
//

            }

        }






        Button bmanual = (Button) findViewById(R.id.btmc);


        bmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                EditText texto;
                texto = (EditText) findViewById(R.id.Textoconclu);
                String tt = texto.getText().toString();

                Intent intl = new Intent(getApplicationContext(), editar_conclu_activity.class);
                intl.putExtra("nombreProyecto", proyectname());
                intl.putExtra("texto", tt);

                startActivity(intl);
                Toast.makeText(getApplicationContext(),
                        "Espera unos segundos. Cargando...", Toast.LENGTH_LONG).show();

            }
        });


    }
    String intro,cuerpo,conclu,car;
    String[] infoCaratula;
public void guardarword(){


    try
    {


        WordDocument doc = new WordDocument();

        Run run = new Run();

        // en este punto debo consultar las 3 partes intro cuerpo y conclu luego juntarlo y mandarlo alr archivo de word





        String archivos [] =fileList();



        if (ArchivoExiste(archivos, proyectname() + "_conclu.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_conclu.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while (linea != null) {
                    introduccion = introduccion + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                conclu = introduccion;


            } catch (IOException e) {

            }
        }
        if (ArchivoExisteI(archivos, proyectname() + "_Intro.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_Intro.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while (linea != null) {
                    introduccion = introduccion + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                intro = introduccion;


            } catch (IOException e) {

            }
        }

        if (ArchivoExisteCU(archivos, proyectname() + "_cuerpo.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_cuerpo.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while (linea != null) {
                    introduccion = introduccion + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                cuerpo = introduccion;


            } catch (IOException e) {

            }
        }


        if (ArchivoExisteC(archivos, proyectname() + ".txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + ".txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while (linea != null) {
                    introduccion = introduccion + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                car = introduccion;


            } catch (IOException e) {

            }
        }else{

        }



infoCaratula = car.split(",,,");




























//
//        run.addText(intro+"\n\n\n\n\n"+cuerpo+"\n\n\n\n\n"+conclu);
//
//
//
//        run.setAsciiFont("Arial");
//        run.setFontSize(12); //14 points
//
//
//        Paragraph paragraph = new Paragraph();
//        paragraph.add(run);
//
//        doc.getBody().add(paragraph);

//
//        ShapeTemplate shapeTemplate = new ShapeTemplate();
//        shapeTemplate.setCoordinateSpaceSize("21600,21600");
//        shapeTemplate.setEdgePath("m@4@5l@4@11@9@11@9@5xe");
//        shapeTemplate.setPreferRelativeResize(true);
//
//        Stroke stroke = new Stroke();
//        stroke.setJoinStyle(StrokeJoinStyle.MITER);
//
//        ShapePath path = new ShapePath();
//        path.setEnableGradient(true);
//        path.setConnectionPointType(ConnectType.FOUR);
//
//        Lock locking = new Lock();
//        locking.setAspectRatio(true);
//
//        shapeTemplate.getContent().add(stroke);
//        shapeTemplate.getContent().add(path);
//        shapeTemplate.getContent().add(locking);
//
//        ShapeStyle style = new ShapeStyle();
//        style.setPosition(Position.ABSOLUTE);
//        style.setLeftMargin("0");
//        style.setTopMargin("0");
//        style.setWidth("467.5pt");
//        style.setHeight("374pt");
//        style.setHorizontalPosition(HorizontalPosition.CENTER);
//        style.setRelativeHorizontalPosition(RelativeHorizontalPosition.MARGIN);
//        style.setVerticalPosition(VerticalPosition.CENTER);
//        style.setRelativeVerticalPosition(RelativeVerticalPosition.MARGIN);
//
//        Shape shape = new Shape();
//        shape.setStyle(style);
//        shape.setID("Shape1");
//        shape.setTypeReference("#ShapeTemplate1");
//        shape.setStroked(false);
//        shape.setAllowInCell(false);
//
//       // String aa = setImageResource(R.drawable.umg)
//        String uriImage = Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.umg).toString();
//
//
//
//
//        Image image = new Image("android.resource://" + getPackageName() +"/"+R.drawable.umg);
//        shape.getContent().add(image);
//
//        VmlObject vmlObject = new VmlObject();
//        vmlObject.getContent().add(shapeTemplate);
//        vmlObject.getContent().add(shape);
//
//        Run headerRun = new Run();
//        headerRun.add(vmlObject);
//
//        Paragraph headerParagraph = new Paragraph();
//        headerParagraph.add(headerRun);
//
//        Header header = new Header();
//        header.add(headerParagraph);
//
//        Section section = new Section();
//        section.setPageSize(new PageSize(12240, 15840)); //8.5 x 11 in
//        section.setHeader(header);
//
//        doc.getBody().setSection(section);
//






















        Run run1 = new Run();
        run1.addText("UNIVERSIDAD MARIANO GÁLVEZ DE GUATEMALA\n" +
                "CENTRO UNIVERSITARIO DE COATEPEQUE\n" +
                "FACULTAD DE INGENIERÍA EN SISTEMAS\n");
        run1.setBold(ExtendedBoolean.TRUE);

        run1.setFontSize(36); //12 points

//        run1.addCarriageReturn();
//        run1.addText("[Your Name]"+ infoCaratula[3]);
//        run1.addCarriageReturn();
//        run1.addText("[Street Address]");
//        run1.addCarriageReturn();
//        run1.addText("[City, ST ZIP Code]");
//        run1.addCarriageReturn();
//        run1.addText("September 25, 2006");
//        run1.setFontSize(24); //12 points

        Run run2 = new Run();
        run2.addText("\""+infoCaratula[1]+"\"");
        run2.setBold(ExtendedBoolean.TRUE);

        run2.setFontSize(44); //12 points

//        run2.addCarriageReturn();
//        run2.addText("[Title]");
//        run2.addCarriageReturn();
//        run2.addText("[Company Name]");
//        run2.addCarriageReturn();
//        run2.addText("[Street Address]");
//        run2.addCarriageReturn();
//        run2.addText("[City, ST ZIP Code]");
//        run2.setFontSize(24); //12 points

        Run run3 = new Run();
        run3.addText(infoCaratula[3]);
        run3.setBold(ExtendedBoolean.TRUE);
        run3.setFontSize(36); //12 points



        Run run4 = new Run();
        run4.addText(infoCaratula[5]);
        run4.setBold(ExtendedBoolean.TRUE);
        run4.setFontSize(36); //12 points

        Run run5 = new Run();
        run5.addText("INTRODUCCIÓN");
        run5.setBold(ExtendedBoolean.TRUE);
        run5.setFontSize(44); //12 points
//
        Run run6 = new Run();
        run6.addText(intro);
        run6.setFontSize(24); //12 points

        Run run7 = new Run();

        run7.addText(infoCaratula[1].toUpperCase());
        run7.setBold(ExtendedBoolean.TRUE);
        run7.setFontSize(36); //12 points\

        Run run8 = new Run();
        run8.addText(cuerpo);
        run8.setFontSize(24); //12 points

        Run run9 = new Run();
        run9.addText("CONCLUSIÓN");
        run9.setBold(ExtendedBoolean.TRUE);
        run9.setFontSize(44); //12 points
//

        Run run10 = new Run();
        run10.addText(conclu);
        run10.setFontSize(24); //12 points

        Paragraph emptyParagraph = new Paragraph();

        Paragraph paragraph1 = new Paragraph();
        paragraph1.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph1.add(run1);

//        Paragraph paragraph = new Paragraph();
//        paragraph.add(run);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph2.add(run2);

        Paragraph paragraph3 = new Paragraph();
        paragraph3.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph3.add(run3);

        Paragraph paragraph4 = new Paragraph();
        paragraph4.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph4.add(run4);
//
        Paragraph paragraph5 = new Paragraph();
        paragraph5.setPageBreakBefore(ExtendedBoolean.TRUE);
        paragraph5.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph5.add(run5);

        Paragraph paragraph6 = new Paragraph();
        paragraph6.setHorizontalTextAlignment(HorizontalAlignmentType.THAI_DISTRIBUTE);
     //d   paragraph6.setPageBreakBefore(ExtendedBoolean.TRUE);
        paragraph6.add(run6);

        Paragraph paragraph7 = new Paragraph();
        paragraph7.setPageBreakBefore(ExtendedBoolean.TRUE);
        paragraph7.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph7.add(run7);

        Paragraph paragraph8 = new Paragraph();
        paragraph8.setHorizontalTextAlignment(HorizontalAlignmentType.THAI_DISTRIBUTE);
        paragraph8.add(run8);


        Paragraph paragraph9 = new Paragraph();
        paragraph9.setPageBreakBefore(ExtendedBoolean.TRUE);
        paragraph9.setHorizontalTextAlignment(HorizontalAlignmentType.CENTER);
        paragraph9.add(run9);
        Paragraph paragraph10 = new Paragraph();
        paragraph10.setHorizontalTextAlignment(HorizontalAlignmentType.THAI_DISTRIBUTE);
        paragraph10.add(run10);


//
//        doc.getBody().add(paragraph);

        doc.getBody().add(paragraph1);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);



        doc.getBody().add(paragraph2);
        doc.getBody().add(emptyParagraph);

        doc.getBody().add(emptyParagraph);

        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(emptyParagraph);

        doc.getBody().add(emptyParagraph);
        doc.getBody().add(paragraph3);
        doc.getBody().add(paragraph4);
       

        doc.getBody().add(paragraph5);
        doc.getBody().add(emptyParagraph);
        doc.getBody().add(paragraph6);
        doc.getBody().add(paragraph7);
        doc.getBody().add(paragraph8);
        doc.getBody().add(paragraph9);
        doc.getBody().add(paragraph10);

//        String destPath = this.getExternalFilesDir(null).getAbsolutePath()+"/sgp/"+"";


        File file = new File(getExternalFilesDir(null),"/Exportaciones IngeWord/"+proyectname()+"/");
        String destPath = file.getAbsolutePath();
        if(!file.exists()){
            file.mkdirs();
        }


//        String pathRep = Environment.getExternalStorageDirectory().
//                getAbsolutePath() + "/SPG/"+"";
//        Toast.makeText(this,"despues", Toast.LENGTH_SHORT).show();
//        File folder = new File(destPath);
//        if(folder.exists()){
//            folder.mkdirs();
//            Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
//        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        doc.save(file + File.separator + proyectname()+"_"+currentDateandTime+ ".docx");
                Log.d("WordFile","Word Path: "+destPath);
        Toast.makeText(this, "Archivo guardado", Toast.LENGTH_LONG).show();


            }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }


}

    private boolean ArchivoExisteC(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+".txt"))
                return true;
        return false;


    }

    private boolean ArchivoExiste(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+"_conclu.txt"))
                return true;
        return false;


    }
    private boolean ArchivoExisteI(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+"_Intro.txt"))
                return true;
        return false;


    }
    private boolean ArchivoExisteCU(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+"_cuerpo.txt"))
                return true;
        return false;


    }


    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_conclu.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
        Toast.makeText(this,"Conclusión Guardada", Toast.LENGTH_SHORT).show();

    }

    public void guarda(){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_conclu.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
//        Toast.makeText(this,"Intoducción Guardada", Toast.LENGTH_SHORT).show();






    }

    public void guardarr(String cad){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_conclu.txt", Activity.MODE_PRIVATE));
            archivo.write(cad);
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
//        Toast.makeText(this,"Intoducción Guardada", Toast.LENGTH_SHORT).show();






    }




    public void introbb(View view){

        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);
        guarda();
        intl.putExtra("nombreProyecto", proyectname());
        startActivity(intl);


    }
    public void cuerpobb (View view){

        Intent intl = new Intent(getApplicationContext(), cuerpo_activity.class);
        intl.putExtra("nombreProyecto", proyectname());
        guarda();
        startActivity(intl);


    }
    public void conclubb (View view){

        Intent intl = new Intent(getApplicationContext(), conclusion_activity.class);
        intl.putExtra("nombreProyecto", proyectname());
        guarda();
        startActivity(intl);


    }



    public void word (View view){

        guardarword();


    }
    public void guardar_referencia (View view){

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_ref_conclusion.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

            Toast.makeText(this,"Se guardó el punto de referencia", Toast.LENGTH_SHORT).show();



        }catch (IOException e){

        }

}

}