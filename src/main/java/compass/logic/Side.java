package compass.logic;

public class Side {

    public String name;

    public Side(String name, int beginDeg, int endDeg) {
        this.name = name;
        this.beginDeg = beginDeg;
        this.endDeg = endDeg;
    }

    public int beginDeg;

    public int endDeg;

    public Boolean isSide(int x) {
        int y, z;
        if (beginDeg > endDeg) {
            y = beginDeg - endDeg;
            z = 359 - beginDeg;

            beginDeg = 0;
            endDeg = 359 - y;

            if ((x + z) < 359) {
                return (beginDeg < (x + z) && (x + z) < endDeg);
            }
            else {
                return (beginDeg < (x + z - 359) && (x + z - 359) < endDeg);
            }
        }
        else {
            return (beginDeg < x && x < endDeg);
        }
    }
}
