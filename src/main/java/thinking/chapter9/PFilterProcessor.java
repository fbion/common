package thinking.chapter9;
//an example for adapter pattern
//using proxy
interface Processor1 {
	String name();
	Object process(Object input);
}

class Apply1 {
	public static void process(Processor1 p, Object s) {
		System.out.println("using processor " + p.name());
		System.out.println(p.process(s));
	}
}

class Waveform {
	private static long counter;
	private final long id = counter++;
	public String toString() {
		return "Waveform " + id;
	}
}

class Filter{
	public String name(){
			return getClass().getSimpleName();
	}
	public Waveform process(Waveform input) {
		return input;
	}
}

class LowPass extends Filter {
	double cutoff;
	
	public LowPass(double cutoff) {
		this.cutoff = cutoff;
	}
	public Waveform process(Waveform input) {
		return input;
	}
}

class HighPass extends Filter {
	double cutoff;
	
	public HighPass(double cutoff) {
		this.cutoff = cutoff;
	}
	public Waveform process(Waveform input) {
		return input;
	}
}


class BrandPass extends Filter {
	double lowCutoff,highCutoff;
	
	public BrandPass(double lowCutoff,double highCutoff){
		this.lowCutoff = lowCutoff;
		this.highCutoff = highCutoff;
	}
	public Waveform process(Waveform input) {
		return input;
	}
}

class FilterAdapter implements Processor1 {
	Filter filter;

	FilterAdapter(Filter filter){
		this.filter = filter;
	}
	
	@Override
	public String name() {
		return filter.name();
	}

	@Override
	public Object process(Object input) {
		return filter.process((Waveform)input);
	}
	
}

public class PFilterProcessor {

	public static void main(String[] args) {
		Waveform w = new Waveform();
		Apply1.process(new FilterAdapter(new LowPass(1.0)), w);
		Apply1.process(new FilterAdapter(new HighPass(2.0)), w);
		Apply1.process(new FilterAdapter(new BrandPass(3.0,4.0)), w);
	}
}
