package com.dronegcs.mavlink.is.drone.mission.survey.grid;

import java.util.ArrayList;
import java.util.List;

import com.geo_tools.Coordinate;
import com.geo_tools.shapes.LineCoordinates;
import com.geo_tools.shapes.LineTools;

public class Trimmer {
	List<LineCoordinates> trimedGrid = new ArrayList<LineCoordinates>();

	public Trimmer(List<LineCoordinates> grid, List<LineCoordinates> polygon) {
		for (LineCoordinates gridLine : grid) {
			ArrayList<Coordinate> crosses = findCrossings(polygon, gridLine);
			processCrossings(crosses, gridLine);
		}
	}

	private ArrayList<Coordinate> findCrossings(List<LineCoordinates> polygon, LineCoordinates gridLine) {

		ArrayList<Coordinate> crossings = new ArrayList<Coordinate>();
		for (LineCoordinates polyLine : polygon) {
			try {
				crossings.add(LineTools.FindLineIntersection(polyLine, gridLine));
			} catch (Exception e) {
			}
		}

		return crossings;
	}

	private void processCrossings(ArrayList<Coordinate> crosses, LineCoordinates gridLine) {
		switch (crosses.size()) {
		case 0:
		case 1:
			break;
		case 2:
			trimedGrid.add(new LineCoordinates(crosses.get(0), crosses.get(1)));
			break;
		default: // TODO handle multiple crossings in a better way
			trimedGrid.add(LineTools.findExternalPoints(crosses));
		}
	}

	public List<LineCoordinates> getTrimmedGrid() {
		return trimedGrid;
	}

}
