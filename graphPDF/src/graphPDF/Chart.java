package graphPDF;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class Chart {

	public static void main(String[] args) {
		writeChartToPDF(generateBarChart(), 500, 400, "barchart.pdf");
		writeChartToPDF(generatePieChart(), 500, 400, "piechart.pdf");
	}

	public static void writeChartToPDF(JFreeChart chart, int width, int height,
			String fileName) {
		
		PdfWriter writer = null;

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height,
					new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

	public static JFreeChart generatePieChart() {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Ukrainians", 77.8);
		dataSet.setValue("Russians", 17.3);
		dataSet.setValue("Belarusians", 0.6);
		dataSet.setValue("Moldovans", 0.5);
		dataSet.setValue("Crimean Tatars", 0.5);
		dataSet.setValue("Bulgarians", 0.4);
		dataSet.setValue("Hungarians", 0.3);
		dataSet.setValue("Romanians", 0.3);
		dataSet.setValue("Poles", 0.3);
		dataSet.setValue("Other", 1.7);

		JFreeChart chart = ChartFactory.createPieChart(
				"Ethnic composition of the population of Ukraine, 2001 Census", dataSet, true, true, false);

		return chart;
	}

	public static JFreeChart generateBarChart() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.setValue(2814258, "Population", "Kiev");
		dataSet.setValue(1441622	, "Population", "Kharkiv");
		dataSet.setValue(1008200, "Population", "Odessa");
		dataSet.setValue(1001962, "Population", "Dnipropetrovsk");
		dataSet.setValue(962024, "Population", "Donetsk");
		dataSet.setValue(772600, "Population", "Zaporizhia");
		dataSet.setValue(760026, "Population", "Lviv");
		dataSet.setValue(664499, "Population", "Kryvyi Rih");

		JFreeChart chart = ChartFactory.createBarChart(
				"Largest cities or towns of Ukraine", "Cities", "Population",
				dataSet, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}

}
