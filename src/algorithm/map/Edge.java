package algorithm.map;

public class Edge implements Comparable<Edge> {
    private Point start;
    private Point end;
    private int weight;
    private boolean d;

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public Edge() {
    }

    public Edge(Point start, Point end, int weight) {
        super();
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int compareTo(Edge o) {
        return o.getWeight() > this.getWeight() ? -1 : o.getWeight() == this.getWeight() ? 0 : 1;
    }

    @Override
    public String toString() {
        return "edge : "+this.getStart().getLabel()+" to "+this.getEnd().getLabel()+" weight: "+this.getWeight();
    }

    
}
