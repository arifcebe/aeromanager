package com.gac.aeromanager.archive.activity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.atlet.model.AtletModel;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.round.handler.RoundViewSqlHandler;
import com.gac.aeromanager.round.model.RoundModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by arifcebe on 23/05/15.
 */
public class ArchiveFragment extends Fragment {
    private AtletTableSqlHandler atletTblSql;
    private Button btnExportToExcel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.archive_activity, null);
        btnExportToExcel = (Button) view.findViewById(R.id.btn_archive_delete_all);
        atletTblSql = new AtletTableSqlHandler(getActivity());

        btnExportToExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exportToExcel();
                dialogExport();
            }
        });

        return view;
    }

    private void dialogExport(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.archive_form);
        dialog.setCancelable(true);
        dialog.show();

        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        final EditText editText = (EditText) dialog.findViewById(R.id.ed_namaFile);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filterNamaFile = editText.getText().toString().replace(" ", "_");
                String fileName = filterNamaFile+".xls";

                //Saving file in external storage
                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File(sdCard.getAbsolutePath() + "/aeromanager");

                //create directory if not exist
                if (!directory.isDirectory()) {
                    directory.mkdirs();
                }

                //file path
                File file = new File(directory, fileName);

                if (editText.getText().toString().isEmpty()){
                    editText.setError("nama file export tidak boleh kosong kakak ^_^");
                }else{
                    if(file.isFile()){
                        editText.setError("file dengan nama"+editText.getText().toString()+" " +
                                "sudah ada kakak, silahkan ganti nama yang lain ^_^");
                    }else{
                        exportToExcel(editText.getText().toString());
                        dialog.dismiss();
                    }

                }


            }
        });
    }

    /**
     * method untuk export ke excel
     */
    private void exportToExcel(String namaFile) {
        String filterNamaFile = namaFile.replace(" ","_");
        String fileName = filterNamaFile+".xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/aeromanager");

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            // worksheet atlet putri
            WritableSheet sheet = workbook.createSheet("atlet putri", 0);
            WritableSheet sheetPutra = workbook.createSheet("atlet putra",1);

            WritableSheet sheetHasilPutri = workbook.createSheet("hasil putri",2);
            WritableSheet sheetHasilPutra = workbook.createSheet("hasil putra",3);

            try {

                // add data to sheet putri
                sheet.addCell(new Label(1, 0, "no dada")); // column and row
                sheet.addCell(new Label(0, 0, "nama"));
                sheet.addCell(new Label(2, 0, "jk"));
                sheet.addCell(new Label(3, 0, "team"));
                sheet.addCell(new Label(4, 0, "status"));

                ArrayList<AtletModel> arrayAtlet = atletTblSql.getAllAtlet("Pi");
                for (int i = 0; i < arrayAtlet.size(); i++) {
                    String noDada = arrayAtlet.get(i).getNo_dada();
                    String nama = arrayAtlet.get(i).getNama();
                    String jk = arrayAtlet.get(i).getJenKel();
                    String team = arrayAtlet.get(i).getTeam_nama();
                    String status = arrayAtlet.get(i).getTeam_status();

                    sheet.addCell(new Label(1,i+1,noDada));
                    sheet.addCell(new Label(0,i+1,nama));
                    sheet.addCell(new Label(2,i+1,jk));
                    sheet.addCell(new Label(3,i+1,team));
                    sheet.addCell(new Label(4,i+1,status));
                    //Config.TRACE(TAG, "show data " + arrayAtlet.get(i));
                }

                //add data to sheet putra
                sheetPutra.addCell(new Label(1, 0, "no dada")); // column and row
                sheetPutra.addCell(new Label(0, 0, "nama"));
                sheetPutra.addCell(new Label(2, 0, "jk"));
                sheetPutra.addCell(new Label(3, 0, "team"));
                sheetPutra.addCell(new Label(4, 0, "status"));

                ArrayList<AtletModel> arrayAtletPutra = atletTblSql.getAllAtlet("Pa");
                for (int i = 0; i < arrayAtletPutra.size(); i++) {
                    String noDada = arrayAtletPutra.get(i).getNo_dada();
                    String nama = arrayAtletPutra.get(i).getNama();
                    String jk = arrayAtletPutra.get(i).getJenKel();
                    String team = arrayAtletPutra.get(i).getTeam_nama();
                    String status = arrayAtletPutra.get(i).getTeam_status();

                    sheetPutra.addCell(new Label(1,i+1,noDada));
                    sheetPutra.addCell(new Label(0, i + 1, nama));
                    sheetPutra.addCell(new Label(2, i + 1, jk));
                    sheetPutra.addCell(new Label(3, i + 1, team));
                    sheetPutra.addCell(new Label(4, i + 1, status));
                    //Config.TRACE(TAG, "show data " + arrayAtlet.get(i));
                }

                //HASIL AKHIR PUTRA
                sheetHasilPutra.addCell(new Label(1, 0 ,"No Dada"));
                sheetHasilPutra.addCell(new Label(0, 0 ,"Nama"));
                sheetHasilPutra.addCell(new Label(2, 0 ,"Team"));
                sheetHasilPutra.addCell(new Label(3, 0 ,"Score"));
                sheetHasilPutra.addCell(new Label(4, 0 ,"Status"));

                HasilAkhirViewSqlHandler hasilAkhirViewSqlHandler = new HasilAkhirViewSqlHandler(getActivity());
                ArrayList<HasilAkhirModel> listHasilAkhirPutra = hasilAkhirViewSqlHandler.getAtletOnHasilAkhir("Pa");
                for (int i = 0; i < listHasilAkhirPutra.size();i++){
                    int row = i + 1;
                    HasilAkhirModel hasilAkhirModel = listHasilAkhirPutra.get(i);

                    sheetHasilPutra.addCell(new Label(1, row , hasilAkhirModel.getNodada()));
                    sheetHasilPutra.addCell(new Label(0, row , hasilAkhirModel.getNama()));
                    sheetHasilPutra.addCell(new Label(2, row , hasilAkhirModel.getTeam_nama()));
                    sheetHasilPutra.addCell(new Label(3, row , String.valueOf(hasilAkhirModel.getScore_total())));
                    sheetHasilPutra.addCell(new Label(4, row , hasilAkhirModel.getTeam_status()));

                }

                //HASIL AKHIR PUTRI
                sheetHasilPutri.addCell(new Label(1, 0 ,"No Dada"));
                sheetHasilPutri.addCell(new Label(0, 0 ,"Nama"));
                sheetHasilPutri.addCell(new Label(2, 0 ,"Team"));
                sheetHasilPutri.addCell(new Label(3, 0 ,"Score"));
                sheetHasilPutri.addCell(new Label(4, 0 ,"Status"));

                ArrayList<HasilAkhirModel> listHasilAkhirPutri = hasilAkhirViewSqlHandler.getAtletOnHasilAkhir("Pi");
                for (int i = 0; i < listHasilAkhirPutri.size();i++){
                    int row = i + 1;
                    HasilAkhirModel hasilAkhirModel = listHasilAkhirPutri.get(i);

                    sheetHasilPutri.addCell(new Label(1, row , hasilAkhirModel.getNodada()));
                    sheetHasilPutri.addCell(new Label(0, row , hasilAkhirModel.getNama()));
                    sheetHasilPutri.addCell(new Label(2, row , hasilAkhirModel.getTeam_nama()));
                    sheetHasilPutri.addCell(new Label(3, row , String.valueOf(hasilAkhirModel.getScore_total())));
                    sheetHasilPutri.addCell(new Label(4, row , hasilAkhirModel.getTeam_status()));


                }

                // DEKLARASI TABLE SQL ROUND PUTRA/PUTRI
                RoundTableSqlHandler roundTableSqlHandler = new RoundTableSqlHandler(getActivity());

                WritableSheet sheetRoundPutri = null;
                WritableSheet sheetRoundPutra = null;

                int countSheet = 4;
                int roundPutri = 1;

                for(int i = 1; i < 8; i++){
                    //LIST ROUND PUTRI
                    ArrayList<RoundModel>  listRoundPutri = roundTableSqlHandler.getAllAtletInRound("Pi",String.valueOf(roundPutri));
                    roundPutri = roundPutri + 1;
                    sheetRoundPutri = workbook.createSheet("round putri "+i,countSheet);
                    countSheet = i+countSheet;
                    sheetRoundPutri.addCell(new Label(0,0,"Nama"));
                    sheetRoundPutri.addCell(new Label(1,0,"No Dada"));
                    sheetRoundPutri.addCell(new Label(2, 0, "Team"));
                    sheetRoundPutri.addCell(new Label(3, 0, "Score"));
                    sheetRoundPutri.addCell(new Label(4, 0,"Status"));

                    for (int j = 0;j < listRoundPutri.size(); j++){
                        int row = j + 1;
                        RoundModel model = listRoundPutri.get(j);
                        sheetRoundPutri.addCell(new Label(0, row, model.getAtletNama()));
                        sheetRoundPutri.addCell(new Label(1, row, model.getNoDada()));
                        sheetRoundPutri.addCell(new Label(2, row, model.getTeamNama()));
                        sheetRoundPutri.addCell(new Label(3, row, String.valueOf(model.getScore())));
                        sheetRoundPutri.addCell(new Label(4, row, model.getTeamStatus()));
                        Log.d("export atlet","nama atlet "+model.getAtletNama());
                    }
                    Log.d("export atlet","sheet atlet putri ke "+countSheet);
                }

                int roundPutra = 1;
                for(int j = 1; j < 8; j++){
                    sheetRoundPutra = workbook.createSheet("round putra "+j,countSheet);
                    countSheet = j + countSheet;


                    //LIST ROUND PUTRA
                    ArrayList<RoundModel>  listRoundPutra = roundTableSqlHandler.getAllAtletInRound("Pa",String.valueOf(roundPutra));
                    roundPutra = roundPutra + 1;

                    Log.d("export atlet","round ke "+roundPutra);

                    sheetRoundPutra.addCell(new Label(0,0,"Nama"));
                    sheetRoundPutra.addCell(new Label(1,0,"No Dada"));
                    sheetRoundPutra.addCell(new Label(2, 0, "Team"));
                    sheetRoundPutra.addCell(new Label(3, 0, "Score"));
                    sheetRoundPutra.addCell(new Label(4, 0 ,"Status"));

                    for (int i = 0;i < listRoundPutra.size(); i++){
                        int row = i + 1;
                        RoundModel model = listRoundPutra.get(i);
                        sheetRoundPutra.addCell(new Label(0, row, model.getAtletNama()));
                        sheetRoundPutra.addCell(new Label(1, row, model.getNoDada()));
                        sheetRoundPutra.addCell(new Label(2, row, model.getTeamNama()));
                        sheetRoundPutra.addCell(new Label(3, row, String.valueOf(model.getScore())));
                        sheetRoundPutra.addCell(new Label(4, row, model.getTeamStatus()));
                        Log.d("export atlet","nama atlet "+model.getAtletNama());
                    }
                    Log.d("export atlet","sheet atlet putra ke "+countSheet);
                }

                // delete all atlet
                AtletTableSqlHandler atletTableSqlHandler = new AtletTableSqlHandler(getActivity());
                atletTableSqlHandler.deleteAllAtlet();

                Toast.makeText(getActivity(),"Export file berhasil",Toast.LENGTH_LONG).show();

            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
