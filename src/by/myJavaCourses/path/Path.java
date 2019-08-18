package by.myJavaCourses.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path {

	

	private int[][] array;
	private int[][] obstacle;
	private List<Point> list = new ArrayList<>();

	Path(int[][] obstacle) {
		this.obstacle = obstacle;
	}

	public void push(Point p, int n) {
		if (array[p.getY()][p.getX()] <= n) {
			return;
		}
		else {
		array[p.getY()][p.getX()] = n; 
		list.add(p); 
		}
	}

	
	public Point checkOfEmptiness() {
		if (list.isEmpty()) {
			return null;
		}
		else {
		return (Point) list.remove(0);
		}
	}

	public Point[] find(Point start, Point end) {
		int tx = 0, ty = 0, n = 0, t = 0;
		Point p;
		array = new int[10][10];
	
		for (int i = 0; i < array.length; i++)
			Arrays.fill(array[i], Integer.MAX_VALUE);
		push(start, 0); 
		while ((p = checkOfEmptiness()) != null) { 
			if (p.equals(end)) {
				System.out.print("Hайден путь длины ");
				System.out.println(n);
			}
			
			n = array[p.getY()][p.getX()] + obstacle[p.getY()][p.getX()];

			
			if ((p.getY() + 1 < obstacle.length) && obstacle[p.getY() + 1][p.getX()] != 0)
				push(new Point(p.getX(), p.getY() + 1), n);
			if ((p.getY() - 1 >= 0) && (obstacle[p.getY() - 1][p.getX()] != 0))
				push(new Point(p.getX(), p.getY() - 1), n);
			if ((p.getX() + 1 < obstacle[p.getY()].length) && (obstacle[p.getY()][p.getX() + 1] != 0))
				push(new Point(p.getX() + 1, p.getY()), n);
			if ((p.getX() - 1 >= 0) && (obstacle[p.getY()][p.getX() - 1] != 0))
				push(new Point(p.getX() - 1, p.getY()), n);
		}
		if (array[end.getY()][end.getX()] == Integer.MAX_VALUE) {
			System.out.println("Пути не существует !!!");
			return null;
		} else
			System.out.println("Поиск завершен, пpойдемся по пути !!!");
		List<Point> path = new ArrayList<>();
		path.add(end);
		int x = end.getX();
		int y = end.getY();
		n = Integer.MAX_VALUE; 
		while ((x != start.getX()) || (y != start.getY())) { 
		
			if (array[y + 1][x] < n) {
				tx = x;
				ty = y + 1;
				t = array[y + 1][x];
			}
			
			if (array[y - 1][x] < n) {
				tx = x;
				ty = y - 1;
				t = array[y - 1][x];
			}
		
			if (array[y][x + 1] < n) {
				tx = x + 1;
				ty = y;
				t = array[y][x + 1];
			}
			if (array[y][x - 1] < n) {
				tx = x - 1;
				ty = y;
				t = array[y][x - 1];
			}
			x = tx;
			y = ty;
			n = t; 
			path.add(new Point(x, y));
		}
		
		Point[] result = new Point[path.size()];
		t = path.size();
		for (Object point : path)
			result[--t] = (Point) point;
		return result;
	}

	
	public class Point {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point)) {
				return false;

			} else {
				return (((Point) o).getX() == x) && (((Point) o).getY() == y);
			}
		}

		@Override
		public int hashCode() {
			return Integer.valueOf(x) ^ Integer.valueOf(y);
		}

		@Override
		public String toString() {
			return "x: " + Integer.valueOf(x).toString() + " y:" + Integer.valueOf(y).toString();
		}

	};
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] array = {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 },
				{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 0 }, 
				{ 0, 1, 0, 2, 1, 1, 0, 0, 1, 0 }, 																										// лабиpинт
				{ 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 }, 
				{ 0, 1, 0, 1, 0, 1, 1, 0, 1, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		Path pathFinder = new Path(array);
		Point start = pathFinder.new Point(1, 1);
		Point end = pathFinder.new Point(4, 3);
		Point[] path = pathFinder.find(start, end); 

		for (Point p : path) {
			System.out.println(p);
		}
		System.out.println();
	}
}
