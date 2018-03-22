package com.albert.jexcel;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * CopyRight (C) www.blogjava.net/ilovezmh  All rights reserved.<p>
 * 
 * WuHan Inpoint Information Technology Development,Inc.<p>
 * 
 * Author zhu<p>
 *
 * @version 1.0    2007-2-6
 *
 * <p>Base on : JDK1.5<p>
 *
 */

public class JexcelSample {
 
 /**
  * excel
  *
  */
 public void writeExc(File filename){
  WritableWorkbook wwb = null;
  try 
  { 
   wwb = Workbook.createWorkbook(filename);   
  }
  catch (Exception e){ 
   e.printStackTrace(); 
  } 
  
  //創建Excel工作表 
  WritableSheet ws = wwb.createSheet("通訊錄", 0);//創建sheet

  try {
   ws.mergeCells(0, 0, 2, 1);//合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
   Label header = new Label(0, 0, "通訊錄(191026班)", getHeader()); 
   ws.addCell(header);//寫入頭
   Label l = new Label(0, 2, "姓名", getTitle());//第3行
   ws.addCell(l);
   l = new Label(1, 2, "電話", getTitle());
   ws.addCell(l);
   l = new Label(2, 2, "地址", getTitle());
   ws.addCell(l);
   l = new Label(0, 3, "小祝", getNormolCell());//第4行
   ws.addCell(l);
   l = new Label(1, 3, "1314***0974", getNormolCell());
   ws.addCell(l);
   l = new Label(2, 3, "武漢武昌", getNormolCell());
   ws.addCell(l);
   l = new Label(0, 4, "小施", getNormolCell());//第5行
   ws.addCell(l);
   l = new Label(1, 4, "1347***5057", getNormolCell());
   ws.addCell(l);
   l = new Label(2, 4, "武漢武昌", getNormolCell());
   ws.addCell(l);
   ws.setColumnView(0,20);//設置列寬
   ws.setColumnView(1,20);
   ws.setColumnView(2,40);
   ws.setRowView(0,400);//設置行高
   ws.setRowView(1,400);
   ws.setRowView(2,500);
   ws.setRowView(3,500);
   ws.setRowView(4,500);
  } catch (RowsExceededException e1) {
   e1.printStackTrace();
  } catch (WriteException e1) {
   e1.printStackTrace();
  }
  
  //輸出流
  try {
   wwb.write();
  } catch (IOException ex) {
   // TODO 自動生成 catch 
   ex.printStackTrace();
  }
  //關閉流
  try {
   wwb.close();
  } catch (WriteException ex) {
   // TODO 自動生成 catch
   ex.printStackTrace();
  } catch (IOException ex) {
   // TODO 自動生成 catch 
   ex.printStackTrace();
  }
  //outStream.close();
  System.out.println("寫入成功！\n");
 }
 
 public void readExc(File filename) throws BiffException, IOException{

    Workbook wb = Workbook.getWorkbook(filename);
    Sheet s = wb.getSheet(0);//第1個sheet
    Cell c = null;
    int row = s.getRows();//總行數
    int col = s.getColumns();//總列數
    for(int i=0;i<row;i++){
     for(int j=0;j<col;j++){
      c = s.getCell(j,i);
      System.out.print(c.getContents()+"  ");
     }
     System.out.println();
    }   
 }
 
 /**
  * 設置頭的樣式
  * @return 
  */
 public static WritableCellFormat getHeader(){
  WritableFont font = new  WritableFont(WritableFont.TIMES, 24 ,WritableFont.BOLD);//義字體
  try {
   font.setColour(Colour.BLUE);//藍色字體
  } catch (WriteException e1) {
   // TODO 
   e1.printStackTrace();
  }
  WritableCellFormat format = new  WritableCellFormat(font);
  try {
   format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
   format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
   format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);//黑色邊框
   format.setBackground(Colour.YELLOW);//黃色背景
  } catch (WriteException e) {
   // TODO 
   e.printStackTrace();
  }
  return format;
 }
 
 /**
  * 設置標題樣式
  * @return
  */
 public static WritableCellFormat getTitle(){
  WritableFont font = new  WritableFont(WritableFont.TIMES, 14);
  try {
   font.setColour(Colour.BLUE);//藍色字體
  } catch (WriteException e1) {
   // TODO 
   e1.printStackTrace();
  }
  WritableCellFormat format = new  WritableCellFormat(font);
  
  try {
   format.setAlignment(jxl.format.Alignment.CENTRE);
   format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
   format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
  } catch (WriteException e) {
   // TODO 
   e.printStackTrace();
  }
  return format;
 }
 
 /**
  * 設置其他單元格樣式
  * @return
  */
 public static WritableCellFormat getNormolCell(){//12號字體，上下左右居中，帶黑色邊框
  WritableFont font = new  WritableFont(WritableFont.TIMES, 12);
  WritableCellFormat format = new  WritableCellFormat(font);
  try {
   format.setAlignment(jxl.format.Alignment.CENTRE);
   format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
   format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
  } catch (WriteException e) {
   // TODO
   e.printStackTrace();
  }
  return format;
 }
 
 public static void main(String[] args) throws IOException, BiffException{
  JexcelSample js = new JexcelSample();
  File f = new File("address.xls");
  f.delete();
  f.createNewFile();
  js.writeExc(f);
  js.readExc(f);
 }

}

