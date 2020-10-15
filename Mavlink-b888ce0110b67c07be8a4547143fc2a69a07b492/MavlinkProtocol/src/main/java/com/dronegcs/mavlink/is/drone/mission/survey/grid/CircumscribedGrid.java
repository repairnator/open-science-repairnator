package com.dronegcs.mavlink.is.drone.mission.survey.grid;

import java.util.ArrayList;
import java.util.List;

import com.geo_tools.CoordBounds;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;
import com.geo_tools.shapes.LineCoordinates;

public class CircumscribedGrid {
	private static final int MAX_NUMBER_OF_LINES = 200;
	List<LineCoordinates> grid = new ArrayList<LineCoordinates>();
	private Coordinate gridLowerLeft;
	private double extrapolatedDiag;
	private Double angle;

	public CircumscribedGrid(List<Coordinate> polygonPoints, Double angle, Double lineDist)
			throws Exception {
		this.angle = angle;

		findPolygonBounds(polygonPoints);
		drawGrid(lineDist);
	}

	private void drawGrid(Double lineDist) throws GridWithTooManyLines {
		int lines = 0;
		Coordinate startPoint = gridLowerLeft;
		while (lines * lineDist < extrapolatedDiag) {
			Coordinate endPoint = GeoTools.newCoordFromBearingAndDistance(startPoint, angle, extrapolatedDiag);

			LineCoordinates line = new LineCoordinates(startPoint, endPoint);
			grid.add(line);

			startPoint = GeoTools.newCoordFromBearingAndDistance(startPoint, angle + 90, lineDist);
			lines++;
			if (lines > MAX_NUMBER_OF_LINES) {
				throw new GridWithTooManyLines();
			}
		}
	}

	private void findPolygonBounds(List<Coordinate> polygonPoints) {
		CoordBounds bounds = new CoordBounds(polygonPoints);
		Coordinate middlePoint = bounds.getMiddle();
		gridLowerLeft = GeoTools.newCoordFromBearingAndDistance(middlePoint, angle - 135,
				bounds.getDiag());
		extrapolatedDiag = bounds.getDiag() * 1.5;
	}

	public List<LineCoordinates> getGrid() {
		return grid;
	}

	public class GridWithTooManyLines extends Exception {
		private static final long serialVersionUID = 1L;
	}

}
