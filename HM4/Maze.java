// Name : Xunzhi Li
// Id: 10457500
// Homework 3:Maze
package hw4;
import java.util.ArrayList;
import java.util.Stack;

public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;
	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	// PROBLEM 1
	public boolean findMazePath(int x, int y) {
//		invalid x or y
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1) {
			return false;
		}
//        If the current cell being analyzed does not have NON_BACKGROUND
		else if (maze.getColor(x, y) != NON_BACKGROUND) {
			return false;
		}
//        If the current cell being analyzed is the exit cell
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH);
			return true;
		} else {
//        	has at least one successful neighbors
			maze.recolor(x, y, TEMPORARY);
			if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
				maze.recolor(x, y, PATH);	
				return true;
			} else {
//       it is a dead end
				maze.recolor(x, y, TEMPORARY);
				return false;
			}
		}
	}

	// ADD METHOD FOR PROBLEM 2
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
		Stack<PairInt> trace = new Stack<PairInt>();
		findMazePathStackBased(x, y, result, trace);
		return result;

	}

	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
//		invalid x or y
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1) {
			return;
		}
//        If the current cell being analyzed does not have NON_BACKGROUND
		else if (maze.getColor(x, y) != NON_BACKGROUND) {
			return;
		} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
//			find the exit successfully
			maze.recolor(x, y, PATH);
//			push this point into trace(update location)
			trace.push(new PairInt(x, y));
//			add the path into path_resut arraylist
			result.add(new ArrayList<>(trace));
//			recolor back to Non_background for next path
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
			return;
		} else {
//			has at least one successful neighbors
			maze.recolor(x, y, PATH);
//			push the this point into trace(update location)
			trace.push(new PairInt(x, y));
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x, y + 1, result, trace);
			findMazePathStackBased(x - 1, y, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);		
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
			return;

		}
	}

	// ADD METHOD FOR PROBLEM 3
	// returns the shortest path in the list of paths

	public ArrayList<PairInt> findMazePathMin(int x, int y) {
//		ArrayList<PairInt> result = new ArrayList<PairInt>();
//        Stack<PairInt> trace = new Stack<PairInt>();
		ArrayList<ArrayList<PairInt>> all_paths = findAllMazePaths(x, y);
		//if all_path is empty,then return an null one
		//if there is only one path,just return it 	
		
		if (all_paths.size() == 0 ){
			ArrayList<PairInt> null_path = new ArrayList<PairInt>();
			return null_path;
		}else if (all_paths.size() == 1){
			return all_paths.get(0);
		}
			else {	
			//	get the first path from all_paths for comparing 		
			ArrayList<PairInt> min_path = all_paths.get(0);
			for (int i = 0; i < all_paths.size(); i++) {
				if(all_paths.get(i).size()< min_path.size() ) {
					min_path = all_paths.get(i);
				}
			}
			return min_path;
		}
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */
