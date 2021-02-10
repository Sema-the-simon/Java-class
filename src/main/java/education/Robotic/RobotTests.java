package education.Robotic;
import education.Robotic.Robot.*;

import static education.Robotic.Robot.moveRobot;

public class RobotTests {
        public static void main(String[] args) {
            Robot robot = new Robot(0,0, Direction.LEFT);
            moveRobot(robot, 0, 0);
            System.out.println(robot.toString());
        }
}
