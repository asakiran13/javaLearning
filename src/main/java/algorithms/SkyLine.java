package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class SkyLineRunner {
	class Point implements Comparable<Point>{
		int x;
		boolean isStart;
		int height;

		Point(int x, boolean isStart, int height){
			this.x = x; this.isStart = isStart; this.height = height;
		}

		@Override
		public int compareTo(Point o){
			if(this.x != o.x){
				return this.x - o.x;
			}

			return ((o.isStart) ? o.height : -o.height) + ((isStart) ? -height : height);
//			if(isStart && o.isStart){
//				return o.height - height;
//			}
//			if(!isStart && !o.isStart){
//				return height - o.height;
//			}
//			if(isStart) return -1;
//			return 1;
		}
	}
	public List<List<Integer>> getSkyline(int[][] buildings) {
		Point[] points = new Point[buildings.length * 2];
		int index = 0;
		for(int[] building : buildings){
			points[index++] = new Point(building[0], true, building[2]);
			points[index++] = new Point(building[1], false, building[2]);
		}
		Arrays.sort(buildings);
		TreeMap<Integer, Integer> queue = new TreeMap<>();
		List<List<Integer>> result = new ArrayList<>();
		queue.put(0,0);
		for(Point point : points){
			if(point.isStart){
				if(point.height > queue.lastKey()){
					result.add(new ArrayList<>(Arrays.asList(point.x, point.height)));
				}
				queue.put(point.height, point.height);
			}else{
				int prevMax = queue.lastKey();
				queue.remove(point.height);
				if(point.height == prevMax){
					result.add(new ArrayList<>(Arrays.asList(point.x, queue.lastKey())));
				}
			}
		}
		return result;
	}
}
public class SkyLine {
}
