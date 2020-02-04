package ensta;

/**
 * AbstractShip
 */
public class AbstractShip {
    private Character label;
    private String name;
    private int size;
    private Direction direction;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public AbstractShip(Character label, String name, int size, Direction direction) {
        this.label = label;
        this.name = name;
        this.size = size;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "AbstractShip [label=" + label + ", name=" + name + ", size=" + size + ", direction=" + direction + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractShip other = (AbstractShip) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size != other.size)
            return false;
        if (direction != other.direction)
            return false;
        return true;
    }

    public void changeCap(Direction direction) {
        this.direction = direction;
    }

}