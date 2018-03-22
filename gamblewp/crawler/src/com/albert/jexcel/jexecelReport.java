package com.albert.jexcel;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import com.albert.dto.SoccerCombine;
import com.albert.dto.SoccerDTO;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class jexecelReport {
	
	String execelFile;
	Vector<SoccerCombine> sc;
	
	static WritableCellFormat formatHeader0_GRAY_25;
	static WritableCellFormat formatHeader0_YELLOW;
	static WritableCellFormat formatHeader0_LIGHT_GREEN;
	static WritableCellFormat formatHeader1;
	static WritableCellFormat formatTitle0_GRAY_25;
	static WritableCellFormat formatTitle0_YELLOW;
	static WritableCellFormat formatNormolCell;
	static WritableCellFormat formatNormolCell0;
	static WritableCellFormat formatNormolCell1;
	
	
	

	public jexecelReport(String file) {
		execelFile = file;
	}

	public Vector<SoccerCombine> getSc() {
		return sc;
	}

	public void setSc(Vector<SoccerCombine> sc) {
		this.sc = sc;
	}

	public void create(Vector v) {
		try {
			this.sc = v;

			File f = new File(execelFile);
			f.delete();
			f.createNewFile();

			writeExc(f);
			//readExc(f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * excel
	 * 
	 */
	public void writeExc(File filename) {
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 創建Excel工作表
		WritableSheet ws0 = wwb.createSheet("FB", 0);// 創建sheet

		try {
			if(formatHeader0_GRAY_25==null)
				formatHeader0_GRAY_25=getHeader0(Colour.GRAY_25);
			if(formatHeader1==null)
				formatHeader1=getHeader1();
			if(formatHeader0_YELLOW==null)
				formatHeader0_YELLOW=getHeader0(Colour.YELLOW);
			if(formatHeader0_LIGHT_GREEN==null)
				formatHeader0_LIGHT_GREEN=getHeader0(Colour.LIGHT_GREEN);
			if(formatTitle0_GRAY_25==null)
				formatTitle0_GRAY_25=getTitle0(Colour.GRAY_25);
			if(formatTitle0_YELLOW==null)
				formatTitle0_YELLOW=getTitle0(Colour.YELLOW);
			if(formatNormolCell==null)
				formatNormolCell=getNormolCell();
			if(formatNormolCell0==null)
				formatNormolCell0=getNormolCell1(0);
			if(formatNormolCell1==null)
				formatNormolCell1=getNormolCell1(1);

			// formate FB headers
			ws0.mergeCells(0, 0, 4, 0);// 合併單元格 行,列（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header0 = new Label(0, 0, "TSLC", formatHeader0_GRAY_25);
			ws0.addCell(header0);// 寫入頭

			ws0.mergeCells(5, 0, 5, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header1 = new Label(5, 0, "POOL", formatHeader1);
			ws0.addCell(header1);// 寫入頭

			ws0.mergeCells(6, 0, 9, 0);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header2 = new Label(6, 0, "TSLC", formatHeader0_YELLOW);
			ws0.addCell(header2);// 寫入頭

			ws0.mergeCells(10, 0, 13, 0);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header3 = new Label(10, 0, "PINNACLE",
					formatHeader0_LIGHT_GREEN);
			ws0.addCell(header3);// 寫入頭

			Label l0 = new Label(0, 1, "Match #", formatTitle0_GRAY_25);// 第3行
			ws0.addCell(l0);

			Label l1 = new Label(1, 1, "League", formatTitle0_GRAY_25);// 第3行
			ws0.addCell(l1);
			ws0.setColumnView(1, 20);

			Label l2 = new Label(2, 1, "Game Time", formatTitle0_GRAY_25);// 第3行
			ws0.addCell(l2);
			ws0.setColumnView(2, 20);

			Label l3 = new Label(3, 1, "(Home)", formatTitle0_GRAY_25);// 第3行
			ws0.addCell(l3);
			ws0.setColumnView(3, 20);
			
			Label l4 = new Label(4, 1, "(Away)", formatTitle0_GRAY_25);// 第3行
			ws0.addCell(l4);
			ws0.setColumnView(4, 20);

			Label l5 = new Label(6, 1, "Line(H)", formatTitle0_YELLOW);// 第3行
			ws0.addCell(l5);

			Label l6 = new Label(7, 1, "HOME", formatTitle0_YELLOW);// 第3行
			ws0.addCell(l6);

			Label l7 = new Label(8, 1, "AWAY", formatTitle0_YELLOW);// 第3行
			ws0.addCell(l7);

			Label l9 = new Label(9, 1, "DRAW", formatTitle0_YELLOW);// 第3行
			ws0.addCell(l9);

			//Label l10 = new Label(10, 1, "%", formatTitle0_YELLOW);// 第3行
			//ws0.addCell(l10);

			//ws0.mergeCells(11, 1, 12, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header4 = new Label(10, 1, "Line(H)",
					formatHeader0_LIGHT_GREEN);
			ws0.addCell(header4);// 寫入頭

			//ws0.mergeCells(13, 1, 14, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header5 = new Label(11, 1, "HOME",
					formatHeader0_LIGHT_GREEN);
			ws0.addCell(header5);// 寫入頭

			//ws0.mergeCells(15, 1, 16, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header6 = new Label(12, 1, "AWAY",
					formatHeader0_LIGHT_GREEN);
			ws0.addCell(header6);// 寫入頭

			//ws0.mergeCells(17, 1, 18, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			Label header7 = new Label(13, 1, "DRAW",
					formatHeader0_LIGHT_GREEN);
			ws0.addCell(header7);// 寫入頭

			// ws0.mergeCells(19, 1, 20, 1);// 合併單元格（左列，左行，右列，右行）從第一行第一列到第二行第三列
			//Label header8 = new Label(19, 1, "%",
			//		formatHeader0_LIGHT_GREEN);
			//ws0.addCell(header8);// 寫入頭
			
			int i=0;
			Enumeration<SoccerCombine> eSC = sc.elements();
			while (eSC.hasMoreElements()) {
				SoccerCombine dtoTemp = (SoccerCombine) eSC.nextElement();
				
				String match="";
				String league="";
				String gameTime="";
				String home="";
				String away="";
				double moneyLineVisitA=0;
				double moneyLineHomeA=0;
				double moneyLineDrawA=0;
				double totalPointA=0;
				double overAdjustA=0;
				double underAdjustA=0;
				double moneyLineVisitB=0;
				double moneyLineHomeB=0;
				double moneyLineDrawB=0;
				double totalPointB=0;
				double overAdjustB=0;
				double underAdjustB=0;
				
				if(dtoTemp.getA()!=null)
				{
					match=((SoccerDTO)dtoTemp.getA()).getGameNumber();
					if(dtoTemp.getB()!=null)
						league=((SoccerDTO)dtoTemp.getB()).getLeague();
					gameTime=((SoccerDTO)dtoTemp.getA()).getEventDatetime();
					home=((SoccerDTO)dtoTemp.getA()).getHomeName();
					away=((SoccerDTO)dtoTemp.getA()).getVisitName();
					moneyLineVisitA=((SoccerDTO)dtoTemp.getA()).getMoneyLineVisit();
					moneyLineHomeA=((SoccerDTO)dtoTemp.getA()).getMoneyLineHome();
					moneyLineDrawA=((SoccerDTO)dtoTemp.getA()).getMoneyLineDraw();
					totalPointA=((SoccerDTO)dtoTemp.getA()).getTotalPoint();
					overAdjustA=((SoccerDTO)dtoTemp.getA()).getOverAdjust();
					underAdjustA=((SoccerDTO)dtoTemp.getA()).getUnderAdjust();
				}
				
				if(dtoTemp.getB()!=null)
				{
					if(dtoTemp.getA()==null)
					{
						match=((SoccerDTO)dtoTemp.getB()).getGameNumber();
						league=((SoccerDTO)dtoTemp.getB()).getLeague();
						gameTime=((SoccerDTO)dtoTemp.getB()).getEventDatetime();
						home=((SoccerDTO)dtoTemp.getB()).getHomeName();
						away=((SoccerDTO)dtoTemp.getB()).getVisitName();
					}

					moneyLineVisitB=((SoccerDTO)dtoTemp.getB()).getMoneyLineVisit();
					moneyLineHomeB=((SoccerDTO)dtoTemp.getB()).getMoneyLineHome();
					moneyLineDrawB=((SoccerDTO)dtoTemp.getB()).getMoneyLineDraw();
					totalPointB=((SoccerDTO)dtoTemp.getB()).getTotalPoint();
					overAdjustB=((SoccerDTO)dtoTemp.getB()).getOverAdjust();
					underAdjustB=((SoccerDTO)dtoTemp.getB()).getUnderAdjust();
				}
				
				ws0.mergeCells(0, 2 * i + 2, 0, 2 * i + 3);
				Label L0 = new Label(0, 2 * i + 2, match, formatNormolCell);// 第3行
				ws0.addCell(L0);

				ws0.mergeCells(1, 2 * i + 2, 1, 2 * i + 3);
				Label L1 = new Label(1, 2 * i + 2, league, formatNormolCell);// 第3行
				ws0.addCell(L1);

				ws0.mergeCells(2, 2 * i + 2, 2, 2 * i + 3);
				Label L2 = new Label(2, 2 * i + 2, gameTime, formatNormolCell);// 第3行
				ws0.addCell(L2);

				ws0.mergeCells(3, 2 * i + 2, 3, 2 * i + 3);
				Label L3 = new Label(3, 2 * i + 2, home, formatNormolCell);// 第3行
				ws0.addCell(L3);

				ws0.mergeCells(4, 2 * i + 2, 4, 2 * i + 3);
				Label L4 = new Label(4, 2 * i + 2, away, formatNormolCell);// 第3行
				ws0.addCell(L4);

				{
					Label L5 = new Label(5, 2 * i + 2, "HAD", formatNormolCell);// 第3行
					ws0.addCell(L5);

					Label L6 = new Label(5, 2 * i + 3, "HILO", formatNormolCell);// 第3行
					ws0.addCell(L6);
				}

				{// Line(H) @TSLC
					Label L7 = new Label(6, 2 * i + 2, "", formatNormolCell);// 第3行
					ws0.addCell(L7);

					// Label L8 = new Label(6, 2*i+3, "2.5",
					// getNormolCell1());// 第3行

					jxl.write.Number number8 = new jxl.write.Number(6,
							2 * i + 3, totalPointA, formatNormolCell0);
					ws0.addCell(number8);
				}

				{// HOME @TSLC
					jxl.write.Number number9 = new jxl.write.Number(7,
							2 * i + 2, moneyLineHomeA, formatNormolCell0);
					ws0.addCell(number9);

					jxl.write.Number number10 = new jxl.write.Number(7,
							2 * i + 3, overAdjustA, formatNormolCell0);
					ws0.addCell(number10);
				}

				{// AWAY @TSLC
					jxl.write.Number number11 = new jxl.write.Number(8,
							2 * i + 2, moneyLineVisitA, formatNormolCell0);
					ws0.addCell(number11);

					jxl.write.Number number12 = new jxl.write.Number(8,
							2 * i + 3, underAdjustA, formatNormolCell0);
					ws0.addCell(number12);
				}

				{// DRAW @TSLC
					jxl.write.Number number13 = new jxl.write.Number(9,
							2 * i + 2, moneyLineDrawA, formatNormolCell0);
					ws0.addCell(number13);

					Label L14 = new Label(9, 2 * i + 3, "", formatNormolCell);// 第3行
					ws0.addCell(L14);
				}

				/*{// % @TSLC
					jxl.write.Number number15 = new jxl.write.Number(10,
							2 * i + 2, 0, formatNormolCell1);
					ws0.addCell(number15);

					jxl.write.Number number16 = new jxl.write.Number(10,
							2 * i + 3, 0, formatNormolCell1);
					ws0.addCell(number16);
				}*/

				{// Line(H):1 @PINNACLE
					Label L17 = new Label(10, 2 * i + 2, "", formatNormolCell);// 第3行
					ws0.addCell(L17);

					jxl.write.Number number18 = new jxl.write.Number(10,
							2 * i + 3, totalPointB, formatNormolCell0);
					ws0.addCell(number18);
				}
				/*
				{// Line(H):2 @PINNACLE
					Label L19 = new Label(12, 2 * i + 2, "", formatNormolCell);// 第3行
					ws0.addCell(L19);

					jxl.write.Number number20 = new jxl.write.Number(12,
							2 * i + 3, totalPointB, formatNormolCell0);
					ws0.addCell(number20);
				}*/

				{// HOME:1 @PINNACLE
					jxl.write.Number number21 = new jxl.write.Number(11,
							2 * i + 2, moneyLineHomeB, formatNormolCell0);
					ws0.addCell(number21);

					jxl.write.Number number22 = new jxl.write.Number(11,
							2 * i + 3, overAdjustB, formatNormolCell0);
					ws0.addCell(number22);
				}
				/*
				{// HOME:2 @PINNACLE
					jxl.write.Number number23 = new jxl.write.Number(14,
							2 * i + 2, moneyLineHomeB, formatNormolCell0);
					ws0.addCell(number23);

					jxl.write.Number number24 = new jxl.write.Number(14,
							2 * i + 3, overAdjustB, formatNormolCell0);
					ws0.addCell(number24);
				}*/

				{// AWAY:1 @PINNACLE
					jxl.write.Number number25 = new jxl.write.Number(12,
							2 * i + 2, moneyLineVisitB, formatNormolCell0);
					ws0.addCell(number25);

					jxl.write.Number number26 = new jxl.write.Number(12,
							2 * i + 3, underAdjustB, formatNormolCell0);
					ws0.addCell(number26);
				}
				/*
				{// AWAY:2 @PINNACLE
					jxl.write.Number number27 = new jxl.write.Number(16,
							2 * i + 2, moneyLineVisitB, formatNormolCell0);
					ws0.addCell(number27);

					jxl.write.Number number28 = new jxl.write.Number(16,
							2 * i + 3, underAdjustB, formatNormolCell0);
					ws0.addCell(number28);
				}*/

				{// DRAW:1 @PINNACLE
					jxl.write.Number number29 = new jxl.write.Number(13,
							2 * i + 2, moneyLineDrawB, formatNormolCell0);
					ws0.addCell(number29);

					Label L30 = new Label(13, 2 * i + 3, "", formatNormolCell);// 第3行
					ws0.addCell(L30);
				}
				/*
				{// DRAW:2 @PINNACLE
					jxl.write.Number number31 = new jxl.write.Number(18,
							2 * i + 2, moneyLineDrawB, formatNormolCell0);
					ws0.addCell(number31);

					Label L32 = new Label(18, 2 * i + 3, "", formatNormolCell);// 第3行
					ws0.addCell(L32);
				}
				*/
				/*
				{// %
					jxl.write.Number number33 = new jxl.write.Number(19,
							2 * i + 2, 0, formatNormolCell1);
					ws0.addCell(number33);

					jxl.write.Number number34 = new jxl.write.Number(19,
							2 * i + 3, 0, formatNormolCell1);
					ws0.addCell(number34);
				}*/
				
				i++;
				
			}
/*
			for (int i = 0; i < 10; i++) {
				ws0.mergeCells(0, 2 * i + 2, 0, 2 * i + 3);
				Label L0 = new Label(0, 2 * i + 2, "Match #", getNormolCell());// 第3行
				ws0.addCell(L0);

				ws0.mergeCells(1, 2 * i + 2, 1, 2 * i + 3);
				Label L1 = new Label(1, 2 * i + 2, "League", getNormolCell());// 第3行
				ws0.addCell(L1);

				ws0.mergeCells(2, 2 * i + 2, 2, 2 * i + 3);
				Label L2 = new Label(2, 2 * i + 2, "Game Time", getNormolCell());// 第3行
				ws0.addCell(L2);

				ws0.mergeCells(3, 2 * i + 2, 3, 2 * i + 3);
				Label L3 = new Label(3, 2 * i + 2, "(Home)", getNormolCell());// 第3行
				ws0.addCell(L3);

				ws0.mergeCells(4, 2 * i + 2, 4, 2 * i + 3);
				Label L4 = new Label(4, 2 * i + 2, "(Away)", getNormolCell());// 第3行
				ws0.addCell(L4);

				{
					Label L5 = new Label(5, 2 * i + 2, "HAD", getNormolCell());// 第3行
					ws0.addCell(L5);

					Label L6 = new Label(5, 2 * i + 3, "HILO", getNormolCell());// 第3行
					ws0.addCell(L6);
				}

				{// Line(H)
					Label L7 = new Label(6, 2 * i + 2, "", getNormolCell());// 第3行
					ws0.addCell(L7);

					// Label L8 = new Label(6, 2*i+3, "2.5",
					// getNormolCell1());// 第3行

					jxl.write.Number number8 = new jxl.write.Number(6,
							2 * i + 3, 2.5, getNormolCell1(0));
					ws0.addCell(number8);
				}

				{// HOME
					jxl.write.Number number9 = new jxl.write.Number(7,
							2 * i + 2, 2.5, getNormolCell1(0));
					ws0.addCell(number9);

					jxl.write.Number number10 = new jxl.write.Number(7,
							2 * i + 3, 2.5, getNormolCell1(0));
					ws0.addCell(number10);
				}

				{// AWAY
					jxl.write.Number number11 = new jxl.write.Number(8,
							2 * i + 2, 2.5, getNormolCell1(0));
					ws0.addCell(number11);

					jxl.write.Number number12 = new jxl.write.Number(8,
							2 * i + 3, 2.5, getNormolCell1(0));
					ws0.addCell(number12);
				}

				{// DRAW
					jxl.write.Number number13 = new jxl.write.Number(9,
							2 * i + 2, 2.5777, getNormolCell1(0));
					ws0.addCell(number13);

					Label L14 = new Label(9, 2 * i + 3, "", getNormolCell());// 第3行
					ws0.addCell(L14);
				}

				{// %
					jxl.write.Number number15 = new jxl.write.Number(10,
							2 * i + 2, .5, getNormolCell1(1));
					ws0.addCell(number15);

					jxl.write.Number number16 = new jxl.write.Number(10,
							2 * i + 3, .5, getNormolCell1(1));
					ws0.addCell(number16);
				}

				{// Line(H):1
					Label L17 = new Label(11, 2 * i + 2, "", getNormolCell());// 第3行
					ws0.addCell(L17);

					jxl.write.Number number18 = new jxl.write.Number(11,
							2 * i + 3, 2.5, getNormolCell1(0));
					ws0.addCell(number18);
				}

				{// Line(H):2
					Label L19 = new Label(12, 2 * i + 2, "", getNormolCell());// 第3行
					ws0.addCell(L19);

					jxl.write.Number number20 = new jxl.write.Number(12,
							2 * i + 3, 0, getNormolCell1(0));
					ws0.addCell(number20);
				}

				{// HOME:1
					jxl.write.Number number21 = new jxl.write.Number(13,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number21);

					jxl.write.Number number22 = new jxl.write.Number(13,
							2 * i + 3, 2.186, getNormolCell1(0));
					ws0.addCell(number22);
				}

				{// HOME:2
					jxl.write.Number number23 = new jxl.write.Number(14,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number23);

					jxl.write.Number number24 = new jxl.write.Number(14,
							2 * i + 3, 2.186, getNormolCell1(0));
					ws0.addCell(number24);
				}

				{// AWAY:1
					jxl.write.Number number25 = new jxl.write.Number(15,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number25);

					jxl.write.Number number26 = new jxl.write.Number(15,
							2 * i + 3, 2.186, getNormolCell1(0));
					ws0.addCell(number26);
				}

				{// AWAY:2
					jxl.write.Number number27 = new jxl.write.Number(16,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number27);

					jxl.write.Number number28 = new jxl.write.Number(16,
							2 * i + 3, 2.186, getNormolCell1(0));
					ws0.addCell(number28);
				}

				{// DRAW:1
					jxl.write.Number number29 = new jxl.write.Number(17,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number29);

					Label L30 = new Label(17, 2 * i + 3, "", getNormolCell());// 第3行
					ws0.addCell(L30);
				}

				{// DRAW:2
					jxl.write.Number number31 = new jxl.write.Number(18,
							2 * i + 2, 2.025, getNormolCell1(0));
					ws0.addCell(number31);

					Label L32 = new Label(18, 2 * i + 3, "", getNormolCell());// 第3行
					ws0.addCell(L32);
				}

				{// %
					jxl.write.Number number33 = new jxl.write.Number(19,
							2 * i + 2, .5, getNormolCell1(1));
					ws0.addCell(number33);

					jxl.write.Number number34 = new jxl.write.Number(19,
							2 * i + 3, .5, getNormolCell1(1));
					ws0.addCell(number34);
				}

			}*/

			// end of format FB header
			/*
			 * Label l = new Label(0, 2, "姓名", getTitle());// 第3行 ws.addCell(l);
			 * l = new Label(1, 2, "電話", getTitle()); ws.addCell(l); l = new
			 * Label(2, 2, "地址", getTitle()); ws.addCell(l); l = new Label(0, 3,
			 * "小祝", getNormolCell());// 第4行 ws.addCell(l); l = new Label(1, 3,
			 * "1314***0974", getNormolCell()); ws.addCell(l); l = new Label(2,
			 * 3, "武漢武昌", getNormolCell()); ws.addCell(l); l = new Label(0, 4,
			 * "小施", getNormolCell());// 第5行 ws.addCell(l); l = new Label(1, 4,
			 * "1347***5057", getNormolCell()); ws.addCell(l); l = new Label(2,
			 * 4, "武漢武昌", getNormolCell()); ws.addCell(l); ws.setColumnView(0,
			 * 20);// 設置列寬 ws.setColumnView(1, 20); ws.setColumnView(2, 40);
			 * ws.setRowView(0, 400);// 設置行高 ws.setRowView(1, 400);
			 * ws.setRowView(2, 500); ws.setRowView(3, 500); ws.setRowView(4,
			 * 500);
			 */
		} catch (RowsExceededException e1) {
			e1.printStackTrace();
		} catch (WriteException e1) {
			e1.printStackTrace();
		}

		// 輸出流
		try {
			wwb.write();
		} catch (IOException ex) {
			// TODO 自動生成 catch
			ex.printStackTrace();
		}
		// 關閉流
		try {
			wwb.close();
		} catch (WriteException ex) {
			// TODO 自動生成 catch
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO 自動生成 catch
			ex.printStackTrace();
		}
		// outStream.close();
		System.out.println("寫入成功！\n");
	}

	public void readExc(File filename) throws BiffException, IOException {

		Workbook wb = Workbook.getWorkbook(filename);
		Sheet s = wb.getSheet(0);// 第1個sheet
		Cell c = null;
		int row = s.getRows();// 總行數
		int col = s.getColumns();// 總列數
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				c = s.getCell(j, i);
				System.out.print(c.getContents() + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * 設置頭的樣式
	 * 
	 * @return
	 */
	public static WritableCellFormat getHeader0(Colour color) {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12,
				WritableFont.BOLD);// 義字體
		try {
			font.setColour(Colour.BLACK);// 藍色字體
		} catch (WriteException e1) {
			// TODO
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			// format.setBorder(Border.ALL, BorderLineStyle.THIN,
			// Colour.BLACK);// 黑色邊框
			format.setBackground(color);// 灰色背景

		} catch (WriteException e) {
			// TODO
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 設置頭的樣式
	 * 
	 * @return
	 */
	public static WritableCellFormat getHeader1() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12,
				WritableFont.BOLD);// 義字體
		try {
			font.setColour(Colour.BLACK);// 藍色字體
		} catch (WriteException e1) {
			// TODO
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			// format.setBorder(Border.ALL, BorderLineStyle.THIN,
			// Colour.BLACK);// 黑色邊框
			format.setBackground(Colour.PINK);// 黃色背景
		} catch (WriteException e) {
			// TODO
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 設置標題樣式
	 * 
	 * @return
	 */
	public static WritableCellFormat getTitle0(Colour color) {
		WritableFont font = new WritableFont(WritableFont.TIMES, 14);
		try {
			font.setColour(Colour.BLUE);// 藍色字體
		} catch (WriteException e1) {
			// TODO
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font);

		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setBackground(color);// 黃色背景

		} catch (WriteException e) {
			// TODO
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 設置其他單元格樣式
	 * 
	 * @return
	 */
	public static WritableCellFormat getNormolCell() {// 12號字體，上下左右居中，帶黑色邊框
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		} catch (WriteException e) {
			// TODO
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 設置其他單元格樣式
	 * 
	 * @return
	 */
	public static WritableCellFormat getNormolCell1(int type) {// 12號字體，上下左右居中，帶黑色邊框
		NumberFormat nf;
		switch (type) {
		case 0:
			nf = new NumberFormat("##0.###");
			break;
		case 1:
			nf = new NumberFormat("####.##%");
			break;
		default:
			nf = new NumberFormat("####.###");
			break;
		}

		WritableCellFormat format = new WritableCellFormat(nf);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		} catch (WriteException e) {
			// TODO
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector v = new Vector();
		jexecelReport jr = new jexecelReport("SRS.xls");
		jr.create(v);
	}

}
