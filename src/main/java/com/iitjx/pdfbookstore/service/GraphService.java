package com.iitjx.pdfbookstore.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class GraphService {
	public byte[] createBarChart(String graphTitle, List<String> xAxisLabels,
			List<Integer> yAxisValues, List<String> xAxisLabelsLastWeek,
			List<Integer> yAxisValuesLastWeek, String xLabel, String yLabel) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < xAxisLabels.size(); i++) {
			dataset.setValue(yAxisValues.get(i), "This Week",
					xAxisLabels.get(i));
		}
		for (int i = 0; i < xAxisLabelsLastWeek.size(); i++) {
			dataset.setValue(yAxisValuesLastWeek.get(i), "Last Week",
					xAxisLabelsLastWeek.get(i));
		}
		JFreeChart barChart = ChartFactory.createBarChart(graphTitle, xLabel,
				yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
		return convertToImage(barChart, 600, 450);
	}

	public byte[] createPieChart(String graphTitle, List<String> labels,
			List<Integer> values) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (int i = 0; i < labels.size(); i++) {
			dataset.setValue(labels.get(i), values.get(i));
		}
		JFreeChart pieChart = ChartFactory.createPieChart(graphTitle, dataset,
				true, true, false);
		return convertToImage(pieChart, 500, 450);
	}

	public byte[] convertToImage(JFreeChart chart, int width, int height) {
		BufferedImage bufferedImage = chart.createBufferedImage(width, height);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] image = {};
		try {
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
			image = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
