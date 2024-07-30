public class CommandPattern {

    //  Define Command Interface
    interface Command {
        void execute();
    }

    //  Implement Concrete Commands
    static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    //  Implement Invoker Class
    static class RemoteControl {
        private Command command;

        public RemoteControl(Command command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
        }
    }

    //  Implement Receiver Class
    static class Light {
        public void turnOn() {
            System.out.println("The light is on.");
        }

        public void turnOff() {
            System.out.println("The light is off.");
        }
    }

        public static void main(String[] args) {
        // Create a Light object
        Light livingRoomLight = new Light();

        // Create command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create remote control with the light on command
        RemoteControl remote = new RemoteControl(lightOn);
        System.out.println("Testing Light On Command:");
        remote.pressButton(); // Light should turn on

        // Change command to light off
        remote = new RemoteControl(lightOff);
        System.out.println("Testing Light Off Command:");
        remote.pressButton(); // Light should turn off
    }
}

//OUTPUT
// Testing Light On Command:
// The light is on.
// Testing Light Off Command:
// The light is off.