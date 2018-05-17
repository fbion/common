package ebook.thinking.chapter10.controller;

public class GreenhouseController {

	public static void main(String[] args) {
		GreenhouseControls gc = new GreenhouseControls();
		gc.addEvent(gc.new Bell(900));
		Event[] eventList = {
				gc.new ThermostatNight(0),
				gc.new LightOn(200),
				gc.new LightOff(400),
				gc.new WaterOff(600),
				gc.new WaterOn(800),
				gc.new ThermostatDay(1400),
		};
		gc.addEvent(gc.new Restart(2000, eventList));
		if(args.length == 0) {
			gc.addEvent(new GreenhouseControls.Terminate(new Integer(3000)));
			gc.run();
		}
		System.out.println("End");
	}
}
