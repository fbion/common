package thinking.chapter14.factory.example;

import thinking.chapter14.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 工厂设计模式
 * @ClassName: RegisteredFactories
 * @Description: 工厂设计模式
 * @author zzh
 * @date 2015年10月11日 下午11:35:53
 * @version 1.0
 */
public class RegisteredFactories
{

    public static void main(String[] args)
    {

    }
}

class Part
{
    @Override
    public String toString()
    {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();

    static
    {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }

    private static Random rand = new Random();

    public static Part createRandom()
    {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter extends Part
{
}

class FuelFilter extends Filter
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<FuelFilter>
    {
        public FuelFilter create()
        {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<AirFilter>
    {
        public AirFilter create()
        {
            return new AirFilter();
        }
    }
}

class CabinAirFilter extends Filter
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<CabinAirFilter>
    {
        public CabinAirFilter create()
        {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<OilFilter>
    {
        public OilFilter create()
        {
            return new OilFilter();
        }
    }
}

class Belt extends Part
{
}

class FanBelt extends Belt
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<FanBelt>
    {
        public FanBelt create()
        {
            return new FanBelt();
        }
    }
}

class PowerSteeringBelt extends Belt
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<PowerSteeringBelt>
    {
        public PowerSteeringBelt create()
        {
            return new PowerSteeringBelt();
        }
    }
}

class GeneratorBelt extends Belt
{
    public static class Factory implements
            thinking.chapter14.factory.Factory<GeneratorBelt>
    {
        public GeneratorBelt create()
        {
            return new GeneratorBelt();
        }
    }
}
