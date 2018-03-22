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
  
  //�Ы�Excel�u�@�� 
  WritableSheet ws = wwb.createSheet("�q�T��", 0);//�Ы�sheet

  try {
   ws.mergeCells(0, 0, 2, 1);//�X�ֳ椸��]���C�A����A�k�C�A�k��^�q�Ĥ@��Ĥ@�C��ĤG��ĤT�C
   Label header = new Label(0, 0, "�q�T��(191026�Z)", getHeader()); 
   ws.addCell(header);//�g�J�Y
   Label l = new Label(0, 2, "�m�W", getTitle());//��3��
   ws.addCell(l);
   l = new Label(1, 2, "�q��", getTitle());
   ws.addCell(l);
   l = new Label(2, 2, "�a�}", getTitle());
   ws.addCell(l);
   l = new Label(0, 3, "�p��", getNormolCell());//��4��
   ws.addCell(l);
   l = new Label(1, 3, "1314***0974", getNormolCell());
   ws.addCell(l);
   l = new Label(2, 3, "�Z�~�Z��", getNormolCell());
   ws.addCell(l);
   l = new Label(0, 4, "�p�I", getNormolCell());//��5��
   ws.addCell(l);
   l = new Label(1, 4, "1347***5057", getNormolCell());
   ws.addCell(l);
   l = new Label(2, 4, "�Z�~�Z��", getNormolCell());
   ws.addCell(l);
   ws.setColumnView(0,20);//�]�m�C�e
   ws.setColumnView(1,20);
   ws.setColumnView(2,40);
   ws.setRowView(0,400);//�]�m�氪
   ws.setRowView(1,400);
   ws.setRowView(2,500);
   ws.setRowView(3,500);
   ws.setRowView(4,500);
  } catch (RowsExceededException e1) {
   e1.printStackTrace();
  } catch (WriteException e1) {
   e1.printStackTrace();
  }
  
  //��X�y
  try {
   wwb.write();
  } catch (IOException ex) {
   // TODO �۰ʥͦ� catch 
   ex.printStackTrace();
  }
  //�����y
  try {
   wwb.close();
  } catch (WriteException ex) {
   // TODO �۰ʥͦ� catch
   ex.printStackTrace();
  } catch (IOException ex) {
   // TODO �۰ʥͦ� catch 
   ex.printStackTrace();
  }
  //outStream.close();
  System.out.println("�g�J���\�I\n");
 }
 
 public void readExc(File filename) throws BiffException, IOException{

    Workbook wb = Workbook.getWorkbook(filename);
    Sheet s = wb.getSheet(0);//��1��sheet
    Cell c = null;
    int row = s.getRows();//�`���
    int col = s.getColumns();//�`�C��
    for(int i=0;i<row;i++){
     for(int j=0;j<col;j++){
      c = s.getCell(j,i);
      System.out.print(c.getContents()+"  ");
     }
     System.out.println();
    }   
 }
 
 /**
  * �]�m�Y���˦�
  * @return 
  */
 public static WritableCellFormat getHeader(){
  WritableFont font = new  WritableFont(WritableFont.TIMES, 24 ,WritableFont.BOLD);//�q�r��
  try {
   font.setColour(Colour.BLUE);//�Ŧ�r��
  } catch (WriteException e1) {
   // TODO 
   e1.printStackTrace();
  }
  WritableCellFormat format = new  WritableCellFormat(font);
  try {
   format.setAlignment(jxl.format.Alignment.CENTRE);//���k�~��
   format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//�W�U�~��
   format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);//�¦����
   format.setBackground(Colour.YELLOW);//����I��
  } catch (WriteException e) {
   // TODO 
   e.printStackTrace();
  }
  return format;
 }
 
 /**
  * �]�m���D�˦�
  * @return
  */
 public static WritableCellFormat getTitle(){
  WritableFont font = new  WritableFont(WritableFont.TIMES, 14);
  try {
   font.setColour(Colour.BLUE);//�Ŧ�r��
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
  * �]�m��L�椸��˦�
  * @return
  */
 public static WritableCellFormat getNormolCell(){//12���r��A�W�U���k�~���A�a�¦����
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

