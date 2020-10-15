package com.dronegcs.mavlink.is.drone.mission.survey.grid;

import java.util.ArrayList;
import java.util.List;

import com.geo_tools.Coordinate;
import com.geo_tools.shapes.LineCoordinates;
import com.geo_tools.shapes.LineSampler;
import com.geo_tools.shapes.LineTools;

public class EndpointSorter {
	private static final int MAX_NUMBER_OF_CAMERAS = 2000;

	private List<Coordinate> gridPoints = new ArrayList<Coordinate>();
	private List<LineCoordinates> grid;
	private Double sampleDistance;
	private List<Coordinate> cameraLocations = new ArrayList<Coordinate>();

	public EndpointSorter(List<LineCoordinates> grid, Double sampleDistance) {
		this.grid = grid;
		this.sampleDistance = sampleDistance;
	}

	public void sortGrid(Coordinate lastpnt, boolean sort) throws Exception {
		while (grid.size() > 0) {
			if (sort) {				
				LineCoordinates closestLine = LineTools.findClosestLineToPoint(lastpnt, grid);
				Coordinate secondWp = processOneGridLine(closestLine, lastpnt, sort);
				lastpnt = secondWp;
			}else{
				LineCoordinates closestLine = grid.get(0);
				Coordinate secondWp = processOneGridLine(closestLine, lastpnt, sort);
				lastpnt = secondWp;
			}
		}
	}

	private Coordinate processOneGridLine(LineCoordinates closestLine, Coordinate lastpnt, boolean sort)
			throws Exception {
		Coordinate firstWP, secondWp;
		firstWP = closestLine.getClosestEndpointTo(lastpnt);
		secondWp = closestLine.getFarthestEndpointTo(lastpnt);

		grid.remove(closestLine);

		updateCameraLocations(firstWP, secondWp);
		gridPoints.add(firstWP);
		gridPoints.add(secondWp);
		
		if (cameraLocations.size() > MAX_NUMBER_OF_CAMERAS) {
			throw new Exception("Too many camera positions");
		}
		return secondWp;
	}

	private void updateCameraLocations(Coordinate firstWP, Coordinate secondWp) {
		List<Coordinate> cameraLocationsOnThisStrip = new LineSampler(firstWP, secondWp)
				.sample(sampleDistance);
		cameraLocations.addAll(cameraLocationsOnThisStrip);
	}

	public List<Coordinate> getSortedGrid() {
		return gridPoints;
	}

	public List<Coordinate> getCameraLocations() {
		return cameraLocations;
	}

}
