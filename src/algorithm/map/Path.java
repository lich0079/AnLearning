package algorithm.map;

import java.util.ArrayList;
import java.util.List;

public class Path {
    @Override
    public String toString() {
        return path.get(path.size()-1).getLabel()+" "+distance;
    }
    private int distance;
    private List<Point> path=new ArrayList<Point>();
    private Point start;
    private Point end;
    
    public Point getStart() {
        return start;
    }
    public void setStart(Point start) {
        this.start = start;
    }
    public Point getEnd() {
        return end;
    }
    public void setEnd(Point end) {
        this.end = end;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public List<Point> getPath() {
        return path;
    }
    public void setPath(List<Point> path) {
        this.path = path;
    }
}
