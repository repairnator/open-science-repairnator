package com.dronegcs.mavlink.is.drone.mission.survey.grid;

import java.util.ArrayList;
import java.util.List;

import com.geo_tools.Coordinate;
import com.geo_tools.shapes.PolylineTools;

public class Grid {
	public List<Coordinate> gridPoints;
	private List<Coordinate> cameraLocations;

	public Grid(List<Coordinate> list, List<Coordinate> cameraLocations) {
		this.gridPoints = list;
		this.cameraLocations = cameraLocations;
	}

	public Grid(Grid grid) {		
		if (grid.gridPoints != null) {
			gridPoints = new ArrayList<>();
			for (Coordinate coord2d : grid.gridPoints) gridPoints.add(new Coordinate(coord2d));
		}
		
		if (grid.cameraLocations != null) {
			cameraLocations = new ArrayList<>();
			for (Coordinate coord2d : grid.cameraLocations) cameraLocations.add(new Coordinate(coord2d));
		}
	}

	public double getLength() {
		return PolylineTools.getPolylineLength(gridPoints);
	}

	public int getNumberOfLines() {
		return gridPoints.size() / 2;
	}

	public List<Coordinate> getCameraLocations() {
		return cameraLocations;
	}

	public int getCameraCount() {
		return getCameraLocations().size();
	}

}