package education.Robotic;

public class Robot {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    int x;
    int y;
    Direction dir;

    public Robot(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Direction getDirection() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void turnLeft() {
        dir = switch (dir) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case DOWN -> Direction.RIGHT;
            case RIGHT -> Direction.UP;
        };
    }

    public void turnRight() {
        dir = switch (dir) {
            case UP -> Direction.RIGHT;
            case LEFT -> Direction.UP;
            case DOWN -> Direction.LEFT;
            case RIGHT -> Direction.DOWN;
        };
    }

    public void stepForward() {
        // шаг в направлении взгляда
        // за один шаг робот изменяет одну свою координату на единицу
        switch (dir) {
            case UP -> y++;
            case LEFT -> x--;
            case DOWN -> y--;
            case RIGHT -> x++;
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        while (robot.getDirection() != Direction.UP) robot.turnRight();
        if (toY > 0) {
            while (robot.getY() < toY) {
                robot.stepForward();
            }
        }
        if (toX > 0) {
            robot.turnRight();
            while (robot.getX() < toX) {
                robot.stepForward();
            }
        }
        if (toY < 0) {
            while (robot.getDirection() != Direction.DOWN) robot.turnRight();
            while (robot.getY() > toY) {
                robot.stepForward();
            }
        }
        if (toX < 0) {
            if (robot.getDirection() == Direction.UP) {
                robot.turnLeft();
            } else {
                while (robot.getDirection() != Direction.LEFT) robot.turnRight();
            }
            while (robot.getX() > toX) {
                robot.stepForward();
            }
        }
    }
    @Override
    public String toString(){
        return "X:" + x + " Y:" + y + " Direction:" + dir;
    }
}