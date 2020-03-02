public class GameObject {
    private int x1;
    private int y1;

    public GameObject(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }

    public int getX() {
        return x1;
    }

    public void setX(int x) {
        this.x1 = x1;
    }

    public int getY() {
        return y1;
    }

    public void setY(int y) {
        this.y1 = y1;
    }

    @Override
    public String toString() {
        return "{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                '}';
    }
}
